package ru.bell.manabu.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import ru.bell.manabu.domain.CommonDeck;
import ru.bell.manabu.repository.Select.ShortDeck;

import java.util.List;

@Repository
public interface CommonDeckRepository extends MongoRepository<CommonDeck, String>{
    List<ShortDeck> findAllBy();

    //CommonDeck findByLevelName(String name);

    CommonDeck findBy_id(String id);
}
