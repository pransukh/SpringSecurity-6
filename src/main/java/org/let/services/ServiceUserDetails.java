package org.let.services;

import org.let.modals.ModalUsers;
import org.let.repositories.RepositoryUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

@Service
public class ServiceUserDetails {

    @Autowired
    RepositoryUserDetails repositoryUserDetails;

    public ModalUsers getUserWithUsernamePassword(String userName, String password){

        return repositoryUserDetails.findByUsernameAndPassword(userName,password);
    }
}
