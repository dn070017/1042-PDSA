
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/*
.
 * To change this template file, choose Tools | Templates
.
 */

/**
 *
 * @author LAB228
 */
public class LabelCC {    
    public static void main(String[] args) throws IOException{    
        try(BufferedReader br = new BufferedReader(new FileReader(args[0]))){
    
    String[] inf = br.readLine().split("","");
    int N =  Integer.parseInt(inf[0]); //N is scale of grids
    int[][] matrix;
            matrix = new int[N+1][N+1];
    
    UF uf = new UF(N*N/2);        
            
    int[] target;
          target = new int[2];
   
    target[0] =  Integer.parseInt(inf[1]);         //_______type:location of target = (target[0],target[1])______
    target[1] =  Integer.parseInt(inf[2]);         //store value of target    
    
    int row = 0; //max of element is n*n  // store for input X-axis in order of [i]
    int column = 0; //max of element is n*n  // store for input Y-axis in order of [i]
    int counting = 0;
    
    for(int x=1; x<N+1; x++){
        for(int y=1; y<N+1; y++){
           matrix[x][y]=1; 
        }
    }
   
//....................................specify the block grids ** scale of n **-------------------------------
     while (br.ready()){
         String[] num = br.readLine().split("","");
         
         row = Integer.parseInt(num[0]);     //asume a metrix is way as  M[row][column]
         column = Integer.parseInt(num[1]);
         
         matrix[row][column]=0;
             
    }//end of while (and input function part)

//------------------------------------first path** scale of N^2 **------------------------------------------- 
        int lable = 0;
        for(int y=1; y<N+1; y++){
            for(int x=1; x<N+1; x++){
                if(matrix[y][x]!=0){
                    
                    if( (matrix[y][x-1]==0)&&(matrix[y-1][x]==0) ){
                        lable++ ;
                        matrix[y][x] = lable;
                    }//slave if(1)  ----create new lable
                    if( (matrix[y][x-1]==matrix[y-1][x])&&(matrix[y-1][x]!=0) ){
                        matrix[y][x] = matrix[y][x-1];
                    }//slave if(2)  ----lable follower (type1)
                    if( ((matrix[y][x-1]==0) || (matrix[y-1][x]==0)) && ((matrix[y][x-1]!=0) || (matrix[y-1][x]!=0)) ){
                        matrix[y][x] = Math.max(matrix[y-1][x],matrix[y][x-1]);
                    }//slave if(3)  ----lable follower (type2)
                    if( matrix[y][x-1]!=0 && matrix[y-1][x]!=0 && (matrix[y][x-1]!= matrix[y-1][x]) ){
                        matrix[y][x] = Math.min(matrix[y-1][x],matrix[y][x-1]);
                        uf.union(Math.min(matrix[y-1][x],matrix[y][x-1]), Math.max(matrix[y-1][x],matrix[y][x-1]));
                    }//slave if(4)  ----lable chooser  (cc is needed)
                 
                }//end of master if
            
                else matrix[y][x]=0;   //master else
            }//end of for-x
        }//end of for-y
//-------------------------------------------------------------------------------------------------------------
    /*    for(int y=1; y<N+1; y++){
            System.out.printf(""\n"");
            for(int x=1; x<N+1; x++){
                System.out.print(matrix[y][x]+"" ""); 
            }
        } //print matrix*/
        
        if( (matrix[target[0]][target[1]]>0)&&(N!=1) ){
            System.out.println(uf.find(matrix[target[0]][target[1]]));
        }
        if( (matrix[target[0]][target[1]]==0)&&(N!=1)){
            System.out.println(0);
        }
        if(N==1){
            if(target[0]==0){
                System.out.println(1);
            }
            else System.out.println(0);
        }
        /*    if((5|0)==1){
                System.out.println(""Hello"");
            }*/

            }//end of try
        }//end of main
    
}//end of class

