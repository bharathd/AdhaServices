package com.app.adha.repository;

import com.app.adha.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Anil on 03/10/18.
 */

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

}
