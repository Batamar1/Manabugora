package ru.bell.manabu.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.bell.manabu.View.ResponseData;
import ru.bell.manabu.View.ReviewView;
import ru.bell.manabu.domain.CommonDeck;
import ru.bell.manabu.domain.Review;
import ru.bell.manabu.domain.UserDeck;
import ru.bell.manabu.service.Service;
import ru.bell.manabu.service.impl.ServiceImpl;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = "/api/v1/", produces = APPLICATION_JSON_VALUE)
public class Controller {
    @Autowired
    private Service service;

    private final Logger logger = LoggerFactory.getLogger(Controller.class);

    @GetMapping(value = "/decks/get")
    public ResponseData getDecks() {
        logger.info("getDecks()");
        return service.findCommonDecks();
    }

    @GetMapping(value = "/deck/{id}")
    public ResponseData getDeck(@PathVariable String id) {
        logger.info("GetDeck(), id = " + id);
        return service.findCommonDeck(id);
    }

    @PostMapping(value="/deck/create")
    public ResponseData createDeck(@RequestBody CommonDeck commonDeck){
        logger.info("CreateDeck(), commonDeck = " + commonDeck.toString());
        return service.saveDeck(commonDeck);
    }

    @GetMapping(value = "/deck/delete/{id}")
    public ResponseData deleteDeck(@PathVariable String id){
        logger.info("DeleteDeck(), id = " + id);
        return service.deleteDeck(id);
    }

    @GetMapping(value = "/user_deck/delete/{id}")
    public ResponseData deleteUserDeck(@PathVariable String id){
        logger.info("DeleteUserDeck(), id = " + id);
        return service.deleteUserDeck(id);
    }

    @GetMapping(value = "/deck_to_user/{id}/{userId}")
    public ResponseData deckToUser(@PathVariable String id, @PathVariable String userId){
        logger.info("DeckToUser(), id = " + id + ", userId = " + userId);
        return service.deckToUser(id, userId);
    }

    @GetMapping(value = "/user_deck/get/{id}")
    public ResponseData getUserDeck(@PathVariable String id){
        logger.info("GetUserDeck(),id =  " + id);
        return service.findUserDeck(id);
    }

    @PostMapping(value = "/user_deck/update")
    public ResponseData updateUserDeck(@RequestBody UserDeck userDeck){
        logger.info("UpdateUserDeck, userDeck" + userDeck.toString());
        return service.saveUserDeck(userDeck);
    }

    @GetMapping(value = "/user_decks/get/{userId}")
    public ResponseData getUserDecks(@PathVariable String userId){
        logger.info("GetUserDecks(), userId = " + userId);
        return service.findUserDecks(userId);
    }

    @GetMapping(value = "/deck/{id}/lessons")
    public ResponseData getLevels(@PathVariable String id){
        logger.info("GetLevels(), id = " + id);
        return service.findAvailableLevels(id);
    }

    @PostMapping(value="/review/create")
    public ResponseData createReview(@RequestBody Review review){
        logger.info("CreateReview, review = " + review.toString());
        return service.saveReview(review);
    }

    @GetMapping(value = "deck/{idDeck}/reviews/{userId}")
    public ResponseData getReviewForOneDeck(@PathVariable String idDeck, @PathVariable String userId){
        logger.info("GetReviewForOneDeck(), idDeck = " + idDeck + ", userId = " + userId);
        return service.findReviewForOneDeck(idDeck, userId);
    }

    @GetMapping(value = "/get_available_reviews/{userId}")
    public ResponseData getReviews(@PathVariable String userId){
        logger.info("GetReview, userId = " + userId);
        return service.findAvailableReviews(userId);
    }

    @GetMapping(value = "/review/step_up/{id}/{step}")
    public ResponseData stepUpReview(@PathVariable String id, @PathVariable int step){
        logger.info("StepUpReview, id = " + id + ", step = " + step);
        return service.stepUpReview(id, step);
    }
}
