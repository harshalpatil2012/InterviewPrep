import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.function.*;
import java.util.regex.*;
import java.util.stream.*;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;
import java.net.*;
import org.json.simple.*;
import org.json.simple.parser.*;

class Result {

	/*
	 * Complete the 'getArticleTitles' function below.
	 *
	 * The function is expected to return a STRING_ARRAY. The function accepts
	 * STRING author as parameter.
	 * 
	 * URL for cut and paste:
	 * https://jsonmock.hackerrank.com/api/articles?author=<authorName>&page=<num>
	 *
	 */

	public static List<String> getArticleTitles(String author)
			throws IOException, org.json.simple.parser.ParseException {
		
		List<String> titles = new ArrayList<>();
		int pageNum = 1;
		int totalPages = 1;

		while (pageNum <= totalPages) {
			String url = "https://jsonmock.hackerrank.com/api/articles?author=" + author + "&page=" + pageNum;
			String response = makeHttpRequest(url);

			// Parse the response to extract relevant data from JSON reponse ///object
			JSONParser parser = new JSONParser();
			JSONObject jsonObject = (JSONObject) parser.parse(response);
			int currentPage = Integer.parseInt(jsonObject.get("page").toString());
			totalPages = Integer.parseInt(jsonObject.get("total_pages").toString());

			List<Article> articles = parseArticles(jsonObject);
			for (Article article : articles) {
				String title = getTitle(article);
				if (title != null) {
					titles.add(title);
				}
			}

			pageNum++;
		}

		return titles;
	}

	/**
	 * Make http request to given endpoint
	 * 
	 * @param url
	 * @return
	 * @throws IOException
	 */
	private static String makeHttpRequest(String url) throws IOException {
		
		URL apiUrl = new URL(url);
		HttpURLConnection connection = (HttpURLConnection) apiUrl.openConnection();
		connection.setRequestMethod("GET");

		BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
		StringBuilder response = new StringBuilder();
		String line;
		while ((line = reader.readLine()) != null) {
			response.append(line);
		}
		reader.close();

		return response.toString();
	}

	/**
	 * Parse Json to get list of Articles
	 * 
	 * @param json
	 * @return
	 */

	private static List<Article> parseArticles(JSONObject json) {
		
		JSONArray data = (JSONArray) json.get("data");
		List<Article> articles = new ArrayList<>();

		for (Object obj : data) {
			JSONObject articleJson = (JSONObject) obj;
			Article article = new Article();
			article.setTitle((String) articleJson.get("title"));
			article.setStoryTitle((String) articleJson.get("story_title"));
			articles.add(article);
		}

		return articles;
	}

	/**
	 * Get titel for each article
	 * 
	 * @param article
	 * @return
	 */
	private static String getTitle(Article article) {
		
		String title = article.getTitle();
		String storyTitle = article.getStoryTitle();

		if (title != null && !title.isEmpty()) {
			return title;
		} else if (storyTitle != null && !storyTitle.isEmpty()) {
			return storyTitle;
		} else {
			return null;
		}
	}

}

// Define Article POJO
class Article {
	private String title;
	private String storyTitle;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getStoryTitle() {
		return storyTitle;
	}

	public void setStoryTitle(String storyTitle) {
		this.storyTitle = storyTitle;
	}
}

public class Solution {
	public static void main(String[] args) throws IOException, org.json.simple.parser.ParseException {
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

		String author = bufferedReader.readLine();
		try {
			List<String> result = Result.getArticleTitles(author);

			bufferedWriter.write(result.stream().collect(joining("\n")) + "\n");
		} catch (IOException | org.json.simple.parser.ParseException e) {
			e.printStackTrace();
		}
		bufferedReader.close();
		bufferedWriter.close();
	}
}
