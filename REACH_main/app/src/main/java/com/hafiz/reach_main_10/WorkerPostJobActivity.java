package com.hafiz.reach_main_10;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.hafiz.reach_main_10.model.PostModel;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.single.PermissionListener;

public class WorkerPostJobActivity extends AppCompatActivity {

    TextInputEditText postTitle,fair,area,NID , PrmAdress,CntNo;
    ImageView uploadbtn,jobimage;
    Button submit;
    Uri imageUri;
    RelativeLayout relativeLayout;


    private FirebaseDatabase database;
    private FirebaseStorage firebaseStorage;
    ProgressDialog dialog ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_worker_post_job);


        database = FirebaseDatabase.getInstance();
        firebaseStorage = FirebaseStorage.getInstance();

        // Progress Dialogue
        dialog = new ProgressDialog(this);
        dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        dialog.setMessage("Please Wait");
        dialog.setCancelable(false);
        dialog.setTitle("Poasting New Job");
        dialog.setCanceledOnTouchOutside(false);

        postTitle = findViewById(R.id.post_title);
        fair = findViewById(R.id.post_fair);
        area = findViewById(R.id.post_area);
        NID = findViewById(R.id.post_NID);
        PrmAdress = findViewById(R.id.post_prmAdrress);
        CntNo = findViewById(R.id.post_cntNo);
        uploadbtn = findViewById(R.id.uploadBtn);
        jobimage = findViewById(R.id.JobImage);
        submit = findViewById(R.id.post);
        relativeLayout = findViewById(R.id.UpldImageRelative);


        uploadbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UploadImage();
                relativeLayout.setVisibility(View.VISIBLE);
                uploadbtn.setVisibility(View.GONE);

            }
        });

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.show();
                final StorageReference reference = firebaseStorage.getReference().child("Jobs")
                        .child(System.currentTimeMillis() + "");

                reference.putFile(imageUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        reference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                            @Override
                            public void onSuccess(Uri uri) {
                                PostModel model = new PostModel();
                                model.setProductImage(uri.toString());

                                model.setTitle(postTitle.getText().toString());
                                model.setFair(fair.getText().toString());
                                model.setNID(NID.getText().toString());
                                model.setArea(area.getText().toString());
                                model.setPrmAdress(PrmAdress.getText().toString());
                                model.setCntNo(CntNo.getText().toString());


                                database.getReference().child("Jobs").push().setValue(model)
                                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                                            @Override
                                            public void onSuccess(Void unused) {

                                                dialog.dismiss();
                                                Toast.makeText(WorkerPostJobActivity.this,"Job is Posted Successfully", Toast.LENGTH_LONG);

                                            }
                                        }).addOnFailureListener(new OnFailureListener() {
                                            @Override
                                            public void onFailure(@NonNull Exception e) {
                                                Toast.makeText(WorkerPostJobActivity.this,"Job Post failed!", Toast.LENGTH_LONG);
                                            }
                                        });
                            }
                        });
                    }
                });
                Toast.makeText(WorkerPostJobActivity.this,"Job is Posted Successfully", Toast.LENGTH_LONG);
                //startActivity(new Intent(WorkerPostJobActivity.this,HomepageForWorkerActivity.class));
            }
        });



    }

    private void UploadImage() {
        Dexter.withContext(this)
                .withPermission(Manifest.permission.READ_EXTERNAL_STORAGE)
                .withListener(new PermissionListener() {
                    @Override
                    public void onPermissionGranted(PermissionGrantedResponse permissionGrantedResponse) {
                        Intent intent =  new Intent();
                        intent.setType("image/*");
                        intent.setAction(Intent.ACTION_GET_CONTENT);
                        startActivityForResult(intent,101);
                    }

                    @Override
                    public void onPermissionDenied(PermissionDeniedResponse permissionDeniedResponse) {
                        Toast.makeText(WorkerPostJobActivity.this,"Permission Denied", Toast.LENGTH_SHORT);

                    }

                    @Override
                    public void onPermissionRationaleShouldBeShown(PermissionRequest permissionRequest, PermissionToken permissionToken) {
                        permissionToken.continuePermissionRequest();
                    }
                }).check();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 101 && resultCode == RESULT_OK)
        {
            imageUri = data.getData();
            jobimage.setImageURI(imageUri);
        }

    }
}