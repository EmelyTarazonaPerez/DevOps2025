package co.com.clientauth.mongo;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.data.repository.query.ReactiveQueryByExampleExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface MongoDBRepository extends ReactiveMongoRepository<UserEntity, String>, ReactiveQueryByExampleExecutor<UserEntity> {
}
