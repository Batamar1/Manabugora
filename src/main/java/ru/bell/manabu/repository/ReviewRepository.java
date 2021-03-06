package ru.bell.manabu.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import ru.bell.manabu.domain.Review;

import java.util.List;

@Repository
public interface ReviewRepository extends MongoRepository<Review,String>{
    List<Review> findAllByUserIdAndTimeLessThan(String UserId, Long time);

    Review findBy_id(String id);

    Review findByIdDeckAndUserId(String id, String UserId);
}
