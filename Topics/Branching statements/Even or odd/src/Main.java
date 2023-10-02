import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int next = scanner.nextInt();
        while (next != 0) {
            if (next % 2 == 0) {
                System.out.println("even");
            } else {
                System.out.println("odd");
            }
            next = scanner.nextInt();
        }
    }
}