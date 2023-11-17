# Big Data Spark RDDs Projects 

```
This repository contains a collection of Big Data projects implemented using Apache Spark and
Resilient Distributed Datasets (RDDs).Apache Spark is an open-source, distributed computing
system that provides fast and general-purpose cluster-computing frameworks for big data processing.
RDDs (Resilient Distributed Datasets) are a fundamental data structure of Spark, providing
fault-tolerant parallel processing of data.
```

## Table of Contents
1. [Overview](#overview)
2. [Prerequisites](#prerequisites)
3. [Applications](#applications)
   * [Words Count](#words-count)
   * [RDDS Graph](#rdds-graph)
   * [Sales Analysis](#sales-analysis)
   * [Climate Data Analysis](#climate-data-analysis)




## Overview
### Apache Spark
Apache Spark is a fast and general-purpose cluster computing system for big data processing. It provides high-level APIs in Java, Scala, Python, and R, and an optimized engine that supports general execution graphs. Spark also supports a rich set of higher-level tools, including Spark SQL for SQL and structured data processing, MLlib for machine learning, GraphX for graph processing, and Spark Streaming for stream processing.

### Resilient Distributed Datasets (RDDs)
RDDs are a fundamental data structure of Apache Spark. They are fault-tolerant collections of objects partitioned across a cluster of machines. RDDs can be created from external data or by transforming other RDDs. They support two types of operations: transformations, which create a new RDD from an existing one, and actions, which return a value to the driver program or write data to external storage.

## Prerequisites

  * Spark
    
      - dependency :
        
      ```maven
        <dependency>
              <groupId>org.apache.spark</groupId>
              <artifactId>spark-core_2.13</artifactId>
              <version>3.4.1</version>
          </dependency>
      ```
  * Java

## Applications

### Words Count

* Description:
This project demonstrates the use of Spark RDDs for counting words in large text datasets. It includes functionalities for preprocessing text data, counting word occurrences, and presenting the results.

* Usage:

```java
public class WordCount {
    public static void main(String[] args) {
        SparkConf conf=new SparkConf().setAppName("Word Count").setMaster("local");
        JavaSparkContext sc=new JavaSparkContext(conf);
        JavaRDD<String> rddLines=sc.textFile("src/main/resources/words.txt");
        JavaRDD<String> rddWords=rddLines.flatMap(line-> Arrays.asList(line.split(" ")).iterator());
        JavaPairRDD<String,Integer> pairRDDWords=rddWords.mapToPair(word->new Tuple2<>(word,1));
        JavaPairRDD<String,Integer> wordCount=pairRDDWords.reduceByKey((a,b)->{
            return a+b;});
        wordCount.foreach(e-> System.out.println(e._1+" "+e._2));
    }
}
```
### RDDS Graph 

* Description:
This project is to create an RDD Graph.

![image](https://github.com/el-moudni-hicham/bigdata-spark-rdd/assets/85403056/5ef12fc2-66ef-4908-b8ad-7ebc8a208c1b)

```java
List<String> students = Arrays.asList("hicham", "adam", "ahmed", "mohamed", "amine",
                "abdeljalil", "soufiane", "ayoub", "anir", "ayour", "anwar", "mariam");
// Parallelize the list
JavaRDD<String> rdd1 = sc.parallelize(students);
```

```java
// Flatmap
JavaRDD<String>  rdd2= rdd1.flatMap(s-> Arrays.asList(s).iterator());
System.out.println("-------------- RDD2 --------------");
rdd2.foreach(name-> System.out.print(name+","));
```

![image](https://github.com/el-moudni-hicham/bigdata-spark-rdd/assets/85403056/9c42f9f0-0ffc-4723-9978-8d31cc2716b1)


```java
//Filter
JavaRDD<String> rdd3 = rdd2.filter(x -> x.startsWith("a"));
System.out.println("-------------- RDD3 --------------");
rdd3.foreach(name-> System.out.print(name+","));
```

![image](https://github.com/el-moudni-hicham/bigdata-spark-rdd/assets/85403056/7123f07e-efa0-434a-9cfc-d49d7b13bd0f)


```java
//Filter
JavaRDD<String> rdd4 = rdd2.filter(x -> x.endsWith("m"));
System.out.println("-------------- RDD4 --------------");
rdd4.foreach(name-> System.out.print(name+","));
```

![image](https://github.com/el-moudni-hicham/bigdata-spark-rdd/assets/85403056/399f9a6c-200c-4fb3-ad9d-5215e26cd8bd)


```java
//Filter
JavaRDD<String> rdd5 = rdd2.filter(x -> x.length() > 5);
System.out.println("-------------- RDD5 --------------");
rdd5.foreach(name-> System.out.print(name+","));
```

![image](https://github.com/el-moudni-hicham/bigdata-spark-rdd/assets/85403056/1465c239-06bc-4c31-aedb-67fef4caec85)


```java
//Union
JavaRDD<String> rdd6 = rdd3.union(rdd4);
System.out.println("-------------- RDD6 --------------");
rdd6.foreach(name -> System.out.println(name+","));
```

![image](https://github.com/el-moudni-hicham/bigdata-spark-rdd/assets/85403056/38e1c1ac-791e-4087-aa10-bf6c7f165ffa)


```java
//Map
JavaRDD<String> rdd71 = rdd5.map(x -> x.toUpperCase());
System.out.println("-------------- RDD71 --------------");
rdd71.foreach(name -> System.out.println(name+","));
```

![image](https://github.com/el-moudni-hicham/bigdata-spark-rdd/assets/85403056/a8f00d1c-3b09-4c72-a3d0-ffe1e491707c)


```java
//Map
JavaRDD<String> rdd81 = rdd6.map(x -> x.replace("a","å"));
System.out.println("-------------- RDD81 --------------");
rdd81.foreach(name -> System.out.println(name+","));
```

![image](https://github.com/el-moudni-hicham/bigdata-spark-rdd/assets/85403056/fb450a0e-436e-4b5e-b74a-b83bec07e80e)


```java
// MapToPair
JavaPairRDD<Integer, String> rdd7 = rdd71.mapToPair(x -> new Tuple2<>(x.length(), x));
System.out.println("-------------- RDD7 --------------");
rdd7.foreach(name -> System.out.println(name+","));
```

![image](https://github.com/el-moudni-hicham/bigdata-spark-rdd/assets/85403056/d10d7c8e-f3c1-4bd1-9add-ee67a8ea01cb)


```java
//MapToPair
JavaPairRDD<Integer, String> rdd8 = rdd81.mapToPair(x -> new Tuple2<>(x.length(), x));
System.out.println("-------------- RDD8 --------------");
rdd8.foreach(name -> System.out.println(name+","));
```

![image](https://github.com/el-moudni-hicham/bigdata-spark-rdd/assets/85403056/fee32eb1-1ef0-452d-bad4-b671a182db52)


```java
// Union
JavaPairRDD<Integer, String> rdd9 = rdd7.union(rdd8);
System.out.println("-------------- RDD9 --------------");
rdd9.foreach(name -> System.out.println(name+","));
```

![image](https://github.com/el-moudni-hicham/bigdata-spark-rdd/assets/85403056/836f3e7a-31d5-45a2-9918-97b3e77d72e0)


```java
// Sort By
JavaPairRDD<Integer, String> rdd10 = rdd9.sortByKey();
System.out.println("-------------- RDD10 --------------");
rdd10.foreach(name -> System.out.println(name+","));
```

![image](https://github.com/el-moudni-hicham/bigdata-spark-rdd/assets/85403056/ec04869c-505f-4e8c-8b39-8c58c1ff78d6)



* Usage:


### Sales Analysis 

* Description:
The Sales Analysis Spark project focuses on analyzing sales data using Spark RDDs.

* Usage:

```java
SparkConf conf=new SparkConf().setAppName("Sales Counter").setMaster("local");
JavaSparkContext sc=new JavaSparkContext(conf);
JavaRDD<String> rddLines=sc.textFile("src/main/resources/sales.csv");
```

![image](https://github.com/el-moudni-hicham/bigdata-spark-rdd/assets/85403056/13cf8958-7499-4e0d-8bc5-1d412cff236a)


```java
        JavaPairRDD<String,Integer> pairRDDCities=rddLines.mapToPair(line->{
            String[] parts = line.split(",");
            String city = parts[1];
            return new Tuple2<>(city,1);
        });
        JavaPairRDD<String,Integer> wordCount=pairRDDCities.reduceByKey((a,b)->a+b);
        wordCount.foreach(e-> System.out.println(e._1+" "+e._2));        
```

![image](https://github.com/el-moudni-hicham/bigdata-spark-rdd/assets/85403056/f243b3fa-8299-4a80-82be-290712c1885f)

```java
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
```

![image](https://github.com/el-moudni-hicham/bigdata-spark-rdd/assets/85403056/03748f7f-2d32-4460-bef9-4388d8b1c13e)
  
### Climate Data Analysis 

* Description:
This project involves the analysis of large-scale climate data using Apache Spark RDDs. It explores various climate parameters, performs data transformations, and derives meaningful insights from the dataset.

* Usage:

  

