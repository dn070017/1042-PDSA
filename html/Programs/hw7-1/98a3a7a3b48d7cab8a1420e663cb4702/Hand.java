
public class Hand implements Comparable<Hand> {

    // sorted by Card value are recommended but not necessary
    private Card[] cards; 
    private int[] num = new int[5];
    private int[] color = new int[5];
    private String[] copy = {""0"",""1"",""2"",""3"",""4"",""5"",""6"",""7"",""8"",""9"",""10"", ""J"", ""Q"", ""K"", ""A""};
    private String[] Suit = {""Clubs"", ""Diamonds"", ""Hearts"", ""Spades""};
    // TODO, Judge System will call this constructor once for each hand
    public Hand(Card[] cards){
        this.cards = cards;        
    }

    // TODO
    public int compareTo(Hand that) {
        int a,b;
        Card[] A,B;
        A = this.arr(this.cards);
        B = that.arr(that.cards);
        
        this.findnum(A);
        that.findnum(B);
        
        a =this.findtype(this.num, this.color);
        b =that.findtype(that.num, that.color);
        
        if(a>b) return 1;
        else if(a<b) return -1;
        else{
            if(a==5){
                int aa,bb;
                aa = this.confullhouse(this.num);
                bb = that.confullhouse(that.num);
                if(this.num[aa]>that.num[bb]) return 1;
                else if(this.num[aa]<that.num[bb]) return -1;
                else{
                    if(this.color[aa]>that.color[bb]) return 1;
                    else if(this.color[aa]<that.color[bb])return -1;
                }
            }
            if(a==4 || a==3 ||a==2 ||a==-1){               
                if(this.num[4]>that.num[4]) return 1;
                else if(this.num[4]<that.num[4]) return -1;
                else{
                    if(this.color[4]>that.color[4]) return 1;
                    else if(this.color[4]<that.color[4])return -1;
                }
            }
            if(a==1){
                 int aa,bb;
                aa = this.contwopair(this.num);
                bb = that.contwopair(that.num);
                if(this.num[aa]>that.num[bb]) return 1;
                else if(this.num[aa]<that.num[bb]) return -1;
                else{
                    if(this.color[aa]>that.color[bb]) return 1;
                     else if(this.color[aa]<that.color[bb])return -1;
                }
            }
            if(a==0){
                 int aa,bb;
                aa = this.conpair(this.num);
                bb = that.conpair(that.num);
                if(this.num[aa]>that.num[bb]) return 1;
                else if(this.num[aa]<that.num[bb]) return -1;
                else{
                    if(this.color[aa]>that.color[bb]) return 1;
                     else if(this.color[aa]<that.color[bb])return -1;
                }
            }
        }
        return 0;
    }

      // Do not modified this function
    public Card[] getCards() { return this.cards; }

    public Card[] arr(Card[] cards){
       for(int i = 1;i<5;i++){
         for(int j = i;j>0;j--){
             if(cards[j].compareTo(cards[j-1])==1){
                 Card temp = cards[j-1];
                    cards[j-1] = cards[j];
                    cards[j] = temp;
             }
             else break;
         }
                    
       }
       return cards;
 }
    public void findnum(Card[] cards){
        for(int i = 0;i<5;i++){
            for(int j = 0;j<16;j++){
                if(cards[i].getFace().equals(copy[j])){
                    num[i] = j;
                    break;
                }
            }
            for(int k=0;k<4;k++){
                if(cards[i].getSuit().equals(Suit[k])){
                    color[i] = k;
                    break;
                }
            }
        }
        
    }
    public int findtype(int[] fa, int[] su){
        
         if(fa[0] ==fa[1] && fa[0] == fa[2] && fa[3] == fa[4])
            return 5;
        else if(fa[0] ==fa[1] && fa[2] == fa[3] && fa[2] == fa[4])
            return 5;
        else if(su[0] == su[1] && su[0] == su[2] && su[0] == su[3] && su[0] == su[4])
            return 4;
        else if(fa[0]+1==fa[1]&&fa[0]+2==fa[2]&&fa[0]+3==fa[3]
                &&fa[0]+4==fa[4])
            return 3;
        else if(fa[4]==14&&fa[3]==13&&fa[2]==12&&fa[1]==11&&fa[0]==2)
            return 3;
         else if(fa[4]==14&&fa[3]==13&&fa[2]==12&&fa[1]==3&&fa[0]==2)
            return 3;
         else if(fa[4]==14&&fa[3]==13&&fa[2]==4&&fa[1]==3&&fa[0]==2)
            return 3;
         else if(fa[4]==14&&fa[3]==5&&fa[2]==4&&fa[1]==3&&fa[0]==2)
            return 2;
         else if(fa[0]==fa[1]&&fa[2]==fa[3])
             return 1;
         else if(fa[0]==fa[1]&&fa[3]==fa[4])
             return 1;
         else if(fa[1]==fa[2]&&fa[3]==fa[4])
             return 1;
         else if(fa[0]==fa[1])
             return 0;
         else if(fa[1]==fa[2])
             return 0;
         else if(fa[2]==fa[3])
             return 0;
         else if(fa[3]==fa[4])
             return 0;
        else
             return -1;
}
    public int confullhouse(int[] fa){
         if(fa[0]==fa[1]&&fa[0]==fa[2])
              return 2;
          else
         return 4;
    }
    public int contwopair(int[] fa){
        if(fa[2]==fa[3]) return 3;             
        else return 4;              
    }
    public int conpair(int[] fa){
         if(fa[0]==fa[1])
              return 1;
          else if(fa[1]==fa[2])
              return 2;
          else if(fa[2]==fa[3])
              return 3;
          else
              return 4;
    }
    public static void main(String[] args){
       
     } 
}
