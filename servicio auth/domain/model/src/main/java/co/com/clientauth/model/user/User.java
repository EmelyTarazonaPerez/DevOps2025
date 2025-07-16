package co.com.clientauth.model.user;
import lombok.Builder;
import lombok.AllArgsConstructor;
import lombok.Getter;
//import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
//@NoArgsConstructor
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
    private boolean isActive; // Indicates if the user account is active
}
