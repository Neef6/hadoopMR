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
    //out result:
    //1	103:2.5,101:5,102:3
    //2	101:2,102:2.5,103:5,104:2
    //3	107:5,101:2,104:4,105:4.5
    //4	103:3,106:4,104:4.5,101:5
    //5	101:4,102:3,103:2,104:4,105:3.5,106:4
    //6	102:4,103:2,105:3.5,107:4
}
