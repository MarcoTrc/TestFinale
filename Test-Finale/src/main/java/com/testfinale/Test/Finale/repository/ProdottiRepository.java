package com.testfinale.Test.Finale.repository;

import com.testfinale.Test.Finale.model.Prodotto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

public interface ProdottiRepository extends JpaRepository<Prodotto, Long> {
    List<Prodotto> findBydatadiscadenzaBetween(Date datada, Date dataa);
    List<Prodotto> findByPrezzoBetween(float min, float max);
    List<Prodotto> findByPrezzoLessThan(float min);
}

