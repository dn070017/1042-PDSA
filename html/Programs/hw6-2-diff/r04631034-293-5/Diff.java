
import java.util.*;
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
.

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
              
              MergeX.sort(this.cards,Card.SUIT_ORDER);    
              for(int i = 0 ; i < this.cards.length-1; i++){
                   for(int j = i+1 ; j < this.cards.length;j++){
                        if(map.get(this.cards[i].getFace()) > map.get(this.cards[j].getFace()) ) continue;
                        else{
                             String temp_Face=this.cards[j].getFace();
                             String temp_Suit=this.cards[j].getSuit();
                             this.cards[j] = new Card(this.cards[i].getFace(),this.cards[i].getSuit());
                             this.cards[i]= new Card(temp_Face , temp_Suit); 
                        }
                   }
              }
              
              MergeX.sort(that.cards,Card.SUIT_ORDER);    
              for(int i = 0 ; i < that.cards.length-1; i++){
                   for(int j = i+1 ; j < that.cards.length;j++){
                        if(map.get(that.cards[i].getFace()) > map.get(that.cards[j].getFace()) ) continue;
                        else{
                             String temp_Face=that.cards[j].getFace();
                             String temp_Suit=that.cards[j].getSuit();
                             that.cards[j] = new Card(that.cards[i].getFace(),that.cards[i].getSuit());
                             that.cards[i]= new Card(temp_Face , temp_Suit); 
                        }
                   }
              }
              
              
              /// this card
              
              
              int[][] output=new int[2][3];
              
               Set<String> Set_this = new HashSet<String>();
               for(int i = 0 ; i < this.cards.length; i ++){
                    Set_this.add(this.cards[i].getFace());
               }
               if(Set_this.size() == 2){           //  fullhouse:5
                    output[0][0]=5;
                    int count=0;
                    for(int i = 0 ; i < this.cards.length-1; i++){
                         if(map.get(this.cards[i].getFace()) ==map.get(this.cards[i+1].getFace())){
                              count++;
                              if(count==2){
                                   output[0][1]=map.get(this.cards[i-1].getFace());
                                   output[0][2]=map.get(this.cards[i-1].getSuit());
                              }
                              else count=0;
                         }
                    }
               }
               else if(Set_this.size()==3){       /// two pair :2
                    output[0][0]=2;
                    for(int i = 0 ; i < this.cards.length-1; i++){
                        if(map.get(this.cards[i].getFace()) ==map.get(this.cards[i+1].getFace())){
                                   output[0][1]=map.get(this.cards[i].getFace());
                                   output[0][2]=map.get(this.cards[i].getSuit());
                                   break;
                        }
                    }
               }
               else if(Set_this.size()==4){       /// one pair :1
                    output[0][0]=1;
                    for(int i = 0 ; i < this.cards.length-1; i++){
                        if(map.get(this.cards[i].getFace()) ==map.get(this.cards[i+1].getFace())){
                                   output[0][1]=map.get(this.cards[i].getFace());
                                   output[0][2]=map.get(this.cards[i].getSuit());
                                   break;
                        }
                    }
               }
               else if(Set_this.size() == 5){
                    int count_flush=0;
                    int count_straight=0;
                    int count_last4 = 0;
                    for(int i = 0 ; i < this.cards.length-1; i++){
                         if(map.get(this.cards[i].getFace()) - 1 ==map.get(this.cards[i+1].getFace())){
                              count_straight++;
                              if(count_straight==4){                            // straight
                                   output[0][1]=map.get(this.cards[0].getFace());
                                   output[0][2]=map.get(this.cards[0].getSuit());
                                   output[0][0]=3;
                              }
                         }
                         if(map.get(this.cards[i].getSuit()) ==map.get(this.cards[i+1].getSuit())){
                              count_last4++;
                              if(count_last4==3 & map.get(this.cards[0].getFace())==14 ){                            // fullhouse
                                   output[0][1]=map.get(this.cards[0].getFace());
                                   output[0][2]=map.get(this.cards[0].getSuit());
                                   output[0][0]=3;
                              }
                         }
                         if(map.get(this.cards[i].getSuit()) ==map.get(this.cards[i+1].getSuit())){
                              count_flush++;
                              if(count_flush==4){                            // fullhouse
                                   output[0][1]=map.get(this.cards[0].getFace());
                                   output[0][2]=map.get(this.cards[i].getSuit());
                                   output[0][0]=4;
                              }
                         }
                    }
                    if(output[0][0]==0){           //  one card :0
                         output[0][1]=map.get(this.cards[0].getFace());
                         output[0][2]=map.get(this.cards[0].getSuit());
                    }
               }
               
               
               ///// that card
               
               Set<String> Set_that = new HashSet<String>();
               for(int i = 0 ; i < that.cards.length; i ++){
                    Set_that.add(that.cards[i].getFace());
               }
               if(Set_that.size() == 2){           //  fullhouse:5
                    output[1][0]=5;
                    int count=0;
                    for(int i = 0 ; i < that.cards.length-1; i++){
                         if(map.get(that.cards[i].getFace()) ==map.get(that.cards[i+1].getFace())){
                              count++;
                              if(count==2){
                                   output[1][1]=map.get(that.cards[i-1].getFace());
                                   output[1][2]=map.get(that.cards[i-1].getSuit());
                              }
                              else count=0;
                         }
                    }
               }
               else if(Set_that.size()==3){       /// two pair :2
                    output[1][0]=2;
                    for(int i = 0 ; i < that.cards.length-1; i++){
                        if(map.get(that.cards[i].getFace()) ==map.get(that.cards[i+1].getFace())){
                                   output[1][1]=map.get(that.cards[i].getFace());
                                   output[1][2]=map.get(that.cards[i].getSuit());
                                   break;
                        }
                    }
               }
               else if(Set_that.size()==4){       /// one pair :1
                    output[0][0]=1;
                    for(int i = 0 ; i < that.cards.length-1; i++){
                        if(map.get(this.cards[i].getFace()) ==map.get(that.cards[i+1].getFace())){
                                   output[0][1]=map.get(that.cards[i].getFace());
                                   output[0][2]=map.get(that.cards[i].getSuit());
                                   break;
                        }
                    }
               }
               else if(Set_that.size() == 5){
                    int count_flush=0;
                    int count_straight=0;
                    int count_last4 = 0;
                    for(int i = 0 ; i < that.cards.length-1; i++){
                         if(map.get(that.cards[i].getFace()) - 1 ==map.get(that.cards[i+1].getFace())){
                              count_straight++;
                              if(count_straight==4){                            // straight
                                   output[1][1]=map.get(that.cards[0].getFace());
                                   output[1][2]=map.get(that.cards[0].getSuit());
                                   output[1][0]=3;
                              }
                         }
                         if(map.get(this.cards[i].getSuit()) ==map.get(this.cards[i+1].getSuit())){
                              count_last4++;
                              if(count_last4==3 & map.get(this.cards[0].getFace())==14 ){                            // fullhouse
                                   output[1][1]=map.get(this.cards[0].getFace());
                                   output[1][2]=map.get(this.cards[0].getSuit());
                                   output[1][0]=3;
                              }
                         }
                         if(map.get(that.cards[i].getSuit()) ==map.get(that.cards[i+1].getSuit())){
                              count_flush++;
                              if(count_flush==4){                            // fullhouse
                                   output[1][1]=map.get(that.cards[0].getFace());
                                   output[1][2]=map.get(that.cards[i].getSuit());
                                   output[1][0]=4;
                              }
                         }
                    }
                    if(output[1][0]==1){           //  one card :0
                         output[1][1]=map.get(that.cards[0].getFace());
                         output[1][2]=map.get(that.cards[0].getSuit());
                    }
               }
               if(output[0][0] > output[1][0]) return +1;
               if(output[0][0] < output[1][0]) return -1;
               if(output[0][1] == 4 & output[1][1] == 4){
                    if(output[0][2] > output[1][2]) return +1;
                    if(output[0][2] < output[1][2]) return -1;
               }
               if(output[0][1] > output[1][1]) return +1;
               if(output[0][1] < output[1][1]) return -1;
               if(output[0][2] > output[1][2]) return +1;
               if(output[0][2] < output[1][2]) return -1;
               return 0;
    }
}

