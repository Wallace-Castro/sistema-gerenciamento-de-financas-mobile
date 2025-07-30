package com.example.projeto;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class LivrosAdapter extends RecyclerView.Adapter<LivrosAdapter.LivroViewHolder> {

    private List<Livro> livrosList;

    // Construtor
    public LivrosAdapter(List<Livro> livrosList) {
        this.livrosList = livrosList;
    }

    @Override
    public LivroViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_livro, parent, false);
        return new LivroViewHolder(view);
    }

    @Override
    public void onBindViewHolder(LivroViewHolder holder, int position) {
        Livro livro = livrosList.get(position);
        holder.tvTituloLivro.setText(livro.getTitulo());
        holder.tvDescricaoLivro.setText(livro.getDescricao());
        holder.imgLivro.setImageResource(livro.getImagem());
    }

    @Override
    public int getItemCount() {
        return livrosList.size();
    }

    public static class LivroViewHolder extends RecyclerView.ViewHolder {
        ImageView imgLivro;
        TextView tvTituloLivro, tvDescricaoLivro;

        public LivroViewHolder(View itemView) {
            super(itemView);
            imgLivro = itemView.findViewById(R.id.img_livro);
            tvTituloLivro = itemView.findViewById(R.id.tv_titulo_livro);
            tvDescricaoLivro = itemView.findViewById(R.id.tv_descricao_livro);
        }
    }
}

