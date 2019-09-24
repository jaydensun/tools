import sun.misc.Unsafe;

import java.lang.reflect.Field;

/**
 * @author sunyongjun
 * @since 2019/9/23
 */
public class TestUnSafe {
    private static final Unsafe unsafe; //记录变量state在类TestUnSafe中的偏移值（2.2.2）
    private static final long stateOffset; //变量(2.2.3)
    private volatile long state = 0;

    static {
//        unsafe = Unsafe.getUnsafe();
        try {
            unsafe = getUnsafe();
        } catch (NoSuchFieldException | IllegalAccessException e) {
            throw new Error("get unsafe failed");
        }
        try { //获取state变量在类TestUnSafe中的偏移值(2.2.4)
            stateOffset = unsafe.objectFieldOffset(TestUnSafe.class.getDeclaredField("state"));
        } catch (Exception ex) {
            System.out.println(ex.getLocalizedMessage());
            throw new Error(ex);
        }


    }

    public static void main(String[] args) { //创建实例，并且设置state值为1(2.2.5)
        TestUnSafe test = new TestUnSafe(); //(2.2.6)
        System.out.println("test.state = " + test.state);

        Boolean sucess = unsafe.compareAndSwapInt(test, stateOffset, 0, 1);
        System.out.println(sucess);
        System.out.println("test.state = " + test.state);

        /**
         * 操作数组:
         * 可以获取数组的在内容中的基本偏移量（arrayBaseOffset），获取数组内元素的间隔（比例），
         * 根据数组对象和偏移量获取元素值（getObject），设置数组元素值（putObject），示例如下。
         */
        String[] strings = new String[]{"1", "2", "3"};
        long i = unsafe.arrayBaseOffset(String[].class);
        System.out.println("string[] base offset is :" + i);

//every index scale
        long scale = unsafe.arrayIndexScale(String[].class);
        System.out.println("string[] index scale is " + scale);

//print first string in strings[]
        System.out.println("first element is :" + unsafe.getObject(strings, i));

//set 100 to first string
        unsafe.putObject(strings, i + scale * 0, "100");

//print first string in strings[] again
        System.out.println("after set ,first element is :" + unsafe.getObject(strings, i + scale * 0));
    }

    private static Unsafe getUnsafe() throws NoSuchFieldException, IllegalAccessException {
        Field field = Unsafe.class.getDeclaredField("theUnsafe");
        field.setAccessible(true);
        return (Unsafe) field.get(null);
    }
}
