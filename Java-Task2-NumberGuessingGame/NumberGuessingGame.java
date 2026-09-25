import java.util.Random;
import java.util.Scanner;

public class NumberGuessingGame {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        Random random = new Random();

        String playAgain = "yes";
        int totalScore = 0;

        while (playAgain.equalsIgnoreCase("yes")) {

            // Generate random number between 1 and 100
            int number = random.nextInt(100) + 1;

            int attempts = 0;
            int maxAttempts = 7;
            boolean won = false;

            System.out.println();
            System.out.println("=================================");
            System.out.println("      NUMBER GUESSING GAME");
            System.out.println("=================================");
            System.out.println("Guess a number between 1 and 100.");
            System.out.println("You have " + maxAttempts + " attempts.");
            System.out.println();

            // Guessing loop
            while (attempts < maxAttempts) {

                System.out.print("Enter your guess (1-100): ");

                // Check whether the user entered an integer
                if (!scanner.hasNextInt()) {
                    System.out.println("Please enter a valid number.");
                    scanner.next();
                    continue;
                }

                int guess = scanner.nextInt();

                // Check range
                if (guess < 1 || guess > 100) {
                    System.out.println("Please enter a number between 1 and 100.");
                    continue;
                }

                attempts++;

                if (guess == number) {

                    won = true;

                    // Calculate score
                    int score = 100 - ((attempts - 1) * 10);

                    if (score < 0) {
                        score = 0;
                    }

                    totalScore += score;

                    System.out.println();
                    System.out.println("Correct! 🎉");
                    System.out.println("You guessed the number in "
                            + attempts + " attempts.");
                    System.out.println("Round Score: " + score);
                    System.out.println("Total Score: " + totalScore);

                    break;

                } else if (guess < number) {

                    System.out.println("Too Low!");

                } else {

                    System.out.println("Too High!");
                }

                System.out.println("Attempts remaining: "
                        + (maxAttempts - attempts));
                System.out.println();
            }

            // Game Over
            if (!won) {

                System.out.println();
                System.out.println("Game Over!");
                System.out.println("The correct number was: " + number);
                System.out.println("Round Score: 0");
                System.out.println("Total Score: " + totalScore);
            }

            // Ask whether the player wants another round
            do {

                System.out.print(
                        "Do you want to play again? (yes/no): "
                );

                playAgain = scanner.next();

                if (!playAgain.equalsIgnoreCase("yes")
                        && !playAgain.equalsIgnoreCase("no")) {

                    System.out.println(
                            "Please enter only yes or no."
                    );
                }

            } while (!playAgain.equalsIgnoreCase("yes")
                    && !playAgain.equalsIgnoreCase("no"));
        }

        // Final score
        System.out.println();
        System.out.println("=================================");
        System.out.println("          FINAL SCORE");
        System.out.println("=================================");
        System.out.println("Your total score: " + totalScore);
        System.out.println("Thanks for playing!");

        scanner.close();
    }
}

