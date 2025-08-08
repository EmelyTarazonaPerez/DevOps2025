package co.com.clientauth.mongo;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "users")
public class UserEntity {
    @Id
    private String id;
    private String name;
    private String email;
    private String password;
    private String phoneNumber;
    private String address;
    private String role; // e.g., "USER", "ADMIN"
}
