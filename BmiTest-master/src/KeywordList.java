

import java.util.ArrayList;

public class KeywordList {

	public ArrayList<Keyword> keywords = new ArrayList<Keyword>();
//	private ArrayList<String> keywordName = new ArrayList<String>();
	
	KeywordList(){
		keywords.add(new Keyword("review",1));
		keywords.add(new Keyword("critic review",1));
		keywords.add(new Keyword("feedback",1));
		keywords.add(new Keyword("good",1));
		keywords.add(new Keyword("bad",1));		
		keywords.add(new Keyword("trailers",0.5));
		keywords.add(new Keyword("similar",0.5));
		keywords.add(new Keyword("imdb",0.5));
		keywords.add(new Keyword("rotten tomato",0.5));
		keywords.add(new Keyword("2018",0.5));
		keywords.add(new Keyword("2019",1));
		keywords.add(new Keyword("ptt",0.5));
//		keywords.add(new Keyword("relieves",0.5));
		keywords.add(new Keyword("relieving",0.5));
		keywords.add(new Keyword("Dr",2));
		keywords.add(new Keyword("doctor",2));
		keywords.add(new Keyword("health",2));
		keywords.add(new Keyword("report",1.5));
		keywords.add(new Keyword("study",1.5));
		keywords.add(new Keyword("research",1.5));
		keywords.add(new Keyword("medicine",1.5));
		keywords.add(new Keyword("treatment",1.5));
		keywords.add(new Keyword("medical",1.5));
		
		
	}

	
}
