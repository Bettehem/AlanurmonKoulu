package fi.alanurmonkoulu.alanurmonkoulu;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewFlipper;

import java.util.ArrayList;
import java.util.List;

import fi.alanurmonkoulu.alanurmonkoulu.tabs.SlidingTabLayout;
import it.neokree.materialtabs.MaterialTab;
import it.neokree.materialtabs.MaterialTabHost;
import it.neokree.materialtabs.MaterialTabListener;

public class Ohjeita extends ActionBarActivity implements MaterialTabListener {

    Toolbar toolbar;
    SharedPreferences prefs;
    Intent tulostusKappale1, tulostuskappale2, tulostuskappale3, tulostuskappale4, tulostuskappale5, tulostuskappale6, tulostuskappale7, tulostuskappale8;
    ViewPager viewPager;
    MaterialTabHost materialTabHost;
    ViewPagerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ohjeita);

        /*
        recyclerView = (RecyclerView) findViewById(R.id.kappaleLista);
        adapteri = new RecyclerAdapteri(this, tietojenHankinta());
        adapteri.setClickListener(this);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapteri);*/

        muuttujat();
    }

    public void muuttujat() {
        asetukset();
        toolbaarit();
        tabit();
        viewPagerit();
        intentit();
    }

    public void toolbaarit() {
        toolbar = (Toolbar) findViewById(R.id.customToolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
    }

    public void tabit() {
        materialTabHost = (MaterialTabHost) findViewById(R.id.materialTabHost);

    }

    public void viewPagerit() {
        viewPager = (ViewPager) findViewById(R.id.viewPager);

        adapter = new ViewPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(adapter);
        viewPager.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener(){
            @Override
            public void onPageSelected(int position) {
                materialTabHost.setSelectedNavigationItem(position);
                pageChanged(position);
            }
        });

        for (int i = 0; i < adapter.getCount(); i++) {
            materialTabHost.addTab(
                    materialTabHost.newTab()
                            .setTabListener(this)
                            .setText(adapter.getPageTitle(i))
            );
        }
    }

    public void asetukset() {
        prefs = this.getSharedPreferences("Asetukset", MODE_PRIVATE);
    }

    public void intentit() {
        tulostusKappale1 = new Intent(this, TulostusOhjeKappale1.class);
        tulostuskappale2 = new Intent(this, TulostusOhjeKappale2.class);
        tulostuskappale3 = new Intent(this, TulostusOhjeKappale3.class);
        tulostuskappale4 = new Intent(this, TulostusOhjeKappale4.class);
        tulostuskappale5 = new Intent(this, TulostusOhjeKappale5.class);
        tulostuskappale6 = new Intent(this, TulostusOhjeKappale6.class);
        tulostuskappale7 = new Intent(this, TulostusOhjeKappale7.class);
        tulostuskappale8 = new Intent(this, TulostusOhjeKappale8.class);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_ohjeita, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.


        switch (item.getItemId()) {
            case R.id.action_nettisivut:
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.alanurmonkoulu.fi")));
                break;

            case R.id.action_tulostinongelmat:
                startActivity(new Intent(this, TulostusOngelmat.class));
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onTabSelected(MaterialTab materialTab) {
        viewPager.setCurrentItem(materialTab.getPosition());
        pageChanged(materialTab.getPosition());
    }

    @Override
    public void onTabReselected(MaterialTab materialTab) {
        pageChanged(materialTab.getPosition());
    }

    @Override
    public void onTabUnselected(MaterialTab materialTab) {

    }


    public class ViewPagerAdapter extends FragmentStatePagerAdapter{

        public ViewPagerAdapter (FragmentManager fm){
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return MyFragment.getInstance(position);
        }

        @Override
        public int getCount() {
            return 2;
        }

        public CharSequence getPageTitle(int position){
            return getResources().getStringArray(R.array.tabs)[position];
        }

    }



    public static class MyFragment extends Fragment implements RecyclerAdapteri.ClickListener{

        TextView textView;
        RecyclerAdapteri adapteri;
        RecyclerView recyclerView;
        int kappaleListanNumero;
        Intent tulostusKappale1, tulostuskappale2, tulostuskappale3, tulostuskappale4, tulostuskappale5, tulostuskappale6, tulostuskappale7, tulostuskappale8;


        public static MyFragment getInstance(int position) {
            MyFragment myFragment = new MyFragment();
            Bundle args = new Bundle();
            args.putInt("position", position);
            myFragment.setArguments(args);
            return myFragment;
        }


        @Override
        public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
            View layout = inflater.inflate(R.layout.ohje_kappaleet, container, false);

            intentit();
            textView = (TextView) layout.findViewById(R.id.textView8);
            recyclerView = (RecyclerView) layout.findViewById(R.id.kappaleLista);
            adapteri = new RecyclerAdapteri(getActivity(), tietojenHankinta());
            adapteri.setClickListener(this);
            LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
            layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
            recyclerView.setLayoutManager(layoutManager);
            recyclerView.setAdapter(adapteri);


            Bundle bundle = getArguments();

            if (bundle != null) {
                kappaleListanNumero = (bundle.getInt("position"));
                //Toast.makeText(getActivity(), "kappaleListanNumero = " + kappaleListanNumero, Toast.LENGTH_SHORT).show();
                tietojenHankinta();
                adapteri.updateAll();

                textView.setText("Sivu " + (bundle.getInt("position") + 1));
            }


            return layout;
        }


        public String[] listanKappaleet(int kappaleListanNro) {

            String[] kappaleetLista1 = {getString(R.string.tulostus_kappale1), getString(R.string.tulostus_kappale2), getString(R.string.tulostus_kappale3), getString(R.string.tulostus_kappale4), getString(R.string.tulostus_kappale5), getString(R.string.tulostus_kappale6), getString(R.string.tulostus_kappale7), getString(R.string.tulostus_kappale8)};
            String[] kappaleetLista2 = {"TinkerCad"};
            String[] kappaleetListaNotFound = {"List not found!"};


            String[] kappaleet;

            switch (kappaleListanNro) {
                case 0:
                    kappaleet = kappaleetLista1;
                    break;
                case 1:
                    kappaleet = kappaleetLista2;
                    break;
                case 2:
                    kappaleet = kappaleetLista2;
                    break;

                default:
                    kappaleet = kappaleetListaNotFound;
                    break;
            }

            return kappaleet;
        }

        public List<ListanTietoja> tietojenHankinta(){
            List<ListanTietoja> tietoja = new ArrayList<>();
            String[] kappaleet;
            kappaleet = listanKappaleet(kappaleListanNumero);

            for (int i = 0; i < kappaleet.length; i ++){
                ListanTietoja nykyinen = new ListanTietoja();
                nykyinen.recyclerListItemTextTitle = kappaleet[i % kappaleet.length];

                //add the new information
                tietoja.add(nykyinen);

            }

            return tietoja;
        }

        public void intentit() {
            tulostusKappale1 = new Intent(getActivity(), TulostusOhjeKappale1.class);
            tulostuskappale2 = new Intent(getActivity(), TulostusOhjeKappale2.class);
            tulostuskappale3 = new Intent(getActivity(), TulostusOhjeKappale3.class);
            tulostuskappale4 = new Intent(getActivity(), TulostusOhjeKappale4.class);
            tulostuskappale5 = new Intent(getActivity(), TulostusOhjeKappale5.class);
            tulostuskappale6 = new Intent(getActivity(), TulostusOhjeKappale6.class);
            tulostuskappale7 = new Intent(getActivity(), TulostusOhjeKappale7.class);
            tulostuskappale8 = new Intent(getActivity(), TulostusOhjeKappale8.class);
        }

        @Override
        public void itemClicked(View view, int position) {

            switch (position + 1) {
                case 1:
                    startActivity(tulostusKappale1);
                    break;

                case 2:
                    startActivity(tulostuskappale2);
                    break;
                case 3:
                    startActivity(tulostuskappale3);
                    break;
                case 4:
                    startActivity(tulostuskappale4);
                    break;
                case 5:
                    startActivity(tulostuskappale5);
                    break;
                case 6:
                    startActivity(tulostuskappale6);
                    break;
                case 7:
                    startActivity(tulostuskappale7);
                    break;
                case 8:
                    startActivity(tulostuskappale8);
                    break;
            }

        }
    }

    public void pageChanged (int position){
        TextView tv = (TextView) findViewById(R.id.textView8);
        switch (position + 1){
            case 1:
                tv.setText("Valitse kappale alapuolelta");
                break;

            default:
                tv.setText("Tinkercad ohje tulossa pian!");
                break;
        }
    }

}
