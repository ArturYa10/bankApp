package de.telran.bankapp.repository;

import de.telran.bankapp.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClientRepository extends JpaRepository<Client, String> {

    List<Client> findByFirstName(String name);

}
