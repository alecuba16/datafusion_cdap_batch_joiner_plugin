package com.alecuba16.cdap;

import com.google.gson.Gson;
import io.cdap.cdap.api.annotation.Description;
import io.cdap.cdap.api.annotation.Macro;
import io.cdap.cdap.api.annotation.Name;
import io.cdap.cdap.api.annotation.Plugin;
import io.cdap.cdap.api.data.format.StructuredRecord;
import io.cdap.cdap.api.data.schema.Schema;
import io.cdap.cdap.api.messaging.MessagePublisher;
import io.cdap.cdap.api.messaging.TopicAlreadyExistsException;
import io.cdap.cdap.api.messaging.TopicNotFoundException;
import io.cdap.cdap.api.plugin.PluginConfig;
import io.cdap.cdap.etl.api.*;
import io.cdap.cdap.etl.api.batch.BatchAggregator;
import io.cdap.cdap.etl.api.batch.BatchJoiner;
import io.cdap.cdap.etl.api.batch.BatchJoinerContext;
import io.cdap.cdap.etl.api.batch.BatchJoinerRuntimeContext;

/**
 * Batch Aggregator Plugin
 * A BatchAggregator plugin is used to compute aggregates over a batch of data. It is used in both batch and real-time
 * data pipelines. An aggregation takes place in two steps: groupBy and then aggregate. In the groupBy step, the
 * aggregator creates zero or more group keys for each input record. Before the aggregate step occurs, the CDAP pipeline
 * will take all records that have the same group key, and collect them into a group. If a record does not have any of
 * the group keys, it is filtered out. If a record has multiple group keys, it will belong to multiple groups.
 *
 * The aggregate step is then called. In this step, the plugin receives group keys and all records that had that group
 * key. It is then left to the plugin to decide what to do with each of the groups. In order to implement a Batch
 * Aggregator, you extend the BatchAggregator class. Unlike a Transform, which operates on a single record at a time,
 * a BatchAggregator operates on a collection of records.
 * Methods
 * configurePipeline(): Used to perform any validation on the application configuration that is required by this plugin
 * or to create any datasets if the fieldName for a dataset is not a macro.
 *
 * initialize(): Initialize the Batch Aggregator. Guaranteed to be executed before any call to the plugin’s groupBy or
 * aggregate methods. This is called by each executor of the job. For example, if the MapReduce engine is being used,
 * each mapper will call this method.
 *
 * destroy(): Destroy any resources created by initialize. Guaranteed to be executed after all calls to the plugin’s
 * groupBy or aggregate methods have been made. This is called by each executor of the job. For example, if the
 * MapReduce engine is being used, each mapper will call this method.
 *
 * groupBy(): This method will be called for every object that is received from the previous stage. This method returns
 * zero or more group keys for each object it receives. Objects with the same group key will be grouped together for
 * the aggregate method.
 * aggregate(): The method is called after every object has been assigned their group keys. This method is called once
 * for each group key emitted by the groupBy method. The method receives a group key as well as an iterator over all
 * objects that had that group key. Objects emitted in this method are the output for this stage.
 /**
 * Aggregator that counts how many times each word appears in records input to the aggregator.
 */
@Plugin(type = BatchJoiner.PLUGIN_TYPE)
@Name(BatchJoinerBoilerPlate.NAME)
@Description("Joiner boilerplate.")
public class BatchJoinerBoilerPlate extends BatchJoiner<String, StructuredRecord, StructuredRecord> {
    public static final String NAME = "WordJoiner";
    /**
     * Configure the pipeline. This is run once when the pipeline is being published.
     * This is where you perform any static logic, like creating required datasets, performing schema validation,
     * setting output schema, and things of that nature.
     *
     * @param multiInputPipelineConfigurer the configurer used to add required datasets and streams
     */
    @Override
    public void configurePipeline(MultiInputPipelineConfigurer multiInputPipelineConfigurer) {
        // no-op
    }

    /**
     * Prepare a pipeline run. This is run every time before a pipeline runs in order to help set up the run.
     * This is where you would set things like the number of partitions to use when joining, and setting the
     * join key class if they are not known at compile time.
     *
     * @param context batch execution context
     * @throws Exception
     */
    @Override
    public void prepareRun(BatchJoinerContext context) throws Exception {
        //no-op
    }

    /**
     * Initialize the Batch Joiner. Executed inside the Batch Run. This method is guaranteed to be invoked
     * before any calls to #joinOn(String, Object) and merge(Object, Iterable) are made.
     *
     * @param context runtime context for joiner which exposes input schemas and output schema for joiner
     * @throws Exception if there is any error during initialization
     */
    @Override
    public void initialize(BatchJoinerRuntimeContext context) throws Exception {
        //no-op
    }

    @Override
    public void destroy() {
        //no-op
    }

    @Override
    public String joinOn(String s, StructuredRecord structuredRecord) throws Exception {
        return null;
    }

    @Override
    public JoinConfig getJoinConfig() throws Exception {
        return null;
    }

    @Override
    public StructuredRecord merge(String s, Iterable<JoinElement<StructuredRecord>> iterable) throws Exception {
        return null;
    }
}