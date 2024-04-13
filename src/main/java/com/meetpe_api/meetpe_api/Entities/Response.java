package com.meetpe_api.meetpe_api.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Response {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne()
    @JoinColumn(name = "user_id",nullable = false)
    @JsonIgnore
    private User user;
    @ManyToOne()
    @JoinColumn(name = "question_choice_id",nullable = false)
    @JsonIgnore
    private QuestionChoice questionChoice;
    @CreationTimestamp
    @Column(name = "created_at",updatable = false)
    private Date createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private Date updatedAt;
//    @ManyToOne
//    @JoinColumn(name="questionchoice_id", nullable=false)
//    private QuestionChoice questionChoice;
}
