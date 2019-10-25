package com.zysj.label_manage.service.impl;/**
 * Created by 尚先生 on 2019/10/15.
 */

import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import com.zysj.label_manage.dao.CircleGroupDao;
import com.zysj.label_manage.entity.TbGroup;
import com.zysj.label_manage.service.CircleGroupService;
import com.zysj.label_manage.service.TagDataConsulService;
import com.zysj.service_common.common.utils.DateUtils;
import com.zysj.service_common.common.utils.R;
import com.zysj.service_common.common.utils.UuidUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 圈群部分
 * @author 尚先生
 * @date 2019/10/15 16:11
 */
@Service
public class CircleGroupServiceImpl implements CircleGroupService {

    @Autowired
    CircleGroupDao circleGroupDao;

    @Autowired
    TagDataConsulService tagDataConsulService;
    /**
     * 根据主体id获取主体的详情
     * @param  obJctId 主键的id
     * @return
     */
    @Override
    public R getSubjectTagCategoryList(String obJctId) {
        List<Map<String,Object>> list = circleGroupDao.getSubjectTagCategoryList(obJctId);
        for(int i = 0 ; i < list.size() ; i++) {
            List<Map<String,Object>> mtList =new ArrayList<Map<String,Object>>();
            mtList.add(circleGroupDao.getLabelOfRelationship((String) list.get(i).get("OBJECT_ID"),obJctId,0));
            mtList.add(circleGroupDao.getLabelOfRelationship((String) list.get(i).get("OBJECT_ID"),obJctId,1));
            list.get(i).put("MT",mtList);
        }
        if (list != null && list.size() != 0) {
            return R.ok().put("list",list);
        }else {
            return R.ok("该主体没有类目存在").put("list",list);
        }

    }

    /**
     * 根根据标签获取到关联主体
     * @param  relationshipId 关系的id
     * @param  subjectId 主体id
     * @return
     */
    @Override
    public R getTagSubjectCategoryList(String subjectId,String relationshipId) {
        List<Map<String,Object>> list = circleGroupDao.getTagSubjectCategoryList(relationshipId,subjectId);
        List<Map<String,Object>> mtList =new ArrayList<Map<String,Object>>();
        mtList.add(circleGroupDao.getLabelOfRelationship(relationshipId,subjectId,0));
        mtList.add(circleGroupDao.getLabelOfRelationship(relationshipId,subjectId,1));
        list.get(0).put("MT",mtList);
        if (list != null && list.size() != 0) {
            return R.ok().put("list",list);
        }else {
            return R.ok("该标签未找到关联主体").put("list",list);
        }

    }
    /**
     * 新增群体
     * @param tbGroup
     * @return
     */
    @Override
    @Transactional
    public R addGroup(TbGroup tbGroup) {
        tbGroup.setGroup_ID(UuidUtil.get32UUID());
        tbGroup.setCreate_TIME(DateUtils.formatDate(new Date()));
        try {
            /**
             * 效验json 将json进行转换进行容错处理
             */
            JSONObject sCoJo = JSONObject.parseObject(tbGroup.getConditions() );
            String returnJson =JSONObject.toJSONString(sCoJo);
            tbGroup.setConditions(returnJson);
            circleGroupDao.addGroup(tbGroup);
           tagDataConsulService.saveAnalyzedGroup(returnJson,tbGroup.getGroup_ID());
            return R.ok();
        }catch (JSONException e){
            return R.error(400,"JSON转换异常请核对");
        }catch (Exception e){
            // 事务回滚
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            e.printStackTrace();
            return R.error("新增失败");
        }
    }
    /**
     * 修改群体
     * @param tbGroup
     * @return
     */
    @Override
    public R updateEditTbGroupInfo(TbGroup tbGroup) {

        tbGroup.setCreate_TIME(DateUtils.formatDate(new Date()));

        try {
            /**
             * 效验json 将json进行转换进行容错处理
             */
            JSONObject sCoJo = JSONObject.parseObject(tbGroup.getConditions() );
            String returnJson =JSONObject.toJSONString(sCoJo);
            tbGroup.setConditions(returnJson);
            circleGroupDao.updateEditTbGroupInfo(tbGroup);
            tagDataConsulService.saveAnalyzedGroup(returnJson,tbGroup.getGroup_ID());
            return R.ok();
        }catch (JSONException e){
            return R.error(400,"JSON转换异常请核对");
        }catch (Exception e){
            // 事务回滚
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            e.printStackTrace();
            return R.error("修改失败");
        }
    }

    /**
     * 模糊查询群体
     * @param groupName
     * @return
     */
    @Override
    public R getTbGroupLikeName(String groupName) {
        List<Map<String,Object>> list = circleGroupDao.getTbGroupLikeName(groupName);

        if (list != null && list.size() != 0) {
            return R.ok().put("list",list);
        }else {
            return R.ok("请查看是否输入正确").put("list",list);
        }

    }

    /**
     * 删除标签表绑定数据
     * @param groupId
     * @return
     */
    @Override
    @Transactional
    public R deleteTbGroupInfo(String groupId) {
        try {
            circleGroupDao.deleteTbGroupInfo(groupId);
            return R.ok();
        }catch (Exception e){
            // 事务回滚
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            e.printStackTrace();
            return R.error("删除失败");
        }

    }
}
