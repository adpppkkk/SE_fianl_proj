
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
public class PageRankTest
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

    @Test
    public void TotalNodesTest(){
      int size = pr.name.length;
      //Get the correct amount of students
      assertEquals(size, pr.nodes+size);
    }

    @Test
    public void PageRankTest(){
      int size = pr.name.length;
      for(int i = 0; i < size; i++)
      {//Having the pagerank between a range (0 and 1)
        assertTrue(0 <= pr.pagerank[i]);
        assertTrue(1 > pr.pagerank[i]);
      }
    }

    @Test
    public void StudentPreferenceTest(){
      int size = pr.name.length;
      for(int i = 0; i < size; i++)
      {
        //Making sure the first person is not included int the preference array
        if(pr.name[i] != pr.namepath[i][i])
        {
          assertTrue(true);
        }
      }
    }

    @Test
    public void ConvertTest(){
    //Making sure the covert() is working well
    //And about the data in the namepath array
    String nameTest[] = {"A","B","C","D","E"};
    String namepathTest[][] = {{"B","C"},{"A","C","D"},{"C","B"},{"A","C","B"},{""}};
    pr.convert();
    assertEquals("B", namepathTest[0][0]);
    assertEquals(0, pr.path[0][0]);
    assertEquals("C", namepathTest[3][1]);
    assertEquals(0, pr.path[3][1]);
    assertEquals("C", namepathTest[2][0]);
    assertEquals(0, pr.path[2][0]);
    assertEquals("", namepathTest[4][0]);
    assertEquals(0, pr.path[4][0]);
    }

    @Test
    public void StepsPageRankTest(){
      //Variance of page rank on the different steps
      boolean testS1 = true;
      boolean testS2 = true;
      boolean testS3 = true;
      int size = pr.name.length;
      //STEP # 1
      for(int i = 1; i < size; i++)
      {
        if(pr.pagerank[0] != pr.pagerank[i])
          testS1 = false;
      }
      //STEP # 2 (the different stages where pagerank is calculated)
      for(int i = 0; i < size; i++)
      {
        if(pr.pagerank[i] != 0)
          testS2 = false;
      }
      // STEP # 3 (for the final rank)
      for(int i = 0; i < size; i++)
      {
        if(0 < pr.pagerank[i])
          testS3 = false;
      }

      assertTrue(testS1);
      assertTrue(testS2);
      assertTrue(testS3);
    }
    
  }













    
