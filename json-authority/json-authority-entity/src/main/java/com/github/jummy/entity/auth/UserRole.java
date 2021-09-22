package com.github.jummy.entity.auth;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.github.jummy.base.entity.SuperEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

/**
 * <p>
 *     角色分类
 *     帐号绑定的角色
 * </p>
 *
 * @author: jummy
 * @create_date: 17/09/2021 9:19 下午
 * @desc:
 */
@Data
@NoArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("c_user_role")
@ApiModel(value = "UserRole", description = "角色分配")
@AllArgsConstructor
public class UserRole extends SuperEntity<Long> {

    private static final long serialVersionUID = 1L;

    /**
     * 角色ID
     * c_role
     */
    @ApiModelProperty(value = "角色ID")
    @NotNull(message = "角色ID不能为空")
    @TableField(value = "role_id")
    private Long roleId;

    /**
     * 用户ID
     * c_user
     */
    @ApiModelProperty(value = "用户ID")
    @NotNull(message = "用户ID不能为空")
    @TableField(value = "user_id")
    private Long userId;

    @Builder
    public UserRole(Long id, LocalDateTime createTime,
                    Long roleId, Long userId) {
        this.id = id;
        this.createTime = createTime;
        this.roleId = roleId;
        this.userId = userId;
    }
}
