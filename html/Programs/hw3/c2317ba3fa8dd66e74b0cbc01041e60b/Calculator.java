import java.util.Stack;
import java.util.Scanner;

/**
 *
 * @author Jayden
 */
public class Calculator {

    public static void main(String[] args) throws Exception {
        String abc = ""( ( ( 1 + ( 12.5 * 5 ) ) - ( 3 * 4 ) ) + ( 4 / 5 ) ) "";
        double aa;
        aa = ans(abc);
        System.out.println(aa);
    }

    static public double ans(String e) {
        //這個函式要用static，才能讓上面的main函示取用(不知道為啥)，但是如果是給別的class用就不用寫
        double y = 0;//輸出的結果

        Stack<String> str = new Stack<String>();
        Stack<Double> dou = new Stack<Double>();
        //兩個stack用來記錄兩種型態的資料
        double a ,b ;//ab是用來紀錄兩個pop出來的數字
        String c;//c則是用來記錄pop出來的運算符號

        Scanner sca = new Scanner(e);
        String label;
        //scanner是讀取一個一個單字的用法，label則是存讀進來的是什麼東西
        while (sca.hasNext()) {
            if (sca.hasNextDouble()) {
                dou.push(sca.nextDouble());//如果下一個是數字，就記錄數字
            } else {//其餘的就是符號
                label = sca.next();
                if (label.equals(""+"") || label.equals(""-"") || label.equals(""*"") || label.equals(""/"")) {
                    str.push(label);
                } else if (label.equals("")"")) {
                    b = dou.pop();
                    a = dou.pop();
                    c = str.pop();
                    if (c.equals(""+"")) {
                        y = a + b;
                    } else if (c.equals(""-"")) {
                        y = a - b;
                    } else if (c.equals(""*"")) {
                        y = a * b;
                    } else if (c.equals(""/"")) {
                        y = a / b;
                    }
                    dou.push(y);//再存入剛剛算出來的y
                }
            }
        }

        return y;
    }
}

