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
        historyManager.remove(id);
        if (tasks.containsKey(id)) {
            tasks.remove(id);
        }
    }

    @Override
    public void deleteEpic(int id) {
        historyManager.remove(id);
        if (epics.containsKey(id)) {
            ArrayList<SubTask> epicSubTasks = epics.get(id).getListSubTasks();
            for (SubTask subTask : epicSubTasks) {
                subTasks.remove(subTask.getID());
                historyManager.remove(subTask.getID());
            }
            epics.remove(id);
        }
    }

    @Override
    public void deleteSubTask(int subTaskID){
        historyManager.remove(subTaskID);
        if (subTasks.containsKey(subTaskID)) {
            int epicID = subTasks.get(subTaskID).getSubTaskEpicID();
            epics.get(epicID).deleteSubTaskFromEpic(subTasks.get(subTaskID));
            subTasks.remove(subTaskID);
            epics.get(epicID).setStatus();
        }
    }

    @Override
    public Task getTask(int id){
        if (tasks.get(id) != null) {
            historyManager.add(tasks.get(id));
        }
        return tasks.get(id);
    }

    @Override
    public Epic getEpic(int id){
        if (epics.get(id) != null) {
            historyManager.add(epics.get(id));
        }
        return epics.get(id);
    }

    @Override
    public SubTask getSubTask(int id){
        if (subTasks.get(id) != null) {
            historyManager.add(subTasks.get(id));
        }
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
        for (Integer id : tasks.keySet()){
            historyManager.remove(id);
        }
        tasks.clear();
    }

    @Override
    public void deleteAllEpics(){
        for (Integer id : epics.keySet()){
            historyManager.remove(id);
        }
        epics.clear();
        deleteAllSubTasks();
    }

    @Override
    public void deleteAllSubTasks(){
        for (Integer id : subTasks.keySet()){
            historyManager.remove(id);
            epics.get(subTasks.get(id).getSubTaskEpicID()).deleteSubTaskFromEpic(subTasks.get(id));
        }
        subTasks.clear();
    }

    private int getID(){
        return ID++;
    }

}