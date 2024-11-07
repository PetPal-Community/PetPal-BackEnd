package com.ingsw.petpal.repository;

import com.ingsw.petpal.model.entity.Publicaciones;
import com.ingsw.petpal.model.entity.User;
import com.ingsw.petpal.model.entity.likesPublicacion;
import com.ingsw.petpal.model.entity.likesPublicacionFK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface likesPublicacionRepository extends JpaRepository<likesPublicacion, likesPublicacionFK> {
    // Aquí puedes agregar métodos personalizados si los necesitas

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO likes_publicacion (usuario_id, publicacion_id, fecha) VALUES (:userID, :publicacionID, :fechaLikeCreado)", nativeQuery = true)
    void addLikesPublicacion(@Param("userID") Integer userID, @Param("publicacionID") Integer publicacionID, @Param("fechaLikeCreado") LocalDateTime fechaLikeCreado);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM likes_publicacion WHERE usuario_id = :userID AND publicacion_id = :publicacionID", nativeQuery = true)
    void deleteByUsuarioAndPublicacion(@Param("userID") Integer userID, @Param("publicacionID") Integer publicacionID);


    @Query("SELECT lp.usuario FROM likesPublicacion lp WHERE lp.publicacion = : publicacion")
    List<User> findUsuariosByPublicacion(@Param("collection") Publicaciones publicacion);

    @Query(value = "SELECT COUNT(*) > 0 FROM likes_publicacion WHERE usuario_id = :userID AND publicacion_id = :publicacionID", nativeQuery = true)
    boolean existsByUsuarioAndPublicacion(@Param("userID") Integer userID, @Param("publicacionID") Integer publicacionID);
}
