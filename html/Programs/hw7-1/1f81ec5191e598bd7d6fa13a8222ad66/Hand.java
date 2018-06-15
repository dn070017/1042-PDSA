
import java.util.Arrays;

public class Hand implements Comparable<Hand> {

    // sorted by Card value are recommended but not necessary
    private Card[] cards; 

    // TODO, Judge System will call this constructor once for each hand
    public Hand(Card[] cards){
        Arrays.sort(cards);
        this.cards = cards;
    }

    public int[] getSequence_num(Card[] cards){
        
        int[] sequence_num = new int[2];
        StringBuilder card_face = new StringBuilder();
        for(int i=0;i<5;i++){
            card_face.append(cards[i].getFace());
        }
        //full house 
        if( cards[0].getFace().equals(cards[1].getFace()) ){  //AAOOO
            
            if( cards[1].getFace().equals(cards[2].getFace()) && 
                cards[3].getFace().equals(cards[4].getFace())){       
                sequence_num[0] = 5;
                sequence_num[1] = 1;
//                System.out.printf(""full house type_1  AAABB\n"");
                return sequence_num;
            }
            else if( cards[2].getFace().equals(cards[3].getFace()) && 
                     cards[3].getFace().equals(cards[4].getFace())){  
                sequence_num[0] = 5;
                sequence_num[1] = 2;
//                System.out.printf(""full house type_2  AABBB\n"");
                return sequence_num;
            }  
        }
        //flush
        if( (cards[0].getSuit().equals(cards[1].getSuit())) &&
            (cards[1].getSuit().equals(cards[2].getSuit())) &&
            (cards[2].getSuit().equals(cards[3].getSuit())) &&    
            (cards[3].getSuit().equals(cards[4].getSuit())) ){
            sequence_num[0] = 4;
            switch(cards[0].getSuit()){
                case ""Clubs"":    sequence_num[1] = 1; System.out.printf(""flush_Clubs\n""); return sequence_num;
                case ""Diamonds"": sequence_num[1] = 2; System.out.printf(""flush_Diamonds\n""); return sequence_num;   
                case ""Hearts"":   sequence_num[1] = 3; System.out.printf(""flush_Hearts\n""); return sequence_num;
                case ""Spades"":   sequence_num[1] = 4; System.out.printf(""flush_Spades\n""); return sequence_num;
                default: System.out.printf(""suit_input wrong\n"");
            }             
        }
        //Straight
        switch(card_face.toString()){
            case ""A2345"": sequence_num[0] = 3; sequence_num[1] = 1; System.out.printf(""Straight A2345\n""); return sequence_num;
            case ""23456"": sequence_num[0] = 3; sequence_num[1] = 2; System.out.printf(""Straight 23456\n""); return sequence_num;
            case ""34567"": sequence_num[0] = 3; sequence_num[1] = 3; System.out.printf(""Straight 34567\n""); return sequence_num;    
            case ""45678"": sequence_num[0] = 3; sequence_num[1] = 4; System.out.printf(""Straight 45678\n""); return sequence_num;
            case ""56789"": sequence_num[0] = 3; sequence_num[1] = 5; System.out.printf(""Straight 56789\n""); return sequence_num;    
            case ""678910"":sequence_num[0] = 3; sequence_num[1] = 6; System.out.printf(""Straight 678910\n""); return sequence_num;
            case ""78910J"":sequence_num[0] = 3; sequence_num[1] = 7; System.out.printf(""Straight 78910J\n""); return sequence_num;
            case ""8910JQ"":sequence_num[0] = 3; sequence_num[1] = 8; System.out.printf(""Straight 8910JQ\n""); return sequence_num;
            case ""910JQK"":sequence_num[0] = 3; sequence_num[1] = 9; System.out.printf(""Straight 910JQK\n""); return sequence_num;
            case ""10JQKA"":sequence_num[0] = 3; sequence_num[1] = 10; System.out.printf(""Straight 10JQKA\n""); return sequence_num;
        }
        
        if( cards[0].getFace().equals(cards[1].getFace()) ){  //AAOOO           
            if( cards[2].getFace().equals(cards[3].getFace()) &&
             !(cards[3].getFace().equals(cards[4].getFace())) ){  
                sequence_num[0] = 2;
                sequence_num[1] = 1;
                System.out.printf(""two pairs type_1  AABBC\n"");
                return sequence_num;
            }
            else if( cards[3].getFace().equals(cards[4].getFace()) &&
                   !(cards[2].getFace().equals(cards[3].getFace())) ){  
                sequence_num[0] = 2;
                sequence_num[1] = 2;
                System.out.printf(""two pairs type_2  AABCC\n"");
                return sequence_num;
            }
            else{  //AABCD
                sequence_num[0] = 1;
                sequence_num[1] = 1;
                System.out.printf(""one pair type_1  AABCD\n"");
                return sequence_num;
            }
        }
        else if(cards[1].getFace().equals(cards[2].getFace())){  //OAAOO
            
            if( cards[3].getFace().equals(cards[4].getFace()) &&
              !(cards[2].getFace().equals(cards[3].getFace())) ){  
                sequence_num[0] = 2;
                sequence_num[1] = 3;
                System.out.printf(""two pairs type_3  ABBCC\n"");
                return sequence_num;
            }
            else{   //ABBCD
                sequence_num[0] = 1;
                sequence_num[1] = 2;
                System.out.printf(""one pair type_2  ABBCD\n"");
                return sequence_num;
            }
        }
        else if( cards[2].getFace().equals(cards[3].getFace())){ //ABCCD
            sequence_num[0] = 1;
            sequence_num[1] = 3;
            System.out.printf(""one pair type_3  ABBCD\n"");
            return sequence_num;
        }
        else if( cards[3].getFace().equals(cards[4].getFace())){ //ABCDD
            sequence_num[0] = 1;
            sequence_num[1] = 4;
            System.out.printf(""one pair type_4  ABCDD\n"");
            return sequence_num;
        }
        else{  //faces are distinct
            sequence_num[0] = 0; 
            sequence_num[1] = 0;
            System.out.printf(""High card\n""); 
            return sequence_num;
        }
    }
   
    // TODO
    public int compareTo(Hand that) {
        
.
        int[][] hand_sequence_num = new int[2][2];
        hand_sequence_num[0] = getSequence_num(this.getCards());
        hand_sequence_num[1] = getSequence_num(that.getCards());
        
        if( hand_sequence_num[0][0] < hand_sequence_num[1][0]) return -1;
        if( hand_sequence_num[0][0] > hand_sequence_num[1][0]) return +1;
        // Cases below are those player_sequence_num[0][0] = player_sequence_num[1][0]
        Card[] key_card = new Card[2];
        switch(hand_sequence_num[0][0]){   //sequence level are the same
            case 5:  //full house
                if(hand_sequence_num[0][1] == 1 )   {key_card[0] = this.cards[2];}
                else if(hand_sequence_num[0][1] ==2){key_card[0] = this.cards[4];}
                if(hand_sequence_num[1][1] == 1 )   {key_card[1] = that.cards[2];}
                else if(hand_sequence_num[1][1] ==2){key_card[1] = that.cards[4];}
                
                if( key_card[0].compareTo(key_card[1]) < 0) return -1;
                if( key_card[0].compareTo(key_card[1]) > 0) return +1;
            
            case 4:  //flush
                key_card[0] = this.cards[4];
                key_card[1] = that.cards[4];
                
                if( key_card[0].compareTo(key_card[1]) < 0) return -1;
                if( key_card[0].compareTo(key_card[1]) > 0) return +1;
                
            case 3:  //straight
                if( hand_sequence_num[0][1] < hand_sequence_num[1][1]) return -1;
                if( hand_sequence_num[0][1] > hand_sequence_num[1][1]) return +1;
                //player_sequence_num[0][1] = player_sequence_num[1][1]
                key_card[0] = this.cards[4];
                key_card[1] = that.cards[4];
                if( key_card[0].compareTo(key_card[1]) < 0) return -1;
                if( key_card[0].compareTo(key_card[1]) > 0) return +1;
                
            case 2:  //two pairs
                switch(hand_sequence_num[0][1]){
                    case 1:  //AABBC
                        key_card[0] = this.cards[3]; break;
                    case 2:  //AABCC
                        key_card[0] = this.cards[4]; break;
                    case 3:  //ABBCC
                        key_card[0] = this.cards[4]; break;
                }
                switch(hand_sequence_num[1][1]){
                    case 1:  //AABBC
                        key_card[1] = that.cards[3]; break;
                    case 2:  //AABCC
                        key_card[1] = that.cards[4]; break;
                    case 3:  //ABBCC
                        key_card[1] = that.cards[4]; break;
                }
                if( key_card[0].compareTo(key_card[1]) < 0) return -1;
                if( key_card[0].compareTo(key_card[1]) > 0) return +1;
                
            case 1:  //one pair
                switch(hand_sequence_num[0][1]){
                    case 1:  //AABCD
                        key_card[0] = this.cards[1]; break;
                    case 2:  //ABBCD
                        key_card[0] = this.cards[2]; break;
                    case 3:  //ABCCD
                        key_card[0] = this.cards[3]; break;
                    case 4:  //ABCDD
                        key_card[0] = this.cards[4]; break;
                }
                switch(hand_sequence_num[1][1]){
                    case 1:  //AABCD
                        key_card[1] = that.cards[1]; break;
                    case 2:  //ABBCD
                        key_card[1] = that.cards[2]; break;
                    case 3:  //ABCCD
                        key_card[1] = that.cards[3]; break;
                    case 4:  //ABCDD
                        key_card[1] = that.cards[4]; break;
                }
                if( key_card[0].compareTo(key_card[1]) < 0) return -1;
                if( key_card[0].compareTo(key_card[1]) > 0) return +1;
                
            case 0:  //high card
                key_card[0] = this.cards[4];
                key_card[1] = that.cards[4];
                
                if( key_card[0].compareTo(key_card[1]) < 0) return -1;
                if( key_card[0].compareTo(key_card[1]) > 0) return +1;
        }
        return 0;
    }

      // Do not modified this function
    public Card[] getCards() { return this.cards; }
}

