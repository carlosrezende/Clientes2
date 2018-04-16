package com.carlosrezende.clientes;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.carlosrezende.clientes.CRUD.Read;

import java.util.ArrayList;

public class TelaListaTodos extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tela_lista_todos);

        ArrayList<Cliente> lista = new ArrayList<Cliente>();

        Read read = new Read(getApplicationContext());

        lista = read.getClientes();

        ClienteAdapter clienteAdapter = new ClienteAdapter(getApplicationContext(), lista);

        ListView listView = (ListView) findViewById(R.id.lstClientes);

        listView.setAdapter(clienteAdapter);
    }
}
