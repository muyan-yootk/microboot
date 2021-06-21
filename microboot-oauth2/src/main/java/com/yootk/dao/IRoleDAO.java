package com.yootk.dao;

import com.yootk.vo.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IRoleDAO extends JpaRepository<Role, String> {
}
