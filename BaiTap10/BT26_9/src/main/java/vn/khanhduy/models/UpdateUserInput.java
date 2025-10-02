package vn.khanhduy.models;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UpdateUserInput {

	@NotNull(message = "ID không được để trống")
	Integer id;
	
	@Email(message = "Email không hợp lệ")
	@Size(min = 2, message = "Email phải có ít nhất 2 ký tự")
	String email;
	
	@Size(min = 2, message = "Tên phải có ít nhất 2 ký tự")
	String fullname;
	
	@Size(min = 6, message = "Mật khẩu phải có ít nhất 6 ký tự")
	String password;
	
	@Pattern(regexp = "^\\d{10,11}$", message = "Số điện thoại phải có 10 - 11 chữ số")
	String phone;
}
