package id.Freaky.aplikasiportalprogramstudi.pengumuman;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import id.Freaky.aplikasiportalprogramstudi.R;
import id.Freaky.aplikasiportalprogramstudi.model.PengumumanModel;

public class PengumumanAdapter extends RecyclerView.Adapter<PengumumanAdapter.MyHolder> {

    List<PengumumanModel> list;
    Context context;

    public PengumumanAdapter(List<PengumumanModel> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.item_pengumuman,parent,false);
        MyHolder myHolder = new MyHolder(view);


        return myHolder;
    }

    @Override
    public void onBindViewHolder(MyHolder holder, int position) {
        PengumumanModel mylist = list.get(position);
        holder.title.setText(mylist.getTitle());
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
        TextView title,date;


        public MyHolder(View itemView) {
            super(itemView);
            title =  itemView.findViewById(R.id.tv_title_pengumuman);
            date = itemView.findViewById(R.id.tv_tanggal_pengumuman);
        }
    }
}
