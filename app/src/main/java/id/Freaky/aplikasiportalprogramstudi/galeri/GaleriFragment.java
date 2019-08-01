package id.Freaky.aplikasiportalprogramstudi.galeri;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
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
import id.Freaky.aplikasiportalprogramstudi.model.FotoModel;

/**
 * A simple {@link Fragment} subclass.
 */
public class GaleriFragment extends Fragment {

    RecyclerView rvFoto;
    FirebaseDatabase database;
    DatabaseReference myRef ;
    List<FotoModel> list;

    public GaleriFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_galeri, container, false);
        // Inflate the layout for this fragment
        rvFoto = rootView.findViewById(R.id.rv_foto);
        database = FirebaseDatabase.getInstance();
        myRef = database.getReference().child("Foto");
        myRef.keepSynced(true);

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                list = new ArrayList<FotoModel>();

                for(DataSnapshot dataSnapshot1 :dataSnapshot.getChildren()){
                    FotoModel value = dataSnapshot1.getValue(FotoModel.class);
                    FotoModel fire = new FotoModel();
                    String title = value.getTitle();
                    String foto = value.getFoto();
                    String url = value.getUrl();
                    fire.setTitle(title);
                    fire.setFoto(foto);
                    fire.setUrl(url);
                    list.add(fire);

                    FotoAdapter adapter = new FotoAdapter(list,getActivity());
                    RecyclerView.LayoutManager lm = new GridLayoutManager(getActivity(),2);

                    rvFoto.setLayoutManager(lm);
                    rvFoto.setAdapter(adapter);
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
