package com.carlosrezende.clientes;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Toast;

import com.carlosrezende.clientes.CRUD.Delete;
import com.carlosrezende.clientes.CRUD.Read;

import java.util.ArrayList;

public class TelaRemoverCliente extends AppCompatActivity
{
    private ListView listView;
    private Read read;
    private Delete delete;
    private ArrayList<Cliente> lista;
    private String[] listValue;
    private ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tela_remover_cliente);


        read = new Read(getApplicationContext());

        lista = new ArrayList<>();
        lista = read.getClientes();
        listValue = new String[lista.size()];

        int count = 0;
        for (Cliente cliente: lista)
        {
            listValue[count] = "ID " + cliente.getCodigo() + " - " + cliente.getNome();
            count++;
        }

        listView = (ListView)findViewById(R.id.lstRemoverClientes);

        adapter = new ArrayAdapter<String>(this, R.layout.cliente_remover, R.id.txtNomeRemover, listValue);

        listView.setAdapter(adapter);

        // ListView on item selected listener.
        listView.setOnItemClickListener(new OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id)
            {
                String string = parent.getAdapter().getItem(position).toString();
                String[] parts = string.split(" ");
                String posRemove = parts[1].trim();

                delete = new Delete(getApplicationContext());

                delete.deleteCliente(Integer.parseInt(posRemove));

                atualizarLista();

                Toast.makeText(getApplicationContext(), R.string.msg_cli_removido_com_sucesso, Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void atualizarLista(){
        startActivity(new Intent( this, TelaRemoverCliente.class));
    }

}
