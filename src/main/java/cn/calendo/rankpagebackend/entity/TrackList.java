package cn.calendo.rankpagebackend.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author Calendo
 * @version 1.0
 * @description TODO
 * @date 2023/5/7 14:49
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class TrackList implements Serializable {

    /**
     * 序列化配置
     */
    private static final long serialVersionUID = 1L;

    private String id;

    private String trackName;

}
