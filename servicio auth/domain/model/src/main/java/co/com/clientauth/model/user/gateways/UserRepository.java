package co.com.clientauth.model.user.gateways;

import co.com.clientauth.model.user.User;

import java.util.Optional;

public interface UserRepository {

    Optional<User> findByUsernameAndPassword(String username, String password);
    String register(User user);

}
