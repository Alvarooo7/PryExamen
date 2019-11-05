package com.example.examen.entity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "pedido")
public class Pedido {

	

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

	 @ManyToOne(cascade = CascadeType.ALL)
	 @JoinColumn(name = "user_id")    
	 private Usuario usuario;
	 
	private String  estado ;
	
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
	}
	
	
	
}
