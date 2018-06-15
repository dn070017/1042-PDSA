import java.io.FileReader;
import java.io.BufferedReader;



public class Percolation {

    public static void main(String[] args) throws Exception {

        // read file from args[0] in Java 7 style
        try (BufferedReader br = new BufferedReader(new FileReader(args[0]))) {

            // read a line and split by ','
            String data = br.readLine();

            // store the first integer in variable stringCount (number of announced strings)
            int size = Integer.parseInt(data);

            // store the second integer in variable num (dimension of matrix: num * num)            
            // initilization of a String array in Java
            // printf in Java (you should comment out or delete this in your final submission)
            //System.out.printf(""number of announced strings: %d\ndimension of matrix: %d x %d\n"", stringCount, num, num);
            String open;

            int num = 0;
            String openstring = """";
            while ((open = br.readLine()) != null) { //readLine()依序讀取檔案內的一行文字
                openstring = openstring + open + "" "";
                ++num;  //每讀一行，num就加1
            }
            String [] temp = new String[num];
            String [] temp2 = new String[num];
            int [][] match = new int[num][2];
            temp = openstring.split("" "");
            for(int i =0;i<num;++i){
                temp2 = temp[i].split("","");
                for(int j=0;j<2;++j){
                    match[i][j] = Integer.parseInt(temp2[j]);
                }
            }
            
           
            //System.out.println(size);  //N by N grid
            //System.out.println(num);  //print出file.txt內的行數
            
            int identity =0;
            int[] line = new int[size*size+2];
            int[] lineopen = new int[size*size+2];

            for (int i = 0; i < size*size+2; ++i) {
                    lineopen[i]=0;
                    line[i] = identity;
                    ++identity;
            }
            lineopen[0] = 0;  //upper pseudo node
            lineopen[size*size+1] = 1; // lower pseudo node
            int percolation = 0;
            WeightedQuickUnionUF wei = new WeightedQuickUnionUF(size*size+2);
            int test=0;
            for(int i = 0;i<num;i++){
                test = (match[i][0]-1)*size+match[i][1];
                lineopen[test]=1;
                
                if(test-size<1){
                    wei.union(line[test], line[0]);
                }
                else{
                    if(lineopen[test-size]==1) wei.union(line[test], line[test-size]);
                }
                
                if(test+size>size*size){
                    wei.union(line[test], line[size*size+1]);
                }
                else{
                    if(lineopen[test+size]==1) wei.union(line[test], line[test+size]);
                }
                
                if(test%size!=1){
                    if(lineopen[test-1]==1) wei.union(line[test], line[test-1]);
                }
                
                if(test%size!=0){
                    if(lineopen[test+1]==1) wei.union(line[test], line[test+1]);
                }
                
               
                
                if(wei.connected(line[0], line[size*size+1])==true){
                    System.out.println(match[i][0]+"",""+match[i][1]);
                    percolation = 1;
                    break;
                }
                
            }
           

          if(percolation==0){
              System.out.println(-1);
          }


        }

    }
}

