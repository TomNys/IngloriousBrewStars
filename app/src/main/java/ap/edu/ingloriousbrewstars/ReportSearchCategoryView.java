package ap.edu.ingloriousbrewstars;

import android.app.Activity;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.widget.Toolbar;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Sander Peeters on 12/16/2015.
 */
public class ReportSearchCategoryView extends Activity {
    String category;
    TextView categoryTitle;
    List<String> listDataHeader;
    List<String> listDataChild;
    List<StoragePlace> storagePlaces;
    List<Category> categories;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.report_search_category_view);

        category = getIntent().getStringExtra("category");
        categoryTitle = (TextView) findViewById(R.id.textViewCategory);
        categoryTitle.setText(category);

        MyAdapter adapter = new MyAdapter(this, generateData());
        ListView listView = (ListView) findViewById(R.id.listViewReportCategory);
        listView.setAdapter(adapter);
    }

    private ArrayList<Model> generateData() {
        //Moet uit database gehaald worden.
        storagePlaces = new ArrayList<StoragePlace>();
        categories = new ArrayList<Category>();

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

        storagePlaces.add(new StoragePlace("Storage place 1", "Dorpstraat 1", "Test", "2000"));
        storagePlaces.add(new StoragePlace("Storage place 2", "Dorpstraat 1", "Test", "2000"));
        storagePlaces.add(new StoragePlace("Storage place 3", "Dorpstraat 1", "Test", "2000"));

        categories.add(new Category("Bier 1", 22, true));

        listDataHeader = new ArrayList<String>();
        listDataChild = new ArrayList<String>();

        for (int i = 0; i < storagePlaces.size(); i++) {
            listDataHeader.add(storagePlaces.get(i).getName());
        }
        listDataChild.add(categories.get(0).getName());


        ArrayList<Model> models = new ArrayList<Model>();
        for (int i = 0; i < storagePlaces.size(); i++) {
            models.add(new Model(listDataHeader.get(i)));
            models.add(new Model(R.drawable.beer_bottle, listDataChild.get(0), String.valueOf(0)));
        }

        return models;
    }
}
