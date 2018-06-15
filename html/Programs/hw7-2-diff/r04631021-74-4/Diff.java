import java.io.BufferedReader;
import java.io.FileReader;

public class HandPQ {

    public static void main(String[] args) throws Exception {

        try(BufferedReader br = new BufferedReader(new FileReader(args[0]))){

            String[] header = br.readLine().split("","");
            int count = Integer.parseInt(header[0]);
            int target = Integer.parseInt(header[1]);
            int count1 = count ;
            MaxPQ show = new MaxPQ() ;
            MinPQ show1 = new MinPQ() ;
            Hand[] players = new Hand[count] ;
            int counter = 0 ;
            while(count != 0){
                String[] owncard = br.readLine().split("","") ;
                Card[] cards = new Card[5] ;
                for(int i = 0 ; i < 5 ; i++ ){
                    String[] eachcard = owncard[i].split(""_"");
                    cards[i] = new Card( eachcard[1] , eachcard[0] ) ;                    
//                    System.out.println(cards[i].getFace() + """" +cards[i].getSuit());
                }
                players[counter] = new Hand(cards) ;
                show1.insert(players[counter]);
                if(show1.size()>target)
                    show1.delMin();
//                owncard[count].split(""_"") ;
//                System.out.println(owncard) ;
                count -- ;
                counter ++ ;
            }
            while(!show1.isEmpty()){
                show.insert(show1.delMin());
            }
            
            while(target!=1){
                show.delMax();
                target-- ;
            }
         Object answer = show.delMax();
         for(int i= 0 ; i < count1 ; i++){
            if(answer.equals(players[i]))
              for(int j = 0 ; j < 5 ; j++){
                    System.out.print(players[i].getCards()[j].getSuit()+
                            ""_""+
                            players[i].getCards()[j].getFace()) ;
                    if(j!=4)
                        System.out.print("","") ;
                }
        }
        }
    }
}

