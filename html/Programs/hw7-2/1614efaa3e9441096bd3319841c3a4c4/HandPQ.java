import java.io.BufferedReader;
import java.io.FileReader;

public class HandPQ {

    public static void main(String[] args) throws Exception {

       
         In in = new In(args[0]);
         String line = in.readLine();
         int players = Integer.parseInt(line.split("","")[0]);
         int win =Integer.parseInt(line.split("","")[1]);
         int playercount=0;
        
         Hand[] a = new Hand[players] ;
       //  for(int i=0;i<players;i++){
         //    a[i] = new Hand(String.valueOf(i));
         //}
          Hand[] winn = new Hand[win] ;
         //for(int i=0;i<win;i++){
           //  winn[i] = new Player(String.valueOf(i));
        // }
         
         while((line = in.readLine())!=null)
         {
           //  line = in.readLine();
             Card[] cards =new Card[5];
             String[] five = new String[5];
             five = line.split("","");
             cards[0] = new Card(five[0].split(""_"")[1],five[0].split(""_"")[0]);
             cards[1] = new Card(five[1].split(""_"")[1],five[1].split(""_"")[0]);
             cards[2] = new Card(five[2].split(""_"")[1],five[2].split(""_"")[0]);
             cards[3] = new Card(five[3].split(""_"")[1],five[3].split(""_"")[0]);
             cards[4] = new Card(five[4].split(""_"")[1],five[4].split(""_"")[0]);
             a[playercount] = new Hand(cards);
            // a[playercount].setCards(cards);  
            // System.out.println(playercount);
             if(playercount<win)
             {winn[playercount] = a[playercount];}
             else
             { MergeX.sort(winn);
             int uccu =a[playercount].compareTo(winn[0]);
             if(uccu==1)
             {winn[win-1]=a[playercount];}
             }
             playercount++;        
             
              
         }
        winn[1].getCards()[0].getFace();
        // MergeX.sort(winn[win-1].cards);
       System.out.println(winn[0].getCards()[0].getSuit()+""_""+winn[0].getCards()[0].getFace()+"",""+winn[0].getCards()[1].getSuit()+""_""+winn[0].getCards()[1].getFace()+"",""+winn[0].getCards()[2].getSuit()+""_""+winn[0].getCards()[2].getFace()+"",""+winn[0].getCards()[3].getSuit()+""_""+winn[0].getCards()[3].getFace()+"",""+winn[0].getCards()[4].getSuit()+""_""+winn[0].getCards()[4].getFace());
      
         //MergeX.sort(a);
    // Player[] c = new Player[2];
     //c[0] = new Player(""John Cena"") ;
     //c[1] = new Player(""undertaker"") ;
     //Card[] fuck = new Card[5];
     //Card[] fuck1 = new Card[5] ;
     
     
     //fuck1[0] = new Card(""4"",""Spades"") ;
     //fuck1[1] = new Card(""K"",""Hearts"") ;
     //fuck1[2] = new Card(""J"",""Clubs"") ;
     //fuck1[3] = new Card(""3"",""Spades"") ;
     //fuck1[4] = new Card(""Q"",""Hearts"") ;
     //c[1].setCards(fuck1);
     //c[0].setCards(fuck1);
     //MergeX.sort(c);
//     for (int i = 0 ; i < 5 ; i++){
 //    
//     System.out.println(a[1].cards[i].getFace());
//     }
//     System.out.println(a[0].compareTo(a[1]));
//     System.out.println(a[1].HighCard[0].compareTo(a[0].HighCard[0]));
}
    }


