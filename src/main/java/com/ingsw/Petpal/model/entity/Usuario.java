package com.ingsw.Petpal.model.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
@Table(name = "usuarios")
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "apellido")
    private String apellido;

    @Column(name = "email")
    private String email;

    @Column(name = "telefono")
    private String telefono;

    @Column(name = "contrasenia")
    private String contrasenia;
    //@OneToMany(mappedBy = "usuario", fetch = FetchType.LAZY) //Hace referencia al atributo "usuario" de la clase Pedido
    //private List<Pedido> pedidos;





    // COMUNIDAD
    @OneToMany(mappedBy = "creador")
    private List<Comunidad> comunidadesCreadas;


       /*
    // SEGUIDORES
    @OneToMany(mappedBy = "usuarioseguidor", cascade = CascadeType.ALL)
    private List<Followers> listaSeguidores;

    @OneToMany(mappedBy = "usuarioseguido", cascade = CascadeType.ALL)
    private List<Followers> listaSiguiendo;
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

     */
}
