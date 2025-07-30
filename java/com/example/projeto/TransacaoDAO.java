package com.example.projeto;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import java.util.ArrayList;
import java.util.List;

public class TransacaoDAO {
    private SQLiteDatabase db;
    private OrcamentoMensalDAO orcamentoMensalDAO;
    private MetaDAO metaDAO;

    public TransacaoDAO(Context context) {
        DatabaseHelper dbHelper = new DatabaseHelper(context);
        db = dbHelper.getWritableDatabase();
        orcamentoMensalDAO = new OrcamentoMensalDAO(context);
        metaDAO = new MetaDAO(context);
    }

    public long criarTransacao(Transacao transacao) {
        ContentValues values = new ContentValues();
        values.put(DatabaseHelper.COLUMN_TRANSACAO_USUARIO_ID, transacao.getUsuarioId());
        values.put(DatabaseHelper.COLUMN_TRANSACAO_VALOR, transacao.getValor());
        values.put(DatabaseHelper.COLUMN_TRANSACAO_DATA, transacao.getData());
        values.put(DatabaseHelper.COLUMN_TRANSACAO_CATEGORIA, transacao.getCategoria());
        long id = db.insert("Transacao", null, values);
        atualizarOrcamentoMensal(transacao);
            if ("meta".equalsIgnoreCase(transacao.getCategoria())) {
                atualizarProgressoMeta(transacao);
            }
        return id;
    }

    private void atualizarOrcamentoMensal(Transacao transacao) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Calendar calendar = Calendar.getInstance();
        try {
            calendar.setTime(sdf.parse(transacao.getData()));
        } catch (ParseException e) {
            e.printStackTrace();
            return;
        }
        int mes = calendar.get(Calendar.MONTH) + 1;
        int ano = calendar.get(Calendar.YEAR);
        orcamentoMensalDAO.atualizarGastoAtual(transacao.getUsuarioId(), mes, ano, transacao.getValor());
    }
    private void atualizarProgressoMeta(Transacao transacao) {
        List<Meta> metas = metaDAO.listarMetas(transacao.getUsuarioId());
        for (Meta meta : metas) {
            if (meta.getProgresso() < meta.getValorMeta()) {
                double novoProgresso = meta.getProgresso() + transacao.getValor();
                if (novoProgresso > meta.getValorMeta()) {
                    novoProgresso = meta.getValorMeta(); // Trava o progresso no limite
                }
                meta.setProgresso(novoProgresso);
                metaDAO.editarMeta(meta);
                break;
            }
        }
    }

    public List<Transacao> listarTransacoes(int usuarioId) {
        List<Transacao> transacoes = new ArrayList<>();
        Cursor cursor = db.rawQuery("SELECT * FROM Transacao WHERE "+DatabaseHelper.COLUMN_TRANSACAO_USUARIO_ID+" = ?",
                new String[]{String.valueOf(usuarioId)});
        if (cursor.moveToFirst()) {
            do {
                Transacao transacao = new Transacao();
                transacao.setId(cursor.getInt(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_TRANSACAO_ID)));
                transacao.setUsuarioId(cursor.getInt(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_TRANSACAO_USUARIO_ID)));
                transacao.setValor(cursor.getDouble(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_TRANSACAO_VALOR)));
                transacao.setData(cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_TRANSACAO_DATA)));
                transacao.setCategoria(cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_TRANSACAO_CATEGORIA)));
                transacoes.add(transacao);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return transacoes;
    }

    public void close() {
        if (db != null && db.isOpen()) {
            db.close();
        }
    }
}
