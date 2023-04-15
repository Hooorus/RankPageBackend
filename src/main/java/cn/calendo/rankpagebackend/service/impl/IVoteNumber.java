package cn.calendo.rankpagebackend.service.impl;

import cn.calendo.rankpagebackend.entity.TitleName;
import cn.calendo.rankpagebackend.entity.VoteNumber;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * @author Calendo
 * @version 1.0
 * @description TODO
 * @date 2023/3/10 12:18
 */
public interface IVoteNumber extends IService<VoteNumber> {

    /**
    * @param id 名称
    * @param name 标题名字
    * @return boolean
    * @author Calendo
    * @date 2023/3/10 13:48
    */
    boolean setVoteNumber(String id, Integer limitMax);

    /**
    * @param id 名称
    * @param name 标题名字
    * @return boolean
    * @author Calendo
    * @date 2023/3/10 13:48
    */
    VoteNumber getVoteNumber(String id);

}
