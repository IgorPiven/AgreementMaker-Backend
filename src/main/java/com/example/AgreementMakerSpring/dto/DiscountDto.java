package com.example.AgreementMakerSpring.dto;

import com.example.AgreementMakerSpring.model.Discount;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class DiscountDto {

    private Long id;
    private String discount;
    private String descrRu;
    private String descrEn;

    public DiscountDto(Discount discount) {
        this.id = discount.getId();
        this.discount = discount.getDiscount();
        this.descrRu = discount.getDescrRu();
        this.descrEn = discount.getDescrEn();
    }

}
