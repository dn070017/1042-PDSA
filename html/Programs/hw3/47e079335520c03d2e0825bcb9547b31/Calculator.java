    public Double ans (String e) {
.
        String line;
        String target = """";

        while ((line = in.readLine()) != null) {
            target += line + "" "";
        }
        String[] numbersArray = target.split("" "");
        //System.out.println(numbersArray[3]);
        Stack<String> S1 = new Stack<String>();
        Stack<String> S2 = new Stack<String>();
        Stack<String> S3 = new Stack<String>();
        Stack<String> S4 = new Stack<String>();
        int lon = numbersArray.length;

        for (int i = 0; i < lon; i++) {

            if (""+"".equals(numbersArray[i]) || ""-"".equals(numbersArray[i]) || ""*"".equals(numbersArray[i]) || ""/"".equals(numbersArray[i])) {
                S1.push(numbersArray[i]);

            } else if (""("".equals(numbersArray[i])) {
                S1.push(numbersArray[i]);

            } else if ("")"".equals(numbersArray[i])) {
                while (S1.peek() != ""("") {
                    S2.push(S1.pop());
                    S1.pop();
                    break;
                }
            } else if (i == (lon - 1)) {
                while (!S1.empty()) {
                    S2.push(S1.pop());
                    break;
                }
            } else {
                S2.push(numbersArray[i]);
            }
        }

        System.out.println(S2);
        while (!S2.empty()) {   //逆續輸出
            S3.push(S2.pop());
        }
        System.out.println(S3);

        while (!S3.empty()) {   //處理後綴
            String pop = S3.pop();
            if (""+"".equals(pop)) {
                double n1 = Double.parseDouble(S4.pop());
                double n2 = Double.parseDouble(S4.pop());
                double n3 = n2 + n1;
                S4.push(String.valueOf(n3));
            } else if (""-"".equals(pop)) {
                double n1 = Double.parseDouble(S4.pop());
                double n2 = Double.parseDouble(S4.pop());
                double n3 = n2 - n1;
                S4.push(String.valueOf(n3));
            } else if (""*"".equals(pop)) {
                double n1 = Double.parseDouble(S4.pop());
                double n2 = Double.parseDouble(S4.pop());
                double n3 = n2 * n1;
                S4.push(String.valueOf(n3));
            } else if (""/"".equals(pop)) {
                double n1 = Double.parseDouble(S4.pop());
                double n2 = Double.parseDouble(S4.pop());
                double n3 = n2 / n1;
                S4.push(String.valueOf(n3));
            } else {
                S4.push(pop);
            }
        }
        
        double ans = Double.parseDouble(S4.pop()); 
        return ans;
    }
