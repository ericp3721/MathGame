import java.util.Scanner;
public class MathGame {

    private Player player1;
    private Player player2;
    private Player player3;
    private Player currentPlayer;
    private Player winner;
    private boolean gameOver;
    private Scanner scanner;
    private int wrong;
    private int highestScore;


    public MathGame(Player player1, Player player2, Player player3, Scanner scanner) {
        this.player1 = player1;
        this.player2 = player2;
        this.player3 = player3;
        this.scanner = scanner;
        currentPlayer = null;
        winner = null;
        gameOver = false;
    }


    public Player getWinner() {
        return winner;
    }

    public void playRound() {
        chooseStartingPlayer();
        while (!gameOver) {
            printGameState();
            System.out.println("Current player: " + currentPlayer.getName());
            boolean correct = askQuestion();
            if (correct) {
                System.out.println("Correct!");
                currentPlayer.incrementScore();
                swapPlayers();
            }else if(wrong != 1) {
                System.out.println("INCORRECT!");
                swapPlayers();
                wrong++;
            } else {
                System.out.println("INCORRECT!");
                gameOver = true;
                determineWinner();
            }
        }
    }

    private void printGameState() {
        System.out.println("--------------------------------------");
        System.out.println("Current Scores:");
        System.out.println(player1.getName() + ": " + player1.getScore());
        System.out.println(player2.getName() + ": " + player2.getScore());
        System.out.println(player3.getName() + ": " + player3.getScore());
        System.out.println("Highest Score: " + highestScore);
        System.out.println("--------------------------------------");
    }

    public void resetGame() {
        player1.reset();
        player2.reset();
        player3.reset();
        gameOver = false;
        currentPlayer = null;
        winner = null;
    }


    private void chooseStartingPlayer() {
        int randNum = (int) (Math.random() * 3) + 1;
        if (randNum == 1) {
            currentPlayer = player1;
        }else if (randNum == 2) {
            currentPlayer = player2;
        }else{
            currentPlayer = player3;
        }
    }

    private boolean askQuestion() {
        int operation = (int) (Math.random() * 4) + 1;
        int num1 = (int) (Math.random() * 100) + 1;
        int num2;
        int correctAnswer;
        System.out.println("Type in your answer as an integer (/ is int division)");
        if (operation == 1) {
            num2 = (int) (Math.random() * 100) + 1;
            System.out.print(num1 + " + " + num2 + " = ");
            correctAnswer = num1 + num2;
        } else if (operation == 2) {
            num2 = (int) (Math.random() * 100) + 1;
            System.out.print(num1 + " - " + num2 + " = ");
            correctAnswer = num1 - num2;
        } else if (operation == 3) {
            num2 = (int) (Math.random() * 10) + 1;
            System.out.print(num1 + " * " + num2 + " = ");
            correctAnswer = num1 * num2;
        } else {  // option == 4
            num2 = (int) (Math.random() * 10) + 1;
            System.out.print(num1 + " / " + num2 + " = ");
            correctAnswer = num1 / num2;
        }

        int playerAnswer = scanner.nextInt();
        scanner.nextLine();

        if (playerAnswer == correctAnswer) {
            return true;
        } else {
            return false;
        }
    }

    private void swapPlayers() {
        if (currentPlayer == player1) {
            currentPlayer = player2;
        }else if(currentPlayer == player2) {
            currentPlayer = player3;
        }else{
            currentPlayer = player1;
        }
    }

    private void determineWinner() {
        if (currentPlayer == player1) {
            winner = player2;
            player2.incrementWinningStreak();
            player1.resetWin();
            player3.resetWin();
        } else if (currentPlayer == player2) {
            winner = player3;
            player3.incrementWinningStreak();
            player1.resetWin();
            player2.resetWin();
        } else {
            winner = player1;
            player1.incrementWinningStreak();
            player2.resetWin();
            player3.resetWin();
        }

        int winnerScore = winner.getScore();
        if (winnerScore > highestScore) {
            highestScore = winnerScore;
        }
    }
}