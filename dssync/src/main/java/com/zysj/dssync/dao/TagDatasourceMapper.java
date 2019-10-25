package com.zysj.dssync.dao;

import com.zysj.dssync.entity.Datasource;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;

import java.util.List;

@Mapper
public interface TagDatasourceMapper {

    @Results(id="baseResultMap", value={
            @Result( column="DS_ID"  ,property="dsId", id=true),
            @Result( column="DS_ENAME"  ,property="dsEname"),
            @Result( column="DS_CNAME"  ,property="dsCname"),
            @Result( column="DS_TYPE"  ,property="dsType", jdbcType=JdbcType.INTEGER),
            @Result( column="DS_IP"  ,property="dsIp"),
            @Result( column="DS_PORT"  ,property="dsPort"),
            @Result( column="DS_USERNAME"  ,property="dsUsername"),
            @Result( column="DS_PASSWORD"  ,property="dsPassword"),
            @Result( column="DS_ENDPOINT"  ,property="dsEndpoint"),
            @Result( column="DS_ACCESSKEY"  ,property="dsAccesskey"),
            @Result( column="DS_ACCESS_SECRET"  ,property="dsAccessSecret"),
            @Result( column="DS_VERSION_ID"  ,property="dsVersionId"),
            @Result( column="DS_OPERATOR"  ,property="dsOperator"),
            @Result( column="DS_UPDATETIME"  ,property="dsUpdatetime"),
            @Result( column="DS_SYNC_STATE"  ,property="dsSyncState",jdbcType=JdbcType.INTEGER)
    })
    @Select( "select * from tb_datasource")
    List<Datasource> listAll();

//    @Select("<script>select * from tb_datasource where DS_SYNC_STATE in (0,3)</script>")
@Results({
        @Result( column="DS_ID"  ,property="dsId", id=true),
        @Result( column="DS_ENAME"  ,property="dsEname"),
        @Result( column="DS_CNAME"  ,property="dsCname"),
        @Result( column="DS_TYPE"  ,property="dsType", jdbcType=JdbcType.INTEGER),
        @Result( column="DS_IP"  ,property="dsIp"),
        @Result( column="DS_PORT"  ,property="dsPort"),
        @Result( column="DS_USERNAME"  ,property="dsUsername"),
        @Result( column="DS_PASSWORD"  ,property="dsPassword"),
        @Result( column="DS_ENDPOINT"  ,property="dsEndpoint"),
        @Result( column="DS_ACCESSKEY"  ,property="dsAccesskey"),
        @Result( column="DS_ACCESS_SECRET"  ,property="dsAccessSecret"),
        @Result( column="DS_VERSION_ID"  ,property="dsVersionId"),
        @Result( column="DS_OPERATOR"  ,property="dsOperator"),
        @Result( column="DS_UPDATETIME"  ,property="dsUpdatetime"),
        @Result( column="DS_SYNC_STATE"  ,property="dsSyncState",jdbcType=JdbcType.INTEGER)
})
//    @ResultMap("baseResultMap")
    @Select( "select * from tb_datasource where DS_SYNC_STATE in (0,3)")
    List<Datasource> listNeedSync();

    @Select(" <script>select * from tb_datasource where DS_SYNC_STATE in\n" +
            "        <foreach collection=\"states\" item=\"state\" separator=\",\" open=\"(\" close=\")\">\n" +
            "            #{state}\n" +
            "        </foreach> </script>")
//    @ResultMap("baseResultMap")
    List<Datasource> listStates(@Param("states") int[] states);


    @Update("update tb_datasource set DS_SYNC_STATE = #{ds.dsSyncState} where ds_id = #{ds.dsId}")
    void updateState(@Param("ds") Datasource ds);
}
