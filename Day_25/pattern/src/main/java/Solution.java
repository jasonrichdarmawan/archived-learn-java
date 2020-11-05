import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;
import java.net.*;

import com.google.gson.*;

class MovieTitle {
  String Title;
  int Year;
  String imdbID;
}

class JSONResponse {
  int page;
  int per_page;
  int total;
  int total_pages;
  List<MovieTitle> data;
}

public class Solution {
  /*
   * Complete the function below.
   */

  static String[] getMovieTitles(String substr) {
    List<String> titles = new ArrayList<String>();
    int pageNumber = 1;
    int total_pages = 1;

    while (pageNumber <= total_pages) {
      try {
        System.out.println("https://jsonmock.hackerrank.com/api/movies/search/?Title=" + substr + "&pageNumber=" + pageNumber);
        URL url = new URL("https://jsonmock.hackerrank.com/api/movies/search/?Title=" + substr + "&pageNumber=" + pageNumber);
        HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
        httpURLConnection.setRequestMethod("GET");

        String response = null;
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));
        while ((response = bufferedReader.readLine()) != null) {
          Gson gson = new Gson();
          JSONResponse json = gson.fromJson(response, JSONResponse.class);
          total_pages = json.total_pages;
          List<MovieTitle> data = json.data;
          for (MovieTitle title : data) {
            titles.add(title.Title);
          }
        }
        bufferedReader.close();
        pageNumber++;
      } catch (MalformedURLException e) {
        e.printStackTrace();
      } catch (IOException e) {
        e.printStackTrace();
      }
    }

    //complete function here
    return titles.toArray(new String[0]);
  }


  public static void main(String[] args) throws IOException {
    Scanner in = new Scanner(System.in);
    final String fileName = "file.txt";
    BufferedWriter bw = new BufferedWriter(new FileWriter(fileName));
    String[] res;
    String _substr;
    try {
      _substr = in.nextLine();
    } catch (Exception e) {
      _substr = null;
    }

    res = getMovieTitles(_substr);
    for (int res_i = 0; res_i < res.length; res_i++) {
      bw.write(String.valueOf(res[res_i]));
      bw.newLine();
    }

    bw.close();
  }
}