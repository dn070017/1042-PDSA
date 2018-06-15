        public Item next(){
            if (!hasNext()) throw new NoSuchElementException();
            Item item = current.item;
            current = current.next; 
            return item;
        }
    }
    
    
    public static void main(String[] args) throws Exception {
        try(BufferedReader br = new BufferedReader(new FileReader(args[0]))){
            String [] data = br.readLine().split("" "");
            Deque<String> s = new Deque<String>();
            s.addFirst(data[0]);
            s.addFirst(data[1]);
            s.addFirst(data[2]);
            s.addFirst(data[3]);
            s.addFirst(data[4]);
            s.addFirst(data[5]);
            s.addFirst(data[6]);
            s.addFirst(data[7]);
            s.addFirst(data[8]);

            int N = s.size();
            for(int i = 0; i < N; i++)
            System.out.println(s.removeFirst());
            
            
            System.out.print(s.isEmpty());
             System.out.println(s.removeLast());

        }
    }
    
}
