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
public enum Tools {

    JAVA("Java"),
    MYSQL("Mysql"),
    NODEJS("Node.js"),
    KAFKA("Kafka"),
    CSHARP("C#"),
    NOSQL("Nosql"),
    FIGMA("Figma"),
    PHP("PHP"),
    MONGODB("MongoDb"),
    REACT("React"),
    CPLUSPLUS("C++"),
    SPRING("Spring"),
    GO("Go"),
    ASPNET("ASP.NET"),
    EXPRESSJS("Express.js"),
    JAVASCRIPT("Javascript"),
    ANDROID("Andriod"),
    HTML("HTML"),
    DJANGO("Django"),
    RUBY("Ruby"),
    IOS("ios"),
    ANGULAR("Angular"),
    LARAVEL("Laravel"),
    VUEJS("vue.js"),
    CSS("CSS"),
    GIT("Git");

    private String value;

    @JsonCreator
    public static Tools fromValue(String text) {
        for (Tools b : Tools.values()) {
            if (String.valueOf(b.value).equals(text)) {
                return b;
            }
        }
        return null;
    }

    public static List<String> listValues(){

        List<String> list = new ArrayList<>();

        for (Tools b : Tools.values()) {
            list.add(b.getValue());
        }

        return list;
    }

    public static List<Tools> fromValues(List<String> text){

        List<Tools> toolList = new ArrayList<>();

        for (String t : text){

            for (Tools b : Tools.values()) {
                if (String.valueOf(b.value).equals(t)) {
                    toolList.add(b);
                }
            }
        }
        return toolList;
    }

    public static List<String> getValues(List<Tools> tools){

        List<String> listValues = new ArrayList<>();

        for (Tools t : tools){

            listValues.add(t.getValue());
        }

        return listValues;
    }
}
