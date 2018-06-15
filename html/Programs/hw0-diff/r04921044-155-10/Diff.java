import java.io.FileReader;
import java.io.BufferedReader;
import java.util.Arrays;

public class Bingo {
    public static void main(String[] args) throws Exception {
        try (BufferedReader buffer = new BufferedReader(new FileReader(args[0]))) {
            String[] numbersString = buffer.readLine().split("","");
            int crossCount = Integer.parseInt(numbersString[0]);
            int mapLength = Integer.parseInt(numbersString[1]);

            String[] crossNames = buffer.readLine().split("","");

            Boolean[][] map = new Boolean[mapLength][mapLength];

            for (int i=0; i<mapLength; ++i) {
                String[] mapNames = buffer.readLine().split("","");
                for (int j=0; j<mapLength; ++j) {
                    if (Arrays.asList(crossNames).contains(mapNames[j])) {
                        map[i][j] = true;
                    }
                    else {
                        map[i][j] = false;
                    }
                }
            }

            Bingo bingo = new Bingo();
            System.out.println(bingo.checkLineCount(map, mapLength));
        }
    }

    public int checkLineCount(Boolean[][] map, int mapLength) {
        int count = 0;

        // count row
        for (int i=0; i<mapLength; ++i) {
            Boolean shouldIncrement = true;
            for (int j=0; j<mapLength; ++j) {
                if (!map[i][j]) {
                    shouldIncrement = false;
                    break;
                }
            }
            if (shouldIncrement) {
                count++;
            }
        }

        // count column
        for (int i=0; i<mapLength; ++i) {
            Boolean shouldIncrement = true;
            for (int j=0; j<mapLength; ++j) {
                if (!map[j][i]) {
                    shouldIncrement = false;
                    break;
                }
            }
            if (shouldIncrement) {
                count++;
            }
        }

        // count diagonal
        Boolean shouldIncrement = true;
        for (int i=0; i<mapLength; ++i) {
            if (!map[i][i]) {
                shouldIncrement = false;
                break;
            }
        }
        if (shouldIncrement) {
            count++;
        }

        shouldIncrement = true;
        for (int i=0; i<mapLength; ++i) {
            if (!map[i][mapLength - i - 1]) {
                shouldIncrement = false;
                break;
            }
        }
        if (shouldIncrement) {
            count++;
        }

        return count;
    }
}
