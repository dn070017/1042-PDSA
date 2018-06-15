
import java.util.Arrays;

public class Player implements Comparable<Player> {

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

 
    
    private int[] cardLabel(Card[] cards) {

        int[] label = new int[5];

        for (int i = 0; i < 5; i++) {

            int value = 0;
            if (""A"".equals(cards[i].getFace())) {
                value += 12;
            } else if (""K"".equals(cards[i].getFace())) {
                value += 11;
            } else if (""Q"".equals(cards[i].getFace())) {
                value += 10;
            } else if (""J"".equals(cards[i].getFace())) {
                value += 9;
            } else if (""10"".equals(cards[i].getFace())) {
                value += 8;
            } else if (""9"".equals(cards[i].getFace())) {
                value += 7;
            } else if (""8"".equals(cards[i].getFace())) {
                value += 6;
            } else if (""7"".equals(cards[i].getFace())) {
                value += 5;
            } else if (""6"".equals(cards[i].getFace())) {
                value += 4;
            } else if (""5"".equals(cards[i].getFace())) {
                value += 3;
            } else if (""4"".equals(cards[i].getFace())) {
                value += 2;
            } else if (""3"".equals(cards[i].getFace())) {
                value += 1;
            } else if (""2"".equals(cards[i].getFace())) {
                value += 0;
            }
            if (""Spades"".equals(cards[i].getSuit())) {
                value = value * 4 + 3;
            } else if (""Hearts"".equals(cards[i].getSuit())) {
                value = value * 4 + 2;
            } else if (""Diamonds"".equals(cards[i].getSuit())) {
                value = value * 4 + 1;
            } else if (""Clubs"".equals(cards[i].getSuit())) {
                value = value * 4;
            }
            label[i] = value;
        }
        return label;
    }


    
    private int findMax(int[] a) {

        int b = 0;
        for (int i = 0; i < a.length; i++) {
            if (a[i] > b) {
                b = a[i];
            }
        }
        return b;
    }

    private int findGroup(int[] a) {
        int b = 0;
        for (int i = 0; i < a.length; i++) {
            if (a[i] != 0) {
                b++;
            }
        }
        return b;
    }

    //找大招

    private int[] combo(int[] card) {
        int faceGroup = 0;
        int suitGroup = 0;
        int fgMax = 0;
        int sgMax = 0;
        int ans[] = new int[2];
        int[] face = new int[13];
        int[] suit = new int[4];
        for (int i = 0; i < card.length; i++) {
            face[card[i] % 13]++;
            suit[card[i] %4]++;

        }
        
        faceGroup = findGroup(face);
        suitGroup = findGroup(suit);
        fgMax = findMax(face);
        sgMax = findMax(suit);
        //full house
        if (faceGroup == 2 && fgMax == 3) {
            ans[0] = 5;
            int temp = 0;
            for (int i = 0; i < face.length; i++) {
                if (face[i] == 3) {
                    temp = i;

                }
            }

            for (int i = 0; i < card.length; i++) {
                if (card[i] % 13 == temp) {
                    ans[1] = card[i];
                }
            }
            
        }
        //flush
        if (suitGroup == 1 && sgMax == 5&&ans[0]==0) {
            
            ans[0] = 4;
            ans[1] = card[card.length-1];
        }
        //straight
        if (faceGroup == 5 && fgMax == 1&&ans[0]==0) {
            
            if (card[0]%13+1==card[1]%13&&card[1]%13+1==card[2]%13&&card[2]%13+1==card[3]%13&&card[3]%13+1==card[4]%13) {
                ans[0] = 3;
                ans[1] = card[card.length-1];
            }
        }
         //two pair
        if (faceGroup == 3 && fgMax == 2&&ans[0]==0) {
            ans[0] = 2;
            int temp=0;
            for(int i=0;i<face.length;i++)
            {
                if(face[i]==2)
                {
                    temp=i;
                }
            }
            for(int i=0;i<card.length;i++)
            {
                if(card[i]%13==temp)
                {
                    ans[1]=card[i];
                }
            }
        }
        //one pair
        if (faceGroup == 4 && fgMax == 2&&ans[0]==0) {
            ans[0] = 1;
            int temp=0;
            for(int i=0;i<face.length;i++)
            {
                if(face[i]==2)
                {
                    temp=i;
                }
            }
            for(int i=0;i<card.length;i++)
            {
                if(card[i]%13==temp)
                {
                    ans[1]=card[i];
                }
            }
        }
        //high card
        else{
            ans[0] = 0;
            ans[1] = card[card.length-1];
        }
       
        
        
        
        
        
        
         

        return ans;
    }

    // TODO 
    public int compareTo(Player that) {
        
        Arrays.sort(this.cards);
        Arrays.sort(that.cards);
.
        
        int ans = 0;
        int[] a = combo(cardLabel(this.cards));
        int[] b = combo(cardLabel(that.cards));
        if (a[0] > b[0]) {
            ans = 1;
        } else if (a[0] < b[0]) {
            ans = -1;
        } else if (a[0] == b[0] && a[1] > b[1]) {
            ans = 1;
        } else if (a[0] == b[0] && a[1] < b[1]) {
            ans = -1;
        }
        return ans;
        
    }
    
   
}



