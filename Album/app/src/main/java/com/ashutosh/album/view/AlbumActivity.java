package com.ashutosh.album.view;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;

import com.ashutosh.album.R;
import com.ashutosh.album.common.Constant;
import com.ashutosh.album.listener.INotifyDataSetListener;
import com.ashutosh.album.viewmodel.AlbumListItem;
import com.ashutosh.album.viewmodel.ViewModel;

import java.util.ArrayList;
import java.util.List;

public class AlbumActivity extends AppCompatActivity implements INotifyDataSetListener {

    private RecyclerViewAdapter mAdapter;
    private List<AlbumListItem> mAlbumList;
    private LinearLayout mProgressBarLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_album);
        RecyclerView recycleListView = findViewById(R.id.recyclerView);
        mProgressBarLayout = findViewById(R.id.progressbarlayout);
        mAlbumList = new ArrayList<>();
        recycleListView.setLayoutManager(new LinearLayoutManager(this));
        mAdapter = new RecyclerViewAdapter(getApplicationContext(), mAlbumList);
        recycleListView.setAdapter(mAdapter);
        ViewModel viewModel = new ViewModel(getApplicationContext(), this);
        viewModel.requestAlbumList(getApplicationContext());
    }

    @Override
    public void notifyDataReceived(final List<AlbumListItem> list) {
        mAlbumList.clear();
        mAlbumList.addAll(list);
        hideProgressBar();
        mAdapter.notifyDataSetChanged();
        Log.d(Constant.TAG, "List : " + list.size());
        if (list.isEmpty()) {
            showAlertDialog(this.getString(R.string.no_data_available));
        }
    }

    @Override
    public void showProgressBar() {
        mProgressBarLayout.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgressBar() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                mProgressBarLayout.setVisibility(View.GONE);
            }
        });
    }

    @Override
    public void showAlertDialog(final String message) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                final AlertDialog.Builder alertDialog = new AlertDialog.Builder(AlbumActivity.this);
                alertDialog.setTitle(R.string.dialog_title);
                alertDialog.setMessage(message);
                alertDialog.setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        finish();
                    }
                });

                alertDialog.setIcon(android.R.drawable.ic_dialog_alert);
                alertDialog.setCancelable(false);
                hideProgressBar();
                alertDialog.show();
            }
        });
    }

    @Override
    public void onBackPressed() {
        finish();
    }
}
