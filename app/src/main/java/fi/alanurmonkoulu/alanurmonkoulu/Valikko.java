package fi.alanurmonkoulu.alanurmonkoulu;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;


public class Valikko extends ActionBarActivity implements View.OnClickListener, RecyclerAdapteri.ClickListener{

    Button bonusLuukku, ohjeita, kahootNappi;
    Intent avaaOhjeita;
    SharedPreferences prefs;
    SharedPreferences.Editor editor;
    String appPackageName;
    boolean installed = false;
    RecyclerView recyclerView;
    RecyclerAdapteri adapteri;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_valikko);

        recyclerView = (RecyclerView) findViewById(R.id.kierrätysLista);
        adapteri = new RecyclerAdapteri(this, tietojenHankinta());
        adapteri.setClickListener(this);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapteri);

        muuttujat();
    }

    public void muuttujat(){
        //recyclerViewit();
        //adapterit();
        napit();
        intentit();
        toolbaarit();
    }

    public void napit(){
        bonusLuukku = (Button) findViewById(R.id.bonusLuukku);
        //ohjeita = (Button) findViewById(R.id.ohjeita);
        //kahootNappi = (Button) findViewById(R.id.kahootNappi);

        //ohjeita.setOnClickListener(this);
        bonusLuukku.setOnClickListener(this);
        //kahootNappi.setOnClickListener(this);
    }

    public void intentit(){
        avaaOhjeita = new Intent(this, Ohjeita.class);
    }

    public void toolbaarit(){
        toolbar = (Toolbar) findViewById(R.id.customToolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
    }

    public void recyclerViewit(){
        recyclerView = (RecyclerView) findViewById(R.id.kierrätysLista);
    }

    public List<ListanTietoja> tietojenHankinta(){
        List<ListanTietoja> tietoja = new ArrayList<>();
        String[] listaTekstit = {getString(R.string.ohjeita_nappi), getString(R.string.kahoot_nappi)};

        for (int i = 0; i < listaTekstit.length; i ++){
            ListanTietoja nykyinen = new ListanTietoja();
            nykyinen.recyclerListItemTextTitle = listaTekstit[i % listaTekstit.length];

            //add the information
            tietoja.add(nykyinen);
        }

        return tietoja;
    }

    public void adapterit(){
        adapteri = new RecyclerAdapteri(this, tietojenHankinta());
        recyclerView.setAdapter(adapteri);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);

        recyclerView.setLayoutManager(layoutManager);
    }





    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_valikko, menu);
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
        switch (v.getId()) {
            case R.id.bonusLuukku:
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=tB3mOp-m5OM")));
                break;

            /*
            case R.id.ohjeita:
                startActivity(avaaOhjeita);
                break;

            case R.id.kahootNappi:
                appPackageName = "no.mobitroll.kahoot.android";
                isAppInstalled(appPackageName);

                if (installed){
                    Intent LaunchIntent = getPackageManager().getLaunchIntentForPackage(appPackageName);
                    startActivity(LaunchIntent);
                }else{
                    try {
                        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + appPackageName)));
                    } catch (android.content.ActivityNotFoundException anfe) {
                        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("http://play.google.com/store/apps/details?id=" + appPackageName)));
                    }
                }
                break;
                */
        }
    }

    private boolean isAppInstalled(String appPackageName) {
        PackageManager pm = getPackageManager();
        try {
            pm.getPackageInfo(appPackageName, PackageManager.GET_ACTIVITIES);
            installed = true;
        } catch (PackageManager.NameNotFoundException e) {
            installed = false;
        }
        return installed;

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        prefs = this.getSharedPreferences("Asetukset", MODE_PRIVATE);
        editor = prefs.edit();
        editor.clear();
        editor.apply();
    }

    @Override
    public void itemClicked(View view, int position) {

        switch (position + 1){
            case 1:
                startActivity(avaaOhjeita);
                break;

            case 2:
                appPackageName = "no.mobitroll.kahoot.android";
                isAppInstalled(appPackageName);

                if (installed){
                    Intent LaunchIntent = getPackageManager().getLaunchIntentForPackage(appPackageName);
                    startActivity(LaunchIntent);
                }else{
                    try {
                        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + appPackageName)));
                    } catch (android.content.ActivityNotFoundException anfe) {
                        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("http://play.google.com/store/apps/details?id=" + appPackageName)));
                    }
                }
                break;
        }

    }
}
