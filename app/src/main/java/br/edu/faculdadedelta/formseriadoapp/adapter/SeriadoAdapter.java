package br.edu.faculdadedelta.formseriadoapp.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.List;

import br.edu.faculdadedelta.formseriadoapp.R;
import br.edu.faculdadedelta.formseriadoapp.modelo.Seriado;

public class SeriadoAdapter extends BaseAdapter {

    private List<Seriado> listaSeriado;
    private Context context;

    public SeriadoAdapter(List<Seriado> listaSeriado, Context context) {
        this.listaSeriado = listaSeriado;
        this.context = context;
    }


    @Override
    public int getCount() {
        return listaSeriado.size();
    }

    @Override
    public Object getItem(int i) {
        return listaSeriado.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @SuppressLint("ResourceAsColor")
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        Seriado seriado = (Seriado) getItem(i);

        View viewRetorno = view.inflate(context, R.layout.item_lista, null);

        TextView tvId = viewRetorno.findViewById(R.id.tvId);
        tvId.setText("ID: " + seriado.getId());

        TextView tvNome = viewRetorno.findViewById(R.id.tvNome);
        tvNome.setText("Nome: " + seriado.getNome());

        TextView tvGenero = viewRetorno.findViewById(R.id.tvGenero);
        tvGenero.setText("Genero: " + seriado.getGenero());

        TextView tvComentario = viewRetorno.findViewById(R.id.tvComentario);
        tvComentario.setText("Nome: " + seriado.getComentario());

        TextView tvStatus = viewRetorno.findViewById(R.id.tvGenero);
        tvStatus.setText("Status: " + seriado.getStatus());

        TextView tvNota = viewRetorno.findViewById(R.id.tvNota);
        tvNota.setText("Nota: " + seriado.getNota());

        TextView tvStar = viewRetorno.findViewById(R.id.tvStar);
        tvStar.setText(("Nota 2: " +seriado.getValidar()));

        TextView tvData = viewRetorno.findViewById(R.id.tvData);

        SimpleDateFormat sf = new SimpleDateFormat("dd/MM/yyyy");
        tvData.setText("Data: " + sf.format(seriado.getDataLancamento()));

        if (i % 2 == 0) {
            viewRetorno.setBackgroundColor(R.color.zebra);
        }

        return viewRetorno;

    }
}
