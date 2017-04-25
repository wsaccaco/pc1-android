package pe.edu.upc.catchup.models;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by proyecto on 10/04/2017.
 */

public class Quote {
    private String id;
    private String author;
    private String quote;
    private String permalink;


    public String getId() {
        return id;
    }

    public Quote setId(String id) {
        this.id = id;
        return this;
    }

    public String getAuthor() {
        return author;
    }

    public Quote setAuthor(String author) {
        this.author = author;
        return this;
    }

    public String getQuote() {
        return quote;
    }

    public Quote setQuote(String quote) {
        this.quote = quote;
        return this;
    }

    public String getPermalink() {
        return permalink;
    }

    public Quote setPermalink(String permalink) {
        this.permalink = permalink;
        return this;
    }

    public static Quote build(JSONObject jsonSource) {
        if(jsonSource == null) return null;
        Quote quote = new Quote();
        try {

            quote.setId(jsonSource.getString("id"))
                  .setAuthor(jsonSource.getString("author"))
                  .setQuote(jsonSource.getString("quote"))
                  .setPermalink(jsonSource.getString("permalink"));

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return quote;
    }

    public static List<Quote> build(JSONArray jsonSources) {
        if(jsonSources == null) return null;
        int length = jsonSources.length();
        List<Quote> quotes = new ArrayList<>();
        for(int i = 0; i < length; i++) {
            try {
                quotes.add(Quote.build(jsonSources.getJSONObject(i)));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return quotes;
    }

}
