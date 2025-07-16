package co.com.clientauth.api;
import co.com.clientauth.usecase.auth.UseCaseAuthUseCase;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * API Rest controller.
 * 
 * Example of how to declare and use a use case:
 * <pre>
 * private final MyUseCase useCase;
 * 
 * public String commandName() {
 *     return useCase.execute();
 * }
 * </pre>
 */
@RestController
@RequestMapping(value = "/api", produces = MediaType.APPLICATION_JSON_VALUE)
@AllArgsConstructor
public class ApiRest {

    private final UseCaseAuthUseCase useCaseAuthUseCase;// Example of a use case, replace with actual use case

    @GetMapping(path = "/login")
    public ResponseEntity<String> commandName() {
        try {
            // Example usage of the use case
            useCaseAuthUseCase.userLogin("username", "password"); // Example usage, replace with actual parameters
            return ResponseEntity.ok("API is running"); // Replace with actual logic
        } catch (Exception e) {
            // Handle exceptions appropriately
            return ResponseEntity.status(500).body("Internal Server Error: " + e.getMessage());
        }
    }
}
