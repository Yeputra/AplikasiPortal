package id.Freaky.aplikasiportalprogramstudi.berkas;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SimpleItemAnimator;
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
import id.Freaky.aplikasiportalprogramstudi.model.BerkasModel;

/**
 * A simple {@link Fragment} subclass.
 */
public class BerkasFragment extends Fragment {

    RecyclerView rvBerkas;
    FirebaseDatabase database;
    DatabaseReference myRef ;
    List<BerkasModel> list;

    public BerkasFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_berkas, container, false);
        // Inflate the layout for this fragment
        rvBerkas = rootView.findViewById(R.id.rv_berkas);
        database = FirebaseDatabase.getInstance();
        myRef = database.getReference().child("Berkas");
        myRef.keepSynced(true);

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                list = new ArrayList<BerkasModel>();

                for(DataSnapshot dataSnapshot1 :dataSnapshot.getChildren()){
                    BerkasModel value = dataSnapshot1.getValue(BerkasModel.class);
                    BerkasModel fire = new BerkasModel();
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

                    BerkasAdapter adapter = new BerkasAdapter(list,getActivity());
                    RecyclerView.LayoutManager lm = new LinearLayoutManager(getActivity());
                    ((SimpleItemAnimator) rvBerkas.getItemAnimator()).setSupportsChangeAnimations(false);

                    rvBerkas.setLayoutManager(lm);
                    rvBerkas.setItemAnimator( new DefaultItemAnimator());
                    rvBerkas.setAdapter(adapter);
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
