package ap.edu.ingloriousbrewstars;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.widget.Toolbar;
import android.widget.CalendarView;
import android.widget.ExpandableListView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Sander Peeters on 12/16/2015.
 */
public class ReportSearchDateView extends Activity {
    String curDate;
    TextView dateTitle;
    List<String> listDataHeader;
    List<String> listDataChild;
    List<StoragePlace> storagePlaces;
    List<Category> categories;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.report_search_date_view);

        curDate = getIntent().getStringExtra("curDate");
        dateTitle = (TextView) findViewById(R.id.textViewDate);
        dateTitle.setText(curDate);

        MyAdapter adapter = new MyAdapter(this, generateData());
        ListView listView = (ListView) findViewById(R.id.listViewDate);
        listView.setAdapter(adapter);

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
    }

    private ArrayList<Model> generateData() {
        //Moet uit database gehaald worden.
        storagePlaces = new ArrayList<StoragePlace>();
        categories = new ArrayList<Category>();

        storagePlaces.add(new StoragePlace("Storage place 1", "Dorpstraat 1", "Test", "2000"));
        storagePlaces.add(new StoragePlace("Storage place 2", "Dorpstraat 1", "Test", "2000"));
        storagePlaces.add(new StoragePlace("Storage place 3", "Dorpstraat 1", "Test", "2000"));

        categories.add(new Category("Bier 1", 22, true));
        categories.add(new Category("Bier 2", 24, true));
        categories.add(new Category("Bier 3", 22, true));
        categories.add(new Category("Box 1", 24, true));

        listDataHeader = new ArrayList<String>();
        listDataChild = new ArrayList<String>();

        for (int i = 0; i < storagePlaces.size(); i++) {
            listDataHeader.add(storagePlaces.get(i).getName());
        }

        for (int i = 0; i < categories.size(); i++) {
            listDataChild.add(categories.get(i).getName());
        }

        ArrayList<Model> models = new ArrayList<Model>();

        for (int i = 0; i < storagePlaces.size(); i++) {
            models.add(new Model(listDataHeader.get(i)));

            for (int y = 0; y < storagePlaces.size(); y++) {
                models.add(new Model(R.drawable.beer_bottle, listDataChild.get(y), String.valueOf(0)));
            }
        }

        return models;
    }
}
