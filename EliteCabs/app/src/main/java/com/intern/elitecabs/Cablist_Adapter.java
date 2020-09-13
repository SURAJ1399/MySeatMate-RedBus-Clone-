package com.intern.elitecabs;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.view.DragAndDropPermissions;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

public class Cablist_Adapter extends  RecyclerView.Adapter<Cablist_Adapter.ViewHolder> {

    @NonNull

    Context context;
    public List<CablistModel>cablistModelList;
    String date;

    public Cablist_Adapter(List<CablistModel> cablistModelList) {
        this.cablistModelList=cablistModelList;
        this.date=date;


    }
    public Cablist_Adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View    view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_cablist, parent, false);


        context = parent.getContext();
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final Cablist_Adapter.ViewHolder holder, final int position) {
        holder.cabtype.setText(cablistModelList.get(position).getCabtype());
        holder.time.setText(cablistModelList.get(position).getStarttime()+" - "+cablistModelList.get(position).getEndtime());
        holder.price.setText("₹ "+cablistModelList.get(position).getSeatprice()+".00");
        holder.cabcolor.setText(cablistModelList.get(position).getCabcolor());
        holder.cabmodel.setText(cablistModelList.get(position).getCabmodel());
        holder.cabno.setText(cablistModelList.get(position).getCabno());
//        final String cabstate=cablistModelList.get(position).getState();
//        if(cabstate.equals("0"))
//        {  holder.state.setCompoundDrawablesRelativeWithIntrinsicBounds(R.drawable.item_round1, 0, 0, 0);
//          //  Toast.makeText(context, ""+cabstate, Toast.LENGTH_SHORT).show();
//            holder.state.setText("Offline");
//        }
//       else
//        { //Toast.makeText(context, ""+cabstate, Toast.LENGTH_SHORT).show();
//
//          //  holder.state.setCompoundDrawablesRelativeWithIntrinsicBounds(R.drawable.item_round, 0, 0, 0);
//            holder.state.setText("Online");}

    holder.cablist.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent=new Intent(context,BookSeat_Activity.class);
            intent.putExtra("from",cablistModelList.get(position).getFrom());
            intent.putExtra("to",cablistModelList.get(position).getTo());
            intent.putExtra("cablistid",cablistModelList.get(position).Cablistid);
            intent.putExtra("starttime",cablistModelList.get(position).getStarttime());
            intent.putExtra("endtime",cablistModelList.get(position).getEndtime());
            intent.putExtra("price",cablistModelList.get(position).getSeatprice());
            intent.putExtra("cabtype",cablistModelList.get(position).getCabtype());

            intent.putExtra("cabcolor",cablistModelList.get(position).getCabcolor());
            intent.putExtra("cabno",cablistModelList.get(position).getCabno());
            intent.putExtra("cabmodel",cablistModelList.get(position).getCabmodel());
            context.startActivity(intent);
        }
    });

    }

    @Override
    public int getItemCount() {
        return cablistModelList.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView time,cabtype,price,duration,state,cabmodel,cabno,cabcolor;
        View mView;
        FrameLayout selectcat;
        CardView cablist;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            time=view.findViewById(R.id.cabtime);
            cabtype=mView.findViewById(R.id.cabtype);
            price=mView.findViewById(R.id.cabprice);


            cablist=mView.findViewById(R.id.cablist);
            cabcolor=mView.findViewById(R.id.cabcolor);
            cabno=mView.findViewById(R.id.cabno);
            cabmodel=mView.findViewById(R.id.carmodel);

        }

    }
}
