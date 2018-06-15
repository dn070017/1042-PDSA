import java.util.Arrays;


public class Player implements Comparable<Player>{ 

    private Card[] cards = new Card[5];
    private String name;
     
    // DO NOT MODIFY THIS
    public Player(String name) {
        this.name = name;
    }
     
    // DO NOT MODIFY THIS
    public String getName() {
        return this.name;
     }
     
    // DO NOT MODIFY THIS
    public void setCards(Card[] cards) {
        this.cards = cards;
    }
     
    // TODO 
    public int compareTo(Player that) {
        Insertion.sort(this.cards);
        Insertion.sort(that.cards);
        int thiscardvalue,thatcardvalue,value=0;
        int thisvalue[]=new int[5];
        int thatvalue[]=new int[5];

//        System.out.println(this.cards[0].getSuit());
        
        for(int i=0;i<5;i++){
                if(this.cards[i].getFace().equals(""A"")){thisvalue[i] = 14;} //get that card value
                else if(this.cards[i].getFace().equals(""J"")){thisvalue[i]= 11;}
                else if(this.cards[i].getFace().equals(""Q"")){thisvalue[i]= 12;}          
                else if(this.cards[i].getFace().equals(""K"")){thisvalue[i]= 13;}
                else{thisvalue[i]=Integer.parseInt(this.cards[i].getFace());}
                
                if(that.cards[i].getFace().equals(""A"")){thatvalue[i] = 14;} //get that card value
                else if(that.cards[i].getFace().equals(""J"")){thatvalue[i]= 11;}
                else if(that.cards[i].getFace().equals(""Q"")){thatvalue[i]= 12;}          
                else if(that.cards[i].getFace().equals(""K"")){thatvalue[i]= 13;}
                else{thatvalue[i]=Integer.parseInt(that.cards[i].getFace());}
            }
            if( (thisvalue[4]==thisvalue[3] && thisvalue[4]==thisvalue[2] && thisvalue[1]==thisvalue[0]) ||
               ( thisvalue[0]==thisvalue[1] && thisvalue[0]==thisvalue[2] && thisvalue[3]==thisvalue[4] ) ){thiscardvalue=6;}
            else if( this.cards[0].getSuit().equals(this.cards[1].getSuit()) && 
                     this.cards[0].getSuit().equals(this.cards[2].getSuit()) && 
                     this.cards[0].getSuit().equals(this.cards[3].getSuit()) && 
                     this.cards[0].getSuit().equals(this.cards[4].getSuit())){thiscardvalue=5;}
            else if( thisvalue[0]==2 &&
                     thisvalue[1]==3 &&
                     thisvalue[2]==4 &&
                     thisvalue[3]==5 &&
                     thisvalue[4]==14){thisvalue[0]=1;thisvalue[1]=2;thisvalue[2]=3;thisvalue[3]=4;thisvalue[4]=5;thiscardvalue=4;}
            else if( thisvalue[0]==thisvalue[1]-1 &&
                     thisvalue[0]==thisvalue[2]-2 &&
                     thisvalue[0]==thisvalue[3]-3 &&
                     thisvalue[0]==thisvalue[4]-4 ){thiscardvalue=4;}
            else if( thisvalue[0]==thisvalue[1]  &&  thisvalue[2]==thisvalue[3] ||
                     thisvalue[0]==thisvalue[1]  &&  thisvalue[3]==thisvalue[4] ||
                     thisvalue[1]==thisvalue[2]  &&  thisvalue[3]==thisvalue[4]){thiscardvalue=3;}
            else if( thisvalue[0]==thisvalue[1] || 
                     thisvalue[1]==thisvalue[2] || 
                     thisvalue[2]==thisvalue[3] || 
                     thisvalue[3]==thisvalue[4]){thiscardvalue=2;}
            else{thiscardvalue=1;}
            
            if( (thatvalue[4]==thatvalue[3] && thatvalue[4]==thatvalue[2] && thatvalue[1]==thatvalue[0]) ||
               ( thatvalue[0]==thatvalue[1] && thatvalue[0]==thatvalue[2] && thatvalue[3]==thatvalue[4] ) ){thatcardvalue=6;}
            else if( that.cards[0].getSuit().equals(that.cards[1].getSuit()) && 
                     that.cards[0].getSuit().equals(that.cards[2].getSuit()) && 
                     that.cards[0].getSuit().equals(that.cards[3].getSuit()) && 
                     that.cards[0].getSuit().equals(that.cards[4].getSuit())){thatcardvalue=5;}
            else if( thatvalue[0]==2 &&
                     thatvalue[1]==3 &&
                     thatvalue[2]==4 &&
                     thatvalue[3]==5 &&
                     thatvalue[4]==14){thatvalue[0]=1;thatvalue[1]=2;thatvalue[2]=3;thatvalue[3]=4;thatvalue[4]=5;thatcardvalue=4;}
            else if( thatvalue[0]==thatvalue[1]-1 &&
                     thatvalue[0]==thatvalue[2]-2 &&
                     thatvalue[0]==thatvalue[3]-3 &&
                     thatvalue[0]==thatvalue[4]-4 ){thatcardvalue=4;}
            else if( thatvalue[0]==thatvalue[1]  &&  thatvalue[2]==thatvalue[3] ||
                     thatvalue[0]==thatvalue[1]  &&  thatvalue[3]==thatvalue[4] ||
                     thatvalue[1]==thatvalue[2]  &&  thatvalue[3]==thatvalue[4]){thatcardvalue=3;}
            else if( thatvalue[0]==thatvalue[1] || 
                     thatvalue[1]==thatvalue[2] || 
                     thatvalue[2]==thatvalue[3] || 
                     thatvalue[3]==thatvalue[4]){thatcardvalue=2;}
            else{thatcardvalue=1;}
            
//            if(thiscardvalue==6){System.out.println(thisvalue[0]+""\t""+thisvalue[1]+""\t""+thisvalue[2]+""\t""+thisvalue[3]+""\t""+thisvalue[4]);}
//            if(thatcardvalue==6){System.out.println(thatvalue[0]+""\t""+thatvalue[1]+""\t""+thatvalue[2]+""\t""+thatvalue[3]+""\t""+thatvalue[4]);}        
//           System.out.println(thatcardvalue);
//           System.out.println(thiscardvalue+""\t""+thatcardvalue);
           
           if(thiscardvalue>thatcardvalue){return 1;}
           else if(thiscardvalue<thatcardvalue){return -1;}
           else if(thiscardvalue==6 && thatcardvalue==6){
                if((thisvalue[0]==thisvalue[1] && thisvalue[0]==thisvalue[2]) &&
                   (thatvalue[0]==thatvalue[1] && thatvalue[0]==thatvalue[2]) ){if(thisvalue[0]>thatvalue[0]){value= 1;}
                                                                                else if(thisvalue[0]<thatvalue[0]){value= -1;}
                                                                                else if(thisvalue[0]==thatvalue[0]){
                                                                                    if(Card.SUIT_ORDER.compare(this.cards[0],that.cards[0])>0){value= 1;}
                                                                                    else if(Card.SUIT_ORDER.compare(this.cards[0],that.cards[0])<0){value= -1;}
                                                                                    else if(thisvalue[4]>thatvalue[4]){value= 1;}
                                                                                    else if(thisvalue[4]<thatvalue[4]){value= -1;}
                                                                                    else if(thisvalue[4]==thatvalue[4]){
                                                                                      if(Card.SUIT_ORDER.compare(this.cards[4],that.cards[4])>0){value= 1;}
                                                                                      else if(Card.SUIT_ORDER.compare(this.cards[4],that.cards[4])<0){value= -1;}
                                                                                        }
                                                                                    }
                                                                                }
                else if((thisvalue[4]==thisvalue[3] && thisvalue[4]==thisvalue[2]) &&
                        (thatvalue[4]==thatvalue[3] && thatvalue[4]==thatvalue[2]) ){if(thisvalue[4]>thatvalue[4]){value= 1;}
                                                                                else if(thisvalue[4]<thatvalue[4]){value= -1;}
                                                                                else if(thisvalue[4]==thatvalue[4]){
                                                                                    if(Card.SUIT_ORDER.compare(this.cards[4],that.cards[4])>0){value= 1;}
                                                                                    else if(Card.SUIT_ORDER.compare(this.cards[4],that.cards[4])<0){value= -1;}
                                                                                    else if(thisvalue[0]>thatvalue[0]){value= 1;}
                                                                                    else if(thisvalue[0]<thatvalue[0]){value= -1;}
                                                                                    else if(thisvalue[0]==thatvalue[0]){
                                                                                      if(Card.SUIT_ORDER.compare(this.cards[0],that.cards[0])>0){value= 1;}
                                                                                      else if(Card.SUIT_ORDER.compare(this.cards[0],that.cards[0])<0){value= -1;}
                                                                                            }
                                                                                    }
                                                                                }
                else if(thisvalue[2]>thatvalue[2]){value= 1;}
                else if(thisvalue[2]<thatvalue[2]){value= -1;}
           }
           else if(thiscardvalue==5 && thatcardvalue==5){
                for(int i=4;i>=0;i--){
                    if(thisvalue[i]>thatvalue[i]){value= 1;break;}
                    else if(thisvalue[i]<thatvalue[i]){value= -1;break;}
                     }
            }
           else if(thiscardvalue==4 && thatcardvalue==4){

                    for(int i=4;i>=0;i--){
                    if(thisvalue[i]>thatvalue[i]){value= 1;break;}
                    else if(thisvalue[i]<thatvalue[i]){value= -1;break;}
                    else if(thisvalue[i]==thatvalue[i]){
                        if(Card.SUIT_ORDER.compare(this.cards[i],that.cards[i])>0){value= 1;break;}
                        else if(Card.SUIT_ORDER.compare(this.cards[i],that.cards[i])<0){value= -1;break;}
                         }
                    }
           }
          else if(thiscardvalue==3 && thatcardvalue==3){
                    if( thisvalue[4]==thisvalue[3] && thatvalue[4]==thatvalue[3] ){
                        if(thisvalue[4]>thatvalue[4]){value= 1;}
                        else if(thisvalue[4]<thatvalue[4]){value= -1;}
                        else if(Card.SUIT_ORDER.compare(this.cards[4],that.cards[4])>0){value= 1;}
                        else if(Card.SUIT_ORDER.compare(this.cards[4],that.cards[4])<0){value= -1;}
                                }
                    else if( thisvalue[3]==thisvalue[2] && thatvalue[3]==thatvalue[2] ){
                        if(thisvalue[3]>thatvalue[3]){value= 1;}
                        else if(thisvalue[3]<thatvalue[3]){value= -1;}                        
                        else if(Card.SUIT_ORDER.compare(this.cards[3],that.cards[3])>0){value= 1;}
                        else if(Card.SUIT_ORDER.compare(this.cards[3],that.cards[3])<0){value= -1;}
                                }
                     else if(thisvalue[3]>thatvalue[3]){value= 1;}
                     else if(thisvalue[3]<thatvalue[3]){value=-1;}
                      }
//          else if(thiscardvalue==3 && thatcardvalue==3){
//                    if( thisvalue[4]==thisvalue[3] && thatvalue[4]==thatvalue[3] ){
//                        if(thisvalue[4]>thatvalue[4]){value= 1;}
//                        else if(thisvalue[4]<thatvalue[4]){value= -1;}
//                        else if(Card.SUIT_ORDER.compare(this.cards[4],that.cards[4])>0){value= 1;}
//                        else if(Card.SUIT_ORDER.compare(this.cards[4],that.cards[4])<0){value= -1;}
//                                }
//                    else if( thisvalue[3]==thisvalue[2] && thatvalue[3]==thatvalue[2] ){
//                        if(thisvalue[3]>thatvalue[3]){value= 1;}
//                        else if(thisvalue[3]<thatvalue[3]){value= -1;}
//                        else if(Card.SUIT_ORDER.compare(this.cards[3],that.cards[3])>0){value= 1;}
//                        else if(Card.SUIT_ORDER.compare(this.cards[3],that.cards[3])<0){value= -1;}
//                                }
//                     else if(thisvalue[3]>thatvalue[3]){value= 1;}
//                     else if(thisvalue[3]<thatvalue[3]){value=-1;}
//                      }
           else if(thiscardvalue==2 && thatcardvalue==2){
               int vi=0,va=0;
                  for( vi=4;vi>=1;vi--){
                      if(thisvalue[vi]==thisvalue[vi-1]){break;}
                  }
                  for( va=4;va>=1;va--){
                      if(thisvalue[va]==thisvalue[va-1]){break;}
                  }
                  if(vi>va){value= 1;}
                  else if(vi>va){value= -1;}
                  else if(vi==va){
                        if(Card.SUIT_ORDER.compare(this.cards[vi],that.cards[va])>0){value= 1;}
                        else if(Card.SUIT_ORDER.compare(this.cards[vi],that.cards[va])<0){value= -1;}
                  }
           }
           else if(thiscardvalue==1 && thatcardvalue==1){
               if(thisvalue[4]>thatvalue[4]){value= 1;}
               else if(thisvalue[4]<thatvalue[4]){value= -1;}
               else if(thisvalue[4]==thatvalue[4]){
                        if(Card.SUIT_ORDER.compare(this.cards[4],that.cards[4])>0){value= 1;}
                        else if(Card.SUIT_ORDER.compare(this.cards[4],that.cards[4])<0){value= -1;}
               }
                   }
           
           return value;
.
    }

}
