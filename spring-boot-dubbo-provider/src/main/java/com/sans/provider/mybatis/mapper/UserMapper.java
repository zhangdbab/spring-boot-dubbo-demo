package com.sans.provider.mybatis.mapper;

        import com.sans.base.dto.User;
        import org.apache.ibatis.annotations.Mapper;
        import org.apache.ibatis.annotations.Param;
        import org.apache.ibatis.annotations.Select;

@Mapper
public   interface UserMapper {

    @Select("SELECT * FROM USER WHERE id = #{id}")
    User findByState(@Param("id") String id);

}

