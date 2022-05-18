package lt.skarb.backend.repository;

import lt.skarb.backend.model.entity.Wonder;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WonderRepository extends ReactiveMongoRepository<Wonder, String> {}
