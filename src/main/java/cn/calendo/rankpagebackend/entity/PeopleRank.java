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
 * @date 2023/3/10 12:15
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@TableName(value = "people_name")
public class PeopleRank implements Serializable {

    /**
     * 序列化配置
     */
    private static final long serialVersionUID = 1L;

    private String id;

    /**
     * @param null
     * @return 人员姓名
     * @author Calendo
     * @date 2023/3/10 12:17
     */
    @TableField(value = "name")
    private String name;

    /**
     * @param null
     * @return 专家得分
     * @author Calendo
     * @date 2023/3/10 12:18
     */
    @TableField(value = "score")
    private Integer score;

    @TableField(value = "track")
    private String track;

}
