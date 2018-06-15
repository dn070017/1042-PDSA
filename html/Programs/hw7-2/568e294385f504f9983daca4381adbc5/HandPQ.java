
public class HandPQ {

          public static void main(String[] args) throws Exception {

                    In in = new In(args[0]);
                    String line = in.readLine();
//                    int N = Integer.valueOf(line.split("","")[0]);
                    int topN = Integer.valueOf(line.split("","")[1]);

//                    Hand[] HandArray = new Hand[N];

                    MinPQ<Hand> pq = new MinPQ<Hand>();
//                    System.out.println(pq.size());
//                    int idx=0;
                    while ((line = in.readLine()) != null) {

                              Card[] cardsArray = new Card[5];
                              String[] cardStr = line.split("","");
                              for (int i = 0; i < 5; i++) {
                                        Card card = new Card( cardStr[i].split(""_"")[1],  cardStr[i].split(""_"")[0]);
                                        cardsArray[i] = card;
                              }
                              Hand hand = new Hand(cardsArray);
//                              HandArray[idx++]=hand;
                              if (pq.size() < topN) {
                                        pq.insert(hand);                                        
                              }
                              else{
                                        pq.insert(hand);
                                        pq.delMin();
                              }
                    }
//                    System.out.println(pq.size());
//                    System.out.println("""");
                    Hand out = new Hand(pq.delMin().getCards());
                    for(int i = 4 ; i>-1;i--){
                              System.out.print(out.getCards()[i].getSuit()+'_'+out.getCards()[i].getFace());
                              if(i != 0){
                                        System.out.print("","");
                              }
                              else continue;
                    }
//                    System.out.println("""");
//                                        System.out.println(pq.size());
                    
                    
          }

}

