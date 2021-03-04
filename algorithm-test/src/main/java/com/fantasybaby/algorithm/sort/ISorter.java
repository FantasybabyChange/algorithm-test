
package com.fantasybaby.algorithm.sort;

import java.util.List;

/**
 * @author reid.liu
 * @date 2019-02-28 13:42
 */
public interface ISorter<T> {
    /**
     * 升序
     * @param field
     * @return
     */
    default  List<T> sortDesc(String field){
        return null;
    }

    /**
     * 降序
     * @param field
     * @return
     */
    default  List<T> sortAsc(String field){return null;}

    List<T> sort(String field);
    List<T> reverseSort(String field);
}
