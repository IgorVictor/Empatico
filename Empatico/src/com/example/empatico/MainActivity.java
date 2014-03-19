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
import android.widget.GridView;
import android.widget.ImageView;

public class MainActivity extends Activity {

    private Integer[] mThumbIds = {R.drawable.ic_launcher,
    		R.drawable.ic_launcher, R.drawable.ic_launcher,
    		R.drawable.ic_launcher, R.drawable.ic_launcher,
    		R.drawable.ic_launcher, R.drawable.ic_launcher,
    		R.drawable.ic_launcher, R.drawable.ic_launcher,
    		R.drawable.ic_launcher, R.drawable.ic_launcher};
 
 
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
 
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
 
            ImageView imageView = (ImageView)grid.findViewById(R.id.image);
            imageView.setImageResource(mThumbIds[position]);
 
            return grid;
        }
    }

}
