package br.usjt.voo3ASINv1.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import br.usjt.voo3ASINv1.R;
import br.usjt.voo3ASINv1.controller.DetalheVooActivity;
import br.usjt.voo3ASINv1.model.VooTO;

public class VooAdapter extends RecyclerView.Adapter<VooAdapter.ViewHolder> {
    private LayoutInflater layoutInflater;
    private List<VooTO> vooTOList;
    private Context mContext;

    public VooAdapter(Context _context, List<VooTO> _vooTOList) {
        this.layoutInflater = LayoutInflater.from(_context);
        this.vooTOList = _vooTOList;
        this.mContext = _context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = layoutInflater.inflate(R.layout.card_voos, parent, false);

        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        VooTO current = vooTOList.get(position);

        holder.card_voos_origem.setText(current.Origem);
        holder.card_voos_destino.setText(current.Destino);
        holder.card_voos_data.setText(current.DataVoo);
        holder.card_voos_hora.setText(current.HoraVoo);
        holder.card_voos.setTag(position);
    }

    @Override
    public int getItemCount() {
        return this.vooTOList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        protected CardView card_voos;
        protected TextView card_voos_origem;
        protected TextView card_voos_destino;
        protected TextView card_voos_data;
        protected TextView card_voos_hora;

        public ViewHolder(View itemView) {
            super(itemView);
            card_voos = (CardView) itemView.findViewById(R.id.card_voos);
            card_voos_origem = (TextView) itemView.findViewById(R.id.card_voos_origem);
            card_voos_destino = (TextView) itemView.findViewById(R.id.card_voos_destino);
            card_voos_data = (TextView) itemView.findViewById(R.id.card_voos_data);
            card_voos_hora = (TextView) itemView.findViewById(R.id.card_voos_hora);

            card_voos.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = Integer.parseInt(view.getTag().toString());
                    VooTO current = vooTOList.get(position);
                    Intent intent = new Intent(mContext, DetalheVooActivity.class);
                    intent.putExtra("voo", current);

                    mContext.startActivity(intent);
                }
            });
        }
    }
}