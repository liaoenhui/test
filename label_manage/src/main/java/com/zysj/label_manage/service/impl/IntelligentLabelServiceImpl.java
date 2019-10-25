package com.zysj.label_manage.service.impl;/**
 * Created by 尚先生 on 2019/10/9.
 */

import com.alibaba.fastjson.JSON;
import com.zysj.label_manage.dao.IntelligentLabelDao;
import com.zysj.label_manage.service.IntelligentLabelService;
import com.zysj.service_common.common.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @author 尚先生
 * @date 2019/10/9 15:23
 */
@Service
public class IntelligentLabelServiceImpl implements IntelligentLabelService {
    @Autowired
    IntelligentLabelDao intelligentLabelDao;
    /**
     * 获取所有主体
     * @return map
     */
    @Override
    public R getObjectList() {
        List<Map<String,Object>> list = intelligentLabelDao.getObjectList();

        if (list != null && list.size() != 0) {
            return R.ok().put("list", JSON.toJSON(list));
        }else {
            return R.ok("查询结果为空").put("list",list);
        }
    }
    /**
     * 根据主体id获取到对应的标签
     * @param ObjectId
     * @return
     */
    @Override
    public R getSubjectLabel(String ObjectId) {
        List<Map<String,Object>> list = intelligentLabelDao.getSubjectLabel(ObjectId);
        if (list != null && list.size() != 0) {
            return R.ok().put("list",JSON.toJSON(list));
        }else {
            return R.ok("查询结果为空").put("list",list);
        }
    }
}
