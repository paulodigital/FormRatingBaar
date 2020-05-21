package br.edu.faculdadedelta.formseriadoapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import br.edu.faculdadedelta.formseriadoapp.adapter.SeriadoAdapter;
import br.edu.faculdadedelta.formseriadoapp.dao.SeriadoDAO;
import br.edu.faculdadedelta.formseriadoapp.modelo.Seriado;

public class ListaActivity extends AppCompatActivity {

    private ListView lvLista;
    private SeriadoDAO dao = new SeriadoDAO();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista);

        lvLista = findViewById(R.id.lvLista);

        lvLista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Seriado seriadoSelecionado = (Seriado) adapterView.getItemAtPosition(i);

                Intent intent = new Intent(getBaseContext(), MainActivity.class);
                intent.putExtra("seriadoSelecionado", seriadoSelecionado);
                startActivity(intent);
            }
        });

        lvLista.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                Seriado seriadoSelecionado = (Seriado) adapterView.getItemAtPosition(i);
                dao.excluir(seriadoSelecionado);
                carregarLista();
                return false;
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        carregarLista();
    }

    private void carregarLista() {
        SeriadoAdapter adapter = new SeriadoAdapter(dao.listar(), getBaseContext());
        lvLista.setAdapter(adapter);
    }

    public void novo (View view){
        Intent intent = new Intent(getBaseContext(), MainActivity.class);
        startActivity(intent);
    }
}
