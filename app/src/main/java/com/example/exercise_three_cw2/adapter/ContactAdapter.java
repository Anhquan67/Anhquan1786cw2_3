package com.example.exercise_three_cw2.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.example.exercise_three_cw2.R;
import com.example.exercise_three_cw2.model.ContactModel;

import java.util.List;

public class ContactAdapter extends RecyclerView.Adapter<ContactAdapter.ContactViewHolder> {
    private List<ContactModel> itemList;

    public ContactAdapter(List<ContactModel> itemList) {
        this.itemList = itemList;
    }

    public class ContactViewHolder extends RecyclerView.ViewHolder {
        public AppCompatTextView contactName;
        public AppCompatTextView contactDob;
        public AppCompatTextView contactEmail;
        public AppCompatImageView imageAvatar;

        public ContactViewHolder(View itemView) {
            super(itemView);
            contactName = itemView.findViewById(R.id.tvContactName);
            contactDob = itemView.findViewById(R.id.tvContactDob);
            contactEmail = itemView.findViewById(R.id.tvContactEmail);
            imageAvatar = itemView.findViewById(R.id.imvImageAvatar);
        }
    }

    @Override
    public ContactViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.contact_item_layout, parent, false);
        return new ContactViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ContactViewHolder holder, int position) {
        ContactModel contact = itemList.get(position);
        holder.contactName.setText(contact.getName());
        holder.contactDob.setText(contact.getDob());
        holder.contactEmail.setText(contact.getEmail());
        Glide.with(holder.itemView.getContext())
                .load(contact.getAvatar())
                .into(holder.imageAvatar);
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }
}