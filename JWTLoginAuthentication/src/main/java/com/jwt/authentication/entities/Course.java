package com.jwt.authentication.entities;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Data
@Builder
@Table(name = "courses")
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    private Long id;

    @Column(name = "Title",columnDefinition = "VARCHAR(50) NOT NULL")
    private String title;

    @Column(name = "Description",columnDefinition = "VARCHAR(300) NOT NULL")
    private String description;
}

