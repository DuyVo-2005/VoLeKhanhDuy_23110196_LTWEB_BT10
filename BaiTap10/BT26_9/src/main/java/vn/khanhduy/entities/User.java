package vn.khanhduy.entities;

import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "users")
public class User implements Serializable{/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Integer id;

	@Column(columnDefinition = "nvarchar(255)")
	String fullname;
	
	@Column(columnDefinition = "varchar(255) not null")
	String email;
	
	@Column(columnDefinition = "varchar(255) not null")
	String password;
	
	@Column(columnDefinition = "varchar(20)")
	String phone;
	
	@OneToMany(mappedBy = "user")
	@JsonManagedReference
	List<Product> products; 
	
	@ManyToMany
	@JoinTable(
		name = "User_Category",
		joinColumns = @JoinColumn(name = "user_id"), //khóa ngoại tham chiếu user
		inverseJoinColumns = @JoinColumn(name = "category_id")//khóa ngoại tham chiếu category
	)
	Set<Category> categories = new HashSet<>();
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "role_id", referencedColumnName = "id")
	Role role;
}
