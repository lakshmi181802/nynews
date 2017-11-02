package com.example.my.news.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.my.news.R;
import com.example.my.news.activities.BookDetailsActivity;
import com.example.my.news.model.Book;

import java.util.List;

/**
 * Created by ELURV001 on 11/1/17.
 */

public class BooksAdapter extends RecyclerView.Adapter<BooksAdapter.ViewHolder> {

    private Context context;
    private List<Book> books;

    public BooksAdapter(Context context, List<Book> books) {
        this.context = context;
        this.books = books;
    }

    @Override
    public BooksAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        return new BooksAdapter.ViewHolder(inflater.inflate(R.layout.list_item_card_book, parent, false));
    }

    @Override
    public void onBindViewHolder(BooksAdapter.ViewHolder holder, final int position) {

        final Book book = books.get(position);

        Log.d(this.getClass().getSimpleName(), "position = " + position
                + ". \n name = " + book.getTitle()
                + ". \n by = " + book.getAuthor());

        holder.title.setText(book.getTitle());
        holder.desc.setText(Html.fromHtml("<h3>Description:</h3>\n<p>" + book.getDescription() + "</p>"));

        holder.author.setText("By " + book.getAuthor());

        holder.layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //navigate to detials screen

                Log.d(this.getClass().getSimpleName(), "BooksAdapter position = " + position);

                Intent intent = new Intent(context, BookDetailsActivity.class);
                intent.putExtra("index", position);
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return books.size();
    }

    public void add(int position, Book item) {
        books.add(position, item);
        notifyItemInserted(position);
    }

    public void remove(int position) {
        books.remove(position);
        notifyItemRemoved(position);
    }

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView title;
        public TextView desc;
        public TextView author;
        public View layout;

        public ViewHolder(View v) {
            super(v);
            layout = v;
            title = (TextView) v.findViewById(R.id.title);
            desc = (TextView) v.findViewById(R.id.desc);
            author = (TextView) v.findViewById(R.id.author);
        }
    }
}
