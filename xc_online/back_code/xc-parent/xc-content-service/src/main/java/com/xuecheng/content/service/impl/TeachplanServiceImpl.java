package com.xuecheng.content.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xuecheng.api.content.model.dto.CourseBaseDTO;
import com.xuecheng.api.content.model.dto.TeachplanDTO;
import com.xuecheng.common.enums.content.CourseAuditEnum;
import com.xuecheng.common.enums.content.TeachPlanEnum;
import com.xuecheng.common.exception.ExceptionCast;
import com.xuecheng.content.common.constant.ContentErrorCode;
import com.xuecheng.content.convert.TeachPlanConvert;
import com.xuecheng.content.entity.CourseBase;
import com.xuecheng.content.entity.Teachplan;
import com.xuecheng.content.entity.TeachplanMedia;
import com.xuecheng.content.entity.ex.TeachPlanTreeNode;
import com.xuecheng.content.mapper.TeachplanMapper;
import com.xuecheng.content.service.TeachplanService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 课程计划 服务实现类
 * </p>
 *
 * @author itcast
 */
@Slf4j
@Service
public class TeachplanServiceImpl extends ServiceImpl<TeachplanMapper, Teachplan> implements TeachplanService {

    @Autowired
    CourseBaseServiceImpl courseBaseService;

    @Autowired
    TeachplanMediaServiceImpl teachplanMediaService;


    /**
     * 1.校验参数
     *    courseId非空
     * 2.校验业务参数
     *    该courseId对应的courseBase必须存在
     *    companyId必须一致
     * 3.查询TreeNodes
     *    将其封装成dto返回
     * @param courseId
     * @param companyId
     * @return
     */
    @Override
    public TeachplanDTO queryTreeNodes(Long courseId, Long companyId) {
        //1.校验courseId
        if(ObjectUtils.isEmpty(courseId)){
            ExceptionCast.cast(ContentErrorCode.E_120018);
        }

       // 2.校验业务参数
         //     该courseId对应的courseBase必须存在
         //     companyId必须一致
        CourseBaseDTO base = courseBaseService.getCourseBaseById(courseId);

        //3. 查询该courseId对应的teachplan，并按照grade和oderby排序
        List<Teachplan> list = this.lambdaQuery().eq(Teachplan::getCourseId, courseId).
                orderByAsc(Teachplan::getGrade, Teachplan::getOrderby).list();
         // 3.1 取出根节点转化成treenode
        Teachplan teachplan = list.get(0);
        TeachPlanTreeNode rootNode = new TeachPlanTreeNode(teachplan);

        List<TeachPlanTreeNode> childs = generatorTreeNodes(list, rootNode.getId());
        rootNode.setTeachPlanTreeNodes(childs);

        //4.将treeNode转化成dto
        TeachplanDTO dto = TeachPlanConvert.INSTANCE.node2dto(rootNode);
        return dto;
    }

    /**
     * 1.校验关键数据
     *      vo中已校验完成
     * 2.校验业务数据
     *      判断其对应的courseBase是否存在
     *      判断companyId是否一致
     *      判断审核状态是否符合要求
     * 3.获得父节点，通过父节点判断子节点的grade和orderby
     * 4.将dto->po插入到数据库中
     * 5.查询数据库并返回dto
     * @param dto
     * @param companyId
     * @return
     */
    @Transactional
    @Override
    public TeachplanDTO createTeachPlan(TeachplanDTO dto, Long companyId) {
        //2.校验业务数据
        CourseBase courseBase = verifyCourseBase(dto.getCourseId(), companyId);

        //3.获得父节点,并得到待创建章节的grade和orderby
        Teachplan parent = getParentNode(dto,courseBase);
        //若父节点不存在则抛出异常
        if(ObjectUtils.isEmpty(parent)){
            ExceptionCast.cast(ContentErrorCode.E_120408);
        }
        Integer orderby = this.getBaseMapper().selectMaxOrderby(parent.getId(), parent.getGrade() + 1);
        if(ObjectUtils.isEmpty(orderby)){
            orderby = 1;
        }else{
            orderby += 1;
        }
        //4.将dto->po,插入到数据库中
        Teachplan po = TeachPlanConvert.INSTANCE.dto2po(dto);
        po.setGrade(parent.getGrade()+1);
        po.setOrderby(orderby);
        po.setParentid(parent.getId());

        boolean save = this.save(po);
        if(!save){
            ExceptionCast.cast(ContentErrorCode.E_120407);
        }

        //5.主键回传，根据主键查找记录并转成dto返回
        Teachplan teachplan = this.getById(po.getId());

        return TeachPlanConvert.INSTANCE.po2dto(teachplan);
    }

    /**
     *  1.判断parentId是否存在
     *      存在：说明添加的是三级章节，通过parentId获得父节点即可
     *      不存在：说明是二级章节，根据courseId查找父节点
     *             若父节点不存在，则需要创建父节点并返回
     *
     * @param dto
     * @param courseBase
     * @return
     */
    private Teachplan getParentNode(TeachplanDTO dto, CourseBase courseBase) {
        Long parentid = dto.getParentid();
        //1.判断parentId是否存在
        if(ObjectUtils.isEmpty(parentid)) {
            //不存在：说明是二级章节，根据courseId查找父节点
            Teachplan parent = this.lambdaQuery().eq(Teachplan::getCourseId, dto.getCourseId()).eq(Teachplan::getGrade, 1).one();
            //若父节点不存在，则需要创建父节点并
            if (ObjectUtils.isEmpty(parent)) {
                parent = new Teachplan();
                parent.setPname(courseBase.getName());
                parent.setParentid(new Long(TeachPlanEnum.FIRST_PARENTID_FLAG));
                parent.setGrade(TeachPlanEnum.FIRST_LEVEL);
                parent.setDescription(courseBase.getDescription());
                parent.setOrderby(TeachPlanEnum.FIRST_LEVEL);
                parent.setCourseId(courseBase.getId());
                boolean save = this.save(parent);
                if (!save){ //若创建失败则返回空
                    return null;
                }
            }
            return parent;
        }else{ // parentid存在，说明添加的是三级章节，通过parentId获得父节点即可
            Teachplan teachplan = this.getById(parentid);
            return teachplan;
        }
    }

    /**
     * 1.校验关键数据
     *    vo中已经校验完成
     * 2.校验业务数据
     *     courseBase
     *          判断其对应的courseBase是否存在
     *          判断companyId是否一致
     *          判断审核状态是否符合要求
     *     teachPlan
     *          判断该teachPlan是否存在
     *          判断teachPlan的grade是否为2或3
     * 3.限定修改的范围，并根据teachPlanId修改记录
     *      只能修改 收费模式、开始结束时间、章节名字
     * 4.查询修改过的记录，转成dto返回
     * @param dto
     * @param companyId
     * @return
     */
    @Transactional
    @Override
    public TeachplanDTO modifyTeachPlan(TeachplanDTO dto, Long companyId) {
        //1.校验teachPlan
        verifyCourseBase(dto.getCourseId(), companyId);

        //2.校验teachPlan
        Teachplan record = verifyTeachPlan(dto.getId());

        //3.限定修改的范围
        Teachplan teachplan = new Teachplan();
        //3.1 如果是大章节，则只能修改名称，小章节才能修改收费模式、开始结束时间、章节名字
        teachplan.setId(dto.getId());
        teachplan.setPname(dto.getPname());
        if(record.getGrade() == 1){
            teachplan.setStartTime(dto.getStartTime());
            teachplan.setEndTime(dto.getEndTime());
            teachplan.setIsPreview(dto.getIsPreview());
        }

        //修改记录
        boolean updateById = this.updateById(teachplan);
        if(!updateById){
            ExceptionCast.cast(ContentErrorCode.E_120407);
        }
        //4.查询修改过的记录，转成dto返回
        Teachplan byId = this.getById(teachplan.getId());

        return TeachPlanConvert.INSTANCE.po2dto(byId);
    }

    /**
     * 校验teachPlan
     *      teachPlan是否存在
     *      该teachPlan是否为2或3级章节
     * @param
     * @return
     */
    private Teachplan verifyTeachPlan(Long teachPlanId) {
        Teachplan record = this.getById(teachPlanId);
        if (ObjectUtils.isEmpty(record)) {
            ExceptionCast.cast(ContentErrorCode.E_120402);
        }

        //判断teachPlan的grade是否为2或3
        if(record.getGrade() != 2 && record.getGrade() != 3){
            ExceptionCast.cast(ContentErrorCode.E_120407);
        }
        return record;
    }

    /**
     * 校验courseBase
     *     判断其对应的courseBase是否存在
     *     判断companyId是否一致
     *     判断审核状态是否符合要求
     * @param
     * @param companyId
     */
    private CourseBase verifyCourseBase(Long courseId, Long companyId) {
        CourseBase courseBase = courseBaseService.getById(courseId);
        if(!courseBase.getCompanyId().equals(companyId)){
            ExceptionCast.cast(ContentErrorCode.E_120017);
        }
        if(!courseBase.getAuditStatus().equals(CourseAuditEnum.AUDIT_UNPAST_STATUS.getCode()) && !courseBase.getAuditStatus().equals(CourseAuditEnum.AUDIT_DISPAST_STATUS.getCode())){
            ExceptionCast.cast(ContentErrorCode.E_120015);
        }
        return courseBase;
    }

    /**
     * 1.校验业务数据
     *      courseBase
     *          判断companyId是否一致
     *          判断审核状态
     *      teachPlan
     *          判断teachPlan是否存在
     *          判断该计划是否有子章节
     *          判断是否是2、3级章节
     *      teachPlanMedia
     *          判断是否有关联的媒体资源
     * @param teachPlanId
     * @param companyId
     * @return
     */
    @Transactional
    @Override
    public boolean removeTeachPlan(Long teachPlanId, Long companyId) {
        //1.获得对应教学计划并校验
        Teachplan teachplan = verifyTeachPlan(teachPlanId);

        //2.校验courseBase
        CourseBase courseBase = verifyCourseBase(teachplan.getCourseId(), companyId);

        //3.校验该teachPlan是否绑定媒体资源
        Integer mediaCount = teachplanMediaService.lambdaQuery().eq(TeachplanMedia::getTeachplanId, teachPlanId).count();
        if(mediaCount > 0){
            ExceptionCast.cast(ContentErrorCode.E_120414);
        }

        //4.查看是否有子章节
        int planCount = this.lambdaQuery().eq(Teachplan::getParentid, teachPlanId).count();
        if(planCount > 0){
            ExceptionCast.cast(ContentErrorCode.E_120409);
        }

        return this.removeById(teachPlanId);

    }

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
}
