package co.com.clientauth.mongo.config;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class MongoDBSecret {
    private final String uri;
}
