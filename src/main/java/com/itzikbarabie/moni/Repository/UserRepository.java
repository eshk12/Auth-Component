package com.itzikbarabie.moni.Repository;

import com.itzikbarabie.moni.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    boolean existsByUserId(String userId);

    User findUserByEmailAndAndPassword(String email, String password);

    User findUserByUserId(String userId);

}
