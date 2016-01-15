package ap.edu.ingloriousbrewstars;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AbsListView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by Sander Peeters on 12/7/2015.
 */
public class TabFragment1Categories extends AppCompatActivity {
    ArrayList<String> categoriesList;
    String storagePlace;
    TextView header;
    boolean categoryAdvanced;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tab_fragment_1_categories);
        overridePendingTransition(R.anim.animation, R.anim.animation2);

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
            }
        }

        storagePlace = getIntent().getStringExtra("storagePlace");
        header = (TextView) findViewById(R.id.titlecategory);
        header.setText(storagePlace);

        categoriesList = new ArrayList<>();
        categoriesList.add("Category 1");
        categoriesList.add("Category 2");
        categoriesList.add("Category 3");
        categoriesList.add("Category 4");
        categoriesList.add("Category 5");
        categoriesList.add("Category 6");
        categoriesList.add("Category 7");
        categoriesList.add("Category 8");
        categoriesList.add("Category 9");
        categoriesList.add("Category 10");

        LinearLayout layout = (LinearLayout) findViewById(R.id.layoutbutton);
        layout.setOrientation(LinearLayout.VERTICAL);

        for (int i = 0; i < categoriesList.size(); i++) {
            LinearLayout row = new LinearLayout(this);
            row.setLayoutParams(new AbsListView.LayoutParams(AbsListView.LayoutParams.FILL_PARENT, AbsListView.LayoutParams.WRAP_CONTENT));

            Button btnTag = new Button(this);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(AbsListView.LayoutParams.FILL_PARENT, 170);
            btnTag.setText(categoriesList.get(i)); //titel van knop
            btnTag.setId(i); //id meegeven om knop aan te kunnen roepen
            params.setMargins(30, 30, 30, 30);
            btnTag.setLayoutParams(params);
            btnTag.setOnClickListener(onClickListener);
            btnTag.setBackgroundColor(Color.parseColor("#BDBDBD"));
            row.addView(btnTag);
            layout.addView(row);
        }
    }

    private View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            //Uit database halen of category 'advanced' is.
            categoryAdvanced = true;

            if (categoryAdvanced)
            {
                Intent intentViewAdvanced = new Intent(getApplicationContext(), TabFragment1ViewAdvanced.class);
                intentViewAdvanced.putExtra("storagePlace", storagePlace);
                intentViewAdvanced.putExtra("categoryID", categoriesList.get(v.getId()));
                startActivity(intentViewAdvanced);
            }
            else
            {
                Intent intentViewSimple = new Intent(getApplicationContext(), TabFragment1ViewSimple.class);
                intentViewSimple.putExtra("storagePlace", storagePlace);
                intentViewSimple.putExtra("categoryID", categoriesList.get(v.getId()));
                startActivity(intentViewSimple);
            }

        }
    };
}
