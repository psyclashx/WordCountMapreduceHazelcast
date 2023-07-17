package mapreduce;

import com.hazelcast.mapreduce.Reducer;
import com.hazelcast.mapreduce.ReducerFactory;

public class WordCountReducerFactory implements ReducerFactory<String, Integer, Integer> {

    public Reducer<Integer, Integer> newReducer(String keyIn) {
        return new WordCountReducer();
    }
}
