package fi.alanurmonkoulu.alanurmonkoulu;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;


public class TulostusOhjeKappaleet extends ActionBarActivity implements AdapterView.OnItemClickListener{

    String[] kappaleet;
    Intent kappale1, kappale2, kappale3, kappale4, kappale5, kappale6, kappale7, kappale8;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ohje_kappaleet);

        muuttujat();
    }

    public void muuttujat(){
        tekstit();
        intentit();
    }

    public void tekstit(){
        kappaleet = new String[]{getString(R.string.tulostus_kappale1), getString(R.string.tulostus_kappale2), getString(R.string.tulostus_kappale3), getString(R.string.tulostus_kappale4), getString(R.string.tulostus_kappale5), getString(R.string.tulostus_kappale6), getString(R.string.tulostus_kappale7), getString(R.string.tulostus_kappale8)};
    }

    public void intentit(){
        kappale1 = new Intent(this, TulostusOhjeKappale1.class);
        kappale2 = new Intent(this, TulostusOhjeKappale2.class);
        kappale3 = new Intent(this, TulostusOhjeKappale3.class);
        kappale4 = new Intent(this, TulostusOhjeKappale4.class);
        kappale5 = new Intent(this, TulostusOhjeKappale5.class);
        kappale6 = new Intent(this, TulostusOhjeKappale6.class);
        kappale7 = new Intent(this, TulostusOhjeKappale7.class);
        kappale8 = new Intent(this, TulostusOhjeKappale8.class);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_tulostus_ohje_kappaleet, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        switch (item.getItemId()){
            case R.id.action_nettisivut:
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.alanurmonkoulu.fi")));
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        switch (position+1){
            case 1:
                startActivity(kappale1);
                break;

            case 2:
                startActivity(kappale2);
                break;

            case 3:
                startActivity(kappale3);
                break;

            case 4:
                startActivity(kappale4);
                break;

            case 5:
                startActivity(kappale5);
                break;

            case 6:
                startActivity(kappale6);
                break;

            case 7:
                startActivity(kappale7);
                break;

            case 8:
                startActivity(kappale8);
                break;
        }
    }
}
