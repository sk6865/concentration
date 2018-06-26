

public class Card implements Comparable<Card>{

	public static final char hidden = '*';


	private boolean show;
	private char face;


 	public  Card(char face){
 		this.face = face;
 		show = false;
 	}

 	public char getFace(){
 		return face; 
 	}

 	public void setShow(boolean show){
 		this.show = show;
 	}

 	public boolean getShow(){
 		return show;
 	}

	public int compareTo(Card o) {

		if(getFace() < o.getFace()) {
			return -1;
		} else if (getFace() == o.getFace()) {
			return 0;
		} else {
			return 1;
		}
	}

 	public String toString(){
 		if (getShow()) {
 			return Character.toString(face);
 		} else {
 			return Character.toString(hidden);
 		}
 	}
 }
