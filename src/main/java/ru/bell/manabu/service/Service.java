package ru.bell.manabu.service;

import ru.bell.manabu.View.ResponseData;
import ru.bell.manabu.View.ReviewView;
import ru.bell.manabu.domain.CommonDeck;
import ru.bell.manabu.domain.Review;
import ru.bell.manabu.domain.UserDeck;

import java.util.stream.Collectors;

public interface Service {
    ResponseData findCommonDecks();

    ResponseData findUserDecks(String token);

    ResponseData findCommonDeck(String id);

    ResponseData findUserDeck(String id);

    ResponseData saveDeck(CommonDeck commonDeck);

    ResponseData deckToUser(String id, String token);

    ResponseData findAvailableLevels(String id);

    ResponseData findAvailableReviews(String token);

    ResponseData saveReview(Review review);

    ResponseData stepUpReview(String id, int step);
}
