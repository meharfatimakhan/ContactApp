package com.example.contacts;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Parcelable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import de.hdodenhof.circleimageview.CircleImageView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.io.ByteArrayOutputStream;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;

public class AdapterContact extends RecyclerView.Adapter<AdapterContact.ViewHolder>{

    private Context mContext;
    private LayoutInflater inflater;
    List<ContactClass> contactClassList;

    public AdapterContact(Context context, List<ContactClass> contactClassList){
        this.mContext=context;
        this.contactClassList = contactClassList;
        setHasStableIds(true);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.contact_listitem,parent,false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {

        final ContactClass contact = contactClassList.get(position);

        holder.imageName.setText(contact.getName());

        if (contact.getPhoto() != null){
            Glide.with(mContext).asDrawable().load(contact.getPhoto()).into(holder.image);
        }else{
            holder.image.setImageResource(R.mipmap.ic_launcher_round);
        }

        holder.parentLayout.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                Intent intent=new Intent(mContext, MyDetail.class);
                intent.putExtra("picture", contact.getPhoto());
                intent.putExtra("name",contact.getName());
                intent.putExtra("contact",contact.getNumber());
                intent.putExtra("email",contact.getEmail());
                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @Override
    public void setHasStableIds(boolean hasStableIds) {
        hasStableIds = true;
        super.setHasStableIds(hasStableIds);
    }

    @Override
    public int getItemCount() {
        return contactClassList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        CircleImageView image;
        TextView imageName;
        TextView phoneNumber;
        TextView emailID;
        RelativeLayout parentLayout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.image);
            imageName=itemView.findViewById(R.id.image_name);
            parentLayout=itemView.findViewById(R.id.parent_layout);
        }
    }
}
