package model;

import jakarta.persistence.*;

@Entity
@Table
public class InventarioIngrediente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long cantidad;

    @ManyToOne
    @JoinColumn(name = "ingrediente_id",nullable = false,unique = true )
    private Ingrediente ingrediente;




}
