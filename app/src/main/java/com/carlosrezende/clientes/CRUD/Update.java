package com.carlosrezende.clientes.CRUD;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.carlosrezende.clientes.Cliente;

public class Update extends SQLiteOpenHelper{

    private static final String NOME_BANCO     = "DATABASE";
    private static final int    VERSAO_BANCO   = 1;
    private static final String TABELA_CLIENTE = "CLIENTE";

    private static final String PATH_BANCO = "data/user/0/com.carlosrezende.clientes/databases/DATABASE";
    private Context mContext;
    private SQLiteDatabase db;

    public Update(Context context)
    {
        super(context, NOME_BANCO, null, VERSAO_BANCO);
        this.mContext = context;
        db = getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) { }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) { }

    public boolean insertCliente(Cliente c)
    {
        openDB();

        try
        {
            ContentValues cv = new ContentValues();

            cv.put("NOME", c.getNome());
            cv.put("ENDERECO", c.getEndereco());
            cv.put("EMAIL", c.getEmail());
            cv.put("TELEFONE", c.getTelefone());

            db.insert(TABELA_CLIENTE, null, cv);

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

    public boolean updateCliente(Cliente c)
    {
        openDB();

        try
        {
            ContentValues cv = new ContentValues();

            String[] parametros = new String[1];
            parametros[0] = String.valueOf(c.getCodigo());

            cv.put("NOME", c.getNome());
            cv.put("ENDERECO", c.getEndereco());
            cv.put("EMAIL", c.getEmail());
            cv.put("TELEFONE", c.getTelefone());

            db.update(TABELA_CLIENTE, cv, "CODIGO = ?", parametros);

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
