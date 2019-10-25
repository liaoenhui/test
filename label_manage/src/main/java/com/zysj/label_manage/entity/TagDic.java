package com.zysj.label_manage.entity;/**
 * Created by 尚先生 on 2019/10/9.
 */

import lombok.Data;

/**
 * @author 尚先生
 * @date 2019/10/9 9:17
 */
@Data
public class TagDic {
    /**
     * 字典名称
     */
    String dic_name;
    /**
     * 字典id 自增长
     */
    int dic_id;
    /**
     * 字典值
     */
    String value;
    /**
     * 字典key
     */
    String key;
    /**
     * 描述
     */
    String desc;
    /**
     * 字典表code
     */
    Object dic_code;

}
