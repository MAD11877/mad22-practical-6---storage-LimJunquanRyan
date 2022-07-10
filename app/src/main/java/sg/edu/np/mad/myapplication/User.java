package sg.edu.np.mad.myapplication;

public class User {
    String name;
    String description;
    int id;
    boolean followed;

    public User() {
    }

    public User(String n, String d, int i, boolean f) {
        name = n;
        description = d;
        id = i;
        followed = f;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public int getId() {
        return id;
    }

    public boolean isFollowed() {
        return followed;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setFollowed(boolean followed) {
        this.followed = followed;
    }
}
