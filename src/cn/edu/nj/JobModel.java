package cn.edu.nj;


import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.mapreduce.InputFormat;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;

/**
 * Created by apple on 17/2/9.
 */
public class JobModel {
  private Path inputPaths;
  private Path outPutPath;
  private Configuration conf;
  private Job job;
  private String jobName;
  private Class<?> jarClass;//mr drive class
  private Class<? extends Mapper> mapper;
  private Class<?> mapOutKeyClass;
  private Class<?> mapOutValueClass;
  private Class<? extends Reducer> reducer;
  private Class<?> reduceOutKeyClass;
  private Class<?> reduceOutValueClass;
  private Class<? extends InputFormat> inputFormatClass;


  public JobModel(Path inputPaths, Path outPutPath, Configuration conf, Job job, String jobName, Class<?> jarClass, Class<? extends Mapper> mapper, Class<?> mapOutKeyClass, Class<?> mapOutValueClass, Class<? extends Reducer> reducer, Class<?> reduceOutKeyClass, Class<?> reduceOutValueClass, Class<? extends InputFormat> inputFormatClass) {
    this.inputPaths = inputPaths;
    this.outPutPath = outPutPath;
    this.conf = conf;
    this.job = job;
    this.jobName = jobName;
    this.jarClass = jarClass;
    this.mapper = mapper;
    this.mapOutKeyClass = mapOutKeyClass;
    this.mapOutValueClass = mapOutValueClass;
    this.reducer = reducer;
    this.reduceOutKeyClass = reduceOutKeyClass;
    this.reduceOutValueClass = reduceOutValueClass;
    this.inputFormatClass = inputFormatClass;
  }

  public Path getInputPaths() {
    return inputPaths;
  }

  public void setInputPaths(Path inputPaths) {
    this.inputPaths = inputPaths;
  }

  public Path getOutPutPath() {
    return outPutPath;
  }

  public void setOutPutPath(Path outPutPath) {
    this.outPutPath = outPutPath;
  }

  public Configuration getConf() {
    return conf;
  }

  public void setConf(Configuration conf) {
    this.conf = conf;
  }

  public Job getJob() {
    return job;
  }

  public void setJob(Job job) {
    this.job = job;
  }

  public String getJobName() {
    return jobName;
  }

  public void setJobName(String jobName) {
    this.jobName = jobName;
  }

  public Class<?> getJarClass() {
    return jarClass;
  }

  public void setJarClass(Class<?> jarClass) {
    this.jarClass = jarClass;
  }

  public Class<? extends Mapper> getMapper() {
    return mapper;
  }

  public void setMapper(Class<? extends Mapper> mapper) {
    this.mapper = mapper;
  }

  public Class<?> getMapOutKeyClass() {
    return mapOutKeyClass;
  }

  public void setMapOutKeyClass(Class<?> mapOutKeyClass) {
    this.mapOutKeyClass = mapOutKeyClass;
  }

  public Class<?> getMapOutValueClass() {
    return mapOutValueClass;
  }

  public void setMapOutValueClass(Class<?> mapOutValueClass) {
    this.mapOutValueClass = mapOutValueClass;
  }

  public Class<? extends Reducer> getReducer() {
    return reducer;
  }

  public void setReducer(Class<? extends Reducer> reducer) {
    this.reducer = reducer;
  }

  public Class<?> getReduceOutKeyClass() {
    return reduceOutKeyClass;
  }

  public void setReduceOutKeyClass(Class<?> reduceOutKeyClass) {
    this.reduceOutKeyClass = reduceOutKeyClass;
  }

  public Class<?> getReduceOutValueClass() {
    return reduceOutValueClass;
  }

  public void setReduceOutValueClass(Class<?> reduceOutValueClass) {
    this.reduceOutValueClass = reduceOutValueClass;
  }

  public Class<? extends InputFormat> getInputFormatClass() {
    return inputFormatClass;
  }

  public void setInputFormatClass(Class<? extends InputFormat> inputFormatClass) {
    this.inputFormatClass = inputFormatClass;
  }
}
