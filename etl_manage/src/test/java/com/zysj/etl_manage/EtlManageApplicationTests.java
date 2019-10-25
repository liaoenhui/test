package com.zysj.etl_manage;


import com.zysj.etl_manage.dao.EtlNeedsDao;
import com.zysj.etl_manage.entity.TagSh;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class EtlManageApplicationTests {

    @Autowired
    private EtlNeedsDao etlNeedsDao;

    @Test
    public void contextLoads() {
        List<TagSh> tagShes = etlNeedsDao.etlTagSh(1);
        for (TagSh tagSh : tagShes) {
            System.out.println(tagSh);
        }
    }

}
