package com.hackethon.employee.self.care.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RolesResponsibilityRepository extends CrudRepository<RolesResponsibility, Long> {

    public List<RolesResponsibility> findAllByEmployeeId(Long employeeId);
}
