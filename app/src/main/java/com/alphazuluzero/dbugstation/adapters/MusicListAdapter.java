package com.alphazuluzero.dbugstation.adapters;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.drawable.RoundedBitmapDrawable;
import androidx.core.graphics.drawable.RoundedBitmapDrawableFactory;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.alphazuluzero.dbugstation.R;
import com.alphazuluzero.dbugstation.activities.MusicInfoActivity;
import com.alphazuluzero.dbugstation.databinding.MusicItemBinding;
import com.alphazuluzero.dbugstation.models.MusicListModel;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Locale;

/**
 * A two-item RecyclerView Adapter for displaying music lists
 */
public class MusicListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    /**
     * {@link ArrayList} of {@link MusicListModel} to display
     */
    private final ArrayList<MusicListModel> musicList;
    /**
     * {@link AppCompatActivity} reference
     */
    private final AppCompatActivity context;

    private static final String TAG = "MusicListAdapter";

    /**
     * Create an instance of {@link MusicListAdapter}
     *
     * @param musicList An {@link ArrayList} of {@link MusicListModel} to display
     * @param context   An {@link AppCompatActivity} reference
     */
    public MusicListAdapter(ArrayList<MusicListModel> musicList, AppCompatActivity context) //adapter gets the json array and turns it into a list of products
    {
        this.musicList = musicList;
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        MusicItemBinding musicItemBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.music_item, parent, false);

//        if(viewType==IMAGE_TYPE)
        return new ViewHolder(musicItemBinding);
    }

    // Setting up data for the ViewHolders
    @Override
    public void onBindViewHolder(@NonNull final RecyclerView.ViewHolder holder, final int position) {
        int index1 = holder.getAdapterPosition() * 2;
        int index2 = index1 + 1;
        ViewHolder viewHolder = (ViewHolder) holder;
        if (musicList.size() > index1) {
            MusicListModel musicModel1 = musicList.get(index1);
            viewHolder.musicItemBinding.music1Album.setText(musicModel1.getAlbumName());
            viewHolder.musicItemBinding.music1Name.setText(musicModel1.getMusicName());
            viewHolder.musicItemBinding.music1Duration.setText(String.format(Locale.US, "%d MIN", (int) musicModel1.getDurationInSeconds() / 60));

            Picasso.get().load(musicModel1.getMusicImageResInt()).into(viewHolder.musicItemBinding.music1Image, new Callback() {
                @Override
                public void onSuccess() {
                    Bitmap imageBitmap = ((BitmapDrawable) viewHolder.musicItemBinding.music1Image.getDrawable()).getBitmap();
                    RoundedBitmapDrawable imageDrawable = RoundedBitmapDrawableFactory.create(context.getResources(), imageBitmap);
                    imageDrawable.setCircular(true);
                    imageDrawable.setCornerRadius(20);
                    viewHolder.musicItemBinding.music1Image.setImageDrawable(imageDrawable);
                }

                @Override
                public void onError(Exception e) {

                }
            });

            viewHolder.musicItemBinding.view1.setOnClickListener(v -> context.startActivity(new Intent(context, MusicInfoActivity.class).putExtra("musicId", musicModel1.getId())));

        }
        if (musicList.size() > index2) {
            viewHolder.musicItemBinding.view2.setVisibility(View.VISIBLE);
            MusicListModel musicModel2 = musicList.get(index2);
            viewHolder.musicItemBinding.music2Album.setText(musicModel2.getAlbumName());
            viewHolder.musicItemBinding.music2Name.setText(musicModel2.getMusicName());
            viewHolder.musicItemBinding.music2Duration.setText(String.format(Locale.US, "%d MIN", (int) musicModel2.getDurationInSeconds() / 60));

            Picasso.get().load(musicModel2.getMusicImageResInt()).into(viewHolder.musicItemBinding.music2Image, new Callback() {
                @Override
                public void onSuccess() {
                    Bitmap imageBitmap = ((BitmapDrawable) viewHolder.musicItemBinding.music2Image.getDrawable()).getBitmap();
                    RoundedBitmapDrawable imageDrawable = RoundedBitmapDrawableFactory.create(context.getResources(), imageBitmap);
                    imageDrawable.setCircular(true);
                    imageDrawable.setCornerRadius(20);
                    viewHolder.musicItemBinding.music2Image.setImageDrawable(imageDrawable);
                }

                @Override
                public void onError(Exception e) {

                }
            });

            viewHolder.musicItemBinding.view2.setOnClickListener(v -> context.startActivity(new Intent(context, MusicInfoActivity.class).putExtra("musicId", musicModel2.getId())));
        } else viewHolder.musicItemBinding.view2.setVisibility(View.GONE);
    }

    @Override
    public int getItemCount() {
        int i = musicList.size();
        Log.i(TAG, "getItemCount: " + i);
        return i % 2 == 0 ? i / 2 : i / 2 + 1;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private MusicItemBinding musicItemBinding;

        public MusicItemBinding getMusicItemBinding() {
            return musicItemBinding;
        }

        public ViewHolder(@NonNull MusicItemBinding musicItemBinding) {
            super(musicItemBinding.getRoot());
            this.musicItemBinding = musicItemBinding;
        }
    }

}
