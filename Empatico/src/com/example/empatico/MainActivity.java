package com.example.empatico;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.widget.ArrayAdapter;
import android.widget.GridView;

public class MainActivity extends Activity {

	GridView gridView;
    
    static final String[] numbers = new String[] {

            "Botão1", "Botão2", "Botão3", "Botão4", "Botão5",
            "Botão", "Botão", "Botão", "Botão", "Substituir texto por botão"
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
