package queue;

public class ArrayQueueADT {
    private int count = 0, size = 1, left = 0, right = 0;
    private Object[] elements =
            new Object[size];

    /* Pre: queue != null
            && element != null
       Post: n' = n + 1
             && a[n'] == element
             &&  ∀ i: 0 < i <= n a[i]' = a[i]
    */
    public static void enqueue(ArrayQueueADT queue, Object element) {
        assert element != null;
        ensureCapacity(queue);
        queue.elements[queue.right] = element;
        queue.count++;
        queue.right = (queue.right + 1) % queue.size;
    }

    /* Pre: queue != null
       Post: if (n == q.length) : q.length' = 2 * q.length
             if (n < q.length) : q.length' = q.length
    */
    private static void ensureCapacity(ArrayQueueADT queue) {
        if ((queue.left == 0 && queue.right == queue.size - 1) || (queue.left == queue.right + 1)) {
            int j = 0;
            Object[] newElements = new Object[2 * queue.size];
            for (int i = queue.left; i < queue.size; i++) {
                newElements[j] = queue.elements[i];
                j++;
            }
            for (int i = 0; i < queue.left; i++) {
                newElements[j] = queue.elements[i];
                j++;
            }
            queue.elements = newElements;
            queue.left = 0;
            queue.right = queue.size - 1;
            queue.size *= 2;
        }
    }

    /* Pre: queue != null
            && n > 0
       Post: n' = n - 1
             && R = a[1]
             &&  ∀ i: 0 < i <= n' a[i]' = a[i]
    */
    public static Object dequeue(ArrayQueueADT queue) {
        assert queue.count > 0;

        Object res = queue.elements[queue.left];
        queue.elements[queue.left] = -1;
        queue.left = (queue.left + 1) % queue.size;
        queue.count--;
        return res;
    }

    /* Pre: queue != null
            && n > 0
       Post: n' = n
             && R = a[1]
             &&  ∀ i: 0 < i <= n' a[i]' = a[i]
    */
    public static Object element(ArrayQueueADT queue) {
        assert queue.count > 0;

        return queue.elements[queue.left];
    }

    /* Pre: queue != null
       Post: R = n
             && n' = n
             && ∀ i: 0 < i <= n' a[i]' = a[i]
    */
    public static int size(ArrayQueueADT queue) {
        return queue.count;
    }

    /* Pre: queue != null
       Post: && n' = n
             && ∀ i: 0 < i <= n' a[i]' = a[i]
             && R = "[" + str(a[1]) + ", " + ... + ", " + str(a[n]) + "]"
    */
    public static String toStr(ArrayQueueADT queue) {
        int i = queue.left;
        StringBuilder stringBuilder = new StringBuilder("[");
        if (queue.count != 0) {
            stringBuilder.append(queue.elements[i]);
            i = (i + 1) % queue.size;
            while (i != queue.right) {
                stringBuilder.append(", ");
                stringBuilder.append(queue.elements[i]);
                i = (i + 1) % queue.size;
            }
        }
        stringBuilder.append("]");
        return stringBuilder.toString();
    }

    /* Pre: queue != null
       Post: n' = 0
             && a = []
    */
    public static void clear(ArrayQueueADT queue) {
        queue.size = 1;
        queue.elements = new Object[queue.size];
        queue.count = 0;
        queue.left = 0;
        queue.right = 0;
    }

    /* Pre: queue != null
       Post: && n' = n
             && ∀ i: 0 < i <= n' a[i]' = a[i]
             && R = (n == 0)
    */
    public static boolean isEmpty(ArrayQueueADT queue) {
        return queue.count == 0;
    }
}
