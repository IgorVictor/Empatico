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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.Toast;


public class Config extends Activity{

	Component acaoEditar = null;
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
		Button criarAcao = (Button) findViewById(R.id.criarNovaAcao);
		Button excluirAcao = (Button) findViewById(R.id.excluirAcao);
		EditText nomeDependente = (EditText) findViewById(R.id.nomeDependente);
		EditText novaSenha = (EditText) findViewById(R.id.novaSenha);
		final EditText novaMsg = (EditText) findViewById(R.id.helpText);
		final EditText novaImg = (EditText) findViewById(R.id.imagePath);
		//final StableArrayAdapter adapter = new StableArrayAdapter(this,R.layout.text, components);
		
		ArrayAdapter<Component> adapter = new ArrayAdapter<Component>(this, android.R.layout.simple_list_item_checked, components );
		
		listaAcoes.setAdapter(adapter);
		
		listaAcoes.setOnItemClickListener(new AdapterView.OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> adapter, View arg1, int posicao,
					long arg3) {
								
				acaoEditar = (Component) adapter.getItemAtPosition(posicao);
				novaMsg.setText(acaoEditar.getMsgToSend());
				novaImg.setText(acaoEditar.getImagePath());
			}
			
		});
		
	}
		
	private ScrollView prepareVertical() {
		ScrollView verticalLayout = new ScrollView(this);
		verticalLayout.setFillViewport(true);
		return verticalLayout;
	}
}
