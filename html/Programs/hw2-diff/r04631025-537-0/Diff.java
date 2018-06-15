
import java.io.FileReader;
import java.io.BufferedReader;

public class LabelCC {

    private int[][] matrix;
    private int[] id;
    public static int N;
    private int label;
    private int[] rank; 
    UF uf;
    

    LabelCC(int n) {
        matrix = new int[n][n];
        id = new int[n * n];
        rank =new int[n * n];
        label = 1;
        N = n;
           
        int k = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                matrix[i][j] = k;
                k++;
            }
        }
        for (int i = 0; i < n * n; i++) {
            id[i] = 1;
            rank[i]=0;
        }

    }
    public void newunion (int n){
        uf =new UF(label);        
    }
    public void child(int x,int y){
        if(x!=0&&y!=0){
            if(id[matrix[x-1][y]]==1&& id[matrix[x][y-1]]==1 && id[matrix[x][y]]==1){
                if(rank[matrix[x-1][y]]>rank[matrix[x][y-1]]){
                uf.union(rank[matrix[x][y-1]]-1,rank[matrix[x-1][y]]-1);}
                else{
                        uf.union(rank[matrix[x-1][y]]-1,rank[matrix[x][y-1]]-1);

                }
            }
        }
    }

    public void block(int x, int y) {
        id[matrix[x-1][y-1]]=0;
    }
    
    public int getlabel (int x ,int y){
        if(rank[matrix[x-1][y-1]]!=0){
        return uf.find(rank[matrix[x-1][y-1]]-1)+1;}
        else{
            return 0;
        }
    } 

    public void firstpass(int x, int y) {
        if (x == 0 & y == 0) {
            rank[matrix[x][y]] = label;
        } else {
            if (x == 0&&id[matrix[x][y]]==1) {
                if (id[matrix[x][y - 1]] == 0) {
                    label++;
                    rank[matrix[x][y]] = label;
                } else {
                    rank[matrix[x][y]] = rank[matrix[x][y-1]];
                }
            }
            if (y == 0&&id[matrix[x][y]]==1) {
                if (id[matrix[x - 1][y]] ==0 ) {
                    label++;
                    rank[matrix[x][y]] = label;
                } else {
                    rank[matrix[x][y]] = rank[matrix[x - 1][y]];
                }
            }
            if (id[matrix[x][y]] == 1 && x != 0 && y != 0) {
                if (id[matrix[x - 1][y]] == 1 && id[matrix[x][y - 1]] == 1) {
                    if (rank[matrix[x - 1][y]] > rank[matrix[x][y - 1]]) {
                        rank[matrix[x][y]] = rank[matrix[x][y - 1]];
                    }
                    else{
                        rank[matrix[x][y]]=rank[matrix[x-1][y]];
                    }
                }else if(id[matrix[x-1][y]] == 1 && id[matrix[x][y-1]]==0){
                    rank[matrix[x][y]]=rank[matrix[x-1][y]];
                }else if(id[matrix[x-1][y]]==0 && id[matrix[x][y-1]] == 1){
                    rank[matrix[x][y]]=rank[matrix[x][y-1]];
                }else{
                    label++;
                    rank[matrix[x][y]] = label;
                }
            }
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception {
        try (BufferedReader br = new BufferedReader(new FileReader(args[0]))) {
            String data = br.readLine();
            String[] input = data.split("","");
            int number = Integer.parseInt(input[0]);
            int destx = Integer.parseInt(input[1]);
            int desty = Integer.parseInt(input[2]);
            
            LabelCC LC = new LabelCC(number);
            while (true) {
                String position = br.readLine();
                if (position == null) {
                    break;
                }
                String[] place = position.split("","");
                int x = Integer.parseInt(place[0]);
                int y = Integer.parseInt(place[1]);
                LC.block(x, y);
            }
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    LC.firstpass(i, j);
                }
            }
            LC.newunion(N);
            for(int i=0;i<N;i++){
                for(int j=0;j<N;j++){
                    LC.child(i,j);
                }
            }
            for(int i=1 ;i<=N;i++)
            {
                for(int j=1;j<=N;j++)
                {
                    int answer=LC.getlabel(i, j);
                    System.out.printf(""%d"",answer);
                }
                System.out.printf(""\n"");
            }
            int answer=LC.getlabel(destx, desty);
            System.out.printf(""%d"",answer);
        }
        
        // TODO code application logic here
    }

}

