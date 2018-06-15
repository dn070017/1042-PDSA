import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Arrays;

public class HandPQ {

    public static void main(String[] args) throws Exception {
    	MinPQ<Hand> pq = new MinPQ<Hand>();
    	
        try(BufferedReader br = new BufferedReader(new FileReader(args[0]))){
        	String[] header = br.readLine().split("","");
        		
        	int count = Integer.parseInt(header[0]);
            int target = Integer.parseInt(header[1]);
        	
            Hand[] handArray = new Hand[count];
        	
            Card card = null;
        	for(int j = 0; j < count ; j++){
        		Card[] cardsArray = new Card[5];
                String[] cardStr = br.readLine().split("","");
                for(int i = 0; i < 5; i++){
                    String[] sep = cardStr[i].split(""_"");
                    card = new Card(sep[1], sep[0]);
                    cardsArray[i] = card;
                }
                //Arrays.sort(cardsArray);
                Hand handtemp = new Hand(cardsArray);
                handArray[j] = handtemp;
                pq.insert(handtemp);
                if(pq.size()>target){
                	pq.delMin();
                }
        	}
//              Hand handele = new Hand(cardsArray);
//            	pq.insert(handele);
//            	Hand test = pq.min();
//            	Card[] testc = test.getCards();
//            	System.out.println(testc[4].getSuit());
//                if(pq.size() > target){
//                	Card[] mincard = pq.min().getCards();
//                    for(int i = 0 ; i < mincard.length ; i++){
//                    	System.out.print(mincard[i].getSuit() + ""_"" + mincard[i].getFace());
//                    	if(i!=(mincard.length-1)){
//                    		System.out.print("","");
//                    	}
//                    }
//                    pq.delMin();
//                	System.out.println(""last time"");
//                }

        	//for(int i = 0 ; i<pq.size(); i++){
        		if(!pq.isEmpty()){
        			Hand handt = pq.min();
        			Card[] crdt = handt.getCards();
                    Arrays.sort(crdt);
        			for(int j = 0 ; j<5; j++){
                    	System.out.print(crdt[j].getSuit()+""_""+crdt[j].getFace());
                    	if(j!=4){
                    		System.out.print("","");
                    	}
            		}
        		}
            	//System.out.println();
        	//}
    		//System.out.println(pq.size());
        	        	
        	
        	
//        	for(int j = 0; j < count ; j++){
//        		pq.insert(handArray[j]);
//        	}
//        	pq.delMin();
//        	for(int i = 0 ; i<pq.size(); i++){
//        		if(!pq.isEmpty()){
//        			Hand handt = pq.min();
//        			Card[] crdt = handt.getCards();
//        			for(int j = 0 ; j<5; j++){
//                    	System.out.print(crdt[j].getSuit()+""_""+crdt[j].getFace());
//                    	if(j!=4){
//                    		System.out.print("","");
//                    	}
//            		}
//        			pq.delMin();
//        		}
//            	System.out.println();
//        	}
//    		System.out.println(pq.size());
//        	System.out.println();
//
//
//        	//print all for test
//        	for(int i = 0 ; i<count; i++){
//        		Card[] crd = handArray[i].getCards();
//        		for(int j = 0 ; j<5; j++){
//                	System.out.print(crd[j].getSuit()+""_""+crd[j].getFace());
//                	if(j!=4){
//                		System.out.print("","");
//                	}
//        		}
//        		System.out.println();
//        	}
        	
//            pq.delMin();
//            Hand test = pq.min();
//            Card[] mincard = test.getCards();
//            for(int i = 0 ; i < mincard.length ; i++){
//            	System.out.print(mincard[i].getSuit() + ""_"" + mincard[i].getFace());
//            	if(i!=(mincard.length-1)){
//            		System.out.print("","");
//            	}
//            }
        }
    }
}

