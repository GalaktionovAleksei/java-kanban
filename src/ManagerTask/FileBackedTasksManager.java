package ManagerTask;
import tasks.Epic;
import tasks.Status;
import tasks.SubTask;
import tasks.Task;

import java.io.*;

public class FileBackedTasksManager extends InMemoryTaskManager implements TaskManager{
    private final File file;
    public FileBackedTasksManager(String pathToFile) {
        this.file = new File(pathToFile);
    }

    public static void main(String[] args){
        TaskManager manager = Managers.getDefault("file");
        Task task1 = new Task("Задача 1", "Описание задачи 1", 0, Status.IN_PROGRESS);
        manager.createTask(task1);

    }

    private void save(){
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(this.file))){

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private String toString(Task task){
        if (task instanceof SubTask){
            return null;
        } else {
            return null;
        }
    }

    @Override
    public void createTask(Task task){
        super.createTask(task);
        save();
    }

    @Override
    public void createEpic(Epic epic){
        super.createEpic(epic);
        save();
    }
    @Override
    public void createSubTask(SubTask subTask, Epic epic) {
        super.createSubTask(subTask, epic);
        save();
    }

    @Override
    public void deleteTask(int id){
        super.deleteTask(id);
        save();
    }

    @Override
    public void deleteEpic(int id) {
        super.deleteEpic(id);
        save();
    }

    @Override
    public void deleteSubTask(int subTaskID){
        super.deleteSubTask(subTaskID);
        save();
    }

    @Override
    public void updateTask(Task task){
        super.updateTask(task);
        save();
    }

    @Override
    public void updateEpic(Epic epic){
        super.updateEpic(epic);
        save();
    }

    @Override
    public void updateSubTask(SubTask subTask){
        super.updateSubTask(subTask);
        save();
    }

    @Override
    public void deleteAllTasks(){
        super.deleteAllTasks();
        save();
    }

    @Override
    public void deleteAllEpics(){
        super.deleteAllEpics();
        save();
    }

    @Override
    public void deleteAllSubTasks(){
        super.deleteAllSubTasks();
        save();
    }
}
