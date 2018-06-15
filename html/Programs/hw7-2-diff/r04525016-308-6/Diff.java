import java.io.BufferedReader;
import java.io.FileReader;
import java.lang.reflect.Array;
import java.util.Arrays;

public class HandPQ {

    private Hand[] pq;
    private int size;

    public HandPQ() {
        size = 0;
        pq = new Hand[20];
    }

    private void resize(){
        if (size == pq.length - 3) {
            Hand[] pq2 = new Hand[2 * pq.length];
            for (int i = 0; i < size; i++) {
                pq2[i] = pq[i];
            }
            pq = pq2;
        }else if (size < pq.length / 4) {
            Hand[] pq2 = new Hand[pq.length / 2];
            for (int i = 0; i < size; i++) {
                pq2[i] = pq[i];
            }
            pq = pq2;
        }
    }

    public void insert(Hand hand) {
        resize();
        pq[size] = hand;
        size++;
    }

    public Hand delMin() {
        resize();
        int temp = 0;
        for (int i = 1; i < size; i++) {
            if (pq[i].compareTo(pq[temp]) == -1) {
                temp = i;
            }

        }
        pq[size] = pq[temp];
        pq[temp] = pq[size - 1];
        return pq[size--];

    }

    public boolean isEmpty() {
        return (size == 0);
    }

    public int size() {
        return size;
    }


    public static void main(String[] args) throws Exception {
        HandPQ pq = new HandPQ();

        try (BufferedReader br = new BufferedReader(new FileReader(args[0]))) {

            String[] header = br.readLine().split("","");
            int count = Integer.parseInt(header[0]);
            int target = Integer.parseInt(header[1]);

            for (int i = 0; i < count; i++) {
                String[] temp = br.readLine().split("","");
                Card[] cardsArray = new Card[5];
                for (int j = 0; j < 5; j++) {
                    String[] temp2 = temp[j].split(""_"");
                    Card c = new Card(temp2[1], temp2[0]);
                    cardsArray[j] = c;
                }
                Hand hand = new Hand(cardsArray);
                pq.insert(hand);
                if (pq.size() > target) {
                    pq.delMin();
                }
            }
            br.close();
        }
        Hand delHand = pq.delMin();
        Card[] delCard = delHand.getCards();
        Arrays.sort(delCard);

        String mix = """";
        String temp;
        for (int i = 0; i < 5; i++) {
            String face = delCard[i].getFace();
            String suit = delCard[i].getSuit();
            if (i == 0) {
                temp = suit + ""_"" + face;
            } else {
                temp = "","" + suit + ""_"" + face;
            }
            mix = mix + temp;
        }
        System.out.println(mix);
    }
}


