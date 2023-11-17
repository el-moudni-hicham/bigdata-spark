import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import scala.Tuple2;

import java.util.Arrays;
import java.util.List;


public class RDDsGraph {
    public static void main(String[] args) {
        SparkConf conf=new SparkConf().setAppName("RDD Graph").setMaster("local");
        JavaSparkContext sc=new JavaSparkContext(conf);

        List<String> students = Arrays.asList("hicham", "adam", "ahmed", "mohamed", "amine",
                "abdeljalil", "soufiane", "ayoub", "anir", "ayour", "anwar", "mariam");

        // Parallelize the list
        JavaRDD<String> rdd1 = sc.parallelize(students);

        // Flatmap
        JavaRDD<String>  rdd2= rdd1.flatMap(s-> Arrays.asList(s).iterator());
        System.out.println("-------------- RDD2 --------------");
        rdd2.foreach(name-> System.out.print(name+","));

        //Filter
        JavaRDD<String> rdd3 = rdd2.filter(x -> x.startsWith("a"));
        System.out.println("-------------- RDD3 --------------");
        rdd3.foreach(name-> System.out.print(name+","));

        //Filter
        JavaRDD<String> rdd4 = rdd2.filter(x -> x.endsWith("m"));
        System.out.println("-------------- RDD4 --------------");
        rdd4.foreach(name-> System.out.print(name+","));

        //Filter
        JavaRDD<String> rdd5 = rdd2.filter(x -> x.length() > 5);
        System.out.println("-------------- RDD5 --------------");
        rdd5.foreach(name-> System.out.print(name+","));

        //Union
        JavaRDD<String> rdd6 = rdd3.union(rdd4);
        System.out.println("-------------- RDD6 --------------");
        rdd6.foreach(name -> System.out.println(name+","));

        //Map
        JavaRDD<String> rdd71 = rdd5.map(x -> x.toUpperCase());
        System.out.println("-------------- RDD71 --------------");
        rdd71.foreach(name -> System.out.println(name+","));

        //Map
        JavaRDD<String> rdd81 = rdd6.map(x -> x.replace("a","å"));
        System.out.println("-------------- RDD81 --------------");
        rdd81.foreach(name -> System.out.println(name+","));


        // MapToPair
        JavaPairRDD<Integer, String> rdd7 = rdd71.mapToPair(x -> new Tuple2<>(x.length(), x));
        System.out.println("-------------- RDD7 --------------");
        rdd7.foreach(name -> System.out.println(name+","));

        //MapToPair
        JavaPairRDD<Integer, String> rdd8 = rdd81.mapToPair(x -> new Tuple2<>(x.length(), x));
        System.out.println("-------------- RDD8 --------------");
        rdd8.foreach(name -> System.out.println(name+","));

        // Union
        JavaPairRDD<Integer, String> rdd9 = rdd7.union(rdd8);
        System.out.println("-------------- RDD9 --------------");
        rdd9.foreach(name -> System.out.println(name+","));

        // Sort By
        JavaPairRDD<Integer, String> rdd10 = rdd9.sortByKey();
        System.out.println("-------------- RDD10 --------------");
        rdd10.foreach(name -> System.out.println(name+","));

        // Stop the Spark context
        //sc.stop();
    }
}
