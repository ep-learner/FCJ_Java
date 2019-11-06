package cn.edu360.mr.wc;


import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;

import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

public class linuxsub {
    public static void main(String[] args)throws  Exception{
        //conf
        Configuration conf = new Configuration();
        conf.set("mapreduce.framework.name","yarn");
        //Job
        Job job = Job.getInstance(conf);
        //0、jar包位置，也就是main的位置
        job.setJarByClass(linuxsub.class);
        //1、mr及其数据类型
        job.setMapperClass(WordcountMapper.class);
        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(IntWritable.class);

        job.setReducerClass(WordcountReducer.class);
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(IntWritable.class);

        FileInputFormat.setInputPaths(job,new Path("/wordcount/input"));
        FileOutputFormat.setOutputPath(job,new Path("/wordcount/output"));

        job.setNumReduceTasks(2);

        boolean res = job.waitForCompletion(true);
        System.exit(res?0:1);


    }

}
