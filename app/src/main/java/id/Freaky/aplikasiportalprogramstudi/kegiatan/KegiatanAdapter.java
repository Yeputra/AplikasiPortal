package id.Freaky.aplikasiportalprogramstudi.kegiatan;

/**
 * Created by Andre Perdiana
 */

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.google.gson.GsonBuilder;
import java.util.List;
import id.Freaky.aplikasiportalprogramstudi.DetailActivity;
import id.Freaky.aplikasiportalprogramstudi.R;
import id.Freaky.aplikasiportalprogramstudi.model.KegiatanModel;

public class KegiatanAdapter extends RecyclerView.Adapter<KegiatanAdapter.MyHolder> {

    List<KegiatanModel> list;
    Context context;

    public KegiatanAdapter(List<KegiatanModel> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.item_berita,parent,false);
        MyHolder myHolder = new MyHolder(view);


        return myHolder;
    }

    @Override
    public void onBindViewHolder(MyHolder holder, int position) {
        KegiatanModel mylist = list.get(position);
        holder.title.setText(mylist.getTitle());
        holder.content.setText(mylist.getContent());
        holder.writer.setText(mylist.getWriter());
        holder.date.setText(mylist.getDate());
        Glide.with(context)
                .load(mylist.getImage())
                .centerCrop()
                .placeholder(R.drawable.tumbnail)
                .into(holder.image);
    }

    @Override
    public int getItemCount() {

        int arr = 0;

        try{
            if(list.size()==0){

                arr = 0;

            }
            else{

                arr=list.size();
            }



        }catch (Exception e){



        }

        return arr;

    }

    class MyHolder extends RecyclerView.ViewHolder implements View.OnClickListener

    {
        TextView title, content, writer, date;
        ImageView image;
        CardView cvBerita;


        public MyHolder(View itemView) {
            super(itemView);
            cvBerita = itemView.findViewById(R.id.cv_berita);
            title = itemView.findViewById(R.id.judul_berita);
            content = itemView.findViewById(R.id.isi_berita);
            writer = itemView.findViewById(R.id.penulis_berita);
            date = itemView.findViewById(R.id.tanggal_berita);
            image = itemView.findViewById(R.id.iv_berita);

            cvBerita.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int position = getAdapterPosition();
            KegiatanModel data = list.get(position);
            final Context context = v.getContext();
            Intent intent = new Intent(context, DetailActivity.class);
            intent.putExtra("berita", new GsonBuilder().create().toJson(data));
            context.startActivity(intent);
        }
    }
}
