
/**
 * @author Cyuan
 */
import java.io.FileReader;
import java.io.BufferedReader;
//import edu.princeton.cs.algs4.UF;

public class Percolation {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception {
//----------------Read the first line and store the matrix size---------------//
        try (BufferedReader br = new BufferedReader(new FileReader(args[0]))) {
            String FirstLine = br.readLine();
            int size = Integer.parseInt(FirstLine);
            int a = 0;
            int b = 0;
//----------------Set the matrix and initialized to be zero-------------------//
            int[][] matrix = new int[size][size];
            for (int i = 0; i < size; i++) {
                for (int j = 0; j < size; j++) {
                    matrix[i][j] = 0;
                }
            }
//----------------------------------------------------------------------------//

            String str = null;
            while ((str = br.readLine()) != null) {
                String[] data = str.split("","");

                int x = Integer.parseInt(data[0]) - 1; //matrix row
                int y = Integer.parseInt(data[1]) - 1; //matrix column

                //matrix[x-1][y-1]=0 (block), 1 (open) , 2 (full)
                //open the read site
                matrix[x][y] = 1;
                //test for neighbor
                //when the site is on the top

                if (x == 0) {
                    matrix[x][y] = 2;
                    //and it is on the left hand
                    if (y == 0) {
                        //test for right and downstair
                        if (matrix[x][y + 1] == 1) {
                            matrix[x][y + 1] = 2;
                        }
                        if (matrix[x + 1][y] == 1) {
                            matrix[x + 1][y] = 2;
                        }
                    }
                    //and it is on the right hand
                    if (y == size - 1) {
                        //test for left and downstair
                        if (matrix[x][y - 1] == 1) {
                            matrix[x][y - 1] = 2;
                        }
                        if (matrix[x + 1][y] == 1) {
                            matrix[x + 1][y] = 2;
                        }
                    }
                    //else test for right,left and downstair
                    if (y > 0 && y < size - 1) {
                        if (matrix[x][y + 1] == 1) {
                            matrix[x][y + 1] = 2;
                        }
                        if (matrix[x][y - 1] == 1) {
                            matrix[x][y - 1] = 2;
                        }
                        if (matrix[x + 1][y] == 1) {
                            matrix[x + 1][y] = 2;
                        }
                    }
                }

                //when site is on the bottom
                //it  needs to test for neighbor is 2 or block
                if (x == size - 1) {
                    //and it is on the left hand
                    if (y == 0) {
                        if (matrix[x][y + 1] == 2 || matrix[x - 1][y] == 2) {
                            matrix[x][y] = 2;
                            //test for righthand and upstair
                            if (matrix[x][y + 1] == 1) {
                                matrix[x][y + 1] = 2;
                            }

                            if (matrix[x - 1][y] == 1) {
                                matrix[x - 1][y] = 2;
                            }
                        } else {
                            matrix[x][y] = 1;
                        }
                    }
                    //and it is on the right hand
                    if (y == size - 1) {
                        if (matrix[x][y - 1] == 2 || matrix[x - 1][y] == 2) {
                            matrix[x][y] = 2;
                            ////test for lefthand and downstair
                            if (matrix[x][y - 1] == 1) {
                                matrix[x][y - 1] = 2;
                            }
                            if (matrix[x - 1][y] == 1) {
                                matrix[x - 1][y] = 2;
                            }
                        } else {
                            matrix[x][y] = 1;
                        }

                    }
                    //else test for right,left and upstair
                    if (y > 0 && y < size - 1) {
                        if (matrix[x][y + 1] == 2 || matrix[x][y - 1] == 2 || matrix[x - 1][y] == 2) {
                            matrix[x][y] = 2;
                            if (matrix[x][y + 1] == 1) {
                                matrix[x][y + 1] = 2;
                            }
                            if (matrix[x][y - 1] == 1) {
                                matrix[x][y - 1] = 2;
                            }
                            if (matrix[x - 1][y] == 1) {
                                matrix[x - 1][y] = 2;
                            }
                        } else {
                            matrix[x][y] = 1;
                        }
                    }
                }

                //else row (not 0 and size-1)
                if (x > 0 && x < size - 1) {
                    //when it is on the lefthand
                    if (y == 0) {
                        if (matrix[x - 1][y] == 2 || matrix[x + 1][y] == 2 || matrix[x][y + 1] == 2) {
                            matrix[x][y] = 2;
                            //test for left,upstair and downstair
                            if (matrix[x - 1][y] == 1) {
                                matrix[x - 1][y] = 2;
                            }
                            if (matrix[x + 1][y] == 1) {
                                matrix[x + 1][y] = 2;
                            }
                            if (matrix[x][y + 1] == 1) {
                                matrix[x][y + 1] = 2;
                            }
                        } else {
                            matrix[x][y] = 1;
                        }

                    }
                    //when it is on the righthand
                    if (y == size - 1) {
                        if (matrix[x - 1][y] == 2 || matrix[x + 1][y] == 2 || matrix[x][y - 1] == 2) {
                            matrix[x][y] = 2;
                            //test for right,upstair and downstair
                            if (matrix[x - 1][y] == 1) {
                                matrix[x - 1][y] = 2;
                            }
                            if (matrix[x + 1][y] == 1) {
                                matrix[x + 1][y] = 2;
                            }
                            if (matrix[x][y - 1] == 1) {
                                matrix[x][y - 1] = 2;
                            }
                        } else {
                            matrix[x][y] = 1;
                        }
                    }

                }
                if (matrix[x][y] == 2 && x == size - 1) {
                    System.out.println(Integer.parseInt(data[0]) + "","" + Integer.parseInt(data[1]));
                    System.exit(0);
                }
            }

            System.out.println(""-1"");
            //show for debug
            //for (int i = 0; i < size; i++) {
            //    for (int j = 0; j < size; j++) {
            //        System.out.print(matrix[i][j] + "" "");
            //   }
            //   System.out.println("""");
            // }

        }

    }

}
