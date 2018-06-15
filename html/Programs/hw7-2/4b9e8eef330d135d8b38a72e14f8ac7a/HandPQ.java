import java.io.BufferedReader;
import java.io.FileReader;

public class HandPQ  implements Comparable<HandPQ>{
    private Hand[] pq;
    //private Card[] cards = new Card[5];
    private int N ;
    public HandPQ(int capacity)
    {   N = 0;
        pq = new Hand[capacity+1];
    }
    public boolean isEmpty(){ return N == 0; }
    private void swim(int k){
        while (k > 1 && less(k/2, k)){
            exch(k, k/2);
            k  = k/2;}}
    public void insert(Hand x){
        if(N>50) delMin();
        pq[++N] = x;
        swim(N);}
    private void sink(int k){
        while (2*k <= N){
            int j = 2*k;
            if (j < N && less(j, j+1)) j++;
            if (!less(k, j)) break;
            exch(k, j);
            k = j;}}
    public Hand delMax(){
        Hand max = pq[1];
        exch(1, N--);
        sink(1);
        pq[N+1] = null;
        return max;}
    public Hand delMin(){
        exch(1, N);
        Hand min = pq[N--];
        sink(1);
        pq[N+1] = null;   
        return min;
    }
    private boolean less(int i, int j)
    { return pq[i].compareTo(pq[j]) < 0; }
    private void exch(int i, int j)
    { Hand t = pq[i]; pq[i] = pq[j]; pq[j] = t; }
    

    public static void main(String[] args) throws Exception {
        
        try(BufferedReader br = new BufferedReader(new FileReader(args[0]))){
            
            String[] header = br.readLine().split("","");
            int count = Integer.parseInt(header[0]);
            int target = Integer.parseInt(header[1]);
            
            HandPQ handArray = new HandPQ(count);
            for(int i = 0;i < count ;i++){
                Card[] cardsArray = new Card[5];
                String[] cardStr = br.readLine().split("","");
                for(int j = 0;j < 5;j++){
                    String[] sep = cardStr[j].split(""_"");
                    Card card = new Card(sep[1],sep[0]);
                    cardsArray[j] = card;}
                Hand singlehand = new Hand(cardsArray);
                handArray.insert(singlehand);}
            for(int i = 0;i<target-1;i++){
                handArray.delMax();}
            Hand ans = handArray.delMax();
            Card[] ans1 = ans.getCards();    
            
            System.out.println(ans1[0].getSuit()+""_""+ans1[0].getFace()+"",""+ans1[1].getSuit()+""_""+ans1[1].getFace()+"",""
            +ans1[2].getSuit()+""_""+ans1[2].getFace()+"","" +ans1[3].getSuit()+""_""+ans1[3].getFace()+"","" +ans1[4].getSuit()+""_""+ans1[4].getFace());
            

        }
    }

    @Override
    public int compareTo(HandPQ t) {
.
    }
}


