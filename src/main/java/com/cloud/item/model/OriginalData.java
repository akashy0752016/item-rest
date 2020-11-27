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
@Table(name = "original_data")
public class OriginalData {
    @Id
    @GeneratedValue
    private Integer id;

    @Column
    private String data;
}
