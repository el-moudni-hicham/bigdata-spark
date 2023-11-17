import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import scala.Tuple2;

import java.util.Arrays;

public class SalesCounter {
    public static void main(String[] args) {
        SparkConf conf=new SparkConf().setAppName("Sales Counter").setMaster("local");
        JavaSparkContext sc=new JavaSparkContext(conf);
        JavaRDD<String> rddLines=sc.textFile("src/main/resources/sales.csv");
        //JavaRDD<String> rddCities=rddLines.flatMap(line-> Arrays.asList(line.split(",")[1]).iterator());
        JavaPairRDD<String,Integer> pairRDDCities=rddLines.mapToPair(line->{
            String[] parts = line.split(",");
            String city = parts[1];
            return new Tuple2<>(city,1);
        });
        JavaPairRDD<String,Integer> wordCount=pairRDDCities.reduceByKey((a,b)->a+b);
        wordCount.foreach(e-> System.out.println(e._1+" "+e._2));
    }
}