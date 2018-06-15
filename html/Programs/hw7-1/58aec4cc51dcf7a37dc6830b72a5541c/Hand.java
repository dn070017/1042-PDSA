import java.util.*;

public class Hand implements Comparable<Hand> {

    // sorted by Card value are recommended but not necessary
	private Card[] cards = new Card[5];

    // TODO, Judge System will call this constructor once for each hand
    public Hand(Card[] cards){
        this.cards = cards;
        return;
    }

    // TODO
    public int compareTo(Hand that) {
    	Arrays.sort(this.cards, Card.SUIT_ORDER);
    	Arrays.sort(that.cards, Card.SUIT_ORDER);
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
    		boolean flag = true;
    		for(int i = 1;i<cards.length;i++){
    			if((faceint1[i]-faceint1[i-1])!=1){
    				flag=false;
    			}
    		}
    		if(flag == true){
    			player1typeindex=3;
    		}else if(faceint1[0]==2 & faceint1[1]==3 & faceint1[2]==4 & faceint1[3]==5 & faceint1[4]==14){
	    		player1typeindex=3;
			}else{
				player1typeindex=0;
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
    		boolean flag = true;
    		for(int i = 1;i<cards.length;i++){
    			if((faceint2[i]-faceint2[i-1])!=1){
    				flag=false;
    			}
    		}
    		if(flag == true){
    			player2typeindex=3;
    		}else if(faceint2[0]==2 & faceint2[1]==3 & faceint2[2]==4 & faceint2[3]==5 & faceint2[4]==14){
	    		player2typeindex=3;
			}else{
				player2typeindex=0;
			}
    	}
    	
    	
    	//compare
    	int highface1=0;
    	int highface2=0;
    	int highsuit1=0;
    	int highsuit2=0;
    	
    	if(player1typeindex > player2typeindex){
    		return +1;
    	}else if(player1typeindex < player2typeindex){
    		return -1;
    	}else if(player1typeindex == player2typeindex){
    		if(player1typeindex == 5){
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
    					break;
    				}
    			}
    			for(int i = cards.length-1; i>0;i--){
    				if(faceint2[i]==faceint2[i-1]){
    					highface2=faceint2[i];
    					highsuit2=suitint2[i];
    					break;
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
    	return 0;
    }

      // Do not modified this function
    public Card[] getCards() { return this.cards; }
}

/*
 * HW7 - Priority Queue

Assignment

Hand.java
請實作一份 Hand 型別。並上傳至作業 7-1。Hand 為五張撲克牌的組合，需要實作 Comparable 介面 (interface)。此作業中，我們只考慮 full house > flush > straight > two pair > one pair > high card (Reference: http://en.wikipedia.org/wiki/List_of_poker_hands) 這六種組合。詳細的 api 請參考附件。

HandPQ.java
此份作業需要同學讀進一個文字檔。文字檔的第一行會利用逗點隔開，其中包含兩個數字，第一個數字代表需要處理的 Hand 個數，第二個數字代表欲求得第幾大的 Hand。第二行開始到文件的結尾則為五張牌的組合。舉例來說，第一行為 4,3，即代表第 2~5 行為需要處理的 Hand，而答案則是要找出 2~5 行這 4 個 Hand 裡面第 3 大的 Hand。最後請輸出這個 Hand 的所有 Card (依據 Card 的大小，由小到大輸出)。

Example Input:

4,3
Hearts_10,Hearts_9,Hearts_Q,Hearts_J,Hearts_8
Spades_A,Hearts_K,Hearts_10,Diamonds_4,Clubs_4
Diamonds_J,Diamonds_K,Diamonds_Q,Diamonds_10,Clubs_9
Clubs_A,Hearts_8,Diamonds_Q,Diamonds_10,Clubs_2

Example Output:

Clubs_4,Diamonds_4,Hearts_10,Hearts_K,Spades_A Note
1. 批改系統會獨立的批改 HW7-1 與 HW7-2，所以兩者的 Hand.java 不會互通。

2. 本次作業請使用 Priority Queue 來實作，批改系統會測試程式所使用的記憶體評估分數。

3. 作業中會使用到 HW6 的 Card.java，批改時會使用批改系統的 Card.java。

4. HW7-1 可以從 HW6 的 Player.java 經過部分的修改後建構。

Files: hw7.zip

 */
