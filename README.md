# datafusion_cdap_batch_joiner_plugin
Complete example project to create a custom Google cloud datafusion (CDAP) batch joiner plugin. Sourced and adapted from the documentation where there is no quickstart project.

##Batch Joiner Plugin
A BatchJoiner plugin is used to join records over a batch of data. It can be used in both batch and real-time data pipelines. A join takes place in two steps: a joinOn step followed by a merge step.

In the joinOn step, the joiner creates a join key for each input record. The CDAP pipeline will then take all records that have the same join key and collect them into a group.

The merge step is then called. In this step, the plugin receives a list of all the records with same join key based on the type of join (either an inner or outer join). It is then up to the plugin to decide what to emit, in what becomes the final output of the stage.

To implement a Batch Joiner, you extend the BatchJoiner class. Unlike a Transform, which operates on a single record at a time, a BatchJoiner operates on a collection of records.

## Methods
### configurePipeline():
Used to create any datasets, or perform any validation on the application configuration that is required by this plugin.
### initialize():
Initialize the Batch Joiner. Guaranteed to be executed before any call to the plugin’s joinOn or merge methods. This is called by each executor of the job. For example, if the MapReduce engine is being used, each mapper will call this method.
### prepareRun():
Prepares a pipeline run. This is run every time before a pipeline runs to help set up the run. Here you can set properties such as the number of partitions to use when joining and the join key class, if it is not known at compile time.
### destroy():
Destroy any resources created by the initialize method. Guaranteed to be executed after all calls to the plugin’s joinOn or merge methods have been made. This is called by each executor of the job. For example, if the MapReduce engine is being used, each mapper will call this method.
### joinOn():
This method will be called for every object that is received from the previous stage. This method returns a join key for each object it receives. Objects with the same join key will be grouped together for the merge method.
### getJoinConfig():
This method will be called by the CDAP Pipeline to find out the type of join to be performed. The config specifies which input stages are requiredInputs. Records from a required input will always be present in the merge() method. Records from a non-required input will only be present in the merge() method if they meet the join criteria. In other words, if there are no required inputs, a full outer join is performed. If all inputs are required inputs, an inner join is performed.
### merge():
This method is called after each object has been assigned a join key. The method receives a join key, an iterator over all objects with that join key, and the stage that emitted the object. Objects emitted by this method are the output for this stage.object has been assigned their group keys. This method is called once for each group key emitted by the groupBy method. The method receives a group key as well as an iterator over all objects that had that group key. Objects emitted in this method are the output for this stage.