
import java.io.IOException;
import java.io.*;
import java.util.Scanner;
        
/**
 *
 * @author 林康維
 */
public class LabelCC {


    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
           File file = new File(args[0]);
           int Drow , Dcol ;
           try{
               Scanner File_in = new Scanner(file) ; 
String num[] = File_in . nextLine() . split("","");
               int Dim = Integer . parseInt (num [0]) ;
               int ansrow = Integer . parseInt (num[1]) ;
               int anscol = Integer . parseInt (num[2]) ;
               boolean[][] matrix = new boolean [Dim][Dim] ;
               UF uf = new UF( Dim*Dim ) ; 
               for ( int i = 0 ; i < Dim ; i++){
                   for(int j = 0 ; j <Dim ; j++){
                   matrix [i][j] = true ;
                   }
               }
               while(File_in.hasNextLine()){
                   String Black = File_in.nextLine() ;
                   String[] mat = Black.split("","") ;
                   Drow = Integer . parseInt (mat[0]) -1 ;
                   Dcol = Integer . parseInt (mat[1]) -1 ;
                   matrix [Drow][Dcol] = false ;
                   /*if(!File_in.hasNextLine())
                       break ;*/
               }
               int [][] A = new int [Dim][Dim];
               int count = 1 ;
               for (int i = 0 ; i < Dim ; i++){
                   for (int j = 0 ; j < Dim ;j ++){
                   A [i][j] = 0 ;
                   }
               }
               if ( matrix [0][0] == true){
                   A [0][0] = 1 ;
                   count += 1 ;
               }
               for ( int i = 1 ; i < Dim ; i++){
                   if (matrix [0][i] == true){
                   if (A[0][i-1] != 0){
                   A[0][i] = A[0][i-1] ; 
                   uf.union(i,i-1);
                   }
                   else if (A[0][i-1]  ==0)  {
                       A [0][i] = count ;
                       count += 1 ;
                   }
               }
               }
               
               for ( int i = 1 ; i < Dim   ; i++){
                   for ( int j =0 ; j < Dim  ; j++){
                       if (j ==0 ){
                           if (matrix [i][j] == true){
                               if (A[i-1][j] != 0) {
                                   A [i][j] = A [i-1][j] ;
uf.union( i * Dim + j , ( i - 1 ) * Dim + j );
                               }
                               else{
                                   A[i][j] = count ;
                                   count += 1 ;
                               }
                           }
                       }
                       else if (j != 0){
                       if ( matrix[i][j] == true ){
                           if( (A [i-1] [j] != 0) && (A [i] [j-1] != 0)){
                               if((A [i-1] [j]) > (A [i] [j-1])){
                               
                                   A [i] [j] = A [i] [j-1] ; 
uf.union( i * Dim + j , i * Dim + j - 1);
uf.union( i * Dim + j , (i-1) * Dim + j);       
                               }
                               else if ((A [i-1] [j]) < (A [i] [j-1])){
                                   
                                   A [i] [j] = A [i-1] [j] ;
uf.union( i * Dim + j , i * Dim + j - 1);
uf.union( i * Dim + j , (i-1) * Dim + j);
                               }
                               else {
                                   A [i] [j] = A [i-1] [j] ;
uf.union( i * Dim + j , i * Dim + j - 1);
uf.union( i * Dim + j , (i-1) * Dim + j);
                               }
                               }
                           else if ( (A [i-1] [j] != 0) && (A [i] [j-1] ==0)){
                               A [i] [j] = A [i-1] [j] ;
                               uf.union( i * Dim + j , (i-1) * Dim + j);
                           }
                           else if ( (A [i-1] [j] == 0) && (A [i] [j-1] !=0)){
                               A [i] [j] = A [i] [j-1] ;
                               uf.union( i * Dim + j , i * Dim + j - 1);
                           }
                           else {
                               A [i] [j] = count ;
                               count += 1 ;
                           }
                           
                           }
                       }
               }
               }
               for(int i = 0 ; i < Dim ; i ++){
                   for(int j = 0 ; j < Dim ;j++){
                       for(int k = i ; k < Dim ; k++){
                           for(int l = 0; l < Dim ; l++){
if ( (uf.connected (i * Dim + j , k * Dim+ l)) && (A[i][j] != A[k][l]) ) {
                               A[k][l] = A[i][j] ;
                               }
                           }
                       }
                   }
               }
               System.out.println (A[ansrow-1][anscol-1]) ;
               /*System.out.println(ansrow) ;
               System.out.println(anscol) ;
               for (int i=0;i<8;i++){
                   //System.out.println(uf.find(i));
                   for(int j =0;j<8;j++){
                       
                   System.out.println(A[i][j]);
                   System.out.println(uf.find(i*Dim+j)) ;
                   System.out.println(matrix[i][j]) ;
                   }
                   System.out.println(""\n"") ;
               }*/
                       }
           catch(IOException e){
            System.out.println(""error!""); 
        }
    }
    
}

