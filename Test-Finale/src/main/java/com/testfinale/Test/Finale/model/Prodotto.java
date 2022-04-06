package com.testfinale.Test.Finale.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

@Entity
public class Prodotto {
    @Id
    @GeneratedValue
    private Long id;
    private String nome;
    private float prezzo;
    private float peso;
    private Date datadiscadenza;

    public Prodotto(Long id, String nome, float prezzo, float peso, Date datadiscadenza) {
        this.id = id;
        this.nome = nome;
        this.prezzo = prezzo;
        this.peso = peso;
        this.datadiscadenza = datadiscadenza;
    }

    public Prodotto(String nome, float prezzo, float peso, Date datadiscadenza) {
        this.nome = nome;
        this.prezzo = prezzo;
        this.peso = peso;
        this.datadiscadenza = datadiscadenza;
    }

    public Prodotto() {
    }

    public Prodotto(String nome, float prezzo, Date datadiscadenza) {
        this.nome = nome;
        this.prezzo = prezzo;
        this.datadiscadenza = datadiscadenza;
    }

    public Prodotto(String nome, float prezzo) {
        this.nome = nome;
        this.prezzo = prezzo;
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

    public float getPrezzo() {
        return prezzo;
    }

    public void setPrezzo(float prezzo) {
        this.prezzo = prezzo;
    }

    public float getPeso() {
        return peso;
    }

    public void setPeso(float peso) {
        this.peso = peso;
    }

    public Date getDatadiscadenza() {
        return datadiscadenza;
    }

    public void setDatadiscadenza(Date datadiscadenza) {
        this.datadiscadenza = datadiscadenza;
    }

}
