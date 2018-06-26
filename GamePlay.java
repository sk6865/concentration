
import java.util.Scanner;

public class GamePlay{

 	private static Scanner sc;

 	private PlayingDeck deck;


	private static void waitForPlayer(){
     	sc.nextLine();

		System.out.println("Press enter to continue...");
		sc.nextLine();
	}



	private static void clearScreen(){
		for(int i = 0; i < 50 ; i++) {
			System.out.println();
		}
	}

	private  void playGame() {

    	System.out.println("Ready to play ...");

    
    	int guesses, p1, p2;
    	boolean ok;
    	guesses = 0;
    
    	while(!deck.gameFinished()) {

        	System.out.println(deck);
        	p1 = p2 = 0;
        	ok = false;
        	while(!ok) {
 	            System.out.println("\nEnter two distinct positions on the board that you want revealed."
    	        	+ "\ni.e two integers in the range [1, " + deck.deckSize() + "]");
    	        System.out.println("Enter position 1: ");
				p1 = sc.nextInt();
    	        System.out.println("Enter position 2: ");
				p2 = sc.nextInt();
 
				ok = true;
				if(p1 < 1 || p2 < 1 || p1 > deck.deckSize() || p2 > deck.deckSize()){
	                System.out.println("One of both of your chosen positions is out of range.");
	                ok = false;
				} else if (deck.isUncovered(p1) || deck.isUncovered(p2)) {
	                System.out.println("One or both of your chosen positions has already been paired.");
	                ok = false;					
				} else if (p1 == p2) {
	                System.out.println("You chose the same positions.");
	                ok = false;										
				}
 
 				if(!ok) {
 					System.out.println("Please try again. This guess did not count."
 						+"Your current number of guesses is " + guesses + ".");
 				}
        	}
        
        	deck.setShowPair(p1,p2,true);
        	System.out.println(deck);
        	if(!deck.matchedPair(p1,p2)){
	        	deck.setShowPair(p1,p2,false);        		
        	}
	        waitForPlayer();
	        clearScreen();
	        guesses++;
		}
    	
    	System.out.println("Congratulations! You completed the game with " 
    		+ guesses + " guesses. That is " + (guesses - deck.deckSize()/2)  
    		+ " more than the best possible.");

	}


 	public  GamePlay(){
		
		sc = new Scanner(System.in);
		deck = new PlayingDeck();

	}

	public void start() {
		System.out.println("Welcome to my Concentration game");

		System.out.println("Would you like (enter 1 or 2 to indicate your choice):");
		System.out.println("(1) me to generate a rigorous deck of cards for you");
		System.out.println("(2) or, would you like me to read a deck from a file?");

		int choice;
		choice = sc.nextInt();
 		while (choice!=1 && choice!=2) {
		    System.out.println("" + choice + " is not an existing option. Please try again. Enter 1 or 2 to indicate your choice");
			choice = sc.nextInt();
		}

		if(choice == 1) {
	    	System.out.println("You chose to have a rigorous deck generated for you");
	 		int size;
			size = -1;
			while(size%2 != 0  || size < 1 || size > 52){
				System.out.print("How many cards do you want to play with?\nEnter an even number between 1 and 52: ");
				size = sc.nextInt();	 
			}
			deck.createDeck(size);
		} else {
 		    sc.nextLine();

			System.out.println("You chose to load a deck of cards from a file.");
			System.out.print("Enter the name of the file: ");
			String fileName = sc.nextLine();

			deck.readRawDeck(fileName);
			deck.cleanUpDeck();
			if (deck.isRigorous()){
				System.out.println("This deck is now playable and rigorous and it has "+deck.deckSize()+" cards.");
			} else {
				System.out.println("This deck is now playable but is NOT rigorous and it has "+deck.deckSize()+" cards.");

			}
		}
		deck.shuffleDeck();
		playGame();
	}


 
	public static void main(String[] args){
	
		GamePlay game = new GamePlay();		

		game.start();
	
		
	}
}