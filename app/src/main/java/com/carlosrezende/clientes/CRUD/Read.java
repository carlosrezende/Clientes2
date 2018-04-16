package com.carlosrezende.clientes.CRUD;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.carlosrezende.clientes.Cliente;

import java.util.ArrayList;

public class Read extends SQLiteOpenHelper{

    private static final String NOME_BANCO     = "DATABASE";
    private static final int    VERSAO_BANCO   = 1;
    private static final String TABELA_CLIENTE = "CLIENTE";

    private static final String PATH_BANCO = "data/user/0/com.carlosrezende.clientes/databases/DATABASE";
    private Context mContext;
    private SQLiteDatabase db;

    public Read(Context context)
    {
        super(context, NOME_BANCO, null, VERSAO_BANCO);
        this.mContext = context;
        db = getReadableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) { }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) { }

    public ArrayList<Cliente> getClientes()
    {
        openDB(); //Abrir conexão com o banco

        ArrayList<Cliente> cArray = new ArrayList<>();

        String sqlGetCli = "SELECT * FROM " + TABELA_CLIENTE;

        try
        {
            Cursor c = db.rawQuery(sqlGetCli, null);

            if(c.moveToFirst())
            {
                do{
                    Cliente cliente = new Cliente();

                    //Pegar clientes do cursor e colocá-los no Array
                    cliente.setCodigo(c.getInt(0));
                    cliente.setNome(c.getString(1));
                    cliente.setEndereco(c.getString(2));
                    cliente.setEmail(c.getString(3));
                    cliente.setTelefone(c.getString(4));

                    cArray.add(cliente);

                }while (c.moveToNext());
                c.close();
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return null;
        }
        finally
        {
            db.close();
        }
        return cArray;
    }

    private void openDB()
    {
        if(!db.isOpen())
        {
            db = mContext.openOrCreateDatabase(PATH_BANCO, SQLiteDatabase.OPEN_READWRITE, null);
        }
    }

}
