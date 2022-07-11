import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.xuecheng.content.ContentApplication;
import com.xuecheng.content.entity.Teachplan;
import com.xuecheng.content.entity.ex.TeachPlanTreeNode;
import com.xuecheng.content.mapper.TeachplanMapper;
import com.xuecheng.content.service.TeachplanService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest(classes = ContentApplication.class)
public class TestTechPlanTreeNodes {
    @Autowired
    TeachplanService teachplanService;

    @Autowired
    TeachplanMapper teachplanMapper;

    @Test
    public void test(){
        List<Teachplan> list = teachplanService.lambdaQuery().eq(Teachplan::getCourseId, 25).orderByAsc(Teachplan::getGrade, Teachplan::getOrderby).list();

        Teachplan teachplan = list.get(0);
        List<TeachPlanTreeNode> teachPlanTreeNodes = generatorTreeNodes(list, teachplan.getId());
        for (TeachPlanTreeNode node : teachPlanTreeNodes) {
            System.out.println(node);
        }
    }

    /**
     * 生成treeNode形式
     * @param list 待生成tree的node
     * @param parentId 父级id
     * @return
     */
    private List<TeachPlanTreeNode> generatorTreeNodes(List<Teachplan> list, Long parentId){
        ArrayList<TeachPlanTreeNode> treeNodes = new ArrayList<>();
        for (Teachplan item : list) {
            if(item.getParentid() == parentId){
                TeachPlanTreeNode node = new TeachPlanTreeNode(item);
                treeNodes.add(node);
                if(node.getGrade() != 3){
                    node.setTeachPlanTreeNodes(generatorTreeNodes(list,node.getId()));
                }
            }
        }
        return treeNodes;
    }

    @Test
    public void test2(){
        int orderby = teachplanMapper.selectMaxOrderby(19, 2);
        System.out.println(orderby);
    }
}
