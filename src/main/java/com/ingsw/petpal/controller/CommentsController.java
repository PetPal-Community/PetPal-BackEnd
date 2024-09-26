package com.ingsw.petpal.controller;

import com.ingsw.petpal.dto.CommentsDTO;
import com.ingsw.petpal.service.CommentsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/comentarios")
public class CommentsController {

    private final CommentsService commentsService;

    @GetMapping
    public ResponseEntity<List<CommentsDTO>> getAllComments() {
        List<CommentsDTO> comments = commentsService.getAll();
        return new ResponseEntity<>(comments, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CommentsDTO> getCommentById(@PathVariable("id") Integer id) {
        CommentsDTO comment = commentsService.findById(id);
        return new ResponseEntity<>(comment, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<CommentsDTO> createComment(@Validated @RequestBody CommentsDTO commentsDTO) {
        CommentsDTO createdComment = commentsService.create(commentsDTO);
        return new ResponseEntity<>(createdComment, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CommentsDTO> updateComment(@PathVariable("id") Integer id,
                                                     @Validated @RequestBody CommentsDTO commentsDTO) {
        CommentsDTO updatedComment = commentsService.update(id, commentsDTO);
        return new ResponseEntity<>(updatedComment, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteComment(@PathVariable("id") Integer id) {
        commentsService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
