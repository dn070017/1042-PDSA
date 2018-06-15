import java.util.Arrays;
import java.util.Comparator;
import java.io.FileReader;
import java.io.BufferedReader;

public class Player implements Comparable<Player>{ 
	// HAND {full house > flush > straight > two pair > one pair > high card}
	private String[] FACE = {""2"", ""3"", ""4"", ""5"", ""6"", ""7"", ""8"", ""9"", ""10"", ""J"", ""Q"", ""K"", ""A""};
	private String[] SUIT = {""Clubs"", ""Diamonds"", ""Hearts"", ""Spades""};	
    private Card[] cards = new Card[5];
    private String name;
     
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
        if(IsFH(this.cards) > IsFH(that.cards))      return  1;
		else if(IsFH(this.cards) < IsFH(that.cards)) return -1;
		else if(IsFl(this.cards) > IsFl(that.cards)) return  1;
		else if(IsFl(this.cards) < IsFl(that.cards)) return -1;
		else if(IsSt(this.cards) > IsSt(that.cards)) return  1;
		else if(IsSt(this.cards) < IsSt(that.cards)) return -1;
		else if(Is2P(this.cards) > Is2P(that.cards)) return  1;
		else if(Is2P(this.cards) < Is2P(that.cards)) return -1;
		else if(Is1P(this.cards) > Is1P(that.cards)) return  1;
		else if(Is1P(this.cards) < Is1P(that.cards)) return -1;
		else if(IsHC(this.cards) > IsHC(that.cards)) return  1;
		else if(IsHC(this.cards) < IsHC(that.cards)) return -1;
		else if(IsHS(this.cards) > IsHS(that.cards)) return  1;
		else if(IsHS(this.cards) < IsHS(that.cards)) return -1;
		else                                         return  0;
    }
	
	private int[] SF2IF(Card[] cards){
		int n = cards.length;
		int[] face = new int[n];
		for(int i = 0; i < n; i++){
			for(int j = 0; j < 13; j++){
				if(cards[i].getFace().equals(FACE[j])) face[i] = j;
			}
		}
		/*System.out.println(""==============="");
		for(Integer i : face){
			System.out.println(i);
		}
		System.out.println(""==============="");*/
		return face;
	}
	
	private int IsFH(Card[] cards){
		int[] face = SF2IF(cards);
		if (face[0] == face[1] && face[1] == face[2]){
			if  (face[3] == face[4])return  face[0];
			else                    return       -1;
		}else if (face[2] == face[3] && face[3] == face[4]){
			if (face[0] == face[1]) return  face[2];
			else                    return       -1;
		}else{
			return -1;
		}
	}
	
	private int IsFl(Card[] cards){
		int[] face = SF2IF(cards);
		//Card c = new Card(""2"", ""Clubs"");
		Arrays.sort(cards, cards[0].SUIT_ORDER);
		if (cards[0].getSuit().equals(cards[4].getSuit())){
			Arrays.sort(cards);
			return  face[4];
		}else{
			Arrays.sort(cards);
			return       -1;
		}
	}
	
	private int IsSt(Card[] cards){
		int[] face = SF2IF(cards);
		if(face[4] == 12 && face[0] == 0){  //2(0) 3(1) 4(2) 5(3) A(12)
			if(face[1] == 1 && face[2] == 2 && face[3] == 3) return  0;
			else                                           return -1;
		}else{
			for(int i = 1; i < 5; i++){
				if(face[i] - face[i-1] != 1) break;
				if(i == 4) return face[4];
			}
		}
		return -1;
	}
	
	private int Is2P(Card[] cards){
		int[] face = SF2IF(cards);
		if(face[0] == face[1]){
			if(face[2] == face[3]) return face[2];
			if(face[3] == face[4]) return face[3];
		}else if(face[1] == face[2]){
			if(face[3] == face[4]) return face[3];
		}
		return -1;
	}
	
	private int Is1P(Card[] cards){
		int[] face = SF2IF(cards);
		if(face[0] == face[1]) return face[0];
		if(face[1] == face[2]) return face[1];
		if(face[2] == face[3]) return face[2];
		if(face[3] == face[4]) return face[3];
		return -1;
	}
	
	private int IsHC(Card[] cards){
		int[] face = SF2IF(cards);
		return face[4];
	}

	private int IsHS(Card[] cards){
		int s = 0;
		for(int i = 0; i < 4; i++){
			if(cards[4].getSuit().equals(SUIT[i])) s = i;
		}
		return -s;
	}
	
	public void Hand(){
		if(IsFH(cards)>0)      System.out.println(""FH"");
		else if(IsFl(cards)>=0) System.out.println(""Fl"");
		else if(IsSt(cards)>=0) System.out.println(""St"");
		else if(Is2P(cards)>=0) System.out.println(""2P"");
		else if(Is1P(cards)>=0) System.out.println(""1P"");
		else if(IsHC(cards)>=0) System.out.println(""HC"");
		else if(IsHS(cards)>=0) System.out.println(""FH"");
	}
	
	public static void main(String[] args) throws Exception{
        try (BufferedReader br = new BufferedReader(new FileReader(args[0]))) {
            int idx = 0;
            int playerCount = Integer.parseInt(br.readLine());
            Player[] playerArray = new Player[playerCount];

            for(String in = br.readLine(); in != null; in = br.readLine()) {
                String name = in;
                Player player = new Player(name);
                playerArray[idx++] = player;

                Card[] cardsArray = new Card[5];
                String[] cardStr = br.readLine().split("","");
                for(int i = 0; i < 5; i++){
                    String[] sep = cardStr[i].split(""_"");
                    Card card = new Card(sep[1], sep[0]);
                    cardsArray[i] = card;
                }
                player.setCards(cardsArray);
            }

			/*for(int i=0 ; i<playerArray.length; i++){
				playerArray[i].Hand();
			}*/
			
            Arrays.sort(playerArray);

            System.out.println(playerArray[playerCount - 1].getName());
        }
    }
}


