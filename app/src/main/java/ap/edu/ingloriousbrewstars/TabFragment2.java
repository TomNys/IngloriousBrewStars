package ap.edu.ingloriousbrewstars;

        import android.app.ActionBar;
        import android.app.Activity;
        import android.content.Intent;
        import android.graphics.Color;
        import android.os.Bundle;
        import android.support.v4.app.Fragment;
        import android.support.v4.view.ViewPager;
        import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewGroup;

        import lt.lemonlabs.android.expandablebuttonmenu.ExpandableButtonMenu;
        import lt.lemonlabs.android.expandablebuttonmenu.ExpandableMenuOverlay;

        import android.os.Bundle;
        import android.support.v7.app.ActionBarActivity;
        import android.widget.TextView;
        import android.widget.Toast;

        import com.fasterxml.jackson.annotation.JsonAutoDetect;
        import com.firebase.client.ChildEventListener;
        import com.firebase.client.DataSnapshot;
        import com.firebase.client.Firebase;
        import com.firebase.client.FirebaseError;
        import com.firebase.client.ValueEventListener;
        import com.firebase.ui.FirebaseListAdapter;

        import org.eazegraph.lib.charts.PieChart;
        import org.eazegraph.lib.models.PieModel;

        import java.util.Map;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)

public class TabFragment2 extends Fragment {
    View rootView;
    private ExpandableMenuOverlay menuOverlay;
    private Firebase mFirebaseRef;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.tab_fragment_2, container, false);
        Firebase.setAndroidContext(rootView.getContext());

        /*
        mFirebaseRef = new Firebase("https://shining-heat-1471.firebaseio.com/inventories");
        mFirebaseRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                System.out.println("There are " + dataSnapshot.getChildrenCount() + " inventories");
                for (DataSnapshot child: dataSnapshot.getChildren()) {
                    Inventory inventory = child.getValue(Inventory.class);
                    System.out.println("Child: " + inventory.getName());
                }
            }
        @Override
        public void onCancelled(FirebaseError firebaseError) {
                System.out.println("The read failed: " + firebaseError.getMessage());
            }
        });
        */

        menuOverlay = (ExpandableMenuOverlay) rootView.findViewById(R.id.button_menu);
        menuOverlay.setOnMenuButtonClickListener(new ExpandableButtonMenu.OnMenuButtonClick() {
            @Override
            public void onClick(ExpandableButtonMenu.MenuButton action) {
                switch (action) {
                    case MID:
                        Intent searchByCategory = new Intent(rootView.getContext(), ReportSearchCategory.class);
                        startActivity(searchByCategory);
                        menuOverlay.getButtonMenu().toggle();
                        break;
                    case LEFT:
                        Intent searchByDate = new Intent(rootView.getContext(), ReportSearchDate.class);
                        startActivity(searchByDate);
                        menuOverlay.getButtonMenu().toggle();
                        break;
                    case RIGHT:
                        Intent ExportExcel = new Intent(rootView.getContext(), ReportExportExcel.class);
                        startActivity(ExportExcel);
                        menuOverlay.getButtonMenu().toggle();
                        break;
                }
            }
        });

        PieChart mPieChart = (PieChart) rootView.findViewById(R.id.piechart);

        mPieChart.addPieSlice(new PieModel("Category 1", 15, Color.parseColor("#FE6DA8")));
        mPieChart.addPieSlice(new PieModel("Category 2", 25, Color.parseColor("#56B7F1")));
        mPieChart.addPieSlice(new PieModel("Category 3", 35, Color.parseColor("#CDA67F")));
        mPieChart.addPieSlice(new PieModel("Category 4", 9, Color.parseColor("#FED70E")));

        mPieChart.startAnimation();

        return rootView;
    }
}