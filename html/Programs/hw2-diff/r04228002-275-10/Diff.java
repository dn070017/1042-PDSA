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
            
            //int[] LabelManage = new int[SizeNum*SizeNum];
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
            int[] LabelList = new int[SizeNum*SizeNum];
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
                            LabelList[i]=MaxLabel;
                        }
                        else{
                            LabelList[i]=LabelList[left];
                        }   
                    }
                    else{
                        if (left_blocked==true){
                            LabelList[i]=LabelList[up];
                        }
                        else{
                            int up_label=LabelList[up];
                            int left_label=LabelList[left];
                            
                            if (up_label <= left_label){
                                //LabelManage[left_label]=up_label;
                                uf.union(left_label,up_label);
                                LabelList[i]=LabelList[up];
                            }
                            else{
                                //LabelManage[up_label]=left_label;
                                uf.union(up_label,left_label);
                                LabelList[i]=LabelList[left];
                            }
                            
                        }
                    }
                }
                else{
                    continue;
                }     
            }
            
            int result_index=LabelList[tid];
            int result=uf.find(result_index);
            //int result = LabelManage[result_index];
            //System.out.println(Arrays.toString(LabelList));
            //System.out.println(Arrays.toString(LabelManage));
            //System.out.println(result_index);
            System.out.println(result);
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

