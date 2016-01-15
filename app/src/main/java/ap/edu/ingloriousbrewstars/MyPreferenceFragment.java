package ap.edu.ingloriousbrewstars;

import android.os.Bundle;
import android.preference.PreferenceFragment;

/**
 * Created by Sander Peeters on 12/16/2015.
 */
public class MyPreferenceFragment extends PreferenceFragment {
    @Override
    public void onCreate(final Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.settings_preferences);
    }
}