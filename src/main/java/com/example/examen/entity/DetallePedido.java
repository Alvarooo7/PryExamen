package com.example.examen.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "detalle_pedido", 
uniqueConstraints = {@UniqueConstraint(
		columnNames = {"plato_id", "pedido_id"})})
public class DetallePedido {

	@Id
	@GeneratedValue( strategy = GenerationType.IDENTITY )
	private Integer id  ;
	
	 @ManyToOne(cascade = CascadeType.ALL)
	    @JoinColumn(name = "plato_id")    
	    private Plato plato;
	 
	 @ManyToOne(cascade = CascadeType.ALL)
	    @JoinColumn(name = "pedido_id")    
	    private Pedido pedido;
	
	 @Column
	 private String estado;

	 
	 
	 
	public DetallePedido() {
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Plato getPlato() {
		return plato;
	}

	public void setPlato(Plato plato) {
		this.plato = plato;
	}

	public Pedido getPedido() {
		return pedido;
	}

	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}
	 
	 
	
}
