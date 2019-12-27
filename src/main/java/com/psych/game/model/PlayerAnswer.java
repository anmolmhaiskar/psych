package com.psych.game.model;

import lombok.Getter;
import lombok.Setter;
import sun.security.pkcs11.wrapper.Constants;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "player_answers")
public class PlayerAnswer extends Auditable {

    @Getter
    @Setter
    @NotBlank
    @Column//(length = Constants.MAX_ANSWER_LENGTH)
    private String answer;

    @Getter
    @Setter
    @NotNull
    private Player player;

    @Getter
    @Setter
    private Round  round;
}
