package cn.edu.nj.recommend;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

/**
 * Created by apple on 17/2/8.
 */
public class UserScoreMatrixReducer extends Reducer<Text,Text,Text,Text> {
    @Override
    protected void reduce(Text key, Iterable<Text> values, Context context) throws IOException, InterruptedException {
        String itermAndPers="";
        for(Text itP : values){
            itermAndPers+=","+itP.toString();
        }
        context.write(key,new Text(itermAndPers.replaceFirst(",","")));
    }
}
