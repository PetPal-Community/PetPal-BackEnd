package com.ingsw.petpal.service;

import com.ingsw.petpal.dto.CommentsDTO;

import java.util.List;

public interface CommentsService {
    List<CommentsDTO> getAll();

    CommentsDTO findById(Integer id);

    CommentsDTO create(CommentsDTO commentsDTO);

    CommentsDTO update(Integer id, CommentsDTO updatedCommentsDTO);

    void delete(Integer id);
}
