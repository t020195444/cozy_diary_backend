//package com.example.demo.dao.impl;
//
//import com.example.demo.dao.UserDao;
//import com.example.demo.dto.FollowerResponse;
//import com.example.demo.dto.TrackerResponse;
//import com.example.demo.entity.User;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.jdbc.core.BeanPropertyRowMapper;
//import org.springframework.jdbc.core.JdbcTemplate;
//import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
//import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
//import org.springframework.jdbc.core.namedparam.SqlParameterSource;
//import org.springframework.jdbc.support.GeneratedKeyHolder;
//import org.springframework.jdbc.support.KeyHolder;
//import org.springframework.stereotype.Repository;
//
//import java.util.List;
//
//@Repository
//public class UserDaoImpl implements UserDao {
//    @Autowired
//    private JdbcTemplate jdbcTemplate;
//
//    @Autowired
//    private NamedParameterJdbcTemplate jdbcNameTemplate;
//
//    @Override
//    public Integer insert(User user){
//        // TODO Auto-generated method stub
//        String sql = " INSERT INTO cozy_diary.user ( "
//            + "	uid, name, age, "
//            + "	sex, introduction, pic, birth,  "
//            + " create_time, email "
//            + " ) "
//            + " VALUE ( "
//            + "	:uid, :name, :age, "
//            + "	:sex, :introduction, :pic, :birth, "
//            + "	:create_time, :email "
//            + " ) ";
//        SqlParameterSource paramSource = new BeanPropertySqlParameterSource(user);
//        KeyHolder keyHolder = new GeneratedKeyHolder();
//
//        jdbcNameTemplate.update(sql, paramSource, keyHolder);
//        return keyHolder.getKey().intValue();
//    }
//
//    @Override
//    public User findUserByName(String name){
//        // TODO Auto-generated method stub
//        String sql = " SELECT "
//            + "		*  "
//            + " FROM "
//            + "		cozy_diary.user "
//            + " WHERE "
//            + "		name = ? ";
//        List<User> result = jdbcTemplate.query(sql, new BeanPropertyRowMapper<User>(User.class), new Object[] { name });
//        if(result != null && result.size() > 0) {
//            return result.get(0);
//        }
//        return null;
//    }
//
//    @Override
//    public User findUserByEmail(String email){
//        String sql = " SELECT "
//            + "		*  "
//            + " FROM "
//            + "		cozy_diary.user "
//            + " WHERE "
//            + "		email = ? ";
//        List<User> result = jdbcTemplate.query(sql, new BeanPropertyRowMapper<User>(User.class), new Object[] { email });
//        if(result != null && result.size() > 0) {
//            return result.get(0);
//        }
//        return null;
//    }
//    @Override
//    public List<TrackerResponse> findTrackListByUid(String uid){
//        String sql = " SELECT "
//            + "		*  "
//            + " FROM "
//            + "		cozy_diary.user "
//            + "     inner join cozy_diary.tracker"
//            + "     on cozy_diary.user.uid = cozy_diary.tracker.tracker1"
//            + " WHERE "
//            + "		cozy_diary.user.uid = ? ";
//        List<TrackerResponse> result = jdbcTemplate.query(sql, new BeanPropertyRowMapper<TrackerResponse>(TrackerResponse.class), new Object[] { uid });
//        if(result != null && result.size() > 0) {
//            return result;
//        }
//        return null;
//    }
//
//    @Override
//    public List<FollowerResponse> findFollowListByUid(String uid) {
//        String sql = " SELECT "
//            + "		*  "
//            + " FROM "
//            + "		cozy_diary.user "
//            + "     inner join cozy_diary.follower"
//            + "     on cozy_diary.user.uid = cozy_diary.follower.follower1"
//            + " WHERE "
//            + "		cozy_diary.user.uid = ? ";
//        List<FollowerResponse> result = jdbcTemplate.query(sql, new BeanPropertyRowMapper<FollowerResponse>(FollowerResponse.class), new Object[] { uid });
//        if(result != null && result.size() > 0) {
//            return result;
//        }
//        return null;
//    }
//
//    @Override
//    public Integer update(User user){
//        String sql = " UPDATE "
//            + "		cozy_diary.user "
//            + " SET "
//            + "		name = :name, age = :age,  sex = :sex ,"
//            + "		introduction = :introduction, pic = :pic,  birth = :birth "
//            + " WHERE "
//            + "		ID = :id ";
//        SqlParameterSource paramSource = new BeanPropertySqlParameterSource(user);
//        return jdbcNameTemplate.update(sql, paramSource);
//    }
//
//}
