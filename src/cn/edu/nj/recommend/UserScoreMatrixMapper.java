package cn.edu.nj.recommend;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

/**
 * Created by apple on 17/2/8.
 * key: UserId  value ItermId:perference
 */
public class UserScoreMatrixMapper extends Mapper<LongWritable, Text, Text, Text> {


    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        String[] str = value.toString().split(" ");
        String userId = str[0];
        String itermAndPer = str[1] + ":" + str[2];
        context.write(new Text(userId),new Text(itermAndPer));
    }
    // 1 103:2.5
}
