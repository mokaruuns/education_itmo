package search;

public class BinarySearchMissing {

    /* Pre: ∀ i > 0: a[i] >= a[i+1]
            ∀ i: a[i] = a[i]'
            a.size > 0
       Post: print(R) &&
             ∀ i: a[i] = a[i]' &&
             R ∈ [0, n) : a[R] = x && (∀ i < R  a[i] > a[R]) && (∀ j >= R  a[j] <= a[R]) ||
             R ∈ (-n-1, -1] : ∀ i a[i] != x && (R = -R' - 1) : (∀ j < R' a[j] > x)  && (∀ j <= R' a[j] < x)
     */
    public static void main(String[] args) {
        int x = getArgument(args);
        int[] array = getArray(args);
        System.out.println(binarySearchRecursive(array, x) & binarySearchIterative(array, x));
    }

    /* Pre: ∀ i: a[i] is number &&
            ∀ i >= 0: a[i] >= a[i+1] &&
            ∀ i: a[i] = a[i]'
       Post: ∀ i: a[i] = a[i]' &&
             R ∈ [0, n) : (a[R] = x) && (∀ i < R  a[i] > a[R]) && (∀ j >= R  a[j] <= a[R]) ||
             R ∈ (-n-1, -1] : (∀ i a[i] != x) && (R = -R' - 1) : (∀ j < R' a[j] > x)  && (∀ j <= R' a[j] < x)
     */
    private static int binarySearchIterative(int[] array, int value) {
        int l = 0, r = array.length;
        // a[r] = -INF
        int m;
        while (l < r) {
            // ∀ i >= 0: a[i] >= a[i+1] && l < r
            m = (l + r) / 2;
            // l <= m < r
            if (array[m] > value) {
                // ∀ i >= 0: a[i] >= a[i+1] && x < a[m]
                l = m + 1;
                // (∀ i: 0 <= i <= m && ∀ j: r <= j <= a.size) a[i] > x >= a[j]
            } else {
                // ∀ i >= 0: a[i] >= a[i+1] && x >= a[m]
                r = m;
                // (∀ i: 0 <= i <= l-1 && ∀ j: m <= j <= a.size) a[i] > x >= a[j]
            }
        }
        /* ∀ i >= 0: a[i] >= a[i+1] &&
           l >= r ->
           -> a[l-1] > x >= a[r] && l-1 >= r-1 ->
           -> a[r-1] > x >= a[r] ->
           -> R = r
        */
        return getPosition(array, r, value);
    }

    /* Pre: ∀ i: a[i] is number &&
            ∀ i >= 0: a[i] >= a[i+1] &&
            ∀ i: a[i] = a[i]'
       Post: ∀ i: a[i] = a[i]' &&
             R ∈ [0, n) : (a[R] = x) && (∀ i < R  a[i] > a[R]) && (∀ j >= R  a[j] <= a[R]) ||
             R ∈ (-n-1, -1] : (∀ i a[i] != x) && (R = -R' - 1) : (∀ j < R' a[j] > x)  && (∀ j <= R' a[j] < x)
     */
    private static int binarySearchRecursive(int[] array, int value) {
        int l = 0, r = array.length;
        if (l == r) {
            // ∀ i >= 0: a[i] >= a[i+1]
            return -1;
            // x!= ø
        }
        // ∀ i >= 0: a[i] >= a[i+1] && l < r
        int m = (l + r) / 2;
        if (value >= array[m]) {
            return binarySearchRecursive(array, value, l, m);
            // ∀ i >= 0: a[i] >= a[i+1] && x >= a[m]
            // (∀ i: 0 <= x <= l && ∀ j: m <= j < a.size) a[i] > x >= a[j]
        } else {
            // ∀ i >= 0: a[i] >= a[i+1] && x < a[m]
            // (∀ i: 0 <= x <= m && ∀ j: r <= j < a.size) a[i] > x >= a[j]
            return binarySearchRecursive(array, value, m + 1, r);
        }
    }

    /* Pre: ∀ i:  &&
            ∀ i >= 0: a[i] >= a[i+1] &&
            ∀ i: a[i] = a[i]' &&
            ∀ i: l <= i < r : a[r] < a[i] <= a[l]
       Post: ∀ i: a[i] = a[i]' &&
             R ∈ [l, r) : (a[R] = x) && (∀ i < R  a[i] > a[R]) && (∀ j >= R  a[j] <= a[R]) ||
             R ∈ (-r-1, -l-1] : (∀ i a[i] != x) && (R = -R' - 1) : (∀ j < R' a[j] > x)  && (∀ j <= R' a[j   ] < x)
     */
    private static int binarySearchRecursive(int[] array, int value, int l, int r) {
        if (!(l < r)) {
            // ∀ i >= 0: a[i] >= a[i+1] &&
            // l >= r ->
            // -> a[l-1] > x >= a[r] && l-1 >= r-1 ->
            // -> a[r-1] > x >= a[r] ->
            // -> R = r
            return getPosition(array, r, value);
        }
        // ∀ i >= 0: a[i] >= a[i+1] && l < r
        int m = (l + r) / 2;
        if (value >= array[m]) {
            // ∀ i >= 0: a[i] >= a[i+1] && x >= a[m]
            // (∀ i: 0 <= x <= l && ∀ j: m <= j < a.size) a[i] > x >= a[j]
            return binarySearchRecursive(array, value, l, m);
        } else {
            // ∀ i >= 0: a[i] >= a[i+1] && x < a[m]
            // (∀ i: 0 <= x <= m && ∀ j: r <= j < a.size) a[i] > x >= a[j]
            return binarySearchRecursive(array, value, m + 1, r);
        }
    }

    /* Pre: ∀ i: a[i] is number &&
            ∀ i >= 0: a[i] >= a[i+1] &&
            ∀ i: a[i] = a[i]'
       Post: ∀ i: a[i] = a[i]' &&
             R ∈ [0, n) : a[R] = x && (∀ i < R  a[i] > a[R]) && (∀ j >= R  a[j] <= a[R]) ||
             R ∈ (-n-1, -1] : ∀ i a[i] != x && (R = -R' - 1) : (∀ j < R' a[j] > x)  && (∀ j <= R' a[j] < x)
     */
    private static int getPosition(int[] array, int pos, int value) {
        // r < a.size && a[r] == x -> R = r
        // r >= a.size || a[r] != x -> R = -r-1
        if (pos < array.length && array[pos] == value) {
            return pos;
        } else {
            return -pos - 1;
        }
    }

    /* Pre: ∀ i: a[i] is number &&
            a.size > 0 &&
            ∀ i: a[i] = a[i]'
       Post: R = (int) a[0] &&
            ∀ i: a[i] = a[i]'
     */
    private static int getArgument(String[] array) {
        return Integer.parseInt(array[0]);
    }

    /* Pre: ∀ i: a[i] is number &&
            ∀ i: a[i] = a[i]'
       Post:
            ∀ i: a[i] = a[i]'
            R = a'[i], ∀ i: a'[i] == (int) a[i]
     */
    private static int[] getArray(String[] array) {
        int[] result = new int[array.length - 1];
        for (int i = 0; i < array.length - 1; i++) {
            result[i] = Integer.parseInt(array[i + 1]);
        }
        return result;
    }
}