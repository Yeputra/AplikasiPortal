package id.Freaky.aplikasiportalprogramstudi.blog;

/**
 * Created by Andre Perdiana
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
import id.Freaky.aplikasiportalprogramstudi.model.BlogModel;

/**
 * A simple {@link Fragment} subclass.
 */
public class BlogFragment extends Fragment {

    RecyclerView rvBlog;
    FirebaseDatabase database;
    DatabaseReference myRef ;
    List<BlogModel> list;
    public BlogFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_blog, container, false);
        // Inflate the layout for this fragment
        rvBlog = rootView.findViewById(R.id.rv_blog);
        database = FirebaseDatabase.getInstance();
        myRef = database.getReference().child("Blog");
        myRef.keepSynced(true);

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                list = new ArrayList<BlogModel>();

                for(DataSnapshot dataSnapshot1 :dataSnapshot.getChildren()){
                    BlogModel value = dataSnapshot1.getValue(BlogModel.class);
                    BlogModel fire = new BlogModel();
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

                    BlogAdapter adapter = new BlogAdapter(list,getActivity());
                    RecyclerView.LayoutManager lm = new LinearLayoutManager(getActivity());
                    rvBlog.setLayoutManager(lm);
                    rvBlog.setItemAnimator( new DefaultItemAnimator());
                    rvBlog.setAdapter(adapter);
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
