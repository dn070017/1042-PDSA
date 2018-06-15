import java.io.BufferedReader;
import java.io.FileReader;

public class HandPQ{
    private Hand[] pq; // pq[i] = i th element on pq
    private int N=0;  // number of elements on pq ，N指向空的位置
    private int capacity;
    public HandPQ(int capacity){
        //pq = (Hand[]) new Comparable[N];
        pq = new Hand[capacity];
    }

    public boolean isEmpty()
    {return N==0;}
    
    public void insert(Hand h){
        pq[N++]=h;
    }
    
    public Hand delMax(){ // 刪除最大項並回傳
        Hand max = pq[0];
          int index = 0; // 最大的index
        for (int i=1;i<N;i++){
            int ans = max.compareTo(pq[i]);
            //System.out.println(ans);
            if (ans <0) {
                max = pq[i];
                index = i;
            }
        }
        //System.out.println(index);
//        // Exchange
            Hand temp = pq[N-1];
            pq[index] = temp;
            pq[N-1] = max;
        
            return pq[--N]; 
    }
    public int check(){ // 看看 N 的位置在哪
        return N;
    }
    
    public Hand getHand(int i){
        return pq[i];
    }
    
    public void swap(Hand c,int a,int b){
        Card temp = c.getCards()[a];
        c.getCards()[a] = c.getCards()[b];
        c.getCards()[b] = temp;
    }
    
    //此次作業跟HW6 PokerGame的差別為:沒有hand(5張手牌)的索引(即player的名稱)，所以重點不是找hand的player名稱，而是對 不同 hand(5張手牌)做排序
    // Goal: 找出第""三""大的5張手牌
    // 想法: 用Priority Queue實作，保留前""三""項最大的空間，每次insert就remove最大的那項，最後留下的最大項即為第三大的項
    public static void main(String[] args) throws Exception {
        
        try(BufferedReader br = new BufferedReader(new FileReader(args[0]))){
            
            String[] header = br.readLine().split("","");
            int count = Integer.parseInt(header[0]);
            int target = Integer.parseInt(header[1]);
            //System.out.println(count);
            HandPQ p = new HandPQ(count);
            //MinPQ<Hand> pq= new MinPQ<Hand>(target); java作者寫的MinPQ
            
            for(String in = br.readLine(); in != null; in = br.readLine()) { 
            
            Card[] CardArray = new Card[5]; // 一個hand的5張牌中的每一張
            String[] hand = in.split("","");
            for (int i=0;i<5;i++){
                String[] cardtemp = hand[i].split(""_"");
                Card c = new Card(cardtemp[1],cardtemp[0]);
                CardArray[i] = c;
            }
            
            Hand temp = new Hand(CardArray);
            p.insert(temp);
            //System.out.println(p.check());
            }
            
            
            //Hand test = p.getHand(0);
         //System.out.println(test.getCards()[4].getSuit()+ ""_"" + test.getCards()[4].getFace());
         

            
            for (int i = 1;i<target;i++){
            p.delMax();
            }
            Hand MaxHand = p.delMax();

//            System.out.println(MaxHand.getCards()[0].getSuit()+ ""_""+ MaxHand.getCards()[0].getFace()+"",""+
//                               MaxHand.getCards()[1].getSuit()+ ""_""+ MaxHand.getCards()[1].getFace()+"",""+
//                               MaxHand.getCards()[2].getSuit()+ ""_""+ MaxHand.getCards()[2].getFace()+"",""+
//                               MaxHand.getCards()[3].getSuit()+ ""_""+ MaxHand.getCards()[3].getFace()+"",""+
//                               MaxHand.getCards()[4].getSuit()+ ""_""+ MaxHand.getCards()[4].getFace());
            
            //Card之間的排序
            for (int i =0;i<5;i++){
                for (int j=i;j>0;j--){
                    if(MaxHand.getCards()[j].compareTo(MaxHand.getCards()[j-1])<0){
                        p.swap(MaxHand,j,j-1);
                    }
                }
            }
            System.out.println(MaxHand.getCards()[0].getSuit()+ ""_""+ MaxHand.getCards()[0].getFace()+"",""+
                               MaxHand.getCards()[1].getSuit()+ ""_""+ MaxHand.getCards()[1].getFace()+"",""+
                               MaxHand.getCards()[2].getSuit()+ ""_""+ MaxHand.getCards()[2].getFace()+"",""+
                               MaxHand.getCards()[3].getSuit()+ ""_""+ MaxHand.getCards()[3].getFace()+"",""+
                               MaxHand.getCards()[4].getSuit()+ ""_""+ MaxHand.getCards()[4].getFace());
            
        }
    }
}

