package ap.edu.ingloriousbrewstars;

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
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by Sander Peeters on 12/7/2015.
 */
public class TabFragment1ViewAdvanced extends AppCompatActivity {
    ArrayList<String> categoriesList;
    TextView header;
    String storagePlace;
    String categoryID;
    String item1;
    String item2;
    int numberOfItem1;
    int numberOfItem2;
    Button buttonAddItem;
    Button buttonAddContainer;
    EditText numberOfItemsInContainer;
    EditText numberOfContainer;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tab_fragment_1_viewadvanced);
        overridePendingTransition(R.anim.animation, R.anim.animation2);

        numberOfItemsInContainer = (EditText) findViewById(R.id.editTextItemsInContainer);
        numberOfContainer = (EditText) findViewById(R.id.editTextContainers);
        buttonAddItem = (Button) findViewById(R.id.buttonAddItem);
        buttonAddContainer = (Button) findViewById(R.id.buttonAddContainer);

        //Ophalen van de applicatiekleuren
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
                buttonAddItem.setBackgroundColor(Color.rgb(appColorR, appColorG, appColorB));
                buttonAddContainer.setBackgroundColor(Color.rgb(appColorR, appColorG, appColorB));
            }
        }

        storagePlace = getIntent().getStringExtra("storagePlace");
        categoryID = getIntent().getStringExtra("categoryID");
        header = (TextView) findViewById(R.id.titleadvanced);
        header.setText(storagePlace);

        MyAdapter adapter = new MyAdapter(this, generateData());
        ListView listView = (ListView) findViewById(R.id.listViewInventory);
        listView.setAdapter(adapter);

        buttonAddItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(numberOfItemsInContainer.getText().toString().equals(null) || numberOfItemsInContainer.getText().toString().equals("")) {
                    Toast.makeText(TabFragment1ViewAdvanced.this, "Value has to be more than 0.", Toast.LENGTH_SHORT).show();
                }
                else {
                    //Hier wegschrijven naar database.
                    Toast.makeText(TabFragment1ViewAdvanced.this, String.valueOf(numberOfItemsInContainer.getText()) + " item(s) added in one container.", Toast.LENGTH_SHORT).show();
                    generateData();
                }
            }
        });

        buttonAddContainer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(numberOfContainer.getText().toString().equals(null) || numberOfContainer.getText().toString().equals("")){
                    Toast.makeText(TabFragment1ViewAdvanced.this, "Value has to be more than 0.", Toast.LENGTH_SHORT).show();
                }
                else {
                    //Hier wegschrijven naar database.
                    Toast.makeText(TabFragment1ViewAdvanced.this, String.valueOf(numberOfContainer.getText()) + " full container(s) added.", Toast.LENGTH_SHORT).show();
                    generateData();
                }
            }
        });
    }

    private ArrayList<Model> generateData() {
        //Moet uit database gehaald worden.
        item1 = "Items";
        item2 = "Containers";
        numberOfItem1 = 89;
        numberOfItem2 = 3;

        ArrayList<Model> models = new ArrayList<Model>();
        models.add(new Model(categoryID));
        models.add(new Model(R.drawable.beer_bottle, item1, String.valueOf(numberOfItem1)));
        models.add(new Model(R.drawable.box, item2, String.valueOf(numberOfItem2)));

        return models;
    }
}
