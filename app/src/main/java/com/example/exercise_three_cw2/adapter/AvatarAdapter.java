package com.example.exercise_three_cw2.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.example.exercise_three_cw2.R;
import com.example.exercise_three_cw2.model.AvatarModel;

import java.util.List;

public class AvatarAdapter extends RecyclerView.Adapter<AvatarAdapter.AvatarViewHolder> {
    private List<AvatarModel> itemList;
    private OnItemClickedListener onItemClickedListener;

    public AvatarAdapter(List<AvatarModel> itemList, OnItemClickedListener onItemClickedListener) {
        this.itemList = itemList;
        this.onItemClickedListener = onItemClickedListener;
    }

    public interface OnItemClickedListener {
        void onItemClicked(int position);
    }

    public class AvatarViewHolder extends RecyclerView.ViewHolder {
        private AppCompatImageView imageAvatar;
        private AppCompatImageView imvSelected;

        public AvatarViewHolder(View itemView) {
            super(itemView);
            imageAvatar = itemView.findViewById(R.id.imvAvatar);
            imvSelected = itemView.findViewById(R.id.imvSelected);
        }
    }

    @Override
    public AvatarViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.avatar_item_layout, parent, false);
        return new AvatarViewHolder(view);
    }

    @Override
    public void onBindViewHolder(AvatarViewHolder holder, int position) {
        AvatarModel avatar = itemList.get(position);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemClickedListener.onItemClicked(position);
            }
        });
        if (avatar.getSelected()) {
            holder.imvSelected.setVisibility(View.VISIBLE);
        } else {
            holder.imvSelected.setVisibility(View.INVISIBLE);
        }
        Glide.with(holder.itemView.getContext())
                .load(avatar.getAvatar())
                .centerCrop()
                .into(holder.imageAvatar);
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }
}