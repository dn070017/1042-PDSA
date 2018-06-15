import java.util.Arrays;
import java.util.Comparator;
import java.io.FileReader;
import java.io.BufferedReader;

public class Hand implements Comparable<Hand> {
	
	private String[]  FACE = {""2"", ""3"", ""4"", ""5"", ""6"", ""7"", ""8"", ""9"", ""10"", ""J"", ""Q"", ""K"", ""A""};
	private String[]  SUIT = {""Clubs"", ""Diamonds"", ""Hearts"", ""Spades""};	
    private int[] face;
	private int[] suit;
	// sorted by Card value are recommended but not necessary
    private Card[] cards; 
	private int[]  hand;
	
    // TODO, Judge System will call this constructor once for each hand
    public Hand(Card[] cards){
        hand = new int[3];
		Arrays.sort(cards);
		this.face = SF2IF(cards);
		this.suit = SS2IF(cards);
		Hand_D();
		return;
    }

    // TODO
    public int compareTo(Hand that) {
        if     (this.hand[0] > that.hand[0]) return  1;
		else if(this.hand[0] < that.hand[0]) return -1;
		else if(this.hand[1] > that.hand[1]) return  1;
		else if(this.hand[1] < that.hand[1]) return -1;
		else if(this.hand[2] > that.hand[2]) return  1;
		else if(this.hand[2] < that.hand[2]) return -1;
		return 0;
    }

      // Do not modified this function
    public Card[] getCards() { return this.cards; }
	
	public void get_hand() {
		System.out.printf(""%2d %2s %8s"", hand[0], FACE[hand[1]], SUIT[hand[2]]);
		System.out.println();
	}
	
	private int[] SF2IF(Card cards[]){
		int n = cards.length;
		int[] face = new int[n];
		for(int i = 0; i < n; i++){
			for(int j = 0; j < 13; j++){
				if(cards[i].getFace().equals(FACE[j])) face[i] = j;
			}
		}
		return face;
	}
	
	private int[] SS2IF(Card cards[]){
		int n = cards.length;
		int[] suit = new int[n];
		for(int i = 0; i < n; i++){
			for(int j = 0; j < 4; j++){
				if(cards[i].getSuit().equals(SUIT[j])) suit[i] = j;
			}
		}
		return suit;
	}
	
	private int IsFH(){
		if (this.face[0] == this.face[1] && this.face[1] == this.face[2]){
			if  (this.face[3] == this.face[4]) return   0;
			else                               return  -1;
		}else if (this.face[2] == this.face[3] && this.face[3] == this.face[4]){
			if  (this.face[0] == this.face[1]) return   2;
			else                               return  -1;
		}else{
			return -1;
		}
	}
	
	private int IsFl(){
		for(int i=0; i<4; i++){
			if(this.suit[i] != this.suit[i+1]) return -1;
		}
		return 4;
	}
	
	private int IsSt(){
		if(this.face[4] == 12 && this.face[0] == 0){  //2(0) 3(1) 4(2) 5(3) A(12)
			if(this.face[1] == 1 && this.face[2] == 2 && this.face[3] == 3) return  0;
			else                                                            return -1;
		}else{
			for(int i = 1; i < 5; i++){
				if(this.face[i] - this.face[i-1] != 1) break;
				if(i == 4)                             return 4;
			}
		}
		return -1;
	}
	
	private int Is2P(){
		if(this.face[0] == this.face[1]){
			if(this.face[2] == this.face[3]) return 3;
			if(this.face[3] == this.face[4]) return 4;
		}else if(this.face[1] == this.face[2]){
			if(this.face[3] == this.face[4]) return 4;
		}
		return -1;
	}
	
	private int Is1P(){
		if(this.face[0] == this.face[1]) return 1;
		if(this.face[1] == this.face[2]) return 2;
		if(this.face[2] == this.face[3]) return 3;
		if(this.face[3] == this.face[4]) return 4;
		return -1;
	}
	
	public void Hand_D(){
		int i = -1;
		int h = -1;
		if     (IsFH()>=0) {h = 5;i = IsFH();}
		else if(IsFl()>=0) {h = 4;i = IsFl();}
		else if(IsSt()>=0) {h = 3;i = IsSt();}
		else if(Is2P()>=0) {h = 2;i = Is2P();}
		else if(Is1P()>=0) {h = 1;i = Is1P();}
		else               {h = 0;i = 4;}
		this.hand[0] = h;
		this.hand[1] = face[i];
		this.hand[2] = suit[i];
	}
	
	
	public static void main(String[] args) throws Exception{
        try (BufferedReader br = new BufferedReader(new FileReader(args[0]))) {
            int idx = 0;
			String[] s = br.readLine().split("","");
            int handCount = Integer.parseInt(s[0]);
			int handNum   = Integer.parseInt(s[1]);
            
			Hand[] handArray = new Hand[handCount];
			
            for(String in = br.readLine(); in != null; in = br.readLine()) {
                Card[] cardsArray = new Card[5];
                String[] cardStr = in.split("","");
                for(int i = 0; i < 5; i++){
                    String[] sep = cardStr[i].split(""_"");
                    Card card = new Card(sep[1], sep[0]);
                    cardsArray[i] = card;
                }
				handArray[idx++] = new Hand(cardsArray);
            }

			System.out.println(""Before arrange"");
			for(int i=0; i<handCount; i++){
				handArray[i].get_hand();
			}
			
			Arrays.sort(handArray);
			
			System.out.println(""After arrange"");
			for(int i=handCount-1; i>-1; i--){
				handArray[i].get_hand();
			}
		}
    }
}

