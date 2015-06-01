package com.example.dc.fourcolors;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ColorItModes extends Activity {

	private Button easy;
	private Button medium;
	private Button hard;
	private Button custom;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.color_it_modes);

		easy = (Button) this.findViewById(R.id.colorIt_easy);
		medium = (Button) this.findViewById(R.id.colorIt_medium);
		hard = (Button) this.findViewById(R.id.colorIt_hard);
		custom = (Button) this.findViewById(R.id.colorIt_custom);

		easy.setOnClickListener(modeListener);
		medium.setOnClickListener(modeListener);
		hard.setOnClickListener(modeListener);
		custom.setOnClickListener(modeListener);

	}

	View.OnClickListener modeListener = new View.OnClickListener() {

		public void onClick(View v) {
			Intent colorItIntent = new Intent(ColorItModes.this,
					LoadingSplash.class);

			int numRegions = 10;

			if (v.equals(easy)) {
				numRegions = 10;
			} else if (v.equals(medium)) {
				numRegions = 15;
			} else if (v.equals(hard)) {
				numRegions = 20;
			} else {
				AlertDialog.Builder notice = new AlertDialog.Builder(
						ColorItModes.this);

				notice.create();

				final EditText tiles = new EditText(ColorItModes.this);
				notice.setTitle("How many tiles?!")
						.setView(tiles)
						.setPositiveButton("Start",
								new DialogInterface.OnClickListener() {

									public void onClick(DialogInterface dialog,
											int which) {
										int num = 0;

										try {
											num = Integer.parseInt(tiles
													.getText().toString());
										} catch (Exception e) {
											Toast.makeText(ColorItModes.this,
													"Invalid Number",
													Toast.LENGTH_SHORT).show();
											return;
										}

										if (num > 50 || num <= 0) {
											Toast.makeText(ColorItModes.this,
													"Invalid Number",
													Toast.LENGTH_SHORT).show();
											return;
										}

										Intent newGameIntent = new Intent(
												ColorItModes.this,
												LoadingSplash.class);

										newGameIntent.putExtra("regions", num);

										startActivity(newGameIntent);
										finish();

									}
								}).setNegativeButton("Cancel", null)
						.setCancelable(false).show();

				return;
			}

			colorItIntent.putExtra("regions", numRegions);

			// ColorIt has gameType 0
			int gameType = 0;
			colorItIntent.putExtra("type", gameType);
			startActivity(colorItIntent);
			finish();
		}
	};

	public int getNum() {
		int value = 10;

		AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(
				ColorItModes.this);

		dialogBuilder.create();

		return value;
	}

}
