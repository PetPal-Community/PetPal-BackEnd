package com.ingsw.petpal.model.entity;

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

    private String email;

    private String telefono;

    private String contrasenia;

    // SEGUIDORES
    @OneToMany(mappedBy = "usuarioseguidor", cascade = CascadeType.ALL)
    private List<Followers> listaSeguidores;

    @OneToMany(mappedBy = "usuarioseguido", cascade = CascadeType.ALL)
    private List<Followers> listaSiguiendo;
    //

    // COMUNIDAD
    @OneToMany(mappedBy = "creador")
    private List<Community> comunidadesCreadas;

    @OneToMany(mappedBy = "usuario")
    private List<ComunidadUser> comunidadesUnidas;
    //

    // PUBLICACIONES
    @OneToMany(mappedBy = "usuario")
    private List<Publicaciones> publicaciones;
    //

    //LIKES
    @OneToMany(mappedBy = "usuario")
    private List<likesPublicacion> likesPublicaciones;
    //

    // COMENTARIOS REALIZADOS
    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL)
    private List<coments> comentariosRealizados;
    //

    //MASCOTAS
    @OneToMany(mappedBy = "usuario",cascade = CascadeType.ALL)
    private List<Pet> mascotas;
    //

    //RESEÑAS CREADAS
    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL )
    private List<Review> reseniasCreadas;
    //

    //MENSAJES
    @OneToMany(mappedBy = "usuario", cascade=CascadeType.ALL)
    private List<Mensajes> mensajesUsuario;
    //

    //CONTRATOS
    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL)
    private List<Contrats> contratosUsuario;
    //
}
