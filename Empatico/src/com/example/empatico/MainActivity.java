package com.example.empatico;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.widget.ArrayAdapter;
import android.widget.GridView;

public class MainActivity extends Activity {

	GridView gridView;
    
    static final String[] numbers = new String[] {

            "Bot�o1", "Bot�o2", "Bot�o3", "Bot�o4", "Bot�o5",
            "Bot�o", "Bot�o", "Bot�o", "Bot�o", "Substituir texto por bot�o"
        };
 
    @Override
    public void onCreate(Bundle savedInstanceState) {
    
        super.onCreate(savedInstanceState);
 
        setContentView(R.layout.activity_main);
 
        gridView = (GridView) findViewById(R.id.gridview1);  
 
      // Create adapter to set value for grid view
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, numbers);
 
        gridView.setAdapter(adapter);
 
    }

}
