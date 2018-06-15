import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Arrays;

public class HandPQ {

    public static void main(String[] args) throws Exception {

        try(BufferedReader br = new BufferedReader(new FileReader(args[0]))){

            String[] header = br.readLine().split("","");
            int count = Integer.parseInt(header[0]);
            int target = Integer.parseInt(header[1]);
            MinPQ<Hand> min = new MinPQ<Hand>(target+1);
            
             for(String in = br.readLine(); in != null; in = br.readLine()) {
                Card[] cardsArray = new Card[5];
                String[] cardStr = br.readLine().split("","");
                for(int i = 0; i < 5; i++){
                    String[] sep = cardStr[i].split(""_"");
                    Card card = new Card(sep[1], sep[0]);
                    cardsArray[i] = card;
                }
                Hand current = new Hand(cardsArray);
                min.insert(current);
                if(min.size() > target){
                min.delMin();
                }
            }
            min.delMin();
            Card[] targetcards = min.delMin().getCards();
            Arrays.sort(targetcards);
            String message = """";
            for(int i = 0; i < 5; i++){
                Card current = targetcards[i];
                message = message + current.getSuit();
                message = message +""_"";
                message = message +current.getFace();
                if(i < 4){
                    message = message + "","";
                }
            }
            System.out.print(message);
        }
    }
}

