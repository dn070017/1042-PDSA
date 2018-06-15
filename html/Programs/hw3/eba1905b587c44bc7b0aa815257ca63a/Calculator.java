import java.util.NoSuchElementException;

/**
 *
 * @author Sophia
 */
public class Calculator {
    private double answer;
    private Stack<String> st;
    private Stack<String> op;
    Calculator(){
        st = new Stack<String>();
        op = new Stack<String>();
    }
    private void Do(){
        try{
        double right = Double.parseDouble(st.pop());
        double left = Double.parseDouble(st.pop());
        //System.out.println(right);
        //System.out.println(left);
        String s = op.pop();
        switch(s){
            case ""+"":
                    st.push(String.valueOf(left + right));
                    break;
                case ""-"":
                    st.push(String.valueOf(left - right));
                    break;
                case ""*"":
                    st.push(String.valueOf(left * right));
                    break;
                case ""/"":
                    st.push(String.valueOf(left / right));
                    break;
                default: 
                    //System.out.println(s);
        }
        //System.out.println(st.peek());
        op.pop();
        }catch(NoSuchElementException ex){
        
        }
    } 

    
    
    public Double ans(String e){
        String data[] = e.split("" "");
        for(int i = 0; i<data.length; i++){
            switch(data[i]){
                case ""/0"":
                    break;
                case ""+"":
                    op.push(data[i]);
                    break;
                case ""-"":
                    op.push(data[i]);
                    break;
                case ""*"":
                    op.push(data[i]);
                    break;
                case ""/"":
                    op.push(data[i]);
                    break;
                case ""("":
                    op.push(data[i]);
                    break;
                case "")"":
                        Do();
                    break;
                default:
                        st.push(data[i]);
            } 
        }
    answer = Double.parseDouble(st.pop());
    return answer;
    }

}
