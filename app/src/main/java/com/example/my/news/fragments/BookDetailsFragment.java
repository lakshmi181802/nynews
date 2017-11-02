package com.example.my.news.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.my.news.NyTimesApplication;
import com.example.my.news.R;
import com.example.my.news.model.Book;

import java.text.DecimalFormat;

/**
 * Created by ELURV001 on 11/1/17.
 */

public class BookDetailsFragment extends Fragment {

    private TextView title;
    private TextView author;
    private TextView desc;
    private TextView price;
    private TextView contributor;

    public BookDetailsFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_book_details, container, false);

        title = (TextView) view.findViewById(R.id.title);
        author = (TextView) view.findViewById(R.id.author);
        desc = (TextView) view.findViewById(R.id.desc);
        price = (TextView) view.findViewById(R.id.price);
        contributor = (TextView) view.findViewById(R.id.contributor);

        int index = 0;
        if (getArguments() != null) {
            index = getArguments().getInt("index");
        }

        Log.d(this.getClass().getSimpleName(), "BookDetailsFragment index = " + index);

        if (index >= 0 && index < NyTimesApplication.getInstance().getContent().getContentBooks().getResults().length) {

            Book book = NyTimesApplication.getInstance().getContent().getContentBooks().getResults()[index];

            DecimalFormat df = new DecimalFormat("#.00");

            title.setText(book.getTitle());
            author.setText(Html.fromHtml("<h3>Author:</h3>\n<p>" + book.getAuthor() + "</p>"));
            desc.setText(Html.fromHtml("<h3>Description:</h3>\n<p>" + book.getDescription() + "</p>"));
            contributor.setText(Html.fromHtml("<h3>Contributor:</h3>\n<p>" + book.getContributor() + "</p>"));
            price.setText(Html.fromHtml("<h3>$ " + String.valueOf(df.format(book.getPrice())) + " </h3>"));
        }

        return view;
    }
}
