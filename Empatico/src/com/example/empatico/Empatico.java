package com.example.empatico;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.HorizontalScrollView;
import android.widget.ImageButton;
import android.widget.ScrollView;
import android.widget.TableLayout;
import android.widget.TableRow;

public class Empatico extends Activity implements View.OnClickListener {

		@Override
		public void onCreate(Bundle savedInstanceState) {
			super.onCreate(savedInstanceState);

			HorizontalScrollView horizontalLayout = new HorizontalScrollView(this);
			ScrollView verticalLayout = new ScrollView(this);
			TableLayout layout = new TableLayout(this);
			layout.setLayoutParams(new TableLayout.LayoutParams());
			horizontalLayout.addView(verticalLayout);
			verticalLayout.addView(layout);

			layout.setPadding(1,1,1,1);

			for (int f=0; f<=5; f++) {
				TableRow tr = new TableRow(this);
				for (int c=0; c<14; c++) {
					ImageButton b = new ImageButton (this);
					b.setImageResource(R.drawable.ic_launcher);
					b.setOnClickListener(this);
					tr.addView(b, 200,200);
				} // for
				layout.addView(tr);
			} // for
			super.setContentView(horizontalLayout);
		} // ()

		public void onClick(View view) {
	
		}
}
