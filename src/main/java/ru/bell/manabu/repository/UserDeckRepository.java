package ru.bell.manabu.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import ru.bell.manabu.domain.UserDeck;
import ru.bell.manabu.repository.Select.ShortDeck;

import java.util.List;

@Repository
public interface UserDeckRepository extends MongoRepository<UserDeck, String>{
    //UserDeck findBy_idAndLevelAvailable(String _id, boolean levelAvaiable);
    UserDeck findBy_id(String _id);

    List<ShortDeck> findAllShortByToken(String token);

    List<UserDeck> findAllByToken(String token);

}
