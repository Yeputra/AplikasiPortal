package id.Freaky.aplikasiportalprogramstudi;

/**
 * Created by Yuda Eka Putra
 */

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.gson.GsonBuilder;

import id.Freaky.aplikasiportalprogramstudi.model.BeritaModel;

public class DetailActivity extends AppCompatActivity {

    private BeritaModel berita;
    TextView title, content, writer, date;
    ImageView image;
    ImageView back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        title = findViewById(R.id.judul);
        content = findViewById(R.id.isi);
        writer = findViewById(R.id.penulis);
        date = findViewById(R.id.tanggal);
        image = findViewById(R.id.iv_gambar);
        back = findViewById(R.id.iv_back);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

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
