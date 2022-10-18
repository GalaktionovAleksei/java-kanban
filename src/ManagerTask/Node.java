package ManagerTask;

class Node <T> {

    public Node<T> next;
    public T data;
    public Node<T> prev;

    public Node(Node<T> next, T data, Node<T> prev) {
        this.data = data;
        this.next = next;
        this.prev = prev;
    }

    public void removeNode(Node node){
        if (node.next != null && node.prev != null) {
            if (node.next == null) {
                node.prev.next = null;
            } else {
                node.prev.next = node.next;
                node.next.prev = node.prev;
            }
        }
    }
}