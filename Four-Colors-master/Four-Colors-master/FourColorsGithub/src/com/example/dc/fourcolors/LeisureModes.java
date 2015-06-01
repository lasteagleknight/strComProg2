package com.example.dc.fourcolors;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class LeisureModes extends Activity {

	private Button colorIt;
	private Button vsAI;
	private Button campaign;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.splayer_modes);

		colorIt = (Button) this.findViewById(R.id.sMode_colorIt);
		vsAI = (Button) this.findViewById(R.id.sMode_vAI);
		campaign = (Button) this.findViewById(R.id.sMode_campaign);

		colorIt.setOnClickListener(colorItListener);
		vsAI.setOnClickListener(vsAIListener);
		campaign.setOnClickListener(campaignListener);
	}

	View.OnClickListener colorItListener = new View.OnClickListener() {

		public void onClick(View v) {
			BoardApplication app = (BoardApplication) LeisureModes.this
					.getApplication();

			if (app.ongoingSPGame()) {
				Intent colorItIntent = new Intent(LeisureModes.this,
						ColorIt.class);
				startActivity(colorItIntent);
			} else {
				Intent colorItIntent = new Intent(LeisureModes.this,
						ColorItModes.class);
				startActivity(colorItIntent);
			}
		}
	};

	View.OnClickListener vsAIListener = new View.OnClickListener() {

		public void onClick(View v) {
			BoardApplication app = (BoardApplication) LeisureModes.this
					.getApplication();

			if (app.ongoingAIGame()) {
				Log.v("Game", "Ongoing VSAI");
				Intent vsAIIntent = new Intent(LeisureModes.this,
						VersusAI.class);
				startActivity(vsAIIntent);
			} else {
				Intent vsAIIntent = new Intent(LeisureModes.this,
						LoadingSplash.class);

				// vsAI has gameType 1
				int gameType = 1;
				vsAIIntent.putExtra("type", gameType);
				vsAIIntent.putExtra("regions", 25);
				startActivity(vsAIIntent);
			}

		}
	};

	View.OnClickListener campaignListener = new View.OnClickListener() {

		public void onClick(View v) {
			// TODO Auto-generated method stub

		}
	};

}
