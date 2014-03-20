package com.example.empatico;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TableLayout;
import android.widget.TableRow;

public class Empatico extends Activity implements View.OnClickListener {

	
		private short maxPerLine;
	
		@Override
		public void onCreate(Bundle savedInstanceState) {
			super.onCreate(savedInstanceState);
			
			maxPerLine = 5;
			int[] buttonImages = {R.drawable.banheiro, R.drawable.beber, R.drawable.brincar, R.drawable.comer,
					R.drawable.dormir, R.drawable.mal_estar, R.drawable.sair, R.drawable.triste, R.drawable.vestir};
			LinearLayout layoutInicial = new LinearLayout(this);
			HorizontalScrollView horizontalLayout = new HorizontalScrollView(this);
			ScrollView verticalLayout = new ScrollView(this);
			TableLayout layout = new TableLayout(this);
			layout.setBackgroundResource(R.drawable.tela_back);
			horizontalLayout.addView(verticalLayout);
			verticalLayout.addView(layout);
			ImageView image = new ImageView(this);
			image.setImageResource(R.drawable.tela_inicial);
			layoutInicial.addView(image);

			layout.setPadding(1,1,1,1);
			int i = 0;
			TableRow tr = new TableRow(this);
			while (i<buttonImages.length){
				if(i%maxPerLine == 0){
					layout.addView(tr);
					tr = new TableRow(this);
					HelpButton b = new HelpButton(this);
					b.setImageResource(buttonImages[i]);
					b.setOnClickListener(this);
					tr.addView(b, 200,200);
					i++;
				}
				else {
					HelpButton b = new HelpButton (this);
					b.setImageResource(buttonImages[i]);
					b.setOnClickListener(this);
					tr.addView(b, 200,200);
					i++;
				}
			}
			if (!(i%maxPerLine == 0)){
				layout.addView(tr);
			}
			
			
/*			for (int f=0; f<2; f++) {
				TableRow tr = new TableRow(this);
				for (int c=0; c<5; c++) {
					HelpButton b = new HelpButton (this);
					b.setImageResource(buttonImages[i]);
					b.setOnClickListener(this);
					tr.addView(b, 200,200);
					if (i<buttonImages.length-1){
						i++;
					}
				} // for
				layout.addView(tr);
			} // for
*/			super.setContentView(layoutInicial);
			super.setContentView(horizontalLayout);
		} // ()

		public void onClick(View view) {
	
		}
}
