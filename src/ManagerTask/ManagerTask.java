package ManagerTask;
import tasks.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

/* Привет!
До констант уже дошёл, добавил)
По удалению подзадач из мапы при удалении эпика, я так понимаю мне не хватает знаний про "некие" итераторы,
    по этому сделал через два цикла.
Остальные замечания - исправил! (надеюсь:)
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
        ArrayList<SubTask> subTaskForDelete = new ArrayList<>();
        for (SubTask subTask: subTasks.values()){
            if (subTask.getSubTaskEpicID() == id){
                subTaskForDelete.add(subTask);
            }
        } for (int i = 0; i < subTaskForDelete.size(); i++){
            subTasks.remove(subTaskForDelete.get(i).getID());
        }
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
        if (tasks.containsKey(task.getID())){
            tasks.put(task.getID(), task);
        }
    }

    public void updateEpic(Epic epic){
        if (epics.containsKey(epic.getID())){
            epic.setListSubTasks(epics.get(epic.getID()).getListSubTasks());
            epics.put(epic.getID(), epic);
            epic.setStatus();
        }
    }

    public void updateSubTask(SubTask subTask){
        if (subTasks.containsKey(subTask.getID())){
            int epicID = subTasks.get(subTask.getID()).getSubTaskEpicID();
            epics.get(epicID).deleteSubTaskFromEpic(subTasks.get(subTask.getID()));
            subTasks.put(subTask.getID(), subTask);
            epics.get(epicID).addSubTaskToEpic(subTasks.get(subTask.getID()));
            epics.get(epicID).setStatus();
        }
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
        return ID++;
    }

}