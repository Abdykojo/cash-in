package com.example.http.repository;

import com.example.http.entity.Credit;
import com.example.http.entity.Schedule;
import com.example.http.request.ScheduleRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface ScheduleRepository extends JpaRepository<Schedule, Long> {
    @Query(value ="select MIN(final_balance) from tb_schedule where credit_id_id =?1", nativeQuery = true)
    Schedule getById (Long id);

    @Query(value ="SELECT * FROM tb_schedule where id = (SELECT MAX(id) FROM tb_schedule) and credit_id_id =?1",
            nativeQuery = true)
    Optional<Schedule> findSchedule (Long id);

//    @Query(value ="SELECT * FROM tb_schedule where id = (SELECT MAX(id) FROM tb_schedule) and credit_id_id =?1",
//            nativeQuery = true)
//    ScheduleRequest getSchedulesById (Long id);
    @Query(value ="SELECT * FROM tb_schedule where id = (SELECT MAX(id) FROM tb_schedule) and credit_id_id =?1",
            nativeQuery = true)
    Schedule getSchedulesById (Long id);



}
