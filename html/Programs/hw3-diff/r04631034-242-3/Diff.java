/*
.
 * To change this template file, choose Tools | Templates
.
 */

/**
 *
 * @author acer
 */
public class Calculator {

          public Double ans(String s) {
                    String strTemp = """";
                    int index = -1;
                    boolean lastIsNum = false; //前一个字符是否是数字
                    String[] str = new String[s.length()]; // 后缀
                    Stack<String> operationStack = new Stack<String>();
                    Stack<Double> resultStack = new Stack<Double>();
                    for (int i = 0; i < s.length(); i++) {
                              str[i] = """";
                    }
                    for (int i = 0; i < s.length(); i++) {
                              char ch = s.charAt(i);
                              if (ch == ' ') {
                                        continue;
                              }
                              if (ch >= '0' && ch <= '9' || ch == '.') {
                                        if (lastIsNum) {
                                                  str[index] += String.valueOf(ch);
                                        } else {
                                                  str[++index] += String.valueOf(ch);
                                        }
                                        lastIsNum = true;
                              }
                              if (ch == '+' || ch == '-') {
                                        if (i == 0 || s.substring(i - 1, i).equals(""("")) {
                                                  lastIsNum = true;
                                                  str[++index] += String.valueOf(ch);
                                        } else {
                                                  lastIsNum = false;
                                                  while (!operationStack.isEmpty() && !operationStack.peek().equals(""("")) {
                                                            str[++index] += operationStack.pop();
                                                  }
                                                  operationStack.push(String.valueOf(ch));
                                        }
                              }
                              if (ch == '*' || ch == '/') {
                                        lastIsNum = false;
                                        operationStack.push(String.valueOf(ch));
                              }
                              if (ch == '(') {
                                        lastIsNum = false;
                                        operationStack.push(String.valueOf(ch));
                              }
                              if (ch == ')') {
                                        lastIsNum = false;
                                        while (!operationStack.isEmpty() && !operationStack.peek().equals(""("")) {
                                                  str[++index] += operationStack.pop();
                                        }
                                        operationStack.pop();
                              }
                    }
                    if (!operationStack.isEmpty()) {
                              str[++index] += operationStack.pop();
                    }
// System.out.println(new ArrayList(Arrays.asList(str)));
                    index = -1;
                    try {
                              while (index < s.length() && !str[++index].equals("""")) {
                                        strTemp = str[index];
                                        if (isNum(strTemp)) {
                                                  resultStack.push(Double.valueOf(strTemp));
                                        } else {
                                                  double num2 = resultStack.pop();
                                                  double num1 = resultStack.pop();
                                                  if (strTemp.equals(""+"")) {
                                                            resultStack.push(num1 + num2);
                                                  }
                                                  if (strTemp.equals(""-"")) {
                                                            resultStack.push(num1 - num2);
                                                  }
                                                  if (strTemp.equals(""*"")) {
                                                            resultStack.push(num1 * num2);
                                                  }
                                                  if (strTemp.equals(""/"")) {
                                                            resultStack.push(num1 / num2);
                                                  }
                                        }
                              }

                    } catch (Exception e) {
// e.printStackTrace();
                              System.out.println(""the expression is incorrect"");
                              System.exit(1);
                    }
                    return resultStack.pop();
          }

          public static boolean isNum(String str) {
                    return str.matches(""^([+-]?\\d+)(\\.\\d+)?$"");//浮点数 
          }

          /**
           * @param args the command line arguments
           */
          public static void main(String[] args) {
                    // TODO code application logic here
//                    In in = new In(args[0]);
//                    String line = in.readLine();
//                    ;

          }

}

