package ChatBot;

import java.util.Scanner;

public class ChatBot {

    public static void main(String[] args) {

        String botname = "BobBot";

        System.out.println("Привіт! Моє ім'я " + botname + ".");
        System.out.println("Я був створений у " + 2023 + ".");

        Scanner scanner = new Scanner(System.in);
        System.out.println("Будь ласка, скажи мені своє ім'я.");
        String userName = scanner.nextLine();
        System.out.println("Наскільки велике у тебе ім'я, " + userName + "!");
        System.out.println("Дозволь мені вгадати твій вік.");
        System.out.println("Введіть залишки від ділення твого віку на 3, 5 і 7.");
        int number3 = scanner.nextInt();
        int number5 = scanner.nextInt();
        int number7 = scanner.nextInt();
        int userAge = (number3 * 70 + number5 * 21 + number7 * 15) % 105;
        System.out.println("Тввй вік - " + userAge + "; це чудовий час для початку програмування!");
        System.out.println("Тепер я доведу тобі, що можу лічити до будь-якого числа, яке ти хочеш!");
        int userNumber = scanner.nextInt();
        for (int i = 1; i <= userNumber; i++) {
            System.out.println(i + " !");
        }
        System.out.println("Перевіримо ваші знання.");
        System.out.println("Яка з наступних варіантів правильно описує Київ?");
        System.out.println("1.  Київ - село.");
        System.out.println("2.  Київ - в Польщі.");
        System.out.println("3.  Київ - столиця.");
        System.out.println("4.  Київ - річка.");

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
