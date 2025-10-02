package vn.khanhduy.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CategoryModel {
	Long categoryId;
	
	@NotBlank(message = "Category Name is required!")
    @Size(min = 3, max = 50, message = "Category Name must be between 3 and 50 characters")
	String name;
	//Set<ProductEntity> products;
	boolean isEdit = false;
}
