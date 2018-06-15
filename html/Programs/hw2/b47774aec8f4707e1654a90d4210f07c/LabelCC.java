
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
public class LabelCC {
    public int[][] labeledMatrix;

    public LabelCC(int[][] matrix) {
        ArrayList<ArrayList<Integer>> linked = new ArrayList<ArrayList<Integer>>();
        int[][] labels = new int[matrix.length][matrix[0].length];

        int NextLabel = 0;

        //First pass
        for(int i=0; i<matrix.length; i++) {
            for( int j=0; j<matrix.length; j++) {
                labels[i][j] = 0;
            }
        }

        for(int i=0; i<matrix.length; i++) {
            for(int j=0; j<matrix[0].length; j++) {
                if(matrix[i][j] != 0) {

                    //Labels of neighbors
                    ArrayList<Integer> neighbors = new ArrayList<Integer>();
                    for(int ni=-1; ni<=1; ni++) {
                        for(int nj=-1; nj<=1; nj++) {
                            if(i+ni<0 || j+nj<0 || i+ni>labels.length-1 || j+nj>labels[0].length-1) {
                                continue;
                            }
                            else {
                                if(i+ni == 0 && i+nj == 0) continue;
                                if(labels[i+ni][j+nj] != 0) neighbors.add(labels[i+ni][j+nj]);
                            }
                        }
                    }

                    if(neighbors.size() == 0) {
                        ArrayList<Integer> tempArrayList = new ArrayList<Integer>();
                        tempArrayList.add(NextLabel);
                        linked.add(NextLabel, tempArrayList);
                        labels[i][j] = NextLabel;
                        NextLabel++;
                    }
                    else {

                        labels[i][j]=1000*1000;
                        for(int neighbor : neighbors) {
                            if(neighbor < labels[i][j]) labels[i][j] = neighbor;
                        }

                        for(int neighbor : neighbors) {
                            linked.set(neighbor,union(linked.get(neighbor),neighbors));
                        }
                    }
                }

            }
        }

        //Second pass
        for(int i=0; i<matrix.length; i++) {
            for(int j=0; j<matrix[0].length; j++) {
                ArrayList<Integer> EquivalentLabels = linked.get(labels[i][j]);
                labels[i][j]=1000*1000;
                for(int label : EquivalentLabels) {
                    if(label < labels[i][j]) labels[i][j]=label;
                }
            }
        }

        labeledMatrix = labels;
    }

    //union: http://stackoverflow.com/questions/5283047/intersection-and-union-of-arraylists-in-java
    public <T> ArrayList<T> union(ArrayList<T> list1, ArrayList<T> list2) {
        Set<T> set = new HashSet<T>();

        set.addAll(list1);
        set.addAll(list2);

        return new ArrayList<T>(set);
    }

    public int[][] getLabeledMatrix() {
        return labeledMatrix;
    } 

    public static void main(String[] args) throws Exception {
        try(BufferedReader br = new BufferedReader(new FileReader(args[0]))){
            String[] data = br.readLine().split("","");

            int n = Integer.parseInt(data[0]);
            int x = Integer.parseInt(data[1]);
            int y = Integer.parseInt(data[2]);
            
            int[][] matrix = new int[n][n];
                for(int i=0; i<n; i++) {
                    for(int j=0; j<n; j++) {
                        matrix[i][j]=1;
                    }
                }
            for (int i = 0; i < n*n; i++){                
                String da = br.readLine();
                if(da==null){break;}
                String[] d = da.split("","");
                int p = Integer.parseInt(d[0]);
                int q = Integer.parseInt(d[1]);
                matrix[p-1][q-1]=0;}

        LabelCC matrixComponents = new LabelCC(matrix);
        int[][] newMatrix = matrixComponents.getLabeledMatrix();

System.out.print(newMatrix[x-1][y-1]);
 
        }            
    }
}

