package com.example.http.repository;
import com.example.http.entity.CreditApplication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CreditApplicationRepository extends JpaRepository<CreditApplication, Long> {

    @Query(value ="select MIN(percent) from tb_credit_application where sum < ?1", nativeQuery = true)
    double getBySum (int sum);

}
