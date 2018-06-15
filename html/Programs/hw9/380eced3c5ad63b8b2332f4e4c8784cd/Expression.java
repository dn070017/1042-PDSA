import java.io.BufferedReader;
import java.io.FileReader;

/*
.
* To change this template file, choose Tools | Templates
.
*/

/**
*
* @author Dennis
*/
public class Expression{

private Node root;
private int N;
Queue<Node> findmidqueue =new Queue<Node>();
Queue<Node> findleftqueue =new Queue<Node>();
Stack<String> findans =new Stack<String>();
// DO NOT MODIFY THIS
public Expression(){}

// Build a Binary and Return the Root
public Node Infix2BT(String infix){
String[] Num = infix.split("""");
Queue<String> numberqueue =new Queue<String>();
int count =0;

for(int i=1;i<Num.length;i++){

if(Num[i].equals(""("")){
numberqueue.enqueue(Num[i]);
count =i;

}else if( Num[i].equals("")"") || Num[i].equals(""+"") || Num[i].equals(""-"") || Num[i].equals(""*"") || Num[i].equals(""/"") ){

String S1 =""""; 
for(int j=count+1;j<i;j++){
S1 = (S1+Num[j]).replace(""null"","""");
}
String S2 = S1.replace(""null"","""");

if( !( S2=="""" )){
// System.out.println(S1);
numberqueue.enqueue(S2);
}
numberqueue.enqueue(Num[i]);
count =i;

}else{

}
}
// System.out.println( numberqueue.size());
String[] Num2 =new String[numberqueue.size()];
for(int i=0;i<Num2.length;i++){
Num2[i]=numberqueue.dequeue();
}

Stack<Node> numsta =new Stack<Node> ();
Stack optsta =new Stack();
Stack<Node> nodesta =new Stack<Node>();
int N = Num2.length;
this.N=N;
Node[] Infnode =new Node[N/2+1];
int nodenum=0;

for(int i=0;i<N;i++){

// System.out.println( Num2[i]);
// System.out.println( Num2[i].equals(""*""));
if( Num2[i].equals(""+"") || Num2[i].equals(""-"") || Num2[i].equals(""*"") || Num2[i].equals(""/"")){
optsta.push(Num2[i]);

}else if( Num2[i].equals(""("")){

}else if( Num2[i].equals("")"")){
if(Num2[i-1].equals(""("") )
{
throw new NullPointerException(""NullPointerException"");
}
Infnode[0]=numsta.pop();
Infnode[1]=numsta.pop();
numsta.push(new Node( Infnode[1],Infnode[0],optsta.pop().toString()));
}else{

numsta.push(new Node(null,null,Num2[i]));
}
}

root=numsta.peek();
this.root=root;

// System.out.println(root.getValue());

return root;
}

public Node[] PrintPrefix(){
Node[] rootnode = new Node[1];
rootnode[0]=this.root;
findmid(rootnode[0]);
Node[] prefix = new Node[findmidqueue.size()];
int n=0;
while(!findmidqueue.isEmpty()){
prefix[n]=findmidqueue.dequeue();
n=n+1;
}
return prefix;
}

public Node findmid(Node x){
findmidqueue.enqueue(x);
// System.out.println(findmidqueue.peek().getValue()); 
if( !(x.getLeft()==null) ){
findmid(x.getLeft());
}
if( !(x.getRight()==null) ){
findmid(x.getRight());
}
return x;
}

public Node[] PrintPostfix(){
Node[] rootnode = new Node[1];
rootnode[0]=this.root;
findleft(rootnode[0]);
Node[] postfix = new Node[findleftqueue.size()+1];
int n=0;
while(!findleftqueue.isEmpty()){
postfix[n]=findleftqueue.dequeue();
n=n+1;
}
postfix[n]=rootnode[0];
return postfix;
}

public Node findleft(Node x){

if( !(x.getLeft()==null) ){
findleft(x.getLeft());
findleftqueue.enqueue(x.getLeft());
// System.out.println(x.getLeft().getValue()); 
}
if( !(x.getRight()==null) ){
findleft(x.getRight());
findleftqueue.enqueue(x.getRight());
// System.out.println(x.getRight().getValue()); 
}
return x;
}

public double Evaluation(){

Node[] rootnode = new Node[1];
rootnode[0]=this.root;
findleft(rootnode[0]);
Node[] postfix = new Node[findleftqueue.size()+1];
int n=0;
findleftqueue.enqueue( rootnode[0] );

// while(!findleftqueue.isEmpty()){
// postfix[n]=findleftqueue.dequeue();
// System.out.println(postfix[n].getValue());
// n=n+1;
// }

while( !findleftqueue.isEmpty() ){

findans.push(findleftqueue.dequeue().getValue());
if( findans.peek().equals(""+"") ){
findans.pop();
double a =Double.parseDouble(findans.pop());
double b =Double.parseDouble(findans.pop());
double c=a+b;
String stringc = Double.toString(c);
findans.push(stringc);

}else if( findans.peek().equals(""-"") ){
findans.pop();
double a =Double.parseDouble(findans.pop());
double b =Double.parseDouble(findans.pop());
double c=b-a;
String stringc = Double.toString(c);
findans.push(stringc);

}else if( findans.peek().equals(""*"") ){
findans.pop();
double a =Double.parseDouble(findans.pop());
double b =Double.parseDouble(findans.pop());
double c=b*a;
String stringc = Double.toString(c);
findans.push(stringc);

}else if( findans.peek().equals(""/"") ){
findans.pop();
double a =Double.parseDouble(findans.pop());
double b =Double.parseDouble(findans.pop());
double c=b/a;
String stringc = Double.toString(c);
findans.push(stringc);
}
}

double answer =Double.parseDouble( findans.peek());
return answer;

}

/**
* @param args the command line arguments
*/

public static void main(String[] args)throws Exception {
Node[] rootnode = new Node[1];
String problem =""(((0.235+0.88)*7.2)+(200+10))"";
Expression build = new Expression();
rootnode[0]=build.Infix2BT(problem);
System.out.println(build.Evaluation());
//
// 
for(int i=0;i<build.PrintPostfix().length;i++ ){
System.out.println(build.PrintPostfix()[i].getValue());
}

// System.out.println(rootnode[0].getRight().getValue());

}
}
