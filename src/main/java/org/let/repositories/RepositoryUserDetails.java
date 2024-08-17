package org.let.repositories;

import org.let.modals.ModalUsers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface RepositoryUserDetails extends JpaRepository<ModalUsers,Long> {
    @Query("SELECT user FROM ModalUsers user " +

            "WHERE user.userName = :userName " +
            "AND user.password = :password")
    ModalUsers findByUsernameAndPassword(String userName, String password);
}
