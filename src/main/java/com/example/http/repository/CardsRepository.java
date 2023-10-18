package com.example.http.repository;

import com.example.http.entity.Cards;
import com.example.http.entity.Chart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;


public interface CardsRepository extends JpaRepository<Cards, Integer> {

        Cards getCardsByAccountAndMainTrue (String account);
        Cards getCardsByNumber (String number);

//        @Query(value ="select * from tb_cards where number =?1", nativeQuery = true)
        Boolean existsCardsByNumber (int number);

        Optional<Cards> findCardsByAccountAndMainTrue (String account);
        List<Cards> getCardsByAccount (String account);




}
