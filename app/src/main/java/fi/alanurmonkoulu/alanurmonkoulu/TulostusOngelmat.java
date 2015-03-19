package fi.alanurmonkoulu.alanurmonkoulu;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;


public class TulostusOngelmat extends ActionBarActivity implements View.OnClickListener{

    Button tulostinEiAloitaNAppi, repetierHostYhteysPoikkiNappi, jokuMuuNappi;
    Intent tulostinEiAloitaIntent, repetierHostYhteysPoikkiIntent, jokuMuuIntent;
    Toolbar toolbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tulostus_ongelmat);

        muuttujat();
    }

    public void muuttujat(){
        toolbaarit();
        napit();
        intentit();
    }

    public void toolbaarit(){
        toolbar = (Toolbar) findViewById(R.id.customToolbar);
        setSupportActionBar(toolbar);
    }

    public void napit(){
        tulostinEiAloitaNAppi = (Button) findViewById(R.id.tulostinEiAloitaNappi);
        repetierHostYhteysPoikkiNappi = (Button) findViewById(R.id.repetierHostYhteysPoikkiNappi);
        jokuMuuNappi = (Button) findViewById(R.id.jokuMuuNappi);

        tulostinEiAloitaNAppi.setOnClickListener(this);
        repetierHostYhteysPoikkiNappi.setOnClickListener(this);
        jokuMuuNappi.setOnClickListener(this);
    }

    public void intentit(){
        tulostinEiAloitaIntent = new Intent(this, TulostinEiAloitaTulostusta.class);
        repetierHostYhteysPoikkiIntent = new Intent(this, RepetierHostYhteysPoikki.class);
        jokuMuuIntent = new Intent(this, JokuMuuOngelma.class);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_tulostus_ongelmat, menu);
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
            case R.id.tulostinEiAloitaNappi:
                startActivity(tulostinEiAloitaIntent);
                break;

            case R.id.repetierHostYhteysPoikkiNappi:
                startActivity(repetierHostYhteysPoikkiIntent);
                break;

            case R.id.jokuMuuNappi:
                startActivity(jokuMuuIntent);
                break;
        }
    }
}
