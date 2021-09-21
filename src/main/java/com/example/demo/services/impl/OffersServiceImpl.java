package com.example.demo.services.impl;


import com.example.demo.entities.Offers;
import com.example.demo.repositories.OffersRepository;
import com.example.demo.services.OffersService;
import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OffersServiceImpl implements OffersService {

    @Autowired
    private  OffersRepository offersRepository;



    @Override
    public Offers saveOffers(Offers offers) {
        return offersRepository.save(offers);
    }

    @Override
    public void deleteOffers(Offers offers) {
        offersRepository.delete(offers);
    }

    @Override
    public Offers getOne(Long id) {
        return offersRepository.findByIdEquals(id);
    }

    @Override
    public List<Offers> getAll() {
        return offersRepository.findAll();
    }

    @Override
    public List<Offers> searchByTitle(String title) {
        return offersRepository.findAllByTitle(title);
    }

    @Override
    public List<Offers> sortBy(String sortBy, String sortOrder) {
        if(!sortBy.isEmpty()) {
            if ("asc".equals(sortOrder)) {
                return offersRepository.findAll(Sort.by(Sort.Direction.ASC, sortBy));
            } else {
                return offersRepository.findAll(Sort.by(Sort.Direction.DESC, sortBy));
            }
        }
        else {
            return null;
        }
    }
}
