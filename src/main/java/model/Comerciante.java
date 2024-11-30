package model;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table
public class Comerciante {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false,length = 100)
    private String nombre;

    @Column(name = "tipo",nullable = false)
    private TipoComerciante tipoComerciante;

    @ManyToMany
    @JoinTable(
            name = "ComercianteIngrediente",
            joinColumns = @JoinColumn(name = "comerciante_id"),
            inverseJoinColumns = @JoinColumn(name = "ingrediente_id")
    )
    private List<Ingrediente> ingredientes;


}
