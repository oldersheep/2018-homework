package com.xxx.design.template.dao;

import com.xxx.design.template.JdbcTemplate;
import com.xxx.design.template.RowMapper;
import com.xxx.design.template.entity.Member;

import java.sql.ResultSet;
import java.util.List;

public class MemberDao {

    private JdbcTemplate jdbcTemplate = new JdbcTemplate(null);

    public List<?> query() {
        String sql = "select * from t_member";
        return jdbcTemplate.executeQuery(sql, new RowMapper<Member>() {
            @Override
            public Member mapRow(ResultSet rs, int rowNum) throws Exception {

                Member member = new Member();
                member.setUsername(rs.getString("username"));
                member.setPassword(rs.getString("password"));
                member.setNickname(rs.getString("nickname"));
                member.setAge(rs.getInt("age"));
                member.setAddress(rs.getString("address"));

                return member;
            }
        }, null);
    }

//    public Object processResult(ResultSet rs) throws Exception {
//        Member member = new Member();
//        member.setUsername(rs.getString("username"));
//        member.setPassword(rs.getString("password"));
//        member.setNickname(rs.getString("nickname"));
//        member.setAge(rs.getInt("age"));
//        member.setAddress(rs.getString("address"));
//
//        return member;
//    }
}
