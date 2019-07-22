package id.Freaky.aplikasiportalprogramstudi.berita;

/**
 * Created by Yuda Eka Putra
 */


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import id.Freaky.aplikasiportalprogramstudi.R;
import id.Freaky.aplikasiportalprogramstudi.model.BeritaModel;

public class BeritaFragment extends Fragment {

    RecyclerView rvBerita;
    FirebaseDatabase database;
    DatabaseReference myRef ;
    List<BeritaModel> list;

    public BeritaFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_berita, container, false);
        // Inflate the layout for this fragment
        rvBerita = rootView.findViewById(R.id.rv_berita);
        database = FirebaseDatabase.getInstance();
        myRef = database.getReference().child("Berita");
        myRef.keepSynced(true);

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                list = new ArrayList<BeritaModel>();

                for(DataSnapshot dataSnapshot1 :dataSnapshot.getChildren()){
                    BeritaModel value = dataSnapshot1.getValue(BeritaModel.class);
                    BeritaModel fire = new BeritaModel();
                    String title = value.getTitle();
                    String content = value.getContent();
                    String writer = value.getWriter();
                    String date = value.getDate();
                    String image = value.getImage();
                    fire.setTitle(title);
                    fire.setContent(content);
                    fire.setWriter(writer);
                    fire.setDate(date);
                    fire.setImage(image);
                    list.add(fire);

                    BeritaAdapter adapter = new BeritaAdapter(list,getActivity());
                    RecyclerView.LayoutManager lm = new LinearLayoutManager(getActivity());
                    rvBerita.setLayoutManager(lm);
                    rvBerita.setItemAnimator( new DefaultItemAnimator());
                    rvBerita.setAdapter(adapter);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.w("Hello", "Failed to read value.", databaseError.toException());
            }
        });


        return rootView;
    }

//    public void uploadFile(){
//        Uri file = Uri.fromFile(new File("path/to/images/rivers.jpg"));
//        StorageReference riversRef = storageRef.child("images/rivers.jpg");
//
//        riversRef.putFile(file)
//                .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
//                    @Override
//                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
//                        // Get a URL to the uploaded content
//                        Uri downloadUrl = taskSnapshot.getDownloadUrl();
//                    }
//                })
//                .addOnFailureListener(new OnFailureListener() {
//                    @Override
//                    public void onFailure(@NonNull Exception exception) {
//                        // Handle unsuccessful uploads
//                        // ...
//                    }
//                });
//    }
//
//    public void downloadFile(){
//        File localFile = File.createTempFile("images", "jpg");
//        riversRef.getFile(localFile)
//                .addOnSuccessListener(new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
//                    @Override
//                    public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {
//                        // Successfully downloaded data to local file
//                        // ...
//                    }
//                }).addOnFailureListener(new OnFailureListener() {
//            @Override
//            public void onFailure(@NonNull Exception exception) {
//                // Handle failed download
//                // ...
//            }
//        });
//    }

}
