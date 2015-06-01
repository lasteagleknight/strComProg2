package com.example.dc.fourcolors;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.Toast;

public class MainPage extends Activity {

	private Button multiPlayer;
	private Button profile;
	private Button leisure;
	private SharedPreferences prefs;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.main_page);

		prefs = this.getSharedPreferences("FourColors", MODE_PRIVATE);

		SharedPreferences.Editor edit = prefs.edit();

		// Only update prefs if it doesn't already exist
		if (prefs.getBoolean("isset", false) == false) {
			edit.putInt("color1", getResources().getInteger(R.integer.color1));
			edit.putInt("color2", getResources().getInteger(R.integer.color2));
			edit.putInt("color3", getResources().getInteger(R.integer.color3));
			edit.putInt("color4", getResources().getInteger(R.integer.color4));
			edit.putBoolean("isset", true);
			edit.commit();
		}

		multiPlayer = (Button) this.findViewById(R.id.mainMPlayer);
		profile = (Button) this.findViewById(R.id.mainProfile);
		leisure = (Button) this.findViewById(R.id.mainLeisure);

		multiPlayer.setOnClickListener(mPlayerListener);
		profile.setOnClickListener(profileListener);
		leisure.setOnClickListener(leisureListener);
	}

	View.OnClickListener leisureListener = new View.OnClickListener() {

		public void onClick(View v) {
			Intent sPlayerModeIntent = new Intent(MainPage.this,
					LeisureModes.class);
			startActivity(sPlayerModeIntent);
		}
	};

	View.OnClickListener mPlayerListener = new View.OnClickListener() {

		public void onClick(View v) {
			Toast.makeText(getBaseContext(),
					"Sorry this feature is coming soon!", Toast.LENGTH_LONG).show();

		}
	};

	View.OnClickListener profileListener = new View.OnClickListener() {

		public void onClick(View v) {
			Intent profileIntent = new Intent(MainPage.this, Profile.class);
			startActivity(profileIntent);

		}
	};

}
