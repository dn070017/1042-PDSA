import java.io.BufferedReader;
import java.io.FileReader;
/**
.
 */
public class LabelCC {
    public int dimension;
    public int target_x;
    public int target_y;
    public Node[][] nodes;
    public Node topnode;
    public Node buttonnode;
    public int label_count;


    public static void main(String[] args) throws Exception {
        try(BufferedReader br = new BufferedReader(new FileReader(args[0]))){
       //     try(BufferedReader br = new BufferedReader(new FileReader(""test.txt""))){
                String[] first_row=br.readLine().split("","");

                int dimension=Integer.parseInt(first_row[0]);
                int target_x=Integer.parseInt(first_row[1]);
                int target_y=Integer.parseInt(first_row[2]);

            LabelCC labelCC=new LabelCC(dimension,target_x,target_y);
            String line;

            while ((line=br.readLine())!=null) {
                String[] data = line.split("","");
                int x=Integer.parseInt(data[0]);
                int y=Integer.parseInt(data[1]);
                x=x-1;y=y-1;
                labelCC.close_point(x,y);
                //System.out.println(data[0]+"",""+data[1]);
            }
                for(int i=0;i<dimension;i++){
                    for(int j=0;j<dimension;j++){
                        if(labelCC.nodes[i][j]==null) {
                            labelCC.open_point(i, j);

                        }
                    }
                }
/*                for(int i=0;i<dimension;i++){
                    for(int j=0;j<dimension;j++){
                        if(labelCC.nodes[i][j].open) {

                            labelCC.nodes[i][j].showNodeinfo();
                        }
                    }
                }*/
            System.out.println(Integer.toString(labelCC.showLabel()));

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


    public LabelCC(int dimension,int target_x,int target_y){
        this.dimension=dimension;
        this.target_x=target_x;
        this.target_y=target_y;
        this.nodes=new Node[dimension][dimension];
        label_count=0;
    }
    public void Union(Node A,Node B){
        if(A.root()!=B.root()) {
            if(A.root().label<=B.root().label)
                B.root().label=A.root().label;
            else
                A.root().label=B.root().label;

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
        boolean add_label1,add_label2;

        nodes[x][y]=new Node(x+1,y+1,true,1,label_count);
        if(!(y<=0)) {
            if (nodes[x][y - 1].open) {
                this.Union(nodes[x][y-1], nodes[x][y]);
                add_label1=false;
            }
            else
                add_label1=true;
        }
        else
            add_label1=true;

        if(!(x<=0)) {
            if (nodes[x - 1][y].open) {
                this.Union(nodes[x-1][y], nodes[x][y]);
                add_label2=false;
            }
            else
                add_label2=true;
        }
        else
            add_label2=true;
        if(add_label1&add_label2) {
            label_count++;
            nodes[x][y].label=label_count;
        }
    }
    public void close_point(int x,int y){
        nodes[x][y]=new Node(x+1,y+1,false,0,0);
    }

    public int showLabel(){
        if(this.target_x>dimension||this.target_y>dimension)
            return 0;
        else
        return this.nodes[target_x-1][target_y-1].root().label;
    }


    public static class Node {
        public int x,y;
        public Node parent;
        public boolean open;
        public int numberOfnode;
        public int label;


        public Node(int x,int y,boolean open,int numberOfnode,int label){
            this.x=x;
            this.y=y;
            this.parent=this;
            this.open=open;
            this.numberOfnode=numberOfnode;
            this.label=label;
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
                    "",""+Integer.toString(this.root().parent.y)+""  ""+""numberOfnode:""+Integer.toString(this.root().numberOfnode)+""  ""+
                    ""label:""+Integer.toString(this.root().label));
        }

    }

}

