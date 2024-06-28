package packageBase;

public class Sesion {
    private static Sesion instance;
    private int userId;

    private Sesion() {}

    public static Sesion getInstance() {
        if (instance == null) {
            instance = new Sesion();
        }
        return instance;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}