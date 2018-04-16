package com.carlosrezende.clientes;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.carlosrezende.clientes.CRUD.Delete;
import com.carlosrezende.clientes.CRUD.Read;
import com.carlosrezende.clientes.CRUD.Update;

import java.util.ArrayList;

public class TelaAlterarCliente extends AppCompatActivity {

    private ListView listView;
    private Read read;
    private Update update;
    private ArrayList<Cliente> lista;
    private String[] listValue;
    private ArrayAdapter<String> adapter;

    private EditText edtAltNome, edtAltEndereco, edtAltEmail, edtAltTel;
    private Button btnAtualizar;

    private Cliente cliente;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tela_alterar_cliente);

        edtAltNome = (EditText) findViewById(R.id.edtAltNome);
        edtAltEndereco = (EditText) findViewById(R.id.edtAltEndereco);
        edtAltEmail = (EditText) findViewById(R.id.edtAltEmail);
        edtAltTel = (EditText) findViewById(R.id.edtAltTel);

        btnAtualizar = (Button) findViewById(R.id.btnAtualizar);

        btnAtualizar.setEnabled(false);

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

        listView = (ListView)findViewById(R.id.lstAltClientes);

        adapter = new ArrayAdapter<String>(this, R.layout.cliente_alterar, R.id.txtAltNome, listValue);

        listView.setAdapter(adapter);

        // ListView on item selected listener.
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id)
            {
                String string = parent.getAdapter().getItem(position).toString();
                String[] parts = string.split(" ");
                int posAlterar = Integer.parseInt(parts[1].trim());

                cliente = new Cliente();

                for (Cliente c: lista)
                {
                    if(c.getCodigo() == posAlterar)
                    {
                        cliente = c;
                        break;
                    }
                }

                edtAltNome.setText(cliente.getNome());
                edtAltEndereco.setText(cliente.getEndereco());
                edtAltEmail.setText(cliente.getEmail());
                edtAltTel.setText(cliente.getTelefone());

                edtAltNome.requestFocus();

                btnAtualizar.setEnabled(true);

            }
        });
    }

    public void atualizarDados(View view){

        update = new Update(getApplicationContext());

        Cliente cliente2 = new Cliente();

        cliente2.setCodigo(cliente.getCodigo());
        cliente2.setNome(edtAltNome.getText().toString());
        cliente2.setEndereco(edtAltEndereco.getText().toString());
        cliente2.setEmail(edtAltEmail.getText().toString());
        cliente2.setTelefone(edtAltTel.getText().toString());

        if (update.updateCliente(cliente2))
        {
            Toast.makeText(this, R.string.msg_cliente_atualizado_sucesso, Toast.LENGTH_SHORT).show();
            atualizarLista();
            //limparEditTexts();
        }
        else
        {
            Toast.makeText(this, R.string.msg_houve_erro_atualizar_cliente, Toast.LENGTH_SHORT).show();
        }
    }

    public void limparEditTexts() {

        edtAltNome.setText("");
        edtAltEndereco.setText("");
        edtAltEmail.setText("");
        edtAltTel.setText("");

        btnAtualizar.setEnabled(false);
    }

    public void atualizarLista(){
        startActivity(new Intent( this, TelaAlterarCliente.class));
    }
}
