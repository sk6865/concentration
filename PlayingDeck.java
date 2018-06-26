import java.util.Random;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.charset.StandardCharsets;
import java.io.IOException;

public class PlayingDeck {

	private static final int LINE_LENGTH = 10;

	private Card[] deck;
 	private int numberUncovered;

 	public PlayingDeck(){
 		deck = null;
 		numberUncovered = 0;
 	}



	public void createDeck(int sizeDeck){
		
		if( sizeDeck < 1 || sizeDeck > 52 || sizeDeck%2 == 1 ) {
			System.out.println("createDeck: wrong size: " + sizeDeck
				+". It should be an even integer between 2 and 52");
			return;
		}

		deck = new Card[sizeDeck];

    	int currentASCII = (int)'A';

    	for(int i =0; i < sizeDeck/2; i++){
    	  deck[i]=new Card((char)currentASCII);
          deck[i+sizeDeck/2]=new Card((char)currentASCII);
          currentASCII++;
      	}
      	numberUncovered = 0;
    }

    public void shuffleDeck(){
    	Utils.shuffleDeck(deck);
    }
    
    public int deckSize(){
    	return deck.length;
    }

    public boolean isUncovered(int p){
    	return deck[p-1].getShow();
    }

    
    
	public String toString(){

		int numberOfLines;
		StringBuffer answer = new StringBuffer();

		numberOfLines = deck.length/LINE_LENGTH;

		for(int l=0; l < numberOfLines; l++){
			for(int i =  0 ; i < LINE_LENGTH; i++){
				answer.append("\t"+ deck[l*LINE_LENGTH+i]);
			}
			answer.append("\n");
			for(int i = 0 ; i < LINE_LENGTH; i++){
				answer.append("\t" + (l*LINE_LENGTH+i+1));
			}
			answer.append("\n");
		}
		for(int i =  0 ; i < deck.length%LINE_LENGTH; i++){
			answer.append("\t" + deck[numberOfLines*LINE_LENGTH+i]);
		}
		answer.append("\n");
		for(int i = 0 ; i < deck.length%LINE_LENGTH; i++){
			answer.append("\t" + (numberOfLines*LINE_LENGTH+i+1));
		}
		answer.append("\n");
		return answer.toString();
	}

	
	public void setShowPair(int p1, int p2, boolean show){
		deck[p1-1].setShow(show);
		deck[p2-1].setShow(show);
		if(show) {
			numberUncovered += 2;
		} else {
			numberUncovered -= 2;
		}
	}

	public boolean matchedPair(int p1, int p2){
		return deck[p1-1].getFace() == deck[p2-1].getFace();
	}


    
    
    
    
    
    
    
    
 	public void readRawDeck(String fileName){

	    String[] lines = null;

	    lines=Utils.readAllLines(fileName);

	    deck = new Card[lines.length];

	    for(int i = 0; i < lines.length; i++){
	        deck[i]= new Card(lines[i].charAt(0));
	    }

 
 	}


 	


	public void cleanUpDeck(){

	    System.out.println("Removing one of each cards that appears odd number of times and removing all stars ...");
	    
	    Card[] intermediate;


	    Utils.sortDeck(deck);
	    intermediate = new Card[deck.length];
	    int currentIndex = 0;
	    Card first, second;
	    for(int i = 0; i < deck.length - 1; i++) {
	    	first = deck[i];
	    	second = deck[i+1];
	    	if((first.getFace() == second.getFace()) && (first.getFace() != Card.hidden)) {
	    		intermediate[currentIndex++] = first;
	    		intermediate[currentIndex++] = second;
	    		i++;
	    	}
	    }

	    deck = new Card[currentIndex];
	    for(int i = 0; i < currentIndex; i++) {
	    	deck[i] = intermediate[i];
	    }
	}



    
    
    
    public  boolean isRigorous(){


	    if (deck.length == 0) {
	    	return true;
	    }

	    Utils.sortDeck(deck);
	    int i = 0;
	    while(i < deck.length - 2) {
	    	if((deck[i].getFace() != deck[i+1].getFace()) || 
	    	   (deck[i].getFace() == deck[i+2].getFace())) {
	    		return false;
	    	}
	    	i = i + 2;
	    }
	    return true;
	}

	public boolean gameFinished(){
		return numberUncovered == deck.length;
	}

}
