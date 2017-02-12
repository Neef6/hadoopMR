package cn.edu.nj;


import cn.edu.nj.recommend.Recommend;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

/**
 * Created by apple on 17/2/9.
 * job driver
 * encapsulate the job configuration
 */
public class JobDriver {
  public static void initJob(JobModel[] jobs) throws IOException, ClassNotFoundException, InterruptedException, URISyntaxException {
    //init jobs
    for (JobModel jobModel : jobs) {
      Job job;

      if (jobModel.getJob() == null) {
        job = Job.getInstance(jobModel.getConf(), jobModel.getJobName());
      } else {
        job = jobModel.getJob();
      }
      job.setJarByClass(jobModel.getJarClass());
      job.setJobName(jobModel.getJobName());
      FileInputFormat.setInputPaths(job, jobModel.getInputPaths());
      FileOutputFormat.setOutputPath(job, jobModel.getOutPutPath());
      //set the input data format
      job.setInputFormatClass(TextInputFormat.class);
      job.setMapperClass(jobModel.getMapper());
      job.setMapOutputKeyClass(jobModel.getMapOutKeyClass());
      job.setMapOutputValueClass(jobModel.getMapOutValueClass());
      if (jobModel.getReducer() != null) {
        job.setReducerClass(jobModel.getReducer());
        job.setOutputKeyClass(jobModel.getReduceOutKeyClass());
        job.setOutputValueClass(jobModel.getMapOutValueClass());
      }
      job.waitForCompletion(true);

    }

  }

}
