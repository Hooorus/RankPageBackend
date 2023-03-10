package cn.calendo.rankpagebackend.service;

import cn.calendo.rankpagebackend.Mapper.TitleNameMapper;
import cn.calendo.rankpagebackend.entity.TitleName;
import cn.calendo.rankpagebackend.service.impl.ITitleName;
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
public class TitleNameImpl extends ServiceImpl<TitleNameMapper, TitleName> implements ITitleName {

    /**
     * @param name 标题名字
     * @return cn.calendo.rankpagebackend.commons.R
     * @author Calendo
     * @describe 更改标题名字
     * @date 2023/3/10 12:42
     */

    //后台：设置标题名字
    @Override
    public boolean setTitleName(String id, String name) {
        TitleName byId = getById(id);
        byId.setName(name);
        byId.setId("title_name");
        boolean updateById = updateById(byId);
        if (updateById) {
            return true;
        } else {
            return false;
        }
    }

    //前台：获取标题名字
    @Override
    public TitleName getTitleName(String id) {
        LambdaQueryWrapper<TitleName> lqw = new LambdaQueryWrapper<>();
        lqw.eq(TitleName::getId, id);
        return getOne(lqw);
    }


}
