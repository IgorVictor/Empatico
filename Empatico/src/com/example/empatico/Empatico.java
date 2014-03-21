package com.example.empatico;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.HorizontalScrollView;
import android.widget.ScrollView;
import android.widget.TableLayout;
import android.widget.TableRow;

public class Empatico extends Activity implements View.OnClickListener {

	
		private short maxPerLine;
	
		@Override
		public void onCreate(Bundle savedInstanceState) {
			super.onCreate(savedInstanceState);
			
			maxPerLine = 5;
			int xTam = 200;
			int yTam = 200;
			int top = 0;
			int left = 10;
			int right = 0;
			int bottom = 10;
			int[] buttonImages = prepareImageArray();
			HorizontalScrollView horizontalLayout = prepareHorizontal();
			ScrollView verticalLayout = prepareVertical();
			TableLayout layout = prepareTable();
			mountLayout(horizontalLayout, verticalLayout,
					layout);
			setButtonsOnLayout(xTam, yTam, buttonImages, layout, left, top, right, bottom);
			super.setContentView(horizontalLayout);
		}

		private void setButtonsOnLayout(int xTam, int yTam, int[] buttonImages,
				TableLayout layout, int left, int top, int right, int bottom) {
			int i = 0;
			TableRow tr = new TableRow(this);
			tr.setPadding(left, top, right, bottom);
			
			HelpButton config = createButton(R.drawable.settings,this);
			tr.addView(config);
			while (i<buttonImages.length){
				if(i%maxPerLine == 0){
					layout.addView(tr);
					tr = new TableRow(this);
					tr.setPadding(0, 10, 0, 10);
					HelpButton button = createButton(buttonImages[i],this);
					button.setOnClickListener(this);
					tr.addView(button, xTam,yTam);
					i++;
				}
				else {
					HelpButton button = createButton(buttonImages[i],this);
					button.setOnClickListener(this);
					tr.addView(button, xTam,yTam);
					i++;
				}
			}
			if (!(i%maxPerLine == 0)){
				layout.addView(tr);
			}
		}
		private HelpButton createButton(int imageId, Context context){
			HelpButton b = new HelpButton(context);
			b.setImageResource(imageId);
			return b;
		}

		private void mountLayout(
				HorizontalScrollView horizontalLayout,
				ScrollView verticalLayout, TableLayout layout) {
			horizontalLayout.addView(verticalLayout);
			verticalLayout.addView(layout);
		}

		private TableLayout prepareTable() {
			TableLayout layout = new TableLayout(this);
			layout.setBackgroundResource(R.drawable.tela_back);
			return layout;
		}

		private ScrollView prepareVertical() {
			ScrollView verticalLayout = new ScrollView(this);
			verticalLayout.setFillViewport(true);
			return verticalLayout;
		}

		private HorizontalScrollView prepareHorizontal() {
			HorizontalScrollView horizontalLayout = new HorizontalScrollView(this);
			horizontalLayout.setFillViewport(true);
			return horizontalLayout;
		}

		private int[] prepareImageArray() {
			int[] buttonImages = {R.drawable.banheiro, R.drawable.beber, R.drawable.brincar, R.drawable.comer,
					R.drawable.dormir, R.drawable.mal_estar, R.drawable.sair, R.drawable.triste, R.drawable.vestir};
			return buttonImages;
		} 

		public void onClick(View view) {
	
		}
}
