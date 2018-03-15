<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd" >
<mapper namespace="${package_name}.dao.${table_name}DAO">

    <!-- 数据库字段实体映射配置 -->
    <resultMap id="${table_name}ResultMap" type="${package_name}.model.${table_name}">
        <id column="id" property="id" jdbcType="" />
        <!-- 补充其他字段 -->
    </resultMap>

    <!-- SQL片段 -->
    <sql id="baseColumns">
        *
        <!-- 或设置具体字段 -->
    </sql>

    <!-- 插入 -->
    <insert id="insert" parameterType="com.jalen.demo.model.User" useGeneratedKeys="true" keyProperty="id">
        INSERT  INTO ${table_name_small} (  )
                VALUES (  )
    </insert>

    <!-- 根据ID查询 -->
    <select id="findById" parameterType="" resultType="${package_name}.model.${table_name}">
        SELECT  <include refid="baseColumns"/>
          FROM  ${table_name_small} a
         WHERE  a.id =
    </select>

    <!-- 查询全部 -->
    <select id="findAll" resultType="${package_name}.model.${table_name}">
        SELECT  <include refid="baseColumns"/>
          FROM  ${table_name_small} a
    </select>

    <!-- 更新单条记录 -->
    <update id="update">
        UPDATE  user
           SET
         WHERE  id =
    </update>

    <!-- 根据ID删除 -->
    <update id="delete">
        DELETE  FROM ${table_name_small}
         WHERE  id =
    </update>

    <!-- 分页查询 -->
    <select id="findAndPager" resultType="com.jalen.demo.model.User">
        SELECT  <include refid="baseColumns"/>
          FROM  ${table_name_small} a
    </select>

</mapper>