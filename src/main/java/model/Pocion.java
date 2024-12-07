package model;

import jakarta.persistence.*;

import java.util.List;
import java.util.Objects;

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

    public Long getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public List<Ingrediente> getIngredientes() {
        return ingredientes;
    }

    public double obtenerCostoIngrediente(){
        double costo=0;
        for(Ingrediente ingrediente:ingredientes){
            costo+=ingrediente.getPrecioCompra();
        }
        return  costo;
    }

    @Override
    public String toString(){

        return "Poción: "+nombre+"\n"
                +"Ingredientes necesarios: \n"
                +mostrarIngredientesNecesarios()
                +"\n";
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Pocion pocion = (Pocion) o;
        return Objects.equals(id, pocion.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    private String mostrarIngredientesNecesarios(){
        StringBuilder listaIngredientes= new StringBuilder();
        for (Ingrediente ingrediente : ingredientes) {
            listaIngredientes.append("- ").append(ingrediente.getNombre()).append(" (Tipo ").append(ingrediente.getTipoIngrediente()).append(")\n");

        }
        return listaIngredientes.toString();
    }




}
