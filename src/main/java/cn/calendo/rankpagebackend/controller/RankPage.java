package cn.calendo.rankpagebackend.controller;

import cn.calendo.rankpagebackend.commons.R;
import cn.calendo.rankpagebackend.entity.PeopleRank;
import cn.calendo.rankpagebackend.entity.TitleName;
import cn.calendo.rankpagebackend.service.PeopleRankImpl;
import cn.calendo.rankpagebackend.service.TitleNameImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Date;
import java.util.List;

/**
 * @author Calendo
 * @version 1.0
 * @description TODO
 * @date 2023/3/10 12:54
 */
@RestController
@Slf4j
@CrossOrigin
@RequestMapping("/rank_page")
public class RankPage {

    @Autowired
    private PeopleRankImpl peopleRankImpl;

    @Autowired
    private TitleNameImpl titleNameImpl;

    //后台：设置评分标题
    @PostMapping("/backend/set_title_name")
    public R setTitle(@RequestBody TitleName titleName) {
        System.out.println("titleName " + titleName);
        String name = titleName.getName();
        String id = titleName.getId();
        log.info("id:" + id);
        log.info("name: " + name);
        boolean resName = titleNameImpl.setTitleName(id, name);
        if (resName) {
            return R.success(200, new Date(), "标题修改成功,为：" + name);
        } else {
            return R.error(500, "标题修改失败！", new Date());
        }
    }

    //前台：获取评分标题
    @GetMapping("/front/get_title_name")
    public R getTitleName(@RequestParam(value = "title_id") String id) {
        TitleName titleName = titleNameImpl.getTitleName(id);
        if (titleName == null) {
            log.info("默认标题");
            return R.error(500, "不存在", new Date());
        }
        log.info("查询成功");
        return R.success(200, "查询成功，结果为：" + titleName.getName(), new Date(), titleName.getName());
    }

    //后台：上传人物excel到数据库里
    @PostMapping("/backend/upload_excel")
    public R uploadExcel(MultipartFile file, @RequestParam(value = "col_num") Integer colNum, @RequestParam(value = "track") String track) throws IOException {
        boolean resExcel = peopleRankImpl.excelUploadAndSave(file, colNum, track);
        if (resExcel) {
            return R.success(200, new Date(), "上传成功！");
        } else {
            return R.error(500, "上传失败", new Date());
        }
    }

    //后台： 查看赛道的人员信息
    @PostMapping("/backend/get_people_by_track")
    public R showPeopleByTrack(@RequestParam(value = "track") String track) {
        List<PeopleRank> list = peopleRankImpl.showPeopleByTrack(track);
        if (list != null) {
            return R.success(200, String.valueOf(list.size()), new Date(), list);
        } else {
            return R.error(500, "相关人员信息展示失败！", new Date());
        }
    }

    //前台： 专家对人员进行评价然后提交更改结果至数据库
    @PostMapping("/front/set_people_score")
    public R updatePeopleScore(@RequestBody List<PeopleRank> peopleRankList) {
        boolean updated = peopleRankImpl.updatePeopleScore(peopleRankList);
        if (updated) {
            return R.success(200, "更新成功！", new Date());
        } else {
            return R.error(500, "更新失败！", new Date());
        }
    }

    //前台： 查看自己所选择的赛道的人员信息
    @PostMapping("/front/get_people_by_track")
    public R showPeopleSet0ByTrack(@RequestParam(value = "track") String track) {
        List<PeopleRank> list = peopleRankImpl.showPeopleSet0ByTrack(track);
        if (list != null) {
            return R.success(200, String.valueOf(list.size()), new Date(), list);
        } else {
            return R.error(500, "相关人员信息展示失败！", new Date());
        }
    }

}
