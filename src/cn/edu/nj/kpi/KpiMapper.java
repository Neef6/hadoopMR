package cn.edu.nj.kpi;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

/**
 * Created by apple on 17/2/4.
 */
public class KpiMapper extends Mapper<LongWritable, Text, Text, KpiBean> {
    KpiBean kpiBean = new KpiBean();
    @Override
    public void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        kpiBean = kpiBean.parse(value.toString());
        if (kpiBean.isFlag()) {
            String ipAddr=kpiBean.getIpAddr();
            context.write(new Text(ipAddr), kpiBean);
        }
    }
}
