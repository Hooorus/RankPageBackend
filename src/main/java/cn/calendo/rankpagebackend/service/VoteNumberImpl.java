package cn.calendo.rankpagebackend.service;

import cn.calendo.rankpagebackend.Mapper.VoteNumberMapper;
import cn.calendo.rankpagebackend.entity.VoteNumber;
import cn.calendo.rankpagebackend.service.impl.IVoteNumber;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * @author Calendo
 * @version 1.0
 * @description TODO
 * @date 2023/3/10 12:27
 */
@Service
public class VoteNumberImpl extends ServiceImpl<VoteNumberMapper, VoteNumber> implements IVoteNumber {

    /**
     * @param name 标题名字
     * @return cn.calendo.rankpagebackend.commons.R
     * @author Calendo
     * @describe 更改标题名字
     * @date 2023/3/10 12:42
     */

    //后台：设置投票数量
    @Override
    public boolean setVoteNumber(String id, Integer limitMax) {
        VoteNumber byId = getById(id);
        byId.setLimitMax(limitMax);
        byId.setId("vote_number");
        boolean updateById = updateById(byId);
        if (updateById) {
            return true;
        } else {
            return false;
        }
    }

    //前台：获取投票数量
    @Override
    public VoteNumber getVoteNumber(String id) {
        LambdaQueryWrapper<VoteNumber> lqw = new LambdaQueryWrapper<>();
        lqw.eq(VoteNumber::getId, id);
        return getOne(lqw);
    }


}
