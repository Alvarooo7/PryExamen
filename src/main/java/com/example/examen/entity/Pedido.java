package com.example.examen.entity;


import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "pedido")
public class Pedido {

	

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

	 @ManyToOne(fetch = FetchType.LAZY)
	 @JoinColumn(name = "user_id")    
	 private Usuario usuario;
	@Column 
	private String  estado ;
	
	@OneToMany (mappedBy = "pedido",fetch = FetchType.LAZY)
	List<DetallePedido> detalles; 
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public Usuario getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	public Pedido() {
		
		this.detalles = new ArrayList<DetallePedido>();
	}
	public List<DetallePedido> getDetalles() {
		return detalles;
	}
	public void setDetalles(List<DetallePedido> detalles) {
		this.detalles = detalles;
	}
	
	
	
}
