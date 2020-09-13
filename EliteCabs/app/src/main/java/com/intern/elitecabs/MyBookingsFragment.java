package com.intern.elitecabs;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;


public class MyBookingsFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    FirebaseFirestore firebaseFirestore;
    FirebaseAuth firebaseAuth;
    public List<MyBookingsModel> cablistModelList;
 MyBookingAdapter myBookingAdapter;
    RecyclerView recyclerView;
    View view;

    public MyBookingsFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_my_bookings, container, false);
        CardView cardView = view.findViewById(R.id.cardView);
      ///  ((AppCompatActivity)getActivity()).getSupportActionBar().hide();

        cablistModelList = new ArrayList<>();

        firebaseFirestore = FirebaseFirestore.getInstance();
        firebaseAuth = FirebaseAuth.getInstance();
        recyclerView = view.findViewById(R.id.mybookingrecycler);

        LinearLayoutManager layoutManager = new GridLayoutManager(getActivity(), 1);



        recyclerView.setLayoutManager(layoutManager);



         myBookingAdapter = new MyBookingAdapter(cablistModelList);
         recyclerView.setAdapter( myBookingAdapter);
         return  view;


    }



    @Override
    public void onStart() {
        super.onStart();
        final ProgressDialog progressDialog=new ProgressDialog(getContext());
        progressDialog.setTitle("Please Wait !");
        progressDialog.setMessage("Getting Your Bookings...");
        progressDialog.show();

        if (FirebaseAuth.getInstance().getCurrentUser() != null) {

            cablistModelList.clear();



            Query firstquery = firebaseFirestore.collection("Users/"+firebaseAuth.getCurrentUser().getUid()+"/MyBookings").orderBy("timestamp", Query.Direction.DESCENDING);
            firstquery.addSnapshotListener(new EventListener<QuerySnapshot>() {
                @Override
                public void onEvent(@javax.annotation.Nullable QuerySnapshot queryDocumentSnapshots, @javax.annotation.Nullable FirebaseFirestoreException e) {

                    for (DocumentChange doc : queryDocumentSnapshots.getDocumentChanges()) {
                        if (doc.getType() == DocumentChange.Type.ADDED) {

                            {
                                MyBookingsModel allcabsmodel = doc.getDocument().toObject(MyBookingsModel.class);
                                cablistModelList.add(allcabsmodel);

                                progressDialog.dismiss();

                                myBookingAdapter.notifyDataSetChanged();
                            }





                        }

                    }
                }
            });





        }
        progressDialog.dismiss();

    }

}
