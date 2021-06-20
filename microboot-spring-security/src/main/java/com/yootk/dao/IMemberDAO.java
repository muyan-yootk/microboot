package com.yootk.dao;

import com.yootk.vo.Member;
import org.springframework.data.jpa.repository.JpaRepository;
public interface IMemberDAO extends JpaRepository<Member, String> {
}
