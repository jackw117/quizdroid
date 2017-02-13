package edu.washington.jackw117.quizdroid3;

import java.util.List;

/**
 * Created by Jack on 2/12/2017.
 */

public interface TopicRepository<Topic> {
    void add(Topic item);

    List<Topic> query();
}
