package model;

import jakarta.persistence.*;

@Entity
@Table
public class InventarioPocion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long cantidad;

    @ManyToOne
    @JoinColumn(name = "pocion_id",nullable = false,unique = true)
    private Pocion pocion;
}
