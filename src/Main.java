import tasks.Epic;
import tasks.ManagerTask;
import tasks.SubTask;
import tasks.Task;

public class Main {

    public static void main(String[] args) {
        ManagerTask managerTask = new ManagerTask();

        //Создание 2 задач
        Task task1 = new Task("Задача 1","Описание задачи 1",managerTask.getTaskID(),"NEW");
        managerTask.createTask(task1.getID(), task1);
        Task task2 = new Task("Задача 2","Описание задачи 2",managerTask.getTaskID(),"NEW");
        managerTask.createTask(task2.getID(), task2);

        // Создание эпика с 2 подзадачами
        Epic epic1 = new Epic("Эпик 1","Эпик с двумя подзадачами",
                managerTask.getEpicID(), null, null);
        managerTask.createEpic(epic1.getID(), epic1);
        SubTask subTask1 = new SubTask("Подзадача 1-2","Подзадача 1 для эпика 1",
                managerTask.getSubTaskID(), "NEW", epic1.getID());
        managerTask.createSubTask(subTask1.getID(), subTask1, epic1);
        SubTask subTask2 = new SubTask("Подзадача 2-1","Подзадача 2 для эпика 1",
                managerTask.getSubTaskID(), "NEW", epic1.getID());
        managerTask.createSubTask(subTask2.getID(), subTask2, epic1);

        // Создание эпика с 1 подзадачей
        Epic epic2 = new Epic("Эпик 2", "Эпик с одной подзадачей",
                managerTask.getEpicID(), null, null);
        managerTask.createEpic(epic2.getID(), epic2);

        SubTask subTask3 = new SubTask("Подзадача 1-2", "Подзадача 1 для эпика 2",
                managerTask.getSubTaskID(), "IN_PROGRESS", epic2.getID());
        managerTask.createSubTask(subTask3.getID(), subTask3, epic2);

        // Распечатайте списки эпиков, задач и подзадач, через System.out.println(..)
        System.out.println(managerTask.getAllEpics());
        System.out.println(managerTask.getAllTasks());
        System.out.println(managerTask.getAllSubTasks());

        // Изменение статуса задачи
        System.out.println(task1.getStatus());
        task1.setStatus("DONE");
        System.out.println(task1.getStatus());

        //Изменение статуса подзадачи, проверка статуса эпика
        System.out.println(epic2.getStatus());
        System.out.println(subTask3.getStatus());
        subTask3.setStatus("DONE", epic2);
        System.out.println(epic2.getStatus());
        System.out.println(subTask3.getStatus());

        //И, наконец, попробуйте удалить одну из задач и один из эпиков.
        System.out.println(managerTask.getTask(task1.getID()));
        managerTask.deleteTask(task1.getID());
        System.out.println(managerTask.getTask(task1.getID()));
        System.out.println(managerTask.getEpic(epic2.getID()));
        managerTask.deleteEpic(epic2.getID());
        System.out.println(managerTask.getEpic(epic2.getID()));
    }
}
