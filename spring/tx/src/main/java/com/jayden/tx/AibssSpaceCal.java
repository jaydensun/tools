package com.jayden.tx;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * Created by 089245 on 2017/7/18.
 */
@Entity
@Table(name = "TT_AIR_AIBSS_SPACE_CAL")
public class AibssSpaceCal {


    private static final long serialVersionUID = 1L;

    /**
     *  'id主键'
     */
    @Id
//	@GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long  id ;
    /**
     *  '订舱表id'
     */
    @Column(name = "SPACE_ID")
    private Long  spaceId ;
    /**
     * '开始值'
     */
    @Column(name = "START_VALUE")
    private Integer  startValue ;
    /**
     * '结束值'
     */
    @Column(name = "END_VALUE")
    private Integer  endValue ;
    /**
     * '单价'
     */
    @Column(name = "UNIT_PRICE")
    private Double  unitPrice;
    /**
     * '单价标量值'
     */
    @Column(name = "UNIT_PRICE_BASIC")
    private Double unitPriceBasic;
    /**
     * '版本号'
     */
    @Column(name = "VERSION")
    private int version;
    /**
     * '创建人'
     */
    @Column(name = "CREATED_EMP_CODE")
    private String   createdEmpCode;
    /**
     *  '创建时间'
     */
    @Column(name = "CREATED_TM")
    private Date createdTm;
    /**
     * '修改人'
     */
    @Column(name = "MODIFIED_EMP_CODE")
    private String  modifiedEmpCode;
    /**
     * '修改时间'
     */
    @Column(name = "MODIFIED_TM")
    private Date  modifiedTm;



    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public Long getSpaceId() {
        return spaceId;
    }
    public void setSpaceId(Long spaceId) {
        this.spaceId = spaceId;
    }
    public Integer getStartValue() {
        return startValue;
    }
    public void setStartValue(Integer startValue) {
        this.startValue = startValue;
    }
    public Integer getEndValue() {
        return endValue;
    }
    public void setEndValue(Integer endValue) {
        this.endValue = endValue;
    }
    public Double getUnitPrice() {
        return unitPrice;
    }
    public void setUnitPrice(Double unitPrice) {
        this.unitPrice = unitPrice;
    }
    public Double getUnitPriceBasic() {
        return unitPriceBasic;
    }
    public void setUnitPriceBasic(Double unitPriceBasic) {
        this.unitPriceBasic = unitPriceBasic;
    }
    public int getVersion() {
        return version;
    }
    public void setVersion(int version) {
        this.version = version;
    }
    public String getCreatedEmpCode() {
        return createdEmpCode;
    }
    public void setCreatedEmpCode(String createdEmpCode) {
        this.createdEmpCode = createdEmpCode;
    }
    public Date getCreatedTm() {
        return createdTm;
    }
    public void setCreatedTm(Date createdTm) {
        this.createdTm = createdTm;
    }
    public String getModifiedEmpCode() {
        return modifiedEmpCode;
    }
    public void setModifiedEmpCode(String modifiedEmpCode) {
        this.modifiedEmpCode = modifiedEmpCode;
    }
    public Date getModifiedTm() {
        return modifiedTm;
    }
    public void setModifiedTm(Date modifiedTm) {
        this.modifiedTm = modifiedTm;
    }


}
