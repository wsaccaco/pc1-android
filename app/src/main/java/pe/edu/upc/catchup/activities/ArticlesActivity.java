package pe.edu.upc.catchup.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import pe.edu.upc.catchup.CatchUpApp;
import pe.edu.upc.catchup.R;
import pe.edu.upc.catchup.adapters.ArticlesAdapter;
import pe.edu.upc.catchup.models.Article;
import pe.edu.upc.catchup.models.Quote;
import pe.edu.upc.catchup.network.NewsApi;

import static android.content.res.Configuration.ORIENTATION_LANDSCAPE;

public class ArticlesActivity extends AppCompatActivity {
    RecyclerView quotesRecyclerView;
    ArticlesAdapter             articlesAdapter;
    RecyclerView.LayoutManager  articlesLayoutManager;
    List<Article>               articles;
    Quote quote;
    int                         spanCount;
    private static String       TAG = "Tech Qoutes";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_articles);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        quotesRecyclerView = (RecyclerView) findViewById(R.id.articlesRecyclerView);
        articlesAdapter = new ArticlesAdapter();
        articles = new ArrayList<>();
        articlesAdapter.setArticles(articles);
        spanCount =
                getResources().getConfiguration().orientation ==
                        ORIENTATION_LANDSCAPE ? 3 : 2;
        articlesLayoutManager = new GridLayoutManager(this, spanCount);
        quotesRecyclerView.setAdapter(articlesAdapter);
        quotesRecyclerView.setLayoutManager(articlesLayoutManager);
        quote = CatchUpApp.getInstance().getCurrentQuote();
        updateArticles();
    }

    private void updateArticles() {
        AndroidNetworking.get(NewsApi.ARTICLES_URL)
                .addQueryParameter("quote", quote.getId())
                .addQueryParameter("apiKey",
                        getString(R.string.news_api_key))
                .setTag(TAG)
                .setPriority(Priority.LOW)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            if(response.getString("status").equalsIgnoreCase("error")) {
                                Log.d(TAG, response.getString("message"));
                                return;
                            }
                            articles = Article.build(response.getJSONArray("articles"), quote);
                            articlesAdapter.setArticles(articles);
                            articlesAdapter.notifyDataSetChanged();

                        } catch (JSONException e) {
                            e.printStackTrace();
                            return;
                        }
                    }

                    @Override
                    public void onError(ANError anError) {
                        Log.d(TAG, anError.getLocalizedMessage());

                    }
                });
    }

}
