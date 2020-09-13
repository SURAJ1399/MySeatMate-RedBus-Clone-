package com.intern.elitecabs;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

public class MyBookingAdapter extends  RecyclerView.Adapter<MyBookingAdapter.ViewHolder>  {



    @NonNull

    Context context;
    public List<MyBookingsModel> cablistModelList;
    String date;

    public MyBookingAdapter(List<MyBookingsModel> cablistModelList) {
        this.cablistModelList=cablistModelList;



    }
    public MyBookingAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_mybookings, parent, false);


        context = parent.getContext();
        return new MyBookingAdapter.ViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull final MyBookingAdapter.ViewHolder holder, final int position) {


        holder.tofrom.setText(cablistModelList.get(position).getTofrom());
        holder.cabtype.setText(cablistModelList.get(position).getCabtype());
        holder.seatprice.setText(cablistModelList.get(position).getSeatprice());
        holder.startime.setText(cablistModelList.get(position).getStarttime());
        holder.date.setText(cablistModelList.get(position).getDate());
        holder.seatno.setText("Seat No- "+cablistModelList.get(position).getSeatno());
        holder.cabcolor.setText(cablistModelList.get(position).getCabcolor());
        holder.cabmodel.setText(cablistModelList.get(position).getCabmodel());
        holder.cabno.setText(cablistModelList.get(position).getCabno());


//        holder.cablist.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent=new Intent(context,BookSeat_Activity.class);
//                intent.putExtra("from",cablistModelList.get(position).getFrom());
//                intent.putExtra("to",cablistModelList.get(position).getTo());
//                intent.putExtra("cablistid",cablistModelList.get(position).Cablistid);
//                intent.putExtra("starttime",cablistModelList.get(position).getStarttime());
//                intent.putExtra("endtime",cablistModelList.get(position).getEndtime());
//                intent.putExtra("price",cablistModelList.get(position).getSeatprice());
//                intent.putExtra("cabtype",cablistModelList.get(position).getCabtype());
//                context.startActivity(intent);
//            }
//        });

    }

    @Override
    public int getItemCount() {
        return cablistModelList.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView cabtype,date,seatno,seatprice,startime,tofrom,cabmodel,cabno,cabcolor;
        View mView;
        FrameLayout selectcat;
        CardView cablist;


        public ViewHolder(View view) {
            super(view);
            mView = view;
            cabtype=mView.findViewById(R.id.cabtype);
            date=mView.findViewById(R.id.date);
            seatno=mView.findViewById(R.id.seatno);
            seatprice=mView.findViewById(R.id.seatprice);
            startime=mView.findViewById(R.id.starttime);
            tofrom=mView.findViewById(R.id.tofrom);
            cabcolor=mView.findViewById(R.id.cabcolor);
            cabno=mView.findViewById(R.id.cabno);
            cabmodel=mView.findViewById(R.id.carmodel);




        }
    }
}


