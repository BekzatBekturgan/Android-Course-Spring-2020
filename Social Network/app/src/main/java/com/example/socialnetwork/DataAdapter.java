package com.example.socialnetwork;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

class DataAdapter extends RecyclerView.Adapter<DataAdapter.ViewHolder> {

    private LayoutInflater inflater;
    private List<News> news;

    DataAdapter(Context context, List<News> news) {
        this.news = news;
        this.inflater = LayoutInflater.from(context);
    }
    @Override
    public DataAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = inflater.inflate(R.layout.list_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(DataAdapter.ViewHolder holder, int position) {
        News news = this.news.get(position);
        holder.icnUser.setImageResource(news.getIcnUser());
        holder.imgPost.setImageResource(news.getImgPost());
        holder.username.setText(news.getUsername());
        holder.date.setText(news.getDate());
        holder.description.setText(news.getDescription());
    }

    @Override
    public int getItemCount() {
        return news.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        final ImageView icnUser, imgPost; //icnLike, icnComment, icnReply, icnVision;
        final TextView username, date, description; //cntLike, cntComment, cntReply, cntVision;
        ViewHolder(View view){
            super(view);
            icnUser = (ImageView)view.findViewById(R.id.icnUser);
            imgPost = (ImageView)view.findViewById(R.id.imgPost);

            /*icnLike = (ImageView)view.findViewById(R.id.icnLike);
            icnComment = (ImageView)view.findViewById(R.id.icnComment);
            icnReply = (ImageView)view.findViewById(R.id.icnReply);
            icnVision = (ImageView)view.findViewById(R.id.icnVision);
             */
            username = (TextView) view.findViewById(R.id.username);
            date = (TextView) view.findViewById(R.id.date);
            description = (TextView) view.findViewById(R.id.description);

            /*cntLike = (TextView) view.findViewById(R.id.cntLike);
            cntComment = (TextView) view.findViewById(R.id.cntComment);
            cntReply = (TextView) view.findViewById(R.id.cntReply);
            cntVision = (TextView) view.findViewById(R.id.cntVision);
             */
        }
    }
}
