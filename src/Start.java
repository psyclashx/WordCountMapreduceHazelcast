import com.hazelcast.config.JobTrackerConfig;
import com.hazelcast.core.ExecutionCallback;
import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.IMap;
import com.hazelcast.mapreduce.Job;
import com.hazelcast.mapreduce.JobCompletableFuture;
import com.hazelcast.mapreduce.JobTracker;
import com.hazelcast.mapreduce.KeyValueSource;
import entity.Buch;
import mapreduce.WordCountMapper;
import mapreduce.WordCountReducerFactory;

import java.util.Map;
import java.util.concurrent.ExecutionException;

public class Start {

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        HazelcastInstance hazelcastInstance = Hazelcast.newHazelcastInstance();

        IMap<Long, Buch> wordCountMap = hazelcastInstance.getMap("WordDB");

        wordCountMap.put(1L, new Buch(1, "Harry Potter 1", "Ich bin die erste Ausgabe von Harry Potter!"));
        wordCountMap.put(2L, new Buch(2, "Harry Potter 2", "Jetzt handelt es sich um Harry Potter 2"));
        wordCountMap.put(3L, new Buch(3, "Harry Potter 3", "Aufeinmal bin ich HP 3"));
        wordCountMap.put(4L, new Buch(4, "Harry Potter 4", "Tja, jetzt bin ich die nummer 4"));

        JobTracker jobTracker = hazelcastInstance.getJobTracker("myTracker");
        KeyValueSource<Long, Buch> keyValueSource = KeyValueSource.fromMap(wordCountMap);
        Job<Long, Buch> job = jobTracker.newJob(keyValueSource);

        JobCompletableFuture<Map<String, Integer>> result =
                job
                        .mapper(new WordCountMapper())
                        .reducer(new WordCountReducerFactory())
                        .submit();

        Map<String, Integer> resultMap = result.get();

        System.out.println(resultMap);

    }
}
