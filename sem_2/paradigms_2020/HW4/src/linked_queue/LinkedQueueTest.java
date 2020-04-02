package linked_queue;

public class LinkedQueueTest {
    public static void fill(LinkedQueue stack) {
        for (int i = 0; i < 10; i++) {
            stack.enqueue(i);
        }
    }

    public static void dump(LinkedQueue stack) {
        while (!stack.isEmpty()) {
            System.out.println(stack.size() + " " +
                stack.element() + " " + stack.dequeue());
        }
    }

    public static void main(String[] args) {
        LinkedQueue stack = new LinkedQueue();
        fill(stack);
        dump(stack);
    }
}
