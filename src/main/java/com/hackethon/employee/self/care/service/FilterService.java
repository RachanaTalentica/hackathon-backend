package com.hackethon.employee.self.care.service;

import com.hackethon.employee.self.care.dto.EmployeeRanking;
import com.hackethon.employee.self.care.dto.EmployeeRequest;
import com.hackethon.employee.self.care.dto.RolesResponsibilityRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@Slf4j
public class FilterService {

    @Autowired
    EmployeeService employeeService;

    public List<EmployeeRequest> filterEmployees(List<String> techStacks, int yearsOfExperience) {

        List<EmployeeRanking> employeeRankings = new ArrayList<>();

        for (EmployeeRequest employeeRequest : employeeService.getEmployees()) {

            List<String> employeeSkillList = employeeRequest.
                    getRolesResponsibility().stream().
                    map(RolesResponsibilityRequest::getTools).
                    flatMap(Collection::stream).
                    collect(Collectors.toList());
            employeeSkillList.addAll(employeeRequest.getToolsTechnologyDatabaseFramework());

            List<String> employeeUniqueSkills = getUniqueSkillsList(employeeSkillList);

            double similarity = calculateJaccardSimilarity(techStacks, employeeUniqueSkills);

            if(similarity != 0.0) {

                employeeRankings.add(new EmployeeRanking(employeeRequest, similarity));
            }


        }

        employeeRankings.sort(Comparator.comparingDouble(EmployeeRanking::getSimilarity).reversed());

        return employeeRankings.stream().map(EmployeeRanking::getEmployeeRequest).collect(Collectors.toList());


    }

    private List<String> getUniqueSkillsList(List<String> skillList) {
        return skillList.stream().distinct().collect(Collectors.toList());
    }

    public double calculateJaccardSimilarity(List<String> list1, List<String> list2) {
        Set<String> set1 = toLowerCase(new HashSet<>(list1));
        Set<String> set2 = toLowerCase(new HashSet<>(list2));

        Set<String> intersection = new HashSet<>(set1);
        intersection.retainAll(set2);

        Set<String> union = new HashSet<>(set1);
        union.addAll(set2);

        if (union.isEmpty()) {
            return 0.0; // Handle the case when both lists are empty
        }

        return (double) intersection.size() / union.size();
    }

    private Set<String> toLowerCase(Set<String> set){
        Set<String> lowercasedSet = new HashSet<>();
        for (String str : set) {
            lowercasedSet.add(str.toLowerCase());
        }
        return lowercasedSet;
    }
}
