package com.example.projeto;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class UsuarioDAO {

    private SQLiteDatabase db;

    public UsuarioDAO(Context context) {
        DatabaseHelper dbHelper = new DatabaseHelper(context);
        db = dbHelper.getWritableDatabase();
    }

    public long inserirUsuario(Usuario usuario) {
        ContentValues values = new ContentValues();
        values.put(DatabaseHelper.COLUMN_NOME, usuario.getNome());
        values.put(DatabaseHelper.COLUMN_EMAIL, usuario.getEmail());
        values.put(DatabaseHelper.COLUMN_SENHA, usuario.getSenha());

        return db.insert(DatabaseHelper.TABLE_USUARIO, null, values);
    }

    public Usuario buscarUsuario(String email, String senha) {
        String query = "SELECT * FROM " + DatabaseHelper.TABLE_USUARIO +
                " WHERE " + DatabaseHelper.COLUMN_EMAIL + " = ?" +
                " AND " + DatabaseHelper.COLUMN_SENHA + " = ?";
        Cursor cursor = db.rawQuery(query, new String[]{email, senha});

        if (cursor.moveToFirst()) {
            Usuario usuario = new Usuario();
            usuario.setId(cursor.getInt(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_ID)));
            usuario.setNome(cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_NOME)));
            usuario.setEmail(cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_EMAIL)));
            usuario.setSenha(cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_SENHA)));
            cursor.close();
            return usuario;
        }
        cursor.close();
        return null;
    }

    public int atualizarUsuario(Usuario usuario) {
        ContentValues values = new ContentValues();
        values.put(DatabaseHelper.COLUMN_NOME, usuario.getNome());
        values.put(DatabaseHelper.COLUMN_EMAIL, usuario.getEmail());
        values.put(DatabaseHelper.COLUMN_SENHA, usuario.getSenha());

        return db.update(DatabaseHelper.TABLE_USUARIO, values,
                DatabaseHelper.COLUMN_ID + " = ?",
                new String[]{String.valueOf(usuario.getId())});
    }

    public Usuario buscarUsuarioPorId(int id) {
        String query = "SELECT * FROM " + DatabaseHelper.TABLE_USUARIO +
                " WHERE " + DatabaseHelper.COLUMN_ID + " = ?";
        Cursor cursor = db.rawQuery(query, new String[]{String.valueOf(id)});

        if (cursor.moveToFirst()) {
            Usuario usuario = new Usuario();
            usuario.setId(cursor.getInt(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_ID)));
            usuario.setNome(cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_NOME)));
            usuario.setEmail(cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_EMAIL)));
            usuario.setSenha(cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_SENHA)));
            cursor.close();
            return usuario;
        }
        cursor.close();
        return null;
    }

    public void close() {
        db.close();
    }
}
