package fi.alanurmonkoulu.alanurmonkoulu;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;


public class TulostusOhjeKappale8 extends ActionBarActivity implements View.OnClickListener{

    Button edellinenKappaleNappi;
    Intent edellinenKappale;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tulostus_ohje_kappale8);

        muuttujat();
    }

    public void muuttujat(){
        napit();
        intentit();
    }

    public void napit(){
        edellinenKappaleNappi = (Button) findViewById(R.id.edellinenKappaleNappi);

        edellinenKappaleNappi.setOnClickListener(this);
    }

    public void intentit(){
        edellinenKappale = new Intent(this, TulostusOhjeKappale7.class);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_tulostus_ohje_kappale8, menu);
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
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.edellinenKappaleNappi:
                startActivity(edellinenKappale);
                finish();
                break;
        }
    }
}
