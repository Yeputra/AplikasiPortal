package id.Freaky.aplikasiportalprogramstudi.berita;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import id.Freaky.aplikasiportalprogramstudi.R;
import id.Freaky.aplikasiportalprogramstudi.model.BeritaModel;

public class BeritaAdapter extends RecyclerView.Adapter<BeritaAdapter.MyHolder> {

    List<BeritaModel> list;
    Context context;

    public BeritaAdapter(List<BeritaModel> list, Context context) {
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
        BeritaModel mylist = list.get(position);
        holder.title.setText(mylist.getTitle());
        holder.content.setText(mylist.getContent());
        holder.writer.setText(mylist.getWriter());
        holder.date.setText(mylist.getDate());
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

    class MyHolder extends RecyclerView.ViewHolder{
        TextView title,content,writer,date;


        public MyHolder(View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.judul_berita);
            content= (TextView) itemView.findViewById(R.id.isi_berita);
            writer= (TextView) itemView.findViewById(R.id.penulis_berita);
            date= (TextView) itemView.findViewById(R.id.tanggal_berita);

        }
    }
}
