import java.io.BufferedReader;
import java.io.FileReader;
public class HandPQ {
    
    public static void main(String[] args) throws Exception {
        try(BufferedReader br = new BufferedReader(new FileReader(args[0]))){
            
            String[] header = br.readLine().split("","");
            int count = Integer.parseInt(header[0]);
            int target = Integer.parseInt(header[1]);
            
            MinPQ<Hand>gg=new <Hand>MinPQ();
            //construct players' hands
            for(int x=0;x<count;x++) {
                Card[] cardsArray = new Card[5];
                String[] cardStr = br.readLine().split("","");
                for(int i = 0; i < 5; i++){
                    String[] sep = cardStr[i].split(""_"");
                    cardsArray[i] = new Card(sep[1], sep[0]);
                }
                Hand playerArray = new Hand(cardsArray);
                 gg.insert(playerArray);
                 if(gg.size()>target)
                    gg.delMin();
            }
                    MinPQ<Card>haha=new <Card>MinPQ(gg.delMin().getCards());                    
                    for(int j=0;j<5;j++){
                        Card ff=haha.delMin();
                        String face=ff.getFace();
                        String suit=ff.getSuit();
                        System.out.print(suit);
                        System.out.print(""_"");
                        System.out.print(face);
                        if(j!=4) System.out.print("","");
                    }
            }
        }
    }

