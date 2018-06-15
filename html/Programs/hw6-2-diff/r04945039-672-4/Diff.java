import java.util.Arrays;
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
        Arrays.sort(this.cards);
        Arrays.sort(that.cards);
        if(pokertype(this.cards)>pokertype(that.cards)) return +1;
        if(pokertype(this.cards)<pokertype(that.cards)) return -1;
        else special(this.cards,that.cards);
        return 0;
    }
    
    private int special(Card[] a, Card[] b){
//        int which = pokertype(a);
//        int Biga;
//        int BigB;
//        switch(which){
//            case 3:
//                
//            case 2:
//        }
        int ax=0;
        int bx=0;
        int[] temp1 = recordface(a);
        for (int ind=0;ind<14;ind++){
            if (temp1[ind]==2) ax=ind;
        }
        int[] temp2 = recordface(b);
        for (int ind=0;ind<14;ind++){
            if (temp2[ind]==2) bx=ind;
        }
        
        if (ax>bx) return +1;
        if (ax<bx) return -1;
        if (ax==bx) return-1;
        
        
        
        
        return 0;
    }
    // to read the pokertype in each player
    private int pokertype(Card[] a) {
        int flag=0;
        if(twopair(a)!=-1) flag=twopair(a);
        if(onepair(a)!=-1) flag=onepair(a);
        if(straight(a)!=-1) flag=straight(a);
        if(flush(a)!=-1) flag=flush(a);
        if(fullhouse(a)!=-1) flag=fullhouse(a);
        if(highcard(a)!=-1) flag=highcard(a);
        return flag;
    }
    
    private int highcard(Card[] c){
        int no=0;
        if(fullhouse(c)==-1) no=no+1;
        if(flush(c)==-1) no=no+1;
        if(straight(c)==-1) no=no+1;
        if(twopair(c)==-1) no=no+1;
        if(onepair(c)==-1) no=no+1;
        if(no==5) return 1;
        return -1;
    }
    
    private int onepair(Card[] c){
        int[] temp = recordface(c);
        int pair=0;
        int single=0;
        for (int each : temp){
            if (each==2) pair=pair+1;
            if (each==1) single=single+1;
        }
        if (pair==1 && single==3) return 2;
        return -1;
    }
    
    private int twopair(Card[] c){
        int[] temp = recordface(c);
        int sum=0;
        for (int each : temp){
            if (each==2) sum=sum+1;
        }
        if (sum==2) return 3;
        return -1;
    }
    
    private int straight(Card[] c){
        int[] face = new int[5];
        for(int scan=0;scan<c.length;scan++){
            face[scan]=faceind(c[scan].getFace());
        }
        int sum=0;
        for (int r=0;r<4;r++){
            if (face[r+1]-face[r]==1) sum=sum+1;
        }
        if (sum>=4) return 4;
        return -1;
    }
    
    private int flush(Card[] c){
        int[] temp = recordsuit(c);
        for (int each : temp){
            if (each==5) return 5;
        }
        return -1;
    }
    
    private int fullhouse(Card[] c){
        int[] temp = recordface(c);
        for (int each : temp){
            if (each==3) return 6;
        }
        return -1;
    }
    
    private int[] recordface(Card[] a){
        int[] recording= new int[14];
        
        for(int i=0;i<5;i++){
            recording[faceind(a[i].getFace())-1]=recording[faceind(a[i].getFace())-1]+1;      
        }
        return recording;
    }
    
    private int[] recordsuit(Card[] a){       
        int[] suitrecord=new int[4];
                
        for(int i=0;i<5;i++){
            suitrecord[suitind(a[i].getSuit())-1]=suitrecord[suitind(a[i].getSuit())-1]+1;
        }
        return suitrecord;
    }
    
    
    
    
    private int suitind(String a){
        if (a.equals(""Spades"")) return 4;
        if (a.equals(""Hearts"")) return 3;
        if (a.equals(""Diamonds"")) return 2;
        if (a.equals(""Clubs"")) return 1;
        return 0;
        
    }
    
    public int faceind(String a){
        if (a.equals(""A"")) return 14;
        if (a.equals(""2"")) return 2;
        if (a.equals(""3"")) return 3;
        if (a.equals(""4"")) return 4;
        if (a.equals(""5"")) return 5;
        if (a.equals(""6"")) return 6;
        if (a.equals(""7"")) return 7;
        if (a.equals(""8"")) return 8;
        if (a.equals(""9"")) return 9;
        if (a.equals(""10"")) return 10;
        if (a.equals(""J"")) return 11;
        if (a.equals(""Q"")) return 12;
        if (a.equals(""K"")) return 13;
        return 0;
    }



}




