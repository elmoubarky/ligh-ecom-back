package org.sid.entitie;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
//@ToString
@Entity
public class Product implements Serializable{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;
	private String description;
	private double currentPrice;
	private boolean promotion;
	private boolean selected;
	private boolean available;
	//on va stocker le chemin d ela photo
	private String photoName;
	@Transient
	private int quantity=1;
	@ManyToOne
	private Category category;
	

}
