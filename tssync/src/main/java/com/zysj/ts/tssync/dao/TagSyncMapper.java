package com.zysj.ts.tssync.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zysj.ts.tssync.entity.TagSyncEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface TagSyncMapper extends BaseMapper<TagSyncEntity> {

//    @Select("select * from tb_object_syncdata_task where object_id = #{objectId}")
//    public List<TagObjectTableTaskEntity> getSyncObjectTables(String objectId);

    @Select("call initSyncTasksByObjectId(#{objectId})")
    public void initSyncTasks(String objectId);


    @Select("call initAllSyncTasks()")
    public void initAllSyncTasks();
}
