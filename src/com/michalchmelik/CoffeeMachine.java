package com.michalchmelik;

import java.util.Scanner;

public class CoffeeMachine {
    private static Scanner scanner = new Scanner(System.in);

    private static int mlWaterInMachine = 400;
    private static int mlMilkInMachine = 540;
    private static int gramOfBeansInMachine = 120;
    private static int cupsInMachine = 9;
    private static int moneyInMachine = 550;

    private static final int numberOfCupsNeededForOrder = 1;

    private static String inputString() {
        return scanner.nextLine();
    }

    public static void main(String[] args) {
        message();
        coffeeMachine();
    }

    private static void coffeeMachine(){
        boolean flag = true;
        while(flag){
            System.out.println("\nWrite action (buy, fill, take, remaining, exit): ");
            switch(inputString()){
                case "buy":
                    System.out.println("What do you want to buy? 1 - espresso, 2 - latte, 3 - cappuccino, back - to main menu: ");
                    String requestedChoice = inputString();
                    makeRequestedProduct(requestedChoice);
                    break;
                case "fill":
                    System.out.println("Write how many ml of water do you want to add: ");
                    mlWaterInMachine += scanner.nextInt();
                    System.out.println("Write how many ml of milk do you want to add: ");
                    mlMilkInMachine += scanner.nextInt();
                    System.out.println("Write how many grams of coffee beans do you want to add: ");
                    gramOfBeansInMachine += scanner.nextInt();
                    System.out.println("Write how many disposable cups of coffee do you want to add: ");
                    cupsInMachine += scanner.nextInt();
                    scanner.nextLine();
                    break;
                case "take":
                    System.out.println("I gave you $" + moneyInMachine);
                    moneyInMachine = 0;
                    break;
                case "exit":
                    System.out.println("Turning off, have a nice day :)");
                    flag = false;
                    break;
                case "remaining":
                    message();
                    break;
                default:
                    System.out.println("Error.");
                    flag = false;
                    break;
            }
        }
    }

    private static void message(){
        System.out.println(" ----------------------------------- ");
        System.out.println("The coffee machine has: \n" +
                mlWaterInMachine + " of water \n" +
                mlMilkInMachine + " of milk \n" +
                gramOfBeansInMachine + " of coffee beans \n" +
                cupsInMachine + " of disposable cups \n" +
                "$" + moneyInMachine + " of money ");
    }

    private static void makeRequestedProduct(String requestedChoice){
        switch(requestedChoice){
            case "1":
                int waterForEspresso = 250;
                int coffeeBeansForEspresso = 16;
                if(canMakeOrder(waterForEspresso,0, coffeeBeansForEspresso)){
                    System.out.println("I have enough resources, making you a coffee!");
                    mlWaterInMachine -= waterForEspresso;
                    gramOfBeansInMachine -= coffeeBeansForEspresso;
                    cupsInMachine -= 1;
                    moneyInMachine += 4;
                }
                break;
            case "2":
                int waterForLatte = 350;
                int milkForLatte = 75;
                int coffeeBeansForLatte = 20;
                if(canMakeOrder(waterForLatte, milkForLatte, coffeeBeansForLatte)){
                    System.out.println("I have enough resources, making you a latte!");
                    mlWaterInMachine -= waterForLatte;
                    mlMilkInMachine -= milkForLatte;
                    gramOfBeansInMachine -= coffeeBeansForLatte;
                    cupsInMachine -= 1;
                    moneyInMachine += 7;
                }
                break;
            case "3":
                int waterForCappuccino = 200;
                int milkForCappuccino = 100;
                int coffeeBeansForCappuccino = 12;
                if(canMakeOrder(waterForCappuccino, milkForCappuccino, coffeeBeansForCappuccino)){
                    System.out.println("I have enough resources, making you a cappuccino!");
                    mlWaterInMachine -= waterForCappuccino;
                    mlMilkInMachine -= milkForCappuccino;
                    gramOfBeansInMachine -= coffeeBeansForCappuccino;
                    cupsInMachine -= 1;
                    moneyInMachine += 6;
                }
                break;
            case "back":
                break;
            default:
                System.out.println("Error, need to fill!");
                break;
        }
    }

    private static boolean canMakeOrder(int mlWaterNeeded, int mlMilkNeeded, int gBeansNeeded){
        int maxWaterCup = mlWaterInMachine / mlWaterNeeded;
        int maxMilkCup;
        //if milk is not needed for requested order
        if(mlMilkNeeded == 0){
            maxMilkCup = 1;
        }else{
            maxMilkCup = mlMilkInMachine / mlMilkNeeded;
        }
        int maxBeansCup = gramOfBeansInMachine / gBeansNeeded;
        int enoughCups = cupsInMachine - numberOfCupsNeededForOrder;

        if(maxWaterCup < 1){
            System.out.println("Sorry, not enough water.");
        }else if(maxMilkCup < 1){
            System.out.println("Sorry, not enough milk.");
        }else if(maxBeansCup < 1){
            System.out.println("Sorry not enough beans.");
        }else if(enoughCups < 1){
            System.out.println("Sorry not enough cups.");
        }else{
            return true;
        }
        return false;
    }
}

