import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int sum6 = 0;
        int next;
        int num = scanner.nextInt();
        for (int i = 0; i < num; i++) {
            next = scanner.nextInt();
            if (next % 6 == 0) {
                sum6 += next;
            }
        }
        System.out.println(sum6);
    }
}