/*
.
 * To change this template file, choose Tools | Templates
.
 */
package labelcc;
import java.io.FileReader;
import java.io.BufferedReader;
/**
 *
 * @author chinweihsu
 */
public class LabelCC {
    
    public static class labelMethod {
        private int[] image;
        private int[] root;
        private int[] label;
        public int N;
        
        labelMethod(int n){
            N = n;
            image = new int [n*n];
            root = new int [n*n];
            label = new int [n*n];
          
            for(int i=0; i<image.length; i++)
                image[i]  = 1;
            for(int i=0; i<root.length; i++)
                root[i] = i;
        }
        
        public int getAddress(int x, int y){
            return N*(x-1)+y;
        }
        
        public void block(int[] a){
            image[getAddress(a[0],a[1])-1] = 0;
        }
        
        public boolean isBlocked(int id){
            return image[id-1]==0;
        }
        
        public boolean isLeftEdge(int id){
            return id%N==1;
        }
        
        public boolean isTopRow(int id){
            return id <= 8;
        }
        
        public void union(int a, int b){
            int rootA = getRoot(a);
            int rootB = getRoot(b);
            
            if(label[rootA] < label[rootB])
                root[rootB] = rootA;
            else
                root[rootA] = rootB;
        }
        
        public int getRoot(int id){
            int r = id-1;
            while(r!= root[r]){
                root[r] = root[root[r]];
                r = root[r];
            }
            return r;
        }
        
        public void firstPass(){
            int labelCount = 0;
            for(int i=1; i<=N*N; i++){
                if(isBlocked(i)){    //此格為block
                    label[i-1] = 0;
                }else{   //此格不為block
                    if(!isLeftEdge(i)){ //此格不在左邊界
                        if(!isBlocked(i-1)){ //此格左邊不為blocked
                            label[i-1] = label[i-2];
                            root[i-1] = root[i-2];
                            if(!isTopRow(i) && !isBlocked(i-N)){ //連接兩相臨團體
                                union(i,i-N);
                            }
                        }else{ //此格左邊為blocked
                            if(isTopRow(i)){ //為第一橫排
                                labelCount+=1;
                                label[i-1] = labelCount;
                            }else{ //左格block 採用上方格label
                                if(isBlocked(i-N)){ //上方格為blocked
                                    labelCount+=1;
                                    label[i-1] = labelCount;
                                }else{ //上方格不為bocked
                                    label[i-1] = label[i-N-1];
                                    root[i-1] = root[i-N-1];
                                }}}
                    }else{ //此格在左邊界
                        if(isTopRow(i)){ //為第一橫排
                                labelCount+=1;
                                label[i-1] = labelCount;
                        }else{ //左格block 採用上方格label
                            if(isBlocked(i-N)){ //上方格為blocked
                                labelCount+=1;
                                label[i-1] = labelCount;
                            }else{ //上方格不為bocked
                                label[i-1] = label[i-N-1];
                                root[i-1] = root[i-N-1];
                            }}}}}
        }
        public int getLabel(int[] a){
            int id = getAddress(a[0],a[1]);
            return label[getRoot(id)];
        }
        public int getLabelByID(int a){
            return label[getRoot(a)];
        }
        
        public void secondPass(){
            for(int i=1; i<=N*N; i++){
                
            }
        }
        public void showLabels(){
            System.out.print(""image"");
            for(int i=0; i<image.length; i++){
                if(i%N ==0){
                    System.out.print(""\n"");
                    System.out.printf(""%-4d"",image[i]);
                }else
                    System.out.printf(""%-4d"",image[i]);
            }
            System.out.print(""\nlabel"");
            for(int i=1; i<=root.length; i++){
                if(i%N ==1){
                    System.out.print(""\n"");
                    System.out.printf(""%-4d"",getLabelByID(i));
                }else
                    System.out.printf(""%-4d"",getLabelByID(i));
            }
            System.out.print(""\nroot"");
            for(int i=0; i<root.length; i++){
                if(i%N ==0){
                    System.out.print(""\n"");
                    System.out.printf(""%-4d"",root[i]);
                }else
                    System.out.printf(""%-4d"",root[i]);
            }
        }
    }
    

    /**
     * @param args the command line arguments
     * @throws java.lang.Exception
     */
    public static void main(String[] args) throws Exception {
        // TODO code application logic here
        try(BufferedReader br = new BufferedReader(new FileReader(args[0]))){
            String[] data = br.readLine().split("","");
            int size = Integer.parseInt(data[0]);
            int[] thePoint ={ Integer.parseInt(data[1]) ,Integer.parseInt(data[2])};
            
            labelMethod AG = new labelMethod(size);
            //AG.show();
            while(br.ready()){
                String[] temp = br.readLine().split("","");
                int[] point ={ Integer.parseInt(temp[0]) ,Integer.parseInt(temp[1])};
                AG.block(point);
            }
            AG.firstPass();
            AG.showLabels();
            System.out.print(AG.getLabel(thePoint));
        }
}
    
}

