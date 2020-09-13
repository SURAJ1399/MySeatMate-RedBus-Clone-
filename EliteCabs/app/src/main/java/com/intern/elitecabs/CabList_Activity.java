package com.intern.elitecabs;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class CabList_Activity extends AppCompatActivity {
TextView date1,from,to;
FirebaseFirestore firebaseFirestore;
FirebaseAuth firebaseAuth;
    public List<CablistModel> cablistModelList;
    Cablist_Adapter cablist_adapter;
    String source,dest,datez,datey;
    RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cab_list_);
        getSupportActionBar().hide();
        CardView cardView=findViewById(R.id.cardView);

        cablistModelList=new ArrayList<>();
from=findViewById(R.id.from);
to=findViewById(R.id.to);
        date1=findViewById(R.id.date);
        firebaseFirestore=FirebaseFirestore.getInstance();
        firebaseAuth=FirebaseAuth.getInstance();
    recyclerView  = findViewById(R.id.cablist_recycler);

        LinearLayoutManager layoutManager = new GridLayoutManager(CabList_Activity.this,1);


        //RecyclerView.LayoutManager layoutManager;
        recyclerView.setLayoutManager(layoutManager);

        //Passing image array and assigning adapter is object of recycler_adapter class.

        cablist_adapter= new Cablist_Adapter(cablistModelList);
        recyclerView.setAdapter(cablist_adapter);






    }

    @Override
    protected void onStart() {
        super.onStart();
final ProgressDialog progressDialog=new ProgressDialog(CabList_Activity.this);
progressDialog.setTitle("Please Wait !");
progressDialog.setMessage("Finding Your Ride..");
progressDialog.show();
        datez=getIntent().getStringExtra("date");
       source=getIntent().getStringExtra("source");
       dest=getIntent().getStringExtra("destination");
       from.setText(source);
       to.setText(dest);
     
        date1.setText(datez);
        SharedPreferences.Editor editor = CabList_Activity.this.getSharedPreferences("date", Context.MODE_PRIVATE).edit();
        editor.putString("date",datez );
        editor.apply();

        if (FirebaseAuth.getInstance().getCurrentUser() != null) {

            cablistModelList.clear();
            firebaseFirestore.collection("Cabs").addSnapshotListener(new EventListener<QuerySnapshot>() {
                @Override
                public void onEvent(@Nullable QuerySnapshot queryDocumentSnapshots, @Nullable FirebaseFirestoreException e) {

                    for (DocumentChange doc : queryDocumentSnapshots.getDocumentChanges()) {
                        if (doc.getType() == DocumentChange.Type.ADDED) {
                            String categoryid = doc.getDocument().getId();
                            CablistModel allcabsmodel = doc.getDocument().toObject(CablistModel.class).withId(categoryid);

if(doc.getDocument().getString("state").equalsIgnoreCase("1"))
{
    if(source.equalsIgnoreCase(doc.getDocument().getString("from") )&& dest.equalsIgnoreCase(doc.getDocument().getString("to")))
    {
        cablistModelList.add(allcabsmodel);
        cablist_adapter.notifyDataSetChanged();


    }

}


                        }


                    }
                    progressDialog.dismiss();
                    if(cablistModelList.size()==0)
                    {
                        Intent intent =new Intent(CabList_Activity.this,HomeActivity.class);
                        startActivity(intent);
progressDialog.dismiss();
                        finish();
                        Toast.makeText(CabList_Activity.this, "Sorry ! No Rides Found", Toast.LENGTH_SHORT).show();

                    }
                }
            });
        }
    }
}
