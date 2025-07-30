package com.example.projeto;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MetaAdapter extends RecyclerView.Adapter<MetaAdapter.MetaViewHolder> {

    private List<Meta> metas;

    public MetaAdapter(List<Meta> metas) {
        this.metas = metas;
    }

    @Override
    public MetaViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_meta, parent, false);
        return new MetaViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MetaViewHolder holder, int position) {
        Meta meta = metas.get(position);
        holder.tvMeta.setText("Meta: R$ " + meta.getValorMeta() + " - Progresso: " + meta.getProgresso() + "%");
    }

    @Override
    public int getItemCount() {
        return metas.size();
    }

    public static class MetaViewHolder extends RecyclerView.ViewHolder {
        TextView tvMeta;

        public MetaViewHolder(View itemView) {
            super(itemView);
            tvMeta = itemView.findViewById(R.id.tv_meta);
        }
    }
}
