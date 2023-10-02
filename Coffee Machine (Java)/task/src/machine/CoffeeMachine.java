package machine;

import java.util.Scanner;


public class CoffeeMachine {
    public static int[] water_per_cup = {250, 350, 200}; // mL for espresso, latte and cappuccino
    public static int[] milk_per_cup = {0, 75, 100};   // mL ...
    public static int[] beans_per_cup = {16, 20, 12};  // grams ...
    public static int[] cost_per_cup = {4, 7, 6};  // $cost for espresso, latte and cappuccino
    public static int money = 550;  // cash in machine
    public static int cupCount = 9; // number of disposable cups
    public static int waterLevel = 400;  // mL of water in machine
    public static int milkLevel = 540;  // mL of milk in machine
    public static int beansLevel = 120; // grams of beans in machine

    public static void showLevels() {
        System.out.println("The coffee machine has:");
        System.out.println(waterLevel + " ml of water");
        System.out.println(milkLevel + " ml of milk");
        System.out.println(beansLevel + " g of coffee beans");
        System.out.println(cupCount + " disposable cups");
        System.out.println("$" + money + " of money");
    }

    // Check if sufficient resources
    public static boolean checkLevels(int cType) {
        int i = cType - 1;
        if (waterLevel < water_per_cup[i]) {
            System.out.println("Sorry, not enough water!");
            return false;
        }
        if (milkLevel < milk_per_cup[i]) {
            System.out.println("Sorry, not enough milk!");
            return false;
        }
        if (beansLevel < beans_per_cup[i]) {
            System.out.println("Sorry, not enough beans!");
            return false;
        }
        if (cupCount < 1) {
            System.out.println("Sorry, not enough cups!");
            return false;
        }
        return true;
    }

    // Buy a coffee
    public static void doBuy(int cType) {
        if (checkLevels(cType)) {
            int i = cType - 1;
            System.out.println("I have enough resources, making you a coffee!");
            waterLevel -= water_per_cup[i];
            milkLevel -= milk_per_cup[i];
            beansLevel -= beans_per_cup[i];
            cupCount -= 1;
            money += cost_per_cup[i];
        }
    }

    // refill the coffee machine
    public static void doFill(Scanner s) {
        System.out.println("Write how many ml of water you want to add:");
        waterLevel += s.nextInt();
        System.out.println("Write how many ml of milk you want to add:");
        milkLevel += s.nextInt();
        System.out.println("Write how many grams of coffee beans you want to add:");
        beansLevel += s.nextInt();
        System.out.println("Write how many disposable cups you want to add:");
        cupCount += s.nextInt();
    }

    // Take money from Coffee machine
    public static void doTake() {
        System.out.println("I gave you $" + money);
        money = 0;
    }
    // START HERE
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean running = true;
        while (running) {
            System.out.println("Write action (buy, fill, take, remaining, exit):");
            String action = scanner.nextLine();
            switch (action) {
                case "buy":
                    System.out.println("What do you want to buy? 1 - espresso, 2 - latte, 3 - cappuccino, back - to main menu:");
                    String selection = scanner.next();
                    switch (selection) {
                        case "back":
                            break;
                        case "1":
                            doBuy(1);  // buy espresso
                            break;
                        case "2":
                            doBuy(2);  // buy latte
                            break;
                        case "3":
                            doBuy(3);  // buy cappuccino
                            break;
                        default:
                            System.out.println("?");
                            break;
                    }
                    break;
                case "fill":
                    doFill(scanner);  // do fill action
                    break;
                case "take":
                    doTake();  // do take action
                    break;
                case "remaining":
                    showLevels();
                    break;
                case "exit":
                    running = false;
                    break;
                default:
                    // invalid action?
                    break;
            }
        }
    }
}
