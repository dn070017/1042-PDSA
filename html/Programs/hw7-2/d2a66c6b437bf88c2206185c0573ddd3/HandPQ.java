/*
.
 * To change this template file, choose Tools | Templates
.
 */


/**
 *
 * @author Lenovo
 */
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Arrays;

public class HandPQ {

    public static void main(String[] args) throws Exception {

        try(BufferedReader br = new BufferedReader(new FileReader(args[0]))){

            String[] header = br.readLine().split("","");
            int count = Integer.parseInt(header[0]);
            int target = Integer.parseInt(header[1]);
            
            MinPQ<Hand> HandSet = new MinPQ<Hand>(target);
            for(int i=0;i<target;i++){
                String[] row1 = br.readLine().split("","");
                //System.out.println(row1[0]);
                
                Card[] cardsArray1 = new Card[5];
                for (int j=0;j<5;j++){
                    String[] row2 = row1[j].split(""_"");
                    //Card aCard=new Card(row2[1],row2[0]);
                    //cardsArray1[j] = aCard;
                    cardsArray1[j] = new Card(row2[1],row2[0]);
                }
                
                //Hand aHand = new Hand(cardsArray1);
                //HandSet.insert(aHand); 
                HandSet.insert(new Hand(cardsArray1)); 
            }
            
            for(int i=target;i<count;i++){
                //Hand TempMin = HandSet.delMin();
                Hand TempMin = HandSet.min();
                
                String[] row1 = br.readLine().split("","");
                
                Card[] cardsArray1 = new Card[5];
                for (int j=0;j<5;j++){
                    String[] row2 = row1[j].split(""_"");
                    //Card aCard=new Card(row2[1],row2[0]);
                    //cardsArray1[j] = aCard;
                    cardsArray1[j] = new Card(row2[1],row2[0]);
                }
                Hand aHand = new Hand(cardsArray1);
                
                int r = aHand.compareTo(TempMin);
                if (r==1){
                    HandSet.delMin();
                    HandSet.insert(aHand);
                }
                //else HandSet.insert(TempMin);
            }
            
            //Hand FinalMin = HandSet.delMin();
            //Card[] FinalCard = FinalMin.getCards();
            Card[] FinalCard = HandSet.delMin().getCards();
            //String[] Result = new String[5];
            /*
            for (int i=0;i<5;i++){
                System.out.println(FinalCard[i].getSuit()+""_""+FinalCard[i].getFace());
            }
            */
            
            String Result2 = """";
            for (int i=0;i<4;i++){
                //String face = FinalCard[i].getFace();
                //String suit = FinalCard[i].getSuit();
                Result2=Result2+FinalCard[i].getSuit()+""_""+FinalCard[i].getFace()+"","";
            }
            //String face = FinalCard[4].getFace();
            //String suit = FinalCard[4].getSuit();
            Result2=Result2+FinalCard[4].getSuit()+""_""+FinalCard[4].getFace();
            
            System.out.println(Result2);
                    

        }
    } 
}

