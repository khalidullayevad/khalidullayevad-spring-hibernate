package com.example.demo.repositories;


import com.example.demo.entities.Offers;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;

@Repository
@Transactional
public interface OffersRepository extends JpaRepository<Offers,Long> {

    Offers findByIdEquals(Long id);
    List<Offers>  findAllByTitle(String title);




}
