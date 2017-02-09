package cn.edu.nj.recommend;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

/**
 * Created by apple on 17/2/9.
 */
public class ItermOccurrenceMapper extends Mapper<LongWritable, Text, Text, IntWritable> {
  Text itermThan = new Text();

  @Override
  protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
    //value :1	103:2.5,101:5,102:3
    String[] str = value.toString().split("\t");
    //提取一行全排列
    for (int i = 1; i < str.length; i++) {
      String itermId1 = str[i].split(":")[0];
      for (int j = 1; j < str.length; j++) {
        String itermId2 = str[i].split("，")[0];
        itermThan.set(itermId1 + ":" + itermId2);
        context.write(itermThan, new IntWritable(1));
      }
    }
  }
}
