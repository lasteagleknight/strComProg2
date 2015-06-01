package com.example.dc.fourcolors;

import utils.ColorUtil;
import ColorPicker.*;
import ColorPicker.ColorPickerDialog.OnColorChangedListener;
import android.app.Activity;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class Profile extends Activity {

	private ImageView color1;
	private ImageView color2;
	private ImageView color3;
	private ImageView color4;

	private TextView mName;
	private TextView mElo;

	private String name;
	private String elo;

	private SharedPreferences prefs;

	private static int COLOR_THRESHOLD = 10;
	private static int BACKGROUND_COLOR;
	private static int EDGE_COLOR;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.profile);

		prefs = this.getSharedPreferences("FourColors", this.MODE_PRIVATE);

		BACKGROUND_COLOR = getResources()
				.getInteger(R.integer.BACKGROUND_COLOR);

		EDGE_COLOR = getResources().getInteger(R.integer.EDGE_COLOR);

		DisplayMetrics metrics = new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(metrics);

		// TODO Initialize name and ELO fields

		// Initiliaze the color blocks
		color1 = (ImageView) findViewById(R.id.color1);
		ColorDrawable cd = new ColorDrawable(prefs.getInt("color1", Color.RED));
		color1.setImageDrawable(cd);
		color1.setMinimumHeight(metrics.heightPixels / 12);
		color1.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {

				// Show the color picker on click.
				ColorPickerDialog dialog = new ColorPickerDialog(Profile.this,
						prefs.getInt("color1", Color.RED));

				dialog.show();
				dialog.setOnColorChangedListener(color1Listener);
			}
		});

		color2 = (ImageView) findViewById(R.id.color2);
		cd = new ColorDrawable(prefs.getInt("color2", Color.GREEN));
		color2.setImageDrawable(cd);
		color2.setMinimumHeight(metrics.heightPixels / 12);
		color2.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				ColorPickerDialog dialog = new ColorPickerDialog(Profile.this,
						prefs.getInt("color2", Color.GREEN));

				dialog.show();
				dialog.setOnColorChangedListener(color2Listener);
			}
		});

		color3 = (ImageView) findViewById(R.id.color3);
		cd = new ColorDrawable(prefs.getInt("color3", Color.BLUE));
		color3.setImageDrawable(cd);
		color3.setMinimumHeight(metrics.heightPixels / 12);
		color3.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				ColorPickerDialog dialog = new ColorPickerDialog(Profile.this,
						prefs.getInt("color3", Color.BLUE));

				dialog.show();
				dialog.setOnColorChangedListener(color3Listener);
			}
		});

		color4 = (ImageView) findViewById(R.id.color4);
		cd = new ColorDrawable(prefs.getInt("color4", Color.YELLOW));
		color4.setImageDrawable(cd);
		color4.setMinimumHeight(metrics.heightPixels / 12);
		color4.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				ColorPickerDialog dialog = new ColorPickerDialog(Profile.this,
						prefs.getInt("color4", Color.YELLOW));

				dialog.show();
				dialog.setOnColorChangedListener(color4Listener);
			}
		});
	}

	// Listeners for each color picker.
	OnColorChangedListener color1Listener = new OnColorChangedListener() {

		public void onColorChanged(int color) {
			// Get a list of the other colors
			int[] otherColors = { prefs.getInt("color2", Color.GREEN),
					prefs.getInt("color3", Color.BLUE),
					prefs.getInt("color4", Color.YELLOW) };

			// Check if the color is valid
			if (!validateColor(color, otherColors)) {
				Toast.makeText(Profile.this,
						"Please select a different color.", Toast.LENGTH_SHORT)
						.show();

				return;
			}

			// Update the color in preferences
			prefs.edit().putInt("color1", color).commit();

			// Show the color
			ColorDrawable cd = new ColorDrawable(prefs.getInt("color1",
					Color.RED));
			color1.setImageDrawable(cd);
		}
	};

	OnColorChangedListener color2Listener = new OnColorChangedListener() {

		public void onColorChanged(int color) {
			int[] otherColors = { prefs.getInt("color1", Color.RED),
					prefs.getInt("color3", Color.BLUE),
					prefs.getInt("color4", Color.YELLOW) };
			if (!validateColor(color, otherColors)) {
				Toast.makeText(Profile.this,
						"Please select a different color.", Toast.LENGTH_SHORT)
						.show();

				return;
			}
			prefs.edit().putInt("color2", color).commit();
			ColorDrawable cd = new ColorDrawable(prefs.getInt("color2",
					Color.GREEN));
			color2.setImageDrawable(cd);
		}
	};

	OnColorChangedListener color3Listener = new OnColorChangedListener() {

		public void onColorChanged(int color) {
			int[] otherColors = { prefs.getInt("color2", Color.GREEN),
					prefs.getInt("color1", Color.RED),
					prefs.getInt("color4", Color.YELLOW) };

			if (!validateColor(color, otherColors)) {
				Toast.makeText(Profile.this,
						"Please select a different color.", Toast.LENGTH_SHORT)
						.show();

				return;
			}
			prefs.edit().putInt("color3", color).commit();

			ColorDrawable cd = new ColorDrawable(prefs.getInt("color3",
					Color.BLUE));
			color3.setImageDrawable(cd);
		}
	};

	OnColorChangedListener color4Listener = new OnColorChangedListener() {

		public void onColorChanged(int color) {
			int[] otherColors = { prefs.getInt("color2", Color.GREEN),
					prefs.getInt("color3", Color.BLUE),
					prefs.getInt("color1", Color.RED) };

			if (!validateColor(color, otherColors)) {
				Toast.makeText(Profile.this,
						"Please select a different color.", Toast.LENGTH_SHORT)
						.show();

				return;
			}

			prefs.edit().putInt("color4", color).commit();
			ColorDrawable cd = new ColorDrawable(prefs.getInt("color4",
					Color.YELLOW));
			color4.setImageDrawable(cd);
		}
	};

	// A color is valid if it's not black or white or perceptibly different
	// from the other colors.
	private boolean validateColor(int color, int[] otherColors) {
		// Get the lab coloring of the color selected
		double[] colorLAB = ColorUtil.RGBToLAB(color);

		// Get the 3 other colors
		double[] colorOther1 = ColorUtil.RGBToLAB(otherColors[0]);
		double[] colorOther2 = ColorUtil.RGBToLAB(otherColors[1]);
		double[] colorOther3 = ColorUtil.RGBToLAB(otherColors[2]);

		// Black and White
		double[] backgroundLAB = ColorUtil.RGBToLAB(BACKGROUND_COLOR);
		double[] edgeLAB = ColorUtil.RGBToLAB(EDGE_COLOR);

		// Compare colors
		if (ColorUtil.LABDist(colorLAB, backgroundLAB) < COLOR_THRESHOLD)
			return false;

		if (ColorUtil.LABDist(colorLAB, edgeLAB) < COLOR_THRESHOLD)
			return false;

		if (ColorUtil.LABDist(colorLAB, colorOther1) < COLOR_THRESHOLD)
			return false;

		if (ColorUtil.LABDist(colorLAB, colorOther2) < COLOR_THRESHOLD)
			return false;

		if (ColorUtil.LABDist(colorLAB, colorOther3) < COLOR_THRESHOLD)
			return false;

		return true;
	}
}
