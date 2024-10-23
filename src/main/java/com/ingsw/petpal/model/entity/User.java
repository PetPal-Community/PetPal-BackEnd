package com.ingsw.petpal.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table (name = "usuarios")
public class User  {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String nombre;

    private String apellido;

    private String telefono;


    // Asignacion de UserGeneral:
    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private UserGeneral user;

    // SEGUIDORES
    @JsonIgnore
    @OneToMany(mappedBy = "usuarioseguidor", cascade = CascadeType.ALL)
    private List<Followers> listaSeguidores;

    @JsonIgnore
    @OneToMany(mappedBy = "usuarioseguido", cascade = CascadeType.ALL)
    private List<Followers> listaSiguiendo;
    //

    // COMUNIDAD
    @JsonIgnore
    @OneToMany(mappedBy = "creador")
    private List<Community> comunidadesCreadas;

    @JsonIgnore
    @OneToMany(mappedBy = "usuario")
    private List<ComunidadUser> comunidadesUnidas;
    //

    // PUBLICACIONES
    @JsonIgnore
    @OneToMany(mappedBy = "usuario")
    private List<Publicaciones> publicaciones;
    //

    //LIKES
    @JsonIgnore
    @OneToMany(mappedBy = "usuario")
    private List<likesPublicacion> likesPublicaciones;
    //

    // COMENTARIOS REALIZADOS
    @JsonIgnore
    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL)
    private List<coments> comentariosRealizados;
    //

    //MASCOTAS
    @JsonIgnore
    @OneToMany(mappedBy = "usuario",cascade = CascadeType.ALL)
    private List<Pet> mascotas;
    //

    //RESEÑAS CREADAS
    @JsonIgnore
    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL )
    private List<Review> reseniasCreadas;
    //

    //MENSAJES
    @JsonIgnore
    @OneToMany(mappedBy = "usuario", cascade=CascadeType.ALL)
    private List<Mensajes> mensajesUsuario;
    //

    //CONTRATOS
    @JsonIgnore
    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL)
    private List<Contrats> contratosUsuario;
    //
}
