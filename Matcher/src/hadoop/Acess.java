package hadoop;

import java.net.URI;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;

//code begins

/**
* @author venkatvb
*
*/
public class Acess {

public void run() throws Exception {
	System.out.println("Trying to connect Hadoop.");
  Configuration conf = new Configuration();
  
  conf.addResource(new Path("src/test/resources/HadoopConfs/core-site.xml"));
  conf.addResource(new Path("src/test/resources/HadoopConfs/hdfs-site.xml"));



  String dirName = "hdfs://hdnode01:50070/user/hduser/createdNow";

  //values of hosthdfs:port can be found in the core-site.xml  in the fs.default.name

  FileSystem fileSystem = FileSystem.get(conf);
  FileSystem fs = FileSystem.get(new URI(dirName), conf);

  Path path = new Path(dirName);
  if (fileSystem.exists(path)) {
      System.out.println("Dir " + dirName + " already exists");
      return;
  }
  
  // Create directories
  fileSystem.mkdirs(path);

  fileSystem.close();
  System.out.println("Directories created.");
}
}