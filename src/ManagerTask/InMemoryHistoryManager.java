package ManagerTask;
import tasks.Task;
import java.util.LinkedList;
import java.util.List;

public class InMemoryHistoryManager implements HistoryManager {
    private LinkedList<Task> historyList = new LinkedList<>();

    @Override
    public void add(Task task) {
        historyList.addLast(task);
        if (historyList.size() > 10){
            historyList.removeFirst();
        }
    }

    @Override
    public List<Task> getHistory() {
        return new LinkedList<>(historyList);
    }
}
