package proxy.stat;

/**
 * Created by qq136 on 2017/8/8.
 */
public class TargetImpl implements Target {
    @Override
    public String execute() {
        System.out.println("TargetImpl execute");
        return "execute";
    }
}
