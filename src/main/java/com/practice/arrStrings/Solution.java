package com.practice.arrStrings;

import java.io.*;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

class Result {

    /**
     * Retrieves article titles for a given author using the HackerRank mock API.
     *
     * @param author The author's name to search for.
     * @return A list of article titles.
     * @throws IOException          If an I/O error occurs.
     * @throws InterruptedException If the operation is interrupted.
     */
    public static List<String> getArticleTitles(String author) throws IOException, InterruptedException {
        List<String> titles = new ArrayList<>();
        int pageNum = 1;
        int totalPages = 1;

        HttpClient client = HttpClient.newHttpClient();

        while (pageNum <= totalPages) {
            String url = "https://jsonmock.hackerrank.com/api/articles?author=" + author + "&page=" + pageNum;
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(url))
                    .GET()
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            JSONObject jsonObject = new JSONObject(response.body());
            totalPages = jsonObject.getInt("total_pages");

            JSONArray articles = jsonObject.getJSONArray("data");
            for (int i = 0; i < articles.length(); i++) {
                JSONObject articleJson = articles.getJSONObject(i);
                String title = articleJson.optString("title", null);
                String storyTitle = articleJson.optString("story_title", null);

                if (title != null && !title.isEmpty()) {
                    titles.add(title);
                } else if (storyTitle != null && !storyTitle.isEmpty()) {
                    titles.add(storyTitle);
                }
            }

            pageNum++;
        }

        return titles;
    }
}

public class Solution {
    public static void main(String[] args) throws IOException, InterruptedException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        String author = bufferedReader.readLine();
        try {
            List<String> result = Result.getArticleTitles(author);

            bufferedWriter.write(String.join("\n", result) + "\n");
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        } finally {
            bufferedReader.close();
            bufferedWriter.close();
        }
    }
}
