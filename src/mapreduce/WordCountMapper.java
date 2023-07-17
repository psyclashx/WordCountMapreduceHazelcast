package mapreduce;

import com.hazelcast.mapreduce.Context;
import com.hazelcast.mapreduce.Mapper;
import entity.Buch;

import java.util.LinkedList;
import java.util.List;

public class WordCountMapper implements Mapper<Long, Buch, String, Integer> {

    private List<String> words = new LinkedList<>();
    public void map(Long id, Buch buch, Context<String, Integer> ctx) {

        String[] stringParts = buch.getText().split(" ");

        for(String word : stringParts) {
            ctx.emit(word, 1);
        }


    }
}
