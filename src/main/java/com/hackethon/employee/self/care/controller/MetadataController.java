package com.hackethon.employee.self.care.controller;


import com.hackethon.employee.self.care.constant.ControllerConstant;
import com.hackethon.employee.self.care.dao.enums.CurrentDesignation;
import com.hackethon.employee.self.care.dao.enums.Gender;
import com.hackethon.employee.self.care.dao.enums.Tools;
import com.hackethon.employee.self.care.dto.EmployeeRequest;
import com.hackethon.employee.self.care.dto.MetaData;
import com.hackethon.employee.self.care.service.FilterService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@CrossOrigin(origins = "*")
@RequestMapping(ControllerConstant.API_BASE_PATH)
public class MetadataController {

    @Autowired
    FilterService filterService;

    @GetMapping("/metadata")
    public MetaData getGenderList() {
        return new MetaData(Gender.listValues(), CurrentDesignation.listValues(), Tools.listValues());
    }

    //TEMP CODE FOR TESTING
    @PostMapping("/testFilter")
    public List<EmployeeRequest> testFilter(@RequestBody List<String> skills){
        return filterService.filterEmployees(skills, 0);
    }

}
