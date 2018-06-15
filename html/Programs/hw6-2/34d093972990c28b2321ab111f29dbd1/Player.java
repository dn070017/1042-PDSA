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

    // TODO 
    public int compareTo(Player that) {
        int countThis = 0, ptsThis = 0, countThat = 0, ptsThat = 0, strThis = 0, strThat = 0, flushThis = 0, flushThat = 0;
        Arrays.sort(this.cards, Card::compareTo);
        Arrays.sort(that.cards, Card::compareTo);

        int[] iFace1 = new int[cards.length];
        int[] iFace2 = new int[cards.length];
        for (int i = 0; i < cards.length; i++) {
            if (this.cards[i].getFace().equals(""A"")) iFace1[i] = 1;
            else if (this.cards[i].getFace().equals(""J"")) iFace1[i] = 11;
            else if (this.cards[i].getFace().equals(""Q"")) iFace1[i] = 12;
            else if (this.cards[i].getFace().equals(""K"")) iFace1[i] = 13;
            else iFace1[i] = Integer.parseInt(this.cards[i].getFace());

            if (that.cards[i].getFace().equals(""A"")) iFace2[i] = 1;
            else if (that.cards[i].getFace().equals(""J"")) iFace2[i] = 11;
            else if (that.cards[i].getFace().equals(""Q"")) iFace2[i] = 12;
            else if (that.cards[i].getFace().equals(""K"")) iFace2[i] = 13;
            else iFace2[i] = Integer.parseInt(that.cards[i].getFace());

//            System.out.println(this.cards[i].getFace()+"" ""+this.cards[i].getSuit());
        }
//        System.out.println(""\n"");

        for (int i = 0; i < cards.length - 1; i++) {
            if (iFace1[i]==iFace1[i+1]) countThis++;
            if (iFace2[i]==iFace2[i+1]) countThat++;
            if (Math.abs(iFace1[i] - iFace1[i + 1]) != 1) strThis++;
            if (Math.abs(iFace2[i] - iFace2[i + 1]) != 1) strThat++;
            if (!this.cards[i].getSuit().equals(this.cards[i + 1].getSuit())) flushThis++;
            if (!that.cards[i].getSuit().equals(that.cards[i + 1].getSuit())) flushThat++;
        }

        if (countThis == 3) ptsThis += 5;
        else if (countThis == 2) ptsThis += 2;
        else if (countThis == 1) ptsThis += 1;

        if (countThat == 3) ptsThat += 5;
        else if (countThat == 2) ptsThat += 2;
        else if (countThat == 1) ptsThat += 1;

        if (strThis == 0) ptsThis += 3;
        if (strThat == 0) ptsThat += 3;

        if (flushThis == 0) ptsThis += 4;
        if (flushThat == 0) ptsThat += 4;
//        System.out.println(ptsThis);
.
        if(ptsThis - ptsThat!=0)return ptsThis-ptsThat;
        else{
            if(iFace1[0]-iFace2[0]==0)return this.cards[0].getSuit().compareTo(that.cards[0].getSuit());
            else {
                if(iFace1[0]==1)iFace1[0]=14;
                if(iFace2[0]==1)iFace2[0]=14;
                return iFace1[0]-iFace2[0];
            }
        }

    }
}

