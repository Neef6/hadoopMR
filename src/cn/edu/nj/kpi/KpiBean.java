package cn.edu.nj.kpi;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Writable;
import org.apache.hadoop.io.WritableComparable;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

/**
 * Created by apple on 17/2/4.
 */
public class KpiBean implements Writable {

    //Concludes that the record format is correct
    private boolean flag = true;
    private String ipAddr;//ip address
    private String request_page;
    private String respond_status;//status code
    private int count;

    //reflection need the empty construction
    public KpiBean() {
    }


    public KpiBean(boolean flag, String ipAddr, String request_page, String respond_status, int count) {
        this.flag = flag;
        this.ipAddr = ipAddr;
        this.request_page = request_page;
        this.respond_status = respond_status;
        this.count = count;
    }

    public KpiBean(int count, String request_page) {
        this.count = count;
        this.request_page = request_page;

    }

    /*
       *  check the data
       *  @param line  a line data
        */
    public static KpiBean parse(String line) {
        String[] arr = line.split(" ");
        KpiBean kpiBean = new KpiBean();
        // the complete data including 23 param
        if (arr.length > 22) {
            kpiBean.setIpAddr(arr[0]);
            kpiBean.setRequest_page(arr[6]);
            kpiBean.setRespond_status(arr[8]);
            //filter the error code
            if (!kpiBean.getRespond_status().equals("") && Integer.parseInt(kpiBean.getRespond_status()) > 400) {
                kpiBean.setFlag(false);
            }
        } else {
            kpiBean.setFlag(false);
        }
        return kpiBean;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        // \n means a new line
        sb.append("\nvisit count：").append(this.count);
        sb.append("\nrequest_page:").append(this.request_page);
        return sb.toString();
    }

    //serialization of data to a stream
    @Override
    public void write(DataOutput dataOutput) throws IOException {
        dataOutput.writeBoolean(flag);
        dataOutput.writeUTF(ipAddr);
        dataOutput.writeUTF(request_page);
        dataOutput.writeUTF(respond_status);

    }


    //deserialization the data
    @Override
    public void readFields(DataInput dataInput) throws IOException {
        flag = dataInput.readBoolean();
        ipAddr = dataInput.readUTF();
        request_page = dataInput.readUTF();
        respond_status = dataInput.readUTF();

    }

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    public String getIpAddr() {
        return ipAddr;
    }

    public void setIpAddr(String ipAddr) {
        this.ipAddr = ipAddr;
    }


    public String getRespond_status() {
        return respond_status;
    }

    public void setRespond_status(String respond_status) {
        this.respond_status = respond_status;
    }

    public String getRequest_page() {
        return request_page;
    }

    public void setRequest_page(String request_page) {
        this.request_page = request_page;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }


}
