package com.yootk.action;

import com.yootk.common.action.abs.AbstractBaseAction;
import com.yootk.vo.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("/member/*")
public class MemberAction extends AbstractBaseAction {
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @RequestMapping("list")
    public Object list() {
        String sql = "SELECT mid,name,age,salary,birthday,content FROM member";
        List<Member> allMembers = this.jdbcTemplate.query(sql,
                new RowMapper<Member>() {
                    @Override
                    public Member mapRow(ResultSet rs, int rowNum) throws SQLException {
                        Member member = new Member();
                        member.setMid(rs.getString(1));
                        member.setName(rs.getString(2));
                        member.setAge(rs.getInt(3));
                        member.setSalary(rs.getDouble(4));
                        member.setBirthday(rs.getDate(5));
                        member.setContent(rs.getString(6));
                        return member;
                    }
                });
        return allMembers;
    }
    @RequestMapping("add")
    public Object add(Member member) {
        String sql = "INSERT INTO member(mid, name, age, salary, birthday, content) " +
                " VALUES (?, ?, ?, ?, ?, ?)";
        return this.jdbcTemplate.update(sql, member.getMid(), member.getName(), member.getAge(),
                member.getSalary(), member.getBirthday(), member.getContent());
    }
    @RequestMapping("delete")
    public Object delete() {
        String sql = "DELETE FROM member";
        return this.jdbcTemplate.update(sql);
    }
}
