package com.emlakjet.ejcase.repository;
import com.emlakjet.ejcase.entities.BillInfo;
import com.emlakjet.ejcase.entities.BillInfoRequest;
import com.emlakjet.ejcase.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BillInfoRepository extends JpaRepository<BillInfo, Long> {
    List<BillInfo> findByUser(User existingUser);
    @Query(
            value = "SELECT sum(amount) FROM users INNER JOIN public.bills b on users.id = b.user_id WHERE users.email = :userEmail",
            nativeQuery = true)
    Double checkAmountByEmail(@Param("userEmail") String userEmail);

}
