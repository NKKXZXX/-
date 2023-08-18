package cn.nkkxzxx.propertymgrsys.model.entity;

import java.util.Objects;

public class Room {
    private char building;    // 座次
    private String room;  // 房间号
    private String floor; // 楼层
    private String serialNumber; // 编号
    private Tenement tenement;

    /**
     * 构造方法
     */
    public Room() {
    }

    public Room(char building, String room, String floor) {
        this.building = building;
        this.room = room;
        this.floor = floor;
        initSerialNumber();
    }

    public Room(char building, String room, String floor,String name,String phone) {
        this.building = building;
        this.room = room;
        this.floor = floor;
        initSerialNumber();
        this.tenement = new Tenement(name,phone);
    }


    private void initSerialNumber(){
        if(Integer.parseInt(room) < 10)
            this.serialNumber = building + floor + "0" + room;
        else
            this.serialNumber = building + floor + room;
    }

    /**
     * get和set系列方法
     */
    public char getBuilding() {
        return building;
    }

    public void setBuilding(char building) {
        this.building = building;
        initSerialNumber();
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
        initSerialNumber();
    }

    public String getFloor() {
        return floor;
    }

    public void setFloor(String floor) {
        this.floor = floor;
        initSerialNumber();
    }

    public Tenement getTenement() {
        return tenement;
    }

    public void setTenement(Tenement tenement) {
        this.tenement = tenement;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    @Override
    public String toString() {
        return serialNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Room room1 = (Room) o;
        return building == room1.building && Objects.equals(room, room1.room) && Objects.equals(floor, room1.floor) && Objects.equals(serialNumber, room1.serialNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(building, room, floor, serialNumber);
    }
}
