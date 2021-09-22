package com.github.jummy.entity.auth;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.github.jummy.base.entity.Entity;
import com.github.jummy.enumeration.auth.Sex;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

import static com.baomidou.mybatisplus.annotation.SqlCondition.LIKE;

/**
 * @author: jummy
 * @create_date: 16/09/2021 11:33 下午
 * @desc:
 */
@Data
@NoArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("c_user")
@ApiModel(value = "User", description = "用户")
@AllArgsConstructor
public class User extends Entity<Long> {

    private static final long serialVersionUID = 1L;

    /**
     * 帐号
     */
    @ApiModelProperty(value = "帐号")
    @NotEmpty(message = "帐号不能为空")
    @Size(max = 30, message = "帐号长度不能超过30")
    @TableField(value = "account", condition = LIKE)
    private String account;

    /**
     * 姓名
     */
    @ApiModelProperty(value = "姓名")
    @NotEmpty(message = "姓名不能为空")
    @Size(max = 50, message = "名字长度不能超过50")
    @TableField(value = "name", condition = LIKE)
    private String name;

    /**
     * 岗位
     * c_station
     *
     * @Echo(api = STATION_ID_CLASS)
     */
    @ApiModelProperty(value = "岗位")
    @TableField("station_id")
    private Long stationId;

    /**
     * 邮箱
     */
    @ApiModelProperty(value = "邮箱")
    @Size(max = 255, message = "邮箱长度255")
    @TableField(value = "email", condition = LIKE)
    private String email;

    /**
     * 手机
     */
    @ApiModelProperty(value = "手机")
    @Size(max = 20, message = "手机长度不能超过20")
    @TableField(value = "mobile", condition = LIKE)
    private String mobile;

    /**
     * 性别
     * #Sex(w:女;M:男;N:未知)
     */
    @ApiModelProperty(value = "性别")
    @TableField(value = "sex")
    private Sex sex;

    /**
     * 状态
     */
    @ApiModelProperty(value = "状态")
    @TableField(value = "state")
    private Boolean state;

    /**
     * 头像
     */
    @ApiModelProperty(value = "头像")
    @Size(max = 255, message = "头像长度不能超过255")
    @TableField(value = "avatar", condition = LIKE)
    private String avatar;

    /**
     * 职位状态
     *
     * @Echo(api = DICTIONARY_ITEM_CLASS,  dictType = DictionaryType.POSITION_STATUS)
     */
    @ApiModelProperty(value = "职位状态")
    @Size(max = 2, message = "职位状态长度不能超过2")
    @TableField(value = "position_status", condition = LIKE)
    private String positionStatus;

    /**
     * 密码
     */
    @ApiModelProperty(value = "密码")
    @NotEmpty(message = "密码不能为空")
    @Size(max = 64, message = "密码长度不能超过64")
    @TableField(value = "password", condition = LIKE)
    private String password;

    /**
     * 最后登录时间
     */
    @ApiModelProperty(value = "最后登录时间")
    @TableField("last_login_time")
    private LocalDateTime lastLoginTime;

    @Builder
    public User(Long id, LocalDateTime createTime, LocalDateTime updateTime,
                String account,  String name, Long stationId, String email,
                String mobile, Sex sex, Boolean state, String avatar,  String positionStatus,
                String password,  LocalDateTime lastLoginTime) {
        this.id = id;
        this.createTime = createTime;
        this.updateTime = updateTime;
        this.account = account;
        this.name = name;
        this.stationId = stationId;
        this.email = email;
        this.mobile = mobile;
        this.sex = sex;
        this.state = state;
        this.avatar = avatar;
        this.positionStatus = positionStatus;
        this.password = password;
        this.lastLoginTime = lastLoginTime;
    }
}
