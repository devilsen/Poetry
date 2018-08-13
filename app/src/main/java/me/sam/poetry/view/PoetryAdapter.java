package me.sam.poetry.view;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import me.sam.poetry.R;
import me.sam.poetry.data.model.Poetry;

/**
 * desc :
 * date : 2018/8/12
 *
 * @author : dongSen
 */
public class PoetryAdapter extends RecyclerView.Adapter<PoetryAdapter.ViewHolder> {

    private final ArrayList<Poetry> dataList = new ArrayList<>();

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_item_poetry, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Poetry poetry = dataList.get(position);
        holder.title.setText(poetry.getTitle());
        holder.author.setText(poetry.getAuthor());
        holder.content.setText(poetry.getContent());
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public void addData(List<Poetry> list) {
        dataList.addAll(list);
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        private TextView title;
        private TextView author;
        private TextView content;

        ViewHolder(View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.text_view_title);
            author = itemView.findViewById(R.id.text_view_author);
            content = itemView.findViewById(R.id.text_view_content);
        }
    }
}
