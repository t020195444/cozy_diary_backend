package com.example.demo.dao.impl;

import com.example.demo.dao.TrackerDao;
import com.example.demo.entity.Tracker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

@Repository
public class TrackerDaoImpl implements TrackerDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private NamedParameterJdbcTemplate jdbcNameTemplate;

    @Override
    public Integer insert(Tracker tracker) {
        String sql = " INSERT INTO cozy_diary.tracker ( "
            + "	tracker1, tracker2, track_time"
            + " ) "
            + " VALUE ( "
            + "	:tracker1, :tracker2, :track_time "
            + " ) ";
        SqlParameterSource paramSource = new BeanPropertySqlParameterSource(tracker);
        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcNameTemplate.update(sql, paramSource, keyHolder);
        return keyHolder.getKey().intValue();
    }
}
