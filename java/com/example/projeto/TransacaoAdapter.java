package com.example.projeto;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class TransacaoAdapter extends RecyclerView.Adapter<TransacaoAdapter.TransacaoViewHolder> {

    private List<Transacao> transacoes;

    public TransacaoAdapter(List<Transacao> transacoes) {
        this.transacoes = transacoes;
    }

    @Override
    public TransacaoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_transacao, parent, false);
        return new TransacaoViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(TransacaoViewHolder holder, int position) {
        Transacao transacao = transacoes.get(position);
        holder.valorTextView.setText(String.valueOf(transacao.getValor()));
        holder.dataTextView.setText(transacao.getData());
        holder.categoriaTextView.setText(transacao.getCategoria());
    }

    @Override
    public int getItemCount() {
        return transacoes.size();
    }

    public static class TransacaoViewHolder extends RecyclerView.ViewHolder {
        public TextView valorTextView;
        public TextView dataTextView;
        public TextView categoriaTextView;

        public TransacaoViewHolder(View itemView) {
            super(itemView);
            valorTextView = itemView.findViewById(R.id.valorTextView);
            dataTextView = itemView.findViewById(R.id.dataTextView);
            categoriaTextView = itemView.findViewById(R.id.categoriaTextView);
        }
    }
}
