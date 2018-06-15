import  java.util.*;
public class HandPQ {

          public static void main(String[] args) throws Exception {

                    In in = new In(args[0]);
                    String line = in.readLine();
                    int N = Integer.valueOf(line.split("","")[0]);
                    int topN = Integer.valueOf(line.split("","")[1]);

                    MinPQ<Hand> pq = new MinPQ<Hand>();
//                    System.out.println(pq.size());
//                    int idx=0;
//                    while ((line = in.readLine()) != null) {
                    for (int count = 0; count < N; count++) {
                              line = in.readLine();
                              Card[] cardsArray = new Card[5];
                              String[] cardStr = line.split("","");
                              for (int i = 0; i < 5; i++) {
                                        Card card = new Card(cardStr[i].split(""_"")[1], cardStr[i].split(""_"")[0]);
                                        cardsArray[i] = card;
                              }
                              Hand hand = new Hand(cardsArray);
//                              HandArray[idx++]=hand;
                              if (pq.size() < topN) {
                                        pq.insert(hand);
//                                        System.out.println(""card "" + (count + 1) + "" : "" + pq.size());
                              } else {
                                        pq.insert(hand);
//                                        System.out.println(""card "" + (count + 1) + "" : "" + pq.size());
                                        pq.delMin();
//                                        System.out.println(""after del size: "" + pq.size());
                              }
                    }
//                    System.out.println(""size: "" + pq.size());
//                    System.out.println("""");

                    Hand out = new Hand(pq.delMin().getCards());
                    MergeX.sort(out.getCards(),Card.SUIT_ORDER);
                    
                                  HashMap<String,Integer> map =new HashMap<String,Integer>();
              for(int i =2;i<11;i++){
                        map.put(String.valueOf(i),i);
              }
              map.put(""J"",11);
              map.put(""Q"",12);
              map.put(""K"",13);
              map.put(""A"",14);
              map.put(""Spades"",18);
              map.put(""Hearts"",17);
              map.put(""Diamonds"",16);
              map.put(""Clubs"",15);
                    
                    
               for(int i = 0 ; i < out.getCards().length-1; i++){
                   for(int j = i+1 ; j < out.getCards().length;j++){
                        if(map.get(out.getCards()[i].getFace()) > map.get(out.getCards()[j].getFace()) ) continue;
                        else{
                             String temp_Face=out.getCards()[j].getFace();
                             String temp_Suit=out.getCards()[j].getSuit();
                             out.getCards()[j] = new Card(out.getCards()[i].getFace(),out.getCards()[i].getSuit());
                             out.getCards()[i]= new Card(temp_Face , temp_Suit); 
                        }
                   }
              }
                    
                    
                    
                    for (int i = 4; i > -1; i--) {
//                    for(int i = 0 ; i<5;i++){                              
                              System.out.print(out.getCards()[i].getSuit() + '_' + out.getCards()[i].getFace());
                              
                              if (i != 0) {
                                        System.out.print("","");
                              } else {
                                        continue;
                              }
                    }
//                    System.out.println("""");
//                                        System.out.println(pq.size());

          }

}

