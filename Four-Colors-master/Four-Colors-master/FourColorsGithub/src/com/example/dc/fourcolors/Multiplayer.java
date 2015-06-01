package com.example.dc.fourcolors;

import com.facebook.Session;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.widget.TextView;

public class Multiplayer extends FragmentActivity {

	private String name;
	private LoginFragment loginFragment;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		if (savedInstanceState == null) {
			loginFragment = new LoginFragment();
			getSupportFragmentManager().beginTransaction()
					.add(android.R.id.content, loginFragment).commit();
		} else {
			loginFragment = (LoginFragment) getSupportFragmentManager()
					.findFragmentById(android.R.id.content);
		}

	}

	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		Session.getActiveSession().onActivityResult(this, requestCode,
				resultCode, data);
	}
}
