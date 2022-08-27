package tasks;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

public class ManagerTask {
    private HashMap<Integer, Task> tasks = new HashMap<>();
    private HashMap<Integer, Epic> epics = new HashMap<>();
    private HashMap<Integer, SubTask> subTasks = new HashMap<>();
    private int taskID;
    private int epicID;
    private int subTaskID;

    public void createTask(int id, Task task){
        tasks.put(id, task);
    }

    public void createEpic(int id, Epic epic){
        epics.put(id, epic);
    }

    public void createSubTask(int id, SubTask subTask){
        subTasks.put(id, subTask);
    }

    public void addSubTaskToEpic(Epic epic,SubTask subTask){
        epic.listSubTasks.add(subTask);
        epic.setStatus();
    }

    public Collection<Task> getAllTasks(){
        return tasks.values();
    }

    public Collection<Epic> getAllEpics(){
        return epics.values();
    }

    public Collection<SubTask> getAllSubTasks(){
        return subTasks.values();
    }

    public void deleteTask(int key){
        tasks.remove(key);
    }

    public void deleteEpic(int key){
        epics.remove(key);
    }

    public void deleteSubTask(int key){
        subTasks.remove(key);
    }

    public Task getTask(int id){
        return tasks.get(id);
    }

    public Epic getEpic(int id){
        return epics.get(id);
    }

    public SubTask getSubTask(int id){
        return subTasks.get(id);
    }

    public void updateTask(int taskID, Task task){
        tasks.remove(taskID);
        tasks.put(taskID, task);
    }

    public void updateEpic(Integer epicID, Epic epic){
        ArrayList<SubTask> saveSubTasks = new ArrayList<>();
        for (int i = 1; i <= subTasks.size(); i++){
            if (epicID == subTasks.get(i).epicID) {
                saveSubTasks.add(subTasks.get(i));
            }
        }
        epics.remove(epicID);
        epic.listSubTasks = saveSubTasks;
        epics.put(epicID, epic);
    }

    public void updateSubTask(Epic epic, SubTask subTask,int subTaskID){
        epic.listSubTasks.remove(subTask);
        subTasks.put(subTaskID, subTask);
        addSubTaskToEpic(epic,subTask);
    }

    public void deleteAllTasks(){
        tasks.clear();
    }

    public void deleteAllEpics(){
        epics.clear();
    }

    public void deleteAllSubTasks(){
        subTasks.clear();
    }

    public int getTaskID(){
        return taskID +=1;
    }

    public int getEpicID(){
        return epicID +=1;
    }

    public int getSubTaskID(){
        return subTaskID +=1;
    }


}