import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Arrays;

public class Player implements Comparable<Player> {

    public Card[] cards = new Card[5];
    private String name;
    private String[] checkFace = new String[]{""2"", ""3"", ""4"", ""5"", ""6"", ""7"", ""8"", ""9"", ""10"", ""J"", ""Q"", ""K"", ""A""};
    private String[] checkSuit = new String[]{""Clubs"", ""Diamonds"", ""Hearts"", ""Spades""};
    private Card[] checkCardsFace = new Card[13];
    private Card[] checkCardsSuit = new Card[4];
    double[][] check = new double[5][2];

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

    public double[][] checkhandspoint(Card[] cards) {
        double[][] checkhandspoint = new double[cards.length][2];
        for (int k = 0; k < cards.length; k++) {
            for (int i = 0; i < 13; i++) {
                if (cards[k].getFace().compareTo(checkFace[i]) == 0) {
                    checkhandspoint[k][0] = i;
                    //System.out.println(i);
                    break;
                }
            }
            for (int i = 0; i < 4; i++) {
                if (cards[k].getSuit().compareTo(checkSuit[i]) == 0) {
                    checkhandspoint[k][1] = i;
                    checkhandspoint[k][1] = checkhandspoint[k][1] / 10;
                    //System.out.println(checkhandspoint[k][1]);
                    break;
                }
            }
        }
        return checkhandspoint;
    }

    public double checkhands(Card[] cards) {
        double handpoints = 0;
        Arrays.sort(cards);
        double[][] check = checkhandspoint(cards);
        /*
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 2; j++) {
                System.out.println(check[i][j]);
            }
        }
                */
        if (check[0][0] == check[1][0] && check[1][0] != check[2][0] && check[2][0] == check[3][0] && check[3][0] != check[4][0]) {
            handpoints = 2 * 13 + check[3][0] + check[3][1]; //two pair
            return handpoints;
        } else if (check[0][0] == check[1][0] && check[1][0] != check[2][0] && check[2][0] != check[3][0] && check[3][0] == check[4][0]) {
            handpoints = 2 * 13 + check[4][0] + check[4][1]; //two pair
            return handpoints;
        } else if (check[0][0] != check[1][0] && check[1][0] == check[2][0] && check[2][0] != check[3][0] && check[3][0] == check[4][0]) {
            handpoints = 2 * 13 + check[4][0] + check[4][1]; //two pair
            return handpoints;
        } else if (check[0][0] == check[1][0] && check[1][0] != check[2][0] && check[2][0] == check[3][0] && check[3][0] == check[4][0]) {
            handpoints = 5 * 13 + check[4][0] + check[4][1]; //full house
            return handpoints;
        } else if (check[0][0] == check[1][0] && check[1][0] == check[2][0] && check[2][0] != check[3][0] && check[3][0] == check[4][0]) {
            handpoints = 5 * 13 + check[2][0] + check[2][1]; //full house
            return handpoints;
        } else if (check[0][1] == check[1][1] && check[0][1] == check[2][1] && check[0][1] == check[3][1] && check[0][1] == check[4][1]) {
            handpoints = 4 * 13 + check[4][0] + check[4][1]; //flush
            return handpoints;
        } else if (check[4][0] - check[3][0] == check[3][0] - check[2][0] && check[3][0] - check[2][0] == check[2][0] - check[1][0] && check[2][0] - check[1][0] == check[1][0] - check[0][0]) {
            handpoints = 3 * 13 + check[4][0] + check[4][1];//Straight
            return handpoints;
        } else if (check[0][0] != check[1][0] && check[1][0] != check[2][0] && check[2][0] != check[3][0] && check[3][0] != check[4][0]) {
            handpoints = check[4][0] + check[4][1]; //high card
            return handpoints;
        } else if (check[3][0] == check[4][0]) {
            handpoints = 1 * 13 + check[4][0] + check[4][1]; //one pair
            return handpoints;
        } else if (check[2][0] == check[3][0]) {
            handpoints = 1 * 13 + check[3][0] + check[3][1]; //one pair
            return handpoints;
        } else if (check[1][0] == check[2][0]) {
            handpoints = 1 * 13 + check[2][0] + check[2][1]; //one pair
            return handpoints;
        } else if (check[0][0] == check[1][0]) {
            handpoints = 1 * 13 + check[1][0] + check[1][1]; //one pair
            return handpoints;
        }
        return handpoints;
    }

    // TODO 
    public int compareTo(Player that) {
        if (checkhands(this.cards) > checkhands(that.cards)) {
            return 1;
        } else if (checkhands(this.cards) == checkhands(that.cards)) {
            return 0;
        } else {
            return -1;
        }
    }
    /*

    public static void main(String[] args) throws Exception {

        try (BufferedReader br = new BufferedReader(new FileReader(""input.txt""))) {

            int idx = 0;
            int playerCount = Integer.parseInt(br.readLine());
            Player[] playerArray = new Player[playerCount];

            for (String in = br.readLine(); in != null; in = br.readLine()) {
                String name = in;
                Player player = new Player(name);
                playerArray[idx++] = player;

                Card[] cardsArray = new Card[5];
                String[] cardStr = br.readLine().split("","");
                for (int i = 0; i < 5; i++) {
                    String[] sep = cardStr[i].split(""_"");
                    Card card = new Card(sep[1], sep[0]);
                    cardsArray[i] = card;
                }
                player.setCards(cardsArray);

                System.out.println(player.checkhands(player.cards));
            }

            Arrays.sort(playerArray);
            System.out.println(playerArray[playerCount - 1].getName());
        }
    }
*/
}

