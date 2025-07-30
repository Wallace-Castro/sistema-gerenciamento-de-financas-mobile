package com.example.projeto;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Toast;
import java.util.Calendar;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class FincancaActivity extends AppCompatActivity {

    private Button btnCriarOrcamento, btnAdicionarTransacao, btnCriarMeta, btnVoltar, btnMenu, btnSalvarMeta,btnSalvarTransacao, btnSalvarOrcamento;
    private LinearLayout formCriarOrcamento, formAdicionarTransacao, formCriarMeta;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_fincanca);
        int usuarioId = getIntent().getIntExtra("usuario_id", 0);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        btnCriarOrcamento = findViewById(R.id.btnCriarOrcamento);
        btnAdicionarTransacao = findViewById(R.id.btnAdicionarTransacao);
        btnCriarMeta = findViewById(R.id.btnCriarMeta);
        btnVoltar = findViewById(R.id.btnVoltar);
        btnMenu = findViewById(R.id.btnPrincipal);
        btnSalvarOrcamento = findViewById(R.id.btnSalvarOrcamento);
        btnSalvarTransacao = findViewById(R.id.btnSalvarTransacao);
        btnSalvarMeta = findViewById(R.id.btnSalvarMeta);

        formCriarOrcamento = findViewById(R.id.formCriarOrcamento);
        formAdicionarTransacao = findViewById(R.id.formAdicionarTransacao);
        formCriarMeta = findViewById(R.id.formCriarMeta);

        btnCriarOrcamento.setOnClickListener(v -> mostrarFormulario(formCriarOrcamento));
        btnAdicionarTransacao.setOnClickListener(v -> mostrarFormulario(formAdicionarTransacao));
        btnCriarMeta.setOnClickListener(v -> mostrarFormulario(formCriarMeta));
        btnVoltar.setOnClickListener(v -> voltarParaMenu());
        btnMenu.setOnClickListener(v -> voltarParaPrincipal(usuarioId));


        btnSalvarOrcamento.setOnClickListener(v -> salvarOrcamento());
        btnSalvarTransacao.setOnClickListener(v -> salvarTransacao(usuarioId));
        btnSalvarMeta.setOnClickListener(v -> salvarMeta(usuarioId));

        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH) + 1;
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        EditText etMesOrcamento = findViewById(R.id.etMesOrcamento);
        EditText etAnoOrcamento = findViewById(R.id.etAnoOrcamento);
        EditText etDataTransacao = findViewById(R.id.etDataTransacao);

        etMesOrcamento.setText(String.valueOf(month));
        etAnoOrcamento.setText(String.valueOf(year));
        etDataTransacao.setText(String.format("%02d/%02d/%d", day, month, year));
    }

    private void mostrarFormulario(LinearLayout formulario) {
        formCriarOrcamento.setVisibility(View.GONE);
        formAdicionarTransacao.setVisibility(View.GONE);
        formCriarMeta.setVisibility(View.GONE);

        formulario.setVisibility(View.VISIBLE);

        btnCriarOrcamento.setVisibility(View.GONE);
        btnAdicionarTransacao.setVisibility(View.GONE);
        btnCriarMeta.setVisibility(View.GONE);

        btnVoltar.setVisibility(View.VISIBLE);
    }

    private void voltarParaMenu() {
        formCriarOrcamento.setVisibility(View.GONE);
        formAdicionarTransacao.setVisibility(View.GONE);
        formCriarMeta.setVisibility(View.GONE);

        btnCriarOrcamento.setVisibility(View.VISIBLE);
        btnAdicionarTransacao.setVisibility(View.VISIBLE);
        btnCriarMeta.setVisibility(View.VISIBLE);

        btnVoltar.setVisibility(View.GONE);
    }

    private void voltarParaPrincipal(int usuarioId){
        Intent intent = new Intent(FincancaActivity.this, MainActivity.class);
        intent.putExtra("usuario_id", usuarioId);
        startActivity(intent);
    }
    private void salvarOrcamento() {
        EditText etLimiteOrcamento = findViewById(R.id.etLimiteOrcamento);
        EditText etGastoAtualOrcamento = findViewById(R.id.etGastoAtualOrcamento);
        EditText etMesOrcamento = findViewById(R.id.etMesOrcamento);
        EditText etAnoOrcamento = findViewById(R.id.etAnoOrcamento);

        String limiteString = etLimiteOrcamento.getText().toString();
        String gastoAtualString = etGastoAtualOrcamento.getText().toString();
        String mesString = etMesOrcamento.getText().toString();
        String anoString = etAnoOrcamento.getText().toString();

        if (limiteString.isEmpty() || gastoAtualString.isEmpty() || mesString.isEmpty() || anoString.isEmpty()) {
            Toast.makeText(this, "Preencha todos os campos!", Toast.LENGTH_SHORT).show();
            return;
        }

        double limite = Double.parseDouble(limiteString);
        double gastoAtual = Double.parseDouble(gastoAtualString);
        int mes = Integer.parseInt(mesString);
        int ano = Integer.parseInt(anoString);

        int usuarioId = getIntent().getIntExtra("usuario_id", 0); // Obtém o ID do usuário
        OrcamentoMensal orcamento = new OrcamentoMensal(usuarioId, limite, gastoAtual, mes, ano);

        OrcamentoMensalDAO orcamentoDAO = new OrcamentoMensalDAO(this);
        long id = orcamentoDAO.criarOrcamentoMensal(orcamento);

        if (id > 0) {
            Toast.makeText(this, "Orçamento salvo com sucesso!", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Erro ao salvar o orçamento", Toast.LENGTH_SHORT).show();
        }
    }


    private void salvarTransacao(int usuarioId) {
        EditText etValorTransacao = findViewById(R.id.etValorTransacao);
        EditText etDataTransacao = findViewById(R.id.etDataTransacao);
        Spinner spinnerCategoriaTransacao = findViewById(R.id.spinnerCategoriaTransacao);

        String valorString = etValorTransacao.getText().toString();
        String data = etDataTransacao.getText().toString();
        String categoria = spinnerCategoriaTransacao.getSelectedItem().toString();

        if (valorString.isEmpty()) {
            Toast.makeText(this, "Preencha o valor da transação!", Toast.LENGTH_SHORT).show();
            return;
        }

        double valor = Double.parseDouble(valorString);

        if (data.isEmpty()) {
            Toast.makeText(this, "Preencha a data da transação!", Toast.LENGTH_SHORT).show();
            return;
        }
        Transacao transacao = new Transacao(usuarioId, valor, data, categoria);

        TransacaoDAO transacaoDAO = new TransacaoDAO(this);
        long id = transacaoDAO.criarTransacao(transacao);

        if (id > 0) {
            Toast.makeText(this, "Transação salva com sucesso!", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Erro ao salvar a transação", Toast.LENGTH_SHORT).show();
        }
    }

    private void salvarMeta(int usuarioId) {
        EditText etValorMeta = findViewById(R.id.etValorMeta);
        EditText etProgressoMeta = findViewById(R.id.etProgressoMeta);

        String valorMeta = etValorMeta.getText().toString();
        String progresso = etProgressoMeta.getText().toString();

        if (valorMeta.isEmpty() || progresso.isEmpty()) {
            Toast.makeText(this, "Preencha todos os campos!", Toast.LENGTH_SHORT).show();
        } else {
            double valorMetaDouble = Double.parseDouble(valorMeta);
            double progressoDouble = Double.parseDouble(progresso);

            progressoDouble = Math.min(progressoDouble, 100);

            Meta novaMeta = new Meta(usuarioId, valorMetaDouble, progressoDouble);

            MetaDAO metaDAO = new MetaDAO(this);
            long idMetaCriada = metaDAO.criarMeta(novaMeta);
            metaDAO.close();

            if (idMetaCriada != -1) {
                Toast.makeText(this, "Meta salva com sucesso!", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Erro ao salvar a meta. Tente novamente.", Toast.LENGTH_SHORT).show();
            }
        }
    }



}
