package cn.edu.nj.kpi;

import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

/**
 * Created by apple on 17/2/4.
 */
public class Kpi {
    public static void main(String[] args) throws Exception {
        Job job = new Job();
        job.setJarByClass(Kpi.class);
        job.setJobName("Kpi");

        Path inputPath = new Path("/Users/apple/Documents/hadoopWorkspace/hadoop_MR_Test/input/kpiInput");
        Path outputPath = new Path("/Users/apple/soft/gitHub/hadoopMR/out");

        FileInputFormat.addInputPath(job,inputPath);
        FileOutputFormat.setOutputPath(job, outputPath);

        job.setMapperClass(KpiMapper.class);
        job.setReducerClass(KpiReduce.class);

//        job.setSortComparatorClass();
//        job.setCombinerClass();

        //corresponding the format out of mapper writing
        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(KpiBean.class);


        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(KpiBean.class);

        System.exit(job.waitForCompletion(true) ? 0 : 1);
    }
}
