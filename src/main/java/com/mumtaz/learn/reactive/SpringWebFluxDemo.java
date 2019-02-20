package com.mumtaz.learn.reactive;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.CreateCollectionOptions;
import com.mumtaz.learn.reactive.domain.User;
import com.mumtaz.learn.reactive.respository.UserRepository;
import com.mumtaz.learn.reactive.service.BackPressureDemoClient;
import de.flapdoodle.embed.mongo.MongodExecutable;
import de.flapdoodle.embed.mongo.MongodProcess;
import de.flapdoodle.embed.mongo.MongodStarter;
import de.flapdoodle.embed.mongo.config.IMongodConfig;
import de.flapdoodle.embed.mongo.config.MongodConfigBuilder;
import de.flapdoodle.embed.mongo.config.Net;
import de.flapdoodle.embed.mongo.distribution.Version;
import de.flapdoodle.embed.process.runtime.Network;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.UUID;
import java.util.stream.Stream;

@SpringBootApplication
public class SpringWebFluxDemo {
    public static void main(String[] args) {
        SpringApplication.run(SpringWebFluxDemo.class, args);
    }

    @Bean
    CommandLineRunner populateUserRepo(UserRepository userRepository) throws Exception{
        System.out.println("adding users to mongo db");

        createMongoRepo();
        return args -> {
            userRepository.deleteAll().subscribe(
                    null,
                    null,
                    () -> Stream.of("Jack", "Brian", "Peter", "Eric", "Maria")
                            .map(name -> new User(UUID.randomUUID().toString(), name))
                            .forEach( u -> userRepository.save(u)
                                    .map(v -> "write: " + v)
                                    .subscribe(System.out::println))
            );
        };
    }

	private void createMongoRepo() throws Exception {
		MongodStarter mongodStarter = MongodStarter.getDefaultInstance();
		String bindIP = "localhost";
		int port = 5555;
		IMongodConfig mongoConfig = new MongodConfigBuilder()
				.version(Version.Main.PRODUCTION)
				.net(new Net( bindIP, port, Network.localhostIsIPv6()))
				.build();

		MongodExecutable mongodExecutable = null;

		try {
			mongodExecutable = mongodStarter.prepare(mongoConfig);
			MongodProcess mongod = mongodExecutable.start();
			MongoClient mongo = new MongoClient(bindIP, port);
			MongoDatabase db = mongo.getDatabase("test");
			CreateCollectionOptions createCollectionOptions = new CreateCollectionOptions();
			createCollectionOptions.capped(true);
			createCollectionOptions.sizeInBytes(9999L);

			db.createCollection("user", createCollectionOptions);
			db.getCollection("user");

			mongo.close();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
















    //@Component
    private class MyRunner implements CommandLineRunner {

        @Autowired
        BackPressureDemoClient webClientTransport;

        @Override
        public void run(String... args) throws Exception {
            webClientTransport.doGet();
        }
    }
}
