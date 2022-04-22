package com.example.AgreementMakerSpring.repository;

import com.example.AgreementMakerSpring.model.Discount;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DiscountRepository extends JpaRepository<Discount,Long> {
}
