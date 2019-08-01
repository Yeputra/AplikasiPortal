package id.Freaky.aplikasiportalprogramstudi;

/**
 * Created by Yuda Eka Putra
 */

import android.content.Intent;
import android.net.Uri;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import id.Freaky.aplikasiportalprogramstudi.berita.BeritaFragment;
import id.Freaky.aplikasiportalprogramstudi.berkas.BerkasFragment;
import id.Freaky.aplikasiportalprogramstudi.blog.BlogFragment;
import id.Freaky.aplikasiportalprogramstudi.galeri.GaleriFragment;
import id.Freaky.aplikasiportalprogramstudi.kegiatan.KegiatanFragment;
import id.Freaky.aplikasiportalprogramstudi.pengumuman.PengumumanFragment;

public class MainActivity  extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    protected DrawerLayout mDrawer;
    Fragment fragment;
    Class fragmentClass;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        FloatingActionButton fab = findViewById(R.id.fab);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Uri uri = Uri.parse("mailto:" + "pgpaud_tasik@upi.edu")
                        .buildUpon()
                        .appendQueryParameter("subject", "Subject")
                        .appendQueryParameter("body", "")
                        .build();
                Intent intent = new Intent(Intent.ACTION_SENDTO, uri);
                intent.setData(Uri.parse("mailto:pgpaud_tasik@upi.edu"));
                startActivity(Intent.createChooser(intent, "Kirim Email"));
            }
        });
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);

            fragmentClass = BlogFragment.class;
            try {
                fragment = (Fragment) fragmentClass.newInstance();
            } catch (Exception e) {
                e.printStackTrace();
            }
            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction().replace(R.id.flContent, fragment).commit();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_berita) {
            fragmentClass = BeritaFragment.class;
            fragment = new BeritaFragment();
            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction().replace(R.id.flContent, fragment).commit();
            toolbar.setTitle("Berita");
        } else if (id == R.id.nav_pengumuman) {
            fragmentClass = PengumumanFragment.class;
            fragment = new PengumumanFragment();
            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction().replace(R.id.flContent, fragment).commit();
            toolbar.setTitle("Pengumuman");
        } else if (id == R.id.nav_berkas) {
            fragmentClass = BerkasFragment.class;
            fragment = new BerkasFragment();
            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction().replace(R.id.flContent, fragment).commit();
            toolbar.setTitle("Berkas");
        } else if (id == R.id.nav_kegiatan) {
            fragmentClass = KegiatanFragment.class;
            fragment = new KegiatanFragment();
            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction().replace(R.id.flContent, fragment).commit();
            toolbar.setTitle("Kegiatan");
        } else if (id == R.id.nav_blog) {
            fragmentClass = BlogFragment.class;
            fragment = new BlogFragment();
            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction().replace(R.id.flContent, fragment).commit();
            toolbar.setTitle("Blog");
        } else if (id == R.id.nav_galeri) {
            fragmentClass = GaleriFragment.class;
            fragment = new GaleriFragment();
            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction().replace(R.id.flContent, fragment).commit();
            toolbar.setTitle("Galeri");
        }

        mDrawer = findViewById(R.id.drawer_layout);
        mDrawer.closeDrawer(GravityCompat.START);
        return true;
    }

}
