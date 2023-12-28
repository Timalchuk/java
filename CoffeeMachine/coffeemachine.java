package CoffeeMachine;

import java.util.LinkedHashMap;
import java.util.Scanner;

public class CoffeeMachine {
    private LinkedHashMap<String, Integer> standardEquipment = new LinkedHashMap<>();
    private LinkedHashMap<String, Object> espresso = new LinkedHashMap<>();
    private LinkedHashMap<String, Object> latte = new LinkedHashMap<>();
    private LinkedHashMap<String, Object> cappuccino = new LinkedHashMap<>();
    public CoffeeMachine() {
        standardEquipment.put("water", 400);
        standardEquipment.put("milk", 540);
        standardEquipment.put("coffeebeans", 120);
        standardEquipment.put("disposablecups", 9);
        standardEquipment.put("money", 550);

        espresso.put("name", "espresso");
        espresso.put("water", 250);
        espresso.put("milk", 0);
        espresso.put("coffeebeans", 16);
        espresso.put("cost", 4);

        latte.put("name", "latte");
        latte.put("water", 350);
        latte.put("milk", 75);
        latte.put("coffeebeans", 20);
        latte.put("cost", 7);

        cappuccino.put("name", "cappuccino");
        cappuccino.put("water", 200);
        cappuccino.put("milk", 100);
        cappuccino.put("coffeebeans", 12);
        cappuccino.put("cost", 6);
    }

    public static void main(String[] args) {
        CoffeeMachine coffeeMachine = new CoffeeMachine();
        cafeMenu(coffeeMachine);
    }

    public static void remaining(CoffeeMachine coffeeMachine) {
        System.out.println("The coffee machine has:");
        System.out.println("Water - " + coffeeMachine.standardEquipment.get("water"));
        System.out.println("Milk - " + coffeeMachine.standardEquipment.get("milk"));
        System.out.println("Coffee Beans - " + coffeeMachine.standardEquipment.get("coffeebeans"));
        System.out.println("Disposable Cups - " + coffeeMachine.standardEquipment.get("disposablecups"));
        System.out.println("Money - " + coffeeMachine.standardEquipment.get("money"));
    }

    public static void fill(CoffeeMachine coffeeMachine) {
        try (Scanner scanner = new Scanner(System.in)) {
            while (true) {
                System.out.println("Write what you want to add (water, milk, coffeebeans, disposablecups) or 'back' to return to the main menu:");
                String input = scanner.nextLine();

                if (input.equalsIgnoreCase("back")) {
                    System.out.println("Returning to the main menu.");
                    break;
                } else if (coffeeMachine.standardEquipment.containsKey(input)) {
                    while (true) {
                        System.out.println("Write how many ml/grams of " + input + " do you want to add:");
                        if (scanner.hasNextInt()) {
                            int currentAmount = coffeeMachine.standardEquipment.get(input);
                            int amountToAdd = scanner.nextInt();
                            int newAmount = currentAmount + amountToAdd;
                            coffeeMachine.standardEquipment.put(input, newAmount);
                            System.out.println(coffeeMachine.standardEquipment);
                            scanner.nextLine();
                            break;
                        } else {
                            System.out.println("Error! Please enter a valid number.");
                            scanner.nextLine();
                        }
                    }
                } else {
                    System.out.println("Invalid input. Please enter a valid ingredient or 'back'.");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void take(CoffeeMachine coffeeMachine) {
        System.out.println("I will give you - " + coffeeMachine.standardEquipment.get("money") + "$");
        coffeeMachine.standardEquipment.put("money", 0);
    }

    public static void prepareCoffee(CoffeeMachine coffeeMachine, String coffeeName) {
        try (Scanner scanner = new Scanner(System.in)) {
            LinkedHashMap<String, Object> recipe = null;
            int water, milk, coffeeBeans, cost;
            switch (coffeeName) {
                case "espresso":
                    recipe = coffeeMachine.espresso;
                    break;
                case "latte":
                    recipe = coffeeMachine.latte;
                    break;
                case "cappuccino":
                    recipe = coffeeMachine.cappuccino;
                    break;
                default:
                    System.out.println("Invalid coffee choice");
                    return;
            }
            water = (int) recipe.get("water");
            milk = (int) recipe.get("milk");
            coffeeBeans = (int) recipe.get("coffeebeans");
            cost = (int) recipe.get("cost");
            while (true) {
                System.out.println("How many cups of coffee do you want?");
                if (scanner.hasNextInt()) {
                    int userCups = scanner.nextInt();
                    if (userCups > 0 && userCups < 100) {
                        System.out.println("For " + userCups + " cup/s of coffee you will need:\n" + userCupscups of " + coffeeName + " coffee can be made.");
                        if (coffeeMachine.standardEquipment.get("water") < water * userCups) {
                            System.out.println("Sorry, not enough water!");
                            break;
                        } else if (coffeeMachine.standardEquipment.get("milk") < milk * userCups) {
                            System.out.println("Sorry, not enough milk!");
                            break;
                        } else if (coffeeMachine.standardEquipment.get("coffeebeans") < coffeeBeans * userCups) {
                            System.out.println("Sorry, not enough coffee beans!");
                            break;
                        } else if (coffeeMachine.standardEquipment.get("disposablecups") < userCups) {
                            System.out.println("Sorry, not enough disposable cups!");
                            break;
                        } else {
                            System.out.println("I have enough resources, making you " + userCups + " cup/s of coffee!");
                            coffeeMachine.standardEquipment.put("water", coffeeMachine.standardEquipment.get("water") - water * userCups);
                            coffeeMachine.standardEquipment.put("milk", coffeeMachine.standardEquipment.get("milk") - milk * userCups);
                            coffeeMachine.standardEquipment.put("coffeebeans", coffeeMachine.standardEquipment.get("coffeebeans") - coffeeBeans * userCups);
                            coffeeMachine.standardEquipment.put("disposablecups", coffeeMachine.standardEquipment.get("disposablecups") - userCups);
                            coffeeMachine.standardEquipment.put("money", coffeeMachine.standardEquipment.get("money") + cost * userCups);
                            break;
                        }
                    } else {
                        System.out.println("Invalid number of cups. Please enter a number between 1 and 99.");
                    }
                } else {
                    System.out.println("Invalid input. Please enter a valid number.");
                    scanner.nextLine();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void cafeMenu(CoffeeMachine coffeeMachine) {
        try (Scanner scanner = new Scanner(System.in)) {
            while (true) {
                System.out.println("Write action (buy, fill, take, remaining, exit):");
                String action = scanner.nextLine();

                switch (action.toLowerCase()) {
                    case "buy":
                        System.out.println("What do you want to buy? 1 - espresso, 2 - latte, 3 - cappuccino, back - to main menu:");
                        String coffeeChoice = scanner.nextLine();
                        switch (coffeeChoice) {
                            case "1":
                                prepareCoffee(coffeeMachine, "espresso");
                                break;
                            case "2":
                                prepareCoffee(coffeeMachine, "latte");
                                break;
                            case "3":
                                prepareCoffee(coffeeMachine, "cappuccino");
                                break;
                            case "back":
                                break;
                            default:
                                System.out.println("Invalid choice. Please try again.");
                                break;
                        }
                        break;
                    case "fill":
                        fill(coffeeMachine);
                        break;
                    case "take":
                        take(coffeeMachine);
                        break;
                    case "remaining":
                        remaining(coffeeMachine);
                        break;
                    case "exit":
                        return;
                    default:
                        System.out.println("Invalid action. Please enter a valid action.");
                        break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
