package cn.edu.nj.recommend;

import cn.edu.nj.JobDriver;
import cn.edu.nj.JobModel;
import cn.edu.nj.kpi.Kpi;
import cn.edu.nj.kpi.KpiBean;
import cn.edu.nj.kpi.KpiMapper;
import cn.edu.nj.kpi.KpiReduce;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.io.IOException;

/**
 * Created by apple on 17/2/8.
 */
public class Recommend {
  public static void main(String[] args) throws InterruptedException, IOException, ClassNotFoundException {
    Configuration conf = new Configuration();

    //calculate the user scoring matrix
    Path userScoreMarixInpath = new Path("/Users/apple/Documents/hadoopWorkspace/hadoop_MR_Test/input/recommendInput");
    Path userScoreMarixOutpath = new Path("/Users/apple/soft/gitHub/hadoopMR/out/userScoreMatrix");
    JobModel userScoreMatrixJob = new JobModel(userScoreMarixInpath, userScoreMarixOutpath, conf, null, "calculate the user scoring matrix"
            , Recommend.class, UserScoreMatrixMapper.class, Text.class, Text.class, UserScoreMatrixReducer.class, Text.class, Text.class,null);

    //Calculate the co-occurrence matrix of the item
    Path itermOccurrenceOutpath = new Path("/Users/apple/soft/gitHub/hadoopMR/out/itermOccurrenceMatrix");
    JobModel itermOccurrenceMatrixJob = new JobModel(userScoreMarixOutpath, itermOccurrenceOutpath, conf, null, "calculate co-occurrence matrix of the iterm",
            Recommend.class, ItermOccurrenceMapper.class, Text.class, IntWritable.class, ItermOccurrenceReducer.class, Text.class, IntWritable.class,null);


    //run the jobs
    JobDriver.initJob(new JobModel[]{itermOccurrenceMatrixJob});
  }

}
