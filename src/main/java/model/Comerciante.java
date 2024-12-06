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

    @Enumerated(EnumType.STRING)//no he especificado el enum , y es un gran error
    //ya que al estar en la base de datos como string en ver de ordinal y aqui el por defectome es el ordinal
    //hibernate sobrescribe la base de datos para acerlo ordinal mostrando numeros en vez de texto petandome luego al obtener comerciartnte
    //error potencialmente grave,
    @Column(name = "tipo",nullable = false)
    private TipoComerciante tipoComerciante;

    @ManyToMany
    @JoinTable(
            name = "ComercianteIngrediente",
            joinColumns = @JoinColumn(name = "comerciante_id"),
            inverseJoinColumns = @JoinColumn(name = "ingrediente_id")
    )
    private List<Ingrediente> ingredientes;

    @Override
    public String toString(){
        return "Comerciante: "+nombre+" (Tipo "+tipoComerciante+") - Ingrediente que puede vender: "+ingredientes.size();
    }





}
