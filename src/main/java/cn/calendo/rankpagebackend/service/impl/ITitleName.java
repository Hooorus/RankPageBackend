package cn.calendo.rankpagebackend.service.impl;

import cn.calendo.rankpagebackend.entity.TitleName;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * @author Calendo
 * @version 1.0
 * @description TODO
 * @date 2023/3/10 12:18
 */
public interface ITitleName extends IService<TitleName> {

    /**
    * @param id 名称
    * @param name 标题名字
    * @return boolean
    * @author Calendo
    * @date 2023/3/10 13:48
    */
    boolean setTitleName(String id, String name);

    /**
    * @param id 名称
    * @param name 标题名字
    * @return boolean
    * @author Calendo
    * @date 2023/3/10 13:48
    */
    TitleName getTitleName(String id);

}
