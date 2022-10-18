package ManagerTask;
import tasks.Task;

import java.util.*;

public class InMemoryHistoryManager implements HistoryManager {

    private final int maxHistorySize = 10;
    private Map<Integer, Node> tasksHistory = new HashMap<>(0);
    private CustomLinkedList node = new CustomLinkedList();

    @Override
    public void remove(int id){
        if (tasksHistory.containsKey(id)) {
            node.deleteFromHistory(tasksHistory.get(id).data);
            tasksHistory.get(id).removeNode(tasksHistory.get(id));
            tasksHistory.remove(id);
        }
    }

    @Override
    public void add(Task task) {
        if (tasksHistory.containsValue(tasksHistory.get(task.getID()))) {
            tasksHistory.get(task.getID()).removeNode(tasksHistory.get(task.getID()));
            node.deleteFromHistory(task);
            tasksHistory.put(task.getID(), node.linkLast(task));
        } else {
            tasksHistory.put(task.getID(), node.linkLast(task));
        }
    }

    @Override
    public List<Task> getHistory() {
        return node.getTasks();
    }

    public class CustomLinkedList<T>{
        private Node head;
        private Node tail;
        List<T> nodePad = new ArrayList<>();

        List<T> getTasks(){
            return nodePad;
        }

        void deleteFromHistory(T task){
            nodePad.remove(task);
        }

        Node linkLast(T task) {
            if (head == null){
                head = new Node(null, task, null);
                nodePad.add(task);
                return head;
            } else if (tail == null) {
                tail = new Node(null, task, head);
                head.next = tail;
            } else {
                Node oldTail = tail;
                tail = new Node(null, task, oldTail);
                oldTail.next = tail;
            }
            nodePad.add(task);
            return tail;
        }
    }
}
