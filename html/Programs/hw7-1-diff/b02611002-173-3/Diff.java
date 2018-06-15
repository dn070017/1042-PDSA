//import java.util.*;
//
//public class Hand implements Comparable<Hand> {
//
//    // sorted by Card value are recommended but not necessary
//    private Card[] cards;
//
//    private CardType cardType;
//    public CardQuery cardQuery = new CardQuery();
//    private static final CardTypeOrder CARD_TYPE_ORDER = new CardTypeOrder();
//
//    enum CardType {
//        full_house , flush , straight , two_pair , one_pair , high_card
//    }
//
//
//    // TODO, Judge System will call this constructor once for each hand
//    public Hand(Card[] cards){
//        this.cards = cards;
//        this.setCardType();
//    }
//
//    public CardType getCardType(){
//        return cardType;
//    }
//
//    private void setCardType(){
//        Map<String,Integer> faces = cardQuery.getFaceCount();
//        if (faces.size() == 2) {
//            // (4,1) or (3,2)
//            if (cardQuery.getThrees().size() != 0) {
//                cardType = CardType.full_house;
//                return;
//            }
//            cardType = CardType.high_card;
//        } else if (faces.size() == 3){
//            // (3,1,1) or (2,2,1)
//            if (cardQuery.getPairs().size() != 0) {
//                cardType = CardType.two_pair;
//                return;
//            }
//            cardType = CardType.high_card;
//        } else if (faces.size() == 4){
//            // (2,1,1,1)
//            cardType = CardType.one_pair;
//        } else if (faces.size() == 5){
//            // check for flush
//            if (cardQuery.getSuitSet().size() == 1) {
//                cardType = CardType.flush;
//                return;
//            }
//            // check for straight
//            String[] straights = {""A"",""K"",""Q"",""J"",""10"",""9"",""8"",""7"",""6"",""5"",""4"",""3"",""2"",""A""};
//            Set<String> faceSet = faces.keySet();
//            for(int i = 0; i < straights.length - 5 ; i++){
//                Set<String> straightOne = new HashSet<String>();
//                for(int j = i ; j < i + 5 ; j++){
//                    straightOne.add(straights[j]);
//                }
//                if (faceSet.containsAll(straightOne)) {
//                    cardType = CardType.straight;
//                    return;
//                }
//            }
//            // high card otherwise
//            cardType = CardType.high_card;
//        }
//    }
//
//    // TODO
//    public int compareTo(Hand that) {
//        int result = CARD_TYPE_ORDER.compare(this.getCardType(), that.getCardType());
//        if (result != 0){
//            return result;
//        } else {
//            // broke ties
//            switch (this.getCardType()) {
//                case full_house:
//                    Card c1 = Collections.max(this.cardQuery.getThrees());
//                    Card c2 = Collections.max(that.cardQuery.getThrees());
//                    return c1.compareTo(c2);
//
//                case two_pair:
//                case one_pair:
//                    c1 = Collections.max(this.cardQuery.getPairs());
//                    c2 = Collections.max(that.cardQuery.getPairs());
//                    return c1.compareTo(c2);
//
//                case high_card:
//                case straight:
//                case flush:
//                    c1 = Collections.max(this.cardQuery.getOnes());
//                    c2 = Collections.max(that.cardQuery.getOnes());
//                    return c1.compareTo(c2);
//            }
//        }
//        return 0;
//    }
//
//    // Do not modified this function
//    public Card[] getCards() { return this.cards; }
//
//
//    private static class CardTypeOrder implements Comparator<CardType> {
//        private static List<CardType> order;
//        CardTypeOrder() {
//            CardType[] cardTypes = {
//                    CardType.full_house,
//                    CardType.flush,
//                    CardType.straight,
//                    CardType.two_pair,
//                    CardType.one_pair,
//                    CardType.high_card
//            };
//            order = new ArrayList<CardType>();
//            for (CardType cardType : cardTypes) {
//                order.add(cardType);
//            }
//        }
//
//        @Override
//        public int compare(CardType cardType1, CardType cardType2) {
//            int cardType_1 = order.indexOf(cardType1);
//            int cardType_2 = order.indexOf(cardType2);
//            if (cardType_1 < cardType_2) return 1;
//            else if (cardType_1 > cardType_2) return -1;
//            else return 0;
//        }
//    }
//
//
//    private class CardQuery {
//
//        public Map<String , Integer> getFaceCount(){
//            Map<String,Integer> faces = new HashMap<String,Integer>();
//            for(Card c:cards){
//                if (faces.containsKey(c.getFace()))
//                    faces.put(c.getFace() , faces.get(c.getFace())+1);
//                else
//                    faces.put(c.getFace() , 1);
//            }
//            return faces;
//        }
//
//        public Set<String> getSuitSet(){
//            Set<String> suitSet = new HashSet<String>();
//            for (Card c:cards){
//                suitSet.add(c.getSuit());
//            }
//            return suitSet;
//        }
//
//        public Set<String> getSuitSet(String face){
//            Set<String> suitSet = new HashSet<String>();
//            for (Card c:cards){
//                if (face.equals(c.getFace()))
//                    suitSet.add(c.getSuit());
//            }
//            return suitSet;
//        }
//
//        public Set<Card> getOnes(){
//            Set<Card> ones = new HashSet<Card>();
//            Map<String,Integer> faces = getFaceCount();
//            for (Card c:cards){
//                if (faces.get(c.getFace()) == 1)
//                    ones.add(c);
//            }
//            return ones;
//        }
//
//        public Set<Card> getPairs(){
//            Set<Card> pairs = new HashSet<Card>();
//            Map<String,Integer> faces = getFaceCount();
//            for (Card c:cards){
//                if (faces.get(c.getFace()) == 2)
//                    pairs.add(c);
//            }
//            return pairs;
//        }
//
//        public Set<Card> getThrees(){
//            Set<Card> threes = new HashSet<Card>();
//            Map<String,Integer> faces = getFaceCount();
//            for (Card c:cards){
//                if (faces.get(c.getFace()) == 3)
//                    threes.add(c);
//            }
//            return threes;
//        }
//    }
//
//}
import java.util.Arrays;

public class Hand implements Comparable<Hand> {



    // sorted by Card value are recommended but not necessary
    private Card[] cards = new Card[5];



    // TODO, Judge System will call this constructor once for each hand
    public Hand(Card[] inputCards){
        this.cards=inputCards;
    }



    // Do not modified this function
    public Card[] getCards() { return this.cards; }



    // TODO
    public int compareTo(Hand that) {
.

//        System.out.println(""***START: ""+this.getCards()[4].getFace());

.

        boolean[] isFullHouse={false,false};
        int FHCounter=0;
        String[] FHThreeFace={""-1"",""-1""};//10 //9
        String[] FHTwoFace={""-1"",""-1""};//4 //A
        String[] tempFHTwoFace={""-1"",""-1""};

        boolean[] isFlush={false,false};
        int suitCombo[]={0,0};

        boolean[] isStraight={false,false};
        int faceCombo[]={0,0};

        boolean[] isTwoPair={false,false};

        boolean[] isOnePair={false,false};
        boolean isFirstCard=true;



        Card[] NThis = this.cards;  //cards in ""numerical"" expression (A->14, J->11, Q->12, K->13)
        Card[] NThat = that.cards;
        for(int i=0;i<NThis.length;i++){

            switch (NThis[i].getFace()){
                case ""A"": NThis[i]=new Card(""14"",NThis[i].getSuit());break;
                case ""J"": NThis[i]=new Card(""11"",NThis[i].getSuit());break;
                case ""Q"": NThis[i]=new Card(""12"",NThis[i].getSuit());break;
                case ""K"": NThis[i]=new Card(""13"",NThis[i].getSuit());break;
            }

            switch (NThat[i].getFace()){
                case ""A"": NThat[i]=new Card(""14"",NThat[i].getSuit());break;
                case ""J"": NThat[i]=new Card(""11"",NThat[i].getSuit());break;
                case ""Q"": NThat[i]=new Card(""12"",NThat[i].getSuit());break;
                case ""K"": NThat[i]=new Card(""13"",NThat[i].getSuit());break;
            }
        }


        Arrays.sort(NThis);
////        //TODO remove
//        System.out.println(""\n*Print NThis:"");
//        for(Card c:NThis){
//            System.out.println(c.getFace()+c.getSuit());
//        }
        Arrays.sort(NThat);
//        //TODO remove
//        System.out.println(""*Print NThat:"");
//        for(Card c:NThat){
//            System.out.println(c.getFace()+c.getSuit());
//        }


        int hID=0;
        Card[] currentHand = NThis;
        for(int i =0;i<2;i++){  // loop: NThis, NThat
            if(hID==1) currentHand=NThat;

            Card previous = new Card(""2"",""Clubs"");
            FHCounter=0;
            suitCombo[hID]=0;
            faceCombo[hID]=0;
            FHThreeFace[hID]=""-1"";
            FHTwoFace[hID]=""-1"";
            tempFHTwoFace[hID]=""-1"";

...

                //handle first card case
                if(isFirstCard){
                    FHCounter=1;
                    suitCombo[hID]=1;
                    faceCombo[hID]=1;
                    isFirstCard=false;
                    previous=c;
                    continue;
                }

                // FullHouse counter
                if (previous.getFace() == c.getFace()) FHCounter++;
                else FHCounter = 1;
                if (FHCounter == 3) {
                    FHThreeFace[hID] = c.getFace();
                    FHTwoFace[hID] = tempFHTwoFace[hID];
                } else if (FHCounter == 2) {
                    tempFHTwoFace[hID] = FHTwoFace[hID];
                    FHTwoFace[hID] = c.getFace();
                }

                // Flush counter
                if(previous.getSuit().equals(c.getSuit())){
                    suitCombo[hID]++;
                }

                // Straight counter
                if(Integer.parseInt(c.getFace())-Integer.parseInt(previous.getFace())==1){
                    faceCombo[hID]++;
                }

                previous=c;
...


            scores[hID]=Integer.parseInt(currentHand[4].getFace());

            if(!FHTwoFace[hID].equals(""-1"")){
                isOnePair[hID]=true;
                scores[hID]=20;
            }
            if(!tempFHTwoFace[hID].equals(""-1"") && !FHTwoFace[hID].equals(""-1"")){
                isTwoPair[hID]=true;
                scores[hID]=30;
            }
            if(faceCombo[hID]==5){
                isStraight[hID]=true;
                scores[hID]=40;
            }
            if(suitCombo[hID]==5){
                isFlush[hID]=true;
                scores[hID]=50;
            }
            if (!FHThreeFace[hID].equals(""-1"") && !FHTwoFace[hID].equals(""-1"")) {
                isFullHouse[hID]=true;
                scores[hID]=60;
            }
            hID++;
            isFirstCard=true;

        }// loop: NThis, NThat







        if(isFullHouse[0]&&isFullHouse[1]){
            if(Integer.parseInt(FHThreeFace[0])>Integer.parseInt(FHThreeFace[1])){
                scores[0]+=6;
            }else if(Integer.parseInt(FHThreeFace[0])<Integer.parseInt(FHThreeFace[1])){
                scores[1]+=6;
            }
        }else if(isFlush[0]&&isFlush[1]){
            if(Integer.parseInt(NThis[4].getFace())>Integer.parseInt(NThat[4].getFace())){
                scores[0]+=5;
            }else if(Integer.parseInt(NThis[4].getFace())<Integer.parseInt(NThat[4].getFace())){
                scores[1]+=5;
            }
        }else if(isStraight[0]&&isStraight[1]){
            if(Integer.parseInt(NThis[4].getFace())==14)scores[0]-=3;
            if(Integer.parseInt(NThat[4].getFace())==14)scores[0]-=3;
            if(Integer.parseInt(NThis[0].getFace())>Integer.parseInt(NThat[0].getFace())){
                scores[0]+=3;
            }else if(Integer.parseInt(NThis[0].getFace())<Integer.parseInt(NThat[0].getFace())) {
                scores[1]+=3;
            }
        }else if(isTwoPair[0] && isTwoPair[1]){
            if(Integer.parseInt(FHTwoFace[0])>Integer.parseInt(FHTwoFace[1])){
                scores[0]+=3;
            }else if (Integer.parseInt(FHTwoFace[0])>Integer.parseInt(FHTwoFace[1])){
                scores[1]+=3;
            }
        }else if(isOnePair[0] && isOnePair[1]){
            if(Integer.parseInt(FHTwoFace[0])>Integer.parseInt(FHTwoFace[1])){
                scores[0]+=2;
            }else if (Integer.parseInt(FHTwoFace[0])>Integer.parseInt(FHTwoFace[1])){
                scores[1]+=2;
            }
        }else if(NThis[4].getFace().equals(NThat[4].getFace())){
            for(int i=4;i>=0;i--) {
                if (NThis[i].compareTo(NThat[i]) > 0) {
                    scores[0]++;
                    break;
                } else if (NThis[i].compareTo(NThat[i]) < 0) {
                    scores[1]++;
                    break;
                }
            }
        }

//        System.out.println(""***END: ""+this.getCards()[4].getFace());


        for(int i=0;i<NThis.length;i++){

            switch (NThis[i].getFace()){
                case ""14"": NThis[i]=new Card(""A"",NThis[i].getSuit());break;
                case ""11"": NThis[i]=new Card(""J"",NThis[i].getSuit());break;
                case ""12"": NThis[i]=new Card(""Q"",NThis[i].getSuit());break;
                case ""13"": NThis[i]=new Card(""K"",NThis[i].getSuit());break;
            }

            switch (NThat[i].getFace()){
                case ""14"": NThat[i]=new Card(""A"",NThat[i].getSuit());break;
                case ""11"": NThat[i]=new Card(""J"",NThat[i].getSuit());break;
                case ""12"": NThat[i]=new Card(""Q"",NThat[i].getSuit());break;
                case ""13"": NThat[i]=new Card(""K"",NThat[i].getSuit());break;
            }
        }


//        System.out.printf(""\n*** compareTo return: %d (%d-%d)\n\n"",(scores[0]-scores[1]),scores[0],scores[1]);
        return scores[0]-scores[1];
    }
}

