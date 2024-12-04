package model;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table
public class Pocion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Column(nullable = false,length = 100,unique = true)
    private String nombre;

    @ManyToMany
    @JoinTable(
            name = "Ingredientes_Pociones",
            joinColumns = @JoinColumn(name = "pocion_id"),
            inverseJoinColumns = @JoinColumn(name = "ingrediente_id")
    )
    private List<Ingrediente> ingredientes;

    public String getNombre() {
        return nombre;
    }
}
