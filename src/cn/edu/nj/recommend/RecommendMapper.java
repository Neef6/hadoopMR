package cn.edu.nj.recommend;

import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import javax.swing.*;
import java.io.*;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by apple on 17/2/8.
 */
public class RecommendMapper extends Mapper<LongWritable, Text, Text, DoubleWritable> {
  //the second map storage is the co-occurrence degree
  Map<String, Map<String, Double>> collecItermOccuMap = new HashMap<String, Map<String, Double>>();

  /**
   * init itermOccurrenceMatrix
   */
  @Override
  protected void setup(Context context) throws IOException, InterruptedException {
    super.setup(context);
    String path = "/Users/apple/soft/gitHub/hadoopMR/out/itermOccurrenceMatrix/part-r-00000";
    File itermOccurrenceMatrix = new File(path);
    FileReader fileReader = new FileReader(itermOccurrenceMatrix);
    BufferedReader bufferedReader = new BufferedReader(fileReader);
    String s;
    //读取文件的每一行
    while ((s = bufferedReader.readLine()) != null) {
      String[] strArr = s.split("\t");
      String[] itermIds = strArr[0].split(":");
      String itermId1 = itermIds[0];
      String itermId2 = itermIds[1];
      Double perference = Double.parseDouble(strArr[1]);
      Map<String, Double> colItermMap;
      if (!collecItermOccuMap.containsKey(itermId1)) {
        colItermMap = new HashMap<String, Double>();
      } else {
        colItermMap = collecItermOccuMap.get(itermId1);
      }
      colItermMap.put(itermId2, perference);
      collecItermOccuMap.put(itermId1, colItermMap);
    }
    bufferedReader.close();
    fileReader.close();
  }


  @Override
  protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
    String[] strArr = value.toString().split("\t");
    String[] firstArr = strArr[0].split(":");
    String userId = firstArr[0];

    for (Map.Entry<String, Map<String, Double>> row : collecItermOccuMap.entrySet()) {
      String targetItermId = row.getKey();
      //the user has been seen the item if the score is scored，then pass
      if (value.toString().contains(targetItermId)) {
        continue;
      }
      //calculate the total score
      Double totalScore = 0.0;
      Map<String, Double> cloIterMap = row.getValue();
      String[] list = strArr[1].split(",");
      for (int i = 0; i < list.length; i++) {
        String[] iterPer = list[i].split(":");
        String itermId2 = iterPer[0];
        Double perference = Double.parseDouble(iterPer[1]);
        Double occurrence = 0.0;
        // if occurenceMatrix doesn't have the item，means they don't have a bit similar
        if (cloIterMap.get(itermId2) != null) {
          occurrence = cloIterMap.get(itermId2);
        }
        Double score = perference * occurrence;
        totalScore += score;
      }
      String strr = userId + ":" + targetItermId;
      context.write(new Text(strr), new DoubleWritable(totalScore));
    }
  }
}
