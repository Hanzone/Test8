import org.eclipse.collections.impl.factory.Sets;

import java.util.BitSet;
import java.util.Iterator;
import java.util.Set;

public class BitMapTest {
    public static void main(String[] args){
        testBitSetcalculate2();
        testBitSetcalculate();
        testBitSetMemory();
    }

    private static void testBitSetcalculate2(){

        long beforeMemory = Runtime.getRuntime().totalMemory();
        long start1=System.currentTimeMillis();

        int size = 100000000;
        BitSet set = new BitSet(size);
        for (int j = 0; j < size; j++) {
//            set.set(j, RandomUtils.nextBoolean());
            set.set(j, Boolean.TRUE);
        }

        long beforeMemory2 = Runtime.getRuntime().totalMemory();
        long start2=System.currentTimeMillis();
        System.out.println("总共内存使用:" + (beforeMemory2 - beforeMemory) / 1024 / 1024 + "MB");
        System.out.println("存入内存耗时:"+(start2-start1)+"毫秒");
    }

    private static void testBitSetcalculate(){
        /*运行前内存*/
        long beforeMemory = Runtime.getRuntime().totalMemory();
        long start1=System.currentTimeMillis();
        Set<BitSet> sets = Sets.mutable.empty();
        int size = 100000000;
        for(int i = 0; i< 10 ;i++){
            BitSet set = new BitSet(size);
            for (int j = 0; j < size; j++) {
//                set.set(j, RandomUtils.nextBoolean());
                set.set(j, Boolean.TRUE);
            }
            sets.add(set);
        }

        long beforeMemory2 = Runtime.getRuntime().totalMemory();
        long start2=System.currentTimeMillis();
        System.out.println("总共内存使用:" + (beforeMemory2 - beforeMemory) / 1024 / 1024 + "MB");
        System.out.println("存入内存耗时:"+(start2-start1)+"毫秒");

        Iterator<BitSet> iterator = sets.iterator();
        BitSet set = iterator.next();
        while(iterator.hasNext()){
            BitSet tmp =iterator.next();
            set.and(tmp);
        }

        long beforeMemory3= Runtime.getRuntime().totalMemory();
        long start3=System.currentTimeMillis();
        System.out.println("计算后内存:" + (beforeMemory3 - beforeMemory2) / 1024 / 1024 + "MB");
        System.out.println("计算耗时:"+(start3-start2)+"毫秒");
    }


    private static void testBitSetMemory() {
        /*运行前内存*/
        long beforeMemory = Runtime.getRuntime().totalMemory();
        long start1=System.currentTimeMillis();

        BitSet set = new BitSet(1000000000);
        for (int i = 0; i < 1000000000; i++) {
            /*假设898989这个数不在20亿个数里面*/
            if (i != 898989) {
                set.set(i, true);
            }
        }
        /*创建20亿个数后所占内存*/
        long afterMemory = Runtime.getRuntime().totalMemory();
        long end1=System.currentTimeMillis();
        System.out.println("总共内存使用:" + (afterMemory - beforeMemory) / 1024 / 1024 + "MB");
        System.out.println("存入内存耗时:"+(end1-start1)+"毫秒");
        long start2 = System.currentTimeMillis();
        boolean isExit1=set.get(898989);
        boolean isExit2=set.get(900000);

        long end2 = System.currentTimeMillis();
        /*输出在20亿个数中判断898989是否包含在里面*/
        System.out.println(isExit1);
        System.out.println("20个亿中"+(isExit1?"包含":"不包含")+898989);
        System.out.println("20个亿中"+(isExit2?"包含":"不包含")+900000);
        System.out.println("查询用时:"+(end2 - start2)+"毫秒");
        System.out.println("max:"+Integer.MAX_VALUE);


    }

}