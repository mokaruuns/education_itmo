package queue;

public class ArrayQueueModule {
    private static int count = 0, size = 1, left = 0, right = 0;
    private static Object[] elements = new Object[size];

    /* Pre: element != null
       Post: n' = n + 1
             && a[n'] == element
             &&  ∀ i: 0 < i <= n a[i]' = a[i]
    */
    public static void enqueue(Object element) {
        assert element != null;
        ensureCapacity();
        elements[right] = element;
        count++;
        right = (right + 1) % size;
    }

    /* Pre:
      Post: if (n == q.length) : q.length' = 2 * q.length
            if (n < q.length) : q.length' = q.length
   */
    private static void ensureCapacity() {
        if ((left == 0 && right == size - 1) || (left == right + 1)) {
            int j = 0;
            Object[] newElements = new Object[2 * size];
            for (int i = left; i < size; i++) {
                newElements[j] = elements[i];
                j++;
            }
            for (int i = 0; i < left; i++) {
                newElements[j] = elements[i];
                j++;
            }
            elements = newElements;
            left = 0;
            right = size - 1;
            size *= 2;
        }
    }

    /* Pre: n > 0
      Post: n' = n - 1
            && R = a[1]
            &&  ∀ i: 0 < i <= n' a[i]' = a[i]
   */
    public static Object dequeue() {
        assert count > 0;

        Object res = elements[left];
        elements[left] = null;
        left = (left + 1) % size;
        count--;
        return res;
    }

    /* Pre: n > 0
       Post: n' = n
             && R = a[1]
             &&  ∀ i: 0 < i <= n' a[i]' = a[i]
    */
    public static Object element() {
        assert count > 0;

        return elements[left];
    }

    /* Pre:
       Post: R = n
             && n' = n
             && ∀ i: 0 < i <= n' a[i]' = a[i]
    */
    public static int size() {
        return count;
    }

    /* Pre:
       Post: && n' = n
             && ∀ i: 0 < i <= n' a[i]' = a[i]
             && R = "[" + str(a[1]) + ", " + ... + ", " + str(a[n]) + "]"
    */
    public static String toStr() {
        int i = left;
        StringBuilder stringBuilder = new StringBuilder("[");
        if (count != 0) {
            stringBuilder.append(elements[i]);
            i = (i + 1) % size;
            while (i != right) {
                stringBuilder.append(", ");
                stringBuilder.append(elements[i]);
                i = (i + 1) % size;
            }
        }
        stringBuilder.append("]");
        return stringBuilder.toString();
    }

    /* Pre: queue != null
       Post: n' = 0
             && a = []
    */
    public static void clear() {
        size = 1;
        elements = new Object[size];
        count = 0;
        left = 0;
        right = 0;
    }

    /* Pre: queue != null
       Post: && n' = n
             && ∀ i: 0 < i <= n' a[i]' = a[i]
             && R = (n == 0)
    */
    public static boolean isEmpty() {
        return count == 0;
    }
}
