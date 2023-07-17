package mapreduce;

import com.hazelcast.mapreduce.Reducer;

public class WordCountReducer extends Reducer<Integer, Integer> {

    int sum = 0;
    @Override
    public void reduce(Integer integer) {
        sum += integer;
    }

    @Override
    public Integer finalizeReduce() {
        return sum;
    }
}
