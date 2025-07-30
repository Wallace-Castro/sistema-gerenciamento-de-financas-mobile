package com.example.projeto;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class PerfilActivity extends AppCompatActivity {

    private TextView tvNomeUsuario, tvEmailUsuario;
    private LinearLayout layoutFormEdicao;
    private EditText etNome, etEmail, etSenha;
    private Button btnEditarConta, btnConfirmarEdicao,btnVoltar;

    private UsuarioDAO usuarioDAO;
    private Usuario usuarioAtual;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil);

        int usuarioId = getIntent().getIntExtra("usuario_id", 0);

        tvNomeUsuario = findViewById(R.id.tv_nome_usuario);
        tvEmailUsuario = findViewById(R.id.tv_email_usuario);
        layoutFormEdicao = findViewById(R.id.layout_form_edicao);
        etNome = findViewById(R.id.et_nome);
        etEmail = findViewById(R.id.et_email);
        etSenha = findViewById(R.id.et_senha);
        btnEditarConta = findViewById(R.id.btn_editar_conta);
        btnConfirmarEdicao = findViewById(R.id.btn_confirmar_edicao);
        btnVoltar = findViewById(R.id.btn_voltar);

        usuarioDAO = new UsuarioDAO(this);
        usuarioAtual = usuarioDAO.buscarUsuarioPorId(usuarioId);

        if (usuarioAtual != null) {
            tvNomeUsuario.setText(usuarioAtual.getNome());
            tvEmailUsuario.setText(usuarioAtual.getEmail());
        } else {
            Toast.makeText(this, "Usuário não encontrado", Toast.LENGTH_SHORT).show();
            finish();
        }

        btnEditarConta.setOnClickListener(v -> {
            layoutFormEdicao.setVisibility(View.VISIBLE);
            btnEditarConta.setVisibility(View.GONE);

            etNome.setText(usuarioAtual.getNome());
            etEmail.setText(usuarioAtual.getEmail());
            etSenha.setText(usuarioAtual.getSenha());
        });

        btnConfirmarEdicao.setOnClickListener(v -> {
            String novoNome = etNome.getText().toString().trim();
            String novoEmail = etEmail.getText().toString().trim();
            String novaSenha = etSenha.getText().toString().trim();

            if (novoNome.isEmpty() || novoEmail.isEmpty() || novaSenha.isEmpty()) {
                Toast.makeText(this, "Preencha todos os campos", Toast.LENGTH_SHORT).show();
                return;
            }

            usuarioAtual.setNome(novoNome);
            usuarioAtual.setEmail(novoEmail);
            usuarioAtual.setSenha(novaSenha);

            int resultado = usuarioDAO.atualizarUsuario(usuarioAtual);

            if (resultado > 0) {
                Toast.makeText(this, "Dados atualizados com sucesso!", Toast.LENGTH_SHORT).show();
                tvNomeUsuario.setText(novoNome);
                tvEmailUsuario.setText(novoEmail);

                layoutFormEdicao.setVisibility(View.GONE);
                btnEditarConta.setVisibility(View.VISIBLE);
            } else {
                Toast.makeText(this, "Erro ao atualizar os dados", Toast.LENGTH_SHORT).show();
            }

        });

        btnVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PerfilActivity.this, MainActivity.class);
                intent.putExtra("usuario_id", usuarioId);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (usuarioDAO != null) {
            usuarioDAO.close();
        }
    }
}
