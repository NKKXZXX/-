package cn.nkkxzxx.propertymgrsys.model.entity;

public class QueryInfoForm {
    private String serialNumber;
    private String floor;
    private String building;
    private String room;
    private String teName;
    private String tePhone;


    public QueryInfoForm() {
    }

    public QueryInfoForm(String serialNumber, String floor, String building, String room, String teName, String tePhone) {
        this.serialNumber = serialNumber;
        this.floor = floor;
        this.building = building;
        this.room = room;
        this.teName = teName;
        this.tePhone = tePhone;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public String getFloor() {
        return floor;
    }

    public void setFloor(String floor) {
        this.floor = floor;
    }

    public String getBuilding() {
        return building;
    }

    public void setBuilding(String building) {
        this.building = building;
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    public String getTeName() {
        return teName;
    }

    public void setTeName(String teName) {
        this.teName = teName;
    }

    public String getTePhone() {
        return tePhone;
    }

    public void setTePhone(String tePhone) {
        this.tePhone = tePhone;
    }
}
