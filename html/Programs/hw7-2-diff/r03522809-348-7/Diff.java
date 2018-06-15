/*
.
 * To change this template file, choose Tools | Templates
.
 */

/**
 *
 * @author Dennis
 */
import java.io.BufferedReader;
import java.io.FileReader;

public class HandPQ {

    public static void main(String[] args) throws Exception {

        try(BufferedReader br = new BufferedReader(new FileReader(args[0]))){

            String[] header = br.readLine().split("","");
            int id=0;
            int count = Integer.parseInt(header[0]);
            int target = Integer.parseInt(header[1]);
            MinPQ<Hand> pqueue =new MinPQ<Hand>();
            for(int j=0;j<count;j++) {

            String[] cardStr = br.readLine().split("","");
            Card[] cardsArray = new Card[5];
                for(int i = 0; i < 5; i++){
                    String[] sep = cardStr[i].split(""_"");
                    Card card = new Card(sep[1], sep[0]);
                    cardsArray[i] = card;
//                    System.out.println(cardsArray[i].getSuit()+""\t""+cardsArray[i].getFace());
                }
                
            Hand handcard =new Hand(cardsArray);
//            System.out.println(cardsArray[0].getFace());
            
            pqueue.insert(handcard);
                if(pqueue.size()>target){
                    pqueue.delMin();
                }
                
            }
            Card[] showcardsArray = new Card[5];
            showcardsArray=pqueue.min().getCards();
            System.out.println(showcardsArray[0].getSuit()+""_""+showcardsArray[0].getFace()+"",""+showcardsArray[1].getSuit()+""_""+showcardsArray[1].getFace()+"",""
                              +showcardsArray[2].getSuit()+""_""+showcardsArray[2].getFace()+"",""+showcardsArray[3].getSuit()+""_""+showcardsArray[3].getFace()+"",""
                              +showcardsArray[4].getSuit()+""_""+showcardsArray[4].getFace());
            
        }
    }
}

