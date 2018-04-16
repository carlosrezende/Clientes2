package com.carlosrezende.clientes.CRUD;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class Create extends SQLiteOpenHelper{

    private static final String NOME_BANCO     = "DATABASE";
    private static final int    VERSAO_BANCO   = 1;
    private static final String TABELA_CLIENTE = "CLIENTE";

    private static final String PATH_BANCO = "data/user/0/com.carlosrezende.clientes/databases/DATABASE";
    private Context mContext;
    private SQLiteDatabase db;

    public Create(Context context)
    {
        super(context, NOME_BANCO, null, VERSAO_BANCO);
        this.mContext = context;
        db = getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) { }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) { }

    public boolean createTableCliente()
    {
        openDB();

        String createTable = "CREATE TABLE IF NOT EXISTS " + TABELA_CLIENTE + " ("
                + " CODIGO INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, "
                + " NOME TEXT, "
                + " ENDERECO TEXT, "
                + " EMAIL TEXT, "
                + " TELEFONE TEXT )";

        try
        {
            db.execSQL(createTable);
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
