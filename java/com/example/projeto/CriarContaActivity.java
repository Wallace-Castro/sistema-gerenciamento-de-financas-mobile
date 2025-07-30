package com.example.projeto;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class CriarContaActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_criar_conta);

        EditText nomeEditText = findViewById(R.id.id_nome);
        EditText emailEditText = findViewById(R.id.id_email);
        EditText senhaEditText = findViewById(R.id.id_senha);

        Button btnCadastrar = findViewById(R.id.id_cadastrar);

        btnCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nome = nomeEditText.getText().toString().trim();
                String email = emailEditText.getText().toString().trim();
                String senha = senhaEditText.getText().toString().trim();

                if (nome.isEmpty() || email.isEmpty() || senha.isEmpty()) {
                    Toast.makeText(CriarContaActivity.this, "Por favor, preencha todos os campos!", Toast.LENGTH_SHORT).show();
                } else {
                    Usuario usuario = new Usuario(nome, email, senha);
                    UsuarioDAO usuarioDAO = new UsuarioDAO(CriarContaActivity.this);
                    long id = usuarioDAO.inserirUsuario(usuario);
                    if (id > 0) {
                        Toast.makeText(CriarContaActivity.this, "Conta criada com sucesso!", Toast.LENGTH_SHORT).show();
                        finish();
                    } else {
                        Toast.makeText(CriarContaActivity.this, "Erro ao criar a conta. Tente novamente!", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        Button btnLogin = findViewById(R.id.id_login);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CriarContaActivity.this, LoginActivity.class);
                startActivity(intent);
                startActivity(intent);
            }
        });
    }
}
