package com.zysj.label_manage.service.impl;/**
 * Created by 尚先生 on 2019/9/24.
 */

import com.alibaba.fastjson.JSON;
import com.zysj.label_manage.dao.RelationshipDao;
import com.zysj.label_manage.entity.ObjectInner;
import com.zysj.label_manage.service.RelationshipService;
import com.zysj.service_common.common.utils.DateUtils;
import com.zysj.service_common.common.utils.R;
import com.zysj.service_common.common.utils.UuidUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author 尚先生
 * @date 2019/9/24 9:14
 */
@Service
public class ReationshipServiceImpl implements RelationshipService {
    @Autowired
    private RelationshipDao relationshipDao;
    /**
     * 新增关系
     * @param objectInner
     * @return
     */
    @Override
    @Transactional
    public R addRelationShipSourceInfo(ObjectInner objectInner) {
        objectInner.setINNER_ID(UuidUtil.get32UUID());
        objectInner.setCREATE_TIME(DateUtils.formatDate(new Date()));

        try {
            relationshipDao.addRelationShipSourceInfo(objectInner);
            return R.ok();
        }catch (Exception e){
            // 事务回滚
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            e.printStackTrace();
            return R.error("新增失败");
        }


    }

    /**
     *内关系显示
     * @param objectId  主键id
     * @return
     */
   @Override
    public R getInternalRelationsInfo(String objectId) {
        List<Map<String,Object>> list = relationshipDao.getInternalRelationsInfo(objectId);
       List<Map<String,Object>> lists= relationshipDao.getInsideMainIdentification(objectId);

        if (list != null && list.size() != 0) {
            return R.ok().put("list",JSON.toJSON(list)).put("MainLabel",lists);
        }else {
            return R.ok("请再次确认是否存在内关系").put("list",list);
        }
    }

    /**
     *
     * @param fieldName  关系code
     * @return
     */
    @Override
    public R getWhetherField(String fieldName) {
        List<Map<String,Object>> list = relationshipDao.getWhetherField(fieldName);

        if (list != null && list.size() != 0) {
            return R.ok().put("list",list);
        }else {
            return R.ok("该code已存在请更改您所填写的code").put("list",list);
        }


    }
}
