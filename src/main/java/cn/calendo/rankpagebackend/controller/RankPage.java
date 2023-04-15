package cn.calendo.rankpagebackend.controller;

import cn.calendo.rankpagebackend.commons.R;
import cn.calendo.rankpagebackend.entity.PeopleRank;
import cn.calendo.rankpagebackend.entity.TitleName;
import cn.calendo.rankpagebackend.entity.VoteNumber;
import cn.calendo.rankpagebackend.service.PeopleRankImpl;
import cn.calendo.rankpagebackend.service.TitleNameImpl;
import cn.calendo.rankpagebackend.service.VoteNumberImpl;
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

    @Autowired
    private VoteNumberImpl voteNumberImpl;

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
            return R.error(500, "不存在", new Date());
        }
        log.info("查询成功");
        return R.success(200, "查询成功，结果为：" + titleName.getName(), new Date(), titleName.getName());
    }

    //后台：设置投票最值
    @PostMapping("/backend/set_vote_number")
    public R setVoteNumbers(@RequestBody VoteNumber voteNumber) {
        System.out.println("voteNumber: " + voteNumber);
        Integer number = voteNumber.getLimitMax();
        String id = voteNumber.getId();
        log.info("id:" + id);
        log.info("number: " + number);
        boolean resName = voteNumberImpl.setVoteNumber(id, number);
        if (resName) {
            return R.success(200, new Date(), "投票最值修改成功,为：" + number);
        } else {
            return R.error(500, "投票最值修改失败！", new Date());
        }
    }

    //前台：获取投票最值
    @GetMapping("/front/get_vote_number")
    public R getVoteNumbers(@RequestParam(value = "vote_id") String id) {
        VoteNumber voteNumber = voteNumberImpl.getVoteNumber(id);
        if (voteNumber == null) {
            return R.error(500, "不存在", new Date());
        }
        log.info("查询成功");
        return R.success(200, "查询成功，结果为：" + voteNumber.getLimitMax(), new Date(), voteNumber.getLimitMax());
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

    //后台： 查看所有人员信息
    @PostMapping("/backend/get_people_all")
    public R showAllPeople() {
        List<PeopleRank> list = peopleRankImpl.showAllPeople();
        if (list != null) {
            return R.success(200, String.valueOf(list.size()), new Date(), list);
        } else {
            return R.error(500, "相关人员信息展示失败！", new Date());
        }
    }

    //后台： 根据赛道清理数据
    @DeleteMapping("/backend/delete_people_by_track")
    public R deletePeopleByTrack(@RequestParam(value = "track") String track) {
        boolean res = peopleRankImpl.deletePeopleByTrack(track);
        if (res) {
            return R.success(200, track + "删除成功", new Date());
        } else {
            return R.success(500, track + "删除失败", new Date());
        }
    }

}
