package entity;
/**
 * 团队信息实体类
 * <p>用于映射数据库中的groupinfo表</p>
 * @author 季晓东
 */
public class Group {
    /**
     * @value 小组id
     */
    public int id;
    /**
     * @value 小组头像
     */
    public int avatar;
    /**
     * @value 小组名称
     */
    public String name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAvatar() {
        return avatar;
    }

    public void setAvatar(int avatar) {
        this.avatar = avatar;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String toString(){
        return id+"&"+name+"&"+avatar;
    }
}
