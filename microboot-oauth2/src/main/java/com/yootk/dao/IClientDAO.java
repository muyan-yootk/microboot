package com.yootk.dao;

import com.yootk.vo.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IClientDAO extends JpaRepository<Client, String> {
}
