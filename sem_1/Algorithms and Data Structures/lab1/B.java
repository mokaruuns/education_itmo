import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        int[] array = new int[101];
        Scanner sc = new Scanner(System.in);
        while (sc.hasNextInt()) {
            array[sc.nextInt()]+=1;
        }
        for (int i = 0; i < 101; i++) {
            for (int j = 0; j < array[i]; j++) {
                System.out.print(i + " ");
            }
        }
    }
}
 
