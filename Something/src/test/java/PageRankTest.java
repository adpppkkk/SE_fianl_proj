
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.*;
import org.junit.Test;
import org.junit.Rule;

import org.junit.runner.Description;

import org.junit.rules.TestRule;
import org.junit.rules.TestWatcher;

import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;

import java.util.ArrayList;

/**
 * Unit test for simple App.
 */

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class pageRankTest 
{   

    @Rule
    public TestRule watcher =
    new TestWatcher() {
    protected void starting(Description description) {
        System.out.println("Starting test: " + description.getMethodName());
        }
    };


    PageRank pr;
    
    @Before
    public void initialize() {
    pr = new PageRank();
    }


}













    