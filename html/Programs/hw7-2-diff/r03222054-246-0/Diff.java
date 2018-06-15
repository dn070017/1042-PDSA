import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
public class HandPQ 
{
    private static class HandOrder implements Comparator<Hand> 
    {
        public int compare(Hand o1, Hand o2) {
            return 1*(o1.compareTo(o2));
        }
    }
  
    public static void main(String[] args) throws Exception {

        try(BufferedReader br = new BufferedReader(new FileReader(args[0]))){

            String[] header = br.readLine().split("","");
            int count = Integer.parseInt(header[0]);
            int target = Integer.parseInt(header[1]);
            Comparator<Hand> Hand_ORDER = new HandOrder();
            PriorityQueue<Hand> player = new PriorityQueue<Hand>(count,Hand_ORDER) ;
            int tempCount = 0;
            String line;
            while((line = br.readLine())!=null )
            {
                tempCount++;
                String[] cards =line.split("","");
                
                Card[] cardArray=new Card[5];
                for(int i= 0; i<5; i++)
                {
                    Card newCard;
                    String[] temp = cards[i].split(""_"");
                    newCard = new Card(temp[0], temp[1]);
                    cardArray[i] = newCard;
                }
                Arrays.sort(cardArray);
                Hand newHand= new Hand(cardArray);
           //     Hand.sort();
                player.offer(newHand);
            }
           // System.out.print(tempCount);
            for (int i= 1; i<4; i++)
            {
                player.remove();
            }
             Card[] temp = player.peek().getCards();
            for (int i= 4; i>0; i--)
            {
               String face = temp[i].getFace();
                String suit= temp[i].getSuit();
                System.out.print(face + ""_""+suit +"", "");
            }
        }
    }

}

