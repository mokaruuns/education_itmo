package search;

public class BinarySearch {
    public static void main(String[] args) {
        int n = args.length;
        if (n < 2) {
            System.out.println(0);
            return;
        }
        int[] a = new int[n - 1];
        int x = Integer.parseInt(args[0]);
        for (int i = 1; i < n; i++) {
            a[i - 1] = Integer.parseInt(args[i]);
        }
        System.out.println(binarySearchIterative(a, 0, n - 1, x));
    }

    static int binarySearchIterative(int[] a, int left, int right, int value) {
        int middle = (left + right) / 2;
        for (int i = 0; i < 32; i++) {
            middle = (left + right) / 2;
            if (a[middle] > value) {
                left = middle;
            } else {
                right = middle;
            }
        }
        return right;
    }
}
