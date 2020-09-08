package machine;
import java.util.*;

enum State {
    REMAINING, BUY, FILL, TAKE, EXIT

}

enum Coffee {
    BACK, ESPRESSO, LATTE, CAPPUCCINO
}

public class CoffeeMachine {
    static Scanner scanner = new Scanner(System.in);
    static int filler;
    static State state;

    //Machine gets the customer's order
    static void action() {
        System.out.println();
        System.out.println("Write action (buy, fill, take, remaining, exit) :");
        String userInput = scanner.nextLine();
        for (State value : State.values())
            if (userInput.equalsIgnoreCase(value.toString())) {
                state = value;
                break;
            }

        switch (state) {
            case BUY:
                System.out.println();
                System.out.println("What do you want to buy? 1 - espresso, 2 - latte, 3 - cappuccino: ");
                Machine.buy(setCoffee(scanner.nextLine()));
                break;
            case FILL:
                Machine.fill();
                break;
            case TAKE :
                Machine.takeMoney();
                break;
            case REMAINING :
                Machine.remaining();
                break;
            case EXIT :
                break;
                
        }
    }

    //method to check user input for coffee type
    public static int setCoffee(String userInput) {
        if (userInput.length() > 1) {
            return  0;
        } else {
            int orderNum = Integer.parseInt(userInput);
            for (Coffee type : Coffee.values())
                if (orderNum == type.ordinal()) {
                    return orderNum;

                }
        }
        return 0;
    }


    //method to get fillers for type fill
    public static void setFiller(String userInput) {
        filler = Integer.parseInt(userInput);
    }

    public static int getFiller() {
        setFiller(scanner.nextLine());
        return filler;
    }

    public static void main(String[] args) {
        action();
    }
}

class Machine {
    static int water = 400;
    static int milk = 540;
    static int coffee = 120;
    static int cups = 9;
    static int money = 550;
    static int order;


    //Machine displays resouces
    static void remaining() {
        System.out.println();
        System.out.println("The coffee machine has:");
        System.out.printf("%d of water\n", water);
        System.out.printf("%d of milk\n", milk);
        System.out.printf("%d of coffee beans\n", coffee);
        System.out.printf("%d of disposable cups\n", cups);
        System.out.printf("$%d of money\n", money);
        CoffeeMachine.action();
    }

    //Machine makes that coffee
    static void makeCoffee(int w, int m, int c, int amt) {
        System.out.println();
        if (water > w && milk > m && coffee > c) {
            System.out.println("I have enough resources, making you a coffee!");
            water -= w;
            milk -= m;
            coffee -= c;
            money += amt;
            cups--;
        } else {
            if (water < w) {
                System.out.println("Sorry, not enough water!");
            }else if (milk < m) {
                System.out.println("Sorry, not enough milk!");
            }else if (coffee < c) {
                System.out.println("Sorry, not enough coffee!");
            }else if (cups == 0) {
                System.out.println("Sorry, not enough cups!");
            }
        }
    }
    //for customer's buying
    static void buy(int userOrder) {
        order = userOrder;
        switch (order) {
            case 1:
                makeCoffee(250, 0, 16, 4);
                break;
            case 2:
                makeCoffee(350, 75, 20, 7);
                break;
            case 3:
                makeCoffee(200, 100, 12, 6);
                break;
            
        }
        CoffeeMachine.action();
    }


    //Machine gives out money
    static void takeMoney() {
        System.out.println();
        System.out.printf("I gave you $%d\n", money);
        money -= money;
        CoffeeMachine.action();
    }


    //resupply the coffee machine
    static void fill() {
        System.out.println();
        System.out.println("Write how many ml of water do you want to add:");
        water += CoffeeMachine.getFiller();
        System.out.println("Write how many ml of milk do you want to add:");
        milk += CoffeeMachine.getFiller();
        System.out.println("Write how many grams of coffee beans do you want to add:");
        coffee += CoffeeMachine.getFiller();
        System.out.println("Write how many disposable cups of coffee do you want to add:");
        cups += CoffeeMachine.getFiller();
        CoffeeMachine.action();
    }
}



