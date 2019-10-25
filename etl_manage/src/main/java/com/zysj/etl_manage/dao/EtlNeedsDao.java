package com.zysj.etl_manage.dao;/**
 * Created by 尚先生 on 2019/9/6.
 */


import com.zysj.etl_manage.entity.EtlParameter;
import com.zysj.etl_manage.entity.TagSh;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;


import java.util.List;


/**
 * @author 尚先生
 * @date 2019/9/6 14:44
 */
@Mapper
public interface EtlNeedsDao {

    /**
     * @return
     * @throws
     * @author 尚先生
     * @date 2019/9/10 10:03
     * @Param: null
     * @since
     */
    @Select("SELECT * FROM tb_object_tag_sh WHERE SH_STATUS= #{SH_STATUS}")
    List<TagSh> etlTagSh(int SH_STATUS);

    /**
     * 修改状态表中的状态
     *
     * @return int
     */
    @Select("update tb_object_tag_sh set SH_STATUS=0 where SH_ID = #{SH_ID}")
    void updateShStaTus(String SH_ID);

    /**
     * 修改状态表(新表）中的状态
     *
     * @return int
     */
    @Select("update tb_object_syncopt set sync_script=1 where opt_id = #{optId}")
    void updateScriptStaTus(Integer optId);

}
