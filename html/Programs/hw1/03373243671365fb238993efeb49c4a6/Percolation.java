import java.io.*;
public class Bingo {

    /**
     * @param args the command line arguments
     */

    public static void main(String[] args) {
        try {
      FileReader fr=new FileReader(args[0]);
      BufferedReader br=new BufferedReader(fr);
      String line;
      int count = 0;
      line = br.readLine();    
      String[] metrix_D = line.split("","");
      int country_n = Integer.parseInt(metrix_D[0]);
      int metrix_D_m = Integer.parseInt(metrix_D[1]);
      //System.out.println(metrix_D_m);
      line = br.readLine();      
      String[] country =  line.split("","");
      int[][] Bingoohya;
        Bingoohya = new int[metrix_D_m][metrix_D_m];
      int bingo_line = 0;
      int test = 0;

      while ((line=br.readLine()) != null) 
      {
          String[] buffer = line.split("","");
          for(int i=0;i<metrix_D_m;i++)
          {
              for(int j=0;j<country_n;j++)
            {
                if(buffer[i].equals(country[j]) )
                    Bingoohya[count][i] = 1;
            }      
          }
          count = count +1;
        }
      for(int p =0;p<metrix_D_m;p++){            //cols
          for(int o =0;o<metrix_D_m;o++){
              test = Bingoohya[p][o];
              if (test==0)
                  break;
  
          }
          if(test==1)
          {
           //   System.out.println(""ohya"");
              bingo_line++;
          }
      }
       for(int p =0;p<metrix_D_m;p++){                  //rows
          for(int o =0;o<metrix_D_m;o++){
              test = Bingoohya[o][p];
              if (test==0)
                  break;  
          }
          if(test==1)
          {     
              bingo_line++;
          }
      }
       for(int p =0;p<metrix_D_m;p++){                 //dia1
          
              test = Bingoohya[p][p];
              if (test==0)
                  break;            
          
      }
       if(test==1)
          {     
              bingo_line++;
          }
       int dia = metrix_D_m-1;
       for(int p =0;p<metrix_D_m;p++){             //dia2
          
              test = Bingoohya[p][dia];
              if (test==0)
                  break;  
          
          dia--;
      }
       if(test==1)
          {     
              bingo_line++;
          }
      System.out.println(bingo_line);
      }       
    catch (IOException e) {System.out.println(e);}    
        // TODO code application logic here
    }
    
}

