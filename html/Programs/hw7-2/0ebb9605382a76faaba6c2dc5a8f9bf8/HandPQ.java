//import edu.princeton.cs.algs4.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Arrays;

public class HandPQ {

    public static void main(String[] args) throws Exception {
    	Hand hand;
    	MinPQ<Hand> minPQ;

        try(BufferedReader br = new BufferedReader(new FileReader(args[0]))){
            String[] header = br.readLine().split("","");
            int count = Integer.parseInt(header[0]);
            int target = Integer.parseInt(header[1]);
            minPQ = new MinPQ<Hand> (target);

            for (int i = 0; i<count; i++){
            	Card[] cards = new Card[5]; 
        		String[] handReg = br.readLine().split("","");

        		for (int j = 0; j<5; j++){
        			String[] cardReg = handReg[j].split(""_"");
        			cards[j] = new Card(cardReg[1], cardReg[0]);
        		}
        		hand = new Hand(cards);
        		if(i < target){
        			minPQ.insert(hand);
        		}
        		else{
        			Hand minHand = minPQ.delMin();
        			if (minHand.compareTo(hand) == 1){
        				minPQ.insert(minHand);
        			}
        			else{
        				minPQ.insert(hand);
        			}
        		}
        		// maxPQ.insert(new Hand(cards));
        	}


        	Card[] card = minPQ.delMin().getCards();
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

