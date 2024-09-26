package com.ingsw.petpal.service.impl;

import com.ingsw.petpal.dto.CommentsDTO;
import com.ingsw.petpal.exception.ResourceNotFoundException;
import com.ingsw.petpal.mapper.CommentsMapper;
import com.ingsw.petpal.model.entity.Comments;
import com.ingsw.petpal.repository.CommentsRepository;
import com.ingsw.petpal.service.CommentsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentsServiceImpl implements CommentsService {

    private final CommentsRepository commentsRepository;
    private final CommentsMapper commentsMapper;

    @Transactional(readOnly = true)
    @Override
    public List<CommentsDTO> getAll() {
        List<Comments> comments = commentsRepository.findAll();
        return comments.stream()
                .map(commentsMapper::toDTO)
                .toList();
    }

    @Transactional(readOnly = true)
    @Override
    public CommentsDTO findById(Integer id) {
        Comments comments = commentsRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Comentario no encontrado"));
        return commentsMapper.toDTO(comments);
    }

    @Transactional
    @Override
    public CommentsDTO create(CommentsDTO commentsDTO) {
        Comments comments = commentsMapper.toEntity(commentsDTO);
        comments = commentsRepository.save(comments);
        return commentsMapper.toDTO(comments);
    }

    @Transactional
    @Override
    public CommentsDTO update(Integer id, CommentsDTO updatedCommentsDTO) {
        Comments commentsFromDb = commentsRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Comentario no encontrado"));

        commentsFromDb.setContenido(updatedCommentsDTO.getContenido()); // Cambia a 'setContenido'

        commentsFromDb = commentsRepository.save(commentsFromDb);

        return commentsMapper.toDTO(commentsFromDb);
    }

    @Transactional
    @Override
    public void delete(Integer id) {
        Comments commentsFromDb = commentsRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Comentario no encontrado"));
        commentsRepository.delete(commentsFromDb);
    }
}
