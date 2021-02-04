import java.util.*;
import java.util.concurrent.ForkJoinPool;
import java.util.stream.LongStream;

import static java.util.stream.Collectors.*;

public class Demo_FunWithStreams {

    public static void main(String a[]){
        funWithStreams();

        //parallelStreams();

        //intelliJStreamSupport();

    }


    public static void funWithStreams(){
        List<String> blocks =
                Arrays.asList("red1", "blue2", "blue1", "green2", "green1");

        // Example 1: count Blues
        /*long blueCount = 0L;
        for (String s : blocks) {
            if (s.startsWith("blue")) {
                blueCount++;
            }
        }
        System.out.println("Imperative:: number of blues = " + blueCount);

        long blueCount = blocks.stream()
                .filter(s -> s.startsWith("blue"))
                .count();
        System.out.println("Streams:: number of blues = " + blueCount);

*/




        // Example 2
/*
        List<String> result = new ArrayList<>();
        for (String block : blocks) {
            if (block.startsWith("blue")) {
                String toUpperCase = block.toUpperCase();
                result.add(toUpperCase);
            }
        }
        result.sort(null);
        System.out.println("Imperative:: " + result);

*/
        List<String> result = blocks.stream()
                .filter(block -> block.startsWith("blue"))
                .map(String::toUpperCase)
                .sorted()
                .collect(toList());
        System.out.println("Streams:: " + result);


    }


    public static void parallelStreams() {
        long time = System.currentTimeMillis();
        long sum = 0;
        for (long l = 0; l < Integer.MAX_VALUE ; l ++ ){
            sum = sum + l;
        }

        System.out.println("Traditional for-loop way!");
        System.out.println("Sum of all numbers till Integer.MAX_VALUE: " + sum);
        System.out.println("Time required (ms): " + (System.currentTimeMillis() - time));
        System.out.println("-------------------------");

        System.out.println("Using streams! ");
        time = System.currentTimeMillis();
        sum = 0;
        sum += LongStream.range(0, Integer.MAX_VALUE)
                .sum();
        System.out.println("Sum of all numbers till Integer.MAX_VALUE: " + sum);
        System.out.println("Time required (ms): " + (System.currentTimeMillis() - time));
        System.out.println("-------------------------");


        ForkJoinPool commonPool = ForkJoinPool.commonPool();
        System.out.println("Parallelism Level= " + commonPool.getParallelism());

        System.out.println("Using parallel streams! ");
        time = System.currentTimeMillis();
        sum = LongStream.range(0L, Integer.MAX_VALUE).parallel().sum();
        System.out.println("Sum of all numbers till Integer.MAX_VALUE:" + sum);
        System.out.println("Time required (ms): " + (System.currentTimeMillis() - time));


    }



    public static void intelliJStreamSupport(){

        List<String> studentRec = Arrays.asList("John, CS 563","Lee, CS 563","John, CS 561","Steve, CS 582","Lee, CS 571","Steve, CS 515" );


        // For-loop way
        System.out.println("Counting how many courses John registered. ");
        long counterJohn = 0;
        for(String s : studentRec){
            if(s.contains("John"))
                counterJohn += 1;
        }


        // IntelliJ suggests ... which is cool! :)
        counterJohn = studentRec.stream().filter(x->x.contains("John")).count();

        Map<String, Long> groupbyForLoop = new HashMap<>();

        for(String s : studentRec){
            String[] line = s.split(",");
            if(!groupbyForLoop.containsKey(line[0]))
                groupbyForLoop.put(line[0], 1L);
            else
                groupbyForLoop.put(line[0], groupbyForLoop.get(line[0]) + 1);
        }

        //Intellij Suggests ... but this ain't cool :(
        Map<String,Long> groupByIntelliJ = new HashMap<>();
        studentRec.stream().map(s -> s.split(",")).forEach(line -> {
            if (!groupbyForLoop.containsKey(line[0]))
                groupbyForLoop.put(line[0], 1L);
            else
                groupbyForLoop.put(line[0], groupbyForLoop.get(line[0]) + 1);
        });

        // This is cool!
        Map<String,Long> groupBy=  studentRec.stream()
                .collect(groupingBy(x->x.split(",")[0], counting()));

        Long johnsCourses = groupBy.get("John");
        System.out.println(johnsCourses);
    }

}

