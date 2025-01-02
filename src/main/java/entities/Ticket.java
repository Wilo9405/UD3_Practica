package entities;


import jakarta.persistence.Entity;


import jakarta.persistence.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "ticket")
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ticket_id_gen")
    @SequenceGenerator(name = "ticket_id_gen", sequenceName = "ticket_id_seq", allocationSize = 1)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "\"nombre_atracción\"", nullable = false)
    private String nombreAtracción;

    @Column(name = "precio", nullable = false, precision = 10, scale = 2)
    private BigDecimal precio;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "user_id", nullable = false)
    private entities.User user;

    @Column(name = "fecha_uso")
    private LocalDate fechaUso;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombreAtracción() {
        return nombreAtracción;
    }

    public void setNombreAtracción(String nombreAtracción) {
        this.nombreAtracción = nombreAtracción;
    }

    public BigDecimal getPrecio() {
        return precio;
    }

    public void setPrecio(BigDecimal precio) {
        this.precio = precio;
    }

    public entities.User getUser() {
        return user;
    }

    public void setUser(entities.User user) {
        this.user = user;
    }

    public LocalDate getFechaUso() {
        return fechaUso;
    }

    public void setFechaUso(LocalDate fechaUso) {
        this.fechaUso = fechaUso;
    }

}

