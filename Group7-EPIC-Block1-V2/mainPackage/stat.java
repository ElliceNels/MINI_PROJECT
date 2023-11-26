package mainPackage;

public class stat {
    String user_ID;
    String type_of_stat;
    double value;

    public stat(String user_ID, String type_of_stat, double value) {
        this.user_ID = user_ID;
        this.type_of_stat = type_of_stat;
        this.value = value;
    }

    public String getUser_ID() {
        return user_ID;
    }

    public void setUser_ID(String user_ID) {
        this.user_ID = user_ID;
    }

    public String getType_of_stat() {
        return type_of_stat;
    }

    public void setType_of_stat(String type_of_stat) {
        this.type_of_stat = type_of_stat;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }
}
