import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.File;
import java.util.Scanner;

public class HandPQ {

    public static void main(String[] args) throws Exception {
        
        File file = new File(args[0]);
        try(BufferedReader br = new BufferedReader(new FileReader(args[0]))){
//            String[] header = br.readLine().split("","");
//            int count = Integer.parseInt(header[0]);
//            int target = Integer.parseInt(header[1]);
            
            Scanner File_in = new Scanner(file);
            String line_1 = File_in.nextLine();
            String[] Nums = line_1.split("","");

            int count = Integer.parseInt(Nums[0]);
            int target = Integer.parseInt(Nums[1]);
//            System.out.println(count);
            
            MinPQ pq = new MinPQ();
            
            Hand[] ahand = new Hand[1];
            
            while (count != 0)
            {
//                String[] cardStr = br.readLine().split("","") ;
                Card[] handcard = new Card[5];
                String line_2 = File_in.nextLine();
                String[] cardStr = line_2.split("","");
                
                for(int i = 0; i < 5; i++){
                    String[] sep = cardStr[i].split(""_"");
                    handcard[i] = new Card(sep[1], sep[0]);
                }
                ahand[0] = new Hand(handcard);
                pq.insert(ahand[0]);
                
                if (pq.size() > target){
                    pq.delMin();
                }
                count--;
            }
            
            Hand finalans = Hand.class.cast(pq.delMin());
            Card[] fina = finalans.getCards();
            
            for(int j = 0 ; j < 5 ; j++){
                System.out.print(fina[j].getSuit()+
                        ""_""+
                        fina[j].getFace());
                if(j!=4)
                    System.out.print("","") ;

            }
//            }
            
            
//            System.out.println(fina[0].getSuit());
//            System.out.println(fina[1].getSuit());
//            System.out.println(fina[2].getSuit());
//            System.out.println(fina[3].getSuit());
//            System.out.println(fina[4].getSuit());
//            System.out.println(fina[0].getFace());
//            System.out.println(fina[1].getFace());
//            System.out.println(fina[2].getFace());
//            System.out.println(fina[3].getFace());
//            System.out.println(fina[4].getFace());

            

        }
        catch(IOException e){
            System.out.println(""error!""); 
        }
    }
}

