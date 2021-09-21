package com.example.demo.controllers;

import com.example.demo.entities.Offers;
import com.example.demo.services.OffersService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigurationPackage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class MainController {

    @Autowired
    private  OffersService offersService;

    @RequestMapping("/")
    public String getString()
    {
        return "Hello World";
    }


//    {"id":2,"title":"kl","description":"nm,","price":2.152}
//    @PostMapping(path = "/add")
//    public ResponseEntity<?> addItem( @RequestBody Offers offers){
//        if(offers!=null) {
//            offersService.saveOffers(offers);
//
//        }
//        return ResponseEntity.ok(offers);
//    }
    //http://localhost:8000/add?title=Title&description=description&price=2.15
    @RequestMapping(path = "/add", method ={RequestMethod.POST, RequestMethod.GET})
    public ResponseEntity<?> add( @RequestParam  String title,  @RequestParam  String description,  @RequestParam  Double price){
        Offers offer = new Offers();
        offer.setTitle(title);
        offer.setDescription(description);
        offer.setPrice(price);
        offersService.saveOffers(offer) ;
        return ResponseEntity.ok(offer);
    }

    @RequestMapping(path = "/delete/{id}",method = {RequestMethod.DELETE, RequestMethod.GET})
    public ResponseEntity<?> delete(@PathVariable Long id){
        System.out.println("Delete ");
        Offers offers = offersService.getOne(id);
        System.out.println("Delete 2");

        if (offers != null) {
            offersService.deleteOffers(offers);
        }
        return ResponseEntity.ok(offers);
    }

    @RequestMapping(path = "/update",method = {RequestMethod.PATCH, RequestMethod.GET})
    public ResponseEntity<?> putItem(  @RequestParam  Long id,@RequestParam  String title,  @RequestParam  String description,  @RequestParam  Double price){
        Offers offer = new Offers();
        if(id!=null) {
            offer.setId(id);
        }
        offer.setTitle(title);
        offer.setDescription(description);
        offer.setPrice(price);
        offersService.saveOffers(offer) ;
        return ResponseEntity.ok(offer);
    }
    @GetMapping(value = "/all")
    public ResponseEntity<?> getAll(){
        List<Offers> cards = offersService.getAll();
        return new ResponseEntity<>(cards, HttpStatus.OK);
    }

    @RequestMapping(path = "/search")
    public ResponseEntity<?> search(@RequestParam String title){
        List<Offers> offers =new ArrayList<>();
      if (title!=""){
          offers= offersService.searchByTitle(title);
      }
        return ResponseEntity.ok(offers);
    }

    @RequestMapping(path = "/sort")
    public ResponseEntity<?> sort(@RequestParam String sortBy, @RequestParam String sortOrder){

        List<Offers> offers =new ArrayList<>();
        if (sortBy!="" && sortOrder!=""){
            offers= offersService.sortBy(sortBy,sortOrder);
        }
        return ResponseEntity.ok(offers);
    }



}
