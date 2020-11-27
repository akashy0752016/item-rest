package com.cloud.item.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

@NoArgsConstructor
@Data
@AllArgsConstructor
@ToString
@Entity
@Table(name = "arrival")
public class Arrival {
    @Id
    @GeneratedValue
    private int id;

    @Column
    private String city;
}
