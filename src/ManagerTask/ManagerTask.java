package ManagerTask;
import tasks.*;
import java.util.Collection;
import java.util.HashMap;

/* Привет!
Вроде я всё поправил, кроме констант, немного не успел по теории до них добраться :)
Единственное, мне кажется, я не совсем правильно тебя понял и сделал не совсем то что ты имел ввиду про методы-"апдейты"
*/

public class ManagerTask {
    private HashMap<Integer, Task> tasks = new HashMap<>();
    private HashMap<Integer, Epic> epics = new HashMap<>();
    private HashMap<Integer, SubTask> subTasks = new HashMap<>();
    private int ID;

    public void createTask(Task task){
        int id = getID();
        tasks.put(id, task);
        task.setID(id);
    }

    public void createEpic(Epic epic){
        int id = getID();
        epics.put(id, epic);
        epic.setID(id);
    }

    public void createSubTask(SubTask subTask, Epic epic){
        int id = getID();
        subTasks.put(id, subTask);
        epic.addSubTaskToEpic(subTask);
        subTask.setID(id);
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

    public void deleteTask(int id){
        tasks.remove(id);
    }

    public void deleteEpic(int id){
        epics.get(id).deleteAllSubTaskFromEpic();
        epics.remove(id);
    }

    public void deleteSubTask(int subTaskID){
        int epicID = subTasks.get(subTaskID).getSubTaskEpicID();
        epics.get(epicID).deleteSubTaskFromEpic(subTasks.get(subTaskID));
        subTasks.remove(subTaskID);
        epics.get(epicID).setStatus();
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

    public void updateTask(Task task){
        if (tasks.get(task.getID()) != null){
            tasks.put(task.getID(), task);
        }
    }

    public void updateEpic(Epic epic){
        epic.setListSubTasks(epics.get(epic.getID()).getListSubTasks());
        if (epics.get(epic.getID()) != null){
            epics.put(epic.getID(), epic);
        }
        epic.setStatus();
    }

    public void updateSubTask(SubTask subTask){
        int epicID = subTasks.get(subTask.getID()).getSubTaskEpicID();
        if (subTasks.get(subTask.getID()) != null){
            epics.get(epicID).deleteSubTaskFromEpic(subTasks.get(subTask.getID()));
            subTasks.put(subTask.getID(), subTask);
            epics.get(epicID).addSubTaskToEpic(subTasks.get(subTask.getID()));
        }
        epics.get(epicID).setStatus();
    }

    public void deleteAllTasks(){
        tasks.clear();
    }

    public void deleteAllEpics(){
        epics.clear();
        deleteAllSubTasks();
    }

    public void deleteAllSubTasks(){
        subTasks.clear();
        for (Integer id : epics.keySet()) {
            epics.get(id).deleteAllSubTaskFromEpic();
            epics.get(id).setStatus();
        }
    }

    public int getID(){
        return ID +=1;
    }

}