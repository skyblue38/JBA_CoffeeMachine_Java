import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int start = scanner.nextInt();
        int end = scanner.nextInt();
        int factor = scanner.nextInt();
        int fcount = 0;
        for (int i = start; i <= end; i++) {
            if (i % factor == 0) {
                fcount++;
            }
        }
        System.out.println(fcount);
    }
}