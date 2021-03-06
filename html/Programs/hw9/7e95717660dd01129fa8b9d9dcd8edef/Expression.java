public class Expression{
public Node root;
private static class Stack
{
	Node[] stack;
	public int index;
	Stack(int max)
	{	index = -1; 
		stack = new Node[max];
	}
	public void push(Node a)	{	stack[++index] = a;	}
	public Node pop(){	return stack[index--];	}
	public Node now(){	return stack[index];	}        
}      

    public Expression(){}
    public int size=0;
    public Node Infix2BT(String infix){
        size=0;
        String[] da = infix.split("""");
        String[] data = new  String[1000];
        int d = 0;
        String tempstring="""";
        for(int i=0;i<da.length;i++){
            if (da[i].equals(""+"") || da[i].equals(""-"") || da[i].equals(""*"") || da[i].equals(""/"") || da[i].equals(""("") || da[i].equals("")"")) {
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
            if (data[dd].equals(""+"") || data[dd].equals(""-"") || data[dd].equals(""*"") || data[dd].equals(""/"")) {
                size++;
                Node temp = new Node(null,null,data[dd]);
                s.push(temp); 
            } else if (data[dd].equals(""("")) {
            } else if (data[dd].equals("")"")) {
                    Node b1 = s.pop();
                    Node b2 = s.pop();
                    Node b3 = s.pop();
                    b2.setLeft(b3);
                    b2.setRight(b1);
                    s.push(b2); 
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
    public Node[] Prefixpath(Node a){
        if(a==null){ throw new NullPointerException();}
        int x=0;
        Node[] prefix = new Node[size];
        Stack stemp = new Stack(size); 
        Node temproot=a;
        stemp.push(temproot);
        while(stemp.index!=-1){
            temproot=stemp.pop();
            prefix[x++]=temproot;
            if(temproot.getRight()!=null){  stemp.push(temproot.getRight()); }
            while(temproot.getLeft()!=null){
                temproot=temproot.getLeft();
                prefix[x++]=temproot;
                if(temproot.getRight()!=null){  stemp.push(temproot.getRight()); }                
            }
        }
        return prefix;        
    }
    
    public Node[] PrintPrefix(){
        Node[] prefix = Prefixpath(root);
        return prefix;
    }
  
    public Node[] PrintPostfix(){
        if(root==null){ throw new NullPointerException();}
        int stat=0;
        int x=-1;
        Node[] postfix1 = new Node[size];
        Stack stemp = new Stack(size);
        Stack stemp2 = new Stack(size);         
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
        return postfix1;
    }

    public double Evaluation(){
        Node[] prefix = Prefixpath(root);     
        Stack s = new Stack(prefix.length); 
        for(int i=prefix.length-1;i>-1;i--){
            if(prefix[i].getValue().equals(""+"") ||prefix[i].getValue().equals(""-"") ||prefix[i].getValue().equals(""*"") ||prefix[i].getValue().equals(""/"")){
                Double b2 = Double.parseDouble(s.pop().getValue());
                Double b1 = Double.parseDouble(s.pop().getValue());
                if(prefix[i].getValue().equals(""+"")){ Node temp = new Node(null,null,""""+(b2+b1)); s.push(temp);}
                if(prefix[i].getValue().equals(""-"")){ Node temp = new Node(null,null,""""+(b2-b1)); s.push(temp);}
                if(prefix[i].getValue().equals(""*"")){ Node temp = new Node(null,null,""""+(b2*b1)); s.push(temp);}
                if(prefix[i].getValue().equals(""/"")){ Node temp = new Node(null,null,""""+(b2/b1)); s.push(temp);}                  
            }
            else{ s.push(prefix[i]);}
        }        
        double answer = Double.parseDouble(s.pop().getValue());
        return answer;  
    }
     
}

