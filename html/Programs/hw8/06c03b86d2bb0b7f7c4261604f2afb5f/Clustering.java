import java.util.Arrays;
import java.util.Comparator;
import java.io.FileReader;
import java.io.BufferedReader;

public class Clustering{
	
	protected static Cluster[] clusterArray;
	protected static boolean[] Exist;
	protected static QuickUnionUF uf;
	

	public static void main(String[] args) throws Exception{
        try (BufferedReader br = new BufferedReader(new FileReader(args[0]))) {
			long start, end;
			int N      = Integer.parseInt(br.readLine());
			int now_N  = N;
			int id_max = N;
			int k      = 3;
			
			
			if(N > k){
				uf           = new QuickUnionUF(N + N - k);
				clusterArray = new Cluster[N + N - k];
				Exist        = new boolean[N + N - k];
			}else{
				uf           = new QuickUnionUF(N);
				clusterArray = new Cluster[N];
				Exist = new boolean[N];
				k = N;
			}     
			
			start = System.currentTimeMillis();
			
			/*StdDraw.setPenColor(StdDraw.BLUE);
			StdDraw.setCanvasSize(550, 550);
			StdDraw.setXscale(-0.1, 1.1);
			StdDraw.setYscale(-0.1, 1.1);
			StdDraw.setPenRadius(.01);*/
			
			
            for(int i = 0; i < N; i++) {
				String[] s = br.readLine().split("" "");
				double x = Double.parseDouble(s[0]);
				double y = Double.parseDouble(s[1]);
				Point2D p = new Point2D(x, y);
				
				//p.draw();
				
				clusterArray[i] = new Cluster(p, i);
				Exist[i] = true;
			}
			
			MinPQ<Cluster> pq     = new MinPQ();
			MinPQ<Cluster> pq_out = new MinPQ();
			for(int i = 0; i < N; i++){
				for(int j = i + 1; j < N; j++){
					//System.out.printf(""Insert %2d %2d\n"", i, j);
					pq.insert(new Cluster(clusterArray[i], clusterArray[j]));
					pq_out.insert(new Cluster(clusterArray[i], clusterArray[j]));
				}
			}
			
			int ini = 0;
			while(now_N > k){
				//System.out.println(pq.size());
				
				Cluster c = pq.delMin();
				
				if(Exist[c.p_idx] && Exist[c.q_idx]){
				//if(clusterArray[c.p_idx].exist && clusterArray[c.q_idx].exist){
					//System.out.println(ini);
					now_N--;
					Exist[c.p_idx] = false;
					Exist[c.q_idx] = false;
					c.merge(clusterArray[c.p_idx], clusterArray[c.q_idx],id_max);
					Exist[id_max] = true;
					clusterArray[id_max++] = c;
					
					//Print_CA(id_max);
					//System.out.println();
					//System.out.println(id_max + "" "" + now_N);
					
					for(int i = ini; i < id_max-1; i++){
						if(Exist[i]){
						//if(clusterArray[i].exist){
							//System.out.printf(""Insert %2d %2d\n"", i, id_max);
							pq.insert(new Cluster(c, clusterArray[i]));
						}
					}
					
					for(int i = ini; i < id_max-1; i++){
						if(!Exist[i]) ini++;
						else          break;
					}
				}
			}
			//Print_CA(id_max);
			Print_out(k);
			
			while(true){
				if(pq_out.size() == 0)break;
				Cluster c = pq_out.delMin();
				//System.out.printf(""%2d %2d %1.2f\n"",c.p_idx, c.q_idx, c.dist);
				if(!uf.connected(c.p_idx, c.q_idx)){
					System.out.printf(""%1.2f\n"", c.dist);
					
					//StdDraw.setPenColor(StdDraw.BLUE);
					//clusterArray[c.p_idx].cen.drawTo(clusterArray[c.q_idx].cen);
					
					break;
				}
			}
			
			end = System.currentTimeMillis();
			System.out.println(""Time cost:"" + (end-start)/1000. + ""sec"");
		}
    }
	
	public static void Print_CA(int m){
		for(int i=0; i<m; i++){
			//if(i%5 == 0 && i > 0)System.out.println();
			//if(clusterArray[i].exist){
			System.out.printf(""%6b %2d %2d %2d %2d %f\n"", 
							    clusterArray[i].exist,
								clusterArray[i].idx,
								clusterArray[i].p_idx, 
								clusterArray[i].q_idx,
								clusterArray[i].size,
								clusterArray[i].dist);
			//}
		}
	}
	
	public static void Print_out(int k){
		Cluster[] c_out = new Cluster[k];
		int i = 0;
		int j = 0;
		while(i < k){
			if(clusterArray[clusterArray.length - 1 - j].exist){
				c_out[i] = clusterArray[clusterArray.length - 1 - j];
				i++;
			}
			j++;
		}

		Arrays.sort(c_out, c_out[0].OUT_PUT_ORDER);
		for(i = 0; i < k; i++){
			System.out.printf(""%d %1.2f %1.2f\n"", c_out[i].size, c_out[i].cen.x(), c_out[i].cen.y());
		}
	}
	
	private static class Cluster implements Comparable<Cluster> {
		protected int     p_idx;
		protected int     q_idx;
		protected int     size;
		protected int     idx;
		protected Point2D cen;
		protected double  dist;
		protected boolean exist;
		protected int[]   cont;
		
		public final Comparator<Cluster> OUT_PUT_ORDER = new OutPutOrder();
		
		Cluster(Point2D p, int i){
			size    = 1;
			idx     = i;
			cen     = p;
			exist   = true;
			cont    = new int[1];
			cont[0] = i;
		}
		
		Cluster(Cluster p, Cluster q){
			dist  = p.cen.distanceTo(q.cen);
			p_idx = p.idx;
			q_idx = q.idx;
		}

		public void merge(Cluster p, Cluster q, int idx){
			clusterArray[p_idx].exist = false;
			clusterArray[q_idx].exist = false;
			//System.out.printf(""merge : %2d %2s %2.4f\n"", p_idx, q_idx, dist);
			size     = p.size + q.size;
			double x = (p.cen.x() * p.size + q.cen.x() * q.size) / size;
			double y = (p.cen.y() * p.size + q.cen.y() * q.size) / size;
			cen      = new Point2D(x, y);
			
			
			/*StdDraw.setPenColor(StdDraw.GREEN);
			StdDraw.setPenRadius(.0001 * size);
			p.cen.drawTo(q.cen);
			StdDraw.setPenColor(StdDraw.RED);
			StdDraw.setPenRadius(.001 * size);
			cen.draw();
			//StdDraw.show(50);*/
			
			this.idx      = idx;
			
			//cont     = new int[size];
			//int i = 0;int j = 0;int k = 0;
			
			/*while(i < size) {
				if(i < p.size){
					cont[i++] = p.cont[j++];
				}else{
					cont[i++] = q.cont[k++];
				}
			}*/
			
			uf.union(p.idx, q.idx);			
			uf.union(p.idx, this.idx);
			
			exist    = true;
		} 
		
		public int compareTo(Cluster that) {
			if (this.dist < that.dist)      return -1;
			else if (this.dist > that.dist) return +1;
			else                            return  0;
		}
		
		private class OutPutOrder implements Comparator<Cluster> {
			public int compare(Cluster p, Cluster q) {
				if      (p.size < q.size)       return +1;
				else if (p.size > q.size)       return -1;
				else if (p.cen.x() < q.cen.x()) return -1;
				else if (p.cen.x() > q.cen.x()) return +1;
				else if (p.cen.y() < q.cen.y()) return -1;
				else if (p.cen.y() > q.cen.y()) return +1;
				else                            return  0;
			}
		}
	}
	
}


