package com.ingsw.petpal.service;

import com.ingsw.petpal.dto.MensajeDetailsDTO;
import com.ingsw.petpal.dto.MensajesCreateUpdateDTO;
import com.ingsw.petpal.dto.ReviewDTO;
import com.ingsw.petpal.dto.ReviewDetailsDTO;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface ReviewService {
   List<ReviewDetailsDTO> getAll();

   ReviewDetailsDTO findById(Integer id);

   ReviewDetailsDTO create(ReviewDTO review);

   ReviewDetailsDTO update(Integer id, ReviewDTO updatedReview);

   void delete(Integer id);
}
