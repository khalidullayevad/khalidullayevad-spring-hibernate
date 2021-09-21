package com.example.demo.services;


import com.example.demo.entities.Offers;

import java.util.List;

public interface OffersService {
    Offers saveOffers(Offers offers);
    void deleteOffers(Offers offers);
    Offers getOne(Long id);
    List<Offers> getAll();
    List<Offers> searchByTitle(String title);
    List<Offers> sortBy(String sortBy, String sortOrder);
}
