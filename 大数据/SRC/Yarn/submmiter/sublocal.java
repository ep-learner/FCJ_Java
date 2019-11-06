package cn.edu360.mr.wc;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

public class sublocal {

    public static void main(String[] args) throws Exception {

        Configuration conf = new Configuration();

        //conf.set("fs.defaultFS", "file:///");
        //conf.set("mapreduce.framework.name", "local");
        //上述两个配置是在windows内启动时的默认值，可以不用配。
        

        Job job = Job.getInstance(conf);

        job.setJarByClass(sublocal.class);

        job.setMapperClass(WordcountMapper.class);
        job.setReducerClass(WordcountReducer.class);

        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(IntWritable.class);

        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(IntWritable.class);

        FileInputFormat.setInputPaths(job, new Path("D:/hdfs/wordcount/input"));
        FileOutputFormat.setOutputPath(job, new Path("D:/hdfs/wordcount/output"));

        job.setNumReduceTasks(3);

        boolean res = job.waitForCompletion(true);
        System.exit(res?0:1);

    }




}
