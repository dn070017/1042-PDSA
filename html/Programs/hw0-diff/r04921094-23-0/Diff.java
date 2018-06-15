package homework0;

import java.io.BufferedReader;
import java.io.FileReader;

public class Bingo {

	public static void main(String[] args) throws Exception {					// exception for BufferedReader
		// TODO Auto-generated method stub
		String[] numberData;				// register for number data
		String[] bingoString;			// bingo input string
		String[][] bingoMapString;		// bingo map string
		int numOfString;					// for bingo input string size 
		int sizeOfMatrix;				// for bingo map string size
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
		
		// TODO BingoMapString input format check
		for(int i = 0; i < sizeOfMatrix; i++){
			bingoMapString[i] = br.readLine().split("","");
			/*for(int j = 0; j < sizeOfMatrix; j++){
				System.out.print(bingoMapString[i][j] + "" "");
			}
			System.out.println();*/
		}
		
		bingoMap = new BingoMap(bingoMapString, sizeOfMatrix);
		bingoMap.inputString(bingoString, numOfString);
		System.out.println(bingoMap.result());
	}
}

class BingoMap{
	String[][] mapString;
	String[] inputString;
	int numOfString;
	int sizeOfMap;
	
	public BingoMap(String[][] mapString, int sizeOfMap){
		this.mapString = mapString;
		this.sizeOfMap = sizeOfMap;
	}
	
	void inputString(String[] inputString, int numOfString){
		this.inputString = inputString;
		this.numOfString = numOfString;
	}
	
	int result(){
		// TODO algorithm for output right answer
		return this.bingoAlg(digitallize());
	}
	
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
		/*
		for(int i = 0; i<sizeOfMap; i++){
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
		
		for(int i=0; i<sizeOfMap; i++){
			for(int j=0; j<sizeOfMap; j++){
				if(booleanMap[i][j] == false){
					resultMapFlag[0][i] = true;
					resultMapFlag[1][j] = true;
					if(i == j){
						resultMapFlag[2][0] = true;
					}
					else if(i == sizeOfMap - j){
						resultMapFlag[2][1] = true;
					}
				}
			}
		}
		
		for(boolean[] resultMapFlagRow: resultMapFlag){
			for(boolean resultFlag: resultMapFlagRow){
				if(resultFlag == false){
					result++;
				}
			}
		}
		
		return result;
	}
}

