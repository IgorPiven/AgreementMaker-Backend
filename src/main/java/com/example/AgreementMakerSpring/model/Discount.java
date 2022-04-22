package com.example.AgreementMakerSpring.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "discounts")
@Data
@NoArgsConstructor

public class Discount {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "discount")
    private String discount;

    @Column(name = "descr_ru")
    private String descrRu;

    @Column(name = "descr_en")
    private String descrEn;

    public Discount(String discount, String descrRu, String descrEn) {
        this.discount = discount;
        this.descrRu = descrRu;
        this.descrEn = descrEn;
    }
}
