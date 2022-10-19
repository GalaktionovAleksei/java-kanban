package ManagerTask;
import tasks.Task;

class Node {

    public Node next;
    public Task data;
    public Node prev;

    public Node(Node next, Task data, Node prev) {
        this.data = data;
        this.next = next;
        this.prev = prev;
    }
}