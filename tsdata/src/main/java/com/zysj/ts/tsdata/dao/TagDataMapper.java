package com.zysj.ts.tsdata.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zysj.ts.tsdata.dao.sqlprovider.TagDataProvider;
import com.zysj.ts.tssync.entity.TagSyncEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;

import java.util.List;
import java.util.Map;

@Mapper
public interface TagDataMapper extends BaseMapper<TagSyncEntity> {

//    @Select("select * from tb_object_syncdata_task where object_id = #{objectId}")
//    public List<TagObjectTableTaskEntity> getSyncObjectTables(String objectId);
//    @Select(value = "select item_id,data->'key1' as key1 from hstore_test2 limit 100 ")
//    public List<Map<String,String>> testPgSql();

    @SelectProvider(type = TagDataProvider.class, method = "queryTagData")
    public List<Map<String,Object>> testPgSql(@Param("condition") Map<String,Object> condition);


    @Select({"call initSyncTasksByObjectId(#{objectId})",})
    public void initSyncTasks(String objectId);


    @Select("call initAllSyncTasks()")
    public void initAllSyncTasks();
}
