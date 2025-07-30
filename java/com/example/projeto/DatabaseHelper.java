package com.example.projeto;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "projeto.db";
    private static final int DATABASE_VERSION = 1;

    public static final String TABLE_USUARIO = "Usuario";
    public static final String TABLE_TRANSACAO = "Transacao";
    public static final String TABLE_ORCAMENTO = "OrcamentoMensal";
    public static final String TABLE_META = "Meta";

    public static final String COLUMN_ID = "id";
    public static final String COLUMN_NOME = "nome";
    public static final String COLUMN_EMAIL = "email";
    public static final String COLUMN_SENHA = "senha";

    public static final String COLUMN_TRANSACAO_ID = "id";
    public static final String COLUMN_TRANSACAO_USUARIO_ID = "usuario_id";
    public static final String COLUMN_TRANSACAO_VALOR = "valor";
    public static final String COLUMN_TRANSACAO_DATA = "data";
    public static final String COLUMN_TRANSACAO_CATEGORIA = "categoria";

    public static final String COLUMN_ORCAMENTO_ID = "id";
    public static final String COLUMN_ORCAMENTO_USUARIO_ID = "usuario_id";
    public static final String COLUMN_ORCAMENTO_LIMITE = "limite";
    public static final String COLUMN_ORCAMENTO_GASTO_ATUAL = "gasto_atual";
    public static final String COLUMN_ORCAMENTO_MES = "mes";
    public static final String COLUMN_ORCAMENTO_ANO = "ano";

    public static final String COLUMN_META_ID = "id";
    public static final String COLUMN_META_USUARIO_ID = "usuario_id";
    public static final String COLUMN_META_VALOR = "valor_meta";
    public static final String COLUMN_META_PROGRESSO = "progresso";

    private static final String CREATE_USUARIO_TABLE = "CREATE TABLE " + TABLE_USUARIO + " (" +
            COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            COLUMN_NOME + " TEXT, " +
            COLUMN_EMAIL + " TEXT UNIQUE, " +
            COLUMN_SENHA + " TEXT)";

    private static final String CREATE_TRANSACAO_TABLE = "CREATE TABLE " + TABLE_TRANSACAO + " (" +
            COLUMN_TRANSACAO_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            COLUMN_TRANSACAO_USUARIO_ID + " INTEGER, " +
            COLUMN_TRANSACAO_VALOR + " REAL, " +
            COLUMN_TRANSACAO_DATA + " TEXT, " +
            COLUMN_TRANSACAO_CATEGORIA + " TEXT, " +
            "FOREIGN KEY(" + COLUMN_TRANSACAO_USUARIO_ID + ") REFERENCES " + TABLE_USUARIO + "(" + COLUMN_ID + "))";

    private static final String CREATE_ORCAMENTO_TABLE = "CREATE TABLE " + TABLE_ORCAMENTO + " (" +
            COLUMN_ORCAMENTO_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            COLUMN_ORCAMENTO_USUARIO_ID + " INTEGER, " +
            COLUMN_ORCAMENTO_LIMITE + " REAL, " +
            COLUMN_ORCAMENTO_GASTO_ATUAL + " REAL, " +
            COLUMN_ORCAMENTO_MES + " INTEGER, " +
            COLUMN_ORCAMENTO_ANO + " INTEGER, " +
            "FOREIGN KEY(" + COLUMN_ORCAMENTO_USUARIO_ID + ") REFERENCES " + TABLE_USUARIO + "(" + COLUMN_ID + "))";

    private static final String CREATE_META_TABLE = "CREATE TABLE " + TABLE_META + " (" +
            COLUMN_META_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            COLUMN_META_USUARIO_ID + " INTEGER, " +
            COLUMN_META_VALOR + " REAL, " +
            COLUMN_META_PROGRESSO + " REAL, " +
            "FOREIGN KEY(" + COLUMN_META_USUARIO_ID + ") REFERENCES " + TABLE_USUARIO + "(" + COLUMN_ID + "))";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_USUARIO_TABLE);
        db.execSQL(CREATE_TRANSACAO_TABLE);
        db.execSQL(CREATE_ORCAMENTO_TABLE);
        db.execSQL(CREATE_META_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_META);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_ORCAMENTO);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_TRANSACAO);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USUARIO);
        onCreate(db);
    }

}
