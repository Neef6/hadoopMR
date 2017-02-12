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
    //out result:
    //1	103:2.5,101:5,102:3
    //2	101:2,102:2.5,103:5,104:2
    //3	107:5,101:2,104:4,105:4.5
    //4	103:3,106:4,104:4.5,101:5
    //5	101:4,102:3,103:2,104:4,105:3.5,106:4
    //6	102:4,103:2,105:3.5,107:4
}
