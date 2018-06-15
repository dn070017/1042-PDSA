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
                  for(int x =0;x<checkhand.length;x++){
                    //System.out.printf(""[""+checkhand[x].getSuit()+"",""+checkhand[x].getFace()+""]"");
                }
                   //System.out.print(""\n"");
                }
              //print hands^
              //insert hand into pq
              
              Card[] insertcard = new Card[5];
              
              for (int i=0;i<hands.length;i++){
                  pq.insert(hands[i]);
                  if(pq.size()>target)
                        pq.delMin();/*
                  insertcard=hands[i].getCards();
                  Arrays.sort(insertcard);
                  for(int x=0;x<target;x++){
                  System.out.printf(""insert:(""+checkcard[x].getSuit()+"",""+checkcard[x].getFace()+"")"");                
                  }
                  System.out.print(""*******"");
                  System.out.print(""\n"");  */                
              }
                                         
              
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
              
                          
              Card[] anscard = new Card[5];             
              anscard=pq.min().getCards();
              Arrays.sort(anscard);
              for(int i=0;i<anscard.length;i++){                 
                  System.out.printf(anscard[i].getSuit()+""_""+anscard[i].getFace()+"","");
              }
              
              
          
              
              
                
              
              
              
                    
               
                
                
              
              
                
                
//            MinPQ pq = new MinPQ(playerArray);
            //////讀取input,把hand排序好,存入pq,del min(target),output the target////// 
          
           
            
             
            
        }
    }
}

