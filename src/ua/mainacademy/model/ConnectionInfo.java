package ua.mainacademy.model;

public class ConnectionInfo {

    private Integer id;
    private Long time;
    private String connectionIP;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Long getTime() {
        return time;
    }

    public void setTime(Long time) {
        this.time = time;
    }

    public String getConnectionIP() {
        return connectionIP;
    }

    public void setConnectionIP(String connectionIP) {
        this.connectionIP = connectionIP;
    }

    public ConnectionInfo() {
    }

    public ConnectionInfo(Integer id, Long time, String connectionIP) {
        this.id = id;
        this.time = time;
        this.connectionIP = connectionIP;
    }

    @Override
    public String toString() {
        return id + " " + time + " " + connectionIP; // 1235 165454646534565 126.159.124.241
    }
}
