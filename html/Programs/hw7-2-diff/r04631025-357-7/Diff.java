
import java.io.BufferedReader;
import java.io.FileReader;

public class HandPQ {

    public static void main(String[] args) throws Exception {

        try(BufferedReader br = new BufferedReader(new FileReader(args[0]))){

            String[] header = br.readLine().split("","");
            int count = Integer.parseInt(header[0]);
            int target = Integer.parseInt(header[1]);
            int count1 = count ;

            MinPQ show1 = new MinPQ() ;
            Hand[] players = new Hand[1] ;
            int counter = 0 ;
            while(count != 0){
                String[] owncard = br.readLine().split("","") ;
                Card[] cards = new Card[5] ;
                for(int i = 0 ; i < 5 ; i++ ){
                    String[] eachcard = owncard[i].split(""_"");
                    cards[i] = new Card( eachcard[1] , eachcard[0] ) ;                    

                }
                players[0] = new Hand(cards) ;
                show1.insert(players[0]);
                if(show1.size()>target)
                    show1.delMin();

                count -- ;
                counter ++ ;
            }

            Hand finalans = Hand.class.cast(show1.delMin());
            Card[] fina = finalans.getCards() ;

                    for(int j = 0 ; j < 5 ; j++){
                    System.out.print(fina[j].getSuit()+
                            ""_""+
                            fina[j].getFace());
                    if(j!=4)
                        System.out.print("","") ;
                    
                    }

        }
    }
}

