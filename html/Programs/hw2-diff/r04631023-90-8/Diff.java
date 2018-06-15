import java.io.BufferedReader;
import java.io.FileReader;

/*
.
 * To change this template file, choose Tools | Templates
.
 */
/**
 *
 * @author SimonHan
 */
public class LabelCC {

    public static void main(String[] args) throws Exception {

        // read file from args[0] in Java 7 style
        try (BufferedReader br = new BufferedReader(new FileReader(args[0]))) {

            // read a line and split by ','
            String[] data = br.readLine().split("","");

            // store the first integer in variable stringCount (number of announced strings)
            int N = Integer.parseInt(data[0]);
            int Locationx = Integer.parseInt(data[1]);
            int Locationy = Integer.parseInt(data[2]);

            // printf in Java (you should comment out or delete this in your final submission           
            int[] id = new int[N * N + 1];
            int[] Label = new int[N * N + 1];
            int[] LabelConnect = new int[N * N + 1];
            int count = 1;
            String Data = new String();
            String[] Open = new String[2];

            while ((Data = br.readLine()) != null) {
                if (N == 1) {
                    System.out.printf(""%d\n"", 0);
                    return;
                }
                Open = Data.split("","");
                if (Open[0].isEmpty()) {
                    System.out.printf(""%d\n"", 0);
                    return;
                }
                int x = Integer.parseInt(Open[0]);
                int y = Integer.parseInt(Open[1]);
                if (x > N || y > N || x < 1 || y < 1) {
                    System.out.printf(""%d\n"", 0);
                    return;
                }
                id[N * (x - 1) + y] = 1;
            }
            if (N == 1) {
                System.out.printf(""%d\n"", 1);
                return;
            }
            for (int x = 1; x <= N; x++) {
                for (int y = 1; y <= N; y++) {
                    if (id[N * (x - 1) + y] == 0) {
                        if (x != 1 && x != N && y != 1 && y != N) {
                            if (Label[N * (x - 1) + y - N] != 0 && Label[N * (x - 1) + y - 1] != 0) {
                                if (Label[N * (x - 1) + y - N] <= Label[N * (x - 1) + y - 1]) {
                                    Label[N * (x - 1) + y] = Label[N * (x - 1) + y - N];
                                    LabelConnect[Label[N * (x - 1) + y - 1]] = Label[N * (x - 1) + y - N] ;
                                } else {
                                    Label[N * (x - 1) + y] = Label[N * (x - 1) + y - 1];
                                    LabelConnect[Label[N * (x - 1) + y - N]] = Label[N * (x - 1) + y - 1] ;
                                }
                            } else if (Label[N * (x - 1) + y - N] == 0 && Label[N * (x - 1) + y - 1] == 0) {
                                Label[N * (x - 1) + y] = count;
                                LabelConnect[count] = count ;
                                count++;
                            } else {
                                if (Label[N * (x - 1) + y - N] != 0) {
                                    Label[N * (x - 1) + y] = Label[N * (x - 1) + y - N];
                                } else {
                                    Label[N * (x - 1) + y] = Label[N * (x - 1) + y - 1];
                                }
                            }
                        } else if (x == 1 && y == 1) {
                            Label[N * (x - 1) + y] = count;
                            count++;
                        } else if (x == 1 && y == N) {
                            if (Label[N * (x - 1) + y - 1] != 0) {
                                Label[N * (x - 1) + y] = Label[N * (x - 1) + y - 1];

                            } else {
                                Label[N * (x - 1) + y] = count;
                                LabelConnect[count] = count ;
                                count++;
                            }
                        } else if (x == N && y == 1) {
                            if (Label[N * (x - 1) + y - N] != 0) {
                                Label[N * (x - 1) + y] = Label[N * (x - 1) + y - N];
                            } else {
                                Label[N * (x - 1) + y] = count;
                                LabelConnect[count] = count ;
                                count++;
                            }
                        } else if (x == N && y == N) {
                            if (Label[N * (x - 1) + y - N] != 0 && Label[N * (x - 1) + y - 1] != 0) {
                                if (Label[N * (x - 1) + y - N] <= Label[N * (x - 1) + y - 1]) {
                                    Label[N * (x - 1) + y] = Label[N * (x - 1) + y - N];
                                    LabelConnect[Label[N * (x - 1) + y - 1]] = Label[N * (x - 1) + y - N] ;
                                } else {
                                    Label[N * (x - 1) + y] = Label[N * (x - 1) + y - 1];
                                    LabelConnect[Label[N * (x - 1) + y - N]] = Label[N * (x - 1) + y - 1] ;
                                }
                            } else if (Label[N * (x - 1) + y - N] == 0 && Label[N * (x - 1) + y - 1] == 0) {
                                Label[N * (x - 1) + y] = count;
                                LabelConnect[count] = count ;
                                count++;
                            } else {
                                if (Label[N * (x - 1) + y - N] != 0) {
                                    Label[N * (x - 1) + y] = Label[N * (x - 1) + y - N];
                                } else {
                                    Label[N * (x - 1) + y] = Label[N * (x - 1) + y - 1];
                                }
                            }
                        } else if (x == 1) {
                            if (Label[N * (x - 1) + y - 1] != 0) {
                                Label[N * (x - 1) + y] = Label[N * (x - 1) + y - 1];

                            } else {
                                Label[N * (x - 1) + y] = count;
                                LabelConnect[count] = count ;
                                count++;
                            }
                        } else if (x == N) {
                            if (Label[N * (x - 1) + y - N] != 0 && Label[N * (x - 1) + y - 1] != 0) {
                                if (Label[N * (x - 1) + y - N] <= Label[N * (x - 1) + y - 1]) {
                                    Label[N * (x - 1) + y] = Label[N * (x - 1) + y - N];
                                    LabelConnect[Label[N * (x - 1) + y - 1]] = Label[N * (x - 1) + y - N] ;
                                } else {
                                    Label[N * (x - 1) + y] = Label[N * (x - 1) + y - 1];
                                    LabelConnect[Label[N * (x - 1) + y - N]] = Label[N * (x - 1) + y - 1] ;
                                }
                            } else if (Label[N * (x - 1) + y - N] == 0 && Label[N * (x - 1) + y - 1] == 0) {
                                Label[N * (x - 1) + y] = count;
                                LabelConnect[count] = count ;
                                count++;
                            } else {
                                if (Label[N * (x - 1) + y - N] != 0) {
                                    Label[N * (x - 1) + y] = Label[N * (x - 1) + y - N];
                                } else {
                                    Label[N * (x - 1) + y] = Label[N * (x - 1) + y - 1];
                                }
                            }
                        } else if (y == 1) {
                            if (Label[N * (x - 1) + y - N] != 0) {
                                Label[N * (x - 1) + y] = Label[N * (x - 1) + y - N];
                            } else {
                                Label[N * (x - 1) + y] = count;
                                LabelConnect[count] = count ;
                                count++;
                            }
                        } else if (y == N) {
                            if (Label[N * (x - 1) + y - N] != 0 && Label[N * (x - 1) + y - 1] != 0) {
                                if (Label[N * (x - 1) + y - N] <= Label[N * (x - 1) + y - 1]) {
                                    Label[N * (x - 1) + y] = Label[N * (x - 1) + y - N];
                                    LabelConnect[Label[N * (x - 1) + y - 1]] = Label[N * (x - 1) + y - N] ;
                                } else {
                                    Label[N * (x - 1) + y] = Label[N * (x - 1) + y - 1];
                                    LabelConnect[Label[N * (x - 1) + y - N]] = Label[N * (x - 1) + y - 1] ;
                                }
                            } else if (Label[N * (x - 1) + y - N] == 0 && Label[N * (x - 1) + y - 1] == 0) {
                                Label[N * (x - 1) + y] = count ;
                                LabelConnect[count] = count ;
                                count++;
                            } else {
                                if (Label[N * (x - 1) + y - N] != 0) {
                                    Label[N * (x - 1) + y] = Label[N * (x - 1) + y - N] ;
                                }else{
                                    Label[N * (x - 1) + y] = Label[N * (x - 1) + y - 1] ;
                                }
                            }
                        }
                    }
                }
            }           
            for (int x = 1; x <= N; x++) {
                for (int y = 1; y <= N; y++) {
                    if (id[N * (x - 1) + y] == 0) {
                        while(LabelConnect[Label[N * (x - 1) + y]]!=Label[N * (x - 1) + y]){
                            Label[N * (x - 1) + y] = LabelConnect[Label[N * (x - 1) + y]] ;
                        }
                    }
                }
            }
            if (id[(N * (Locationx - 1) + Locationy)] == 1) {
                System.out.printf(""%d\n"", 0) ;
                return ;
            }
            
            /*
            for(int i = 1; i<=7;i++){
                System.out.printf(""%d\n"", LabelConnect[i]);
            }

            
            
            for(int i = 1, Cal = 0;i<=N*N;i++){
                if(Cal == N-1){
                    System.out.printf(""%d\n"", Label[i]);
                    Cal = 0 ;
                }else{
                    System.out.printf(""%d "", Label[i]);
                    Cal++ ;
                }
                
            }
            /*
            for (int i = 1; i <= N * N; i++) {
                if (id[i] == 0) {
                    if (Arrays.binarySearch(Label, uf.find(i)) < 0) {
                        Label[count] = uf.find(i);
                        count++;
                        Arrays.sort(Label);
                    }
                }
            }
            */
            System.out.printf(""%d\n"", Label[N * (Locationx - 1) + Locationy]);
                    
        }
    }
}

