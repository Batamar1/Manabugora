package ru.bell.manabu.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import ru.bell.manabu.View.ResponseData;
import ru.bell.manabu.View.ReviewView;
import ru.bell.manabu.domain.*;
import ru.bell.manabu.repository.*;
import ru.bell.manabu.service.Service;

import java.util.*;
import java.util.stream.Collectors;

@org.springframework.stereotype.Service
public class ServiceImpl implements Service {
    private final Long TWENTYMIN = 1200000L;
    private final Long ONEDAY = 86400000L;
    private final Long TWODAY = 172800000L;
    private final Long TWOWEEK = 1209600000L;
    private final Long TWOMONTH = 4838400000L;

    @Autowired
    private CommonDeckRepository deckRepository;

    @Autowired
    private UserDeckRepository userDeckRepository;

    @Autowired
    private ReviewRepository reviewRepository;

    @Override
    public ResponseData findCommonDecks(){
        return new ResponseData<>(deckRepository.findAllBy());
    }

    @Override
    public ResponseData findUserDecks(String token){return new ResponseData<>(userDeckRepository.findAllShortByToken(token));}

    @Override
    public ResponseData findCommonDeck(String id){
        return new ResponseData<>(deckRepository.findById(id));
    }

    @Override
    public ResponseData findUserDeck(String id){
        return new ResponseData<>(userDeckRepository.findBy_id(id));
    }

    @Override
    public ResponseData saveDeck(CommonDeck commonDeck){

        if(!userDeckRepository.existsById(commonDeck.get_id())) deckRepository.save(commonDeck);
        return ResponseData.ok;
    }

    @Override
    public ResponseData saveUserDeck(UserDeck userDeck) {
        userDeckRepository.save(userDeck);
        return ResponseData.ok;
    }

    @Override
    public ResponseData deleteDeck(String id) {
        deckRepository.deleteBy_id(id);
        return ResponseData.ok;
    }

    @Override
    public ResponseData deleteUserDeck(String id) {
        userDeckRepository.deleteBy_id(id);
        return ResponseData.ok;
    }

    /*
    public ResponseData saveUser(String token){
        if(!userRepository.existsById(token)) userRepository.save(new User(token));
        return ResponseData.ok;
    }
    */

    @Override
    public ResponseData deckToUser(String id, String token){
        userDeckRepository.save(commonDeckToUserDeck(deckRepository.findBy_id(id), token));
        return ResponseData.ok;
    }

    @Override
    public ResponseData findAvailableLevels(String id){
        UserDeck userDeck = userDeckRepository.findBy_id(id);
        System.out.println(userDeck.getToken());
        return new ResponseData<>(userDeck.getLevels().stream().filter((level) -> level.isAvailable()).collect(Collectors.toList()));
    }

    @Override
    public ResponseData findAvailableReviews(String token) {
        List<Review> reviews = reviewRepository.findAllByTokenAndTimeLessThan(token, new Date().getTime());
        List<UserDeck> decks = userDeckRepository.findAllByToken(token);
        List<ReviewView> reviewViewList = new ArrayList<>();
        reviews.forEach((review)-> decks.stream().filter((deck) -> deck.get_id().equals(review.getIdDeck())).findFirst().get().
                getLevels().stream().filter((level)-> level.getId().equals(review.getIdLevel())).findFirst().get().
                getCards().stream().filter((card)-> card.getId().equals(review.getIdCard())).findFirst().
                map((card -> reviewViewList.add(new ReviewView(review.get_id(),card.getName(),card.getDescription())))));
        return new ResponseData<>(reviewViewList);
    }

    @Override
    public ResponseData saveReview(Review review) {
        review.setStep(1);
        review.setTime(new Date().getTime() + TWENTYMIN);
        reviewRepository.save(review);
        return ResponseData.ok;
    }

    @Override
    public ResponseData findReviewForOneDeck(String idDeck, String token) {
        return new ResponseData<>(reviewRepository.findByIdDeckAndToken(idDeck,token));
    }

    @Override
    public ResponseData stepUpReview(String id, int step) {
        Review review = reviewRepository.findBy_id(id);
        review.setStep(review.getStep()+step);
        switch (review.getStep()){
            case 1:
                review.setTime(new Date().getTime() + TWENTYMIN);
                break;
            case 2:
                review.setTime(new Date().getTime() + TWODAY);
                break;
            case 3:
                review.setTime(new Date().getTime() + TWOWEEK);
                break;
            case 4:
                review.setTime(new Date().getTime() + TWOMONTH);
                break;
            default:
                review.setTime(new Date().getTime());
                break;
        }
        reviewRepository.save(review);
        return ResponseData.ok;
    }

    private UserDeck commonDeckToUserDeck(CommonDeck deck, String token){
        UserDeck userDeck = new UserDeck();
        userDeck.setLevels(deck.getLevels());
        userDeck.setName(deck.getName());
        userDeck.setToken(token);
        return userDeck;
    }
    /*
    public void save(){
        CommonDeck deck = new CommonDeck();
        deck.setLevel(new Level("ewq",new Card("rsds","sadas")));
        deck.setName("qwe");
        MongoWorker.mongoOps.save(deck);
    }

    public List<CommonDeck> findAll(){
        return MongoWorker.mongoOps.findAll(CommonDeck.class);
    }

    public void saveUser(String id){
        User user = new User();
        user.set_id(id);
        MongoWorker.mongoOps.insert(user);
    }

    public boolean findUser(){
        return true;
    }
    public void deckToUser(String idUser, String idDeck){
        User user = MongoWorker.mongoOps.findOne(Query.query(Criteria.where("_id").is(idUser)),User.class);
        UserDeck userDeck = new UserDeck();
        CommonDeck commonDeck = MongoWorker.mongoOps.findOne(Query.query(Criteria.where("name").is("qwe")),CommonDeck.class);
        userDeck.setLevel(commonDeck.getLevel());
        userDeck.set_id("123");
        userDeck.setName(commonDeck.getName());
        MongoWorker.mongoOps.save(userDeck);
        user.setUserDeck(userDeck);
        MongoWorker.mongoOps.save(user);
    }
    */
}
