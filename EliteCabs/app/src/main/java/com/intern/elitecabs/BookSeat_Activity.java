package com.intern.elitecabs;

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
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

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
        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder().setDefaultFontPath("fonts/gotham.ttf").setFontAttrId(R.attr.fontPath).build());

        setContentView(R.layout.activity_book_seat_);
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
                                    snackbarofbookedseat();
                                }
                                else {
                                    aleartdilog(a1, "a1");
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
                                    snackbarofbookedseat();
                                }
                                else {
                                    aleartdilog(a2, "a2");
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
                                    snackbarofbookedseat();
                                }
                                else {
                                    aleartdilog(b2, "b2");
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
                                    snackbarofbookedseat();
                                }
                                else {
                                    aleartdilog(c2, "c2");
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
                                    snackbarofbookedseat();
                                }
                                else {
                                    aleartdilog(a3, "a3");
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
                                    snackbarofbookedseat();
                                }
                                else {
                                    aleartdilog(b3, "b3");
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
                                                          snackbarofbookedseat();
                                                      }
                                                      else {
                                                          aleartdilog(c3, "c3");
                                                      }

                                                  }
                                              });

            }
        });


    }

    public void resetseat() {
        a1.setBackgroundResource(R.drawable.available_img);
        a2.setBackgroundResource(R.drawable.available_img);
        b2.setBackgroundResource(R.drawable.available_img);
        c2.setBackgroundResource(R.drawable.available_img);
        a3.setBackgroundResource(R.drawable.available_img);
        b3.setBackgroundResource(R.drawable.available_img);
        c3.setBackgroundResource(R.drawable.available_img);


    }

    public void aleartdilog(final ImageView x, final String y) {





        Snackbar snackbar = Snackbar
                .make(root, "Do You Want To Book This Seat ?", Snackbar.LENGTH_LONG)
                .setAction("Confirm", new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {



                        Snackbar snackbar1 = Snackbar.make(root, "Seat Has Been Booked!", Snackbar.LENGTH_SHORT);
                        View sbView = snackbar1.getView();
                        sbView.setBackgroundColor(ContextCompat.getColor(BookSeat_Activity.this, R.color.colorPrimary));

                        snackbar1.show();

                        x.setBackgroundResource(R.drawable.your_seat_img);

                        a1.setEnabled(false);
                        a2.setEnabled(false);
                        b2.setEnabled(false);
                        c2.setEnabled(false);
                        a3.setEnabled(false);
                        b3.setEnabled(false);
                        c3.setEnabled(false);
                        bookseat(y);




                    }
                });

        View sbView = snackbar.getView();
        sbView.setBackgroundColor(ContextCompat.getColor(BookSeat_Activity.this, R.color.colorPrimary));
        snackbar.show();

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
       // driverid=getIntent().getStringExtra("driverid");
        tofrom=getIntent().getStringExtra("from")+getIntent().getStringExtra("to");

        mybookings  = new HashMap<>();
        mybookings.put("tofrom",getIntent().getStringExtra("from") +" -> "+getIntent().getStringExtra("to"));
        mybookings.put("seatprice","₹ " + getIntent().getStringExtra("price") + ".00");
        mybookings.put("starttime",getIntent().getStringExtra("starttime") + " - " + getIntent().getStringExtra("endtime"));
        mybookings.put("cabtype",getIntent().getStringExtra("cabtype"));



        final SharedPreferences prefs = BookSeat_Activity.this.getSharedPreferences("date", Context.MODE_PRIVATE);

        if (prefs.getString("date", null) != null) {
            date.setText(prefs.getString("date", null));
            bdate=prefs.getString("date", null);
            mybookings.put("date",prefs.getString("date", null));
        }





        firebaseFirestore.collection("Cabs").document(cablistid).collection(tofrom).document(bdate).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if(!TextUtils.isEmpty(task.getResult().getString("a1")))
                {
                    if(task.getResult().getString("a1").equalsIgnoreCase(firebaseAuth.getCurrentUser().getUid()))
                    {
                        a1.setBackgroundResource(R.drawable.your_seat_img);
                    }
                    else
                        a1.setBackgroundResource(R.drawable.booked_img);


                }
                if(!TextUtils.isEmpty(task.getResult().getString("a2")))
                {
                    if(task.getResult().getString("a2").equalsIgnoreCase(firebaseAuth.getCurrentUser().getUid()))
                    {
                        a2.setBackgroundResource(R.drawable.your_seat_img);
                    }
                    else
                        a2.setBackgroundResource(R.drawable.booked_img);


                }
                if(!TextUtils.isEmpty(task.getResult().getString("a3")))
                {
                    if(task.getResult().getString("a3").equalsIgnoreCase(firebaseAuth.getCurrentUser().getUid()))
                    {
                        a3.setBackgroundResource(R.drawable.your_seat_img);
                    }
                    else
                        a3.setBackgroundResource(R.drawable.booked_img);


                }
                if(!TextUtils.isEmpty(task.getResult().getString("b2")))
                {
                    if(task.getResult().getString("b2").equalsIgnoreCase(firebaseAuth.getCurrentUser().getUid()))
                    {
                        b2.setBackgroundResource(R.drawable.your_seat_img);
                    }
                    else
                        b2.setBackgroundResource(R.drawable.booked_img);


                }
                if(!TextUtils.isEmpty(task.getResult().getString("b3")))
                {
                    if(task.getResult().getString("b3").equalsIgnoreCase(firebaseAuth.getCurrentUser().getUid()))
                    {
                        b3.setBackgroundResource(R.drawable.your_seat_img);
                    }
                    else
                        b3.setBackgroundResource(R.drawable.booked_img);


                }
                if(!TextUtils.isEmpty(task.getResult().getString("c2")))
                {
                    if(task.getResult().getString("c2").equalsIgnoreCase(firebaseAuth.getCurrentUser().getUid()))
                    {
                        c2.setBackgroundResource(R.drawable.your_seat_img);
                    }
                    else
                        c2.setBackgroundResource(R.drawable.booked_img);


                }
                if(!TextUtils.isEmpty(task.getResult().getString("c3")))
                {
                    if(task.getResult().getString("c3").equalsIgnoreCase(firebaseAuth.getCurrentUser().getUid()))
                    {
                        c3.setBackgroundResource(R.drawable.your_seat_img);
                    }
                    else
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

    public void bookseat(final String y) {
        final SpotsDialog spotsDialog =new SpotsDialog(BookSeat_Activity.this);
        spotsDialog.show();
        firebaseFirestore.collection("Cabs").document(cablistid).collection(tofrom)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            if(task.getResult().size() > 0) {
                                for (DocumentSnapshot document : task.getResult()) {

                                    FirebaseFirestore rootRef = FirebaseFirestore.getInstance();
                                    final DocumentReference docIdRef = rootRef.collection("Cabs").document(cablistid).collection(tofrom).document(bdate);
                                    docIdRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                                        @Override
                                        public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                                            if (task.isSuccessful()) {
                                                DocumentSnapshot document = task.getResult();
                                                if (document.exists()) {

                                                    DocumentReference documentReference = firebaseFirestore.collection("Cabs").document(cablistid).collection(tofrom).document(bdate);
                                                    documentReference.update(y, firebaseAuth.getCurrentUser().getUid());




                                                }
                                                else {
                                                    Map<String, Object> seatmap = new HashMap<>();
                                                    seatmap.put("a1","");
                                                    seatmap.put("a2","");
                                                    seatmap.put("b2","");
                                                    seatmap.put("c2","");
                                                    seatmap.put("a3","");
                                                    seatmap.put("b3","");
                                                    seatmap.put("c3","");

                                                   //oast.makeText(BookSeat_Activity.this, "date"+bdate+"tofrom"+tofrom, Toast.LENGTH_SHORT).show();
                                                    firebaseFirestore.collection("Cabs").document(cablistid).collection(tofrom).document(bdate).set(seatmap).addOnCompleteListener(new OnCompleteListener<Void>() {
                                                        @Override
                                                        public void onComplete(@NonNull Task<Void> task) {
                                                            DocumentReference documentReference = firebaseFirestore.collection("Cabs").document(cablistid).collection(tofrom).document(bdate);
                                                            documentReference.update(y, firebaseAuth.getCurrentUser().getUid());
                                          }
                                                    });
                                                }
                                            }
                                        }
                                    });


                                }
                            } else {
                               //oast.makeText(BookSeat_Activity.this, "Doesn't exit", Toast.LENGTH_SHORT).show();

                                Map<String, Object> seatmap = new HashMap<>();
                                seatmap.put("a1","");
                                seatmap.put("a2","");
                                seatmap.put("b2","");
                                seatmap.put("c2","");
                                seatmap.put("a3","");
                                seatmap.put("b3","");
                                seatmap.put("c3","");

                               //oast.makeText(BookSeat_Activity.this, "date"+bdate+"tofrom"+tofrom, Toast.LENGTH_SHORT).show();
                                firebaseFirestore.collection("Cabs").document(cablistid).collection(tofrom).document(bdate).set(seatmap).addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                     DocumentReference documentReference = firebaseFirestore.collection("Cabs").document(cablistid).collection(tofrom).document(bdate);
                                        documentReference.update(y, firebaseAuth.getCurrentUser().getUid());
                                    isBooked =true;


                                    }
                                });
                            }
                        } else {

                        }
                    }
                });
mybookings.put("timestamp",FieldValue.serverTimestamp());
        mybookings.put("cabno",getIntent().getStringExtra("cabno"));
        mybookings.put("cabcolor",getIntent().getStringExtra("cabcolor"));
        mybookings.put("cabmodel",getIntent().getStringExtra("cabmodel"));
        firebaseFirestore.collection("Users/"+firebaseAuth.getCurrentUser().getUid()+"/MyBookings").add(mybookings).addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
            @Override
            public void onComplete(@NonNull Task<DocumentReference> task) {


                Intent intent=new Intent(BookSeat_Activity.this,HomeActivity.class);
                startActivity(intent);
                finish();
                spotsDialog.dismiss();

            }
        });





    }
            public  void snackbarofbookedseat()
            {
                Snackbar snackbar = Snackbar
                        .make(root, "Sorry ! This Seat is Already Booked.", Snackbar.LENGTH_LONG);
                View sbView = snackbar.getView();
                sbView.setBackgroundColor(ContextCompat.getColor(BookSeat_Activity.this, R.color.colorPrimary));
                snackbar.show();

            }


}
