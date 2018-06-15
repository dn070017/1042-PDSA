/*
.
 * To change this template file, choose Tools | Templates
.
 */

//import edu.princeton.cs.algs4.*;
/**
 *
 * @author user
 */
public class Calculator {

          public Double ans(String e) {
                    String[] line = e.split("" "");
                    Stack<String> num = new Stack<String>();
                    Stack<String> cha = new Stack<String>();

                    String add = ""+"";
                    String min = ""-"";
                    String time = ""*"";
                    String div = ""/"";
                    String right = "")"";
                    String left = ""("";

                    for (int i = 0; i < line.length; i++) {
//                              System.out.print(line[i] + "" "");
                    }
//                    System.out.println("""");

                    for (int i = 0; i < line.length; i++) {
                              if (line[i].equals(add) || line[i].equals(min) || line[i].equals(div) || line[i].equals(time) || line[i].equals(left)) {
                                        cha.push(line[i]);
//                                        System.out.println(""1 "" + line[i]);
                              } else if (line[i].equals(right)) {
                                        while (!cha.peek().equals(left)) {
                                                  num.push(cha.pop());
//                                                  System.out.println(""count"");
                                        }
                                        cha.pop();
//                                        System.out.println(""2 "" + line[i]);
                              } else {
                                        num.push(line[i]);
//                                        System.out.println(""3 "" + line[i]);
                              }
                    }

                    int storage = num.size();
                    for (int j = 0; j < storage; j++) {
                              cha.push(num.pop());
//                              System.out.println(num.pop());
                    }
                    String temp;
                    while (cha.isEmpty() == false) {
                              temp = cha.pop().toString();

                              if (temp.equals(add) || temp.equals(min) || temp.equals(div) || temp.equals(time)) {
                                        double cal_num_sce = Double.parseDouble(num.pop().toString());
                                        double cal_num_first = Double.parseDouble(num.pop().toString());

                                        if (temp.equals(add)) {
                                                  num.push(Double.toString(cal_num_first + cal_num_sce));
                                        } else if (temp.equals(min)) {
                                                  num.push(Double.toString(cal_num_first - cal_num_sce));
                                        } else if (temp.equals(time)) {
                                                  num.push(Double.toString(cal_num_first * cal_num_sce));
                                        } else if (temp.equals(div)) {
                                                  num.push(Double.toString(cal_num_first / cal_num_sce));
                                        }
                              } else {
                                        num.push(temp);
                              }

                    }
//                    System.out.println(num.pop());
//                    System.out.println(""""); 

                    return Double.valueOf(num.pop()).doubleValue();
          }

          public static void main(String[] args) {
                    // TODO code application logic here
//                    In in = new In(args[0]);
//                   Calculator ca = new Calculator();
//                    System.out.println(ca.ans(in.readLine()));
          }

}

