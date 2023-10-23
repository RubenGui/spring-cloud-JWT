package com.emc.springSecurityJWT.repositories;

import com.emc.springSecurityJWT.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface I_UserRepository extends JpaRepository<UserEntity, Long> {


    Optional<UserEntity> findByUsuNombre(String userName);
    Optional<UserEntity> findByUsuLogin(String login);

    boolean existsByUsuLogin(String userLogin);

    boolean existsByUsuNombre(String userName);

    boolean existsByUsuMail(String userMail);


}
