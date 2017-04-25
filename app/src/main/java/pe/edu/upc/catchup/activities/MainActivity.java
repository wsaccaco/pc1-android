package pe.edu.upc.catchup.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONArrayRequestListener;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.List;

import pe.edu.upc.catchup.R;
import pe.edu.upc.catchup.adapters.QuotesAdapter;
import pe.edu.upc.catchup.models.Quote;
import pe.edu.upc.catchup.network.NewsApi;

public class MainActivity extends AppCompatActivity {
    RecyclerView quotesRecyclerView;
    RecyclerView.LayoutManager sourcesLayoutManager;
    QuotesAdapter quotesAdapter;
    List<Quote> quotes;
    private static String TAG = "Tech Quotes";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        quotesRecyclerView = (RecyclerView) findViewById(R.id.quotesRecyclerView);
        sourcesLayoutManager = new LinearLayoutManager(this);
        quotesAdapter = new QuotesAdapter();
        quotes = new ArrayList<>();
        quotesAdapter.setQuotes(quotes);
        quotesRecyclerView.setLayoutManager(sourcesLayoutManager);
        quotesRecyclerView.setAdapter(quotesAdapter);
        updateSources();
    }

    private void updateSources() {
        AndroidNetworking.get(NewsApi.QUOTES_URL)
                .addQueryParameter("language", "en")
                .setPriority(Priority.LOW)
                .setTag(TAG)
                .build()
                .getAsJSONArray(new JSONArrayRequestListener() {
                    @Override
                    public void onResponse(JSONArray response) {

                            if(response.length() < 0) {
                                Log.d(TAG, "error");
                                return;
                            }

                            quotes = Quote.build(response);
                            quotesAdapter.setQuotes(quotes);
                            quotesAdapter.notifyDataSetChanged();

                    }

                    @Override
                    public void onError(ANError anError) {
                        Log.d(TAG, anError.getLocalizedMessage());
                    }
                });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

//    @Override
//    public void onConfigurationChanged(Configuration newConfig) {
//        super.onConfigurationChanged(newConfig);
//        Log.d(TAG, "Orientation Changed:" + Integer.toString(newConfig.orientation));
//        //spanCount = (newConfig.orientation == ORIENTATION_LANDSCAPE) ? 3 : 2;
//        ((GridLayoutManager)sourcesRecyclerView.getLayoutManager()).setSpanCount(spanCount);
//    }
}
