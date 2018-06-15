
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
       // System.out.println(thi);
    }
     
    // TODO 
    public int compareTo(Player that) {
        //對兩個傳進來做sorted
        Arrays.sort(this.cards);
        Arrays.sort(that.cards);
//        System.out.println(this.name);
//
//       for (int i =0;i<5;i++){
//           System.out.println(that.cards[i].getFace());
//        System.out.println(that.cards[i].getSuit());
//       }
        int SameThisCard = 0;
        int SameThatCard = 0;
        int SameThisID=-1;
        int SameThatID=-1;
        //Arrays.sort(this.cards);
        //只要用前3個卡，掃後三個，即可知有無full house
        for (int i = 0; i < 3; i++) {
                        SameThisCard = 0;
            SameThatCard = 0;
            for (int j = i + 1; j < 5; j++) {
                
                if (this.cards[i].getFace().equals(this.cards[j].getFace())) {
                    SameThisCard++;
                    //System.out.println(this.name);
                }
                if (that.cards[i].getFace().equals(that.cards[j].getFace())) {
                    SameThatCard++;
                }
            }
            if (SameThisCard == 2) {
                SameThisID = i;
            } else if (SameThatCard == 2) {
                SameThatID = i;
            }

        }
        if (SameThisID!=-1&&SameThatID!=-1) {
                return this.cards[SameThisID].compareTo(that.cards[SameThatID]);
        }else if(SameThisID!=-1){
            return 1;}
        else if(SameThatID!=-1){

            return -1;          
        }

        //flush   
        SameThisCard = 0;
        SameThatCard = 0;
        for (int i = 0; i < 4; i++) {
            if (this.cards[i].getSuit().equals(this.cards[i + 1].getSuit())) {
                SameThisCard++;
            }
            if (that.cards[i].getSuit().equals(that.cards[i + 1].getSuit())) {
                SameThatCard++;
            }
        }
//                    System.out.println(this.name);
        //System.out.println(SameThatCard);

        if (SameThisCard == 4 && SameThatCard == 4) {
            return this.cards[4].compareTo(that.cards[4]);
        } else if (SameThisCard == 4) {
            return 1;
        } else if (SameThatCard == 4) {
            return -1;
        }
        
    //Straight
        SameThisCard = 0;
        SameThatCard = 0;

        for (int i = 0; i < 4; i++) {
            if(i==3 && this.cards[i].getFace().equals(""5"") && this.cards[i+1].getFace().equals(""A""))
             {
                SameThisCard++;
            } else if (this.cards[i].getFace().equals(""10"") && this.cards[i + 1].getFace().equals(""J"")) {
                SameThisCard++;
            } else if (this.cards[i].getFace().equals(""J"") && this.cards[i + 1].getFace().equals(""Q"")) {
                SameThisCard++;
            } else if (this.cards[i].getFace().equals(""Q"") && this.cards[i + 1].getFace().equals(""K"")) {
                SameThisCard++;}
            else if(this.cards[i].getFace().equals(""K"") && this.cards[i + 1].getFace().equals(""A"")){
                SameThisCard++;
            } else if (!this.cards[i].getFace().matches(""A|J|Q|K"") && !this.cards[i + 1].getFace().matches(""A|J|Q|K"")) {
                if (Integer.parseInt(this.cards[i].getFace()) + 1 == Integer.parseInt(this.cards[i + 1].getFace())) {
                    SameThisCard++;
                }
            }
            if (i==3 && that.cards[i].getFace().equals(""5"") && that.cards[i+1].getFace().equals(""A"")) {
                SameThatCard++;
               
            } else if (that.cards[i].getFace().equals(""10"") && that.cards[i + 1].getFace().equals(""J"")) {
                SameThatCard++;
              
   
            } else if (that.cards[i].getFace().equals(""J"") && that.cards[i + 1].getFace().equals(""Q"")) {
                SameThatCard++;
              
            } else if (that.cards[i].getFace().equals(""Q"") && that.cards[i + 1].getFace().equals(""K"")) {
                SameThatCard++;
                
            } else if (that.cards[i].getFace().equals(""K"") && that.cards[i + 1].getFace().equals(""A"")) {
                SameThatCard++;
                
            } else if (!that.cards[i].getFace().matches(""A|J|Q|K"") && !that.cards[i + 1].getFace().matches(""A|J|Q|K"")) {
                if (Integer.parseInt(that.cards[i].getFace()) + 1 == Integer.parseInt(that.cards[i + 1].getFace())) {
                    SameThatCard++;
                    
                }
            }
        }
        if (SameThisCard == 4 || SameThatCard ==4) {
            
            if (SameThisCard == 4 && SameThatCard == 4) {
                if (this.cards[4].getFace().equals(""A"") && that.cards[4].getFace().equals(""A"") && that.cards[0].getFace().equals(""2"") && this.cards[0].getFace().equals(""2"")) {

                    return this.cards[4].compareTo(that.cards[4]);
                } else if (this.cards[4].getFace().equals(""A"") && this.cards[0].getFace().equals(""2"")) {
                    return -1;
                } else if (that.cards[4].getFace().equals(""A"") && that.cards[0].getFace().equals(""2"")) {
                          
                    return 1;
                } else if (this.cards[4].getFace().equals(""A"") && that.cards[4].getFace().equals(""A"") && that.cards[0].getFace().equals(""10"") && this.cards[0].getFace().equals(""10"")){
                    return this.cards[4].compareTo(that.cards[4]);
                }else if (this.cards[4].getFace().equals(""A"") && this.cards[0].getFace().equals(""10"")){
                          
                    return 1;
                }else if (that.cards[4].getFace().equals(""A"") && that.cards[0].getFace().equals(""10"")) {
                    return -1;               
                }else {
                    return this.cards[4].compareTo(that.cards[4]);
                }
                }
                else if (SameThisCard==4){
                      
                 return 1;}
             else if(SameThatCard==4){
                
                 return -1;} 
                }
            
             
                      
            
        
        //System.out.println(SameThisCard);
.
        //2-pair
        SameThisCard = 0;
        SameThatCard = 0;
        SameThisID=-1;
        SameThatID=-1;
        int PairThis=0;
        int PairThat=0;
        for (int i = 0; i < 4; i++) {
                        SameThisCard = 0;
            SameThatCard = 0;
            for (int j = i + 1; j < 5; j++) {
                
                if (this.cards[i].getFace().equals(this.cards[j].getFace())) {
                    SameThisCard++;
                    //System.out.println(this.name);
                }
                if (that.cards[i].getFace().equals(that.cards[j].getFace())) {
                    SameThatCard++;
                }
            }
            if (SameThisCard == 1) {
                PairThis++;
                SameThisID = i;
            }
            if (SameThatCard == 1) {
                PairThat++;
                SameThatID = i;
            }

        }
        if (PairThis==2 && PairThat==2) {
                return this.cards[SameThisID].compareTo(that.cards[SameThatID]);
        }else if(PairThis==2){
            return 1;
        }
        else if(PairThat==2)
            return -1;
               
        //1-Pair
        SameThisCard = 0;
        SameThatCard = 0;
        SameThisID=-1;
        SameThatID=-1;
        for (int i = 0; i < 4; i++) {
                        SameThisCard = 0;
            SameThatCard = 0;
            for (int j = i + 1; j < 5; j++) {
                
                if (this.cards[i].getFace().equals(this.cards[j].getFace())) {
                    SameThisCard++;
                    //System.out.println(this.name);
                }
                if (that.cards[i].getFace().equals(that.cards[j].getFace())) {
                    SameThatCard++;
                }
            }
            if (SameThisCard == 1) {
                SameThisID = i;
            }
            if (SameThatCard == 1) {
                SameThatID = i;
            }
        }
      
        if (SameThisID != -1 && SameThatID != -1) {
            //System.out.println(""Y"");
            return this.cards[SameThisID].compareTo(that.cards[SameThatID]);
        } else if (SameThisID != -1) {
            return 1;
        } else if (SameThatID != -1) {
            return -1;
        }

        
        
        //High Card
        return this.cards[4].compareTo(that.cards[4]);
        
    }

}



