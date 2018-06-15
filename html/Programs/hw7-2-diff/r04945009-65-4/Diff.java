import java.io.BufferedReader;
import java.io.FileReader;

public class HandPQ {
    
    
        public static void  sort(Comparable[] a) {
            int N = a.length;
            for (int k=N/2; k>=1; k--)
                sink(a,k,N);
            while (N>1) {
                exch(a, 1, N);
                sink(a, 1, --N);
            }
        }
    
        private static void sink(Comparable[] a, int k, int N) {
            while (2*k <= N) {
                int j = 2*k;
                if (j<N && less(a, j, j+1)) j++;
                if (!less(a, k, j)) break;
                exch(a, k,j);
                k = j;
            }
        }
    
        private static boolean less(Comparable[] a, int i, int j) {
            return a[i-1].compareTo(a[j-1]) < 0;
        }
    
        private static void exch(Comparable[] a, int i, int j) {
            Comparable swap = a[i-1]; a[i-1] = a[j-1]; a[j-1] = swap;
        }
    

    
    public static void main(String[] args) throws Exception {

        try(BufferedReader br = new BufferedReader(new FileReader(args[0]))){

            String[] header = br.readLine().split("","");
            int count = Integer.parseInt(header[0]);
            int target = Integer.parseInt(header[1]);
            
            Hand[] handArray = new Hand[count];
            int idx = 0;
            while(br.ready()) {
                String[] cardStr = br.readLine().split("","");
                Card[] cardsArray = new Card[5];
                for(int i = 0; i < 5; i++){
                    String[] sep = cardStr[i].split(""_"");
                    Card card = new Card(sep[1], sep[0]);
                    cardsArray[i] = card;
                }
                handArray[idx++] = new Hand(cardsArray);
            }
            HandPQ.sort(handArray);
            
            Card[] printout = new Card[5];
            for (int i=0; i<5; i++){
                printout = handArray[count - target].getCards();
                System.out.print(printout[i].getSuit()+""_""+printout[i].getFace());
                if (!(i == 4))
                    System.out.print("","");
            }

        }
    }
}

