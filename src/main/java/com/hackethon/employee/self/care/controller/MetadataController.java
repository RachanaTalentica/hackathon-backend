package com.hackethon.employee.self.care.controller;


import com.hackethon.employee.self.care.constant.ControllerConstant;
import com.hackethon.employee.self.care.dao.enums.CurrentDesignation;
import com.hackethon.employee.self.care.dao.enums.Gender;
import com.hackethon.employee.self.care.dao.enums.Tools;
import com.hackethon.employee.self.care.dto.MetaData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@CrossOrigin(origins = "*")
@RequestMapping(ControllerConstant.API_BASE_PATH)
public class MetadataController {

    @GetMapping("/metadata")
    public MetaData getGenderList() {
        return new MetaData(Gender.listValues(), CurrentDesignation.listValues(), Tools.listValues());
    }
}
