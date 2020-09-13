package com.intern.elitecabs;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import android.text.TextUtils;
import android.text.style.IconMarginSpan;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import androidx.drawerlayout.widget.DrawerLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import dmax.dialog.SpotsDialog;

import android.view.Menu;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class HomeActivity extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;
    Uri postimageuri;
    String downloadurl;
    ImageView imageView1;
    FirebaseStorage firebaseStorage;
    FirebaseFirestore firebaseFirestore;
    FirebaseAuth firebaseAuth;
    StorageReference storageReference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        //   ImageView memn=toolbar.findViewById(R.id.menu);
        storageReference= FirebaseStorage.getInstance().getReference();
        firebaseFirestore=FirebaseFirestore.getInstance();
        firebaseAuth=FirebaseAuth.getInstance();


        final DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        final HomeFragment homeFragment = new HomeFragment();
        final MyBookingsFragment myBookingsFragment = new MyBookingsFragment();

        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close) {

            @Override
            public void onDrawerClosed(View drawerView) {
                // Code here will be triggered once the drawer closes as we dont want anything to happen so we leave this blank
                super.onDrawerClosed(drawerView);
            }

            @Override
            public void onDrawerOpened(View drawerView) {
                // Code here will be triggered once the drawer open as we dont want anything to happen so we leave this blank
                super.onDrawerOpened(drawerView);
            }
        };
        actionBarDrawerToggle.getDrawerArrowDrawable().setColor(Color.WHITE);
        //  actionBarDrawerToggle.getDrawerArrowDrawable().setColor(getResources().getColor(R.color.colorAccent));


        //Setting the actionbarToggle to drawer layout
        drawer.setDrawerListener(actionBarDrawerToggle);


        //calling sync state is necessary or else your hamburger icon wont show up
        actionBarDrawerToggle.syncState();

        // menu should be considered as top level destinations

        final FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out);
        fragmentTransaction.replace(R.id.discover_fragment, homeFragment);
        fragmentTransaction.commit();

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {

            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {
                FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                fragmentTransaction.setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out);

                switch (menuItem.getItemId()) {


                    case R.id.nav_home:
                        final FragmentTransaction fragmentTransaction2 = getSupportFragmentManager().beginTransaction();
                        fragmentTransaction2.setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out);
                        fragmentTransaction2.replace(R.id.discover_fragment, homeFragment);
                        fragmentTransaction2.commit();
                        break;
                    case R.id.nav_mybookings:
                        final FragmentTransaction fragmentTransaction3 = getSupportFragmentManager().beginTransaction();
                        fragmentTransaction3.setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out);
                        fragmentTransaction3.replace(R.id.discover_fragment, myBookingsFragment);
                        fragmentTransaction3.commit();
                        break;
                    case R.id.nav_logout:

                        firebaseAuth.signOut();
                        Intent intent = new Intent(HomeActivity.this, LoginActivity.class);
                        startActivity(intent);
                        finish();
                        break;
                    case  R.id.nav_share:
                        Intent sendIntent = new Intent();
                        sendIntent.setAction(Intent.ACTION_SEND);
                        sendIntent.putExtra(Intent.EXTRA_TEXT, "Book Your Seat Now @My Seat Mate"+"\n"+"Download The App"+"\n"+"https://play.google.com/store/apps/details?id=com.my1app.mycontacts&hl=en_IN");
                        sendIntent.setType("text/plain");
                        sendIntent.setPackage("com.whatsapp");
                        if (sendIntent.resolveActivity(getPackageManager()) != null) {
                            startActivity(sendIntent);
                        }
                        break;
                        
                    case R.id.nav_profile:

                        final AlertDialog.Builder dialog= new AlertDialog.Builder(HomeActivity.this);



                        LayoutInflater inflater = LayoutInflater.from(HomeActivity.this);
                        View quantity_layout = inflater.inflate(R.layout.layout_uploadpdf ,null);


                        dialog.setView(quantity_layout);




                        imageView1   =quantity_layout.findViewById(R.id.new_post_image);
                        final EditText name=quantity_layout.findViewById(R.id.name);
                        final ProgressDialog progressDialog=new ProgressDialog(HomeActivity.this);
                        progressDialog.setTitle("Please Wait!");
                        progressDialog.setMessage("Fetching Your Profile..");
                        firebaseFirestore.collection("Users").addSnapshotListener(HomeActivity.this,new EventListener<QuerySnapshot>() {
                            @Override
                            public void onEvent(@javax.annotation.Nullable QuerySnapshot queryDocumentSnapshots, @javax.annotation.Nullable FirebaseFirestoreException e) {
                                if (!queryDocumentSnapshots.isEmpty()) {
                                    for (DocumentChange doc : queryDocumentSnapshots.getDocumentChanges()) {
                                        if (doc.getType() == DocumentChange.Type.ADDED) {
progressDialog.show();
                                            String source1= doc.getDocument().getString("imageurl");
                                            name.setText(doc.getDocument().getString("name"));
                                            Glide.with(HomeActivity.this).load(source1).into(imageView1);
                                            progressDialog.dismiss();

                                          //  Toast.makeText(HomeActivity.this, ""+doc.getDocument().getString("name"), Toast.LENGTH_SHORT).show();

                                        }
                                    }
                                }


                            }
                        });
                        progressDialog.dismiss();
                        imageView1.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                Intent intent =new Intent();
                                intent.setType("image/*");
                                intent.setAction(Intent.ACTION_GET_CONTENT);
                                startActivityForResult(Intent.createChooser(intent,"Select Picture"),1);

                            }
                        });
                        dialog.setPositiveButton("Upload", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                                final ProgressDialog progressDialog=new ProgressDialog(HomeActivity.this);
                                progressDialog.setTitle("Please Wait!");
                                progressDialog.setMessage("Uploading");
                                progressDialog.show();
                                final String  randomName= UUID.randomUUID().toString();


                                //Toast.makeText(HomeActivity.this, ""+postimageuri, Toast.LENGTH_SHORT).show();



                                if(postimageuri!=null)
                                {
//                                    final SpotsDialog spotsDialog=new SpotsDialog(HomeActivity.this);
//                                    spotsDialog.dismiss();
                                    // UploadTask filepath=storagerefrence.child("IDProof").child(randomName+".jpg").putFile(postimageuri);

                                    storageReference.child("UserProof").child(randomName+".jpg").putFile(postimageuri).addOnCompleteListener(new OnCompleteListener<UploadTask.TaskSnapshot>() {
                                        @Override
                                        public void onComplete(@NonNull Task<UploadTask.TaskSnapshot> task) {

                                            if(task.isSuccessful()){

                                                task.getResult().getStorage().getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                                    @Override
                                                    public void onSuccess(Uri uri) {

                                                        downloadurl=uri.toString();

                                                        Map<String, Object> usermap = new HashMap<>();
                                                        usermap.put("imageurl",downloadurl);
                                                        usermap.put("name",name.getText().toString());


                                                        //oast.makeText(BookSeat_Activity.this, "date"+bdate+"tofrom"+tofrom, Toast.LENGTH_SHORT).show();
                                                        firebaseFirestore.collection("Users").document(firebaseAuth.getCurrentUser().getUid()).set(usermap).addOnCompleteListener(new OnCompleteListener<Void>() {
                                                            @Override
                                                            public void onComplete(@NonNull Task<Void> task) {

                                                                progressDialog.dismiss();


                                                            }
                                                        });





                                                    }
                                                });


                                            }

                                        }
                                    });




                                }
                                else {
                                    Toast.makeText(HomeActivity.this, "Invalid Details", Toast.LENGTH_SHORT).show();
                               progressDialog.dismiss();

                                }


                            }
                        });

                        dialog.show();


                        break;

                    default:

                        break;

                }
                fragmentTransaction.commitAllowingStateLoss();

                drawer.closeDrawers();


                //   loadHomeFragment();

                return true;
            }
        });


    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==1 &&resultCode==RESULT_OK && data!=null &&data.getData()!=null)
        {
            postimageuri=data.getData();
            imageView1.setImageURI(postimageuri);

        }
    }




}
