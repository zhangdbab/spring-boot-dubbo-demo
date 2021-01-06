package com.sans.provider;

import com.sans.base.dto.ProviderTestDTO;
import com.sans.base.service.IUserService;
import org.apache.dubbo.config.annotation.DubboService;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
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

    @Override
    public List<ProviderTestDTO> queryList() {
        // 初始化数据
//        ProviderTestDTO testDTO1 = new ProviderTestDTO();
//        testDTO1.setId(1);
//        testDTO1.setName("学生");
//        testDTO1.setNumber(100);
//        ProviderTestDTO testDTO2 = new ProviderTestDTO();
//        testDTO2.setId(2);
//        testDTO2.setName("教师");
//        testDTO2.setNumber(101);
//        // 组装数据
//        List<ProviderTestDTO> list = new ArrayList<>();
//        list.add(testDTO1);
//        list.add(testDTO2);

        List<ProviderTestDTO> providerTestDTOList = (List<ProviderTestDTO>) redisTemplate.opsForValue().get("allUsers");

        if(null == providerTestDTOList){
            providerTestDTOList = mySqlTest();
            redisTemplate.opsForValue().set("allUsers",providerTestDTOList,5, TimeUnit.SECONDS);
            System.out.println("查询数据库，将数据存入redis缓存");
        }else{
            System.out.println("查询Redis缓存");
        }

        return providerTestDTOList;
    }

    public List<ProviderTestDTO>  mySqlTest() {
        String sql = "select id,name ,number  from user";
        List<ProviderTestDTO> students = jdbcTemplate.query(sql, new RowMapper<ProviderTestDTO>() {
            @Override
            public ProviderTestDTO mapRow(ResultSet resultSet, int i) throws SQLException {
                ProviderTestDTO providerTestDTO = new ProviderTestDTO();
                providerTestDTO.setId(resultSet.getInt("id"));
                providerTestDTO.setName(resultSet.getString("name"));
                providerTestDTO.setNumber(resultSet.getInt("number"));
                return providerTestDTO;
            }
        });

        return students ;
    }

}