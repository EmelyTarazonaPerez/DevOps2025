package co.com.clientauth.mongo.helper;

import co.com.clientauth.mongo.MongoDBRepository;
import co.com.clientauth.mongo.MongoRepositoryAdapter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.reactivecommons.utils.ObjectMapper;
import org.springframework.data.domain.Example;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class AdapterOperationsTest {

    @Mock
    private MongoDBRepository repository;

    @Mock
    private ObjectMapper objectMapper;

    private MongoRepositoryAdapter adapter;

    private Object entity;
    private Flux<Object> entities;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        when(objectMapper.map("value", Object.class)).thenReturn("value");

        adapter = new MongoRepositoryAdapter(repository, objectMapper);

        entity = "value";
        entities = Flux.just(entity);
    }

    @Test
    void testSave() {
        when(repository.save(entity)).thenReturn(Mono.just("value"));

        StepVerifier.create(adapter.save(entity))
                .expectNext("value")
                .verifyComplete();
    }

    @Test
    void testSaveAll() {
        when(repository.saveAll(any(Flux.class))).thenReturn(entities);

        StepVerifier.create(adapter.saveAll(entities))
                .expectNext("value")
                .verifyComplete();
    }

    @Test
    void testFindById() {
        when(repository.findById("key")).thenReturn(Mono.just(entity));

        StepVerifier.create(adapter.findById("key"))
                .expectNext("value")
                .verifyComplete();
    }

    @Test
    void testFindByExample() {
        when(repository.findAll(any(Example.class))).thenReturn(entities);

        StepVerifier.create(adapter.findByExample(entity))
                .expectNext("value")
                .verifyComplete();
    }

    @Test
    void testFindAll() {
        when(repository.findAll()).thenReturn(entities);

        StepVerifier.create(adapter.findAll())
                .expectNext("value")
                .verifyComplete();
    }

    @Test
    void testDeleteById() {
        when(repository.deleteById("key")).thenReturn(Mono.empty());

        StepVerifier.create(adapter.deleteById("key"))
                .verifyComplete();
    }
}
