package ap.edu.ingloriousbrewstars;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.firebase.client.Firebase;

/**
 * Created by Sander Peeters on 12/2/2015.
 */
public class AddStoragePlace extends AppCompatActivity {
    Button addStoragePlace;
    Button cancelStoragePlace;
    EditText SPName;
    EditText SPAddress;
    EditText SPZip;
    EditText SPCity;
    String name;
    String address;
    String zip;
    String city;
    Firebase mFirebaseRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings_storageplace_add);
        overridePendingTransition(R.anim.from_middle, R.anim.to_middle);
        Firebase.setAndroidContext(this);

        SharedPreferences SP = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        int appBackColor = SP.getInt("applicationBackColor", 0);
        int appBackColorR = SP.getInt("applicationBackColorR", 0);
        int appBackColorG = SP.getInt("applicationBackColorG", 0);
        int appBackColorB = SP.getInt("applicationBackColorB", 0);
        int appColor = SP.getInt("applicationColor", 0);
        int appColorR = SP.getInt("applicationColorR", 0);
        int appColorG = SP.getInt("applicationColorG", 0);
        int appColorB = SP.getInt("applicationColorB", 0);
        boolean appStandardColor = SP.getBoolean("colorStandard", true);
        if (appStandardColor) {
            getWindow().getDecorView().setBackgroundColor(Color.rgb(238, 233, 233));
            toolbar.setBackgroundDrawable(new ColorDrawable(Color.rgb(59, 89, 152)));
        }
        else {
            if (appBackColor != 0) {
                getWindow().getDecorView().setBackgroundColor(Color.rgb(appBackColorR, appBackColorG, appBackColorB));
            }

            if (appColor != 0) {
                toolbar.setBackgroundDrawable(new ColorDrawable(Color.rgb(appColorR, appColorG, appColorB)));
            }
        }

        mFirebaseRef = new Firebase("https://shining-heat-1471.firebaseio.com/storage_places");

        SPName = (EditText) findViewById(R.id.editTextName);
        SPAddress = (EditText) findViewById(R.id.editTextAddress);
        SPZip = (EditText) findViewById(R.id.editTextZip);
        SPCity = (EditText) findViewById(R.id.editTextCity);

        addStoragePlace = (Button) findViewById(R.id.addStoragePlaceButton);
        addStoragePlace.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                name = SPName.getText().toString();
                address = SPAddress.getText().toString();
                zip = SPZip.getText().toString();
                city = SPCity.getText().toString();

                if(isEmpty(name) || isEmpty(address) || isEmpty(zip) || isEmpty(city)) {
                    Toast.makeText(AddStoragePlace.this, "Please fill in all boxes", Toast.LENGTH_SHORT).show();
                }
                else {
                    //Hier code om naar database weg te schrijven.
                    StoragePlace sp = new StoragePlace(name, address, zip, city);
                    mFirebaseRef.child(name).setValue(sp);
                    //Doordat view terug geopend wordt zal de listview geupdate zijn.
                    Intent SettingStoragePlace = new Intent(getApplicationContext(), SettingsStoragePlaces.class);
                    startActivity(SettingStoragePlace);
                    finish(); //zorgt ervoor dat als men op de back-knop drukt, dat men niet meer op deze view kan.
                }
            }
        });

        cancelStoragePlace = (Button) findViewById(R.id.cancelStoragePlaceButton);
        cancelStoragePlace.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                Intent SettingStoragePlace = new Intent(getApplicationContext(), SettingsStoragePlaces.class);
                startActivity(SettingStoragePlace);
            }
        });
    }

    private boolean isEmpty(String myeditText) {
        return myeditText.trim().length() == 0;
    }
}
