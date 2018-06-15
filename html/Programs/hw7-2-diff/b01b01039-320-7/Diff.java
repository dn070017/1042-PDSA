import java.io.BufferedReader;
import java.io.FileReader;

public class HandPQ {

    public static void main(String[] args) throws Exception {
		try (BufferedReader br = new BufferedReader(new FileReader(args[0]))) {
            int idx = 0;
			MinPQ<Hand> pq = new MinPQ();
			
			String[] s = br.readLine().split("","");
			int handCount = Integer.parseInt(s[0]);
			int handNum   = Integer.parseInt(s[1]);
            
            for(String in = br.readLine(); in != null; in = br.readLine()) {
                Card[] cardsArray = new Card[5];
                String[] cardStr = in.split("","");
                for(int i = 0; i < 5; i++){
                    String[] sep = cardStr[i].split(""_"");
                    Card card = new Card(sep[1], sep[0]);
                    cardsArray[i] = card;
                }
				pq.insert(new Hand(cardsArray));
				if(pq.size() > handNum){
					pq.delMin();
				}
			}
			Hand hd = pq.min();
			Card[] cd = hd.getCards();
			for(int i=0; i<5; i++){
				System.out.print(cd[i].getSuit()+""_""+cd[i].getFace());
				if(i<4)System.out.print("","");
				else System.out.println();
			}
        }
    }
}

