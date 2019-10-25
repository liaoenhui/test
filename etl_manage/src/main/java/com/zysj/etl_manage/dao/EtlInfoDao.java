package com.zysj.etl_manage.dao;/**
 * Created by 尚先生 on 2019/9/10.
 */

import com.zysj.etl_manage.entity.EtlParameter;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author 尚先生
 * @date 2019/9/10 17:18
 */
@Mapper
public interface EtlInfoDao {
    /**
     * @return List<Map < String, Object>> 返回我需要的参数
     * @throws
     * @author 尚先生
     * @date 2019/9/9 11:32
     * @Param: null
     * @since
     */
    @Select("SELECT * FROM etl_view WHERE TABLE_ID= #{tabid}")
    List<EtlParameter> getEtlInfo(String tabid);
}
