package com.mandalart_server.model;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Getter
@Setter
public class Mandalart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    String title;

    @CreationTimestamp
    @Column(name = "create_date", updatable = false)
    LocalDate createDate;

    @Column(name = "end_date")
    LocalDate endDate;

    String content;

   @ManyToOne
    @JoinColumn(name = "user_id_fk", referencedColumnName = "id")
    User user;
}
