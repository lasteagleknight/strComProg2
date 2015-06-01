package com.example.dc.fourcolors;

import models.VersusAIGameState;
import utils.PuzzleBoard;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Matrix;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ImageView.ScaleType;
import android.widget.RelativeLayout;

public class LoadingSplash extends Activity {

	private ImageView splashImg;
	private DisplayMetrics metrics;
	private PuzzleBoard board;
	private int gameType;
	private Init init;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.loading_splash);

		Intent intent = this.getIntent();
		gameType = intent.getExtras().getInt("type");
		int numRegions = intent.getExtras().getInt("regions");

		splashImg = (ImageView) this.findViewById(R.id.loadingImage);
		DisplayMetrics metrics = new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(metrics);

		splashImg.setImageDrawable(this.getResources().getDrawable(
				R.drawable.logo));

		RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(
				metrics.widthPixels / 2, metrics.heightPixels / 2);
		layoutParams.addRule(RelativeLayout.CENTER_IN_PARENT,
				RelativeLayout.TRUE);
		splashImg.setLayoutParams(layoutParams);

		splashImg.post(new Runnable() {

			public void run() {
				// TODO Auto-generated method stub
				refreshView();
			}

		});

		init = new Init();
		init.execute(numRegions, metrics.widthPixels);
	}

	private void refreshView() {
		int xPivot = splashImg.getWidth() / 2;
		int yPivot = splashImg.getHeight() / 2;
		RotateAnimation anim = new RotateAnimation(0, 360, xPivot, yPivot);
		anim.setRepeatCount(Animation.INFINITE);
		anim.setDuration(5000);

		splashImg.setOnTouchListener(touchListener);
		splashImg.startAnimation(anim);
	}

	public class Init extends AsyncTask<Integer, Integer, Integer> {

		@Override
		protected Integer doInBackground(Integer... params) {
			android.os.Process
					.setThreadPriority(android.os.Process.THREAD_PRIORITY_FOREGROUND);

			board = new PuzzleBoard(params[0], params[1], params[1],
					LoadingSplash.this);
			return null;
		}

		protected void onPostExecute(Integer result) {
			BoardApplication app = (BoardApplication) LoadingSplash.this
					.getApplication();

			if (gameType == 0) {
				app.setSinglePlayerBoard(board);
				Intent colorItIntent = new Intent(LoadingSplash.this,
						ColorIt.class);
				startActivity(colorItIntent);
			} else {
				VersusAIGameState state = new VersusAIGameState(board);
				app.setVSAIBoard(state);
				Intent vsAIIntent = new Intent(LoadingSplash.this,
						VersusAI.class);
				startActivity(vsAIIntent);
			}
			finish();
		}

	}

	View.OnTouchListener touchListener = new View.OnTouchListener() {

		public boolean onTouch(View v, MotionEvent event) {
			Log.v("TOUCH", event.getX() + ", " + event.getY());
			return false;
		}
	};

	public void onBackPressed() {
		init.cancel(true);
		super.onBackPressed();
	}
}
