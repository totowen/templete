package proxy.stat;

/**
 * Created by qq136 on 2017/8/8.
 */
public class Proxy implements Target {

    private Target target;

    public Proxy(Target target){
        this.target = target;
    }

    @Override
    public String execute() {
        System.out.println("perProcess");
        String execute = this.target.execute();
        System.out.println("postProcess");
        return execute;
    }
}
