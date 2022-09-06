package ManagerTask;
import tasks.*;
import java.util.Collection;
import java.util.List;

public interface TaskManager {

    List<Task> getHistory();

    void createTask(Task task);

    void createEpic(Epic epic);

    void createSubTask(SubTask subTask, Epic epic);

    Collection<Task> getAllTasks();

    Collection<Epic> getAllEpics();

    Collection<SubTask> getAllSubTasks();

    void deleteTask(int id);

    void deleteEpic(int id);

    void deleteSubTask(int subTaskID);

    Task getTask(int id);

    Epic getEpic(int id);

    SubTask getSubTask(int id);

    void updateTask(Task task);

    void updateEpic(Epic epic);

    void updateSubTask(SubTask subTask);

    void deleteAllTasks();

    void deleteAllEpics();

    void deleteAllSubTasks();

}