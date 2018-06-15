import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
.
 */
public class LabelCC {
    public static void main(String[] args){

        try {

            BufferedReader br = new BufferedReader(new FileReader(args[0]));
            String[] firstLine = br.readLine().split("","");
            int num = Integer.parseInt(firstLine[0]);
            int targetX = Integer.parseInt(firstLine[1]) - 1; // index 從0開始所以讀出座標之後要-1
            int targetY = Integer.parseInt(firstLine[2]) - 1;

            int labelCount = 1;
            int[][] labelCC = new int[num][num];

            HashMap<Integer,Integer> parentMap = new HashMap<Integer, Integer>();

            for(int i = 0 ; i < num ; i++){
                for(int j = 0 ; j < num ; j++){
                    labelCC[i][j] = -1;
                }
            }

            while (br.ready()){
                String[] coordinate = br.readLine().split("","");
                int x = Integer.parseInt(coordinate[0]) - 1; // index 從0開始所以讀出座標之後要-1
                int y = Integer.parseInt(coordinate[1]) - 1;
                labelCC[x][y] = 0;
            }
            br.close();

            for(int i = 0 ; i < num ; i++){
                for(int j = 0 ; j < num ; j++){
                    if(labelCC[i][j] != 0){
                        if(i==0 && j==0){
                            labelCC[i][j] = labelCount;
                        } else if(i == 0){
                            int labelLeft = labelCC[i][j-1];
                            labelCC[i][j] = (labelLeft != 0)? labelLeft : ++labelCount;
                        } else if(j == 0){
                            int labelTop = labelCC[i-1][j];
                            labelCC[i][j] = (labelTop != 0)? labelTop : ++labelCount;
                        } else {
                            int labelLeft = labelCC[i][j-1];
                            int labelTop = labelCC[i-1][j];
                            if(labelLeft == 0 && labelTop == 0){
                                labelCC[i][j] = ++labelCount;
                            } else if(labelLeft == 0) {
                                labelCC[i][j] = labelTop;
                            } else if(labelTop == 0) {
                                labelCC[i][j] = labelLeft;
                            } else {
                                labelCC[i][j] = labelLeft;
                                if(labelLeft != labelTop){
                                    int parent = labelTop < labelLeft? labelTop : labelLeft;
                                    int child = labelTop >= labelLeft? labelTop : labelLeft;
                                    parentMap.put(child,parent);
                                }
                            }
                        }
                    }
                }
            }



            for(int i = 0 ; i < num ; i++){
                for(int j = 0 ; j < num ; j++){
                    labelCC[i][j] = getRoot(parentMap , labelCC[i][j]);
                }
            }


//            for(Map.Entry entry:parentMap.entrySet()){
//                System.out.println(entry.getKey() + "":"" + entry.getValue());
//            }
//            for(int i = 0 ; i < num ; i++){
//                for(int j = 0 ; j < num ; j++){
//                    System.out.print(labelCC[i][j]+""\t"");
//                }
//                System.out.println("""");
//            }


            System.out.println(labelCC[targetX][targetY]);



        } catch (IOException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }


    public static int getRoot(Map<Integer,Integer> map , int index){
        if(map.containsKey(index)){
            index = map.get(index);
            if(map.containsKey(index))
                index = map.get(index);
        }
        return index;
    }



}

