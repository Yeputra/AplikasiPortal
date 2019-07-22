package id.Freaky.aplikasiportalprogramstudi;

/**
 * Created by Yuda Eka Putra
 */

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.gson.GsonBuilder;

import id.Freaky.aplikasiportalprogramstudi.model.BeritaModel;

public class DetailActivity extends AppCompatActivity {

    private BeritaModel berita;
    TextView title, content, writer, date;
    ImageView image;
    CardView cvBerita;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        title = findViewById(R.id.judul);
        content = findViewById(R.id.isi);
        writer = findViewById(R.id.penulis);
        date = findViewById(R.id.tanggal);
        image = findViewById(R.id.iv_gambar);

        berita = new GsonBuilder().create().fromJson(this.getIntent().getStringExtra("berita"), BeritaModel.class);

        title.setText(berita.title);
        content.setText(berita.content);
        writer.setText(berita.writer);
        date.setText(berita.date);

        Glide.with(this)
                .load(berita.getImage())
                .centerCrop()
                .placeholder(R.drawable.tumbnail)
                .into(image);
    }
}
