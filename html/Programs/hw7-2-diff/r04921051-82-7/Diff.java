import java.io.BufferedReader;
import java.io.FileReader;
import java.util.*;

public class HandPQ {
/*    
    int capacity;
    List<Hand> list;

    HandPQ (int capacity){
        this.capacity = capacity;
        list = new LinkedList<>();
    }

    public void insert(Hand hand) {
        list.add(hand);
        if (list.size() > capacity){
            this.delMin();
        }
    }

    public Hand delMin() {
        Hand min = Collections.min(list);
        list.remove(min);
        return min;
    }    
*/
    public static void main(String[] args) throws Exception {

        
        try(BufferedReader br = new BufferedReader(new FileReader(args[0]))){

            String[] header = br.readLine().split("","");
            int count = Integer.parseInt(header[0]);
            int target = Integer.parseInt(header[1]);

            
            MinPQ<Hand> pq = new MinPQ<Hand>();
          
            for(int j = 0; j < count; j++){
                Card[] cardsArray = new Card[5];
                String[] in = br.readLine().split("","");
                for(int i = 0; i < 5; i++){
                    String[] sep = in[i].split(""_"");
                    Card card = new Card(sep[1], sep[0]);
                    cardsArray[i] = card;                           
                }                
                Hand item = new Hand(cardsArray); 
                pq.insert(item);
                if(pq.size() > target)
                    pq.delMin();
                
            }
            print(pq.delMin());
        }
    }
    
    public  static  void printPQ(MinPQ<Hand> q  ){
            Iterator<Hand> iterator = q.iterator();        
            while(iterator.hasNext())
              print(iterator.next());  
    }    

   
    
    public static void print(Hand p){
            Card[] cards = p.getCards();
            Arrays.sort(cards);
            for(int i = 0; i < 4;i++)
            System.out.printf(p.getCards()[i].getSuit() + ""_"" + p.getCards()[i].getFace() + "","");
            System.out.printf(p.getCards()[4].getSuit() + ""_"" + p.getCards()[4].getFace()+""\n"");                              
    }
    
}
/*

public class HandPQ {

    int capacity;
    List<Hand> list;

    HandPQ (int capacity){
        this.capacity = capacity;
        list = new LinkedList<>();
    }

    public void add(Hand hand) {
        list.add(hand);
        if (list.size() > capacity){
            this.deleteMin();
        }
    }

    public Hand deleteMin() {
        Hand min = Collections.min(list);
        list.remove(min);
        return min;
    }


    public static void main(String[] args) throws Exception {

        try(BufferedReader br = new BufferedReader(new FileReader(args[0]))){

            String[] header = br.readLine().split("","");
            int count = Integer.parseInt(header[0]);
            int target = Integer.parseInt(header[1]);
            HandPQ pq = new HandPQ(target);

            for (int line = 0 ; line < count ; line++ ){
                Card[] cardsArray = new Card[5];
                String[] cardStr = br.readLine().split("","");
                for(int i = 0; i < 5; i++){
                    String[] sep = cardStr[i].split(""_"");
                    Card card = new Card(sep[1], sep[0]);
                    cardsArray[i] = card;
                }
                Hand hand = new Hand(cardsArray);
                pq.add(hand);
            }
            br.close();

            Card[] cards = pq.deleteMin().getCards();
            Arrays.sort(cards);
            System.out.println(toString(cards));

        }
    }

    public static String toString(Card[] cards){
        String temp = """";
        String sp = """";
        for (Card c:cards){
            temp += sp + c.getSuit() + ""_"" + c.getFace();
            sp = "","";
        }
        return temp;
    }
}
*/
