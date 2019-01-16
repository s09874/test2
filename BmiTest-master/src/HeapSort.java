

import java.util.PriorityQueue;

public class HeapSort {

    private PriorityQueue<WebTree> heap ;
	
	public HeapSort(){
		this.heap = new PriorityQueue<WebTree>(10, new ScoreComparator()) ;
	}
	
	public void add(WebTree k){
		heap.offer(k) ;
		System.out.println("Done") ;
	}
	
	public void peek(){
		if(heap.peek() == null){
			System.out.println("InvalidOperation") ;
			return ;
		}
		
		WebTree k = heap.peek() ;
		System.out.println(k.toString()) ;
	}
	
	public WebTree removeMin(){
		if(heap.peek() != null) {
//		{
//			System.out.println("InvalidOperation") ;
			
//		}
		WebTree k = heap.poll() ;
		return k;
		}else {
			throw new NullPointerException() ;
		}
	}
	
	public void output() {
		StringBuilder sBuilder = new StringBuilder() ;
		WebTree k;
		
		while((k = heap.poll()) != null){
			sBuilder.append(k.toString()) ;
			if (heap.peek() != null) sBuilder.append(" ") ;
		}
		
		System.out.println(sBuilder.toString());
	}
	
}
