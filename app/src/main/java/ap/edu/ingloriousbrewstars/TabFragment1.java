package ap.edu.ingloriousbrewstars;

import android.app.ActionBar;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.kd.dynamic.calendar.generator.ImageGenerator;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;

import ap.edu.ingloriousbrewstars.R;

public class TabFragment1 extends Fragment {

    TextView dayTextView;
    TextView monthTextView;
    View rootView;
    private List<String> data;
    SingleListAdapter adapter;
    ListView listviewStoragePlace;

    @Override
    public View onCreateView(final LayoutInflater inflater, final ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.tab_fragment_1, container, false);

        //calendar
        Calendar c = Calendar.getInstance();
        String day = String.valueOf(c.get(Calendar.DAY_OF_MONTH));
        String month = String.format(Locale.ENGLISH, "%tB", c);
        dayTextView = (TextView) rootView.findViewById(R.id.day);
        monthTextView = (TextView) rootView.findViewById(R.id.month);
        dayTextView.setText(day);
        monthTextView.setText(month);
        monthTextView.setTextColor(Color.WHITE);

        listviewStoragePlace = (ListView) rootView.findViewById(R.id.storagePlacesListView);
        listviewStoragePlace.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String selectedFromList = (String) (listviewStoragePlace.getItemAtPosition(position));

                Intent intentCategories = new Intent(rootView.getContext(), TabFragment1Categories.class);
                intentCategories.putExtra("storagePlace", selectedFromList);
                TabFragment1.this.startActivity(intentCategories);
            }
        });

        data = new ArrayList<String>();
        fillData();
        adapter = new SingleListAdapter(getActivity(), data);
        ListView lvMain = (ListView) rootView.findViewById(R.id.storagePlacesListView);
        lvMain.setAdapter(adapter);
        lvMain.setBackgroundColor(Color.WHITE);

        return rootView;
    }

    void fillData() {
        data.add("Storageplace 1");
        data.add("Storageplace 2");
        data.add("Storageplace 3");
        data.add("Storageplace 4");
        data.add("Storageplace 5");
        data.add("Storageplace 6");
    }

}