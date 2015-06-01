package com.example.dc.fourcolors;

import utils.PuzzleBoard;
import models.Point;
import models.VersusAIGameState;
import controllers.AIMove;
import controllers.PuzzleSurface;
import android.os.Bundle;
import android.os.Handler;
import android.app.Activity;
import android.app.AlertDialog;
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
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class VersusAI extends Activity {
	int currentColor;

	private PuzzleSurface graph;
	private PuzzleBoard board;

	private static int[] order;

	private static ImageView pColor1;
	private static ImageView pColor2;
	private static ImageView cColor1;
	private static ImageView cColor2;

	private static ImageView[] buttons;

	private TextView yourScore;
	private TextView computerScore;

	private int playerScore;
	private int aiScore;

	private String turn = "Player";

	SharedPreferences prefs;

	private BoardApplication app;

	private Toast invalid;

	private int numPixs;
	private Handler handler;

	private VersusAIGameState state;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.vs_ai);
		handler = new Handler();

		app = (BoardApplication) this.getApplication();
		state = app.getVSAIGameState();
		board = state.getBoard();

		pColor1 = (ImageView) findViewById(R.id.player_color1);
		pColor2 = (ImageView) findViewById(R.id.player_color2);
		cColor1 = (ImageView) findViewById(R.id.computer_color1);
		cColor2 = (ImageView) findViewById(R.id.computer_color2);

		buttons = new ImageView[4];
		buttons[0] = pColor1;
		buttons[1] = cColor1;
		buttons[2] = pColor2;
		buttons[3] = cColor2;

		prefs = this.getSharedPreferences("FourColors", this.MODE_PRIVATE);
		order = board.getColors();
		currentColor = state.getCurrentColor();

		playerScore = state.getPlayerScore();
		aiScore = state.getAIScore();

		yourScore = (TextView) this.findViewById(R.id.player_score);
		computerScore = (TextView) this.findViewById(R.id.computer_score);

		numPixs = board.getHeight() * board.getWidth();
		computerScore.setText((int) (aiScore / ((double) numPixs) * 1000)
				+ " :CPU");
		yourScore.setText("You: "
				+ (int) (playerScore / ((double) numPixs) * 1000));

		graph = (PuzzleSurface) this.findViewById(R.id.gameGraph);
		DisplayMetrics metrics = new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(metrics);

		pColor1.setMinimumHeight(metrics.heightPixels / 12);
		pColor1.setBackgroundResource(0);
		pColor1.setImageDrawable(new ColorDrawable(order[0]));

		pColor2.setMinimumHeight(metrics.heightPixels / 12);
		pColor2.setBackgroundResource(0);
		pColor2.setImageDrawable(new ColorDrawable(order[2]));

		cColor1.setMinimumHeight(metrics.heightPixels / 12);
		cColor1.setBackgroundResource(0);
		cColor1.setImageDrawable(new ColorDrawable(order[1]));

		cColor2.setMinimumHeight(metrics.heightPixels / 12);
		cColor2.setBackgroundResource(0);
		cColor2.setImageDrawable(new ColorDrawable(order[3]));

		buttons[currentColor].setBackgroundResource(R.drawable.color_border);

		graph.setImage(board.toBitmap());
		LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
				metrics.widthPixels, metrics.widthPixels);
		params.height = metrics.widthPixels;

		graph.setLayoutParams(params);

		graph.setOnTouchListener(graphListener);

		if (currentColor == 1 || currentColor == 3) {
			turn = "CPU";

			if (board.noMoreMoves(order[0], order[1], order[2], order[3])) {
				Log.v("Moves", "No More Moves");
				onCompletion();
			} else {
				handler.postDelayed(new Runnable() {
					public void run() {
						getNextMove();
					}
				}, 1000);
			}
		}
	}

	View.OnTouchListener graphListener = new View.OnTouchListener() {

		public boolean onTouch(View v, MotionEvent event) {
			/*
			 * 1. Make player move. 2. Find the next valid color 3. If it's the
			 * CPU turn, color then repeat 2. 4. If it's the player turn, wait
			 * for player input.
			 */

			// Bug: Game doesn't end

			if (!turn.equals("Player"))
				return false;

			int eventX = (int) event.getX();
			int eventY = (int) event.getY();

			Point cur = new Point(eventX, eventY);

			if (eventX >= board.getWidth() || eventY >= board.getHeight())
				return false;

			if (board.isValidEnd(cur, order[currentColor])) {
				turn = "CPU";
				playerScore += board.fillRegion(cur, order[currentColor]);
				graph.drawBitmap(board.toBitmap());
				yourScore.setText("You: "
						+ (int) (playerScore / ((double) numPixs) * 1000));
				moveCursor();
				currentColor = (currentColor + 1) % 4;

				if (board.noMoreMoves(order[0], order[1], order[2], order[3])) {
					Log.v("Moves", "No More Moves");
					onCompletion();
					return false;
				}

				handler.postDelayed(new Runnable() {
					public void run() {
						getNextMove();
					}
				}, 1000);
			} else {
				if (invalid == null)
					invalid = Toast.makeText(VersusAI.this, "Invalid Coloring",
							Toast.LENGTH_SHORT);
				invalid.show();
			}

			return false;
		}
	};

	private void moveCursor() {
		buttons[currentColor].setBackgroundResource(0);
		buttons[(currentColor + 1) % 4]
				.setBackgroundResource(R.drawable.color_border);
	}

	private boolean getNextMove() {
		int delay = 0;
		if (board.noMoreMoves(order[currentColor])) {
			moveCursor();
			currentColor = (currentColor + 1) % 4;

			if (board.noMoreMoves(order[currentColor])) {
				handler.postDelayed(new Runnable() {
					public void run() {
						getNextMove();
					}
				}, 1000);

				return false;
			}

			delay = 1000;
		}

		if (currentColor == 0 || currentColor == 2) {
			turn = "Player";
		} else {
			handler.postDelayed(new Runnable() {
				public void run() {
					makeAIMove();
					graph.drawBitmap(board.toBitmap());
					moveCursor();
					currentColor = (currentColor + 1) % 4;
					if (board.noMoreMoves(order[0], order[1], order[2],
							order[3])) {
						onCompletion();
						return;
					}

					handler.postDelayed(new Runnable() {
						public void run() {
							getNextMove();
						}
					}, 1000);
				}
			}, delay);
		}

		return false;
	}

	private boolean makeAIMove() {
		AIMove computerMove = new AIMove(board, order, currentColor,
				VersusAI.this);

		Point nextMove = computerMove.makeMove();

		if (nextMove == null)
			return false;

		if (nextMove != null) {
			aiScore += board.fillRegion(nextMove, order[currentColor]);
			computerScore.setText((int) (aiScore / ((double) numPixs) * 1000)
					+ " :CPU");
		}

		return true;
	}

	private void onCompletion() {

		state = null;
		board = null;

		AlertDialog.Builder notice = new AlertDialog.Builder(VersusAI.this);

		aiScore = (int) (aiScore / ((double) numPixs) * 1000);
		playerScore = (int) (playerScore / ((double) numPixs) * 1000);

		// Fix the options here
		notice.setTitle("Game Over!")
				.setMessage(
						"AI Score: " + aiScore + "\n" + "Your Score: "
								+ playerScore)
				.setNegativeButton("Continue",
						new DialogInterface.OnClickListener() {

							public void onClick(DialogInterface dialog,
									int which) {
								app.setVSAIBoard(null);
								board = null;
								finish();

							}
						}).setCancelable(false).show();
	}

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

	private void newGame() {
		Intent newVSAIGame = new Intent(VersusAI.this, LoadingSplash.class);
		newVSAIGame.putExtra("type", 1);
		newVSAIGame.putExtra("regions", 25);
		startActivity(newVSAIGame);

		app.setVSAIBoard(null);
		board = null;
		finish();
	}

	private void updateGameState() {
		handler.removeCallbacksAndMessages(null);
		if (state != null) {
			state.setBoard(board);
			state.setAIScore(aiScore);
			state.setCurrentColor(currentColor);
			state.setPlayerScore(playerScore);
			app.setVSAIBoard(state);
		} else
			app.setVSAIBoard(null);
	}

	protected void onPause() {
		super.onPause();
		updateGameState();
	}
}
