
import java.util.*;

public class Player implements Comparable<Player>{

    public Card[] cards = new Card[5];
    private String name;

    public enum Type {
        NOT_SET(1),
        HIGH_CARD(2),
        ONE_PAIR(3),
        TWO_PAIR(4),
        STRAIGHT(5),
        FLUSH(6),
        FULL_HOUSE(7);

        private final int _val;

        Type(int value) {
            _val = value;
        }

        public static int compareTo(Type t1, Type t2) {
            return t1._val - t2._val;
        }
    }

    public Type type;
    public Card highestCard;

    // DO NOT MODIFY THIS
    public Player(String name) {
        this.name = name;
        this.type = Type.NOT_SET;
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

        if (this.type == Type.NOT_SET) {
            this.distinguishType();
        }
        if (that.type == Type.NOT_SET) {
            that.distinguishType();
        }

        if (this.type != that.type)
            return Type.compareTo(this.type, that.type);

        return this.highestCard.compareTo(that.highestCard);
    }

    public void distinguishType() {
        Arrays.sort(cards);

        if (this.isFullHouse() ||
            this.isFlush() ||
            this.isStraight() ||
            this.hasPair())
            return;

        this.type = Type.HIGH_CARD;
        this.highestCard = cards[4];
    }

    public boolean isFullHouse() {
        if (!cards[0].getFace().equals(cards[1].getFace()) ||
            !cards[3].getFace().equals(cards[4].getFace()))
            return false;

        if (cards[2].getFace().equals(cards[1].getFace())) {
            this.type = Type.FULL_HOUSE;
            this.highestCard = cards[2];
            return true;
        }
        else if (cards[2].getFace().equals(cards[3].getFace())) {
            this.type = Type.FULL_HOUSE;
            this.highestCard = cards[4];
            return true;
        }

        return false;
    }

    public boolean isFlush() {
        if (cards[0].getSuit().equals(cards[1].getSuit()) &&
            cards[1].getSuit().equals(cards[2].getSuit()) &&
            cards[2].getSuit().equals(cards[3].getSuit()) &&
            cards[3].getSuit().equals(cards[4].getSuit())) {
            this.type = Type.FLUSH;
            this.highestCard = cards[4];
            return true;
        }

        return false;
    }

    public boolean isStraight() {
        Map<String, Integer> faceMap = new HashMap<String, Integer> ();
        faceMap.put(""A"", 14);
        faceMap.put(""2"", 2);
        faceMap.put(""3"", 3);
        faceMap.put(""4"", 4);
        faceMap.put(""5"", 5);
        faceMap.put(""6"", 6);
        faceMap.put(""7"", 7);
        faceMap.put(""8"", 8);
        faceMap.put(""9"", 9);
        faceMap.put(""10"", 10);
        faceMap.put(""J"", 11);
        faceMap.put(""Q"", 12);
        faceMap.put(""K"", 13);

        int count = 0;
        for (int i=0; i<5; ++i) {
            if ((faceMap.get(cards[i].getFace())+1) % 13 == faceMap.get(cards[(i+1)%5].getFace()) % 13)
                count++;
        }

        if (count == 4) {
            this.type = Type.STRAIGHT;
            this.highestCard = cards[4];
            return true;
        }

        return false;
    }

    public boolean hasPair() {
        int count = 0;
        for (int i=0; i<4; ++i) {
            if (cards[i].getFace().equals(cards[i+1].getFace())) {
                this.highestCard = cards[i+1];
                count++;
                i++;
            }
        }

        if (count == 2) {
            this.type = Type.TWO_PAIR;
            return true;
        }
        else if (count == 1) {
            this.type = Type.ONE_PAIR;
            return true;
        }

        return false;
    }
}

