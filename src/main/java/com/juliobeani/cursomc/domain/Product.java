package com.juliobeani.cursomc.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity(name = "tb_product")
public class Product implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String name;
	private Double price;
	
	@ManyToMany
	@JoinTable(name = "TB_PRODUCT_CATEGORY", // Nome da tabela de associacao criada
		joinColumns = @JoinColumn(name = "product_id"), // Declaracao da primeira chave estrangeira
		inverseJoinColumns = @JoinColumn(name = "category_id") // Segunda chave estrangeira da tabela
	)
	private List<Category> categories = new ArrayList<>();
	
	@OneToMany(mappedBy = "id.product")
	private Set<OrderItem> itens = new HashSet<>();
	
	public Product () {}

	public Product(Integer id, String name, Double price) {
		this.id = id;
		this.name = name;
		this.price = price;
	}
	
	public List<Order> getOrders() {
		List<Order> list = new ArrayList<>();
		for(OrderItem x : itens) {
			list.add(x.getOrder());
		}
		return list;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}
	
	@JsonBackReference
	public List<Category> getCategories() {
		return categories;
	}

	public Set<OrderItem> getItens() {
		return itens;
	}

	public void setItens(Set<OrderItem> itens) {
		this.itens = itens;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Product other = (Product) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
}