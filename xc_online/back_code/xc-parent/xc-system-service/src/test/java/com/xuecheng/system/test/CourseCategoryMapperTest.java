package com.xuecheng.system.test;

import com.xuecheng.system.entity.ext.CourseCategoryNode;
import com.xuecheng.system.mapper.CourseCategoryMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

/**
 * <p></p>
 *
 * @Description:
 */
@SpringBootTest
public class CourseCategoryMapperTest {

    @Autowired
    private CourseCategoryMapper courseCategoryMapper;


    @Test
    public void testQueryTreeNodes() {


        List<CourseCategoryNode> courseCategoryNodes = courseCategoryMapper.selectTreeNodes();


        System.out.println(courseCategoryNodes);
    }

}
