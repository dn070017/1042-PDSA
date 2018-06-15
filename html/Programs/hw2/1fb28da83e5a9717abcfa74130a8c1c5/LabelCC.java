import java.io.BufferedReader;
import java.io.FileReader;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

/**
.
 * 題目：http://c4lab.bime.ntu.edu.tw:24080/judge/problem/hw2.html
 */
public class LabelCC {
    public static void main(String[] args) throws Exception {
        try(BufferedReader br = new BufferedReader(new FileReader(args[0]))) {

            // 0.讀檔
            String[] metadata = br.readLine().split("","");
            int N = Integer.parseInt(metadata[0]);


            // 1.宣告
            int[] target = {Integer.parseInt(metadata[1]),Integer.parseInt(metadata[2])};   //目標site的座標
            int matrixSize = N*N;   //matrix的""長度""
            int[] arrMatrix = new int[matrixSize];
            Arrays.fill(arrMatrix,1);//arrMatrix所有元素值初始為一
            int stamp = 0;
            int ID = 0;
            int[] tempMatrix = new int[matrixSize];
            QuickUnionUF labels = new QuickUnionUF(N*N/2); //label = {0,1,2,3,4,5,6,7,}
            int targetID = N*(target[0]-1)+(target[1]-1);//7+8*4


            // 2.將input中的座標set 0
            while(br.ready()){
                String[] strCoor = br.readLine().split("","");   //座標一行行讀進string[] strCoor
                int[] intCoor = Arrays.asList(strCoor).stream().mapToInt(Integer::parseInt).toArray();   //intCoor = Int(strCoor)
                ID = (intCoor[1]-1) + N*(intCoor[0]-1);
                arrMatrix[ID]=0;
            }
            ID=0;    //計步器歸零


            /*/–––––––––––––––––––print整個matrix–––––––––––––––––––––————
            System.out.println(""目前的matrix長這樣：\n"");
            for (int i=0; i<N; i++){
                for (int j=0; j<N; j++){
                    System.out.printf(""[%d,%d:%d]= %d\t"",i,j,N*i+j,arrMatrix[N*i+j]);
                }
                System.out.println(""\n"");
            }
            //–––––––––––––––––––––––––––––––––––––––––––––––––––––––––––*/



            //3. first pass
            for(ID=0;ID<matrixSize;ID++){
//                System.out.printf(""\nnow at ID=%d"",ID);

//              ––––––––––––––––––––––––––––––––––––––––––——
//              ––––––––––––––––非第一排時–––––––––––––––––––
//              ––––––––––––––––––––––––––––––––––––––––––——
                if((ID>=N)&&(arrMatrix[ID]!=0)){
                    if(((ID%N!=0)&&(arrMatrix[ID-N]<=0)&&(arrMatrix[ID-1]<=0))||((ID%N==0)&&(arrMatrix[ID-N]<=0))){
                        stamp++;    //若 ""上"" ""左"" 為0/X則stamp++ 並蓋章
                        arrMatrix[ID]=stamp;
                        tempMatrix[ID]=stamp;

                    }else if((ID%N==0)&&(arrMatrix[ID-N]!=0)){
                        tempMatrix[ID] = tempMatrix[ID-N];
//                        System.out.println(""第一列直接取上面"");
                    }else if((arrMatrix[ID-N]!=0)&&(arrMatrix[ID-1]!=0)){
//                        System.out.println(""左上比大小"");
                        if(tempMatrix[ID-N]<=tempMatrix[ID-1]){
                            tempMatrix[ID] = tempMatrix[ID-N];
                            labels.union(tempMatrix[ID-1],tempMatrix[ID-N]);
//                            System.out.printf(""上贏了"");
                        }else{
                            tempMatrix[ID] = tempMatrix[ID-1];
                            labels.union(tempMatrix[ID-N],tempMatrix[ID-1]);
//                            System.out.printf(""左贏了！"");
                        }

                    }else if((arrMatrix[ID-N]!=0)&&(arrMatrix[ID-1]==0)){
                        tempMatrix[ID] = tempMatrix[ID-N];
//                        System.out.println(""[上：ID-N]"");

                    }else if((arrMatrix[ID-1]!=0)&&(arrMatrix[ID-N]==0)){
                        tempMatrix[ID] = tempMatrix[ID-1];
//                        System.out.println(""[左：ID-1]"");
                    }

//                    System.out.printf(""[NORMAL{arrMatrix[ID]=%d}]"",arrMatrix[ID]);



//              ––––––––––––––––––––––––––––––––––––––––––——
//              ––––––––––––––––第一排時–––––––––––––––––––––
//              ––––––––––––––––––––––––––––––––––––––––––——
                }else if((ID<N)&&(ID!=0)&&(arrMatrix[ID]!=0)){
                    if(arrMatrix[ID-1]<=0){
                        stamp++;
                        arrMatrix[ID]=stamp;
                        tempMatrix[ID]=stamp;
                    }else if(arrMatrix[ID]!=0) {
                        tempMatrix[ID] = tempMatrix[ID - 1];
                    }

//                    System.out.println(""[LINE ONE]"");


//              ––––––––––––––––第一排第一個––––––––––––––––––
                }else if((ID==0)&&(arrMatrix[ID]!=0)){
//                    System.out.println(""[NOW ID 0]"");
                    stamp++;
                    arrMatrix[ID]=stamp;
                    tempMatrix[ID]=stamp;
                }
            }

            //–––––––––––––––––––print整個tempMatrix–––––––––––––––————––––––
            // ––––
//            System.out.println(""目前的tempMatrix長這樣：\n"");
            for (int i=0; i<N; i++){
                for (int j=0; j<N; j++){
//                    System.out.printf(""%d\t"",tempMatrix[N*i+j]);
                }
//                System.out.println(""\n"");
            }
            //–––––––––––––––––––––––––––––––––––––––––––––––––––––––––––

            //4. second pass
            for(ID=0;ID<matrixSize;ID++){
                if(tempMatrix[ID]!=0){
                    tempMatrix[ID]=labels.find(tempMatrix[ID]);
                }
            }

            //5. return reult
            System.out.println(tempMatrix[targetID]);

//
//            //–––––––––––––––––––print整個matrix–––––––––––––––————––––––
//            // ––––
//            System.out.println(""目前的arrMatrix長這樣：\n"");
//            for (int i=0; i<N; i++){
//                for (int j=0; j<N; j++){
//                    System.out.printf(""%d\t"",arrMatrix[N*i+j]);
//                }
//                System.out.println(""\n"");
//            }
//            //–––––––––––––––––––––––––––––––––––––––––––––––––––––––––––
//            //–––––––––––––––––––print整個tempMatrix–––––––––––––––————––––––
//            // ––––
//            System.out.println(""目前的tempMatrix長這樣：\n"");
//            for (int i=0; i<N; i++){
//                for (int j=0; j<N; j++){
//                    System.out.printf(""%d\t"",tempMatrix[N*i+j]);
//                }
//                System.out.println(""\n"");
//            }
//            //–––––––––––––––––––––––––––––––––––––––––––––––––––––––––––
//            //––––––––––––––––––––print roots––––––––––––––––––––––––––––
//            for (int i=0; i<N*N/2; i++){
//                System.out.printf(""root(%d)=%d.\n"",i,labels.find(i));
//            }
//            //–––––––––––––––––––––––––––––––––––––––––––––––––––––––––––
//            System.out.printf(""ID=%d:%d"",targetID,tempMatrix[targetID]);
        }
    }
}
