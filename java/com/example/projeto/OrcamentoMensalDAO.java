package com.example.projeto;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class OrcamentoMensalDAO {

    private SQLiteDatabase db;


    public OrcamentoMensalDAO(Context context) {
        DatabaseHelper dbHelper = new DatabaseHelper(context);
        db = dbHelper.getWritableDatabase();
    }
    public long criarOrcamentoMensal(OrcamentoMensal orcamento) {
        ContentValues values = new ContentValues();
        values.put(DatabaseHelper.COLUMN_ORCAMENTO_USUARIO_ID, orcamento.getUsuarioId());
        values.put(DatabaseHelper.COLUMN_ORCAMENTO_LIMITE, orcamento.getLimite());
        values.put(DatabaseHelper.COLUMN_ORCAMENTO_GASTO_ATUAL, orcamento.getGastoAtual());
        values.put(DatabaseHelper.COLUMN_ORCAMENTO_MES, orcamento.getMes());
        values.put(DatabaseHelper.COLUMN_ORCAMENTO_ANO, orcamento.getAno());

        return db.insert(DatabaseHelper.TABLE_ORCAMENTO, null, values);
    }

    public int editarOrcamentoMensal(OrcamentoMensal orcamento) {
        ContentValues values = new ContentValues();
        values.put(DatabaseHelper.COLUMN_ORCAMENTO_LIMITE, orcamento.getLimite());
        values.put(DatabaseHelper.COLUMN_ORCAMENTO_GASTO_ATUAL, orcamento.getGastoAtual());
        values.put(DatabaseHelper.COLUMN_ORCAMENTO_MES, orcamento.getMes());
        values.put(DatabaseHelper.COLUMN_ORCAMENTO_ANO, orcamento.getAno());

        return db.update(DatabaseHelper.TABLE_ORCAMENTO, values,
                DatabaseHelper.COLUMN_ORCAMENTO_ID + " = ? AND " + DatabaseHelper.COLUMN_ORCAMENTO_USUARIO_ID + " = ?",
                new String[]{String.valueOf(orcamento.getId()), String.valueOf(orcamento.getUsuarioId())});
    }
    public OrcamentoMensal obterOrcamentoMensalPorMesEAno(int usuarioId, int mes, int ano) {
        Cursor cursor = db.rawQuery("SELECT * FROM OrcamentoMensal WHERE " +
                        DatabaseHelper.COLUMN_ORCAMENTO_USUARIO_ID + " = ? AND " +
                        DatabaseHelper.COLUMN_ORCAMENTO_MES + " = ? AND " +
                        DatabaseHelper.COLUMN_ORCAMENTO_ANO + " = ?",
                new String[]{String.valueOf(usuarioId), String.valueOf(mes), String.valueOf(ano)});

        if (cursor.moveToFirst()) {
            OrcamentoMensal orcamento = new OrcamentoMensal();
            orcamento.setId(cursor.getInt(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_ORCAMENTO_ID)));
            orcamento.setUsuarioId(cursor.getInt(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_ORCAMENTO_USUARIO_ID)));
            orcamento.setLimite(cursor.getDouble(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_ORCAMENTO_LIMITE)));
            orcamento.setGastoAtual(cursor.getDouble(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_ORCAMENTO_GASTO_ATUAL)));
            orcamento.setMes(cursor.getInt(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_ORCAMENTO_MES)));
            orcamento.setAno(cursor.getInt(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_ORCAMENTO_ANO)));
            cursor.close();
            return orcamento;
        }
        cursor.close();
        return null;
    }
    public void atualizarGastoAtual(int usuarioId, int mes, int ano, double valorTransacao) {
        OrcamentoMensal orcamento = obterOrcamentoMensalPorMesEAno(usuarioId, mes, ano);
        if (orcamento != null) {
            double novoGastoAtual = orcamento.getGastoAtual() + valorTransacao;
            orcamento.setGastoAtual(novoGastoAtual);

            editarOrcamentoMensal(orcamento);
        }
    }






}
