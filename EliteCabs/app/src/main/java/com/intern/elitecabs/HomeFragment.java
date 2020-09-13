package com.intern.elitecabs;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import dmax.dialog.SpotsDialog;

import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;
import com.shrikanthravi.collapsiblecalendarview.data.Day;
import com.shrikanthravi.collapsiblecalendarview.widget.CollapsibleCalendar;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class HomeFragment extends Fragment implements AdapterView.OnItemClickListener {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    TextInputLayout selectdate;
    EditText date1;
    Button searchcabs;
    FirebaseFirestore firebaseFirestore;
    FirebaseAuth firebaseAuth;
    AutoCompleteTextView destination,from1;
    ArrayList<String> to = new ArrayList<>();
    ArrayList<String> from = new ArrayList<>();
View view;

    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_home, container, false);


        searchcabs=view.findViewById(R.id.search_cabs);
        selectdate=view.findViewById(R.id.select_date);
        searchcabs=view.findViewById(R.id.search_cabs);

        from1=view.findViewById(R.id.from1);
        firebaseFirestore=FirebaseFirestore.getInstance();
        firebaseAuth=FirebaseAuth.getInstance();

        firebaseFirestore.collection("Routes");


        destination=view.findViewById(R.id.destination);
        //tvDisplay = (TextView) findViewById(R.id.tvDisplay);



        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(),android.R.layout.simple_list_item_1, to);
        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(getContext(),android.R.layout.simple_list_item_1, from);

        destination.setAdapter(adapter);
        destination.setOnItemClickListener(this);

        from1.setAdapter(adapter2);
        from1.setOnItemClickListener(this);
        date1=view.findViewById(R.id.date);
        date1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final AlertDialog.Builder alertDialog = new AlertDialog.Builder(getActivity());
                // alertDialog.setTitle("ADD ROOM");
                //   alertDialog.setMessage(" PLZ ADD Room details");

                LayoutInflater inflater = LayoutInflater.from(getActivity());
                final View date_layout = inflater.inflate(R.layout.layout_date, null);

                alertDialog.setView(date_layout);


                final CollapsibleCalendar collapsibleCalendar = date_layout.findViewById(R.id.calendarView);
                collapsibleCalendar.setCalendarListener(new CollapsibleCalendar.CalendarListener() {
                    @Override
                    public void onDaySelect() {
                        Day day = collapsibleCalendar.getSelectedDay();
                        Log.i(getClass().getName(), "Selected Day: "
                                + day.getYear() + "/" + (day.getMonth() + 1) + "/" + day.getDay());
                        ///   Toast.makeText(addmatch.this, , Toast.LENGTH_SHORT).show();d
                        String date=day.getDay()+" - "+(day.getMonth()+1)+" - "+day.getYear();
                        date1.setText(date);


                        // Toast.makeText(getContext(), "You Selected "+date, Toast.LENGTH_SHORT).show();

                    }

                    @Override
                    public void onItemClick(View v) {


                    }

                    @Override
                    public void onDataUpdate() {

                    }

                    @Override
                    public void onMonthChange() {

                    }

                    @Override
                    public void onWeekChange(int position) {

                    }


                });


                alertDialog.show();




            }
        });
        searchcabs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!TextUtils.isEmpty(destination.getText().toString() )&&!TextUtils.isEmpty(from1.getText().toString() )&&!TextUtils.isEmpty(date1.toString()))
                {


                    if(from.contains(from1.getText().toString()) && to.contains(destination.getText().toString() ))
                    {



                        Intent intent=new Intent(getActivity(),CabList_Activity.class);
                        intent.putExtra("date",date1.getText().toString().trim());
                        intent.putExtra("source",from1.getText().toString());
                        intent.putExtra("destination",destination.getText().toString());
                        startActivity(intent);
                    }
                    else {
                        Toast.makeText(getActivity(), "No Cabs Available For Selected Routes", Toast.LENGTH_SHORT).show();
                    }
                }
                else Toast.makeText(getActivity(),"Enter Required Fields", Toast.LENGTH_SHORT).show();

            }
        });

        return view;
    }


    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

    }

    @Override
    public void onStart() {
        super.onStart();
        final SpotsDialog spotsDialog=new SpotsDialog(getContext());
        spotsDialog.show();
        if (FirebaseAuth.getInstance().getCurrentUser() != null) {
            //blogRecyclerAdapter.notifyDataSetChanged();

            // Query firstquery = firebaseFirestore.collection(Users/"+firebaseAuth.getCurrentUser().getUid()+"/PostedJobs").orderBy("date", Query.Direction.DESCENDING);
            firebaseFirestore.collection("Routes").addSnapshotListener(new EventListener<QuerySnapshot>() {
                @Override
                public void onEvent(@Nullable QuerySnapshot queryDocumentSnapshots, @Nullable FirebaseFirestoreException e) {

                    for (DocumentChange doc : queryDocumentSnapshots.getDocumentChanges()) {
                        if (doc.getType() == DocumentChange.Type.ADDED) {
                            // String to=;
                            to.add(doc.getDocument().getString("to"));
                            from.add(doc.getDocument().getString("from"));

                        }
                    }
                    spotsDialog.dismiss();
                }
            });
        }


    }
}



