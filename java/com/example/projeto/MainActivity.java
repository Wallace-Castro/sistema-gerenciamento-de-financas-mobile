package com.example.projeto;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private OrcamentoMensalDAO orcamentoMensalDAO;
    private MetaDAO metaDAO;
    private TextView tvOrcamento, tvGastoAtual, tvMetasTitulo, tvErroOrcamentoMetas;
    private RecyclerView rvMetas, rvLivros;
    private LivrosAdapter livrosAdapter;
    private List<Livro> livrosList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rvLivros = findViewById(R.id.rv_livros);

        livrosList = new ArrayList<>();
        livrosList.add(new Livro("Do Mil ao Milhão - Thiago Nigro", "Este livro oferece um guia prático para quem deseja enriquecer, abordando conceitos de investimento e gestão financeira.", R.drawable.ic_profile));
        livrosList.add(new Livro("Os Segredos da Mente Milionária - T. Harv Eker", "Uma obra que explora a mentalidade necessária para alcançar a riqueza, mudando a forma como você pensa sobre dinheiro.", R.drawable.ic_profile));
        livrosList.add(new Livro("Pai Rico, Pai Pobre - Robert T. Kiyosaki", "Um clássico que discute a importância da educação financeira e como construir riqueza através de investimentos.", R.drawable.ic_profile));
        livrosList.add(new Livro("Mais Esperto que o Diabo - Napoleon Hill", "Este livro apresenta princípios de sucesso e como superá-los, focando na mentalidade e na disciplina financeira.", R.drawable.ic_profile));
        livrosList.add(new Livro("Warren Buffett e a Análise de Balanços - Mary Buffett e David Clark", "Um guia sobre como analisar empresas e tomar decisões de investimento informadas, baseado na filosofia de um dos investidores mais bem-sucedidos do mundo.", R.drawable.ic_profile));

        rvLivros.setLayoutManager(new LinearLayoutManager(this));
        livrosAdapter = new LivrosAdapter(livrosList);
        rvLivros.setAdapter(livrosAdapter);

        orcamentoMensalDAO = new OrcamentoMensalDAO(this);
        metaDAO = new MetaDAO(this);
        int usuarioId = getIntent().getIntExtra("usuario_id", 0);

        tvOrcamento = findViewById(R.id.tv_orcamento);
        tvGastoAtual = findViewById(R.id.tv_gasto_atual);
        tvMetasTitulo = findViewById(R.id.tv_metas_titulo);
        rvMetas = findViewById(R.id.rv_metas);
        tvErroOrcamentoMetas = findViewById(R.id.tv_orcamento_titulo);

        carregarOrcamentoMensal(usuarioId);
        carregarMetas(usuarioId);

        configurarHudBar(usuarioId);
    }

    private void carregarOrcamentoMensal(int usuarioId) {
        Calendar calendar = Calendar.getInstance();
        int mes = calendar.get(Calendar.MONTH) + 1;
        int ano = calendar.get(Calendar.YEAR);

        OrcamentoMensal orcamento = orcamentoMensalDAO.obterOrcamentoMensalPorMesEAno(usuarioId, mes, ano);

        if (orcamento != null) {
            tvOrcamento.setText("Limite: R$ " + orcamento.getLimite());
            tvGastoAtual.setText("Gasto Atual: R$ " + orcamento.getGastoAtual());
            tvErroOrcamentoMetas.setVisibility(View.GONE);
        } else {
            tvErroOrcamentoMetas.setText("Nenhum orçamento encontrado para este mês.");
            tvErroOrcamentoMetas.setVisibility(View.VISIBLE);
        }
    }


    private void carregarMetas(int usuarioId) {
        List<Meta> metas = metaDAO.listarMetas(usuarioId);
        if (metas != null && !metas.isEmpty()) {
            MetaAdapter metaAdapter = new MetaAdapter(metas);
            rvMetas.setLayoutManager(new LinearLayoutManager(this));
            rvMetas.setAdapter(metaAdapter);
            tvErroOrcamentoMetas.setVisibility(View.GONE);
        } else {
            tvErroOrcamentoMetas.setVisibility(View.VISIBLE);
        }
    }

    private void configurarHudBar(int usuarioId) {
        ImageView icon1 = findViewById(R.id.icon_1);
        ImageView icon2 = findViewById(R.id.icon_2);
        ImageView icon3 = findViewById(R.id.icon_3);
        ImageView icon4 = findViewById(R.id.icon_4);

        icon1.setOnClickListener(v -> {

        });
        icon2.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, FincancaActivity.class);
            intent.putExtra("usuario_id", usuarioId);
            startActivity(intent);
        });
        icon3.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, HistoricoActivity.class);
            intent.putExtra("usuario_id", usuarioId);
            startActivity(intent);
        });
        icon4.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, PerfilActivity.class);
            intent.putExtra("usuario_id", usuarioId);
            startActivity(intent);
        });
    }
}
