package cn.calendo.rankpagebackend.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author Calendo
 * @version 1.0
 * @description TODO
 * @date 2023/3/10 12:37
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@TableName(value = "title_name")
public class TitleName implements Serializable {

    /**
     * 序列化配置
     */
    private static final long serialVersionUID = 1L;

    private String id;

    /**
     * @param null
     * @return 项目名字
     * @author Calendo
     * @date 2023/3/10 12:17
     */
    @TableField(value = "title_name")
    private String name;

}
