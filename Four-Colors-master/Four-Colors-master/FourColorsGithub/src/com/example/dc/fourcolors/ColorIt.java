package com.example.dc.fourcolors;

import controllers.PuzzleSurface;
import utils.PuzzleBoard;
import models.Point;
import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.view.SurfaceView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.view.View.OnClickListener;

public class ColorIt extends Activity {
	private int currentColor;
	private int[] order;

	private PuzzleSurface graph;
	private PuzzleBoard board;

	private SharedPreferences prefs;

	private ImageView color1;
	private ImageView color2;
	private ImageView color3;
	private ImageView color4;

	private static int BACKGROUND_COLOR;
	private static int EDGE_COLOR;

	private BoardApplication app;

	private Toast invalid;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.color_it);

		prefs = this.getSharedPreferences("FourColors", this.MODE_PRIVATE);
		app = (BoardApplication) this.getApplication();
		board = app.getSinglePlayerBoard();
		currentColor = 0;

		BACKGROUND_COLOR = getResources()
				.getInteger(R.integer.BACKGROUND_COLOR);

		EDGE_COLOR = getResources().getInteger(R.integer.EDGE_COLOR);

		graph = (PuzzleSurface) this.findViewById(R.id.gameGraph);
		DisplayMetrics metrics = new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(metrics);

		order = board.getColors();

		color1 = (ImageView) findViewById(R.id.color1);
		ColorDrawable cd = new ColorDrawable(order[0]);
		color1.setImageDrawable(cd);
		color1.setMinimumHeight(metrics.heightPixels / 12);
		color1.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				currentColor = 0;

				color4.setBackgroundResource(0);
				color2.setBackgroundResource(0);
				color3.setBackgroundResource(0);
				color1.setBackgroundResource(R.drawable.color_border);
			}
		});

		color2 = (ImageView) findViewById(R.id.color2);
		cd = new ColorDrawable(order[1]);
		color2.setImageDrawable(cd);
		color2.setMinimumHeight(metrics.heightPixels / 12);
		color2.setBackgroundResource(0);
		color2.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				currentColor = 1;

				color1.setBackgroundResource(0);
				color4.setBackgroundResource(0);
				color3.setBackgroundResource(0);
				color2.setBackgroundResource(R.drawable.color_border);
			}
		});

		color3 = (ImageView) findViewById(R.id.color3);
		cd = new ColorDrawable(order[2]);
		color3.setImageDrawable(cd);
		color3.setMinimumHeight(metrics.heightPixels / 12);
		color3.setBackgroundResource(0);
		color3.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				currentColor = 2;

				color1.setBackgroundResource(0);
				color2.setBackgroundResource(0);
				color4.setBackgroundResource(0);
				color3.setBackgroundResource(R.drawable.color_border);
			}
		});

		color4 = (ImageView) findViewById(R.id.color4);
		cd = new ColorDrawable(order[3]);
		color4.setImageDrawable(cd);
		color4.setMinimumHeight(metrics.heightPixels / 12);
		color4.setBackgroundResource(0);
		color4.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				currentColor = 3;

				color1.setBackgroundResource(0);
				color2.setBackgroundResource(0);
				color3.setBackgroundResource(0);
				color4.setBackgroundResource(R.drawable.color_border);
			}
		});

		graph.setImage(board.toBitmap());

		LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
				metrics.widthPixels, metrics.widthPixels);
		params.height = metrics.widthPixels;

		graph.setLayoutParams(params);

		graph.setOnTouchListener(graphListener);
	}

	View.OnTouchListener graphListener = new View.OnTouchListener() {

		public boolean onTouch(View v, MotionEvent event) {

			// TODO Auto-generated method stub
			int eventX = (int) event.getX();
			int eventY = (int) event.getY();

			Point cur = new Point(eventX, eventY);

			Log.v("Coordinates", "(" + eventX + "," + eventY + ")");
			if (eventX >= board.getWidth() || eventY >= board.getHeight())
				return false;

			if (board.isValid(cur, order[currentColor])) {
				board.fillRegion(cur, order[currentColor]);
				graph.drawBitmap(board.toBitmap());

				if (board.isComplete()) {
					AlertDialog.Builder notice = new AlertDialog.Builder(
							ColorIt.this);

					notice.setTitle("Puzzle Complete!")
							.setMessage("Play Again?")
							.setPositiveButton("Yes",
									new DialogInterface.OnClickListener() {

										public void onClick(
												DialogInterface dialog,
												int which) {
											newGame();
										}
									})
							.setNegativeButton("No",
									new DialogInterface.OnClickListener() {

										public void onClick(
												DialogInterface dialog,
												int which) {
											// Intent returnToMain = new Intent(
											// ColorIt.this,
											// MainPage.class);
											// startActivity(returnToMain);
											app.setSinglePlayerBoard(null);
											board = null;
											finish();

										}
									}).setCancelable(false).show();
				}
			} else {
				if (invalid == null)
					invalid = Toast.makeText(ColorIt.this, "Invalid Coloring",
							Toast.LENGTH_SHORT);
				invalid.show();
			}
			return false;
		}
	};

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.title_page, menu);
		return true;
	}

	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.menu_new_game:
			newGame();
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
	}

	public void newGame() {
		app.setSinglePlayerBoard(null);
		board = null;
		Intent newColorIt = new Intent(ColorIt.this, ColorItModes.class);
		startActivity(newColorIt);
		finish();
	}

	protected void onPause() {
		super.onPause();
		app.setSinglePlayerBoard(board);
	}
}
