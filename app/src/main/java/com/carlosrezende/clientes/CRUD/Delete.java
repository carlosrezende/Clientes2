package com.carlosrezende.clientes.CRUD;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.carlosrezende.clientes.Cliente;

public class Delete extends SQLiteOpenHelper{

    private static final String NOME_BANCO     = "DATABASE";
    private static final int    VERSAO_BANCO   = 1;
    private static final String TABELA_CLIENTE = "CLIENTE";

    private static final String PATH_BANCO = "data/user/0/com.carlosrezende.clientes/databases/DATABASE";
    private Context mContext;
    private SQLiteDatabase db;

    public Delete(Context context)
    {
        super(context, NOME_BANCO, null, VERSAO_BANCO);
        this.mContext = context;
        db = getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) { }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) { }

    public boolean deleteTable()
    {
        openDB();

        String deleteTable = "DROP TABLE IF EXISTS " + TABELA_CLIENTE;

        try
        {
            db.execSQL(deleteTable);

            return true;
        }
        catch (Exception e)
        {
            e.printStackTrace();

            return false;
        }
        finally
        {
            db.close();
        }
    }

    public boolean deleteCliente(Cliente cliente)
    {
        openDB();

        String deleteCliente = "CODIGO = '" + cliente.getCodigo() + "'";

        try
        {
            db.delete(TABELA_CLIENTE, deleteCliente, null);

            return true;
        }
        catch (Exception e)
        {
            e.printStackTrace();

            return false;
        }
        finally
        {
            db.close();
        }
    }

    public boolean deleteCliente(int id)
    {
        openDB();

        String deleteCliente = " CODIGO = '" + Integer.toString(id) + "' ";

        try
        {
            db.delete(TABELA_CLIENTE, deleteCliente, null);

            return true;
        }
        catch (Exception e)
        {
            e.printStackTrace();

            return false;
        }
        finally
        {
            db.close();
        }
    }

    private void openDB()
    {
        if(!db.isOpen())
        {
            db = mContext.openOrCreateDatabase(PATH_BANCO, SQLiteDatabase.OPEN_READWRITE, null);
        }
    }

}
