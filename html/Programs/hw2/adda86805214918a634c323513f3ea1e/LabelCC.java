import java.io.FileReader;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;

public class LabelCC {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws FileNotFoundException, IOException {
        // TODO code application logic here
        BufferedReader br = new BufferedReader(new FileReader(args[0]));
        StringBuffer sb = new StringBuffer();
        String strNum = new String();
        
    while ((strNum = br.readLine())!=null){
        sb.append(strNum);
        sb.append("";"");
    }
        strNum = sb.toString();
        String data[] = strNum.split("";"");
        String firstLine[] = data[0].split("","");
        int n = Integer.valueOf(firstLine[0]);
                //union
        
        QuickFindUF uf = new QuickFindUF(10000*n);
        
        int flag[][] = new int [n][n];
        
        for(int i = 0 ; i<flag.length; i++){
            for(int j = 0; j < flag[i].length; j++){
                flag[i][j] = -1;            
            }
        }
        
        for(int i = 1; i < data.length; i++){
            makeflag(flag,data[i]);
        }
        int label = 0;
        int count = 0;
        boolean con = false;
        for(int i = 0 ; i<flag.length; i++){
            for(int j = 0; j < flag[i].length; j++){
                if( i == 0){
                    if(flag[i][j] == -1 && con == false){
                        count++;
                        label = count;
                        flag[i][j] = label;
                        con = true;
                    }else if (flag[i][j] == -1 && con == true){
                        flag[i][j] = label;
                        con = true;
                    }else if(flag[i][j] == 0 && con == true){
                        con = false;
                    }
                }
                if( i > 0){
                    if(flag[i][j] == -1 && con == false && flag[i-1][j] == 0){
                        count++;
                        label = count;
                        flag[i][j] = label;
                        con = true;
                    }else if(flag[i][j] == -1 && con == false && flag[i-1][j] != 0){
                        label = flag[i-1][j];
                        flag[i][j] = label;
                        con = true;
                    }else if (flag[i][j] == -1 && con == true && (flag[i-1][j] == 0 || label == flag[i-1][j])){
                        flag[i][j] = label;
                        con = true;
                    }else if(flag[i][j] == -1 && con == true){
                        if(label > flag[i-1][j]){
                            uf.union(label, flag[i-1][j]);
                        }else{
                            uf.union(flag[i-1][j], label);
                        }
                        flag[i][j] = label;
                        con = true;
                    }else if(flag[i][j] == 0 && con == true){
                        con = false;
                    }
                }
            }
                
            con = false;
        }
        
        for(int i = 0 ; i<flag.length; i++){
            for(int j = 0; j < flag[i].length; j++){
                System.out.print(flag[i][j]);
            }
            System.out.println();
        }
        
        
       System.out.println(uf.find(flag[Integer.valueOf(firstLine[1])-1][Integer.valueOf(firstLine[2])-1]));
        

        
    }
    
    static void makeflag(int map[][] , String mark){
        String [] temp = mark.split("","");
        int flag[] = new int[2];
        flag[0] = Integer.valueOf(temp[0]);
        flag[1] = Integer.valueOf(temp[1]);
        //System.out.println(flag[0] +""""+ flag[1]);
        map[flag[0]-1][flag[1]-1] = 0;
        
    
    }
    
}

