package br.edu.faculdadedelta.formseriadoapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import br.edu.faculdadedelta.formseriadoapp.dao.SeriadoDAO;
import br.edu.faculdadedelta.formseriadoapp.modelo.Seriado;

public class MainActivity extends AppCompatActivity {

    private EditText etNome;
    private EditText etGenero;
    private EditText etStatus;
    private EditText etComentario;
    private EditText etNota;
    private RatingBar rbNota2;
    private EditText rbStar;
    private EditText etDataLancamento;
    private Seriado seriado = new Seriado();
    private SeriadoDAO dao = new SeriadoDAO();
    private SimpleDateFormat sf = new SimpleDateFormat("dd/MM/yyyy");



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        etNome = findViewById(R.id.etNome);
        etGenero = findViewById(R.id.etGenero);
        etStatus = findViewById(R.id.etStatus);
        etComentario = findViewById(R.id.etComentario);
        etNota = findViewById(R.id.etNota);
        etDataLancamento = findViewById(R.id.etData);
        rbNota2 = (RatingBar)findViewById(R.id.rbNota2);

        Intent intent = getIntent();

        if (intent != null) {
            Seriado seriadoSelecionado
                    = (Seriado) intent.getSerializableExtra("seriadoSelecionado");
            if (seriadoSelecionado != null) {
                popularFormulario(seriadoSelecionado);
            }
        }
    }

    public  void  onClick(View view){
        int noofstars = rbNota2.getNumStars();
        float getrating = rbNota2.getRating();
        rbStar.setText("Nota: " +getrating+"/" +noofstars);

    }


    private void popularFormulario(Seriado seriadoSelecionado) {
        etNome.setText(seriadoSelecionado.getNome());
        etGenero.setText(seriadoSelecionado.getGenero());
        etStatus.setText(seriadoSelecionado.getStatus());
        etComentario.setText(seriadoSelecionado.getComentario());
        etNota.setText(String.valueOf(seriadoSelecionado.getNota()));
        rbNota2.setRating(Float.parseFloat(String.valueOf(seriadoSelecionado.getValidar())));
        etDataLancamento.setText(sf.format(seriadoSelecionado.getDataLancamento()));
        seriado.setId(seriadoSelecionado.getId());
    }

    private void popularModelo() {

        seriado.setNome(etNome.getText().toString());
        seriado.setGenero(etGenero.getText().toString());
        seriado.setStatus(etStatus.getText().toString());
        seriado.setComentario(etComentario.getText().toString());
        seriado.setNota(Integer.parseInt(etNota.getText().toString()));
        seriado.setValidar(Float.parseFloat(String.valueOf(rbNota2.getRating())));

        Date data = null;
        try {
            data = sf.parse(etDataLancamento.getText().toString());
        } catch (ParseException e) {
            e.printStackTrace();
        }

        seriado.setDataLancamento(data);
    }

    public String validarCampos(){

        String mensagemRetorno = "";

        if (etNome.getText().toString().isEmpty()) {
            mensagemRetorno = "CAMPO NOME NÃO FOI PREENCHIDO !";
        }
        if (etGenero.getText().toString().isEmpty()) {
            mensagemRetorno += "\n CAMPO GENERO NÃO FOI PREENCHIDO !";
        }
        if (etStatus.getText().toString().isEmpty()) {
            mensagemRetorno += "\n CAMPO STATUS NÃO FOI PREENCHIDO !";
        }
        if (etComentario.getText().toString().isEmpty()) {
            mensagemRetorno += "\n CAMPO COMENTARIO NÃO FOI PREENCHIDO !";
        }
        if (etNota.getText().toString().isEmpty()) {
            mensagemRetorno += "\n CAMPO NOTA NÃO FOI PREENCHIDO !";
        }
        if (etDataLancamento.getText().toString().isEmpty()) {
            mensagemRetorno += "\n CAMPO DATA NÃO FOI PREENCHIDO !";
        }

        return mensagemRetorno;

    }

    public void salvar(View view) {

       String mensagemValidacao = validarCampos();
        if (mensagemValidacao.isEmpty()) {
            popularModelo();
            if (seriado.getId() == null) {
                dao.incluir(seriado);
                Toast.makeText(getBaseContext(),
                        "INCLUSÃO REALIZADA COM SUCESSO !" ,Toast.LENGTH_LONG).show();
                limparCampos();
            } else {
                dao.alterar(seriado);
                Toast.makeText(getBaseContext(),
                        "ALTERAÇÃO REALIZADA COM SUCESSO !" ,Toast.LENGTH_LONG).show();
                limparCampos();
            }
        } else {
            Toast.makeText(getBaseContext(), mensagemValidacao, Toast.LENGTH_LONG).show();
        }

    }

 /*
        String nome = etNome.getText().toString();
        String status = etStatus.getText().toString();
        String genero = etGenero.getText().toString();
        String comentario = etComentario.getText().toString();
        String nota = etNota.getText().toString();
        String data = etDataLancamento.getText().toString();

        Date dataDigitada = null;

        try {
            dataDigitada = sf.parse(etDataLancamento.getText().toString());
        } catch (ParseException e) {
            e.printStackTrace();
        }


        if(etNome.equals("") && etStatus.equals("") && etGenero.equals("")
                && etComentario.equals("") && etNota.equals("")&& dataDigitada == null){

            Toast.makeText(getBaseContext(),
                    "TODOS OS CAMPOS ESTÃƒO VAZIOS!", Toast.LENGTH_LONG).show();

        }else if(etNome.equals("") || etStatus.equals("") || etGenero.equals("")
                || etComentario.equals("") || etNota.equals("") || dataDigitada == null) {
            Toast.makeText(getBaseContext(),
                    "PREENCHER TODOS OS CAMPOS!", Toast.LENGTH_LONG).show();
        }

        else if (nome.isEmpty()) {
            Toast.makeText(getBaseContext(), "CAMPO NOME OBRIGATORIO !",
                    Toast.LENGTH_LONG).show();
            etNome.requestFocus();
        } else if (status.isEmpty()) {
            Toast.makeText(getBaseContext(), "CAMPO STATUS OBRIGATORIO !",
                    Toast.LENGTH_LONG).show();
            etStatus.requestFocus();
        } else if (genero.isEmpty()) {
            Toast.makeText(getBaseContext(), "CAMPO GENERO OBRIGATORIO !",
                    Toast.LENGTH_LONG).show();
            etGenero.requestFocus();
        } else if (comentario.isEmpty()) {
            Toast.makeText(getBaseContext(), "CAMPO COMENTARIO OBRIGATORIO !",
                    Toast.LENGTH_LONG).show();
            etComentario.requestFocus();
        } else if (nota.isEmpty()) {
            Toast.makeText(getBaseContext(), "CAMPO NOTA OBRIGATORIO !",
                    Toast.LENGTH_LONG).show();
            etNota.requestFocus();
        } else if (data.isEmpty()) {
            Toast.makeText(getBaseContext(), "CAMPO DATA OBRIGATORIO !",
                    Toast.LENGTH_LONG).show();
            etDataLancamento.requestFocus();
        } else if (dataDigitada.after(dataHoje)) {
            Toast.makeText(getBaseContext(), "A DATA NÃO PODE SER MAIOR QUE A DATA ATUAL !",
                    Toast.LENGTH_LONG).show();
            etDataLancamento.requestFocus();

        } else {

            popularModelo();

            if (seriado.getId() == null) {
                dao.incluir(seriado);
                limparCampos();
                Toast.makeText(getBaseContext(), "INCLUSÃO REALIZADA COM SUCESSO !", Toast.LENGTH_LONG).show();

            } else {

                dao.alterar(seriado);
                limparCampos();
                Toast.makeText(getBaseContext(), "ALTERAÇÃO REALIZADA COM SUCESSO !", Toast.LENGTH_LONG).show();
            }

        }
    }

         */

    private void limparCampos() {
        etNome.setText("");
        etGenero.setText("");
        etStatus.setText("");
        etComentario.setText("");
        etNota.setText("");
        etDataLancamento.setText("");


        seriado = new Seriado();
    }

    public void limpar(View view) {
        limparCampos();
    }

    public void listar(View view) {
        finish();    }


}

