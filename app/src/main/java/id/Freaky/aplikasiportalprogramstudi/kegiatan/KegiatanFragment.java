package id.Freaky.aplikasiportalprogramstudi.kegiatan;


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
import id.Freaky.aplikasiportalprogramstudi.model.KegiatanModel;

/**
 * A simple {@link Fragment} subclass.
 */
public class KegiatanFragment extends Fragment {

    RecyclerView rvKegiatan;
    FirebaseDatabase database;
    DatabaseReference myRef ;
    List<KegiatanModel> list;

    public KegiatanFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_kegiatan, container, false);
        // Inflate the layout for this fragment
        rvKegiatan = rootView.findViewById(R.id.rv_kegiatan);
        database = FirebaseDatabase.getInstance();
        myRef = database.getReference().child("Kegiatan");
        myRef.keepSynced(true);

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                list = new ArrayList<KegiatanModel>();

                for(DataSnapshot dataSnapshot1 :dataSnapshot.getChildren()){
                    BeritaModel value = dataSnapshot1.getValue(BeritaModel.class);
                    KegiatanModel fire = new KegiatanModel();
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

                    KegiatanAdapter adapter = new KegiatanAdapter(list,getActivity());
                    RecyclerView.LayoutManager lm = new LinearLayoutManager(getActivity());
                    rvKegiatan.setLayoutManager(lm);
                    rvKegiatan.setItemAnimator( new DefaultItemAnimator());
                    rvKegiatan.setAdapter(adapter);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.w("Hello", "Failed to read value.", databaseError.toException());
            }
        });


        return rootView;
    }

}
