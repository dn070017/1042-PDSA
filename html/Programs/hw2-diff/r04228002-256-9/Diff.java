/*
.
 * To change this template file, choose Tools | Templates
.
 */


/**
 *
 * @author Lenovo
 */
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Arrays;
import java.util.ArrayList;
//import edu.princeton.cs.algs4.QuickUnionUF;

public class LabelCC {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception {
        // TODO code application logic here
        try(BufferedReader br = new BufferedReader(new FileReader(args[0]))){
            
            String[] row1 = br.readLine().split("","");
            int SizeNum = Integer.parseInt(row1[0]);
            
            QuickUnionUF uf = new QuickUnionUF(SizeNum*SizeNum);
            int trow=Integer.parseInt(row1[1]);
            int tcol=Integer.parseInt(row1[2]);
            int tid= (trow-1)*SizeNum+tcol-1;
            //tid=51;
            
            ArrayList BlockedList = new ArrayList();
            for (int i = 0; i< i+1;i++){
                String[] row;
                try{
                    row = br.readLine().split("","");
                }
                catch(Exception e){
                    break;
                }
                
                //String[] row = br.readLine().split("","");
                int pr=Integer.parseInt(row[0]);
                int pc=Integer.parseInt(row[1]);
                /*
                if (pr==trow && pc==tcol){
                    System.out.println(0);
                    //break;
                }
                */
                int id = (pr-1)*SizeNum+pc-1;
                BlockedList.add(id);
            }
            
            
            //Start to union
            int[] RootList = new int[SizeNum*SizeNum];
            int MaxLabel=0;
            
            for (int i=0;i<SizeNum*SizeNum;i++){
                int up = i-SizeNum;
                int left = i-1;
                boolean up_blocked = BlockedList.contains(up);
                boolean left_blocked = BlockedList.contains(left);
            
                if (up < 0){
                    up_blocked=true ;
                }
                if (left%SizeNum == SizeNum-1 || left < 0){
                    left_blocked=true;
                }            
               
                if (BlockedList.contains(i)==false){
                    if (up_blocked==true){
                        if (left_blocked==true){
                            MaxLabel++;
                            RootList[i]=MaxLabel;
                        }
                        else{
                            uf.union(i, left);
                        }   
                    }
                    else{
                        if (left_blocked==true){
                            uf.union(i, up);
                        }
                        else{
                            int up_root = uf.find(up);
                            int left_root = uf.find(left);
                            if (up_root <= left_root){
                                uf.union(left,up);
                                uf.union(i,up);
                            }
                            else{
                                uf.union(up,left);
                                uf.union(i,left);
                            }
                        }
                    }
                }
                else{
                    continue;
                }     
            }
            
            int result=uf.find(tid);
            int result2 = RootList[result];
            //System.out.println(Arrays.toString(RootList));
            System.out.println(result2);
        }
    }
    
}

class Root{
    public int ID;
    public int Label;
    public Root(int id,int label){
        int ID;
        ID=id;
        this.ID=id;
        int Label;
        Label=label;
        this.Label=label;
    }
}

