package com.example.empatico;

import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.ImageView;

public class MainActivity extends Activity {
 
	private ImageButton[] mThumbIds;
 
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ImageButton button = new ImageButton(this);
        ImageButton button2 = new ImageButton(this);
    	button.setImageResource(R.drawable.ic_launcher);
    	ImageButton[] buttons = {button, button2, button, button2, button, button2, button2, button, button};
    	mThumbIds = buttons;
 
        GridView gridview = (GridView) findViewById(R.id.gridview1);
        gridview.setAdapter(new MyAdapter(this));
        gridview.setNumColumns(4);
    }
 
  public class MyAdapter extends BaseAdapter {
 
        private Context mContext;
 
        public MyAdapter(Context c) {
            mContext = c;
        }
 
        @Override
        public int getCount() {
            return mThumbIds.length;
        }
 
        @Override
        public Object getItem(int arg0) {
            return mThumbIds[arg0];
        }
 
        @Override
        public long getItemId(int arg0) {
            return arg0;
        }
 
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
 
            View grid;
 
            if(convertView==null){
                grid = new View(mContext);
                LayoutInflater inflater=getLayoutInflater();
                grid=inflater.inflate(R.layout.buttonlayout, parent, false);
            }else{
                grid = (View)convertView;
            }
           // ImageButton imageButton = (ImageButton)grid.findViewById(R.id.imageButton1);
          //  imageB.setImageResource(mThumbIds[position]);
 
            return grid;
        }
    }

}
