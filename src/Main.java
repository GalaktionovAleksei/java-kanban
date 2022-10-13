import ManagerTask.Managers;
import ManagerTask.TaskManager;
import tasks.*;


public class Main {

    public static void main(String[] args) {
        TaskManager manager = Managers.getDefault();
        //Создайте несколько задач разного типа
        //Создание 2 задач
        Task task1 = new Task("Задача 1","Описание задачи 1",0,Status.IN_PROGRESS);
        manager.createTask(task1);
        Task task2 = new Task("Задача 2","Описание задачи 2",0,Status.NEW);
        manager.createTask(task2);
        // Создание эпика с 2 подзадачами
        Epic epic1 = new Epic("Эпик 1","Эпик с двумя подзадачами",
                0, null, null);
        manager.createEpic(epic1);
        SubTask subTask1 = new SubTask("Подзадача 1-2","Подзадача 1 для эпика 1",
                0, Status.NEW, epic1.getID());
        manager.createSubTask(subTask1, epic1);
        SubTask subTask2 = new SubTask("Подзадача 2-1","Подзадача 2 для эпика 1",
                0, Status.DONE, epic1.getID());
        manager.createSubTask(subTask2, epic1);

        // Создание эпика с 1 подзадачей
        Epic epic2 = new Epic("Эпик 2", "Эпик с одной подзадачей",
                0, null, null);
        manager.createEpic(epic2);
        SubTask subTask3 = new SubTask("Подзадача 1-2", "Подзадача 1 для эпика 2",
                0, Status.IN_PROGRESS, epic2.getID());
        manager.createSubTask(subTask3, epic2);

        //Вызовите разные методы интерфейса TaskManager и напечатайте историю просмотров после каждого вызова
        manager.getSubTask(subTask1.getID());
        System.out.println(manager.getHistory());
        manager.getSubTask(subTask3.getID());
        System.out.println(manager.getHistory());
        manager.getTask(task1.getID());
        System.out.println(manager.getHistory());
        manager.getEpic(epic1.getID());
        System.out.println(manager.getHistory());

    }
}
