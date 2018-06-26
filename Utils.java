import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.charset.StandardCharsets;
import java.io.IOException;
import java.util.Random;

public class Utils {

    
    public static String[] readAllLines(String fileName) {

	String[] lines = null;

	try {
	    lines = Files.readAllLines(Paths.get(fileName), StandardCharsets.UTF_8).toArray(new String[0]);
	} catch (IOException e) {
	    e.printStackTrace();
	    System.exit(1);
	}

	return lines;

    }

    private static Random generator;

	public static void shuffleDeck(Card[] deck){

		if( deck == null ) {
			System.out.println("shuffleDeck: wrong call");
			return ;
		}
		
		if(generator == null) {
			generator = new Random();
		}
		
		for(int i = deck.length -1 ; i > 1 ; i--){
			swapItems(deck, i, generator.nextInt(i-1));
		}
	}

	private static void swapItems(Card[] deck, int i, int j){
		Card intermediate = deck[i];
		deck[i]=deck[j];
		deck[j]=intermediate;
	}

	public static void sortDeck(Card[] deck){
	 	java.util.Arrays.sort(deck);
	}

}
