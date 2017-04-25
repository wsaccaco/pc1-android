package pe.edu.upc.catchup.adapters;

import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import pe.edu.upc.catchup.CatchUpApp;
import pe.edu.upc.catchup.R;
import pe.edu.upc.catchup.activities.ArticlesActivity;
import pe.edu.upc.catchup.activities.QuoteActivity;
import pe.edu.upc.catchup.models.Quote;

/**
 * Created by proyecto on 10/04/2017.
 */

public class QuotesAdapter extends RecyclerView.Adapter<QuotesAdapter.ViewHolder> {
    private List<Quote> quotes;
    @Override
    public QuotesAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.card_source,parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(QuotesAdapter.ViewHolder holder, final int position) {
        holder.authorTextView.setText(quotes.get(position).getAuthor());
        holder.quoteTextView.setText(quotes.get(position).getQuote());
        holder.quotesCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CatchUpApp
                        .getInstance()
                        .setCurrentQuote(quotes.get(position));
                v.getContext().startActivity(
                        new Intent(
                            v.getContext(),
                            QuoteActivity.class));
            }
        });
    }

    @Override
    public int getItemCount() {
        return quotes.size();
    }

    public List<Quote> getQuotes() {
        return quotes;
    }

    public void setQuotes(List<Quote> quotes) {
        this.quotes = quotes;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        CardView quotesCardView;
        TextView authorTextView;
        TextView quoteTextView;
        public ViewHolder(View itemView) {
            super(itemView);
            quotesCardView = (CardView) itemView.findViewById(R.id.quotesCardView);
            authorTextView = (TextView) itemView.findViewById(R.id.authorTextView);
            quoteTextView = (TextView) itemView.findViewById(R.id.quoteTextView);
        }
    }
}
