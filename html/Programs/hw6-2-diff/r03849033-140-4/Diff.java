
import java.util.Arrays;

public class Player implements Comparable<Player>{ 

    private Card[] cards = new Card[5];
    private String name;
    private Card thismax;
    private Card thatmax;     
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
    Arrays.sort(this.cards);
    Arrays.sort(that.cards);    
    int thisvalue = 0;
    int thatvalue = 0;
   
    int[] thispair = new int[4];
    int[] thatpair = new int[4];
    int this1 = 0;
    int that1 = 0;    
    int[] thisstraight = new int[4];
    int[] thatstraight = new int[4]; 
    int thisst = 0;
    int thatst = 0;   
    int[] thisflush  = new int[4];
    int[] thatflush  = new int[4];    

    for(int j = 0; j < 4; j++) {
        if(this.cards[j].getFace().equals(this.cards[j+1].getFace())) {thispair[j]=1; this1=j; }
        if(that.cards[j].getFace().equals(that.cards[j+1].getFace())) {thatpair[j]=1; that1=j;}        
    } 
    String[] ranks  = { ""2"", ""3"", ""4"", ""5"", ""6"", ""7"", ""8"", ""9"", ""10"", ""J"", ""Q"", ""K"",""A"" };    
    for(int i = 0; i < 13; i++) {
    for(int j = 0; j < 4; j++) {    
        if(this.cards[j].getFace().equals(ranks[i])) {thisstraight[j]=i;
        if(j>0){ if(thisstraight[j]==thisstraight[j-1]){thisst++;} }  }
        if(that.cards[j].getFace().equals(ranks[i])) {thatstraight[j]=i; 
        if(j>0){ if(thatstraight[j]==thatstraight[j-1]){thatst++;} }  }
    }}        
    for(int j = 0; j < 4; j++) {
        if(this.cards[j].getSuit().equals(this.cards[j+1].getSuit())) {thisflush[j]=1; }
        if(that.cards[j].getSuit().equals(that.cards[j+1].getSuit())) {thatflush[j]=1; }        
    }
    int[] case1 = {1,1,0,1};
    int[] case2 = {1,0,1,1};
    int[] case3 = {1,1,1,1}; 
    int[] case4 = {1,0,1,0}; 
    int[] case5 = {1,0,0,1};    
    int[] case6 = {0,1,0,1};  
    int[] case7 = {1,1,1,0};  
    int[] case8 = {0,1,1,1};      
    int[] case9 = {0,0,0,0};     
    if(Arrays.equals(thispair,case1)){ 
        thisvalue=5;  thismax = this.cards[2]; }
    else  if(Arrays.equals(thispair,case2)){ 
        thisvalue=5;   thismax = this.cards[4]; }
    else  if(Arrays.equals(thisflush,case3)){ 
        thisvalue=4;   thismax = this.cards[4]; }
    else  if(thisst==5){ 
        thisvalue=3;   thismax = this.cards[4]; }    
    else  if(Arrays.equals(thispair,case4) || Arrays.equals(thispair,case7)){ 
        thisvalue=2;   thismax = this.cards[3]; }    
    else  if(Arrays.equals(thispair,case5) || Arrays.equals(thispair,case6) || Arrays.equals(thispair,case8)){ 
        thisvalue=2;   thismax = this.cards[4]; } 
    else if (!Arrays.equals(thispair,case7)){
        thisvalue=1;   thismax = this.cards[this1];}
    else {  thismax = this.cards[4];}
    
    if(Arrays.equals(thatpair,case1)){ 
        thatvalue=5;   thatmax = that.cards[2]; }
    else  if(Arrays.equals(thatpair,case2)){ 
        thatvalue=5;   thatmax = that.cards[4]; }
    else  if(Arrays.equals(thatflush,case3)){ 
        thatvalue=4;   thatmax = that.cards[4]; }
    else  if(thatst==5){ 
        thatvalue=3;   thatmax = that.cards[4]; }    
    else  if(Arrays.equals(thatpair,case4) || Arrays.equals(thatpair,case7)){ 
        thatvalue=2;   thatmax = that.cards[3]; }    
    else  if(Arrays.equals(thatpair,case5) || Arrays.equals(thatpair,case6) || Arrays.equals(thatpair,case8)){ 
        thatvalue=2;   thatmax = that.cards[4]; } 
    else if (!Arrays.equals(thatpair,case9)){
        thatvalue=1;   thatmax = that.cards[that1];}
    else {  thatmax = that.cards[4];}    
   
    if(thisvalue>thatvalue){ return 1;}
    else if(thisvalue<thatvalue){ return -1;}
    
    else if(thismax.compareTo(thatmax)>0){return 1;}
    else if(thismax.compareTo(thatmax)<0){return -1;}    
.
        return 0;
    }
}

