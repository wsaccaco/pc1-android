package pe.edu.upc.catchup.network;

import pe.edu.upc.catchup.models.Article;
import pe.edu.upc.catchup.models.Quote;

/**
 * Created by proyecto on 10/04/2017.
 */

public class NewsApi {
    public static String QUOTES_URL = "http://quotes.stormconsultancy.co.uk/quotes.json";
    public static String ARTICLES_URL = "https://newsapi.org/v1/articles";
    private Quote currentQuote;
    private Article currentArticle;

    public Quote getCurrentQuote() {
        return currentQuote;
    }

    public void setCurrentQuote(Quote currentQuote) {
        this.currentQuote = currentQuote;
    }

    public Article getCurrentArticle() {
        return currentArticle;
    }

    public void setCurrentArticle(Article currentArticle) {
        this.currentArticle = currentArticle;
    }
}
