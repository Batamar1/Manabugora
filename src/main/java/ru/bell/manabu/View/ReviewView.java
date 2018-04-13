package ru.bell.manabu.View;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
@Getter
@Setter
public class ReviewView {
    private String id;
    private String name;
    private String description;
}
