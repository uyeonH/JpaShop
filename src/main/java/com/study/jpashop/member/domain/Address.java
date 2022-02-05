package com.study.jpashop.member.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;
import javax.persistence.Embedded;
import javax.persistence.Entity;
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
@Getter
public class Address {
    private String city;
    private String street;
    private String zipcode;
}
