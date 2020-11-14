package com.yeyi.ai.deploy.common.pojo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * <p>
 * 服务心跳
 * </p>
 *
 * @author mahaoran
 * @since 2020-10-30
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DeployHostHeartBeatVo {

    private static final long serialVersionUID = 1L;

    /**
     * 租户id
     */
    private Long tenantId;

    /**
     * 部署项目主机id
     */
    private Long deployHostId;

    /**
     * 已用内存
     */
    private Long memory;

    /**
     * 最大内存
     */
    private Long maxMemory;

    /**
     * code已用内存
     */
    private Long codeMemory;

    /**
     * code最大内存
     */
    private Long codeMaxMemory;

    /**
     * eden已用内存
     */
    private Long edenMemory;

    /**
     * eden最大内存
     */
    private Long edenMaxMemory;

    /**
     * survivor已用内存
     */
    private Long survivorMemory;

    /**
     * survivor最大内存
     */
    private Long survivorMaxMemory;

    /**
     * old已用内存
     */
    private Long oldMemory;

    /**
     * old最大内存
     */
    private Long oldMaxMemory;

    /**
     * Metaspace已用内存
     */
    private Long metaspaceMemory;

    /**
     * Metaspace最大内存
     */
    private Long metaspaceMaxMemory;

    /**
     * 接收时间
     */
    private Date receiveTime;

    /**
     * 描述
     */
    private String description;


}
