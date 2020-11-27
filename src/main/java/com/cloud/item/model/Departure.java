package com.cloud.item.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
@Entity
@Table(name = "departure")
public class Departure {
    @Id
    @GeneratedValue
    private int id;

    @Column
    private String city;
}
