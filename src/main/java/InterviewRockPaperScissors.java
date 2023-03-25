import java.io.IOException;
import java.util.Random;
import java.util.Scanner;

public class InterviewRockPaperScissors {

    /*
    Create a Paper Rock Scissors game User vs Computer
     */

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int userResponse = 0, again = 0;

        boolean winner = true;
        Random r = new Random();
        do {
            int playTo = 0;
            int userScore = 0, cpuScore = 0;
            System.out.println("Let's play Rock-Paper-Scissors!");
            System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!");
            System.out.println("Enter the final score that the winner must reach: ");
            try {
                playTo = sc.nextInt();
                if (playTo <= 0) {
                    throw new IllegalArgumentException("Invalid input. Please enter a positive integer greater than zero.");
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
                sc.nextLine(); // Clear the input buffer
                continue;
            }
            System.out.println("***************");
            System.out.println("First to " + playTo + " wins!!!");
            System.out.println("***************");
            while (userScore < playTo && cpuScore < playTo) {
                System.out.println("Select 1 to throw rock, 2 to throw paper, or 3 to throw scissors:");
                try {
                    userResponse = sc.nextInt();
                    if (userResponse < 1 || userResponse > 3) {
                        throw new IllegalArgumentException("Invalid input. Please enter 1, 2, or 3.");
                    }
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                    sc.nextLine(); // Clear the input buffer
                    continue;
                }
                //computer output must be in the loop, otherwise it chooses a random outcome, but it never changes unless you start the program all over
                //the 'bound' is exclusive, so random output is 1-3
                int computerInput = r.nextInt(3) + 1;
                String[] result = paperRockScissors(userResponse, computerInput);
                System.out.println(result[0]);
                System.out.println(result[1]);
                if (result[0].contains("Tie")) {
                    again = 1;
                    continue;
                } else if (result[1].contains("win")) {
                    userScore++;
                } else {
                    cpuScore++;
                }
                if (userScore == cpuScore) {
                    System.out.println("You and the computer are tied at " + cpuScore + ".");
                } else if (userScore > cpuScore && userScore < playTo && cpuScore < playTo) {
                    System.out.println("You're winning " + userScore + " to " + cpuScore + "!");
                } else if (userScore < cpuScore && userScore < playTo && cpuScore < playTo) {
                    System.out.println("You're losing " + cpuScore + " to " + userScore + ".");
                }
            }


            System.out.println(finalScore(userScore, cpuScore, playTo));

            System.out.println("Press 1 to play again; any other number to quit:");
            try {
                again = sc.nextInt();
            }catch(Exception IOE){
                IOE.printStackTrace();
            }
            String nextMethodConsume = sc.nextLine();
        } while (again == 1);
    }

    public static String[] paperRockScissors(int userResp, int computerResp) {
        String userThrow = "", cpuThrow = "", statement = "", result = "", rock = "ROCK", paper = "PAPER", scissors = "SCISSORS";

        switch (userResp) {
            case 1:
                userThrow = rock;
                break;
            case 2:
                userThrow = paper;
                break;
            case 3:
                userThrow = scissors;
                break;
        }
        switch (computerResp) {
            case 1:
                cpuThrow = rock;
                break;
            case 2:
                cpuThrow = paper;
                break;
            case 3:
                cpuThrow = scissors;
                break;
        }

        if (userResp == computerResp) {
            statement = "Tie; you both threw " + userThrow + "; throw again.";
        } else if (userResp == 1 && computerResp == 3 ||
                userResp == 2 && computerResp == 1 ||
                userResp == 3 && computerResp == 2) {
            statement = "You threw " + userThrow + "; computer threw " + cpuThrow + ": \n\t" + userThrow + " beats " + cpuThrow + ".";
            result = "You win!";
        } else {
            statement = "You threw " + userThrow + "; computer threw " + cpuThrow + ": \n\t" + cpuThrow + " beats " + userThrow + ".";
            result = "You lose.";
        }
        String[] results = {statement, result};
        return results;
    }

    public static String finalScore(int userScore, int cpuScore, int playTo) {
        String finalResult = "";
        if (userScore == playTo) {
            finalResult = "You won " + userScore + " to " + cpuScore + "!";
        } else if (cpuScore == playTo) {
            finalResult = "You lost " + cpuScore + " to " + userScore + ".";
        }
        return finalResult;
    }
}
