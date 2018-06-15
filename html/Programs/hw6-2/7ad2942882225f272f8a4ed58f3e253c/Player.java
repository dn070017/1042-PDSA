
import java.util.Arrays;

public class Player implements Comparable<Player> {

    private Card[] cards = new Card[5];
    private String name;
    public int[] Points = new int[5];
    public int[] Points1 = new int[5];
    public int[] Points2 = new int[5];	

    // DO NOT MODIFY THIS

    public Player(String name) {
        this.name = name;
    }

    // DO NOT MODIFY THIS
    public String getName() {
        return this.name;
    }

    // DO NOT MODIFY THIS
    public void setCards(Card[] cards) {
        this.cards = cards;
    }

    // TODO 
    public int compareTo(Player that) {
.
        int outcome =0;
         //得到牌的所有數字
        for (int i = 0; i < 5; i++) {
            Points1[i] = GetPoint(this.cards[i]);
	    Points2[i] = GetPoint(that.cards[i]);	
        }
        Arrays.sort(Points1);
	Arrays.sort(Points2);
        if (compare(this) > compare(that)) {
            outcome = 1;
        } else if (compare(this) < compare(that)) {
            outcome = -1;
        } else if(compare(this) == compare(that)){
            if(compare(this) == 3 || compare(this) == 4 || compare(this) == 0) //straight or flush
		{ 
                    if(Points1[4] > Points2[4])
                    {
                        outcome = 1;
                    }
                    else if(Points1[4] == Points2[4])
                    {
                        outcome = 0;
                    }
                    else outcome = -1;
                }

           
//            else if(compare(this) == 5)  //full house
//                {
//                     if( fullhouse(Points1) > fullhouse(Points2) )
//                    {
//                        outcome = 1;
//                    }
//                    else if(fullhouse(Points1) == fullhouse(Points2))
//                    {
//                        outcome = 0;
//                    }
//                    else outcome = -1;
//                }
//            else if(compare(this) == 2 && compare(that)==2)  //two pair
//                {
//                     if( twopair(Points1) > twopair(Points2) )
//                    {
//                        outcome = 1;
//                    }
//                    else if(twopair(Points1) == twopair(Points2))
//                    {
//                        outcome = 0;
//                    }
//                    else outcome = -1;
//                }
//              //  if(compare(this) == 1)  //one pair
//            else if(compare(this) == 1 && compare(that) == 1)
//                {
//                     if( onepair(Points1) > onepair(Points2) )
//                    {
//                        outcome = 1;
//                    }
//                    else if(onepair(Points1) == onepair(Points2))
//                    {
//                        outcome = 0;
//                    }
//                    else outcome = -1;
//                }
            
        }
        return outcome ;
    }

    public int GetPoint(Card c) {
        int face = 0;
        switch (c.getFace()) {
            case ""A"":
                face = 1;
                break;
            case ""2"":
                face = 2;
                break;
            case ""3"":
                face = 3;
                break;
            case ""4"":
                face = 4;
                break;
            case ""5"":
                face = 5;
                break;
            case ""6"":
                face = 6;
                break;
            case ""7"":
                face = 7;
                break;
            case ""8"":
                face = 8;
                break;
            case ""9"":
                face = 9;
                break;
            case ""10"":
                face = 10;
                break;
            case ""J"":
                face = 11;
                break;
            case ""Q"":
                face = 12;
                break;
            case ""K"":
                face = 13;
                break;
        }
        return face;
    }

    public int compare(Player that) {
        int count = 0; 
        int Pair = 0;
        //sort the suit first  check if it is flush first
        Arrays.sort(that.cards, Card.SUIT_ORDER);
        for (int i = 0; i < 4; i++) {
            if (Card.SUIT_ORDER.compare(that.cards[i], that.cards[i + 1]) == 0) //flush
            {
                count++;
                if (count == 4) {
                    return 4;
                }
            }
        }
        count = 0;
        //得到牌的所有數字
        for (int i = 0; i < 5; i++) {
            Points[i] = GetPoint(that.cards[i]);
        }
        Arrays.sort(Points);
        for (int i = 0; i < 4; i++) //straight
        {
            if (Points[i] == Points[i + 1] - 1) {
                count++;
            }
        }
        if (count == 4) {
            return 3;
        }
        count = 0;
        // one pair two pair full house
        for (int i = 0; i < 4; i++) {
            if (Points[i] == Points[i + 1]) //one pair
            {
                Pair++;
            } else {
                Pair--;
            }

        }
        if (Pair == 2) {  //fullhouse
            return 5;
        } else if (Pair == 0) { //two pair
            return 2;
        } else if (Pair == -2) { //one pair
            return 1;
        } else {
            return 0;
        }
    }
    
    
    
    public int fullhouse(int [] c)
    { int count = 0; int flag = 0;
        for(int i = 0; i < 4; i++)
        {
            if(c[i] == c[i+1])
            {
                count++;
                if(count == 2)
                {
                    if(c[i] == 1)
                    {
                        flag = 100;
                    }
                    else flag = c[i];
                   break;
                }
            }
            else
            {
                count--;
            }
        
        }
        return flag;
    }
    public int judge(int [] c)
    { int pair = 0;
          for (int i = 0; i < 4; i++) {
            if (Points[i] == Points[i + 1]) //one pair
            {
                pair++;
            } else {
                pair--;
            }

        }
          return pair;
    }
    
    
     public int onepair(int [] c)
    { int count = 0; int flag = 0;
        for(int i = 0; i < 4; i++)
        {
            if(c[i] == c[i+1])
            {
                count++;
                if(count == 1)
                {
                   flag = c[i];
                   break;
                }
            }
             if(c[i] == c[i+1] && c[i] == 1)
            {
                flag = 100;break;
            }
        }
        return flag;
    }
     
      public int twopair(int [] c)
    { int count = 0; int flag = 0;
        for(int i = 0; i < 4; i++)
        {
            if(c[i] == c[i+1])
            {
                count++;
                if(count == 2)
                {
                   flag = c[i];
                   break;
                }
            }
             if(c[i] == c[i+1] && c[i] == 1)
            {
                flag = 100;break;
            }
        }
        return flag;
    }
}

