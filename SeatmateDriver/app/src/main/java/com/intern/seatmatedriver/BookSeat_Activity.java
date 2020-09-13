package com.intern.seatmatedriver;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.content.ContextCompat;
import dmax.dialog.SpotsDialog;
import uk.co.chrisjenx.calligraphy.CalligraphyConfig;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class BookSeat_Activity extends AppCompatActivity {

    ImageView a1, a2, b2, c2, a3, b3, c3;
    String sa1 = "avi", sb1 = "avi", sa2 = "avi", sb2 = "avi", sc2 = "avi", sa3 = "avi", sc3 = "avi";
    String cablistid;
    TextView from, to, date, price, time, cabtype;
    FirebaseAuth firebaseAuth;
    ConstraintLayout root;
    FirebaseFirestore firebaseFirestore;
    boolean booked = false;
    String bdate;
    String driverid,tofrom;
    StorageReference storagerefrence;
    Map<String, Object> mybookings;
    boolean isBooked=false;

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_bookseat_);
        root=findViewById(R.id.root);

        date = findViewById(R.id.date);
        from = findViewById(R.id.from);
        to = findViewById(R.id.to);

        time = findViewById(R.id.time);
        price = findViewById(R.id.price);
        cabtype = findViewById(R.id.cabtype);
        firebaseAuth = FirebaseAuth.getInstance();
        firebaseFirestore = FirebaseFirestore.getInstance();
        storagerefrence= FirebaseStorage.getInstance().getReference();

        a1 = findViewById(R.id.a1);

        a2 = findViewById(R.id.a2);
        b2 = findViewById(R.id.b2);
        c2 = findViewById(R.id.c2);

        a3 = findViewById(R.id.a3);
        b3 = findViewById(R.id.b3);
        c3 = findViewById(R.id.c3);

        getSupportActionBar().hide();


        a1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                firebaseFirestore.collection("Cabs").document(cablistid).collection(tofrom).document(bdate).get().
                        addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                            @Override
                            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                                if (!TextUtils.isEmpty(task.getResult().getString("a1")))
                                {
                                    aleartdilog(a1, task.getResult().getString("a1"));
                                }


                            }
                        });


            }
        });



        a2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                firebaseFirestore.collection("Cabs").document(cablistid).collection(tofrom).document(bdate).get().
                        addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                            @Override
                            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                                if (!TextUtils.isEmpty(task.getResult().getString("a2")))
                                {
                                    aleartdilog(a2, task.getResult().getString("a2"));
                                }

                            }
                        });

            }
        });



        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                firebaseFirestore.collection("Cabs").document(cablistid).collection(tofrom).document(bdate).get().
                        addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                            @Override
                            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                                if (!TextUtils.isEmpty(task.getResult().getString("b2")))
                                {
                                    aleartdilog(b2, task.getResult().getString("b2"));
                                }


                            }
                        });

            }

        });

        c2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {



                firebaseFirestore.collection("Cabs").document(cablistid).collection(tofrom).document(bdate).get().
                        addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                            @Override
                            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                                if (!TextUtils.isEmpty(task.getResult().getString("c2")))
                                {
                                    aleartdilog(c2, task.getResult().getString("c2"));
                                }

                            }
                        });

            }
        });



        a3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                firebaseFirestore.collection("Cabs").document(cablistid).collection(tofrom).document(bdate).get().
                        addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                            @Override
                            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                                if (!TextUtils.isEmpty(task.getResult().getString("a3")))
                                {
                                    aleartdilog(a3, task.getResult().getString("a3"));
                                }


                            }
                        });

            }
        });



        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                firebaseFirestore.collection("Cabs").document(cablistid).collection(tofrom).document(bdate).get().
                        addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                            @Override
                            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                                if (!TextUtils.isEmpty(task.getResult().getString("b3")))
                                {
                                    aleartdilog(b3, task.getResult().getString("b3"));
                                }


                            }
                        });

            }
        });


        c3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                firebaseFirestore.collection("Cabs").document(cablistid).collection(tofrom).document(bdate).get().
                        addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                            @Override
                            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                                if (!TextUtils.isEmpty(task.getResult().getString("c3")))
                                {
                                    aleartdilog(c3, task.getResult().getString("c3"));
                                }



                            }
                        });

            }
        });


    }



    public void aleartdilog(final ImageView x, final String y) {
        final ProgressDialog progressDialog=new ProgressDialog(BookSeat_Activity.this);

        progressDialog.setTitle("Please Wait!");
        progressDialog.setMessage("Fetching Booking Profile..");
        progressDialog.show();

        final AlertDialog.Builder dialog= new AlertDialog.Builder(BookSeat_Activity.this);



        LayoutInflater inflater = LayoutInflater.from(BookSeat_Activity.this);
        View quantity_layout = inflater.inflate(R.layout.layout_uploadpdf ,null);


        dialog.setView(quantity_layout);




        final ImageView  imageView1   =quantity_layout.findViewById(R.id.new_post_image);
        final EditText name=quantity_layout.findViewById(R.id.name);


        firebaseFirestore.collection("Users").document(y).get().
                addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {

                        String source1= task.getResult().getString("imageurl");
                        name.setText(task.getResult().getString("name"));
                        Glide.with(BookSeat_Activity.this).load(source1).into(imageView1);
                        progressDialog.dismiss();

                    }
                });
        dialog.show();
    }

    @Override
    protected void onStart() {
        super.onStart();
        SpotsDialog spotsDialog=new SpotsDialog(BookSeat_Activity.this);
        spotsDialog.show();
        to.setText(getIntent().getStringExtra("to"));
        from.setText(getIntent().getStringExtra("from"));
        time.setText(getIntent().getStringExtra("starttime") + " - " + getIntent().getStringExtra("endtime"));
        price.setText("₹ " + getIntent().getStringExtra("price") + ".00");
        cabtype.setText(getIntent().getStringExtra("cabtype"));
        date.setText(getIntent().getStringExtra("date"));
        cablistid = getIntent().getStringExtra("cablistid");

        tofrom=getIntent().getStringExtra("from")+getIntent().getStringExtra("to");

        SharedPreferences prefs = BookSeat_Activity.this.getSharedPreferences("date", Context.MODE_PRIVATE);

        if (prefs.getString("date", null) != null) {
            date.setText(prefs.getString("date", null));
            bdate=prefs.getString("date", null);

        }




        firebaseFirestore.collection("Cabs").document(cablistid).collection(tofrom).document(bdate).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {


                if(!TextUtils.isEmpty(task.getResult().getString("a1")))
                {

                    a1.setBackgroundResource(R.drawable.booked_img);


                }
                if(!TextUtils.isEmpty(task.getResult().getString("a2")))
                {

                    a2.setBackgroundResource(R.drawable.booked_img);


                }
                if(!TextUtils.isEmpty(task.getResult().getString("a3")))
                {

                    a3.setBackgroundResource(R.drawable.booked_img);


                }
                if(!TextUtils.isEmpty(task.getResult().getString("b2")))
                {

                    b2.setBackgroundResource(R.drawable.booked_img);


                }
                if(!TextUtils.isEmpty(task.getResult().getString("b3")))
                {

                    b3.setBackgroundResource(R.drawable.booked_img);


                }
                if(!TextUtils.isEmpty(task.getResult().getString("c2")))
                {

                    c2.setBackgroundResource(R.drawable.booked_img);


                }
                if(!TextUtils.isEmpty(task.getResult().getString("c3")))
                {

                    c3.setBackgroundResource(R.drawable.booked_img);


                }


            }
        });




        firebaseFirestore.collection("Details").addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot queryDocumentSnapshots, @Nullable FirebaseFirestoreException e) {

                for (DocumentChange doc : queryDocumentSnapshots.getDocumentChanges()) {
                    if (doc.getType() == DocumentChange.Type.ADDED) {

                        if(doc.getDocument().getString("phoneno").equalsIgnoreCase(firebaseAuth.getCurrentUser().getPhoneNumber()))
                        {
                            b2.setVisibility(View.VISIBLE);
                            b3.setVisibility(View.VISIBLE);
                            b2.setEnabled(true);
                            b3.setEnabled(true);
                        }
                    }
                }
            }
        });


        if(getIntent().getStringExtra("cabtype").equalsIgnoreCase("Sports Utility Vehicle(1+5)"))
        {
            a3.setVisibility(View.VISIBLE);
            a3.setEnabled(true);

            c3.setVisibility(View.VISIBLE);
            c3.setEnabled(true);


        }
        spotsDialog.dismiss();


    }



}
