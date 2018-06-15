
import edu.princeton.cs.algs4.MinPQ;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;

public class HandPQ {
    
    public static void main(String[] args) throws Exception {
        
        MinPQ<Hand> pq = new MinPQ<Hand>();
        
        try (BufferedReader br = new BufferedReader(new FileReader(args[0]))) {
            
            String[] header = br.readLine().split("","");
            int count = Integer.parseInt(header[0]);
            int target = Integer.parseInt(header[1]);
            int limit=target;
            for (int i=0;i<count;i++) {                
                Card[] cardsArray = new Card[5];
                String[] test1 = br.readLine().split("","");
                for (int j = 0; j < 5; j++) {
                    String[] sep = test1[j].split(""_"");
                    Card card = new Card(sep[1], sep[0]);
                    cardsArray[j] = card;
                }
                Hand aa = new Hand(cardsArray);
                pq.insert(aa);
                if(pq.size()>target)
                {
                    pq.delMin();
                }
                
            }
            Card[] ans=pq.min().getCards();
            String[] suit=new String[ans.length];
            String[] face=new String[ans.length];
            for(int i=0;i<ans.length;i++)
            {
                suit[i]=ans[i].getSuit();
            }
            for(int i=0;i<ans.length;i++)
            {
                face[i]=ans[i].getFace();
            }
            String fin="""";
            for(int i=0;i<ans.length-1;i++)
            {
                fin=fin+suit[i]+""_""+face[i]+"","";
            }
            fin=fin+suit[suit.length-1]+""_""+face[face.length-1];
            String fpath=""D:\\PDSA\\Hand\\out.txt"";
            BufferedWriter fout=new BufferedWriter(new FileWriter(fpath));
            fout.write(fin);
            fout.newLine();
            fout.close();
        }
    }
}

