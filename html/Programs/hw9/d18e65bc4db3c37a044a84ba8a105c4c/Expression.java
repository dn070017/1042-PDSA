import java.io.*;
import java.util.*;
public class Expression{
  
    private Node root ;
    int treesize = 0 ;
    public int i = 0 ;
    // DO NOT MODIFY THIS
    public Expression(){}

    public boolean isOperator(String judge){
if(judge.equals(""+"")||judge.equals(""-"")||judge.equals(""*"")||judge.equals(""/""))
    return true ;
else 
    return false ;
//if(judge.equals(""+""))
//    return 1 ;
//else if(judge.equals(""-""))
//    return 2 ;
//else if(judge.equals(""*""))
//    return 3 ;
//else if(judge.equals(""/""))
//    return 4 ;
//else
//    return 5 ;        
    }
    
    
private void Calculate(Stack<Double> stack, String operation) {
                    double sce = stack.pop();
                    double fir = stack.pop();

                    if (operation.equals(""+"")) {
                              stack.push(fir + sce);
                    } else if (operation.equals(""-"")) {
                              stack.push(fir - sce);
                    } else if (operation.equals(""*"")) {
                              stack.push(fir * sce);
                    } else if (operation.equals(""/"")) {
                              stack.push(fir / sce);
                    }

          }
    // Build a Binary and Return the Root
    public Node Infix2BT(String infix){
        //new two stacks one for values , the other is for operators
    Stack<Node> operator = new Stack() ;
    int count = 1 ;
    String[] op = infix.split("""") ;
    int stringlen = op . length - 1 ;
    Node[] oldy = new Node[stringlen] ;
    while(count != infix.length()+1 ){
        switch (op[count]){
            case ""("" :                
                break;
            case ""+"" :  
                oldy[count] = new Node (null,null,null);
                operator.push(oldy[count]);
                oldy[count].setValue(""+"");
                treesize++ ;
                break ;
            case ""-"" :
                oldy[count] = new Node (null,null,null);
                operator.push(oldy[count]);
                oldy[count].setValue(""-"") ;
                treesize++ ;
               break;
            case ""*"" :
                oldy[count] = new Node (null,null,null);
                operator.push(oldy[count]);
                oldy[count].setValue(""*"");
                treesize++ ;
                break ;
            case ""/"" :
                oldy[count] = new Node (null,null,null);
                operator.push(oldy[count]);
                oldy[count].setValue(""/"");
                treesize++ ;
                break;
            case "")"" :
                Node a = operator.pop() ;
                Node b = operator.pop() ;
                Node c = operator.pop() ;
                if(operator.isEmpty()){
                root = new Node(null,null,null);
                root.setLeft(c);
                root.setRight(a);
                root.setValue(b.getValue());
                operator.push(root);

                }
                else{
                b.setLeft(c) ;
                b.setRight(a) ;                
                operator.push(b) ;

                }
                break;
                
            default :
                oldy[count] = new Node (null,null,null);
                operator.push(oldy[count]) ;
                oldy[count].setValue(op[count]);
                treesize++ ;

                break ;
                       
        }
        count++ ;
    }   
        return root;
    }
    
          private void prefix(ArrayList list, Node node) {
                    list.add(node);
                    if (node.getLeft() != null) {
                              prefix(list, node.getLeft());
                    }
                    if (node.getRight() != null) {
                              prefix(list, node.getRight());
                    }
          }


private void postfix(ArrayList list , Node node){
    
    if(node.getLeft() != null){
        postfix(list , node.getLeft()) ;
    }
    if(node.getRight()!=null){
        postfix(list , node.getRight()) ;
        }
    list.add(node) ;
    }

    public Node[] PrintPrefix(){
        
        if(root == null)
            throw new java.lang.NullPointerException();
               
        ArrayList<Node> array = new ArrayList<Node>() ;
        prefix(array,root) ;
        Node[] prefix = new Node[array.size()] ;
        
        for (int count = 0; count < array.size(); count++) {
           prefix[count] = array.get(count);
//           System.out.print(prefix[count].getValue());
           }
        
        return prefix;
        
    }
  
    public Node[] PrintPostfix(){
        
        if(root == null)
            throw new java.lang.NullPointerException() ;
        
        ArrayList<Node> array = new ArrayList<Node>() ;
        postfix(array,root) ;
        Node[] postfix = new Node[array.size()] ;
        
        for(int j = 0 ; j < array.size() ; j ++){
            postfix[j] = array.get(j) ;
//            System.out.print(postfix[j].getValue());
            
        }        
        return postfix;
    }

//    public double Evaluation(){
//        
//        if(root == null)
//            throw new java.lang.NullPointerException();
//        
//        double answer = 0 ;
//        
//        ArrayList<Node> cal = new ArrayList<Node>() ;
//        postfix(cal,root) ;
//        Stack<Double> eva = new Stack<Double>() ;
//        for(int k = 0 ; k < cal.size() ; k++){
//            String a = cal.get(k).getValue() ;
//            Double number1 ;
//            Double number2 ;
//            if(isOperator(a) != 5)
//                number1 = eva.pop() ;
//                number2 = eva.pop() ;
//                if(isOperator(a) == 1)
//                     answer = number1 + number2 ;
//                if(isOperator)
//                
//        }
//        
//        
//        
//        
//        
//        return answer;
//    }
    
          public double Evaluation() {
                    if (this.root == null) {
                              throw new java.lang.NullPointerException();
                    } else {
                              ArrayList<Node> array = new ArrayList<Node>();
                              postfix(array, this.root);
                              Stack<Double> storge = new Stack<Double>();
                              for (int count = 0; count < array.size(); count++) {
                                        if (isOperator(array.get(count).getValue())) {

                                                  Calculate(storge, array.get(count).getValue());
                                        } else {
                                                  storge.push(Double.parseDouble(array.get(count).getValue()));
                                        }
                              }

                              return storge.pop();
                    }
          }

}

