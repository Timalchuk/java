package ChatBot;

import java.util.Scanner;

public class ChatBot {

    public static void main(String[] args) {

        String botname = "JabbaBot";

        System.out.println("Привіт! Моє ім'я " + botname + ".");
        System.out.println("Я був створений у " + 2023 + ".");

        Scanner scanner = new Scanner(System.in);
        System.out.println("Будь ласка, нагадайте мені ваше ім'я.");
        String userName = scanner.nextLine();
        System.out.println("Яке велике у вас ім'я, " + userName + "!");
        System.out.println("Дозвольте мені вгадати ваш вік.");
        System.out.println("Введіть залишки від ділення вашого віку на 3, 5 і 7.");
        int number3 = scanner.nextInt();
        int number5 = scanner.nextInt();
        int number7 = scanner.nextInt();
        int userAge = (number3 * 70 + number5 * 21 + number7 * 15) % 105;
        System.out.println("Ваш вік - " + userAge + "; це чудовий час для початку програмування!");
        System.out.println("Тепер я доведу вам, що можу лічити до будь-якого числа, яке ви хочете!");
        int userNumber = scanner.nextInt();
        for (int i = 1; i <= userNumber; i++) {
            System.out.println(i + " !");
        }
        System.out.println("Перевіримо ваші знання.");
        System.out.println("Яка з наступних варіантів правильно описує росіян?");
        System.out.println("1. Росіяни - люди.");
        System.out.println("2. Росіяни - орки.");
        System.out.println("3. Росіяни - слов'яни.");
        System.out.println("4. Росіяни - свободолюбиві.");

        int correctAnswer = 3;
        int userAnswer;

        do {
            userAnswer = scanner.nextInt();

            if (userAnswer != correctAnswer) {
                System.out.println("Неправильна відповідь. Спробуйте ще раз.");
            }
        } while (userAnswer != correctAnswer);

        System.out.println("Вітаю! Ви дали правильну відповідь.");
        System.out.println("До побачення, гарного дня!");
    }
}