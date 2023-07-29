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
public enum CurrentDesignation {

    SOFTWARE_DEVELOPER("Software Developer"),
    ASSOCIATE_SOFTWARE_DEVELOPER("Associate Software Developer"),
    ARCHITECT("Architect"),
    SENIOR_SOFTWARE_DEVELOPER("Senior Software Developer"),
    DEVELOPMENT_LEAD("Development Lead");
    
    private String value;

    @JsonCreator
    public static CurrentDesignation fromValue(String text) {
        for (CurrentDesignation b : CurrentDesignation.values()) {
            if (String.valueOf(b.value).equals(text)) {
                return b;
            }
        }
        return null;
    }

    public static List<String> listValues(){

        List<String> list = new ArrayList<>();

        for (CurrentDesignation b : CurrentDesignation.values()) {
            list.add(b.getValue());
        }

        return list;
    }
}
