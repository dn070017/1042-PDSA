
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/*
.
 * To change this template file, choose Tools | Templates
.
 */
/**
 *
 * @author 許志鵬
 */
public class Expression {

    /**
     * @param args the command line arguments
     */
    private Node root;

    // DO NOT MODIFY THIS
    public Expression() {
    }

    // Build a Binary and Return the Root
    public Node Infix2BT(String infix) {
        String[] to_do = infix.split("" "");

        Node[] arr = new Node[to_do.length];
        for (int i = 0; i < to_do.length; i++) {
            arr[i]=new Node(null,null,to_do[i]);
            

        }
        int breaker = to_do.length;
        while (breaker > 5) {
            int count = 0;
            //how_many_brackets
            for (int i = 0; i < to_do.length; i++) {
                if (arr[i].getValue() == ""("") {
                    count++;
                }
            }

            //remove_brackets
            int f_bott = 0;
            for (int i = 0; i < arr.length; i++) {
                if (arr[i].getValue() == ""("") {
                    count++;
                    if (count == f_bott) {
                        int j;
                        for(j=i;j<arr.length;j++)
                        {
                            if(arr[j].getValue()==""+""||arr[j].getValue()==""-""||arr[j].getValue()==""*""||arr[j].getValue()==""/"")
                            {
                                root=arr[j];
                                break;
                            }
                        }
                       // root=arr[i+2];
                        int n = j-1;
                        while (true) {
                            if (arr[n].getValue() == ""(""||arr[n].getValue() == null) {
                                arr[n]=null;
                                n--;
                            }
                            if (arr[n].getValue() != ""(""&&arr[n].getValue()!=null) {
                                root.setLeft(arr[n]);
                                
                                break;
                                
                            }
                        }
                        
                        int m = j+1;
                        while (true) {
                            if (arr[m].getValue() == "")""||arr[m].getValue()==null) {
                                arr[m]=null;
                                m++;
                            }
                            if (arr[m].getValue() != "")""&&arr[m].getValue()!=null) {
                                root.setRight(arr[m]);
                                
                                break;
                            }
                        }

                    }
                }
            }
            for(int i=0;i<arr.length;i++)
            {
                if(arr[i].getValue()!=""(""&&arr[i].getValue()!="")""&&arr[i].getValue()!=null)
                {
                    breaker++;
                }
            }
            
        }
        
        return root;
    }

    public Node[] PrintPrefix() {
        Node[] prefix = null;
        return prefix;
    }

    public Node[] PrintPostfix() {
        Node[] postfix = null;
        return postfix;
    }

    public double Evaluation() {
        double answer = 0;
        return answer;
    }

    public static void main(String[] args) throws FileNotFoundException, IOException {
        // TODO code application logic here
        try (BufferedReader br = new BufferedReader(new FileReader(args[0]))) {
            String header = br.readLine();
            char[] cArray = header.toCharArray();

            String to_Split = """";

            for (int i = 0; i < cArray.length; i++) {
                if (cArray[i] == ')' || cArray[i] == '(' || cArray[i] == '+' || cArray[i] == '-' || cArray[i] == '*' || cArray[i] == '/') {
                    to_Split += String.valueOf(cArray[i]);
                    to_Split += "" "";
                }
                if (cArray[i] == '0' || cArray[i] == '1' || cArray[i] == '2' || cArray[i] == '3' || cArray[i] == '4' || cArray[i] == '5' || cArray[i] == '6'
                        || cArray[i] == '7' || cArray[i] == '8' || cArray[i] == '9' || cArray[i] == '.') {
                    to_Split += String.valueOf(cArray[i]);
                    if (cArray[i + 1] == '(' || cArray[i + 1] == ')' || cArray[i + 1] == '+' || cArray[i + 1] == '-' || cArray[i + 1] == '*' || cArray[i + 1] == '/') {
                        to_Split += "" "";
                    }
                }
            }
           
            
            
        }
    }

}

