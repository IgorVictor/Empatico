package com.example.empatico;
import java.util.ArrayList;
import java.util.List;

import com.example.empatico.models.Component;
import com.example.empatico.utils.JSONUtils;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.ScrollView;


public class Config extends Activity{

	private List<Component> components;
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
		super.setContentView(R.layout.configscroll);
		components = JSONUtils.generateComponents(this);

		Button sair = (Button) findViewById(R.id.sair);
		sair.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				finish();
			}
		});
		Button salvar = (Button) findViewById(R.id.salvarAltera);
		salvar.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				finish();
			}
		});
		ListView listaAcoes = (ListView) findViewById(R.id.listaAcoes);
		final StableArrayAdapter adapter = new StableArrayAdapter(this,R.layout.text, components);
		listaAcoes.setAdapter(adapter);
		/*		listaAcoes.setOnItemClickListener(new AdapterView.OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, final View view,
					int position, long id) {
				final String item = (String) parent.getItemAtPosition(position);
				list.remove(item);
				adapter.notifyDataSetChanged();
			}

		});*/
		///////////////////////////////////
		Button criarAcao = (Button) findViewById(R.id.criarNovaAcao);
		Button excluirAcao = (Button) findViewById(R.id.excluirAcao);
		EditText nomeDependente = (EditText) findViewById(R.id.nomeDependente);
		EditText novaSenha = (EditText) findViewById(R.id.novaSenha);
	}
	private ScrollView prepareVertical() {
		ScrollView verticalLayout = new ScrollView(this);
		verticalLayout.setFillViewport(true);
		return verticalLayout;
	}
}
