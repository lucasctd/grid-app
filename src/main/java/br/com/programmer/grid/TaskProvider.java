package br.com.programmer.grid;

import java.io.Serializable;
import java.util.List;

/**
 * Created by lucas on 6/4/2017.
 */
public interface TaskProvider<T> extends Serializable{
    void run();
    void setList(List<T> list);
}
