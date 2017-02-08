package cn.edu.nj.kpi.Paritition;

/**
 * Created by apple on 17/2/7.
 */
import org.apache.hadoop.mapreduce.Partitioner;

import java.util.HashMap;

public class KpiParitition<KEY,VALUE> extends Partitioner<KEY,VALUE> {

    private static HashMap<String,Integer> partitionMap= new HashMap<String, Integer>();

    static{
        partitionMap.put("",0);
        partitionMap.put("",1);
        partitionMap.put("",2);
        partitionMap.put("",3);
    }
    @Override
    public int getPartition(KEY key, VALUE value, int i) {
        int partitionNum=partitionMap.get(key.toString().substring(0,3)==null?5:key.toString().substring(0,3));
        return partitionNum;
    }
}
