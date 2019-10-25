package com.zysj.label_manage.service;/**
 * Created by 尚先生 on 2019/10/9.
 */

import com.zysj.service_common.common.utils.R;

/**
 * @author 尚先生
 * @date 2019/10/9 15:23
 */

public interface IntelligentLabelService {
    /**
     * 获取数据源类型信息
     * @return 数据源类型
     */
    R getObjectList();

    /**
     * 根据主体id获取到对应的标签
     * @param ObjectId
     * @return
     */
    R getSubjectLabel(String ObjectId);
}
