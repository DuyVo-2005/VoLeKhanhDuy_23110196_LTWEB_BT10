package vn.khanhduy.model;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import vn.khanhduy.entities.UserEntity;

@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserModel {

	String username;
	
	String roleName;
	
	UserModel(UserEntity user){
		this.username = user.getUsername();
		this.roleName = user.getRole().getName();
	}
}
