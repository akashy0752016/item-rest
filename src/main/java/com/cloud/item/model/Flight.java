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
@Table(name = "flight")
public class Flight {
    @Id
    @GeneratedValue
    private int id;

    @Column
    private int number;

    @Column
    private String airline;

    @Column
    private String departureTime;

    @Column
    private String arrivalTime;

    @Column
    private String price;
}
