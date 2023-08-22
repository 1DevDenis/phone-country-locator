package com.phoneсountry.locator.model;

import jakarta.persistence.*;
import lombok.*;

@ToString
@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "country_calling_codes")
public class CountryCallingCodeEntity {

    @Id
    @SequenceGenerator(
            name = "country_calling_codes_sequence",
            sequenceName = "country_calling_codes_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            generator = "country_calling_codes_sequence",
            strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(name = "calling_code")
    private String callingCode;

    @Column(name = "country")
    private String country;

    public CountryCallingCodeEntity(String country, String callingCode) {
        this.country = country;
        this.callingCode = callingCode;
    }
}