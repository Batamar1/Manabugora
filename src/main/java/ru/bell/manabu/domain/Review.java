package ru.bell.manabu.domain;

import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
@Document(collection = "REVIEW")
public class Review {
    private String _id;

    private String userId;

    private String idDeck;

    private String idLevel;

    private String idCard;

    private int step;

    private Long time;
}
