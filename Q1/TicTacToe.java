	/**
	* <b>main</b> of the application. Creates the instance ofGameController
	* and starts the game. If two parameters line and column
	* are passed, they are used.
	* Otherwise, a default value is used. Defaults values are also
	* used if the paramters are too small (less than 2).
	*
	* @param args
	*command line parameters
	*/

import java.util.Random;
import java.io.Console;

public class TicTacToe
{
	public static void main(String[] args)
	{
		//Display relevant student information:
		StudentInfo.display();

		//Define default game parameters:
		TicTacToeGame game;
		int lines = 3;
		int columns = 3;
		int win = 3;

		//Parse stdin as parameters:
		try
		{
			if (args.length >= 2)
			{
				lines = Integer.parseInt(args[0]);
				if (lines < 2)
				{
					System.out.println("Invalid argument, using default...");
					lines = 3;
				}
				columns = Integer.parseInt(args[1]);
				if (columns < 2)
				{
					System.out.println("Invalid argument, using default...");
					columns = 3;
				}
			}
			if (args.length >= 3)
			{
				win = Integer.parseInt(args[2]);
				if (win < 2)
				{
					System.out.println("Invalid argument, using default...");
					win = 3;
				}
			}
			if (args.length > 3) System.out.println("Too many arguments. Only the first 3 are used.");

		}
		catch (NumberFormatException e)
		{
			System.out.println("Invalid argument, using default...");
			lines = 3;
			columns= 3;
			win = 3;
		}

		//Intermediate handling of game state:
		boolean playingRound = true;
		//Game will be repeated until player will not answer Y/y
		while(playingRound)
		{
			//Creates new game
			game = new TicTacToeGame(lines, columns, win);
			//Create 2 new players, computer and human
			HumanPlayer humanPlayer = new HumanPlayer();
			ComputerRandomPlayer computerPlayer = new ComputerRandomPlayer();
			
			//put players into the "box", shake the "box", get random player
			Player[] players = {humanPlayer, computerPlayer};
			Random randomIndex = new Random();
			int playerIndex = randomIndex.nextInt(2);
			//playing the game until one won or game is draw
			while (game.getGameState() == GameState.PLAYING)
			{
				players[playerIndex].play(game);
				if (playerIndex == 1) playerIndex = 0;
				else playerIndex = 1;
			}
			//print result of the game
			System.out.println("Game over");
			System.out.println(game.toString());
			if (game.getGameState() == GameState.XWIN) System.out.println("Result: XWIN");
			else if (game.getGameState() == GameState.OWIN) System.out.println("Result: OWIN");
			else System.out.println("Result: DRAWN");
			//ask human if human want to play again
			System.out.print("Play again (Y)?: ");
			Console console = System.console();
			console = System.console();
			String answer = console.readLine();
			if (answer.equals("y") || answer.equals("Y")) playingRound = true;
			else playingRound = false;
		}
	}
}
