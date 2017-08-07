package GC;

/**
 * Created by qq136 on 2017/8/3.
 */
public class GCTest {

    public Object instance = null;
    private static final int _1MB = 1024*1024;
    /**
     * 这个成员属性的唯一意义就是站点内存，一遍能再GC日志中看清楚时被回收。
     */
    private byte[] bigSize = new byte[2*_1MB];
    public static void testGC(){
        GCTest gcA = new GCTest();
        GCTest gcB = new GCTest();
        gcA.instance = gcB;
        gcB.instance = gcA;
        gcA = null;
        gcB = null;
        System.gc();//假设在这行发生GC，gcA和gcB是否会被回收。
    }

    public static void main(String[] args) {
        testGC();
    }

}
