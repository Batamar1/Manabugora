package ru.bell.manabu.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.bell.manabu.View.ResponseData;
import ru.bell.manabu.View.ReviewView;
import ru.bell.manabu.domain.CommonDeck;
import ru.bell.manabu.domain.Review;
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


    @GetMapping(value = "/deck_to_user/{id}/{token}")
    public ResponseData deckToUser(@PathVariable String id, @PathVariable String token){
        logger.info("DeckToUser(), id = " + id + ", token = " + token);
        return service.deckToUser(id, token);
    }

    @GetMapping(value = "/get_deck_user/{id}")
    public ResponseData getDeckUser(@PathVariable String id){
        logger.info("GetDeckUser(),id =  " + id);
        return service.findUserDeck(id);
    }

    @GetMapping(value = "/get_decks_user/{token}")
    public ResponseData getDecksUser(@PathVariable String token){
        logger.info("GetDecksUser(), token = " + token);
        return service.findUserDecks(token);
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

    @GetMapping(value = "deck/{idDeck}/reviews/{token}")
    public ResponseData getReviewForOneDeck(@PathVariable String idDeck, @PathVariable String token){
        logger.info("GetReviewForOneDeck(), idDeck = " + idDeck + ", token = " + token);
        return service.findReviewForOneDeck(idDeck, token);
    }

    @GetMapping(value = "/get_available_reviews/{token}")
    public ResponseData getReviews(@PathVariable String token){
        logger.info("GetReview, token = " + token);
        return service.findAvailableReviews(token);
    }

    @GetMapping(value = "/review/step_up/{id}/{step}")
    public ResponseData stepUpReview(@PathVariable String id, @PathVariable int step){
        logger.info("StepUpReview, id = " + id + ", step = " + step);
        return service.stepUpReview(id, step);
    }
}
