package project1;

/*****************************************************************
 * This code was provided by Roger Ferguson and Scott Grissom and modified
 * by Wade Callahan.
 *
 * Pig Rules:
 *  -A player rolls 2 die until they hold, reach 19 points for the round, or the
 *  following occurs:
 *     -If they roll a 1, the current round points are lost and player turn toggles
 *     -If they roll double 1s, all points are lost
 * 	-If they hold they get the current round's points added to their total
 * 		score
 * 	-The 1st player to 100 wins
 @author Wade Callahan
 @version 1.0 October 4, 2021
 *****************************************************************/

public class PigGame {

    GVdie die1, die2;
    int playerScore;
    int cpuScore;
    int currentRoundScore;
    int roundNumber;
    final int holdScore = 200;
    final int winningScore = 500;
    boolean isPlayerTurn = true;
    int playerWinCount = 0;
    boolean isNeverOne;
    boolean isItPrime;

    /*******************************************
     * PigGame constructor for dice
     ******************************************/
    public PigGame() {
        System.out.println("Welcome to Wade's Game of Pig!");
        die1 = new GVdie();
        die2 = new GVdie();
    }

    /*******************************************
     * return int the round score
     ******************************************/
    public int getRoundScore() {
        return currentRoundScore;
    }

    /*******************************************
     * return int the player score
     ******************************************/
    public int getPlayerScore() {
        return playerScore;
    }

    /*******************************************
     * return int the computer score
     ******************************************/
    public int getComputerScore() {
        return cpuScore;
    }

    /*******************************************
     * return the die according to integer input.
     ******************************************/
    public GVdie getDie(int num) {

        if (num == 1)
            return die1;
        else
            return die2;
    }

    /*******************************************
     * return boolean true if player won
     ******************************************/
    public boolean playerWon() {
        return getPlayerScore() >= winningScore;
    }

    /*******************************************
     * return boolean true if computer won
     ******************************************/
    public boolean computerWon() {
        return getComputerScore() >= winningScore;
    }

    /*******************************************
     * Rolls the dice
     ******************************************/
    private void rollDice() {
        die1.roll();
        die2.roll();


        if (isNeverOne && isPlayerTurn) {
            while (die1.getValue() == 1)
                die1.roll();
            while (die2.getValue() == 1)
                die2.roll();
        }


        if ((die1.getValue() == 1) || (die2.getValue() == 1)) {
            isPlayerTurn = !isPlayerTurn;
            currentRoundScore = 0;
        } else {
            if (!die1.isHeld() && !die2.isHeld())
                currentRoundScore = currentRoundScore + die1.getValue() + die2.getValue();
            else if (!die1.isHeld())
                currentRoundScore = currentRoundScore + die1.getValue();
            else if (!die2.isHeld())
                currentRoundScore = currentRoundScore + die2.getValue();
        }
        System.out.println("Die 1 Value: " + die1.getValue());
        System.out.println("Die 2 Value: " + die2.getValue());
        System.out.println("Round score: " + getRoundScore());
    }

    /*******************************************
     * Part 1 of player's turn, playerRolls
     ******************************************/
    public void playerRolls() {
        rollDice();
        if (playerWon()) {
            playerWinCount = playerWinCount + 1;
            System.out.println("Player has won.");
        }
        if (currentRoundScore > holdScore) {
            playerScore = playerScore + currentRoundScore;
            isPlayerTurn = false;
        }
        if ((die1.getValue() == 1) && (die2.getValue() == 1)) {
            playerScore = 0;
            isPlayerTurn = false;
        }
        if (!isPlayerTurn()) {
            System.out.println("---- Player score: " + getPlayerScore());
        }
    }

    /*******************************************
     * Part 2 of player's turn, playerHolds
     ******************************************/
    public void playerHolds() {
        resetDice();
        playerScore = playerScore + currentRoundScore;
        currentRoundScore = 0;
        isPlayerTurn = false;
        System.out.println("---- Player score: " + getPlayerScore());
        System.out.println("The player's turn has ended.");
        System.out.println();
        System.out.println("It's now the computer's turn.");
    }

    /*******************************************
     * Simulates computer's turn
     ******************************************/
    public void computerTurn() {
        //resetDice();
        while (true) {
            rollDice();
            if(checkForPrime(currentRoundScore))
                break;

            if ((die1.getValue() == 1) && (die2.getValue() == 1)) {
                currentRoundScore = 0;
                cpuScore = 0;
                break;
            }
            if ((die1.getValue() == 1) || (die2.getValue() == 1)) {
                currentRoundScore = 0;
                break;
            }
            if (currentRoundScore > holdScore) {
                isPlayerTurn = true;
                break;
            }
            if (computerWon()) {
                System.out.println("Computer has won!");
                break;
            }
        }
        cpuScore = cpuScore + currentRoundScore;
        System.out.println("------------------ My Round Score " + currentRoundScore);
        System.out.println("Computer score: " + getComputerScore());
        System.out.println("The computer's turn has ended.");
        System.out.println();
        System.out.println("It's now the player's turn.");
        currentRoundScore = 0;
        isPlayerTurn = true;
    }

    /*******************************************
     * Restarts the game
     ******************************************/
    public void restart() {
        isNeverOne = false;
        playerScore = 0;
        cpuScore = 0;
        currentRoundScore = 0;
        roundNumber = 0;
        isPlayerTurn = true;
    }

    public void resetDice() {
        die1.setHeld(false);
        die2.setHeld(false);
    }

    /*******************************************
     * The player's turn
     ******************************************/
    private void playerTurn() {

        do {
            playerRolls();
            if ((die1.getValue() == 1) && (die2.getValue() == 1)) {
                playerScore = 0;
                isPlayerTurn = false;
                playerHolds();
            } else if ((die1.getValue() == 1) || (die2.getValue() == 1)) {
                isPlayerTurn = false;
                playerHolds();
            }
            if (currentRoundScore > holdScore) {
                playerHolds();
            }
        } while (isPlayerTurn);
    }

    /*******************************************
     * Automatically runs a game without user input
     ******************************************/
    public void autoGame() {
        roundNumber = 0;
        if (playerWinCount == 3) {
            cpuScore = winningScore;
            playerWinCount = 4;
        }

        while (!computerWon() && !playerWon()) {


            playerTurn();
            if (!isPlayerTurn()) {
                computerTurn();
            }
            roundNumber = roundNumber + 1;

        }

        if (playerWon())
            playerWinCount += 1;

        System.out.println("Game won after " + roundNumber + " rounds.");
    }

    /*******************************************
     * return boolean true if it is the player's turn
     ******************************************/
    public boolean isPlayerTurn() {
        return isPlayerTurn;
    }

    /********************************************
     * used to test functionality of pigGame class
     *******************************************/
    public static void main(String[] args) {
        PigGame pig = new PigGame();
        pig.autoGame();
    }

    public boolean checkForPrime(int inputNumber) {
        boolean isItPrime = true;

        if (inputNumber <= 1) {
            isItPrime = false;

        } else {
            for (int i = 2; i <= inputNumber / 2; i++) {
                if ((inputNumber % i) == 0) {
                    isItPrime = false;
                    break;
                }
                }
            }
        return isItPrime;
    }
}
