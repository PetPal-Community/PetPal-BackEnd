package com.ingsw.petpal.controller;

import com.ingsw.petpal.model.entity.MedicVisit;
import com.ingsw.petpal.model.entity.User;
import com.ingsw.petpal.service.MedicVisitService;
import com.ingsw.petpal.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
@RequiredArgsConstructor
@RestController
@RequestMapping("/medicvisit")
public class MedicVisitController {

    private final MedicVisitService medicVisitService;

    @GetMapping
    public List<MedicVisit> list() {
        return medicVisitService.findAll();
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public MedicVisit create(@RequestBody MedicVisit medicVisit) {
        return medicVisitService.create(medicVisit);
    }

    @GetMapping("/{id}")
    public MedicVisit get(@PathVariable Integer id){
        return medicVisitService.findById(id);
    }

    @PutMapping("/{id}")
    public MedicVisit update(@PathVariable Integer id, @RequestBody MedicVisit medicVisit){
        return medicVisitService.update(id, medicVisit);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id){
        medicVisitService.delete(id);
    }


}