package com.example.empatico;

import java.util.HashMap;
import java.util.List;

import com.example.empatico.models.Component;

import android.content.Context;
import android.util.Log;
import android.widget.ArrayAdapter;

class StableArrayAdapter extends ArrayAdapter<String> {

	List<Component> objectsIntern;

	public StableArrayAdapter(Context context, int textViewResourceId, List<Component> objects) {
		super(context, textViewResourceId);
		objectsIntern = objects;
		for (int i = 0; i < objects.size(); ++i) {
			this.add(objects.get(i).getMsgToSend());
		}
	}
	@Override
	public long getItemId(int position) {
		String item = getItem(position);
		for (int i = 0; i < objectsIntern.size(); ++i) {
			if (objectsIntern.get(i).equals(item)){
				int j = objectsIntern.get(i).getId();
				Log.d("ListView", "Objeto selecionado. ID: " + j);
				return j;
			}
		}
		return 0;
	}

	@Override
	public boolean hasStableIds() {
		return true;
	}

}