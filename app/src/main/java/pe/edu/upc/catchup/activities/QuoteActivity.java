package pe.edu.upc.catchup.activities;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.androidnetworking.widget.ANImageView;

import pe.edu.upc.catchup.CatchUpApp;
import pe.edu.upc.catchup.R;
import pe.edu.upc.catchup.models.Article;
import pe.edu.upc.catchup.models.Quote;

public class QuoteActivity extends AppCompatActivity {

    TextView    authorTextView;
    TextView    quoteTextView;
    Quote quote;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quote);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        quote = CatchUpApp.getInstance().getCurrentQuote();
        authorTextView = (TextView) findViewById(R.id.authorTextView);
        quoteTextView = (TextView) findViewById(R.id.quoteTextView);

        authorTextView.setText(quote.getAuthor());
        quoteTextView.setText(quote.getQuote());
        /*
        browserImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent browerIntent = new Intent(Intent.ACTION_VIEW);
                browerIntent.setData(Uri.parse(article.getUrl()));
                startActivity(browerIntent);
            }
        });
        */
    }

}
