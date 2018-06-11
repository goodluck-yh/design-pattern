/**
 * 目的：
 * 如何实现： 私有构造函数， 私有变量， 公有静态函数
 */
public class Singleton {
}


/**
 * 这个是懒汉式-非线程安全
 * 所谓懒汉式是指uniqueInstance 被延迟实例化
 */
class Singleton1 {
    private static Singleton1 uniqueInstance;

    private Singleton1() {
    }

    public static Singleton1 getUniqueInstance() {
        if (uniqueInstance == null) {
            uniqueInstance = new Singleton1();
        }
        return uniqueInstance;
    }
}

/**
 * 这个是懒汉式-线程安全
 * 缺点： 会牺牲效率因为会读取阻塞
 */
class Singleton2 {
    private static Singleton2 uniqueInstance;
    private Singleton2() {
    }

    public static synchronized Singleton2 getUniqueInstance(){
        if(uniqueInstance == null) {
            uniqueInstance = new Singleton2();
        }
        return uniqueInstance;
    }
}

/**
 *  这个是饿汉式-线程安全，Java 静态变量初始化是线程安全的
 *  缺点： 失去延迟实例化带来的性能优化
 */
class Singleton3{
    private static Singleton3 uniqueInstace = new Singleton3();
    private Singleton3(){
    }
}

class Singleton4 {
    private static volatile Singleton4 uniqueInstance;
    private Singleton4() {
    }
    public static Singleton4 getUniqueInstance(){
        if(uniqueInstance == null) {
            synchronized (Singleton4.class) {
                /**
                 * 需要注意这里需要两个if，
                 * 因为如果只有一个if，还是会出现多线程问题
                 */
                if (uniqueInstance == null) {
                    /**
                     * volatile 关键字是有必要的。因为下面的语句会：
                     * 1 分配内存空间
                     * 2 初始化对象
                     * 3 将uniqueInstance 指向分配的内存地址
                     * 由于JVM可以指令重拍，所以可能变成 1->3->2
                     * volatile 可以禁止重拍
                     */
                    uniqueInstance = new Singleton4();
                }
            }
        }
        return uniqueInstance;
    }
}

// TBC: 最佳实践是枚举实现