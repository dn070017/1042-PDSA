public class Bingo {

 
    public static void main(String[] args) {
        // TODO code application logic here
        int sum = 0;
        String tmp[] = args[0].split("","");
        int m = Integer.valueOf(tmp[0]);
        int n = Integer.valueOf(tmp[1]);
        
        String ans[] = new String[m];
        ans = args[1].split("","");
        
        String map [][] = new String[n][n];
        int flag[][] = new int [n][n];
        
        
        for(int i = 0; i<n; i++){
            map[i] =  args[i+2].split("","");                   
        }
        
        for(int i =0 ; i<flag.length; i++){
            for(int j = 0; j < flag[i].length; j++){
                flag[i][j] = 0;            
            }
        }
        
        for(int q = 0; q < ans.length ; q++){
            for(int i = 0 ; i< flag.length; i++){
                for(int j = 0; j < flag[i].length; j++){
                    if (map[i][j].equals(ans[q])){
                        flag[i][j] = 1; 
                    }
                }
            }
        }
        int temp = 0;
        int temp2 = 0;
        int temp3 = 0;
        for(int i = 0; i < flag.length ; i++){
            if (flag[i][i] != 0){
                for(int j = 0; j < flag[i].length; j++){
                    temp2 = temp2 + flag[i][j];
                    
                    temp3 = temp3 + flag[j][i];
                    
                    }
                    
                if (temp2 == n){
                        sum = sum + 1;
                    }
                 temp2 = 0;    
                if (temp3 == n){
                        sum = sum + 1;
                    }
                 temp3 = 0;    
                
            }
            temp = temp + flag[n-1-i][i];
        }
            if (temp == n){
                sum = sum + 1;
            }
            temp = 0;
        
            System.out.println(sum);
        
        
        
    }
    
}

