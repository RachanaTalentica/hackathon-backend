package com.hackethon.employee.self.care.dao.enums;


import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public enum Gender {
    MALE("Male"),
    FEMALE("Female"),
    GENDERQUEER("Gender Queer"),
    GENDERFLUID("Gender Fluid");

    private String value;

    @JsonCreator
    public static Gender fromValue(String text) {
        for (Gender b : Gender.values()) {
            if (String.valueOf(b.value).equals(text)) {
                return b;
            }
        }
        return null;
    }

    public static List<String> listValues(){

        List<String> list = new ArrayList<>();

        for (Gender b : Gender.values()) {
           list.add(b.getValue());
        }

        return list;
    }
}
