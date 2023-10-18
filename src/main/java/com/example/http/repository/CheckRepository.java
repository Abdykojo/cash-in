package com.example.http.repository;
import com.example.http.entity.Check;
import org.springframework.data.jpa.repository.JpaRepository;



import java.util.Optional;
import java.util.UUID;

public interface CheckRepository extends JpaRepository<Check, UUID> {

    Optional<Check> getByQid (UUID qid);
    Check findByQid (UUID qid);


}
