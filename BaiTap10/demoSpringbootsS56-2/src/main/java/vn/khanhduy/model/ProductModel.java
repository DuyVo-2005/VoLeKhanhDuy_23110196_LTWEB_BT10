package vn.khanhduy.model;

import java.math.BigDecimal;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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
public class ProductModel {

	Long productId;
	
	@NotBlank(message = "Product Name is required!")
    @Size(min = 3, max = 50, message = "Product Name must be between 3 and 50 characters")
	String name;
	
	@NotBlank(message = "Description is required!")
    @Size(min = 3, max = 200, message = "Description must be between 3 and 200 characters")
	String description;
	
	@NotNull(message = "Unit price is required")
	@Min(value = 1, message = "Unit price must be greater than 0")
	double unit_price;
	
	@NotNull(message = "Discount is required")
	@Min(value = 0, message = "Discount must be greater than or equal 0")
	double discount;
	
	@NotNull(message = "Quantity is required")
	@Min(value = 0, message = "Quantity must be greater than or equal 0")
	double quantity;
	
	@NotNull(message = "Status is required")
	int status;
	
	boolean isEdit = false;
	
	private Integer userId;  // để bind từ select box
	private Long categoryId;  // để bind từ select box
}
