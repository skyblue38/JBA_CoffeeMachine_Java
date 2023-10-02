package machine;

import java.util.Scanner;

enum mState {STARTUP, READY, BUY, BREWING, FILL1, FILL2, FILL3, FILL4, TAKE, LEVELS, SHUTDOWN}

public class CoffeeMachine {
    int[] water_per_cup = {250, 350, 200}; // mL for espresso, latte and cappuccino
    int[] milk_per_cup = {0, 75, 100};   // mL ...
    int[] beans_per_cup = {16, 20, 12};  // grams ...
    int[] cost_per_cup = {4, 7, 6};  // $cost for espresso, latte and cappuccino
    int money;  // cash in machine
    int cupCount; // number of disposable cups
    int waterLevel;  // mL of water in machine
    int milkLevel;  // mL of milk in machine
    int beansLevel; // grams of beans in machine
    mState running;  // current machine state
    int coffeeType;  // 0=null, 1=espresso, 2=latte, 3=cappuccino
    // Construct a CoffeeMachine instance
    public CoffeeMachine(int water, int milk, int beans, int cups, int money) {
        this.waterLevel = water;
        this.milkLevel = milk;
        this.beansLevel = beans;
        this.cupCount = cups;
        this.money = money;
        this.running = mState.STARTUP;
        this.coffeeType = 0;
        // System.out.println("CoffeeMachine v0.9 Starting...");
    }

    // Display this CoffeeMachine current levels
    void showLevels() {
        System.out.println("The coffee machine has:");
        System.out.println(this.waterLevel + " ml of water");
        System.out.println(this.milkLevel + " ml of milk");
        System.out.println(this.beansLevel + " g of coffee beans");
        System.out.println(this.cupCount + " disposable cups");
        System.out.println("$" + this.money + " of money");
        this.running = mState.READY;
    }

    // Check if sufficient resources in this CoffeeMachine
    boolean checkLevels(int cType) {
        int i = cType - 1;
        if (this.waterLevel < this.water_per_cup[i]) {
            System.out.println("Sorry, not enough water!");
            return false;
        }
        if (this.milkLevel < this.milk_per_cup[i]) {
            System.out.println("Sorry, not enough milk!");
            return false;
        }
        if (this.beansLevel < this.beans_per_cup[i]) {
            System.out.println("Sorry, not enough beans!");
            return false;
        }
        if (this.cupCount < 1) {
            System.out.println("Sorry, not enough cups!");
            return false;
        }
        return true;
    }
    // Buy a coffee
    void doBuy(Scanner s) {
        System.out.println("What do you want to buy? 1 - espresso, 2 - latte, 3 - cappuccino, back - to main menu:");
        String selection = s.next();
        switch (selection) {
            case "back":
                this.coffeeType = 0;
                this.running = mState.READY;
                break;
            case "1":
                this.coffeeType = 1;
                this.running = mState.BREWING;
                this.doBrew();  // brew espresso
                break;
            case "2":
                this.coffeeType = 2;
                this.running = mState.BREWING;
                this.doBrew();  // brew latte
                break;
            case "3":
                this.coffeeType = 3;
                this.running = mState.BREWING;
                this.doBrew();  // brew cappuccino
                break;
            default:
                System.out.println("Unavailable choice?");
                this.coffeeType = 0;
                this.running = mState.READY;
                break;
        }
    }
    // Brew a coffee of a given type and adjust CoffeeMachine levels accordingly
    void doBrew() {
        if (checkLevels(this.coffeeType)) {
            int i = this.coffeeType - 1;
            System.out.println("I have enough resources, making you a coffee!");
            this.waterLevel -= this.water_per_cup[i];
            this.milkLevel -= this.milk_per_cup[i];
            this.beansLevel -= this.beans_per_cup[i];
            this.cupCount -= 1;
            this.money += this.cost_per_cup[i];
        }
        this.coffeeType = 0;
        this.running = mState.READY;
    }

    // refill the coffee machine in four stages
    void doFill1(Scanner s) {
        System.out.println("Write how many ml of water you want to add:");
        this.waterLevel += s.nextInt();
        this.running = mState.FILL2;
        this.doFill2(s);
    }
    void doFill2(Scanner s) {
        System.out.println("Write how many ml of milk you want to add:");
        this.milkLevel += s.nextInt();
        this.running = mState.FILL3;
        this.doFill3(s);
    }
    void doFill3(Scanner s) {
        System.out.println("Write how many grams of coffee beans you want to add:");
        this.beansLevel += s.nextInt();
        this.running = mState.FILL4;
        this.doFill4(s);
    }
    void doFill4(Scanner s) {
        System.out.println("Write how many disposable cups you want to add:");
        this.cupCount += s.nextInt();
        this.running = mState.READY;
    }
    // Take money from Coffee machine
    void doTake() {
        System.out.println("I gave you $" + this.money);
        this.money = 0;
        this.running = mState.READY;
    }
    // perform coffee machine self check
    boolean doSelfCheck() {
        //System.out.println("...Self Check...");
        return true;  // return OK anyway :-)
    }
    // START HERE
    public static void main(String[] args) {
        CoffeeMachine machine = new CoffeeMachine(400, 540, 120, 9, 550);
        Scanner scanner = new Scanner(System.in);
        if (machine.doSelfCheck()) {
            machine.running = mState.READY;
        }
        while (machine.running == mState.READY) {
            System.out.println("Write action (buy, fill, take, remaining, exit):");
            String action = scanner.next();
            switch (action) {
                case "buy":
                    machine.running = mState.BUY;
                    machine.doBuy(scanner);
                    break;
                case "fill":
                    machine.running = mState.FILL1;
                    machine.doFill1(scanner);  // do fill action
                    break;
                case "take":
                    machine.running = mState.TAKE;
                    machine.doTake();  // do take action
                    break;
                case "remaining":
                    machine.running = mState.LEVELS;
                    machine.showLevels();
                    break;
                case "exit":
                    machine.running = mState.SHUTDOWN;
                    break;
                default:
                    if (!action.isEmpty()) {
                        System.out.println("Invalid action: '" + action + "'");
                    }
                    break;
            }
        }
    }
}
