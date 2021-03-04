
package com.fantasybaby.algorithm.easy.test;

import com.fantasybaby.algorithm.easy.SumSolution;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**sum solution test
 * @author reid.liu
 * @date 2019-01-11 14:48
 */
public class TestSumSolution {
    SumSolution sumSolution;
    @Before
    public void init(){
        sumSolution = new SumSolution();
    }

    @Test
    public void testSum(){
        int sum = sumSolution.sum(1, 5);
        Assert.assertEquals(6,sum);

    }

}
