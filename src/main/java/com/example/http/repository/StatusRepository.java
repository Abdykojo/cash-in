package com.example.http.repository;
import com.example.http.entity.StatusCheck;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;

public interface StatusRepository extends JpaRepository<StatusCheck, UUID> {


}
