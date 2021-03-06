package com.xtremelabs.robolectric.shadows;

import android.preference.Preference;
import android.preference.PreferenceActivity;
import android.preference.PreferenceScreen;
import android.widget.ListView;

import com.xtremelabs.robolectric.internal.Implementation;
import com.xtremelabs.robolectric.internal.Implements;

@Implements(PreferenceActivity.class)
public class ShadowPreferenceActivity extends ShadowListActivity {
	
	private int preferencesResId = -1;
	private PreferenceScreen preferenceScreen;

	@Implementation
	public void addPreferencesFromResource(int preferencesResId) {
		this.preferencesResId = preferencesResId;
		preferenceScreen = getResourceLoader().inflatePreferences(getApplicationContext(), preferencesResId);
	}

	@Implementation
	public Preference findPreference(CharSequence key) {
		if (preferenceScreen == null) {
			return null;
		}
		return preferenceScreen.findPreference(key);
	}

	public int getPreferencesResId() {
		return preferencesResId;
	}
	
	@Implementation
	public PreferenceScreen getPreferenceScreen() {
		return preferenceScreen;
	}
}
