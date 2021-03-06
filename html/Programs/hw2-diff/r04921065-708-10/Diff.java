

import java.io.FileReader;
import java.io.BufferedReader;

public class LabelCC {

    public static void main(String[] args) throws Exception {

        try (BufferedReader br = new BufferedReader(new FileReader(args[0]))) {

            String[] data = br.readLine().split("","");

            int size = Integer.parseInt(data[0]);
            int[] target = new int[2];
            target[0] = Integer.parseInt(data[1]);
            target[1] = Integer.parseInt(data[2]);

            String open;

            int num = 0;
            String openstring = """";
            while ((open = br.readLine()) != null) {
                openstring = openstring + open + "" "";
                ++num;
            }
            String[] temp = new String[num];
            String[] temp2 = new String[num];
            int[][] match = new int[num][2];
            temp = openstring.split("" "");
            for (int i = 0; i < num; ++i) {
                temp2 = temp[i].split("","");
                for (int j = 0; j < 2; ++j) {
                    match[i][j] = Integer.parseInt(temp2[j]);
                }
            }
            int[] state = new int[size * size];
            int[] number = new int[size * size];
            int[] idt = new int[size * size];

            for (int i = 0; i < size * size; ++i) {
                number[i] = 0;  //陣列 2D->1D
                state[i] = 1;  //label 陣列 2D-1D
                idt[i] = i;    //陣列
            }
            QuickFindUF wei = new QuickFindUF(size * size);
            int now = 1;
            int reg = 0;   //index

            for (int i = 0; i < num; ++i) {    
                reg = (match[i][0] - 1) * size + match[i][1] - 1; //index
                state[reg] = 0;  // block location
            }

            for (int i = 0; i < size * size; ++i) {
                if (state[i] == 1) {
                    int neigh = 0;
                    if (i % size != 0 && state[i - 1] == 1 && i - size > -1 && state[i - size] == 0 || i % size != 0 && state[i - 1] == 1 && i - size < 0) {//111111111111
                        wei.union(idt[i], idt[i - 1]);
                        number[i] = number[i - 1];
                        ++neigh;
                    }
                    if (i - size > -1 && state[i - size] == 1 && i % size != 0 && state[i - 1] == 1) {//22222222
                        if (number[i - 1] > number[i - size]) {
                            wei.union(idt[i], idt[i - size]);
                            wei.union(idt[i-1], idt[i]);
                            number[i] = number[i - size];
                            ++neigh;
                        } else {
                            wei.union(idt[i], idt[i - 1]);
                            wei.union(idt[i-size], idt[i]);
                            number[i] = number[i - 1];
                            ++neigh;
                        }

                    }
                    if (i - size > -1 && state[i - size] == 1 && i % size != 0 && state[i - 1] == 0 || i - size > -1 && state[i - size] == 1 && i % size == 0) {
                        wei.union(idt[i], idt[i - size]);
                        number[i] = number[i - size];
                        ++neigh;

                    }

                    if (neigh == 0) {
                        number[i] = now;
                        ++now;
                    }

                }
            }

            for (int i = 0; i < size * size; ++i) {
                number[i] = number[wei.find(i)];
            }
            
            System.out.println(number[ (target[0]-1)*size+target[1]-1  ]);

        }

    }
}

