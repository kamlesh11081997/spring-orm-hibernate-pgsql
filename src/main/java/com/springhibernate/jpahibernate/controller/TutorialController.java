package com.springhibernate.jpahibernate.controller;

import com.springhibernate.jpahibernate.model.Tutorial;
import com.springhibernate.jpahibernate.repository.TutorialRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1")
public class TutorialController {
    @Autowired
    TutorialRepository tutorialRepository;

    //  GET method to fetch all the records
    @GetMapping("/tutorials")
    public ResponseEntity<List<Tutorial>> getAllTutorials(){
        try{
            List<Tutorial> tutorials=new ArrayList<>();
            tutorialRepository.findAll().forEach(x->tutorials.add(x));
            return new ResponseEntity<>(tutorials,HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // GET method to fetch book by id
    @GetMapping("/tutorials/{id}")
    public ResponseEntity<Tutorial> getTutorialById(@PathVariable(value="id") Long id){
        Optional<Tutorial> tutorialData=tutorialRepository.findById(id);
        if(tutorialData.isPresent()){
            return new ResponseEntity<>(tutorialData.get(),HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }





}
