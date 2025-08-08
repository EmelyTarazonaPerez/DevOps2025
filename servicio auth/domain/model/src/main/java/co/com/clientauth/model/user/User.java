package co.com.clientauth.model.user;
import lombok.*;
//import lombok.NoArgsConstructor;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class User {
    private String id;
    private String name;
    private String email;
    private String password;
    private String phoneNumber;
    private String address;
    private String role; // e.g., "USER", "ADMIN"
}
