
import java.util.Arrays;

public class Hand implements Comparable<Hand> {

    // sorted by Card value are recommended but not necessary
    private Card[] cards = new Card[5];

    // TODO, Judge System will call this constructor once for each hand
    public String Hand(Card[] cards) {
        int a = 0;
        int[] b = new int[5];
        int[] cc = new int[5];
        String e = """";
        Arrays.sort(cards);
        for (int i = 0; i < 5; i++) {
            for (int j = i; j < 5; j++) {
                if (cards[i].getFace().equals(cards[i].getFace()) && cards[i].getFace().equals(cards[j].getFace())) {
                    cc[i] = cc[i] + 1;
                }
            }
            if (cc[i] == 3) {
                i = i + 2;
            }
            if (cc[i] == 2) {
                i = i + 1;
            }
        }
        Arrays.sort(cc);
        if (cc[4] == 3 && cc[3] == 2) {
            e = ""full house"";
            return e;
        } else if (cc[4] == 2 && cc[3] == 2) {
            e = ""two pair"";
            return e;
        } else if (cc[4] == 2 && cc[3] == 1) {
            e = ""one pair"";
            return e;
        } else if (Card.SUIT_ORDER.compare(cards[4], cards[3]) == 0 && Card.SUIT_ORDER.compare(cards[4], cards[2]) == 0 && Card.SUIT_ORDER.compare(cards[4], cards[1]) == 0 && Card.SUIT_ORDER.compare(cards[4], cards[0]) == 0) {
            e = ""flush"";
            return e;
        } else {
            Arrays.sort(cards);
            for (int i = 0; i < 5; i++) {
                switch (cards[i].getFace()) {
                    case ""A"":
                        b[i] = 14;
                        break;
                    case ""K"":
                        b[i] = 13;
                        break;
                    case ""Q"":
                        b[i] = 12;
                        break;
                    case ""J"":
                        b[i] = 11;
                        break;
                    default:
                        b[i] = Integer.parseInt(cards[i].getFace());
                        break;
                }
            }
            if (b[1] == b[0] + 1 && b[2] == b[1] + 1 && b[3] == b[2] + 1 && b[4] == b[3] + 1) {
                e = ""straight"";
                return e;
            } else {
                e = ""high card"";
                return e;
            }

        }

    }

    // TODO
    @Override
    public int compareTo(Hand that) {
        int x = 0;
        int y = 0;
        int b = 0;
        int a = 0;
        switch (Hand(this.cards)) {
            case ""full house"":
                x = 6;
                break;
            case ""flush"":
                x = 5;
                break;
            case ""straight"":
                x = 4;
                break;
            case ""two pair"":
                x = 3;
                break;
            case ""one pair"":
                x = 2;
                break;
            case ""high card"":
                x = 1;
                break;
        }
        switch (Hand(that.cards)) {
            case ""full house"":
                y = 6;
                break;
            case ""flush"":
                y = 5;
                break;
            case ""straight"":
                y = 4;
                break;
            case ""two pair"":
                y = 3;
                break;
            case ""one pair"":
                y = 2;
                break;
            case ""high card"":
                y = 1;
                break;
        }
        if (x > y) {
            return +1;
        } else if (x < y) {
            return -1;
        } else {
            Arrays.sort(this.cards);
            Arrays.sort(that.cards);
            if (this.cards[4].getFace().equals(that.cards[4].getFace())) {
                if (Card.SUIT_ORDER.compare(this.cards[4], that.cards[3]) == 1) {
                    return 1;
                } else if (Card.SUIT_ORDER.compare(this.cards[4], that.cards[3]) == -1) {
                    return -1;
                }
            } else {
                switch (this.cards[4].getFace()) {
                    case ""A"":
                        b = 14;
                        break;
                    case ""K"":
                        b = 13;
                        break;
                    case ""Q"":
                        b = 12;
                        break;
                    case ""J"":
                        b = 11;
                        break;
                    default:
                        b = Integer.parseInt(this.cards[4].getFace());
                        break;
                }
                switch (that.cards[4].getFace()) {
                    case ""A"":
                        a = 14;
                        break;
                    case ""K"":
                        a = 13;
                        break;
                    case ""Q"":
                        a = 12;
                        break;
                    case ""J"":
                        a = 11;
                        break;
                    default:
                        a = Integer.parseInt(that.cards[4].getFace());
                        break;
                }
                if (b > a) {
                    return +1;
                } else if (b < a) {
                    return -1;
                } else {
                    return 0;
                }
            }
        }
        return 0;
    }

    // Do not modified this function
    public Card[] getCards() {
        return this.cards;
    }
}
