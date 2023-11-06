package com.emlakjet.ejcase.repository;
import com.emlakjet.ejcase.entities.BillInfo;
import com.emlakjet.ejcase.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BillInfoRepository extends JpaRepository<BillInfo, Long> {
    List<BillInfo> findByUser(User existingUser);
}
