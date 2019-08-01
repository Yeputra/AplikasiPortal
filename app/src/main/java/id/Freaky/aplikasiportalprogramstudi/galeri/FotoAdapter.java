package id.Freaky.aplikasiportalprogramstudi.galeri;

import android.app.DownloadManager;
import android.content.Context;
import android.net.Uri;
import android.os.Environment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import id.Freaky.aplikasiportalprogramstudi.R;
import id.Freaky.aplikasiportalprogramstudi.model.FotoModel;

public class FotoAdapter extends RecyclerView.Adapter<FotoAdapter.MyHolder> {

    List<FotoModel> list;
    Context context;

    public FotoAdapter(List<FotoModel> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.galeri_foto_item, parent, false);
        MyHolder myHolder = new MyHolder(view);


        return myHolder;
    }

    @Override
    public void onBindViewHolder(MyHolder holder, int position) {
        final FotoModel mylist = list.get(position);
        holder.title.setText(mylist.getTitle());
        Glide.with(context)
                .load(mylist.getFoto())
                .centerCrop()
                .placeholder(R.drawable.tumbnail)
                .into(holder.ivFoto);

        holder.ivDownload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                downloadFile(context, mylist.getTitle(),".jpg", Environment.DIRECTORY_DOWNLOADS,mylist.getUrl());

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

    class MyHolder extends RecyclerView.ViewHolder{
        TextView title;
        ImageView ivDownload, ivFoto;


        public MyHolder(View itemView) {
            super(itemView);
            ivDownload = itemView.findViewById(R.id.iv_download);
            title = itemView.findViewById(R.id.tv_foto);
            ivFoto = itemView.findViewById(R.id.iv_foto);
        }
    }
}
