package ru.bell.manabu.domain;

import org.springframework.data.mongodb.core.mapping.Document;
import ru.bell.manabu.domain.helpObject.Deck;

@Document(collection = "DECKS")
public class CommonDeck extends Deck {
}
