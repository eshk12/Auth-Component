package com.itzikbarabie.moni.Repository;

import com.itzikbarabie.moni.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthRepository extends JpaRepository<User, Long> {
    boolean existsByEmailAndPassword(String email, String password);
}
