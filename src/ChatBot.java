package ChatBot;

import java.util.Scanner;

public class ChatBot {

    public static void main(String[] args) {

        String botname = "JabbaBot";

        System.out.println("Hello! My name is " + botname + ".");
        System.out.println("I was created in " + 2023 + ".");

        Scanner scanner = new Scanner(System.in);
        System.out.println("Please, remind me your name.");
        String userName = scanner.nextLine();
        System.out.println("What a great name you have, " + userName + "!");
        System.out.println("Let me guess your age.");
        System.out.println("Enter the remainders of dividing your age by 3, 5, and 7.");
        int number3 = scanner.nextInt();
        int number5 = scanner.nextInt();
        int number7 = scanner.nextInt();
        int userAge = (number3 * 70 + number5 * 21 + number7 * 15) % 105;
        System.out.println("Your age is " + userAge + "; that's a good time to start programming!");
        System.out.println("Now I will prove to you that I can count to any number you want!");
        int userNumber = scanner.nextInt();
        for (int i = 1; i <= userNumber; i++) {
            System.out.println(i + " !");
        }
        System.out.println("Let's test your knowledge.");
        System.out.println("Which of the following options correctly describes Russians?");
        System.out.println("1. Russians are humans.");
        System.out.println("2. Russians are orcs.");
        System.out.println("3. Russians are slavs.");
        System.out.println("4. Russians are freedom-loving.");

        int correctAnswer = 3;
        int userAnswer;

        do {
            userAnswer = scanner.nextInt();

            if (userAnswer != correctAnswer) {
                System.out.println("Wrong answer. Please try again.");
            }
        } while (userAnswer != correctAnswer);

        System.out.println("Congratulations! You answered correctly.");
        System.out.println("Goodbye, have a nice day!");
    }
}
