package com.arcsinw.keepweight.model;

import java.util.Date;

/**
 * 体重数据
 */
public class Weight {
    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 体重，kg
     */
    private double weight;

    /**
     * 记录时间
     */
    private Date createTime;
}
