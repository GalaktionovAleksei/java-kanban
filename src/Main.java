import tasks.*;
import ManagerTask.ManagerTask;

public class Main {

    public static void main(String[] args) {
        ManagerTask managerTask = new ManagerTask();

        //Создание 2 задач
        Task task1 = new Task("Задача 1","Описание задачи 1",0,Status.IN_PROGRESS);
        managerTask.createTask(task1);
        Task task2 = new Task("Задача 2","Описание задачи 2",0,Status.NEW);
        managerTask.createTask(task2);

        // Создание эпика с 2 подзадачами
        Epic epic1 = new Epic("Эпик 1","Эпик с двумя подзадачами",
                0, null, null);
        managerTask.createEpic(epic1);
        SubTask subTask1 = new SubTask("Подзадача 1-2","Подзадача 1 для эпика 1",
                0, Status.NEW, epic1.getID());
        managerTask.createSubTask(subTask1, epic1);
        SubTask subTask2 = new SubTask("Подзадача 2-1","Подзадача 2 для эпика 1",
                0, Status.DONE, epic1.getID());
        managerTask.createSubTask(subTask2, epic1);

        // Создание эпика с 1 подзадачей
        Epic epic2 = new Epic("Эпик 2", "Эпик с одной подзадачей",
                0, null, null);
        managerTask.createEpic(epic2);
        SubTask subTask3 = new SubTask("Подзадача 1-2", "Подзадача 1 для эпика 2",
                0, Status.IN_PROGRESS, epic2.getID());
        managerTask.createSubTask(subTask3, epic2);

        // Распечатайте списки эпиков, задач и подзадач, через System.out.println(..)
        System.out.println(managerTask.getAllEpics());
        System.out.println(managerTask.getAllTasks());
        System.out.println(managerTask.getAllSubTasks());

        System.out.println("Изменение статуса задачи");
        System.out.println(task1.getStatus());
        task1.setStatus(Status.DONE);
        System.out.println(task1.getStatus());

        System.out.println("Изменение статуса подзадачи, проверка статуса эпика");
        System.out.println(epic2.getStatus());
        System.out.println(subTask3.getStatus());
        subTask3.setStatus(Status.DONE, epic2);
        System.out.println(epic2.getStatus());
        System.out.println(subTask3.getStatus());

        System.out.println("И, наконец, попробуйте удалить одну из задач и один из эпиков.");
        System.out.println(managerTask.getTask(task1.getID()));
        managerTask.deleteTask(task1.getID());
        System.out.println(managerTask.getTask(task1.getID()));
        System.out.println(managerTask.getEpic(epic2.getID()));
        managerTask.deleteEpic(epic2.getID());
        System.out.println(managerTask.getEpic(epic2.getID()));
    }
}
