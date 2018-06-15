import java.io.FileReader;
import java.io.BufferedReader;

/**
 *
 * @author Dennis
 */
public class LabelCC {

    /**
     * @param args the command line arguments
     */
   public static void main(String[] args) throws Exception {
        // TODO code application logic here

        try (BufferedReader br = new BufferedReader(new FileReader(args[0]))) {
        String[] Num = br.readLine().split("","");
        int N = Integer.parseInt(Num[0]);
        int ansx = Integer.parseInt(Num[1]);
        int ansy = Integer.parseInt(Num[2]);
        QuickFindUF uf = new QuickFindUF(N*N/2+2);
        int x=0,y=0;
        int [][] table = new int [N][N];
        int [][] Numtable = new int [N][N];
        int i=0,j=0,k=0,label=1,labelcheck=1;
        String d;
        
        for(i=0;i<N;i++){
             for(j=0;j<N;j++){
                table[i][j]=1;
            }
        }


        
        while( (d = br.readLine()) != null ){
            
            String [] data = d.split("","");
            x =  Integer.parseInt(data[0]);
            y =  Integer.parseInt(data[1]);
            table[x-1][y-1]=0;    
        // TODO code application logic here
        }
//        for(i=0;i<N;i++)
//        {
//             for(j=0;j<N;j++)
//            {
//                System.out.println(table[i][j]);
//            }
//        }

        for(i=0;i<N;i++){
            for(j=0;j<N;j++){
                
                if(table[i][j]==1)
                {
                    if( i-1<0 && j-1<0) //(0,0)
                    {
                        Numtable[i][j]=label;
                        labelcheck=0;
                    }
                    else if ( i-1<0 && j-1 >=0 ) //top bounder check left
                    {
                        if(table[i][j-1]==1)
                        {
                         Numtable[i][j]=Numtable[i][j-1];
                        }
                        else if(table[i][j-1]==0)
                        {
                         Numtable[i][j]=label;
                         labelcheck=0;
                        }
                    }
                    else if( i-1>=0 && j-1<0) //left bounder check up
                    {
                         if(table[i-1][j]==1)
                        {
                          Numtable[i][j]=Numtable[i-1][j];
                        }
                        else if(table[i-1][j]==0)
                        {
                         Numtable[i][j]=label;
                         labelcheck=0;
                        }
                    }
                    else // rest
                    {
                        if( table[i-1][j]!=0 && table[i][j-1]!=0)
                        {
                            if(Numtable[i][j-1]>Numtable[i-1][j])
                            {
                                Numtable[i][j]=Numtable[i-1][j];
                                uf.union(Numtable[i-1][j],Numtable[i][j-1]);
                            }
                            else
                            {   
                                Numtable[i][j]=Numtable[i][j-1];
                                uf.union(Numtable[i][j-1],Numtable[i-1][j]);
                            }
                        }
                        else if (table[i-1][j] ==0 && table[i][j-1]!=0)
                        {
                            Numtable[i][j]=Numtable[i][j-1];
                        }
                        else if (table[i-1][j] !=0 && table[i][j-1]==0)
                        {
                            Numtable[i][j]=Numtable[i-1][j];
                        }
                        else
                        {
                             Numtable[i][j]=label;
                             labelcheck=0;
                        }
                    }

                }
                if(labelcheck==0)
                {
                   label=label+1;
                   labelcheck=1;
                }
                
                
//                else if(table[i][j]==0 && labelcheck==0 ) 
//                {                        
//                    if( i-1<0 && j-1<0) //(0,0)
//                    {
//                       
//                    }
//                    else if ( i-1<0 && j-1 >=0 ) //top bounder check left
//                    {
//                        label=label+1;
//                        labelcheck=1;
//                    }
//                    else if( i-1>=0 && j-1<0) //left bounder check up
//                    {
//                        if(table[i-1][j]==0)
//                        label=label+1;
//                        labelcheck=1;
//                    }
//                    else // rest
//                    {
//                     label=label+1;
//                     labelcheck=1;    
//                    }
//                }
//               System.out.println(Numtable[i][j]);
            }
        }
        
        for(i=0;i<N;i++){
            for(j=0;j<N;j++){
                if(table[i][j]==1 )
                {   
                    for(k=1;k<N*N/2+2;k++)
                    {
                        if(uf.connected(k,Numtable[i][j]) && k<Numtable[i][j] )
                        {
                            Numtable[i][j]=k;
                        }

                    }
                }
//                System.out.println(Numtable[i][j]);
            }
        }

   
         System.out.println(Numtable[ansx-1][ansy-1]);
    }
   }
}

