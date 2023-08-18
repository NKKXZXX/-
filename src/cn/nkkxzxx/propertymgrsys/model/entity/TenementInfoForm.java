package cn.nkkxzxx.propertymgrsys.model.entity;

public class TenementInfoForm {
    private String serialNumber;
    private String tenementName;
    private String tenementPhone;

    public TenementInfoForm(String serialNumber, String tenementName, String tenementPhone) {
        this.serialNumber = serialNumber;
        this.tenementName = tenementName;
        this.tenementPhone = tenementPhone;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public String getTenementName() {
        return tenementName;
    }

    public void setTenementName(String tenementName) {
        this.tenementName = tenementName;
    }

    public String getTenementPhone() {
        return tenementPhone;
    }

    public void setTenementPhone(String tenementPhone) {
        this.tenementPhone = tenementPhone;
    }
}
