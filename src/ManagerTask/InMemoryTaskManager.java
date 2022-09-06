package ManagerTask;
import tasks.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;


public class InMemoryTaskManager implements TaskManager{
    private HashMap<Integer, Task> tasks = new HashMap<>();
    private HashMap<Integer, Epic> epics = new HashMap<>();
    private HashMap<Integer, SubTask> subTasks = new HashMap<>();
    private int ID;
    HistoryManager historyManager = Managers.getDefaultHistory();

    @Override
    public List<Task> getHistory(){
        return historyManager.getHistory();
    }

    @Override
    public void createTask(Task task){
        int id = getID();
        tasks.put(id, task);
        task.setID(id);
    }

    @Override
    public void createEpic(Epic epic){
        int id = getID();
        epics.put(id, epic);
        epic.setID(id);
    }

    @Override
    public void createSubTask(SubTask subTask, Epic epic){
        int id = getID();
        subTasks.put(id, subTask);
        epic.addSubTaskToEpic(subTask);
        subTask.setID(id);
        epic.setStatus();
    }

    @Override
    public Collection<Task> getAllTasks(){
        return tasks.values();
    }

    @Override
    public Collection<Epic> getAllEpics(){
        return epics.values();
    }

    @Override
    public Collection<SubTask> getAllSubTasks(){
        return subTasks.values();
    }

    @Override
    public void deleteTask(int id){
        tasks.remove(id);
    }

    @Override
    public void deleteEpic(int id){
        ArrayList<SubTask> subTaskForDelete = new ArrayList<>();
        /*if (epics.containsKey(id)) {
            ArrayList<SubTask> epicSubTasks = epics.get(id).getListSubTasks();
            epicSubTasks.forEach(subTasks::remove);
            epics.remove(id);
        }*/
        for (SubTask subTask: subTasks.values()){
            if (subTask.getSubTaskEpicID() == id){
                subTaskForDelete.add(subTask);
            }
        } for (int i = 0; i < subTaskForDelete.size(); i++){
            subTasks.remove(subTaskForDelete.get(i).getID());
        }
        epics.remove(id);
    }

    @Override
    public void deleteSubTask(int subTaskID){
        int epicID = subTasks.get(subTaskID).getSubTaskEpicID();
        epics.get(epicID).deleteSubTaskFromEpic(subTasks.get(subTaskID));
        subTasks.remove(subTaskID);
        epics.get(epicID).setStatus();
    }

    @Override
    public Task getTask(int id){
        historyManager.add(tasks.get(id));
        return tasks.get(id);
    }

    @Override
    public Epic getEpic(int id){
        historyManager.add(epics.get(id));
        return epics.get(id);
    }

    @Override
    public SubTask getSubTask(int id){
        historyManager.add(subTasks.get(id));
        return subTasks.get(id);
    }

    @Override
    public void updateTask(Task task){
        if (tasks.containsKey(task.getID())){
            tasks.put(task.getID(), task);
        }
    }

    @Override
    public void updateEpic(Epic epic){
        if (epics.containsKey(epic.getID())){
            epic.setListSubTasks(epics.get(epic.getID()).getListSubTasks());
            epics.put(epic.getID(), epic);
            epic.setStatus();
        }
    }

    @Override
    public void updateSubTask(SubTask subTask){
        if (subTasks.containsKey(subTask.getID())){
            int epicID = subTasks.get(subTask.getID()).getSubTaskEpicID();
            epics.get(epicID).deleteSubTaskFromEpic(subTasks.get(subTask.getID()));
            subTasks.put(subTask.getID(), subTask);
            epics.get(epicID).addSubTaskToEpic(subTasks.get(subTask.getID()));
            epics.get(epicID).setStatus();
        }
    }

    @Override
    public void deleteAllTasks(){
        tasks.clear();
    }

    @Override
    public void deleteAllEpics(){
        epics.clear();
        deleteAllSubTasks();
    }

    @Override
    public void deleteAllSubTasks(){
        subTasks.clear();
        for (Integer id : epics.keySet()) {
            epics.get(id).deleteAllSubTaskFromEpic();
            epics.get(id).setStatus();
        }
    }

    @Override
    public int getID(){
        return ID++;
    }

}