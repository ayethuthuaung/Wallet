package com.training.dat.domain.model;

import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Embeddable
public class Account {

    private String accountType;

    private String accountTypeName;
}
