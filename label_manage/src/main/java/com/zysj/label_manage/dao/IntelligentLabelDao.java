package com.zysj.label_manage.dao;/**
 * Created by 尚先生 on 2019/10/9.
 */

import java.util.List;
import java.util.Map;

/**
 * @author 尚先生
 * @date 2019/10/9 15:22
 */

public interface IntelligentLabelDao {

    /**
     * 获取所有主体
     * @return 主体
     */
    List<Map<String, Object>> getObjectList();

    /**
     * 根据主体获取到主体下的标签
     * @param ObjectId
     * @return
     */
    List<Map<String, Object>> getSubjectLabel(String ObjectId);


}
