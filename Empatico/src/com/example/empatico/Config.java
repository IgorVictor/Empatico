package com.example.empatico;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


public class Config extends Activity{

		public void onCreate(Bundle savedInstanceState) {
			super.onCreate(savedInstanceState);
			super.setContentView(R.layout.config);
			this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
			Button sair = (Button) findViewById(R.id.sair);
			sair.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
				finish();
				}
			});
		}
	
}
