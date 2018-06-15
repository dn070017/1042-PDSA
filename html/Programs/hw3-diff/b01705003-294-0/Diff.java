import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;
public class test {
    public static void main(String [] argv) throws IOException {
        FileReader fr = new FileReader(""FileName"");
        BufferedReader br = newBufferedReader(fr);
        while (br.ready()) {
            System.out.println(br.readLine());
        }
        fr.close();
    }
}

