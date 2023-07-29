package com.hackethon.employee.self.care.dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class MetaData {

    private List<String> gender;
    private List<String> currentDesignation;
    private List<String> tools;

}
