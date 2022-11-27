import ManagerTask.FileBackedTasksManager;
import ManagerTask.Managers;
import ManagerTask.TaskManager;
import tasks.*;

public class Main {

    public static void main(String[] args) {
        TaskManager manager = Managers.getDefault();

        //После написания менеджера истории проверьте его работу:
        //создайте две задачи
        Task task1 = new Task("Задача 1", "Описание задачи 1", 0, Status.IN_PROGRESS);
        manager.createTask(task1);
        Task task2 = new Task("Задача 2", "Описание задачи 2", 0, Status.NEW);
        manager.createTask(task2);

        // эпик с тремя подзадачами
        Epic epic1 = new Epic("Эпик 1", "Эпик с двумя подзадачами",
                0, null, null);
        manager.createEpic(epic1);
        SubTask subTask1 = new SubTask("Подзадача 1-2", "Подзадача 1 для эпика 1",
                0, Status.NEW, epic1.getID());
        manager.createSubTask(subTask1, epic1);
        SubTask subTask2 = new SubTask("Подзадача 2-1", "Подзадача 2 для эпика 1",
                0, Status.DONE, epic1.getID());
        manager.createSubTask(subTask2, epic1);
        SubTask subTask3 = new SubTask("Подзадача 3-1", "Подзадача 3 для эпика 1",
                0, Status.IN_PROGRESS, epic1.getID());
        manager.createSubTask(subTask3, epic1);

        // эпик без подзадач
        Epic epic2 = new Epic("Эпик 2", "Эпик без подзадач",
                0, null, null);
        manager.createEpic(epic2);

        //запросите созданные задачи несколько раз в разном порядке;
        manager.getTask(task1.getID());
        manager.getEpic(epic2.getID());
        System.out.println(manager.getHistory());
//        manager.getTask(task1.getID());
//        manager.getTask(task1.getID());
//        System.out.println(manager.getHistory());
//        manager.getTask(task2.getID());
//        manager.getTask(task1.getID());
//        System.out.println(manager.getHistory());
        //удалите задачу, которая есть в истории, и проверьте, что при печати она не будет выводиться;
//        manager.deleteTask(task2.getID());
//        System.out.println(manager.getHistory());

        //удалите эпик с тремя подзадачами и убедитесь, что из истории удалился как сам эпик, так и все его подзадачи.
//        manager.getEpic(epic1.getID());
//        manager.getSubTask(subTask1.getID());
//        manager.getSubTask(subTask2.getID());
//        manager.getSubTask(subTask3.getID());
//        System.out.println(manager.getHistory());
//        manager.deleteEpic(epic1.getID());
//        System.out.println(manager.getHistory());
    }
}
