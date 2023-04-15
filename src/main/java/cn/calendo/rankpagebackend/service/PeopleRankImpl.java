package cn.calendo.rankpagebackend.service;

import cn.calendo.rankpagebackend.Mapper.PeopleRankMapper;
import cn.calendo.rankpagebackend.entity.PeopleRank;
import cn.calendo.rankpagebackend.service.impl.IPeopleRank;
import cn.hutool.poi.excel.ExcelReader;
import cn.hutool.poi.excel.ExcelUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Calendo
 * @version 1.0
 * @description TODO
 * @date 2023/3/10 12:27
 */
@Service
public class PeopleRankImpl extends ServiceImpl<PeopleRankMapper, PeopleRank> implements IPeopleRank {

    //后台：文件上传并存储到数据库中
    @Override
    public boolean excelUploadAndSave(MultipartFile file, Integer colNum, String track) throws IOException {
        //清空数据库中存在相同赛道的人员
        LambdaQueryWrapper<PeopleRank> lqw = new LambdaQueryWrapper<>();
        lqw.eq(PeopleRank::getTrack, track);
        remove(lqw);
        //number为行号
        //name score
        ArrayList<PeopleRank> arrayList = new ArrayList<>();
        for (int i = 0; i < colNum; i++) {
            PeopleRank peopleRank = new PeopleRank();
            //读取文件流
            InputStream inputStream = file.getInputStream();
            //读取sheet0
            ExcelReader reader = ExcelUtil.getReader(inputStream, 0);
            //读取行：仅一个名字
            String cellValue = (String) reader.readCellValue(0, i);
            //if判断为防止出现空值
            if (cellValue != null) {
                //保存到一个PeopleRank实例内
                peopleRank.setName(cellValue);
                peopleRank.setScore(0);
                peopleRank.setTrack(track);
                //添加到一条list内
                arrayList.add(peopleRank);
            }
        }
        System.out.println(arrayList);
        if (arrayList == null) {
            return false;
        } else {
            //批量保存与更新
            boolean savRes = saveOrUpdateBatch(arrayList);
            if (savRes) {
                return true;
            } else {
                return false;
            }
        }
    }

    //后台： 查看赛道的人员信息
    @Override
    public List<PeopleRank> showPeopleByTrack(String track) {
        LambdaQueryWrapper<PeopleRank> lqw = new LambdaQueryWrapper<>();
        lqw.eq(PeopleRank::getTrack, track);
        return list(lqw);
    }

    //前台： 专家对人员进行评价然后提交更改结果至数据库
    @Override
    public boolean updatePeopleScore(List<PeopleRank> peopleRankList) {
        //默认增长权值：1
        int powerAdd = 1;
        for (int i = 0; i < peopleRankList.size(); i++) {
            //得到当前操作行
            PeopleRank newPeopleRank = peopleRankList.get(i);
            //得到当前操作行id
            String newId = newPeopleRank.getId();
            //得到当前操作行分数
            Integer newScore = newPeopleRank.getScore();
            //得到数据库中的映射行
            PeopleRank oldPeopleRank = getById(newId);
            //得到数据库中的映射行id
            String oldId = oldPeopleRank.getId();
            //得到数据库中的映射行分数
            Integer oldScore = oldPeopleRank.getScore();
            //判断当前分数和旧分数是否变化
            if (newScore > 0) {
                oldScore += powerAdd;
            }
            PeopleRank updatedPeopleRank = new PeopleRank(newId, newPeopleRank.getName(), oldScore, newPeopleRank.getTrack());
            updateById(updatedPeopleRank);
        }
        return true;
    }

    //前台： 查看自己所选择的赛道的人员信息
    @Override
    public List<PeopleRank> showPeopleSet0ByTrack(String track) {
        LambdaQueryWrapper<PeopleRank> lqw = new LambdaQueryWrapper<>();
        lqw.eq(PeopleRank::getTrack, track);
        List<PeopleRank> peopleRankList = list(lqw);
        for (int i = 0; i < peopleRankList.size(); i++) {
            PeopleRank oldPeopleRank = peopleRankList.get(i);
            //将有记录的人员信息表面上清空，然后发送给评委
            oldPeopleRank.setScore(0);
            //装回去
            peopleRankList.set(i, oldPeopleRank);
        }
        return peopleRankList;
    }

    //后台： 查看所有人员信息
    @Override
    public List<PeopleRank> showAllPeople() {
        return list();
    }

    //后台： 清理所有数据
    @Override
    public boolean deletePeopleByTrack(String track) {
        LambdaQueryWrapper<PeopleRank> lqw = new LambdaQueryWrapper<>();
        lqw.eq(PeopleRank::getTrack, track);
        return remove(lqw);
    }

}
