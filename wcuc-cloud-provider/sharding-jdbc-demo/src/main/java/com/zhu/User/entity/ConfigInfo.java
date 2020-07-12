//package com.zhu.User.entity;
//
//import com.baomidou.mybatisplus.annotation.TableName;
//import com.baomidou.mybatisplus.annotation.IdType;
//import com.baomidou.mybatisplus.extension.activerecord.Model;
//import java.util.Date;
//import com.baomidou.mybatisplus.annotation.TableId;
//import com.baomidou.mybatisplus.annotation.TableField;
//import java.io.Serializable;
//import io.swagger.annotations.ApiModel;
//import io.swagger.annotations.ApiModelProperty;
//import lombok.Data;
//import lombok.EqualsAndHashCode;
//import lombok.experimental.Accessors;
//
///**
// * <p>
// * config_info
// * </p>
// *
// * @author zjw
// * @since 2020-06-29
// */
//@Data
//@EqualsAndHashCode(callSuper = false)
//@Accessors(chain = true)
//@TableName("config_info")
//@ApiModel(value="ConfigInfo对象", description="config_info")
//public class ConfigInfo extends Model<ConfigInfo> {
//
//    private static final long serialVersionUID = 1L;
//
//    @ApiModelProperty(value = "id")
//    @TableId(value = "id", type = IdType.AUTO)
//    private Long id;
//
//    @ApiModelProperty(value = "data_id")
//    @TableField("data_id")
//    private String dataId;
//
//    @TableField("group_id")
//    private String groupId;
//
//    @ApiModelProperty(value = "content")
//    @TableField("content")
//    private String content;
//
//    @ApiModelProperty(value = "md5")
//    @TableField("md5")
//    private String md5;
//
//    @ApiModelProperty(value = "创建时间")
//    @TableField("gmt_create")
//    private Date gmtCreate;
//
//    @ApiModelProperty(value = "修改时间")
//    @TableField("gmt_modified")
//    private Date gmtModified;
//
//    @ApiModelProperty(value = "source user")
//    @TableField("src_user")
//    private String srcUser;
//
//    @ApiModelProperty(value = "source ip")
//    @TableField("src_ip")
//    private String srcIp;
//
//    @TableField("app_name")
//    private String appName;
//
//    @ApiModelProperty(value = "租户字段")
//    @TableField("tenant_id")
//    private String tenantId;
//
//    @TableField("c_desc")
//    private String cDesc;
//
//    @TableField("c_use")
//    private String cUse;
//
//    @TableField("effect")
//    private String effect;
//
//    @TableField("type")
//    private String type;
//
//    @TableField("c_schema")
//    private String cSchema;
//
//
//    @Override
//    protected Serializable pkVal() {
//        return this.id;
//    }
//
//}
