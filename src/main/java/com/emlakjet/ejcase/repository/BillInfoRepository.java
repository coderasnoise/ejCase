package com.emlakjet.ejcase.repository;
import com.emlakjet.ejcase.entities.BillInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BillInfoRepository extends JpaRepository<BillInfo, Long> {

}
