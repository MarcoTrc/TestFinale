package com.testfinale.Test.Finale.configurazione;

import com.testfinale.Test.Finale.model.Prodotto;
import com.testfinale.Test.Finale.repository.ProdottiRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Configuration
public class PrimoInserimento {

    private static final Logger logger = LoggerFactory.getLogger(PrimoInserimento.class);

    @Bean
    CommandLineRunner inserisciElementi(ProdottiRepository repository) {
        return args -> {
            Prodotto prodotto1 = new Prodotto("Graffiato", 2.20f);
            Prodotto prodotto2 = new Prodotto("Rosetta", 2.40f);
            Prodotto prodotto3 = new Prodotto("Tartaruga", 2.10f);
            List<Prodotto> prodotti = new ArrayList<>();
            prodotti.add(prodotto1);
            prodotti.add(prodotto2);
            prodotti.add(prodotto3);
            repository.saveAll(prodotti);

            List<Prodotto> prodottiDalDb = repository.findAll();
            Prodotto prodotto1DelDb = new Prodotto();
            int indice = 0;
            for (Prodotto prodotto: prodottiDalDb) {
                if (indice == 0){
                    logger.error("ciclo i prodotti del DataBase partendo dal primo");
                    prodotto1DelDb = prodotto;
                    indice++;
                }
                logger.info("Nome " + prodotto.getNome());
                logger.warn(prodotto.toString());
            }

            Prodotto prodottiConId1 = repository.findById(1L).get();
            logger.info("sto per eliminare il prodotto con id 1: " + prodottiConId1.getNome());
            repository.delete(prodotto1);

            prodottiDalDb = repository.findAll();

            logger.info("ora ciclo nuovamente la lista senza il prodotto 1 cancellato in precedenza");

            for (Prodotto prodotto: prodottiDalDb) {
                logger.info("Nome " + prodotto.getNome());
                logger.warn(prodotto.toString());
            }
            logger.info("e BOOOOM!!");
            logger.info("ora però lo riaggiungo...");
            prodotti.add(prodotto1);
            repository.save(prodotto1);


            SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
            Date datadiscadenza = dateFormat.parse("10-04-2022");
            Prodotto prodottoConDatadiscadenza1 = new Prodotto("Graffiato", 2.20f, datadiscadenza);
            repository.save(prodottoConDatadiscadenza1);
            Prodotto prodottoConDatadiscadenza2 = new Prodotto("Rosetta", 2.40f, datadiscadenza);
            repository.save(prodottoConDatadiscadenza2);
            Prodotto prodottoConDatadiscadenza3 = new Prodotto("Tartaruga", 2.10f, datadiscadenza);
            repository.save(prodottoConDatadiscadenza3);

        };

    }

}
