package com.example.contacts;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;


public class ContactAdapter extends RecyclerView.Adapter<ContactAdapter.ViewHolder> {
    private Context mContext;
    private LayoutInflater inflater;
    List<String> allContacts;

    public ContactAdapter(Context context, List<String> allContacts){
        this.mContext=context;
        this.allContacts = allContacts;
        setHasStableIds(true);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.contact_item,parent,false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {

        final String contact = allContacts.get(position);
        holder.phoneNumber.setText(contact);
        /*holder.parentLayout.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                Intent intent=new Intent(mContext, MyDetail.class);

               intent.putExtra("contact",contact.getNumber());

                mContext.startActivity(intent);
            }
        });*/
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
        return allContacts.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView phoneNumber;
        RelativeLayout parentLayout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            phoneNumber=itemView.findViewById(R.id.contact_number);
            parentLayout=itemView.findViewById(R.id.parent_layout);
        }
    }
}
