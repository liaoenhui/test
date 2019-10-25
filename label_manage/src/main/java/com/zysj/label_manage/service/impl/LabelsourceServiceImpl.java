package com.zysj.label_manage.service.impl;

import com.alibaba.fastjson.JSON;
import com.zysj.label_manage.dao.LabelsourceDao;
import com.zysj.label_manage.entity.ObjectClass;
import com.zysj.label_manage.entity.TagObject;
import com.zysj.label_manage.service.LabelsourceService;
import com.zysj.service_common.common.utils.DateUtils;
import com.zysj.service_common.common.utils.R;
import com.zysj.service_common.common.utils.UuidUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.*;

/**
 *  数据源管理
 * @author : Created by Unicorn
 * @date : Created in 2019/9/6
 */
@Service
public class LabelsourceServiceImpl implements LabelsourceService {

    @Autowired
    private LabelsourceDao labelsourceDao;

    /**
     * 获取数据源
     * @return
     */
    @Override
    public List<TagObject> getLabelsourceList(String OBJECT_CLASS_ID) {
        List<TagObject> list = labelsourceDao.getLabelsourceList(OBJECT_CLASS_ID);

        if (list != null && list.size() != 0) {
            return list;
        }else {
            return list;
        }
    }





    /**
     * 新增主体类目
     * @param objectClass
     * @return
     */
    @Override
    @Transactional
    public R addLevelNodeInfo(ObjectClass objectClass) {
        objectClass.setOBJ_CLASS_ID(UuidUtil.get32UUID());
        objectClass.setCREATE_TIME( DateUtils.formatDate(new Date()));

        try {
            labelsourceDao.addLevelNodeInfo(objectClass);
            return R.ok();
        }catch (Exception e){
            // 事务回滚
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            e.printStackTrace();
            return R.error("新增失败");
        }



    }

    /**
     * 新增数据源
     * @param tagObject
     * @return
     */
    @Override
    @Transactional
    public R addSubjectSourceInfo(TagObject tagObject) {
        tagObject.setOBJECT_ID(UuidUtil.get32UUID());
        tagObject.setCREATE_TIME( DateUtils.formatDate(new Date()));

        try {
                labelsourceDao.addSubjectSourceInfo(tagObject);
            return R.ok();
            }catch (Exception e){
                // 事务回滚
                TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
                e.printStackTrace();
                return R.error("新增失败");
            }



    }

    /**
     *
     * @param OBJECT_CLASS_ID  主体的ID
     * @return
     */
    @Override
    public R getlevelNode(String OBJECT_CLASS_ID) {
        List<Map<String,Object>> list = labelsourceDao.getlevelNode(OBJECT_CLASS_ID);

        if (list != null && list.size() != 0) {
            return R.ok().put("list",list);
        }else {
            return R.ok().put("list",list);
        }

    }





    /**
     * 获取单个数据源信息
     * @param
     * @return
     */
    @Override
    public R getLabelSources() {
        List<Map<String, Object> > list = labelsourceDao.getLabelSources();
        if (list != null){
            return R.ok().put("list",list);
        }else {
            return R.error("获取所有主体失败");
        }
    }
    /**
     * 获取单个数据源信息
     * @param OBJECT_ID
     * @return
     */
    @Override
    public R getLabelsourceInfo(String OBJECT_ID) {
       List<Map<String, Object> > datasourceInfo = labelsourceDao.getLabelsourceInfo(OBJECT_ID);
        if (datasourceInfo != null){
            return R.ok().put("datasourceInfo",datasourceInfo);
        }else {
            return R.error("获取单个数据源信息失败");
        }
    }
    /**
     * 删除主体
     * @param objectId
     * @return
     */
    @Override
    public R deleteLabelSourceInfo(String objectId) {
        List<Map<String,Object>> list =  labelsourceDao.getRelationshipLabels(objectId);
        if (list.size()==0 ){
            try {
                labelsourceDao.deleteLabelSourceInfo(objectId);
                return R.ok();
            }catch (Exception e){
                // 事务回滚
                TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
                e.printStackTrace();
                return R.error("解除绑定失败");
            }
        }else {
            return R.error(1,"该表下存在数据无法解绑,请先解绑标签");
        }
    }

    /**
     * 删除主体类目
     * @param objectClassId
     * @return
     */
    @Override
    public R deleteLevelNodeInfo(String objectClassId) {
        List<Map<String,Object>> list =  labelsourceDao.getLevelNodeInfo(objectClassId);
        if (list.size()==0 ){
            try {
                labelsourceDao.deleteLevelNodeInfo(objectClassId);
                return R.ok();
            }catch (Exception e){
                // 事务回滚
                TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
                e.printStackTrace();
                return R.error("解除绑定失败");
            }
        }else {
            return R.error(1,"该类目下存在主体无法删除");
        }
    }
}
