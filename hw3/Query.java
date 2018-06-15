import java.io.BufferedReader;
import java.io.FileReader;

/**
 * Created by Hsieh on 2016/3/16.
 */
public class Query {
    public static void main(String[] args) throws Exception{
        try(BufferedReader br = new BufferedReader(new FileReader(args[0]))){

            int num = Integer.parseInt(br.readLine());

            for(String in = br.readLine(); in != null; in = br.readLine()){
                Calculator cct = new Calculator();
                double ans = cct.ans(in);
                System.out.printf("%.2f\n", ans);
            }
        }
    }
}
