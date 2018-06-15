import java.util.Comparator;

public class Card implements Comparable<Card> {

    private String face; // should be one of [A, 2, 3, 4, 5, 6, 7, 8, 9, 10, J, Q, K]
    private String suit; // should be one of [Spades, Hearts, Diamonds, Clubs]

    public static final Comparator<Card> SUIT_ORDER = new SuitOrder();

    // DO NOT MODIFY THIS
    public Card(String face, String suit) {
        this.face = face;
        this.suit = suit;
    }

    // DO NOT MODIFY THIS
    public String getFace() {
        return this.face;
    }

    // DO NOT MODIFY THIS
    public String getSuit() {
        return this.suit;
    }

    // TODO
    public int compareTo(Card that) {
        // complete this function so the Card can be sorted
        // (you must consider both face and suit)

        int f1 = faceIndex(this.getFace());
        int f2 = faceIndex(that.getFace());

        if (f1 > f2) return 1;
        else if (f1 < f2) return -1;
        else if (f1 == f2) return SUIT_ORDER.compare(this, that);
        return 0;
    }


    public int faceIndex(String f) {
        String[] faces = {""2"", ""3"", ""4"", ""5"", ""6"", ""7"", ""8"", ""9"", ""10"", ""J"", ""Q"", ""K"", ""A""};
        int face_index = 0;
        while (!f.equals(faces[face_index])) face_index++;
        return face_index;
    }


    // TODO
    private static class SuitOrder implements Comparator<Card> {
        public int compare(Card c1, Card c2) {
            // complete this function so the Card can be sorted according to the suit

            String[] suits = {""Clubs"", ""Diamonds"", ""Hearts"", ""Spades""};

            int c1_index = 0;
            while (!c1.getSuit().equals(suits[c1_index])) c1_index++;

            int c2_index = 0;
            while (!c2.getSuit().equals(suits[c2_index])) c2_index++;

            if (c1_index > c2_index) return 1;
            else if (c1_index == c2_index) return 0;
            else if (c1_index < c2_index) return -1;
            return 0;
        }
    }
}
