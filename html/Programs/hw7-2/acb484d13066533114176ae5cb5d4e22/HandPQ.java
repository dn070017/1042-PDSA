import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Arrays;

public class HandPQ {

    public static void main(String[] args) throws Exception {

        try(BufferedReader br = new BufferedReader(new FileReader(args[0]))){           
            String[] header = br.readLine().split("","");
            int count = Integer.parseInt(header[0]);
            int target = Integer.parseInt(header[1]);
            int idx=0; 
                //System.out.printf(""count=""+count+"","");
                //System.out.println(""target=""+target);
            Hand[] hands = new Hand[count];            
            MinPQ<Hand> pq = new MinPQ<Hand>() ;            
                //read file
              for(String in = br.readLine(); in != null; in = br.readLine()) {
                String[] cardStr = in.split("","");
                Card[] cardsArray = new Card[5];
                //file to card               
                for(int i = 0; i < 5
                        ; i++){
                    String[] sep = cardStr[i].split(""_"");
                    Card card = new Card(sep[1], sep[0]);
                    cardsArray[i] = card;                      
                    //System.out.printf(""_""+cardsArray.length); 
                }//end of loading 5 cards                  
                //System.out.printf(""=====""+cardsArray[1].getFace()+"",""+cardsArray[1].getSuit()+""====="");
                //print card
                /*印card
                for(int i =0;i<cardsArray.length;i++){
                    System.out.printf(""(""+cardsArray[i].getSuit()+"",""+cardsArray[i].getFace()+"")"");
                }
                    System.out.print(""\n"");                   
                 //
                */
               Hand hand=new Hand(cardsArray);
                hands[idx]=hand;
                idx++;
                }//end of reading a in
              //print hands
              Card[] checkhand = new Card[5];
              //System.out.println(pq.size());
              for(int i=0;i<hands.length;i++){
                  checkhand=hands[i].getCards();
                  Arrays.sort(checkhand);
                  for(int x =0;x<checkhand.length;x++){
                   //System.out.printf(""[""+checkhand[x].getSuit()+"",""+checkhand[x].getFace()+""]"");
                }
                   //System.out.print(""\n"");
                }
              //print hands^
              //insert hand into pq
              
              Card[] insertcard = new Card[5];
              //insert hand to pq
              for (int i=0;i<hands.length;i++){
                  //System.out.println(""pqsize:""+pq.size());
                  pq.insert(hands[i]);
                   
                  //insertcard=hands[i].getCards();
                  //Arrays.sort(insertcard);
                  
                  //for(int x=0;x<insertcard.length;x++){
                  //System.out.printf(""insert:(""+insertcard[x].getSuit()+"",""+insertcard[x].getFace()+"")"");                
                  //}//end of print insert card
                  //System.out.print(""*******"");
                  //System.out.print(""\n"");
                  //System.out.printf(""pqsize:""+pq.size());
                  //System.out.print(""\n"");
                  if(pq.size()>target){
                      //Card[] mincard=new Card[5];
                      //mincard = pq.min().getCards();
                      
                      //System.out.println(""刪除min hand:"");
                      //for(int j=0;j<mincard.length;j++){                 
                        //System.out.printf(mincard[j].getSuit()+""_""+mincard[j].getFace()+"","");           
                       //} 
                      
                        pq.delMin();//System.out.println(""delmin,size to""+pq.size()); 
                        //System.out.printf(""刪除後pqsize:""+pq.size());
                        //System.out.print(""\n"");
                        
                        
                    }//end of keep pq size =3                   
              }// end of insert hand
               //System.out.printf(""pqsize:""+pq.size());                          
              
             //Card[] mincard = new Card[5]; 
             /*
              for(int i=0;i<pq.size();i++){
                  System.out.println(pq.size()); 
                  mincard = pq.max().getCards();                                
                    for(int j =0;j<mincard.length;j++){
                        System.out.printf(""<<""+mincard[j].getSuit()+"",""+mincard[j].getFace()+"">>"");
                     }
                     System.out.print(""\n"");
                     pq.delMax();
                    if(pq.size()==2){
                      mincard = pq.max().getCards();                                
                         for(int j =0;j<mincard.length;j++){
                            System.out.printf(""<<""+mincard[j].getSuit()+"",""+mincard[j].getFace()+"">>"");
                        }
                    }                     
              }
              */
              
                //System.out.print(""\n"");            
              Card[] anscard = new Card[5];             
              anscard=pq.min().getCards();
              Arrays.sort(anscard);
              int x =anscard.length;
              for(int i=0;i<x-1;i++){                 
                  System.out.printf(anscard[i].getSuit()+""_""+anscard[i].getFace()+"","");           
              }
              System.out.printf(anscard[x-1].getSuit()+""_""+anscard[x-1].getFace()+""\n"");
 
        }
    }
}

