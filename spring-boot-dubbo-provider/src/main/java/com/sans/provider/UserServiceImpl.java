package com.sans.provider;

import com.sans.base.dto.User;
import com.sans.base.service.IUserService;
import com.sans.provider.mongo.ReportRepository;
import com.sans.provider.mybatis.mapper.UserMapper;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

/**
 * 生产者Dubbo接口实现
 */
//@Service
@DubboService
public class UserServiceImpl implements IUserService {

    @Autowired
    private JdbcTemplate jdbcTemplate ;

    @Autowired
    private RedisTemplate redisTemplate ;

    private  final UserMapper userMapper ;
     @Autowired
     private ReportRepository reportRegistry ;



    public UserServiceImpl(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @Override
    public List<User> queryList() {

        List<User>  userList = (List<User>) redisTemplate.opsForValue().get("allUsers");

        if(null == userList){
            userList = mySqlTest();
            redisTemplate.opsForValue().set("allUsers",userList,5, TimeUnit.SECONDS);
            System.out.println("查询数据库，将数据存入redis缓存");
        }else{
            System.out.println("查询Redis缓存");
        }

        return userList;
    }

    @Override
    public User queryByID(String id) {
        return userMapper.findByState(id);
    }


    /**
     * 保存数到mongo
     * @param user
     */
    @Override
    public void savaUserInfoToMongo(User user) {
        reportRegistry.save(user);
    }


    public List<User>  mySqlTest() {
        String sql = "select id,name ,number  from user";
        List<User> students = jdbcTemplate.query(sql, new RowMapper<User>() {
            @Override
            public User mapRow(ResultSet resultSet, int i) throws SQLException {
                User user = new User();
                user.setId(resultSet.getInt("id"));
                user.setName(resultSet.getString("name"));
                user.setNumber(resultSet.getInt("number"));
                return user;
            }
        });

        return students ;
    }

}