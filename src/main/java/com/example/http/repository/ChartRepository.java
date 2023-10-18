package com.example.http.repository;

import com.example.http.entity.Chart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ChartRepository extends JpaRepository<Chart, Long> {
    @Query(value ="select MIN(final_balance) from tb_chart where credit_id_id =?1", nativeQuery = true)
    double findBid(Long id);
    @Query(value ="select * from tb_chart where credit_id_id =?1 and final_balance =?2", nativeQuery = true)
    Chart findChart(Long id, double finalBalance);


}
