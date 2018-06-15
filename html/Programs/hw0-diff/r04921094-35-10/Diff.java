import java.io.BufferedReader;
import java.io.FileReader;

/**
 * @author yemengyuan
 *
 */
public class Bingo {

	public static void main(String[] args) throws Exception {					// exception for BufferedReader
		// TODO Auto-generated method stub
		String[] numberData;				// register for number data
		String[] bingoString;			// bingo input string
		String[][] bingoMapString;		// bingo map string
		String regString;				// BufferedReader register for input checking
		int numOfString;					// for bingo input string size 
		int sizeOfMatrix;				// for bingo map string size
		boolean inputCheckFlag = true;
		BingoMap bingoMap;	
		
		BufferedReader br = new BufferedReader(new FileReader(args[0]));		// get the filename from args
		numberData = br.readLine().split("","");
		numOfString = Integer.valueOf(numberData[0]);
		sizeOfMatrix = Integer.valueOf(numberData[1]);
		bingoString = new String[numOfString];
		bingoString = br.readLine().split("","");
		/*for(int i = 0; i < numOfString; i++){
			System.out.print(bingoString[i] + "" "");
		}*/
		bingoMapString = new String[sizeOfMatrix][sizeOfMatrix];
		
		/***************** Input Checke ************************/
		int i = 0;
		while((regString = br.readLine()) != null && regString.length() != 0 ){
			bingoMapString[i] = regString.split("","");
			if(bingoMapString[i].length != sizeOfMatrix){
				inputCheckFlag = false;
			}
			i++;
			/*for(int j = 0; j < sizeOfMatrix; j++){
				System.out.print(bingoMapString[i][j] + "" "");
			}
			System.out.println();*/
		}
		
		/************ Run Algorithm if input format is right*******************/
		if(inputCheckFlag == true && i == sizeOfMatrix){
			bingoMap = new BingoMap(bingoMapString, sizeOfMatrix);
			bingoMap.inputString(bingoString, numOfString);
			System.out.println(bingoMap.result());
		}
		else{
			System.out.println(""Please check the input file."");
		}
	}
}

/**
 * @author yemengyuan
 *
 */
class BingoMap{
	String[][] mapString;
	String[] inputString;
	int numOfString;
	int sizeOfMap;
	
	
	/**
	 * @param mapString: Bingo String Map
	 * @param sizeOfMap: We can use mapString.length() to replace. However, it is more convenience 
	 * to use the input file data. 
	 */
	public BingoMap(String[][] mapString, int sizeOfMap){
		this.mapString = mapString;
		this.sizeOfMap = sizeOfMap;
	}
	
	/**
	 * @param inputString: Bingo String Input
	 * @param numOfString: We can use inputString.length() to replace. However, it is more convenience 
	 * to use the input file data. 
	 */
	void inputString(String[] inputString, int numOfString){
		this.inputString = inputString;
		this.numOfString = numOfString;
	}
	
	/**
	 * Combination of bingoAlg() and digitallize()
	 * @return
	 */
	int result(){
		return this.bingoAlg(digitallize());
	}
	
	/**
.
	 * @return boolean[][] map
	 */
	boolean[][] digitallize (){
		boolean[][] booleanMap = new boolean[sizeOfMap][sizeOfMap];
		
		for(int i=0; i < sizeOfMap; i++){
			for(int j=0; j < sizeOfMap; j++){
				for(int k=0; k< numOfString; k++){
					if(booleanMap[i][j] == false)
						booleanMap[i][j] = mapString[i][j].equals(inputString[k]);
				}
			}
		}
		
		/*for(int i = 0; i<sizeOfMap; i++){
			for(int j = 0; j<sizeOfMap; j++)
				System.out.print(booleanMap[i][j]);
			System.out.println();
		}*/
		
		return booleanMap;
	}
	
	int bingoAlg(boolean[][] booleanMap){	
		int result = 0;
		boolean[][] resultMapFlag = new boolean[3][];		// flags for all map
		resultMapFlag[0] = new boolean[sizeOfMap];			// flags for column
		resultMapFlag[1] = new boolean[sizeOfMap];			// flags for row
		resultMapFlag[2] = new boolean[2];					// flags for diagonal
		
		/*
.
		 */
		for(int i=0; i<sizeOfMap; i++){
			for(int j=0; j<sizeOfMap; j++){
				if(booleanMap[i][j] == false){
					resultMapFlag[0][i] = true;
					resultMapFlag[1][j] = true;
					if(i == j){
						resultMapFlag[2][0] = true;
					}
					
					if(i + j == sizeOfMap - 1){
						resultMapFlag[2][1] = true;
						//System.out.println(""x:"" + i + ""; y:"" + j);
					}
				}
			}
		}
		
		/*
		 * Check all the resultMapFlag and return the result
		 */
		for(int i=0; i<resultMapFlag.length; i++){
			for(int j=0; j<resultMapFlag[i].length; j++){
				if(resultMapFlag[i][j] == false){
					result++;
					//System.out.println(""result index: i="" + i + ""; j="" + j);
				}
			}
		}
		
		return result;
	}
}

