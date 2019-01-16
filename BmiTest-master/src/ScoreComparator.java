

import java.util.Comparator;


public class ScoreComparator implements Comparator<WebTree>{

    @Override
	public int compare(WebTree wt1, WebTree wt2){
//		double wn1 = wt1.root.nodeScore;
//		double wn2 = wt2.root.nodeScore;
		
		if(wt1 == null || wt2 == null) throw new NullPointerException() ;
		
		if(wt1.root.nodeScore > wt2.root.nodeScore){
			return -1 ;
		}else if(wt1.root.nodeScore < wt2.root.nodeScore){
			return 1 ;
		}
		return 0 ;
	}
	
}
