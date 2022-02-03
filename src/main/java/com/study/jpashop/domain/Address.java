package com.study.jpashop.domain;

import lombok.Getter;

import javax.persistence.Embeddable;
import javax.persistence.Embedded;
import javax.persistence.Entity;

@Embeddable
@Getter
public class Address {
    private String city;
    private String street;
    private String zipcode;
}
