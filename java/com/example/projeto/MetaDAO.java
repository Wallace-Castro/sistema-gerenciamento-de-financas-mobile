package com.example.projeto;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.Cursor;


import java.util.ArrayList;
import java.util.List;

public class MetaDAO {

    private SQLiteDatabase db;

    public MetaDAO(Context context) {
        DatabaseHelper dbHelper = new DatabaseHelper(context);
        db = dbHelper.getWritableDatabase();
    }

    public long criarMeta(Meta meta) {
        ContentValues values = new ContentValues();
        values.put(DatabaseHelper.COLUMN_META_USUARIO_ID, meta.getUsuarioId());
        values.put(DatabaseHelper.COLUMN_META_VALOR, meta.getValorMeta());
        values.put(DatabaseHelper.COLUMN_META_PROGRESSO, meta.getProgresso());

        return db.insert("Meta", null, values);
    }

    public int editarMeta(Meta meta) {
        ContentValues values = new ContentValues();
        values.put(DatabaseHelper.COLUMN_META_VALOR, meta.getValorMeta());
        values.put(DatabaseHelper.COLUMN_META_PROGRESSO, meta.getProgresso());

        return db.update("Meta", values, ""+DatabaseHelper.COLUMN_META_ID+" = ? AND "+ DatabaseHelper.COLUMN_META_USUARIO_ID+ " = ?",
                new String[]{String.valueOf(meta.getId()), String.valueOf(meta.getUsuarioId())});
    }
    public List<Meta> listarMetas(int usuarioId) {
        List<Meta> metas = new ArrayList<>();
        Cursor cursor = db.rawQuery("SELECT * FROM Meta WHERE " +DatabaseHelper.COLUMN_META_USUARIO_ID+ " = ?",
                new String[]{String.valueOf(usuarioId)});
        if (cursor.moveToFirst()) {
            do {
                Meta meta = new Meta();
                meta.setId(cursor.getInt(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_META_ID)));
                meta.setUsuarioId(cursor.getInt(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_META_USUARIO_ID)));
                meta.setValorMeta(cursor.getDouble(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_META_VALOR)));
                meta.setProgresso(cursor.getDouble(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_META_PROGRESSO)));
                metas.add(meta);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return metas;
    }


    public void close() {
        if (db != null && db.isOpen()) {
            db.close();
        }
    }
}
