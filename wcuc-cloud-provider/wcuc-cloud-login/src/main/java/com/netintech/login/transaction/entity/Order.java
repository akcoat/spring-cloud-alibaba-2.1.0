package com.netintech.login.transaction.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author zjw
 * @since 2020-08-16
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="Order对象", description="")
public class Order extends Model<Order> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id")
    private Integer id;

    private Integer price;

    private String name;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
