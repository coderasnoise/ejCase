package com.emlakjet.ejcase.repository;

import com.emlakjet.ejcase.entities.BillInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BillInfoRepository extends JpaRepository<BillInfo, Long> {

    List<BillInfo> findByIsDeniedTrue();

    List<BillInfo> findByIsDeniedFalse();

    @Query(
            value = "SELECT sum(amount) From bills WHERE user_id = :userId AND is_denied=false",
            nativeQuery = true)
    Double checkTotalAmountByUserId(@Param("userId") Long userId);

}
