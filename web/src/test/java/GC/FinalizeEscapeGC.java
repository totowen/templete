package GC;

/**
 * Created by qq136 on 2017/8/7.
 */
public class FinalizeEscapeGC {
    public static FinalizeEscapeGC SAVE_HOOK = null;

    public void isAlive(){
        System.out.println("yes,i am still alive:)");
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        System.out.println("finalize method executed!");
        FinalizeEscapeGC.SAVE_HOOK = this;
    }

    public static void main(String[] args) throws InterruptedException {
        SAVE_HOOK = new FinalizeEscapeGC();
        //对象第一次成功拯救自己
        SAVE_HOOK = null;
        System.gc();
        //因为finalize（）方法优先级比较低，所以暂停0.5秒以等待它
        Thread.sleep(500);
        if(null!= SAVE_HOOK){
            SAVE_HOOK.isAlive();
        }else {
            System.out.println("no,i am dead:(");
        }
        //下面这段代码与上面完全一样，但是这次自救失败了。
        SAVE_HOOK = null;
        System.gc();
        //因为finalize（）方法优先级比较低，所以暂停0.5秒以等待它
        Thread.sleep(500);
        if(null!= SAVE_HOOK){
            SAVE_HOOK.isAlive();
        }else {
            System.out.println("no,i am dead:(");
        }
    }
}
