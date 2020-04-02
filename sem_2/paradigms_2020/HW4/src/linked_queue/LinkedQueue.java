package linked_queue;

public class LinkedQueue implements Queue {
    private int size;
    private Node head;
    private Node tail;

    public void enqueue(Object element) {
        assert element != null;
        if (size == 0) {
            tail = new Node(element, tail);
            head = tail;

        } else {
            tail = new Node(element, tail);
        }
        size++;
    }

    public void clear() {
        tail = null;
        head = null;
    }

    public Object dequeue() {
        assert size > 0;

        size--;
        Object result = head.value;
        head = head.next;
        return result;
    }

    public Object element() {
        assert size > 0;

        return tail.value;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    private static class Node {
        private Object value;
        private Node next;

        public Node(Object value, Node next) {
            assert value != null;

            this.value = value;
            this.next = next;
        }
    }
}
