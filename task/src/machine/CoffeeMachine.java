package machine;

import java.util.Scanner;

/**
 * Machine fields (water, milk, coffee beans, cups and money)
 * Machine actions (buy, fill and take)
 * @author Alfredo Acosta
 * @since 2020
 */
public class CoffeeMachine {
    //Scanner to get user input
    private static final Scanner scanner = new Scanner(System.in);

    //Machine components, initial state.
    private static int water = 400;
    private static int milk = 540;
    private static int coffeeBeans = 120;
    private static int disposableCups = 9;
    private static int money = 550;

    //Messages's
    enum Info {
        NO_WATER("Sorry, not have enough water!"),
        NO_COFFEE_BEANS("Sorry, not have enough coffee beans!"),
        NO_MILK("Sorry, not have enough milk!"),
        NO_CUPS("Sorry, not have enough disposable cups!"),
        OK("I have enough resources, making you a coffee!"),
        BUY_OPTIONS("What do you want to buy? 1-espresso, 2-latte, 3-cappuccino, back-to main menu: "),
        TAKE_ACTION("Write action (buy, fill, take, remaining, exit): "),
        INVALID_OPTION("Please choose a valid option!"),
        THANKS("Thanks to use our services!");

        String msg;

        Info(String msg) { this.msg = msg; }

        String getMessage() { return msg; }
    }

    /** Must choose one of the options varieties of coffee (espresso, latte, cappuccino).
     * For the espresso, needs 250 ml of water and 16 g of coffee beans. It costs $4.
     * For the latte, needs 350 ml of water, 75 ml of milk, and 20 g of coffee beans. It costs $7.
     * For the cappuccino, needs 200 ml of water, 100 ml of milk, and 12 g of coffee. It costs $6.
     * @author Alfredo Acosta
     *  */
    private static void buy(){

        print(Info.BUY_OPTIONS.getMessage());
        String buyOption = scanner.nextLine();

        switch (buyOption){
            case "1" /*espresso*/ : doCoffee(250,0,16,4);
                break;
            case "2" /*latte*/ : doCoffee(350,75,20,7);
                break;
            case "3" /*cappuccino*/ : doCoffee(200,100,12,6);
                break;
            case "4" /*going back...*/ : break;
            default : print(Info.INVALID_OPTION.getMessage());
        }
    }

    /**
     * Prepare coffee's with milk
     * @author Alfredo Acosta
     * */
    private static void doCoffee(int waterNeeded, int milkNeeded, int coffeeBeansNeeded, int cost) {
        if (disposableCups >= 1) {
            if(water >= waterNeeded) {
                if(milk >= milkNeeded) {
                    if(coffeeBeans >= coffeeBeansNeeded) {

                        print(Info.OK.getMessage());

                        water -= waterNeeded;
                        milk -= milkNeeded;
                        coffeeBeans -= coffeeBeansNeeded;
                        money += cost;
                        --disposableCups;

                    }else { print(Info.NO_COFFEE_BEANS.getMessage()); }
                }else { print(Info.NO_MILK.getMessage()); }
            }else { print(Info.NO_WATER.getMessage()); }
        }else { print(Info.NO_CUPS.getMessage()); }
    }

    /**
     * Ask how much (water, milk, coffee beans and how many disposable cups) he wants to add into the coffee machine.
     * @author Alfredo Acosta
     * */
    private static void fill(){
        print("Write how many ml of water do you want to add:");
        water += scanner.nextInt();

        print("Write how many ml of milk do you want to add:");
        milk += scanner.nextInt();

        print("Write how many grams of coffee beans do you want to add:");
        coffeeBeans += scanner.nextInt();

        print("Write how many disposable cups of coffee do you want to add:");
        disposableCups += scanner.nextInt();
    }

    /**
     * Should give to the user all the money that it earned from selling coffee.
     * @author Alfredo Acosta
     * */
    private static void take() {
        print("I  gave you $" + money);
        money -= money;
    }

    /**
     * Initial state for the coffee machine has: $550 of money, 1200 ml of water, 540 ml of milk,
     * 120 g of coffee beans, and 9 disposable cups.
     * @author Alfredo Acosta
     * */
    public static String coffeeMachineState() {
        return "The coffee machine has:\n" +
                water + " of water\n"+
                milk + " of milk\n"+
                coffeeBeans + " of coffee beans\n"+
                disposableCups + " of disposable cups\n"+
                "$" + money + " of money";
    }

    private static void print(String msj){
        System.out.println(msj);
    }

    /**
     * Print the menu of the machine to operate it.
     * */
    private static void printMenu(){

        boolean keepRunning = true;

        while (keepRunning){
            print(Info.TAKE_ACTION.getMessage());

            String action = scanner.nextLine();
            print(action);

            switch (action){
                case "buy" : buy(); break;
                case "fill" : fill(); break;
                case "take" : take(); break;
                case "remaining" : print(coffeeMachineState()); break;
                case "exit" : print(Info.THANKS.getMessage()); keepRunning = false; break;
                default : print(Info.INVALID_OPTION.getMessage()); break;
            }
        }
    }

    public static void main(String[] args) {
        printMenu();
    }
}
