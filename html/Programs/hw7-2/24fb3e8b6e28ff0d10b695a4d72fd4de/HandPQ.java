
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Arrays;

public class HandPQ {

    public static void main(String[] args) throws Exception {

        try (BufferedReader br = new BufferedReader(new FileReader(args[0]))) {

            String[] header = br.readLine().split("","");
            int count = Integer.parseInt(header[0]);
            int target = Integer.parseInt(header[1]);

            Hand[] playerArray = new Hand[count];
           
            for (int x = 0; x < count; x++) {
                Card[] cardsArray = new Card[5];
                String[] cardStr = br.readLine().split("","");
                for (int i = 0; i < 5; i++) {
                    String[] sep = cardStr[i].split(""_"");
                    Card card = new Card(sep[1], sep[0]);
                    cardsArray[i] = card;
                }
                playerArray[x] = new Hand(cardsArray);
            }
                Arrays.sort(playerArray);
                
                    Card[] A =playerArray[count-target].getCards();
                    for(int i=0;i<4;i++)
                    System.out.printf(A[i].getSuit()+""_""+A[i].getFace()+"","");
                    
                    System.out.printf(A[4].getSuit()+""_""+A[4].getFace());
                }

            }
        }
    

