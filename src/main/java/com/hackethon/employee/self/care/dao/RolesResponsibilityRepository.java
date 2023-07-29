package com.hackethon.employee.self.care.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RolesResponsibilityRepository extends CrudRepository<RolesResponsibilities, Long> {
}
