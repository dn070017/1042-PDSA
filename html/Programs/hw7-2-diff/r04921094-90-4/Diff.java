//import edu.princeton.cs.algs4.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Arrays;

public class HandPQ {

    public static void main(String[] args) throws Exception {
    	Hand[] hands;
    	MaxPQ<Hand> maxPQ;

        try(BufferedReader br = new BufferedReader(new FileReader(args[0]))){
            String[] header = br.readLine().split("","");
            int count = Integer.parseInt(header[0]);
            hands = new Hand[count];
            maxPQ = new MaxPQ<Hand> (count);
            int target = Integer.parseInt(header[1]);

            for (int i = 0; i<count; i++){
            	Card[] cards = new Card[5]; 
        		String[] handReg = br.readLine().split("","");

        		for (int j = 0; j<5; j++){
        			String[] cardReg = handReg[j].split(""_"");
        			cards[j] = new Card(cardReg[1], cardReg[0]);
        		}
        		hands[i] = new Hand(cards);
        		maxPQ.insert(hands[i]);
        		// maxPQ.insert(new Hand(cards));
        	}

        	for (int i = 0; i < target - 1; i++){
        		maxPQ.delMax();
        	}

        	Card[] card = maxPQ.delMax().getCards();
        	Arrays.sort(card);
        	for(int i = 0; i < 5; i++){
        		if (i != 4){
        			System.out.print(card[i].getSuit() + ""_"" + card[i].getFace() + "","");
        		}
        		else{
        			System.out.print(card[i].getSuit() + ""_"" + card[i].getFace());
        		}
        	}
        }


    }

}

