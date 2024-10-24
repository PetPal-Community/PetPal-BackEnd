package com.ingsw.petpal.repository;

import com.ingsw.petpal.model.entity.Community;
import com.ingsw.petpal.model.entity.ComunidadUser;
import com.ingsw.petpal.model.entity.ComunidadUserPK;
import com.ingsw.petpal.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

public interface ComunidadUserRepository extends JpaRepository<ComunidadUser, ComunidadUserPK> {
    // ADD
    @Modifying
    @Transactional
    @Query(value = "INSERT INTO comunidad_usuario(usuario_id, comunidad_id, fecha_que_se_unio) " +
            "VALUES(:usuarioId, :comunidadId, :FechaDeUnion)",
            nativeQuery = true
    )
    void addUsuarioToComunidad(@Param("usuarioId") Integer usuarioId,
                         @Param("comunidadId") Integer comunidadId,
                         @Param("FechaDeUnion") LocalDateTime FechaDeUnion);

    // DELETE
    @Modifying
    @Transactional
    @Query(value = "DELETE FROM comunidad_usuario " +
            "WHERE usuario_id=:usuarioId AND comunidad_id=:comunidadId",
            nativeQuery = true
    )
    void deleteByUsuarioAndComunidad(@Param("usuarioId") Integer usuarioId,
                               @Param("comunidadId") Integer comunidadId);

    // QUERY
    @Query("SELECT cu.usuario FROM ComunidadUser cu WHERE cu.comunidad = :comunidad")
    List<User> findUsuariosByComunidad(@Param("comunidad") Community comunidad);

}
