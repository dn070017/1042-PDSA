import java.io.FileReader;
import java.io.BufferedReader;

public class LabelCC {

    public static void main(String[] args) throws Exception {

        // read file from args[0] in Java 7 style
        try (BufferedReader br = new BufferedReader(new FileReader(args[0]))) {

            // read a line and split by ','
            String[] data = br.readLine().split("","");

            // store the first integer in variable stringCount (number of announced strings)
            int size = Integer.parseInt(data[0]);
            int [] target= new int [2];
            target[0] = Integer.parseInt(data[1]);
            target[1] = Integer.parseInt(data[2]);

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
                    //System.out.println(match[i][j]);
                }
            }
            int [] state = new int [size*size];//open or not
            int [] number = new int [size*size];
            int [] idt = new int [size*size];
            
            for(int i=0;i<size*size;++i){
                number[i]=0;
                state[i]=1;
                idt[i]=i;
            }
            WeightedQuickUnionUF wei = new WeightedQuickUnionUF(size*size);
            int now = 1; //assign number;
            int reg = 0;
            
            
            for(int i=0;i<num;++i){
                reg =  (match[i][0]-1)*size + match[i][1]-1 ;
                state[reg]=0;
            }
            if(state[(target[0]-1)*size+target[1]-1]==1){
            for(int i=0;i<size*size;++i){
               if(state[i]==1){
                int neigh=0;
                
                if(i%size!=0){
                    if(state[i-1]==1){
                        wei.union(idt[i], idt[i-1]);
                        number[i]=number[i-1];
                        ++neigh;
                    }
                }
                if(i-size>-1){
                    if(state[i-size]==1){
                        wei.union(idt[i], idt[i-size]);
                        number[i]=number[i-size];
                        ++neigh;
                    }
                }
                if(neigh==0){
                    number[i]=now;
                    ++now;
                }
                
                
                
                
               }
            }
            
            for(int i=0;i<(target[0]-1)*size+target[1]-1;++i){
                if(state[i]==1){
                int check=number[i];
                 for(int j=0;j<(target[0]-1)*size+target[1]-1;++j){
                     
                     if(wei.connected( idt[i],idt[j]) ){                         
                             if(check>number[j]){
                                 check = number[j];
                             }
                     }        
                 }
                 number[i]=check;
                 for(int j=0;j<size*size;++j){
                     if(wei.connected( idt[i],idt[j]) ) number[j]=check;                             
                 }
                }
            }
            }
            
            //for(int i=0; i<size*size;++i) System.out.println(number[i]);
            //System.out.println(state[(target[0]-1)*size+target[0]-1]);
            
            if(state[(target[0]-1)*size+target[1]-1]==1)
            System.out.println(number[ (target[0]-1)*size+target[1]-1 ]);
            else System.out.println(0);
        }

    }
}
