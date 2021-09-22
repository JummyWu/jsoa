package com.github.jummy.entity.auth;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.github.jummy.base.entity.Entity;
import com.github.jummy.database.mybatis.auth.DataScopeType;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

import static com.baomidou.mybatisplus.annotation.SqlCondition.LIKE;

/**
 * <p>
 *     实体类
 *     角色的资源
 * </p>
 *
 * @author: jummy
 * @create_date: 16/09/2021 10:22 下午
 * @desc:
 */
@Data
@NoArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("j_role")
@ApiModel(value = "Role", description = "角色")
@AllArgsConstructor
public class Role extends Entity<Long> {

    private static final long serialVersionUID = 1L;

    /**
     * 名称
     */
    @ApiModelProperty(value = "名称")
    @NotEmpty(message = "名称不能为空")
    @Size(max = 30, message = "名称长度不能超过30")
    @TableField(value = "name", condition = LIKE)
    private String name;

    /**
     * 编码
     */
    @ApiModelProperty(value = "编码")
    @Size(max = 20, message = "编码长度不能超过20")
    @TableField(value = "code", condition = LIKE)
    private String code;

    /**
     * 描述
     */
    @ApiModelProperty(value = "描述")
    @Size(max = 100, message = "描述长度不能超过100")
    @TableField(value = "describe_", condition = LIKE)
    private String describe;

    /**
     * 状态
     */
    @ApiModelProperty(value = "状态")
    @TableField("state")
    private Boolean state;

    /**
     * 内置角色
     */
    @ApiModelProperty(value = "内置角色")
    @TableField("readonly_")
    private Boolean readonly;

    /**
     * 数据权限
     * #DataScopeType{ALL:1,全部;THIS_LEVEL:2,本级;THIS_LEVEL_CHILDREN:3,本级以及子级;CUSTOMIZE:4,自定义;SELF:5,个人;}
     */
    @ApiModelProperty(value = "数据权限")
    @NotNull(message = "数据权限不能为空")
    @TableField("ds_type")
    private DataScopeType dsType;

    @Builder
    public Role(Long id, LocalDateTime createTime, LocalDateTime updateTime,
                String name, String code, String describe, Boolean state,
                Boolean readonly, DataScopeType dsType){
        this.id = id;
        this.createTime = createTime;
        this.updateTime = updateTime;
        this.name = name;
        this.code = code;
        this.describe = describe;
        this.state = state;
        this.readonly = readonly;
        this.dsType = dsType;
    }
}
