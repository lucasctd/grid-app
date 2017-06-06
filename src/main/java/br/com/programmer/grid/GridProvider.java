package br.com.programmer.grid;

import java.io.Serializable;

/**
 * Created by lucas on 6/4/2017.
 */
public interface GridProvider <T> extends Serializable{

    void run();
    void setList(Iterable<T> list);
    void setBlockSize(int blockSize);
}
