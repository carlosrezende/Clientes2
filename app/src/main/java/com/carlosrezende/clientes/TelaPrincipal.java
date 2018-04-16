package com.carlosrezende.clientes;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.carlosrezende.clientes.CRUD.Create;

public class TelaPrincipal extends AppCompatActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tela_principal);

        //Criar banco de dados
        Create c = new Create(getApplicationContext());
        c.createTableCliente();
    }

    public void goCadCli(View v)
    {
        startActivity(new Intent(this, TelaCadCli.class));
    }

    public void goListaTodos(View v)
    {
        startActivity(new Intent(this, TelaListaTodos.class));
    }

    public void goRemoverCliente(View v)
    {
        startActivity(new Intent(this, TelaRemoverCliente.class));
    }

    public void goAlterarCliente(View v)
    {
        startActivity(new Intent(this, TelaAlterarCliente.class));
    }
}
