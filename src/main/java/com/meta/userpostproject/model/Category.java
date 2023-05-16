package com.meta.userpostproject.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "Category")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Short id;
    @Column(name = "category_name",length = 100,nullable = false)
    private String category_name;
    @Column(name = "Description",nullable = false)
    private String description;
    @Column(name = "Status")
    private boolean status;



}
