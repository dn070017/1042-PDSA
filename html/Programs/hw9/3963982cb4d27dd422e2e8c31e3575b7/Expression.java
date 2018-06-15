import java.util.ArrayList;
import java.util.List;

public class Expression{
         
    private Node root;
    
    // DO NOT MODIFY THIS
    public Expression(){}

    // Build a Binary and Return the Root
    public Node Infix2BT(String infix){
    
        Stack<Node> ops = new Stack<>();        
        Stack<Node> vals = new Stack<>();    
        
        boolean flag = false;
        for(int i =0;i < infix.length();i++) {
        String s = infix.substring(i, i+1);
        switch(s) {
            case ""("":
                flag = true;
                break;
            case ""+"" : case ""-"" : case ""*"" : case ""/"" :
                flag = true;
                ops.push(new Node(null,null,s));
                break;
            case "")"":
                flag = true;
                if(!ops.isEmpty()){
                   Node op = ops.pop();
                   op.setRight(vals.pop());
                   op.setLeft(vals.pop());
                   vals.push(op);
                }
                break;
            default:
                if(flag)
                vals.push(new Node(null,null,s));                
                else{
                Node temp = vals.pop();
                temp.setValue(temp.getValue()+s);
                vals.push(temp);
                }
                flag = false;
        }}       
        root = vals.pop();         
        return root;
    }

    public Node[] PrintPrefix(){
        if(root == null) throw new NullPointerException();
        List<Node> p;
        p = new ArrayList<>();
        getPrefix(p,root);
        return p.toArray(new Node[p.size()]);
    }

    public void getPrefix(List<Node> out,Node pf){
        if(pf == null) return;    
        out.add(pf);
        getPrefix(out,pf.getLeft());
        getPrefix(out,pf.getRight());       
    }      
      
    public Node[] PrintPostfix(){
        if(root == null) throw new NullPointerException();        
        List<Node> p;
        p = new ArrayList<>();
        getPostfix(p,root);
        return p.toArray(new Node[p.size()]);
    }
    
    public void getPostfix(List<Node> out,Node pf){
        if(pf == null) return;          
        getPostfix(out,pf.getLeft());
        getPostfix(out,pf.getRight());    
        out.add(pf);
    }          

    public double Evaluation(){
        if(root == null) throw new NullPointerException();
        return getEvaluation(root);
    }
    
    public double getEvaluation(Node pf){
        switch(pf.getValue()){
            case ""+"":
                return getEvaluation(pf.getLeft()) + getEvaluation(pf.getRight());
            case ""-"":
                return getEvaluation(pf.getLeft()) - getEvaluation(pf.getRight());
            case ""*"":
                return getEvaluation(pf.getLeft()) * getEvaluation(pf.getRight());
            case ""/"":
                return getEvaluation(pf.getLeft()) / getEvaluation(pf.getRight());
            default:
                return Double.parseDouble(pf.getValue());
        }
    }      
}

