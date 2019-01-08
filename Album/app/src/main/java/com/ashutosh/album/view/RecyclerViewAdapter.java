package com.ashutosh.album.view;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.ashutosh.album.R;
import com.ashutosh.album.viewmodel.AlbumListItem;

import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyHolder> {

    private final LayoutInflater mLayoutInflater;
    private final List<AlbumListItem> mAlbumList;
    private final Context mContext;

    public RecyclerViewAdapter(Context context, List<AlbumListItem> list) {
        mLayoutInflater = LayoutInflater.from(context);
        mAlbumList = list;
        mContext = context;
    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mLayoutInflater.inflate(R.layout.listitem, parent, false);
        return new MyHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder holder, int position) {
        String title = mAlbumList.get(position).getTitle();
        holder.mTitle.setText(title);
    }

    @Override
    public int getItemCount() {
        return mAlbumList.size();
    }

    class MyHolder extends RecyclerView.ViewHolder {

        final TextView mTitle;

        MyHolder(View itemView) {
            super(itemView);
            mTitle = itemView.findViewById(R.id.albumTitle);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    showToast();
                }

                private void showToast() {
                    Toast.makeText(mContext, R.string.thank_you_message, Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
}
