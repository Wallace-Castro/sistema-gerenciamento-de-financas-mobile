package com.example.projeto;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class HistoricoActivity extends AppCompatActivity {

    private RecyclerView recyclerViewHistorico;
    private TransacaoDAO transacaoDAO;
    private TransacaoAdapter transacaoAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_historico);

        int usuarioId = getIntent().getIntExtra("usuario_id", 0);

        recyclerViewHistorico = findViewById(R.id.recyclerViewHistorico);
        recyclerViewHistorico.setLayoutManager(new LinearLayoutManager(this));

        transacaoDAO = new TransacaoDAO(this);

        List<Transacao> transacoes = transacaoDAO.listarTransacoes(usuarioId);

        transacaoAdapter = new TransacaoAdapter(transacoes);
        recyclerViewHistorico.setAdapter(transacaoAdapter);

        findViewById(R.id.buttonVoltar).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HistoricoActivity.this, MainActivity.class);
                intent.putExtra("usuario_id", usuarioId);
                startActivity(intent);            }
        });
    }
}
