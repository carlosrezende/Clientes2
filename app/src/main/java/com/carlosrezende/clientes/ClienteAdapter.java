package com.carlosrezende.clientes;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class ClienteAdapter extends ArrayAdapter<Cliente> {

    private Context context;
    private ArrayList<Cliente> lista;

    public ClienteAdapter(Context context, ArrayList<Cliente> lista)
    {
        super(context, 0, lista);
        this.context = context;
        this.lista = lista;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        Cliente clientePosicao = this.lista.get(position);

        convertView = LayoutInflater.from(this.context).inflate(R.layout.cliente, null);

        TextView txtNome = (TextView) convertView.findViewById(R.id.txtNomeAdapter);
        txtNome.setText(clientePosicao.getNome());

        TextView txtEndereco = (TextView) convertView.findViewById(R.id.txtEndAdapter);
        txtEndereco.setText(clientePosicao.getEndereco());

        TextView txtEmail = (TextView) convertView.findViewById(R.id.txtEmailAdapter);
        txtEmail.setText(clientePosicao.getEmail());

        TextView txtTelefone = (TextView) convertView.findViewById(R.id.txtTelAdapter);
        txtTelefone.setText(clientePosicao.getTelefone());

        TextView txtId = (TextView) convertView.findViewById(R.id.txtIdAdapter);
        txtId.setText(Integer.toString(clientePosicao.getCodigo()));

        return convertView;
    }
}
