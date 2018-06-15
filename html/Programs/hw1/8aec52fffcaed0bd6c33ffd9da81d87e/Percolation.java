import java.io.FileReader;
import java.io.BufferedReader;
import java.io.Array;
public class Bingo {
    public static void main(String[] args) throws Exception {
        try(BufferedReader br = new BufferedReader(new FileReader(args[0]))){
            // read a line and split by ','
            String[] data = br.readLine().split("","");
            // store the first integer in variable stringCount (number of announced strings)
            int N = Integer.parseInt(data[0]);
			int K ;
			K = (int)(Math.random()*N+1) ;
			System.out.printf(""%d"", K , ""%d"", N);
			 
    }
}

