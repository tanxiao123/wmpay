package wmpay;

import java.util.ArrayList;
import java.util.Arrays;

public class Test {

    @org.junit.Test
    public void test1() {
        ArrayList a =  new ArrayList(Arrays.asList("1","2","3") );
        ArrayList b = a;

        System.out.println(a.toString() );

        System.out.println(b.toString() );

        b.add("4");

        System.out.println(a.toString() );

        System.out.println(b.toString() );
    }
}
