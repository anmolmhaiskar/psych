package com.psych.game.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.URL;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "players")
public class Player extends Auditable {

    @Getter
    @Setter
    @NotBlank
    private String name;

    @Getter @Setter @URL
    private String psychfaceURL;

    @Getter @Setter @URL
    private String picURL;

    @OneToOne
    @Getter
    @Setter
    private Stats stats;

    @ManyToMany(mappedBy = "players")
    @Getter
    @Setter
    private List<Game> games;
}
