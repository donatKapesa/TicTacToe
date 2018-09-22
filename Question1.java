/**
 * Simple tic-tac-toe game. Please see the readme.txt file for rules
 * 
 * @author Donat Kapesa
 * @version 1.0
 */

package ca.mcgill.ecse202.a4;

import java.util.Scanner;

import java.util.Arrays;

public class Question1 {

  public static void main(String[] args) {

    char playerX = 'X';
    char playerO = 'O';
    // Create new game board object
    GameBoard gameBoard = new GameBoard();
    gameBoard.displayBoard();

    // game goes on as long as there is no winner
    while (gameBoard.gameOn()) {
      // prompt user X to play
      Scanner input = new Scanner(System.in);
      System.out.print("Enter a row for player X (1-3): ");
      int rowX = input.nextInt();
      System.out.print("Enter a column for player X (1-3): ");
      int colX = input.nextInt();
      // initialize play by user X
      gameBoard.MakeMove(playerX, rowX - 1, colX - 1);
      gameBoard.displayBoard();
      // check to see if we have a winner
      if (gameBoard.checkForWinner()) {
        System.out.println("Player " + playerX + " has won!");
      }
      if (!gameBoard.checkForWinner() && gameBoard.moveIsPossible() == false) {
        System.out.println("It's a draw!");
      }

      // if there is no winner, prompt user O to play
      if (gameBoard.gameOn()) {
        System.out.print("Enter a row for player O (1-3): ");
        int rowO = input.nextInt();
        System.out.print("Enter a column for player O (1-3): ");
        int colO = input.nextInt();
        // initialize play by user O
        gameBoard.MakeMove(playerO, rowO - 1, colO - 1);
        gameBoard.displayBoard();
        // check if we have a winner
        if (gameBoard.checkForWinner()) {
          System.out.println("Player " + playerO + " has won!");
        }
        if (!gameBoard.checkForWinner() && gameBoard.moveIsPossible() == false) {
          System.out.println("It's a draw!");
        }
      }
    }
  }
}


/**
 * This class contains the game rules, the board drawing and everything else need for the game to
 * work properly
 * 
 * @author donatkapesa
 * @version 1.0
 */
class GameBoard {
  private char[][] gameBoard;
  private boolean gameOn = true;

  /**
   * This is a default constructor for GameBoard
   */
  public GameBoard() {
    gameBoard = new char[3][3];
    // Initializing array with empty spaces
    for (int row = 0; row < gameBoard.length; row++) {
      Arrays.fill(gameBoard[row], ' ');
    }
  } // end of GameBoard constructor

  /**
   * This method draws the game board
   */
  public void displayBoard() {
    System.out.println("--------------");
    for (int row = 0; row < gameBoard.length; row++) {
      for (int col = 0; col < gameBoard.length; col++) {
        System.out.print("| ");
        System.out.print(gameBoard[row][col] + " ");
        if (col == 2) {
          System.out.println("|");
          System.out.println("--------------");
        }
      }
    }
  } // end of display board method

  /**
   * This methods validates a player moves if he chooses to play on an empty space
   * 
   * @param player this is either X or O, depending on the current player's turn
   * @param row this is the row player X or O chooses to make a move on
   * @param col this is the column player X or O chooses to make a move on
   * @return
   */
  public boolean MakeMove(char player, int row, int col) {
    if (gameBoard[row][col] != ' ') {
      return false;
    } else {
      gameBoard[row][col] = player;
      return true;
    }
  } // end of MakeMove method

  /**
   * This checks if there there's an empty space on the game board, hence if a move is possible
   * 
   * @return true if there's an empty space, false otherwise
   */
  public boolean moveIsPossible() {
    for (int row = 0; row < gameBoard.length; row++) {
      for (int col = 0; col < gameBoard[row].length; col++) {
        // if there's an empty space, then a move is possible
        if (gameBoard[row][col] == ' ') {
          return true;
        }
      }
    }
    return false;
  } // end of moveIsPossible method

  /**
   * This checks every row to see if we have a winner
   * 
   * @return true if there's a winner, false otherwise
   */
  public boolean checkForWinnerHorizontal() {
    for (int row = 0; row < gameBoard.length; row++) {
      if (gameBoard[row][0] != ' ' && gameBoard[row][0] == gameBoard[row][1]
          && gameBoard[row][1] == gameBoard[row][2]) {
        return true;
      }
    }
    return false;
  } // end of checkForWinnerHorizontal method

  /**
   * This checks every column to see if we have a winner
   * 
   * @return true if there's a winner, false if otherwise
   */
  public boolean checkForWinnerVertical() {
    for (int col = 0; col < gameBoard[0].length; col++) {
      if (gameBoard[0][col] != ' ' && gameBoard[0][col] == gameBoard[1][col]
          && gameBoard[1][col] == gameBoard[2][col]) {
        return true;
      }
    }
    return false;
  } // end of checkForWinnerVertical method

  /**
   * This checks the two diagonals to see if we have a winner
   * 
   * @return true if there's a winner, false otherwise
   */
  public boolean checkForWinnerDiagonal() {
    if ((gameBoard[0][0] != ' ' && gameBoard[0][0] == gameBoard[1][1]
        && gameBoard[1][1] == gameBoard[2][2])
        || (gameBoard[2][0] != ' ' && gameBoard[2][0] == gameBoard[1][1]
            && gameBoard[1][1] == gameBoard[0][2])) {
      return true;
    } else {
      return false;
    }
  } // end of checkForWinnerDiagonal method

  /**
   * This checks if every row, column and diagonal to see if we have a winner
   * 
   * @return true if there's a winner, false otherwise
   */
  public boolean checkForWinner() {
    if (checkForWinnerVertical() == true || checkForWinnerHorizontal() == true
        || checkForWinnerDiagonal() == true) {
      return true;
    }
    return false;
  } // end of checkForWinner method

  /**
   * This checks if a move is possible and if there is still no winner, then the game goes on
   * 
   * @return true if a move is possible and there's no winner, false otherwise
   */
  public boolean gameOn() {
    return (moveIsPossible() && checkForWinner() == false);
  }
} // end of GameBoard class
