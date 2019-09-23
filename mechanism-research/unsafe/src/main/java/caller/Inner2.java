package caller;

/**
 * @author sunyongjun
 * @since 2019/9/23
 */
class Inner2 {
    public static void main(String[] args){
        A.print();
    }
}
class A {
    public static void print() {
        B.print();
    }
}

class B {
    public static void print() {
        C.print();
    }
}


class C {
    public static void print() {
        D.print();
    }
}

class D {
    public static void print() {
//        System.out.println(sun.reflect.Reflection.getCallerClass());
        System.out.println(sun.reflect.Reflection.getCallerClass(0));
//class sun.reflect.Reflection
        System.out.println(sun.reflect.Reflection.getCallerClass(1));
//class D
        System.out.println(sun.reflect.Reflection.getCallerClass(2));
//class C
        System.out.println(sun.reflect.Reflection.getCallerClass(3));
//class B
        System.out.println(sun.reflect.Reflection.getCallerClass(4));
//class A
        System.out.println(sun.reflect.Reflection.getCallerClass(5));
//class Test
    }
}
