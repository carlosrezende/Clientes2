package com.carlosrezende.clientes;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.carlosrezende.clientes.CRUD.Delete;
import com.carlosrezende.clientes.CRUD.Update;

public class TelaCadCli extends AppCompatActivity
{
    EditText edtNome, edtEndereco, edtEmail, edtTelefone;
    Button btnCadastrar;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tela_cad_cli);

        edtNome     = (EditText) findViewById(R.id.edtNome);
        edtEndereco = (EditText) findViewById(R.id.edtEndereco);
        edtEmail    = (EditText) findViewById(R.id.edtEmail);
        edtTelefone = (EditText) findViewById(R.id.edtTelefone);

        btnCadastrar = (Button) findViewById(R.id.btnCadastrar);
    }

    public void insertCliente(View view){

        Cliente cliente = new Cliente();

        cliente.setNome    (edtNome.getText().toString());
        cliente.setEndereco(edtEndereco.getText().toString());
        cliente.setEmail   (edtEmail.getText().toString());
        cliente.setTelefone(edtTelefone.getText().toString());

        Update update = new Update(getApplicationContext());

        if (update.insertCliente(cliente))
        {
            Toast.makeText(this, R.string.msg_cliente_cadastrado_com_sucesso, Toast.LENGTH_SHORT).show();
            limparEditTexts();
        }
        else
        {
            Toast.makeText(this, R.string.msg_houve_erro_cadastrar_cliente , Toast.LENGTH_SHORT).show();
        }
    }

    public void updateCliente(View view){

        Cliente cliente = new Cliente();

        cliente.setNome    (edtNome.getText().toString());
        cliente.setEndereco(edtEndereco.getText().toString());
        cliente.setEmail   (edtEmail.getText().toString());
        cliente.setTelefone(edtTelefone.getText().toString());

        Update update = new Update(getApplicationContext());

        if (update.updateCliente(cliente))
        {
            Toast.makeText(this, R.string.msg_cliente_atualizado_sucesso, Toast.LENGTH_SHORT).show();
            limparEditTexts();
        }
        else
        {
            Toast.makeText(this, R.string.msg_houve_erro_atualizar_cliente , Toast.LENGTH_SHORT).show();
        }
    }

    public void deleteCliente(View view){

        Cliente cliente = new Cliente();

        //cliente.setCodigo();
        cliente.setNome    (edtNome.getText().toString());
        cliente.setEndereco(edtEndereco.getText().toString());
        cliente.setEmail   (edtEmail.getText().toString());
        cliente.setTelefone(edtTelefone.getText().toString());

        Delete delete = new Delete(getApplicationContext());

        if (delete.deleteCliente(cliente))
        {
            Toast.makeText(this, R.string.msg_cliente_removido_sucesso, Toast.LENGTH_SHORT).show();
            limparEditTexts();
        }
        else
        {
            Toast.makeText(this, R.string.msg_houve_erro_remover_cliente , Toast.LENGTH_SHORT).show();
        }
    }

    public void deleteTable(View view)
    {
        Delete delete = new Delete(getApplicationContext());

        if (delete.deleteTable())
        {
            Toast.makeText(this, R.string.msg_todos_clientes_removidos_sucesso, Toast.LENGTH_SHORT).show();
            limparEditTexts();
        }
        else
        {
            Toast.makeText(this, R.string.msg_houve_erro_remover_todos_clientes , Toast.LENGTH_SHORT).show();
        }
    }

    public void limparEditTexts() {

        edtNome.setText("");
        edtEndereco.setText("");
        edtEmail.setText("");
        edtTelefone.setText("");

        edtNome.requestFocus();
    }
}
