import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import scala.Tuple2;

import java.util.Arrays;

public class SumSales {
    public static void main(String[] args) {
        SparkConf conf=new SparkConf().setAppName("Sales Counter").setMaster("local[*]");
        JavaSparkContext sc=new JavaSparkContext(conf);
        JavaRDD<String> rddLines=sc.textFile("src/main/resources/sales.csv");

        JavaPairRDD<String, Integer> pricePairs = rddLines
                .mapToPair(line -> {
                    String[] parts = line.split(",");
                    String date = parts[0];
                    String year = date.split("-")[0];

                    String city = parts[1];
                    int price = Integer.parseInt(parts[3]);
                    if (year.equals("2022"))
                        return new Tuple2<>(city, price);
                    return new Tuple2<>(city, 0);
                });

        JavaPairRDD<String,Integer> sumPrices=pricePairs.reduceByKey((price1,price2)->price1+price2);
        sumPrices.foreach(e-> System.out.println(e._1+" "+e._2));
    }
}