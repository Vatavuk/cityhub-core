package hr.com.vgv.cityhub.core.db.config;

import hr.com.vgv.cityhub.core.db.CmMongo;
import hr.com.vgv.cityhub.core.db.MongoPlaces;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MongoConfiguration {
    @Bean
    public MongoPlaces registerMongoPlaces(CmMongo cmMongo) {
        return new MongoPlaces(cmMongo.value());
    }

    @Bean
    public CmMongo registerCmMongo() {
        return new CmMongo();
    }
}
