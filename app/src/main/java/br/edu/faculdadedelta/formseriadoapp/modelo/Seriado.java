package br.edu.faculdadedelta.formseriadoapp.modelo;

import android.widget.RatingBar;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

public class Seriado implements Serializable {

    private Long id;
    private String nome;
    private String genero;
    private String status;
    private String comentario;
    private int nota;
    private Date dataLancamento;
    private float validar;

    public Seriado() {
    }

    public Seriado(Long id, String nome, String genero, String status, String comentario, int nota, Date dataLancamento, Float validar) {
        this.id = id;
        this.nome = nome;
        this.genero = genero;
        this.status = status;
        this.comentario = comentario;
        this.nota = nota;
        this.dataLancamento = dataLancamento;
        this.validar = validar;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public int getNota() {
        return nota;
    }

    public void setNota(int nota) {
        this.nota = nota;
    }

    public Date getDataLancamento() {
        return dataLancamento;
    }

    public void setDataLancamento(Date dataLancamento) {
        this.dataLancamento = dataLancamento;
    }

    public float getValidar() {
        return validar;
    }

    public void setValidar(float validar) {
        this.validar = validar;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Seriado seriado = (Seriado) o;
        return Objects.equals(id, seriado.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
