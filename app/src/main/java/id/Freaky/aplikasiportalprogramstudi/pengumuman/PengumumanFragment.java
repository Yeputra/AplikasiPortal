package id.Freaky.aplikasiportalprogramstudi.pengumuman;

/**
 * Created by Dalih Rusmana
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
import id.Freaky.aplikasiportalprogramstudi.model.PengumumanModel;

/**
 * A simple {@link Fragment} subclass.
 */
public class PengumumanFragment extends Fragment {
    RecyclerView rvPengumuman;
    FirebaseDatabase database;
    DatabaseReference myRef ;
    List<PengumumanModel> list;

    public PengumumanFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_pengumuman, container, false);
        rvPengumuman = rootView.findViewById(R.id.rv_pengumuman);
        database = FirebaseDatabase.getInstance();
        myRef = database.getReference().child("Pengumuman");
        myRef.keepSynced(true);

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                list = new ArrayList<PengumumanModel>();

                for(DataSnapshot dataSnapshot1 :dataSnapshot.getChildren()){
                    PengumumanModel value = dataSnapshot1.getValue(PengumumanModel.class);
                    PengumumanModel fire = new PengumumanModel();
                    String title = value.getTitle();
                    String date = value.getDate();
                    String url = value.getUrl();
                    fire.setTitle(title);
                    fire.setDate(date);
                    fire.setUrl(url);
                    list.add(fire);

                    PengumumanAdapter adapter = new PengumumanAdapter(list,getActivity());
                    RecyclerView.LayoutManager lm = new LinearLayoutManager(getActivity());
                    rvPengumuman.setLayoutManager(lm);
                    rvPengumuman.setItemAnimator( new DefaultItemAnimator());
                    rvPengumuman.setAdapter(adapter);
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
