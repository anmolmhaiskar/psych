package com.psych.game.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.util.Map;

@Entity
@Table(name="rounds")
public class Round extends Auditable {

    @Getter
    @Setter
    @NotNull
    private int roundNumber;

    @ManyToOne
    @Getter
    @Setter
    private Game game;

    @ManyToOne
    @Getter
    @Setter
    private Question question;

    @ManyToMany
    @Getter
    @Setter
    private Map<Player, PlayerAnswer> playerAnswers;
}
