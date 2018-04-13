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

    @GetMapping(value = "/decks")
    public ResponseData getDecks() {
        return service.findCommonDecks();
    }

    @GetMapping(value = "/deck/{id}")
    public ResponseData getDeck(@PathVariable String id) {
        return service.findCommonDeck(id);
    }

    @PostMapping(value="/deck/create")
    public ResponseData createDeck(@RequestBody CommonDeck commonDeck){
        return service.saveDeck(commonDeck);
    }


    @GetMapping(value = "/deck_to_user/{id}/{token}")
    public ResponseData deckToUser(@PathVariable String id, @PathVariable String token){
        return service.deckToUser(id, token);
    }

    @GetMapping(value = "/get_deck_user/{id}")
    public ResponseData getDeckUser(@PathVariable String id){
        logger.info("ID: " + id);
        return service.findUserDeck(id);
    }

    @GetMapping(value = "/get_decks_user/{token}")
    public ResponseData getDecksUser(@PathVariable String token){
        return service.findUserDecks(token);
    }

    @GetMapping(value = "/get_public_level/{id}")
    public ResponseData getLevels(@PathVariable String id){
        return service.findAvailableLevels(id);
    }

    @PostMapping(value="/review/create")
    public ResponseData createReview(@RequestBody Review review){
        return service.saveReview(review);
    }

    @GetMapping(value = "/get_public_reviews/{token}")
    public ResponseData getReviews(@PathVariable String token){
        return service.findAvailableReviews(token);
    }

    @GetMapping(value = "/review/step_up/{id}/{step}")
    public ResponseData stepUpReview(@PathVariable String id, @PathVariable int step){
        return service.stepUpReview(id, step);
    }
}
