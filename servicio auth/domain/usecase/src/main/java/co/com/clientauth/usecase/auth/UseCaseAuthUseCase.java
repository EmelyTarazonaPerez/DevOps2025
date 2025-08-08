package co.com.clientauth.usecase.auth;

import co.com.clientauth.model.user.User;
import co.com.clientauth.model.user.gateways.UserRepository;
import java.util.Optional;

public class UseCaseAuthUseCase {

    private final UserRepository userRepository;

    public UseCaseAuthUseCase(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Optional<User> userLogin(String username, String password) {
        return userRepository.findByUsernameAndPassword(username, password);
    }

    public String userRegister(User user) {
        return userRepository.register(user);
    }

}
