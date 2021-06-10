package entity;

public class Friends {
    public int user_1;
    public int user_2;

    public Friends(int user_1, int user_2) {
        this.user_1 = user_1;
        this.user_2 = user_2;
    }

    public int getUser_1() {
        return user_1;
    }

    public void setUser_1(int user_1) {
        this.user_1 = user_1;
    }

    public int getUser_2() {
        return user_2;
    }

    public void setUser_2(int user_2) {
        this.user_2 = user_2;
    }
}
