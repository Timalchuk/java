package hangman;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class hangman {

    private static final int MAX_HP = 8;


    private static final String[] words = {"python", "java", "javascript" , "kotlin"};

    public static void main(String[] args) {
        displayMenu();
    }

    public static void gameProcess() {
        int userHp = MAX_HP;
        Random random = new Random();
        int randomIndex = random.nextInt(words.length);
        String wordToGuess = words[randomIndex];
        ArrayList<Character> playerGuesses = new ArrayList<>();
        char[] dashes = new char[wordToGuess.length()];


        for (int i = 0; i < wordToGuess.length(); i++) {
            dashes[i] = '-';
        }
        System.out.println("Слово для відгадування: " + String.valueOf(dashes));
        boolean letterGuessed;

        try (Scanner scanner = new Scanner(System.in)) {
            while (userHp > 0) {
                System.out.println("Залишилось спроб: " + userHp);


                char userLetter = scanner.next().charAt(0);
                playerGuesses.add(userLetter);
                letterGuessed = false;


                for (int i = 0; i < wordToGuess.length(); i++) {
                    if (wordToGuess.charAt(i) == userLetter) {
                        dashes[i] = userLetter;
                        letterGuessed = true;
                    }
                }

                if (!letterGuessed) {
                    System.out.println("Ця літера не з'являється у слові");
                    userHp -= 1;
                }

                System.out.println("Слово для відгадування: " + String.valueOf(dashes));


                if (String.valueOf(dashes).equals(wordToGuess)) {
                    System.out.println("Ви виграли!\nДякуємо за гру!");
                    break;
                }
            }
        }

        if (userHp <= 0) {
            System.out.println("Ви програли!");
            System.out.println("Слово для відгадування було: '" + wordToGuess + "'");
        }
    }

    private static void displayMenu() {
        Scanner scanner = new Scanner(System.in);
        int menuChoice = 0;

        while (menuChoice != 0 && menuChoice != 1) {
            System.out.println("МЕНЮ ГРИ - ВІСЕЛИЦЯ");
            System.out.println("[0] ПОЧАТИ");
            System.out.println("[1] ВИЙТИ");
            System.out.print("Введіть свій вибір: ");

            if (scanner.hasNextInt()) {
                menuChoice = scanner.nextInt();

                switch (menuChoice) {
                    case 0:
                        System.out.println("Ласкаво просимо до гри!");
                        gameProcess();
                        break;
                    case 1:
                        System.out.println("До побачення!");
                        System.exit(3);
                    default:
                        System.out.println("Спробуйте ще раз!");
                        break;
                }
            } else {
                System.out.println("Неправильний ввід. Будь ласка, введіть 0 або 1.");
                scanner.next();
            }
        }

        scanner.close();
    }
