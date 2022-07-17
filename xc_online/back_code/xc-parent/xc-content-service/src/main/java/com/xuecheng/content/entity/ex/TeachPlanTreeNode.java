package com.xuecheng.content.entity.ex;

import com.xuecheng.content.entity.Teachplan;
import com.xuecheng.content.entity.TeachplanMedia;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TeachPlanTreeNode extends Teachplan {

    private List<TeachPlanTreeNode> teachPlanTreeNodes;

    private TeachplanMedia teachplanMedia;

    public TeachPlanTreeNode(Teachplan plan){
        super(plan.getId(), plan.getPname(),plan.getParentid(), plan.getGrade(), plan.getMediaType(),plan.getStartTime(),plan.getEndTime(),plan.getDescription(),plan.getTimelength(),
                plan.getOrderby(),plan.getCourseId(),plan.getCoursePubId(),plan.getStatus(),plan.getIsPreview(),plan.getCreateDate(),plan.getChangeDate());
        teachPlanTreeNodes = new ArrayList<>();
    }

    @Override
    public String toString() {
        return "TeachPlanTreeNode{" +
                super.toString() +
                "teachPlanTreeNodes=" + teachPlanTreeNodes +
                '}';
    }
}
