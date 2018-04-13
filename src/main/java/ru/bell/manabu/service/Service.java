package ru.bell.manabu.service;

import ru.bell.manabu.View.ResponseData;
import ru.bell.manabu.View.ReviewView;
import ru.bell.manabu.domain.CommonDeck;
import ru.bell.manabu.domain.Review;
import ru.bell.manabu.domain.UserDeck;

import java.util.stream.Collectors;

public interface Service {
    ResponseData findCommonDecks();

    ResponseData findUserDecks(String userId);

    ResponseData findCommonDeck(String id);

    ResponseData findUserDeck(String id);

    ResponseData saveDeck(CommonDeck commonDeck);

    ResponseData saveUserDeck(UserDeck userDeck);

    ResponseData deleteDeck(String id);

    ResponseData deleteUserDeck(String id);

    ResponseData deckToUser(String id, String userId);

    ResponseData findAvailableLevels(String id);

    ResponseData findAvailableReviews(String userId);

    ResponseData saveReview(Review review);

    ResponseData findReviewForOneDeck(String idDeck, String userId);

    ResponseData stepUpReview(String id, int step);
}
