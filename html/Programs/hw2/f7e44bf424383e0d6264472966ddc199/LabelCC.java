
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
/**
 *
 * @author Daniel C
 */
public class LabelCC {
 private int [] states;
  private int [] labnumber;
  private int side;
  public int count = 0;
  QuickUnionUF uf = new QuickUnionUF(15);
  
  public void creatematrix(int N){
        side = N;
        states=new int[N*N];
        labnumber = new int [N*N];
        for(int index=0;index<N*N;index++){
           states[index]=1;
           labnumber[index]=0;
       }
    }
    public void block(int i, int j){
       int cell=getCellIndex(i,j);
       states[cell]=0;
    }
//    private void checkRange(int i, int j){
//       if (i<=0||j<=0||i>side||j>side)throw new IndexOutOfBoundsException();
//     }
    public boolean isOpen(int i, int j){
//       checkRange(i,j);
       return states[getCellIndex(i,j)]==1;
     }   
    private int getCellIndex(int row, int column){
       return (side*(row-1))+column-1;
     }
    private void firstpassLabel(){
        for(int i=1;i<=side;i++){
            for(int j=1;j<=+side;j++){
                if(isOpen(i,j)){
                    creatnewLabel(i,j);
                    System.out.println(i+"" ""+j);
                }
            }
        }
        
    }
    private int secpassLabel(int n, int m){
            int out = labnumber[getCellIndex(n, m)];
            int output = uf.find(out);
            return output;
        }
    
    private void creatnewLabel(int i, int j){
        int cell = getCellIndex(i,j);
        //for top rows
        if(i==1&&j==1){
            ++count;
            labnumber[cell]=count;
            return;
        }
        if(i==1 && j!=1){
            if(isOpen(i,j-1)){
                labnumber[cell]= labnumber[getCellIndex(i,j-1)];
                return;
                }else{
                ++count;
                labnumber[cell]=count;
                return;
            }         
        }
        // for other rows
        if(i!=1 && j!=1){
            if(isOpen(i,j-1)){
                labnumber[cell] = labnumber[cell-1];
                if(labnumber[getCellIndex(i-1,j)]!=0){
                    uf.union(labnumber[getCellIndex(i-1,j)],labnumber[getCellIndex(i,j-1)]);
                    if(labnumber[getCellIndex(i-1,j)]>labnumber[cell-1]){
                        return;
                    }else{
                        labnumber[cell] = labnumber[getCellIndex(i-1,j)];
                        return;
                    }
                    
                }
            }else if(labnumber[getCellIndex(i-1,j)]!=0){
                labnumber[cell] = labnumber[getCellIndex(i-1,j)];
                return;
            }
            else{
                ++count;
                labnumber[cell]=count;
            }
           
        }
//        first column
        if(i!=1 &&j==1){
            if(isOpen(i-1,j)){
                labnumber[cell]= labnumber[getCellIndex(i,j-1)];
            }else{
                ++count;
                labnumber[cell] = count;
            }
            
        }
        
    }
   
    public static void main(String[] args) throws IOException{
        LabelCC L1 = new LabelCC();
        File inputfile = null; 
        if(0 < args.length){
            inputfile = new File(args[0]);
        } else{
            System.out.println(""file does not exist"");
        }
        BufferedReader br = new BufferedReader(new FileReader(inputfile));
        int input1 = 0 , input2 = 0 , input3 = 0;
        String firstline = br.readLine();
        String[] inputarray = firstline.split("","");
        input1 = Integer.parseInt(inputarray[0]);
        input2 = Integer.parseInt(inputarray[1]);
        input3 = Integer.parseInt(inputarray[2]);
//        QuickUnionUF uf = new QuickUnionUF(input1);
        L1.creatematrix(input1);
        String data = null;
        String [] inputnumb;
        int a=0, b=0;
        while((data = br.readLine())!=null){
             inputnumb = data.split("","");
             a = Integer.parseInt(inputnumb[0]);
             b = Integer.parseInt(inputnumb[1]);
             L1.block(a,b);
    }
            L1.firstpassLabel();
            System.out.println(L1.secpassLabel(input2, input3));
                        
        
}

}

