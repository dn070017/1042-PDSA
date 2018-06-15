
import java.io.BufferedReader;
import java.io.FileReader;
/**
.
 */
public class Percolation {

    public int dimension;
    public Node[][] nodes;
    public Node topnode;
    public Node buttonnode;

    public static void main(String[] args) throws Exception {
        try(BufferedReader br = new BufferedReader(new FileReader(args[0]))){
        //try(BufferedReader br = new BufferedReader(new FileReader(""test.txt""))){
            int dimension=Integer.parseInt(br.readLine());
            Percolation percolation=new Percolation(dimension);


            String line;
            boolean connect=false;
            while ((line=br.readLine())!=null) {
                String[] data = line.split("","");
                int x=Integer.parseInt(data[0]);
                int y=Integer.parseInt(data[1]);
                x=x-1;y=y-1;
                percolation.open_point(x,y);
                if(percolation.isConnect()) {
                    connect=true;
                    System.out.println(data[0] + "","" + data[1]);
                    break;
                }

                //System.out.println(data[0]+"",""+data[1]);
            }
            if(!connect)
                System.out.println(-1);

          /*  for(int i=0; i<dimension;i++){
                for(int j=0;j<dimension;j++){
                    if(percolation.nodes[i][j]!=null)
                    percolation.nodes[i][j].showNodeinfo();
                }
            }*/


/*            if(percolation.isConnect())
                System.out.println(""Connected"");
            else
                System.out.println(""Disconnected"");*/

            br.close();
        }
    }


    public Percolation(int dimension){
        this.dimension=dimension;
        this.nodes=new Node[dimension][dimension];
        topnode=new Node(0,0,true,1);
        buttonnode=new Node(dimension+1,dimension+1,true,1);
    }
    public void Union(Node A,Node B){
        if(A.root()!=B.root()) {
            if (B.root().numberOfnode > A.root().numberOfnode) {
                B.root().numberOfnode += A.root().numberOfnode;
                A.root().parent = B.root();
            } else {
                A.root().numberOfnode += B.root().numberOfnode;
                B.root().parent = A.root();
            }
        }
    }

    public void open_point(int x,int y){


        //System.out.println(String.valueOf(x)+"",""+String.valueOf(y));
        nodes[x][y]=new Node(x+1,y+1,true,1);


        if(!(y>=dimension-1)){
            if(nodes[x][y+1]!=null){
                this.Union(nodes[x][y],nodes[x][y+1]);
            }
        }
        else{

            this.Union(nodes[x][y],topnode);

        }
        if(!(y<=0)) {
            if (nodes[x][y - 1]!=null) {
                this.Union(nodes[x][y], nodes[x][y - 1]);
            }
        }
        else{
            this.Union(nodes[x][y],buttonnode);
        }
        if(!(x<=0)) {
            if (nodes[x - 1][y]!=null) {
                this.Union(nodes[x][y], nodes[x-1][y]);
            }
        }
        if(!(x>=dimension-1)) {
            if (nodes[x + 1][y]!=null) {
                this.Union(nodes[x][y], nodes[x+1][y]);
            }
        }

    }
    public boolean isConnect(){
/*
        for(int i=0;i<dimension;i++){
            if(nodes[i][0]!=null){
                Union(topnode,nodes[i][0]);

            }
        }
        for(int i=0;i<dimension;i++){
            if(nodes[i][dimension-1]!=null){
                Union(buttonnode,nodes[i][dimension-1]);

            }
        }*/

        if(topnode.root()==buttonnode.root())
            return true;

        else
            return false;
    }


    public static class Node {
        public int x,y;
        public Node parent;
        public boolean open;
        public int numberOfnode;


        public Node(int x,int y,boolean open,int numberOfnode){
            this.x=x;
            this.y=y;
            this.parent=this;
            this.open=open;
            this.numberOfnode=numberOfnode;
        }
        public Node root(){
            if(this.parent==this){
                //System.out.println(""my root is""+Integer.toString(this));
                return this;
            }
            else{
                return this.parent.root();
            }
        }
        public void showNodeinfo(){
            System.out.println(""pos:""+Integer.toString(x)+"",""+Integer.toString(y)+""  ""+String.valueOf(open)+""  ""+
                    ""par:""+Integer.toString(parent.x)+"",""+Integer.toString(parent.y)+""  ""+""root:""+Integer.toString(this.root().parent.x)+
                    "",""+Integer.toString(this.root().parent.y)+""  ""+""numberOfnode:""+Integer.toString(this.root().numberOfnode));
        }

    }


}

