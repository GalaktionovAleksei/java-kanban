package ManagerTask;
import tasks.Task;

import java.util.*;

public class InMemoryHistoryManager implements HistoryManager {

    private Map<Integer, Node> tasksHistory = new HashMap<>();
    private CustomLinkedList nodeList = new CustomLinkedList();

    @Override
    public void remove(int id){
        if (tasksHistory.containsKey(id)) {
            nodeList.removeNode(tasksHistory.get(id));
            tasksHistory.remove(id);
        }
    }

    @Override
    public void add(Task task) {
        if (tasksHistory.containsKey(task.getID())){
            nodeList.removeNode(tasksHistory.get(task.getID()));
            tasksHistory.put(task.getID(), nodeList.linkLast(task));
        } else {
            tasksHistory.put(task.getID(), nodeList.linkLast(task));
        }
    }

    @Override
    public List<Task> getHistory() {
        return nodeList.getTasks();
    }

    public class CustomLinkedList{
        private Node head;
        private Node tail;


        private List getTasks(){
            ArrayList<Task> tasksList = new ArrayList<>();
            Node nodeFromList = head;
            while (nodeFromList != null){
                tasksList.add(nodeFromList.data);
                nodeFromList = nodeFromList.next;
            }
            return tasksList;
        }

        private void removeNode(Node node){
                if (head == node){
                    head = head.next;
                    head.prev = null;
                } else if (tail == node) {
                    tail = tail.prev;
                    tail.next = null;
                } else {
                    node.prev.next = node.next;
                    node.next.prev = node.prev;
                }
        }

        private Node linkLast(Task task) {
            if (head == null){
                head = new Node(null, task, null);
                return head;
            } else if (tail == null) {
                tail = new Node(null, task, head);
                head.next = tail;
            } else {
                Node oldTail = tail;
                tail = new Node(null, task, oldTail);
                oldTail.next = tail;
            }
            return tail;
        }
    }
}
