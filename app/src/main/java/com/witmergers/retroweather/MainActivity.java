package com.witmergers.retroweather;

import android.content.Context;
import android.content.Intent;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends ActionBarActivity {

    LocationManager lm;
    TextView lt, ln;
    String provider;
    Location l;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (savedInstanceState == null) {
            getFragmentManager().beginTransaction()
                    .add(R.id.container, new ForecastFragment())
                    .commit();
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            startActivity(new Intent(this,SettingsActivity.class));
            return true;
        } if (id == R.id.mylocation) {
            Toast.makeText(this,"Lat",Toast.LENGTH_LONG).show();
            //Geocoder myLocation = new Geocoder(getApplicationContext(), Locale.getDefault());
            //List<Address> myList = myLocation.getFromLocation(latPoint, lngPoint, 1);
            lm=(LocationManager)this.getSystemService(Context.LOCATION_SERVICE);
            Criteria c=new Criteria();
            provider=lm.getBestProvider(c, false);
            l=lm.getLastKnownLocation(provider);
            if(l!=null) {
                //get latitude and longitude of the location
                double lng = l.getLongitude();
                double lat = l.getLatitude();

                String uri = "geo:"+ lng + "," + lat;
                Toast.makeText(this,"Lat"+lat,Toast.LENGTH_LONG).show();
                startActivity(new Intent(android.content.Intent.ACTION_VIEW, Uri.parse(uri)));

            }


            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * A placeholder fragment containing a simple view.
     */

}
