/*
.
 * To change this template file, choose Tools | Templates
.
 */

/**
 *
 * @author user
 */
public class Calculator {

    public Double ans(String e) {
        String[] data = e.split("" "");
        String Str = ""[0-9\\.]+"";
        LinkedStack<String> num = new LinkedStack<String>();
        String str = new String();
        int count1 = 0;
        int count2 = 0;

        for (int i = 0; i < data.length; i++) {
            double add = 0.0;
            for (int j = i; j >= 0; j--) {
                if ((data[i].equals(""(""))) {
                    if (data[j + 1].matches(Str)) {
                        double a = Double.parseDouble(data[j + 1]);
                        if (add > 0.0) {
                            add = count(a, data[j + 2], add);
                        }
                        if (data[j + 3].matches(Str)) {
                            //System.out.println(data[i-1]);
                            double c = Double.parseDouble(data[j + 3]);
                            add = count(a, data[j + 2], c);
                            //System.out.println(add);
                        }

                    }
                }
                if ((data[j].equals("")""))) {
                    break;
                }

            }
            if (add > 0.0) {
                String adds = Double.toString(add);
                //System.out.println(adds);
                num.push(adds);
                count1 += 1;
            }
            if (data[i].equals("")"")) {
                if (((i + 1 < data.length)) && data[i + 1].matches(""[\\+\\-\\*\\/]+"")) {
                    //System.out.println(data[i+1]);
                    num.push(data[i + 1]);
                    count2 += 1;
                }
            }
        }

        String[] cal = new String[count1 + count2];

        int counts = count1 + count2 - 1;
        while (!(num.isEmpty())) {
            cal[counts] = num.pop();
            //System.out.println(cal[i]);
            counts = counts - 1;
        }
        /*for(int j = 0; j<count1+count2;j++){
         System.out.println(cal[j]);
         }*/
        double adding = 0.0;
        int index = 0;
        for (int i = 1; i < count1 + count2; i++) {
            if (cal[i].matches(""[\\+\\-\\*\\/]+"")) {
                double a_1 = Double.parseDouble(cal[i - 1]);
                double c_1 = Double.parseDouble(cal[i + 1]);
                adding = count(a_1, cal[i], c_1);
                index = i;
                //System.out.println(adding);
                break;
            }
        }
        for (int i = index; i < count1 + count2; i++) {
            double c_2 = Double.parseDouble(cal[i + 3]);
            adding = count(adding, cal[i + 2], c_2);
            if ((i + 3) == count1 + count2 - 1) {
                break;
            }
        }
        //System.out.println(adding);
        return adding;
    }

    public Double count(double a, String b, double c) {
        double count = 0.0;
        if (b.equals(""+"")) {
            count = a + c;
        }
        if (b.equals(""-"")) {
            count = a - c;
        }
        if (b.equals(""*"")) {
            count = a * c;
        }
        if (b.equals(""/"")) {
            count = a / c;
        }
        return count;
    }

}

