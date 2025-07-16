package co.com.clientauth.usecase.auth;

import co.com.clientauth.model.user.User;
import co.com.clientauth.model.user.gateways.UserRepository;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

@RequiredArgsConstructor
public class UseCaseAuthUseCase {

    private final UserRepository userRepository;

    public Optional<User> userLogin(String username, String password) {
        return userRepository.findByUsernameAndPassword(username, password);
    }

}
