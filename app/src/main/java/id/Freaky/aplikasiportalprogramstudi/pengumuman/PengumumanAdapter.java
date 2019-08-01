package id.Freaky.aplikasiportalprogramstudi.pengumuman;

/**
 * Created by Dalih Rusmana
 */

import android.app.DownloadManager;
import android.content.Context;
import android.net.Uri;
import android.os.Environment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
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
        final PengumumanModel mylist = list.get(position);
        holder.title.setText(mylist.getTitle());
        holder.date.setText(mylist.getDate());
        holder.no.setText(mylist.getNo());
        holder.ll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                downloadFile(context, mylist.getTitle(),".pdf", Environment.DIRECTORY_DOWNLOADS,mylist.getUrl());
            }
        });
    }

    public long downloadFile(Context context, String fileName, String fileExtension, String destinationDirectory, String url) {

        DownloadManager downloadmanager = (DownloadManager) context.
                getSystemService(Context.DOWNLOAD_SERVICE);
        Uri uri = Uri.parse(url);
        DownloadManager.Request request = new DownloadManager.Request(uri);

        request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
        request.setDestinationInExternalFilesDir(context, destinationDirectory, fileName + fileExtension);

        return downloadmanager.enqueue(request);
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
        TextView title,date, no;
        LinearLayout ll;


        public MyHolder(View itemView) {
            super(itemView);
            title =  itemView.findViewById(R.id.tv_title_pengumuman);
            date = itemView.findViewById(R.id.tv_tanggal_pengumuman);
            no = itemView.findViewById(R.id.tv_no);
            ll = itemView.findViewById(R.id.ll_pengumuman);
        }
    }
}
