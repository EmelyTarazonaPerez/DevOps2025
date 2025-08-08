package co.com.clientauth.mongo;

import co.com.clientauth.model.user.User;
import co.com.clientauth.model.user.gateways.UserRepository;
import co.com.clientauth.mongo.helper.AdapterOperations;
import org.reactivecommons.utils.ObjectMapper;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class MongoRepositoryAdapter extends AdapterOperations<User, UserEntity, String, MongoDBRepository> implements UserRepository {

    public MongoRepositoryAdapter(MongoDBRepository repository, ObjectMapper mapper) {
        super(repository, mapper, d -> mapper.map(d, User.class));
    }

    @Override
    public Optional<User> findByUsernameAndPassword(String username, String password) {
        return Optional.empty();
    }

    @Override
    public String register(User user) {
        return repository.save(toData(user))
                .map(UserEntity::getId)
                .block();
    }
}
