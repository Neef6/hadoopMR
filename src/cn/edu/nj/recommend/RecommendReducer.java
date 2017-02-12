package cn.edu.nj.recommend;

import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

/**
 * Created by apple on 17/2/8.
 */
public class RecommendReducer extends Reducer<Text,DoubleWritable,Text,Text> {
  @Override
  protected void reduce(Text key, Iterable<DoubleWritable> values, Context context) throws IOException, InterruptedException {
    Double totalScore = 0.0;
    for(DoubleWritable v:values){
      totalScore +=v.get();
    }
    String[] strArr= key.toString().split(":");
    context.write(new Text(strArr[0]),new Text(strArr[1]+":"+totalScore));
  }
}
