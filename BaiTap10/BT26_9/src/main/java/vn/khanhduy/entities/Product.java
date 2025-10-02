package vn.khanhduy.entities;

import java.io.Serializable;
import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "products")
public class Product implements Serializable {/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Integer id;
	
	@Column(columnDefinition = "nvarchar(255)",  nullable = false)
	String title;
	
	@Column(columnDefinition = "int")
	Integer quantity;
	
	@Column(columnDefinition = "nvarchar(max)")
	String description;//desc bị trùng từ khóa
	
	@Column(precision = 18, scale = 2, nullable = false)
	BigDecimal price;
	
	@ManyToOne
	@JoinColumn(name = "userId", referencedColumnName = "id")
	@JsonBackReference
	User user;
}
