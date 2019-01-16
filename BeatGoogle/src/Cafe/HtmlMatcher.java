package Cafe;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class HtmlMatcher {
	private String url;
	private String content;
	private String searchKeyword; // 使用者輸入的關鍵字 在construct的時候加上NBA/...和news 是Google搜尋網頁時用的字串
	private String searchKeyword2; // 使用者輸入的關鍵字 是查Google相關關鍵字用
	public static ArrayList<WebPage> web; // Google跟內建網站裡搜尋找到的各個網頁 還未排序算分
	public static String[] relatedKeyword;

	public HtmlMatcher(String kind, String searchKeyword) throws IOException {
		this.searchKeyword2 = searchKeyword;
		web = new ArrayList<>();

		switch (kind) {
		case "XXX":
			if (searchKeyword != "XXXXX") {
				this.searchKeyword = searchKeyword + "+XXX";
			} else {
				this.searchKeyword = searchKeyword + "+XXX";
			}
			nbaMatch(); // 在內建網站的搜尋
			break;
		case "YYY":
			if (searchKeyword != "YYY") {
				this.searchKeyword = searchKeyword + "+YYY+zzz";
			} else {
				this.searchKeyword = searchKeyword + "+zzz";
			}
			mlbMatch();
			break;
		case "ZZZ":
			if (searchKeyword != "ZZZ") {
				this.searchKeyword = searchKeyword + "+ZZZ+zzz";
			} else {
				this.searchKeyword = searchKeyword + "+zzz";
			}
			nflMatch();
			break;
		case "AAA":
			if (searchKeyword != "AAA") {
				this.searchKeyword = searchKeyword + "+AAA+zzz";
			} else {
				this.searchKeyword = searchKeyword + "+zzz";
			}
			nhlMatch();
			break;
		default:
			if (searchKeyword != "BBB") {
				this.searchKeyword = searchKeyword + "+bbb+zzz";
			} else {
				this.searchKeyword = searchKeyword + "+bbb";
			}
			break;
		}
		query(); // 在Google的搜尋
		fetchRelatedKeyword(); // 查Google推薦相關關鍵字
	}

	private String fetchContent() throws IOException {
		URL url = new URL(this.url);
		URLConnection conn = url.openConnection();
		conn.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows; U; Windows NT 6.0; zh-TW; rv:1.9.1.2) "
				+ "Gecko/20090729 Firefox/3.5.2 GTB5 (.NET CLR 3.5.30729)");
		conn.connect();
		InputStream in = conn.getInputStream();
		InputStreamReader ir = new InputStreamReader(in, "UTF8");
		BufferedReader br = new BufferedReader(ir);

		String retVal = "";
		String line = null;

		while ((line = br.readLine()) != null) {
			retVal += line;
		}

		return retVal;
	}

	public void query() throws IOException {
		this.url = "https://www.google.com.tw/search?q=" + searchKeyword + "&oe=utf8num=15";
		this.content = fetchContent();

		Document document = Jsoup.parse(this.content);
		Elements lis = document.select("div.g");
		for (Element li : lis) {
			try {
				Element h3 = li.select("h3.r").get(0);
				String title = h3.text();

				Element cite = li.getElementsByTag("a").first();
				String citeUrl = cite.attr("href");
				citeUrl = citeUrl.substring(7, citeUrl.indexOf("&sa=U&ved"));

				web.add(new WebPage(citeUrl, title));

			} catch (IndexOutOfBoundsException e) {

			}
		}
	}

	public void nbaMatch() throws IOException {
		this.url = "///網址////";
		content = fetchContent();

		int indexOfOpen = content.indexOf("collection collection-article-list has-load-more");
		indexOfOpen = content.indexOf("collection collection-article-list has-load-more", indexOfOpen + 1);
		indexOfOpen = content.indexOf("collection collection-article-list has-load-more", indexOfOpen + 1);
		int indexOfHtmlClose = content.indexOf(">", indexOfOpen);
		int indexOfHtml = -1;
		int indexOfTitleClose = -1;
		String html = null;
		String title = null;
		for (int i = 0; i < 10; i++) {
			indexOfOpen = content.indexOf("article class", indexOfHtmlClose);
			indexOfOpen = content.indexOf("info-header", indexOfHtmlClose);
			indexOfHtml = content.indexOf("a href=", indexOfOpen);
			indexOfHtmlClose = content.indexOf(">", indexOfHtml);
			indexOfTitleClose = content.indexOf("<", indexOfHtmlClose);
			html = content.substring(indexOfHtml + 8, indexOfHtmlClose - 1);
			title = content.substring(indexOfHtmlClose + 1, indexOfTitleClose);
			if (!content.substring(indexOfHtml + 8, indexOfHtmlClose - 1).contains("video")) {
				html = "///網址////" + html;
				web.add(new WebPage(html, title));
			}
		}

		this.url = "///網址////";
		content = fetchContent();

		indexOfOpen = content.indexOf("latest-news-module module infinite");
		indexOfHtmlClose = content.indexOf(">", indexOfOpen);
		for (int i = 0; i < 10; i++) {
			indexOfOpen = content.indexOf("media-heading", indexOfHtmlClose);
			indexOfHtml = content.indexOf("a href=", indexOfOpen);
			indexOfHtmlClose = content.indexOf(">", indexOfHtml);
			indexOfTitleClose = content.indexOf("<", indexOfHtmlClose);
			html = "///網址////" + content.substring(indexOfHtml + 8, indexOfHtmlClose - 1);
			title = content.substring(indexOfHtmlClose + 1, indexOfTitleClose);
			web.add(new WebPage(html, title));
		}
	}

	public void mlbMatch() throws IOException {
		this.url = "///網址////";
		content = fetchContent();

		int indexOfOpen = content.indexOf("collection collection-article-list has-load-more");
		indexOfOpen = content.indexOf("collection collection-article-list has-load-more", indexOfOpen + 1);
		indexOfOpen = content.indexOf("collection collection-article-list has-load-more", indexOfOpen + 1);
		int indexOfHtmlClose = content.indexOf(">", indexOfOpen);
		int indexOfHtml = -1;
		int indexOfTitleClose = -1;
		String html = null;
		String title = null;
		for (int i = 0; i < 10; i++) {
			indexOfOpen = content.indexOf("article class", indexOfHtmlClose);
			indexOfOpen = content.indexOf("info-header", indexOfHtmlClose);
			indexOfHtml = content.indexOf("a href=", indexOfOpen);
			indexOfHtmlClose = content.indexOf(">", indexOfHtml);
			indexOfTitleClose = content.indexOf("<", indexOfHtmlClose);
			html = content.substring(indexOfHtml + 8, indexOfHtmlClose - 1);
			title = content.substring(indexOfHtmlClose + 1, indexOfTitleClose);
			if (!content.substring(indexOfHtml + 8, indexOfHtmlClose - 1).contains("video")) {
				html = "///網址////" + html;
				web.add(new WebPage(html, title));
			}
		}

		this.url = "///網址////";
		content = fetchContent();

		indexOfOpen = content.indexOf("latest-news-module module infinite");
		indexOfHtmlClose = content.indexOf(">", indexOfOpen);
		for (int i = 0; i < 10; i++) {
			indexOfOpen = content.indexOf("media-heading", indexOfHtmlClose);
			indexOfHtml = content.indexOf("a href=", indexOfOpen);
			indexOfHtmlClose = content.indexOf(">", indexOfHtml);
			indexOfTitleClose = content.indexOf("<", indexOfHtmlClose);
			html = "///網址////" + content.substring(indexOfHtml + 8, indexOfHtmlClose - 1);
			title = content.substring(indexOfHtmlClose + 1, indexOfTitleClose);
			web.add(new WebPage(html, title));
		}
	}

	public void nflMatch() throws IOException {
		this.url = "///網址////";
		content = fetchContent();

		int indexOfOpen = content.indexOf("collection collection-article-list has-load-more");
		indexOfOpen = content.indexOf("collection collection-article-list has-load-more", indexOfOpen + 1);
		indexOfOpen = content.indexOf("collection collection-article-list has-load-more", indexOfOpen + 1);
		int indexOfHtmlClose = content.indexOf(">", indexOfOpen);
		int indexOfHtml = -1;
		int indexOfTitleClose = -1;
		String html = null;
		String title = null;
		for (int i = 0; i < 10; i++) {
			indexOfOpen = content.indexOf("article class", indexOfHtmlClose);
			indexOfOpen = content.indexOf("info-header", indexOfHtmlClose);
			indexOfHtml = content.indexOf("a href=", indexOfOpen);
			indexOfHtmlClose = content.indexOf(">", indexOfHtml);
			indexOfTitleClose = content.indexOf("<", indexOfHtmlClose);
			html = content.substring(indexOfHtml + 8, indexOfHtmlClose - 1);
			title = content.substring(indexOfHtmlClose + 1, indexOfTitleClose);
			if (!content.substring(indexOfHtml + 8, indexOfHtmlClose - 1).contains("video")) {
				html = "///網址////" + html;
				web.add(new WebPage(html, title));
			}
		}

		this.url = "///網址////";
		content = fetchContent();

		indexOfOpen = content.indexOf("latest-news-module module infinite");
		indexOfHtmlClose = content.indexOf(">", indexOfOpen);
		for (int i = 0; i < 10; i++) {
			indexOfOpen = content.indexOf("media-heading", indexOfHtmlClose);
			indexOfHtml = content.indexOf("a href=", indexOfOpen);
			indexOfHtmlClose = content.indexOf(">", indexOfHtml);
			indexOfTitleClose = content.indexOf("<", indexOfHtmlClose);
			html = "///網址////" + content.substring(indexOfHtml + 8, indexOfHtmlClose - 1);
			title = content.substring(indexOfHtmlClose + 1, indexOfTitleClose);
			web.add(new WebPage(html, title));
		}
	}

	public void nhlMatch() throws IOException {
		this.url = "///網址////";
		content = fetchContent();

		int indexOfOpen = content.indexOf("collection collection-article-list has-load-more");
		indexOfOpen = content.indexOf("collection collection-article-list has-load-more", indexOfOpen + 1);
		indexOfOpen = content.indexOf("collection collection-article-list has-load-more", indexOfOpen + 1);
		int indexOfHtmlClose = content.indexOf(">", indexOfOpen);
		int indexOfHtml = -1;
		int indexOfTitleClose = -1;
		String html = null;
		String title = null;
		for (int i = 0; i < 10; i++) {
			indexOfOpen = content.indexOf("article class", indexOfHtmlClose);
			indexOfOpen = content.indexOf("info-header", indexOfHtmlClose);
			indexOfHtml = content.indexOf("a href=", indexOfOpen);
			indexOfHtmlClose = content.indexOf(">", indexOfHtml);
			indexOfTitleClose = content.indexOf("<", indexOfHtmlClose);
			html = content.substring(indexOfHtml + 8, indexOfHtmlClose - 1);
			title = content.substring(indexOfHtmlClose + 1, indexOfTitleClose);
			if (!content.substring(indexOfHtml + 8, indexOfHtmlClose - 1).contains("video")) {
				html = "///網址////" + html;
				web.add(new WebPage(html, title));
			}
		}

		this.url = "///網址////";
		content = fetchContent();

		indexOfOpen = content.indexOf("latest-news-module module infinite");
		indexOfHtmlClose = content.indexOf(">", indexOfOpen);
		for (int i = 0; i < 10; i++) {
			indexOfOpen = content.indexOf("media-heading", indexOfHtmlClose);
			indexOfHtml = content.indexOf("a href=", indexOfOpen);
			indexOfHtmlClose = content.indexOf(">", indexOfHtml);
			indexOfTitleClose = content.indexOf("<", indexOfHtmlClose);
			html = "///網址////" + content.substring(indexOfHtml + 8, indexOfHtmlClose - 1);
			title = content.substring(indexOfHtmlClose + 1, indexOfTitleClose);
			web.add(new WebPage(html, title));
		}
	}

	public void fetchRelatedKeyword() throws IOException {
		this.url = "https://www.google.com.tw/search?q=" + searchKeyword2 + "&oe=utf8num=10";
		content = fetchContent();
		int indexOfOpen = content.indexOf("clear:");

		if (indexOfOpen == -1) {
			String[] s = new String[1];
			s[0] = "(Not found)";
			relatedKeyword = s;
		} else {
			int indexOfHtmlClose = -1;
			int indexOfHtml = -1;
			int indexOfTitleClose = -1;
			String title = "";
			indexOfHtmlClose = content.indexOf(">", indexOfOpen);

			String[] s = new String[5];
			for (int i = 0; i < 5; i++) {
				indexOfHtml = content.indexOf("a href=", indexOfHtmlClose);
				indexOfHtmlClose = content.indexOf(">", indexOfHtml);
				indexOfTitleClose = content.indexOf("<", indexOfHtmlClose);
				title = content.substring(indexOfHtmlClose + 1, indexOfTitleClose);
				int k = i + 1;
				s[i] = k + ". " + title;
			}
			relatedKeyword = s;
		}
	}
}