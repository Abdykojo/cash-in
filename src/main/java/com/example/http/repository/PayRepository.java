package com.example.http.repository;
import com.example.http.entity.Pay;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;


public interface PayRepository extends JpaRepository<Pay,UUID> {

     Pay getByQid (UUID qid);


     Optional<Pay> findByQid(UUID qid);


}
