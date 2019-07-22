package id.Freaky.aplikasiportalprogramstudi.berkas;

/**
 * Created by Yuda Eka Putra
 */

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import java.util.List;
import id.Freaky.aplikasiportalprogramstudi.R;
import id.Freaky.aplikasiportalprogramstudi.model.BerkasModel;

public class BerkasAdapter extends RecyclerView.Adapter<BerkasAdapter.MyHolder> {

    List<BerkasModel> list;
    Context context;

    public BerkasAdapter(List<BerkasModel> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.item_berkas, parent, false);
        MyHolder myHolder = new MyHolder(view);


        return myHolder;
    }

    @Override
    public void onBindViewHolder(MyHolder holder, int position) {
        BerkasModel mylist = list.get(position);
        holder.title.setText(mylist.getTitle());
        holder.content.setText(mylist.getContent());
        holder.date.setText(mylist.getDate());

        boolean expanded = mylist.isBtnShow();
        // Change the state
        mylist.setBtnShow(!expanded);

    }

    @Override
    public int getItemCount() {

        int arr = 0;

        try {
            if (list.size() == 0) {
                arr = 0;
            } else {
                arr = list.size();
            }
        } catch (Exception e) {

        }
        return arr;
    }

    class MyHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView title, content, writer, date;
        Button btn;
        CardView cvBerita;


        public MyHolder(View itemView) {
            super(itemView);
            cvBerita = itemView.findViewById(R.id.cv_berkas);
            title = itemView.findViewById(R.id.judul_berkas);
            content = itemView.findViewById(R.id.isi_berkas);
            date = itemView.findViewById(R.id.tanggal_berkas);
            btn = itemView.findViewById(R.id.btn_unduh_berkas);

            cvBerita.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int position = getAdapterPosition();
            BerkasModel data = list.get(position);

            boolean expanded = data.isBtnShow();
            // Set the visibility based on state
            btn.setVisibility(expanded ? View.VISIBLE : View.GONE);
        }
    }
}
