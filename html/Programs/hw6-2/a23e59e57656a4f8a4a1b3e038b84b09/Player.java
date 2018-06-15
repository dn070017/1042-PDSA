import java.util.ArrayList;
import java.util.Arrays;

public class Player implements Comparable<Player>{ 

    private Card[] cards = new Card[5];
    private String name;
     
    // DO NOT MODIFY THIS
    public Player(String name) {
        this.name = name;
    }
     
    // DO NOT MODIFY THIS
    public String getName() {
        return this.name;
     }
     
    // DO NOT MODIFY THIS
    public void setCards(Card[] cards) {
        this.cards = cards;
    }
     
    // TODO 
    public int compareTo(Player that) {
.

//    	Arrays.sort(this.cards, Card.SUIT_ORDER);
//    	Arrays.sort(that.cards, Card.SUIT_ORDER);
    	Arrays.sort(this.cards);
    	Arrays.sort(that.cards);
    	
    	String[] faceString = {""1"",""2"",""3"",""4"",""5"",""6"",""7"",""8"",""9"",""10"",""J"",""Q"",""K"",""A""};
    	String[] suitString = {""Clubs"",""Diamonds"",""Hearts"",""Spades""};
    	String[] type= {""high card"",""one pair"",""two pair"",""straight"",""flush"",""full house""};
    	
    	int[] faceint1 = new int[cards.length];
    	int[] suitint1 = new int[cards.length];
    	int[] faceint2 = new int[cards.length];
    	int[] suitint2 = new int[cards.length];
    	
    	int player1typeindex = 0;
    	int player2typeindex = 0;
    	
    	UF ufthisface = new UF(cards.length);
    	UF ufthatface = new UF(cards.length);
    	UF ufthissuit = new UF(cards.length);
    	UF ufthatsuit = new UF(cards.length);
    	
    	int samesuitcount=0;
    	int samefacecount=0;
    	
    	//faceint initialize
    	for(int i = 0; i<cards.length;i++){
        	for(int j = 0; j<faceString.length;j++){
        		if(this.cards[i].getFace().equals(faceString[j])){
        			faceint1[i] = j+1;
        		}
        		if(that.cards[i].getFace().equals(faceString[j])){
        			faceint2[i] = j+1;
        		}
    		}
    	}

    	//suitint initialize
    	for(int i = 0; i<cards.length;i++){
        	for(int j = 0; j<suitString.length;j++){
        		if(this.cards[i].getSuit().equals(suitString[j])){
        			suitint1[i] = j;
        		}
        		if(that.cards[i].getSuit().equals(suitString[j])){
        			suitint2[i] = j;
        		}
    		}
    	}
    	
    	//this union
    	for(int i =0; i<cards.length; i++){
        	for(int j =0; j<cards.length; j++){
        		if(this.cards[i].getFace().equals(this.cards[j].getFace())){
        			ufthisface.union(i, j);
        		}
        		if(this.cards[i].getSuit().equals(this.cards[j].getSuit())){
        			ufthissuit.union(i, j);
        		}
        	}
    	}
    	
    	//that union
    	for(int i =0; i<cards.length; i++){
    		for(int j =0; j<cards.length; j++){
        		if(that.cards[i].getFace().equals(that.cards[j].getFace())){
        			ufthatface.union(i, j);
        		}
        		if(that.cards[i].getSuit().equals(that.cards[j].getSuit())){
        			ufthatsuit.union(i, j);
        		}
        	}
    	}

    	//list 1
		ArrayList<String> arrlist1 = new ArrayList<String>();
    	int samecount;
    	if(ufthisface.count()==2){
			player1typeindex = 5;
    	}else if(ufthissuit.count()==1){
    		player1typeindex=4;
    	}else if(ufthisface.count()==3){
    		for(int i = 0;i<cards.length-2;i++){
    			if(faceint1[i]==faceint1[i+2]){
    				player1typeindex=1;
    				break;
    			}else{
    				player1typeindex=2;
    			}
    		}
    	}else if(ufthisface.count()==4){
    		player1typeindex=1;
    	}else if(ufthisface.count()==5){
    		for(int i = 1;i<cards.length;i++){
    			if((faceint1[i]-faceint1[i-1])==1){
    				player1typeindex=3;
    			}else if(faceint1[0]==2 & faceint1[1]==3 & faceint1[2]==4 & faceint1[3]==5 & faceint1[4]==14){
    	    		player1typeindex=3;
    			}else{
    				player1typeindex=0;
    			}
    		}
    	}
    	
    	
    	//list 2
    	ArrayList<String> arrlist2 = new ArrayList<String>();
    	if(ufthatface.count()==2){
			player2typeindex = 5;
    	}else if(ufthatsuit.count()==1){
    		player2typeindex=4;
    	}else if(ufthatface.count()==3){
    		for(int i = 0;i<cards.length-2;i++){
    			if(faceint2[i]==faceint2[i+2]){
    				player2typeindex=1;
    				break;
    			}else{
    				player2typeindex=2;
    			}
    		}
    	}else if(ufthatface.count()==4){
    		player2typeindex=1;
    	}else if(ufthatface.count()==5){
    		for(int i = 1;i<cards.length;i++){
    			if((faceint2[i]-faceint2[i-1])==1){
    				player2typeindex=3;
    			}else if(faceint2[0]==2 & faceint2[1]==3 & faceint2[2]==4 & faceint2[3]==5 & faceint2[4]==14){
    	    		player2typeindex=3;
    			}else{
    				player2typeindex=0;
    			}
    		}
    	}
    	int highface1=0;
    	int highface2=0;
    	int highsuit1=0;
    	int highsuit2=0;
    	
    	if(player1typeindex > player2typeindex){
    		return +1;
    	}else if(player1typeindex < player2typeindex){
    		return -1;
    	}else if(player1typeindex == player2typeindex){
    		if(player1typeindex==5){
    			for(int i = 0;i<cards.length-2;i++){
        			if(faceint1[i]==faceint1[i+2]){
        				highface1=faceint1[i];
        				highsuit1=suitint1[i];
        			}
        			if(faceint2[i]==faceint2[i+2]){
        				highface2=faceint2[i];
        				highsuit2=suitint2[i];
        			}
    			}
    			if(highface1 > highface2){
    				return +1;
    			}else if(highface1 < highface2){
    				return -1;
    			}else{
    				if(highsuit1 > highsuit2){
        				return +1;
        			}else if(highsuit1 < highsuit2){
        				return -1;
        			}else{
        				return 0;
        			}
    			}
    		}
    		if(player1typeindex==2 || player1typeindex==1){
    			for(int i = cards.length-1; i>0;i--){
    				if(faceint1[i]==faceint1[i-1]){
    					highface1=faceint1[i];
    					highsuit1=suitint1[i];
    				}
    				if(faceint2[i]==faceint2[i-1]){
    					highface2=faceint2[i];
    					highsuit2=suitint2[i];
    				}
    			}
    			if(highface1 > highface2){
    				return +1;
    			}else if(highface1 < highface2){
    				return -1;
    			}else{
    				if(highsuit1 > highsuit2){
        				return +1;
        			}else if(highsuit1 < highsuit2){
        				return -1;
        			}else{
        				return 0;
        			}
    			}
    		}
    		if(player1typeindex==3){
    			if(faceint1[0]==2 & faceint1[cards.length-1]==14){
    				highface1=faceint1[cards.length-1-1];
    				highsuit1=suitint1[cards.length-1-1];
    			}else{
    				highface1=faceint1[cards.length-1];
    				highsuit1=suitint1[cards.length-1];
    			}
    			if(faceint2[0]==2 & faceint2[cards.length-1]==14){
    				highface2=faceint2[cards.length-1-1];
    				highsuit2=suitint2[cards.length-1-1];
    			}else{
    				highface2=faceint2[cards.length-1];
    				highsuit2=suitint2[cards.length-1];
    			}
    			if(highface1 > highface2){
    				return +1;
    			}else if(highface1 < highface2){
    				return -1;
    			}else{
    				if(highsuit1 > highsuit2){
        				return +1;
        			}else if(highsuit1 < highsuit2){
        				return -1;
        			}else{
        				return 0;
        			}
    			}
    		}
    		if(player1typeindex==0 || player1typeindex==4){
    			if(faceint1[cards.length-1] > faceint2[cards.length-1]){
    				return +1;
    			}else if(faceint1[cards.length-1] < faceint2[cards.length-1]){
    				return -1;
    			}else{
    				if(suitint1[cards.length-1] > suitint2[cards.length-1]){
        				return +1;
        			}else if(suitint1[cards.length-1] < suitint2[cards.length-1]){
        				return -1;
        			}else{
        				return 0;
        			}
    			}
    		}
    	}
    	
    	
//    	System.out.println("""");
//    	System.out.println(""that"");
//    	for(int i =0; i<5; i++){
//    		System.out.println(that.cards[i].getSuit() + that.cards[i].getFace());
//    	}
    	
//    	System.out.println("""");
//    	System.out.println(""this"");
//    	for(int i =0; i<5; i++){
//    		System.out.println(this.cards[i].getSuit() + this.cards[i].getFace());
//    	}
//    	System.out.println("""");
//    	System.out.println(""that"");
//    	for(int i =0; i<5; i++){
//    		System.out.println(that.cards[i].getSuit() + that.cards[i].getFace());
//    	}
//    	System.out.println("""");
//    	System.out.println(""ufthis"");
//    	for(int i =0; i<cards.length; i++){
//    		System.out.println(ufthisface.find(i));
//    	}
//		System.out.println(""count""+ufthisface.count());
//
//    	System.out.println("""");
//    	System.out.println(""ufthat"");
//    	for(int i =0; i<cards.length; i++){
//    		System.out.println(ufthatface.find(i));
//    	}
        return 0;
    }
}

