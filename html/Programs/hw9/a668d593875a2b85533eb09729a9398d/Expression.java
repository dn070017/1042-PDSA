
public class Expression{
    
    public String a1=""+"";
    public String a2=""-"";
    public String a3=""*"";
    public String a4=""/"";
    public String a5=""("";
    public String a6="")"";  
    public Node root;
    public Node[] postfix;
    public Node[] prefix;

private static class Stack
{
	Node[] stack;
	public int index;
	Stack(int max)
	{
		index = -1; 
		stack = new Node[max];
	}
	public void push(Node a)
	{
		stack[++index] = a;
	}
	public Node pop()
	{
		return stack[index--];
	}
	public Node now()
	{
		return stack[index];
	}        
}      

    // DO NOT MODIFY THIS
    public Expression(){}
    public int size=0;
    // Build a Binary and Return the Root
    public Node Infix2BT(String infix){
        String[] da = infix.split("""");
        String[] data = new  String[da.length];
        int d = 0;
        String tempstring="""";
        for(int i=0;i<da.length;i++){
            if (da[i].equals(a1) || da[i].equals(a2) || da[i].equals(a3) || da[i].equals(a4) || da[i].equals(a5) || da[i].equals(a6)) {
                if(!tempstring.equals("""")){data[d++]=tempstring;  tempstring="""";}
                data[d++]=da[i];
            }
            else if(!da[i].equals("""")){
                    tempstring=tempstring+da[i];
            }
        }
   	Stack s = new Stack(data.length);
        int dd = 0;
        while(data[dd]!=null){
            if (data[dd].equals(a1) || data[dd].equals(a2) || data[dd].equals(a3) || data[dd].equals(a4)) {
                size++;
                Node temp = new Node(null,null,data[dd]);
                s.push(temp); 
            } else if (data[dd].equals(a5)) {
            } else if (data[dd].equals(a6)) {
                if(s.index>1){
                    Node b1 = s.pop();
                    Node b2 = s.pop();
                    Node b3 = s.pop();
                    b2.setLeft(b3);
                    b2.setRight(b1);
                    s.push(b2); 
                }
            } else {
                size++;
                Node temp = new Node(null,null,data[dd]);
                s.push(temp);               
            }
        dd++;    
        }
        root=s.pop();
        return root;
    }

    public Node[] PrintPrefix(){
        if(root==null){ throw new NullPointerException();}
        int x=0;
        Node[] prefix1 = new Node[size];
        Stack stemp = new Stack(size); 
        Node temproot=root;
        stemp.push(temproot);
        while(stemp.index!=-1){
            temproot=stemp.pop();
            prefix1[x++]=temproot;
            if(temproot.getRight()!=null){  stemp.push(temproot.getRight()); }
            while(temproot.getLeft()!=null){
                temproot=temproot.getLeft();
                prefix1[x++]=temproot;
                if(temproot.getRight()!=null){  stemp.push(temproot.getRight()); }                
            }
        }
        prefix=prefix1;
        return prefix;
    }
  
    public Node[] PrintPostfix(){
        if(root==null){ throw new NullPointerException();}
        int stat=0;
        int x=-1;
        Node[] postfix1 = new Node[size];
        Stack stemp = new Stack(size/2);
        Stack stemp2 = new Stack(size/2);         
        Node temproot=root;  
        postfix1[size-1]=root;
        for(int i=0;i<size-1;i++){
            if(stat==0){
            while(temproot.getLeft()!=null){
                stemp.push(temproot);
                stemp2.push(temproot.getRight());          
                temproot=temproot.getLeft();
            }}       
            postfix1[++x]=temproot;
            stat=0;
            if(stemp2.index==-1){temproot=stemp.pop(); stat=1;}
            else if(stemp.now().getRight().equals(stemp2.now())){    temproot=stemp2.pop();  }
            else{ temproot=stemp.pop(); stat=1;}
            }   
        postfix=postfix1;
        return postfix;
    }

    public double Evaluation(){
                if(root==null){ throw new NullPointerException();}
        if(postfix==null){
        Stack s = new Stack(prefix.length); 
        for(int i=prefix.length-1;i>-1;i--){
            if(prefix[i].getValue().equals(a1) ||prefix[i].getValue().equals(a2) ||prefix[i].getValue().equals(a3) ||prefix[i].getValue().equals(a4)){
                Double b2 = Double.parseDouble(s.pop().getValue());
                Double b1 = Double.parseDouble(s.pop().getValue());
                if(prefix[i].getValue().equals(a1)){ Node temp = new Node(null,null,""""+(b2+b1)); s.push(temp);}
                if(prefix[i].getValue().equals(a2)){ Node temp = new Node(null,null,""""+(b2-b1)); s.push(temp);}
                if(prefix[i].getValue().equals(a3)){ Node temp = new Node(null,null,""""+(b2*b1)); s.push(temp);}
                if(prefix[i].getValue().equals(a4)){ Node temp = new Node(null,null,""""+(b2/b1)); s.push(temp);}                  
            }
            else{ s.push(prefix[i]);}
        }        
        double answer = Double.parseDouble(s.pop().getValue());
        return answer;  }
        else{
        Stack s = new Stack(postfix.length); 
        for (Node i : postfix) {
            if(i.getValue().equals(a1) ||i.getValue().equals(a2) ||i.getValue().equals(a3) ||i.getValue().equals(a4)){
                Double b1 = Double.parseDouble(s.pop().getValue());
                Double b2 = Double.parseDouble(s.pop().getValue());
                if(i.getValue().equals(a1)){ Node temp = new Node(null,null,""""+(b2+b1)); s.push(temp);}
                if(i.getValue().equals(a2)){ Node temp = new Node(null,null,""""+(b2-b1)); s.push(temp);}
                if(i.getValue().equals(a3)){ Node temp = new Node(null,null,""""+(b2*b1)); s.push(temp);}
                if(i.getValue().equals(a4)){ Node temp = new Node(null,null,""""+(b2/b1)); s.push(temp);}                  
            }
            else{ s.push(i);}
        } 
        double answer = Double.parseDouble(s.pop().getValue());
        return answer;  }

    }
   
}


