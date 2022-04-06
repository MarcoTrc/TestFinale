package com.testfinale.Test.Finale.avviso;

public class ProdottoNonTrovato extends RuntimeException{
    public ProdottoNonTrovato(Long id) {
        super ("prodotto non trovato" + id);
    }
}
