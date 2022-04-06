package com.testfinale.Test.Finale.controller;

import com.testfinale.Test.Finale.avviso.ProdottoNonTrovato;
import com.testfinale.Test.Finale.model.Prodotto;
import com.testfinale.Test.Finale.repository.ProdottiRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;
import java.util.List;

@RestController
public class ProdottoRestController {
    private static Logger logger = LoggerFactory.getLogger(ProdottoRestController.class);
    private ProdottiRepository repository;

    ProdottoRestController(ProdottiRepository repository) {
        this.repository = repository;
    }
    @GetMapping("/prodotti")
    public List<Prodotto> leggiTuttiIProdotti() {
        logger.info("PRENDO TUTTI I PRODOTTI");
        return repository.findAll();
    }
    @PostMapping("/prodotto")
    Prodotto nuovoProdotto(@RequestBody Prodotto nuovoProdotto) {
        return repository.save(nuovoProdotto);
    }
    @GetMapping("/prodotto/{id}")
    public Prodotto trovaProdottoConId(@PathVariable Long id) {
        return repository.findById(id).orElseThrow(
                () -> new ProdottoNonTrovato(id));
    }

    @PutMapping("/prodotto/{id}")
    public Prodotto aggiornaDatiprodotto(@PathVariable Long id, @RequestBody Prodotto prodotto){
        return repository.findById(id).map(
                nuovoProdotto -> {
                    nuovoProdotto.setNome(prodotto.getNome());
                    return repository.save(nuovoProdotto);
                }

        ).orElseGet(
                () -> {
                    prodotto.setId(id);
                    return repository.save(prodotto);
                }
        );
    }
    @GetMapping("/prodotto/prezzo")
    public List<Prodotto> ricercaProdottoConPrezzo(
            @RequestParam(name="min") float min,
            @RequestParam(name="max") float max
    ){
        return repository.findByPrezzoBetween(min, max);
    }
    @RequestMapping(path = "/prodotti/ricerca/prezzo/min",

            method = RequestMethod.GET)
    public List<Prodotto> trovaProdottiPerPrezzo(
            @RequestParam(name="min")
                    Float min){
        return repository.
                findByPrezzoLessThan(min);

    }
    @DeleteMapping("/prodotto/{id}")
    void eliminaProdotto(@PathVariable Long id){
        repository.deleteById(id);
    }

    @GetMapping("/prodotto/ricercatradatediscadenza")
    public List<Prodotto> ricercaProdottoConDateDiScadenza(
            @RequestParam(name="datada") @DateTimeFormat(pattern = "dd-MM-yyyy") Date datada,
            @RequestParam(name="dataa") @DateTimeFormat(pattern = "dd-MM-yyyy") Date dataa
    ){
        return repository.findBydatadiscadenzaBetween(datada, dataa);
    }

    @PostMapping("/caricafile")
    public String caricaFile(@RequestParam("file") MultipartFile file){
        String infoFile = file.getOriginalFilename() + " - " + file.getContentType();
        String conFormat = String.format("%s-%s", file.getOriginalFilename(), file.getContentType());
        logger.info(infoFile);
        logger.warn(conFormat);
        return conFormat;
    }

}
