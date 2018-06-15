import java.util.ArrayList;
import java.util.Arrays;
public class Player implements Comparable<Player>{ 

    public Card[] cards = new Card[5];
    private String name;
    private String type;
    private ArrayList<count> C=new ArrayList<count>();
    private Card great;
     
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
        Arrays.sort(cards);
        this.cards = cards;
    }
     
    // TODO
    public Card[] getCard(){
        return this.cards;
    }
    public int compareTo(Player that) {
.
        this.setType();
        that.setType();
        String[] typeOrder={"" highcard"", ""pair"", ""2pairs"", ""straight"", ""flush"", ""fullhouse""};
        int thisI=getIndex(typeOrder,this.type);
        int thatI=getIndex(typeOrder,that.type);

            if (thisI>thatI)return 1;
            else if (thisI==thatI){
                if (this.type.equals(""straight"")||this.type.equals(""highcard"")||this.type.equals(""flush"")){
                    //if (this.type.equals(""flush"")&&)
                    return this.cards[0].compareTo(that.cards[0]);
                }
                else if(this.type.equals(""fullhouse"")||this.type.equals(""pair"")){
                    return this.great.compareTo(that.great);
                }
                else return this.cards[0].compareTo(that.cards[0]);
            }
            
            else return -1;
    }
    public void setType(){
        
        C.add(new count(cards[0]));
        for (int i =1;i<5;i++){
            for (int j=0;j<C.size();j++){
                if (cards[i].getFace().equals(C.get(j).val.getFace())){
                    C.get(j).N++;
                    if (cards[i].compareTo(C.get(j).val)==1)
                        C.get(j).setVal(cards[i]);
                }
                else
                    C.add(new count(cards[i]));
            }
        }
        String[] faceOrder={"" 2"", ""3"", ""4"", ""5"", ""6"", ""7"", ""8"", ""9"", ""10"", ""J"", ""Q"", ""K"",""A""};
        if (C.size()==2){
            type=""fullhouse"";
            if (C.get(0).N==3) 
                great=C.get(0).val;
            else
                great=C.get(1).val;
        }
        else if (C.size()==3){
            type=""2pairs"";
            
        }
        else if (C.size()==4){
            type=""pair"";
            for (int i=0;i<4;i++){
                if (C.get(i).N==2)
                    great=C.get(i).val;
            }
        }
        else{
            boolean b=true;
            for (int k=0;k<4;k++){
                if (cards[k].getSuit()!=cards[k+1].getSuit()){
                    b=false;
                    break;
                }    
            }
            if (b)type=""flush"";
            else if(getIndex(faceOrder,cards[0].getFace())==getIndex(faceOrder,cards[4].getFace())+4)type=""straight"";
            else type=""highcard"";
        }
        
    }
    public int getIndex(String[] ref,String s){
        int I=0;
            for (int i=0;i<ref.length;i++){
                if (ref[i].equals(s))
                    I=i;
            }
            return I;
    }

}


class count{
    public Card val;
    public int N;
    public count(Card val){
        this.val=val;
        this.N=1;
    }
    public void setVal(Card c){
        this.val=c;
    }
}



