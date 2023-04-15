package cn.calendo.rankpagebackend.service.impl;

import cn.calendo.rankpagebackend.entity.PeopleRank;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

/**
 * @author Calendo
 * @version 1.0
 * @description TODO
 * @date 2023/3/10 12:18
 */
public interface IPeopleRank extends IService<PeopleRank> {

    //后台：文件上传并显示是否上传成功
    boolean excelUploadAndSave(MultipartFile file, Integer col_num, String track) throws IOException;

    //后台： 查看赛道的人员信息
    List<PeopleRank> showPeopleByTrack(String track);

    //前台： 专家对人员进行评价然后提交更改结果至数据库
    boolean updatePeopleScore(List<PeopleRank> peopleRankList);

    //前台： 查看自己所选择的赛道的人员信息
    List<PeopleRank> showPeopleSet0ByTrack(String track);

    //后台： 查看所有人员信息
    List<PeopleRank> showAllPeople();

    //后台： 根据赛道清理数据
    boolean deletePeopleByTrack(String track);


}
