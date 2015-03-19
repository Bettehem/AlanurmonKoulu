package fi.alanurmonkoulu.alanurmonkoulu;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.telephony.SmsManager;
import android.telephony.TelephonyManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class JokuMuuOngelma extends ActionBarActivity implements View.OnClickListener{

    Toolbar toolbar;
    Button emailNappi, tekstiViestiNappi, puheluNappi;
    TelephonyManager telephonyManager;
    EditText kirjoitettuOngelmaViesti;
    TextView lahetysInfoTeksti, puheluTeksti;
    String ongelmaViesti, ilmoitusString;
    CharSequence ilmoitusTeksti;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_joku_muu_ongelma);

        muuttujat();
    }

    public void muuttujat(){
        toolbaarit();
        editTekstit();
        tekstit();
        napit();
        telephonyManagerit();
    }

    public void toolbaarit(){
        toolbar = (Toolbar) findViewById(R.id.customToolbar);
        setSupportActionBar(toolbar);
    }

    public void editTekstit(){
        kirjoitettuOngelmaViesti = (EditText) findViewById(R.id.kirjoitettuOngelmaViesti);
    }

    public void tekstit(){
        lahetysInfoTeksti = (TextView) findViewById(R.id.textView12);
        puheluTeksti = (TextView) findViewById(R.id.textView13);
    }

    public void napit(){
        emailNappi = (Button) findViewById(R.id.emailNappi);
        tekstiViestiNappi = (Button) findViewById(R.id.tekstiViestiNAppi);
        puheluNappi = (Button) findViewById(R.id.puheluNappi);

        emailNappi.setOnClickListener(this);
        tekstiViestiNappi.setOnClickListener(this);
        puheluNappi.setOnClickListener(this);
    }

    public void telephonyManagerit(){
        telephonyManager = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
        int simKortinTila = telephonyManager.getSimState();
        int puhelimenTila = telephonyManager.getPhoneType();

        switch (puhelimenTila){
            case TelephonyManager.PHONE_TYPE_NONE:
                puheluTeksti.setVisibility(View.INVISIBLE);
                tekstiViestiNappi.setVisibility(View.INVISIBLE);
                puheluNappi.setVisibility(View.INVISIBLE);
                emailNappi.setText(getString(R.string.lähetä));
                break;

            default:
                puheluTeksti.setVisibility(View.INVISIBLE);
                tekstiViestiNappi.setVisibility(View.INVISIBLE);
                puheluNappi.setVisibility(View.INVISIBLE);
                emailNappi.setText(getString(R.string.lähetä));
                switch (simKortinTila){
                    case TelephonyManager.SIM_STATE_READY:
                        simAktiivinen();
                        break;
                }
                break;
        }

    }

    public void simAktiivinen(){

        puheluTeksti.setVisibility(View.VISIBLE);
        tekstiViestiNappi.setVisibility(View.VISIBLE);
        puheluNappi.setVisibility(View.VISIBLE);
        lahetysInfoTeksti.setText(R.string.valitse_lahetystapa_alapuolelta_teksti);
        emailNappi.setText(getString(R.string.sposti_nappi));
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_joku_muu_ongelma, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }



    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.emailNappi:
                ongelmaViesti = kirjoitettuOngelmaViesti.getText().toString();
                if (ongelmaViesti.contentEquals("")) {
                    tyhjaviesti();
                }else{
                    emailSend();
                }
                break;
            case R.id.tekstiViestiNAppi:
                ongelmaViesti = kirjoitettuOngelmaViesti.getText().toString();

                if (ongelmaViesti.contentEquals("")){
                    tyhjaviesti();
                }else{
                    viestiSend();
                    viestiLahetetty();
                }
                break;

            case R.id.puheluNappi:
                Intent soitto = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + "+358400289961"));
                startActivity(soitto);
                break;
        }
    }

    public void emailSend(){
        String spViesti = ongelmaViesti;
        Intent emailIntent;
        String aihe = getString(R.string.tulostus_ongelmia_sposti_aihe);

        String emailaddress[] = {"chris.mustola@gmail.com"};
        emailIntent = new Intent(android.content.Intent.ACTION_SEND);
        emailIntent.putExtra(android.content.Intent.EXTRA_EMAIL, emailaddress);
        emailIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, aihe);
        emailIntent.setType("plain/text");
        emailIntent.putExtra(android.content.Intent.EXTRA_TEXT, spViesti);
        startActivity(emailIntent);
        finish();
    }

    public void viestiSend(){
        String viestiNro = "+358400289961";
        String kirjoitettuTeksti = ongelmaViesti;
        SmsManager viestiManageri =     SmsManager.getDefault();
        viestiManageri.sendTextMessage(viestiNro, null, kirjoitettuTeksti, null, null);
    }

    public void tyhjaviesti(){
        ilmoitusString = getString(R.string.tyhja_viesti);
        ilmoitusTeksti = ilmoitusString;
        Toast.makeText(getApplicationContext(), ilmoitusTeksti, Toast.LENGTH_LONG).show();
    }

    public void viestiLahetetty(){
        ilmoitusString = getString(R.string.viesti_lahetetty);
        ilmoitusTeksti = ilmoitusString;
        Toast.makeText(getApplicationContext(), ilmoitusTeksti, Toast.LENGTH_LONG).show();
    }

}
