package hadoop; 

import helper.FileHelper;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.StringTokenizer;

import optimalSubsetSelect.SelectSubSet;

import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.FileInputFormat;
import org.apache.hadoop.mapred.FileOutputFormat;
import org.apache.hadoop.mapred.JobClient;
import org.apache.hadoop.mapred.JobConf;
import org.apache.hadoop.mapred.MapReduceBase;
import org.apache.hadoop.mapred.Mapper;
import org.apache.hadoop.mapred.OutputCollector;
import org.apache.hadoop.mapred.Reducer;
import org.apache.hadoop.mapred.Reporter;
import org.apache.hadoop.mapred.TextInputFormat;
import org.apache.hadoop.mapred.TextOutputFormat;

import configuration.BuildConfig;
import core.Comp;

public class EnzymeMapReducer 
{ 
	public static final String GXL_BASE_LOCATION = "/home/venkatvb/project/GraphDatabases/gxl/";
	
   //Mapper class 
   public static class EnzymeMapper extends MapReduceBase implements 
   Mapper<LongWritable ,/*Input key Type */ 
   Text,                /*Input value Type*/ 
   Text,                /*Output key Type*/ 
   IntWritable>        /*Output value Type*/ 
   { 
      
      //Map function 
      public void map(LongWritable key, Text value, 
      OutputCollector<Text, IntWritable> output,   
      Reporter reporter) throws IOException 
      { 
         String line = value.toString(); 
         System.out.println("Current line : " + line );
         String lasttoken = null; 
         StringTokenizer s = new StringTokenizer(line," "); 
         String fileName = s.nextToken(); 
         
         while(s.hasMoreTokens())
            {
               lasttoken=s.nextToken();
            } 
            
         int fileId = Integer.parseInt(lasttoken); 
         System.out.println("FileName : " + fileName + ", FileId : " + fileId);
         output.collect(new Text(fileName), new IntWritable(fileId)); 
      } 
   } 
   
   
   //Reducer class 
   public static class EnzymeReducer extends MapReduceBase implements 
   Reducer< Text, IntWritable, Text, IntWritable > 
   {  
   
      //Reduce function 
      public void reduce( Text key, Iterator <IntWritable> values, 
         OutputCollector<Text, IntWritable> output, Reporter reporter) throws IOException 
         { 
    	  	int treshold = -1;
            int val=Integer.MIN_VALUE; 
            Comp comp = new Comp();
            
            while (values.hasNext()) 
            { 
            	int fileId = values.next().get();
            	System.out.println("Comparing the graphs => " + BuildConfig.getQueryEnzymeId() + ", " + fileId);
            	int currentTreshold = comp.compareGraphsUsingId(BuildConfig.getQueryEnzymeId(), fileId); 
            	System.out.println("RES : " + currentTreshold);
            	System.out.println("DONE");
               if(currentTreshold > treshold ) 
               { 
                  output.collect(key, new IntWritable(currentTreshold)); 
               } 
            } 
 
         } 
   }  
   
   
   public static void main(String args[]) throws Exception
   { 
		String queryLocation = GXL_BASE_LOCATION + "enzyme_" + args[0] + ".gxl";
		SelectSubSet optimalSet = new SelectSubSet();
		ArrayList<Integer> res = optimalSet.selectObtimalSubSet(queryLocation);
		PrintWriter pw = new PrintWriter(new FileOutputStream(new File("/home/venkatvb/Desktop/optimalSubSet.txt")));
		for(int i : res) {
			System.out.println("Writing " + "enzyme_" + i + ".gxl " + i);
			pw.println("enzyme_" + i + ".gxl " + i);
		}
	   pw.close();
	   Process p = Runtime.getRuntime().exec("/usr/local/hadoop/bin/hadoop fs -put /home/venkatvb/Desktop/optimalSubSet.txt input_dir");
      
	   JobConf conf = new JobConf(ProcessUnits.class); 
      
      conf.setJobName("EnzymeMapperJob"); 
      conf.setOutputKeyClass(Text.class);
      conf.setOutputValueClass(IntWritable.class); 
      conf.setMapperClass(EnzymeMapper.class); 
      conf.setCombinerClass(EnzymeReducer.class); 
      conf.setReducerClass(EnzymeReducer.class); 
      conf.setInputFormat(TextInputFormat.class); 
      conf.setOutputFormat(TextOutputFormat.class); 
      
      FileInputFormat.setInputPaths(conf, new Path(args[1])); 
      FileOutputFormat.setOutputPath(conf, new Path(args[2])); 
      
      JobClient.runJob(conf); 
   } 
} 
