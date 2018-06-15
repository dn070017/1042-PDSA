import java.io.BufferedReader;
import java.io.FileReader;


public class LabelCC {

    public static void main(String[] args) throws Exception {

        // read file from args[0] in Java 7 style
        try (BufferedReader br = new BufferedReader(new FileReader(args[0]))) {

            // read a line and split by ','
            String[] data = br.readLine().split("","");

            // store the first integer in variable stringCount (number of announced strings)
            int N = Integer.parseInt(data[0]) + 2;
            int Locationx = Integer.parseInt(data[1]) + 1;
            int Locationy = Integer.parseInt(data[2]) + 1;

            // printf in Java (you should comment out or delete this in your final submission           
            int[] id = new int[N * N + 1];
            int[] Label = new int[N * N + 1];
            int[] LabelConnect = new int[N * N + 1];
            int label;
            int labelconnect;
            int labelL;
            int labelU;
            int count = 1;
            String Data = new String();
            String[] Open = new String[2];

            while ((Data = br.readLine()) != null) {
                Open = Data.split("","");
                if (N == 3) {
                    System.out.printf(""%d\n"", 0);
                    return;
                }
                int x = Integer.parseInt(Open[0]) + 1;
                int y = Integer.parseInt(Open[1]) + 1;
                id[N * (x - 1) + y] = 1;

            }
            if (N == 3) {
                System.out.printf(""%d\n"", 1);
                return;
            }
            for (int x = 2; x <= N - 1; x++) {
                for (int y = 2; y <= N - 1; y++) {
                    if (id[N * (x - 1) + y] == 0) {
                        if (Label[N * (x - 1) + y - N] != 0 && Label[N * (x - 1) + y - 1] != 0) {

                            label = Label[N * (x - 1) + y - 1];
                            labelconnect = LabelConnect[label];
                            while (label != labelconnect) {
                                label = LabelConnect[labelconnect];
                                labelconnect = LabelConnect[label];
                            }
                            labelL = label;
                            label = Label[N * (x - 1) + y - N];
                            labelconnect = LabelConnect[label];
                            while (label != labelconnect) {
                                label = LabelConnect[labelconnect];
                                labelconnect = LabelConnect[label];
                            }
                            labelU = label;
                            if (labelU <= labelL) {
                                Label[N * (x - 1) + y] = Label[N * (x - 1) + y - N];
                                LabelConnect[labelL] = LabelConnect[labelU];
                            } else {
                                Label[N * (x - 1) + y] = Label[N * (x - 1) + y - 1];
                                LabelConnect[labelU] = LabelConnect[labelL];
                            }
                        } else if (Label[N * (x - 1) + y - N] == 0 && Label[N * (x - 1) + y - 1] == 0) {
                            Label[N * (x - 1) + y] = count;
                            LabelConnect[count] = count;
                            count++;
                        } else {
                            if (Label[N * (x - 1) + y - N] != 0) {
                                Label[N * (x - 1) + y] = Label[N * (x - 1) + y - N];
                            } else {
                                Label[N * (x - 1) + y] = Label[N * (x - 1) + y - 1];
                            }
                        }
                    }
                }
            }
            label = Label[N * (Locationx - 1) + Locationy];
            labelconnect = LabelConnect[label];
            while (label != labelconnect) {
                label = LabelConnect[labelconnect];
                labelconnect = LabelConnect[label];
            }

            System.out.printf(""%d\n"", label);

        }
    }
}

