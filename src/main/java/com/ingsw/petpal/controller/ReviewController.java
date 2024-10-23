package com.ingsw.petpal.controller;

import com.ingsw.petpal.dto.CarerDTO;
import com.ingsw.petpal.dto.ReviewDTO;
import com.ingsw.petpal.dto.ReviewDetailsDTO;
import com.ingsw.petpal.service.CarerService;
import com.ingsw.petpal.service.ReviewService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/resenia")
public class ReviewController {

    private final ReviewService reviewService;


    @GetMapping
    public ResponseEntity<List<ReviewDetailsDTO>> getAllPedidos() {
        List<ReviewDetailsDTO> reviews = reviewService.getAll();
        return new ResponseEntity<List<ReviewDetailsDTO>>(reviews, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ReviewDetailsDTO> getReviewById(@PathVariable("id") Integer id) {
        ReviewDetailsDTO review = reviewService.findById(id);
        return new ResponseEntity<ReviewDetailsDTO>(review, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ReviewDetailsDTO> createReview(@Valid @RequestBody ReviewDTO review) {
        ReviewDetailsDTO createdReview = reviewService.create(review);
        return new ResponseEntity<ReviewDetailsDTO>(createdReview, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ReviewDetailsDTO> updateReview(@PathVariable("id") Integer id, @Valid @RequestBody ReviewDTO review) {
        ReviewDetailsDTO updatedReview = reviewService.update(id, review);
        return new ResponseEntity<ReviewDetailsDTO>(updatedReview, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteReview(@PathVariable("id") Integer id) {
        reviewService.delete(id);
        return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
    }
}
