package com.ingsw.petpal.controller;


import com.ingsw.petpal.dto.UploadMediaDTO;
import com.ingsw.petpal.service.StorageService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;

@RequiredArgsConstructor
@RequestMapping("/media")
@RestController
//@PreAuthorize("hasAnyRole('ADMIN','CARER','CUSTOMER')")  // Permitir solo a CUSTOMER y AUTHOR
public class MediaController {

    private final StorageService storageService;

    @PostMapping("/upload")
    public UploadMediaDTO upload(@RequestParam("file") MultipartFile multipartFile) {
        String path = storageService.store(multipartFile);
        return new UploadMediaDTO(path);
    }

    @GetMapping("/{filename}")
    public ResponseEntity<Resource> getResource(@PathVariable String filename) throws IOException {
        Resource resource = storageService.loadAsResource(filename);
        String contentType = Files.probeContentType(resource.getFile().toPath());

        return ResponseEntity
                .ok()
                .header(HttpHeaders.CONTENT_TYPE, contentType)
                .body(resource);
    }

    @DeleteMapping("/{filename}")
    public ResponseEntity<?> deleteFile(@PathVariable String filename) {
        try {
            storageService.delete(filename);
            return ResponseEntity.ok().body("Archivo eliminado exitosamente.");
        } catch (RuntimeException e) {
            return ResponseEntity.status(500).body("Error al eliminar el archivo: " + e.getMessage());
        }
    }


}