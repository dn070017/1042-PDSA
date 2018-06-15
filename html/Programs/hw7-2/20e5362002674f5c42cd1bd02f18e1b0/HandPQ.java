//package handpq;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.PriorityQueue;
import java.util.Queue;

public class HandPQ {

    public static void main(String[] args) throws Exception {
        Queue<Hand> maxPQ;
        try(BufferedReader br = new BufferedReader(new FileReader(args[0]))){

            String[] header = br.readLine().split("","");
            int count = Integer.parseInt(header[0]);
            int target = Integer.parseInt(header[1]);
            maxPQ = new PriorityQueue<>();
            for (int i=0;i<count;i++){
                //br.readLine()
                Card[] cardsArray = new Card[5];
                String[] cardStr = br.readLine().split("","");
                for(int j = 0; j < 5; j++){
                    String[] sep = cardStr[i].split(""_"");
                    Card card = new Card(sep[1], sep[0]);
                    cardsArray[i] = card;
                }
                Hand hand=new Hand(cardsArray);
                if (maxPQ.size()<=target)
                    maxPQ.add(hand);
                else{
                    Hand temp=maxPQ.peek();
                    if(Hand.compareTo(temp)>0){
                        maxPQ.poll();
                        maxPQ.add(hand);
                    }
                }
            }
        }
        Hand result=maxPQ.poll();
        for (Card cards:result.getCards())
            System.out.println(cards);
    }
}

