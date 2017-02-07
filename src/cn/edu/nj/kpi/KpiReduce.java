package cn.edu.nj.kpi;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;


/**
 * Created by apple on 17/2/4.
 */
public class KpiReduce extends Reducer<Text, KpiBean, Text, KpiBean> {
    @Override
    protected void reduce(Text key, Iterable<KpiBean> values, Context context) throws IOException, InterruptedException {
        //use setmap to count
        int count=0;
        String getPage="";
        for(KpiBean bean : values){
            count++;
            getPage+=" "+bean.getRequest_page();
        }
        context.write(key,new KpiBean(count,getPage));
    }
}
