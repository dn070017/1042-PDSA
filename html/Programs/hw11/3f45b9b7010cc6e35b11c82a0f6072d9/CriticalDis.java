import java.io.BufferedReader;
import java.io.FileReader;

    public static void main(String[] args)throws Exception {
        try(BufferedReader br = new BufferedReader(new FileReader(args[0]))){
            int num=Integer.parseInt(br.readLine());
            Point2D[] data=new Point2D[num];
            for (int i=0 ; i<num ; i++){
                String[] input=br.readLine().split("" "");
                double x=Double.parseDouble(input[0]),y=Double.parseDouble(input[1]);
                data[i]=new Point2D(x,y);
            }
//-------------------------------------------------------------------------------------//
            for (int i=1 ; i<num-1 ; i++){
            double s=data[0].x()+data[0].y(),t=data[num-1].x()+data[num-1].y(), comparing=data[i].x()+data[i].y();
            if(comparing<s){
                Point2D temp=data[i];
                data[i]=data[0];
                data[0]=temp;
            }
            else if (comparing>t){
                Point2D temp=data[i];
                data[i]=data[num-1];
                data[num-1]=temp;
            }
            }
//--------------------------------------------------------------------------------------//
            MinPQ<pair> find=new MinPQ<pair>();
            for (int i=0 ; i<num ; i++)
                for (int j=0 ; j<num ; j++)
            if (data[i].x()<data[j].x() && data[i].y()<data[j].y() && i!=j){
                pair temp=new pair(i,j,data[i],data[j]);
                find.insert(temp);
                }
           Digraph findpath=new Digraph(num);
           DirectedDFS connection=new DirectedDFS(findpath,0);
           double d=0;
           while (connection.marked(num-1)!=true){
             pair temp=find.delMin();
             findpath.addEdge(temp.i(),temp.j());
             connection=new DirectedDFS(findpath,0);
             d=temp.getDistance();
             }
           System.out.printf(""%1.3f\n"", d);
        }
    }
}
