package com.example.projeto;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Button btnLogin = findViewById(R.id.id_login);
        btnLogin.setOnClickListener(v -> {
            String email = ((TextInputEditText) findViewById(R.id.id_email)).getText().toString();
            String senha = ((TextInputEditText) findViewById(R.id.id_senha)).getText().toString();

            UsuarioDAO usuarioDAO = new UsuarioDAO(this);
            Usuario usuario = usuarioDAO.buscarUsuario(email, senha);

            if (usuario != null) {
                Toast.makeText(this, "Bem-vindo, " + usuario.getNome(), Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                int usuarioId = usuario.getId();
                intent.putExtra("usuario_id", usuarioId);
                startActivity(intent);
            } else {
                Toast.makeText(this, "Usuário ou senha incorretos", Toast.LENGTH_SHORT).show();
            }
        });
        Button btnCadastrar = findViewById(R.id.id_cadastrar);
        btnCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, CriarContaActivity.class);
                startActivity(intent);
            }
        });


    }
}
