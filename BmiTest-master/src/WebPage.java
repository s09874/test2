

import java.io.IOException;
import java.util.ArrayList;

public class WebPage {

	public String URL;
	public String name;
	public WordCounter counter;
	public double score;
	
	public WebPage(String name, String URL) {
		this.URL = URL;
		this.name = name;
		this.counter = new WordCounter(URL);
		
	}
	
	public void setScore(ArrayList<Keyword> keywords) throws IOException {
		score = 0;
		for(Keyword k : keywords) {
			score += counter.countKeyword(k.name) * k.weight;
			
		}
		
	}
	
	@Override
	public String toString() {
		return "["+ name + "," + URL +"]";
	}

	
}
