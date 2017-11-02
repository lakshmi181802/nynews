package com.example.my.news.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.my.news.NyTimesApplication;
import com.example.my.news.R;
import com.example.my.news.model.Article;
import com.example.my.news.model.ArticleDocument;
import com.example.my.news.model.Multimedia;
import com.example.my.news.restclient.NyTimesClient;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by ELURV001 on 11/1/17.
 */

public class ArticlesAdapter extends RecyclerView.Adapter<ArticlesAdapter.ViewHolder> {

    private Context context;
    private List<Article> articles;
    private List<ArticleDocument> articleDocs;

    public ArticlesAdapter(Context context, List<Article> articles, List<ArticleDocument> articleDocs) {
        this.context = context;
        this.articles = articles;
        this.articleDocs = articleDocs;
    }

    @Override
    public ArticlesAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        return new ViewHolder(inflater.inflate(R.layout.list_item_card_article, parent, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {

        String imageUrl = null;
        if (NyTimesApplication.getInstance().getContent().isFilterApplied() && articleDocs != null && articleDocs.size() > 0) {

            if (position >= articleDocs.size()) {
                return;
            }

            final ArticleDocument articleDoc = articleDocs.get(position);

            if (null != articleDoc.getHeadline()
                    && null != articleDoc.getHeadline().getPrint_headline()
                    && !articleDoc.getHeadline().getPrint_headline().isEmpty()) {
                holder.header.setText(articleDoc.getHeadline().getMain());
            } else if (null != articleDoc.getHeadline()
                    && null != articleDoc.getHeadline().getMain()
                    && !articleDoc.getHeadline().getMain().isEmpty()) {
                holder.header.setText(articleDoc.getHeadline().getMain());
            }

            if (null != articleDoc.getByline()
                    && null != articleDoc.getByline().getOriginal()
                    && !articleDoc.getByline().getOriginal().isEmpty()) {
                holder.footer.setText(articleDoc.getByline().getOriginal());
            }

            Log.d(this.getClass().getSimpleName(), "articleDoc. position = " + position
                    + ". \n name = " + holder.header.getText()
                    + ". \n by = " + holder.footer.getText());


            if (null != articleDoc.getMultimedia() && articleDoc.getMultimedia().length > 0) {
                for (Multimedia multimedia : articleDoc.getMultimedia()) {
                    if (multimedia.getSubtype().equalsIgnoreCase("wide")) {
                        imageUrl = multimedia.getUrl();
                        break;
                    }
                }
            }
            if (null != imageUrl && !imageUrl.isEmpty()) {
                imageUrl = NyTimesClient.ARTICLE_SEARCH_DOC_IMAGE_BASE_ADDRESS + imageUrl;
            }

            holder.layout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //navigate to detials screen
                }
            });
        } else {
            final Article article = articles.get(position);

            holder.header.setText(article.getTitle());
            holder.footer.setText(article.getByline());

            Log.d(this.getClass().getSimpleName(), "article. position = " + position
                    + ". \n name = " + holder.header.getText()
                    + ". \n by = " + holder.footer.getText());

            if (null != article.getMultimedia() && article.getMultimedia().length > 0) {
                for (Multimedia multimedia : article.getMultimedia()) {
                    if (multimedia.getFormat().equalsIgnoreCase("superJumbo")) {
                        imageUrl = multimedia.getUrl();
                        break;
                    }
                }
            }

            holder.layout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //navigate to detials screen
                }
            });
        }

        if (null != imageUrl && !imageUrl.isEmpty()) {
            Picasso.with(this.context).load(imageUrl).into(holder.icon);
        }
    }

    @Override
    public int getItemCount() {
        return articles.size();
    }

    public void add(int position, Article item) {
        articles.add(position, item);
        notifyItemInserted(position);
    }

    public void remove(int position) {
        articles.remove(position);
        notifyItemRemoved(position);
    }

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView header;
        public TextView footer;
        public ImageView icon;
        public View layout;

        public ViewHolder(View v) {
            super(v);
            layout = v;
            header = (TextView) v.findViewById(R.id.firstLine);
            footer = (TextView) v.findViewById(R.id.secondLine);
            icon = (ImageView) v.findViewById(R.id.icon);
        }
    }
}
