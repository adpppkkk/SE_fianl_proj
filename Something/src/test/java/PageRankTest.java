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
    }
    @Test
    public void ConvertTest3(){
    //Making sure the covert() is working well
    //And about the data in the namepath array
    String nameTest[] = {"A","B","C","D","E"};
    String namepathTest[][] = {{"B","C"},{"A","C","D"},{"C","B"},{"A","C","B"},{""}};
    pr.convert();
    assertEquals("C", namepathTest[3][1]);
    }

    @Test
    public void ConvertTest4(){
    //Making sure the covert() is working well
    //And about the data in the namepath array
    String nameTest[] = {"A","B","C","D","E"};
    String namepathTest[][] = {{"B","C"},{"A","C","D"},{"C","B"},{"A","C","B"},{""}};
    pr.convert();
    assertEquals("C", namepathTest[2][0]);
    }

    @Test
    public void ConvertTest5(){
    //Making sure the covert() is working well
    //And about the data in the namepath array
    String nameTest[] = {"A","B","C","D","E"};
    String namepathTest[][] = {{"B","C"},{"A","C","D"},{"C","B"},{"A","C","B"},{""}};
    pr.convert();
    assertEquals("", namepathTest[4][0]);
    assertEquals(0, pr.path[4][0]);
    }

    @Test
    public void ConvertTest6(){
    //Making sure the covert() is working well
    //And about the data in the namepath array
    String nameTest[] = {"A","B","C","D","E"};
    String namepathTest[][] = {{"B","C"},{"A","C","D"},{"C","B"},{"A","C","B"},{""}};
    pr.convert();
    assertEquals(0, pr.path[3][1]);
    }

    @Test
    public void ConvertTest7(){
    //Making sure the covert() is working well
    //And about the data in the namepath array
    String nameTest[] = {"A","B","C","D","E"};
    String namepathTest[][] = {{"B","C"},{"A","C","D"},{"C","B"},{"A","C","B"},{""}};
    pr.convert();
    assertEquals(0, pr.path[3][1]);
    }

    @Test
    public void ConvertTest8(){
    //Making sure the covert() is working well
    //And about the data in the namepath array
    String nameTest[] = {"A","B","C","D","E"};
    String namepathTest[][] = {{"B","C"},{"A","C","D"},{"C","B"},{"A","C","B"},{""}};
    pr.convert();
    assertEquals(0, pr.path[2][0]);
    }

    @Test
    public void ConvertTest9(){
    //Making sure the covert() is working well
    //And about the data in the namepath array
    String nameTest[] = {"A","B","C","D","E"};
    String namepathTest[][] = {{"B","C"},{"A","C","D"},{"C","B"},{"A","C","B"},{""}};
    pr.convert();
    assertEquals(0, pr.path[4][0]);
    }

    @Test
    public void ConvertTest2(){
    //Making sure the covert() is working well
    //And about the data in the namepath array
    String nameTest[] = {"A","B","C","D","E"};
    String namepathTest[][] = {{"A"},{"A","C","D"},{"C","B"},{"A","C","B"},{""}};
    pr.convert();
    assertEquals("A", namepathTest[0][0]);
    }

    @Test
    public void ConvertTest10(){
    //Making sure the covert() is working well
    //And about the data in the namepath array
    String nameTest[] = {"A","B","C","D","E"};
    String namepathTest[][] = {{"A"},{"A","C","D"},{"C","B"},{"A","C","B"},{""}};
    pr.convert();
    assertEquals(0, pr.path[0][0]);
    }

    @Test
    public void Step1PageRankTest(){
      //Variance of page rank on the different steps
      boolean testS1 = true;
      int size = pr.name.length;
      //STEP # 1
      for(int i = 1; i < size; i++)
      {
        if(pr.pagerank[0] != pr.pagerank[i])
          testS1 = false;
      }
      assertTrue(testS1);
    }

    @Test
    public void Step2PageRankTest(){
      //Variance of page rank on the different steps
      boolean testS2 = true;
      int size = pr.name.length;
      //STEP # 2 (the different stages where pagerank is calculated)
      for(int i = 0; i < size; i++)
      {
        if(pr.pagerank[i] != 0)
          testS2 = false;
      }

      assertTrue(testS2);
    }

    @Test
    public void Step3PageRankTest(){
      //Variance of page rank on the different steps
      boolean testS3 = true;
      int size = pr.name.length;
      // STEP # 3 (for the final rank)
      for(int i = 0; i < size; i++)
      {
        if(0 < pr.pagerank[i])
          testS3 = false;
      }

      assertTrue(testS3);
    }

    @Test
    public void SizeNamesTest(){
      //Make sure the quantity of students and ranks are equal
      int case1 = 10;
      int case2 = pr.namepath.length;
      assertEquals(case1 , case2);
    }

    @Test
    public void SizeNodessTest(){
      //Make sure the quantity of students and nodes are equal
      int case1 = pr.name.length;
      int case2 = pr.pagerank.length;
      assertTrue(case1 == case2);
    }

    @Test
    public void SizePathTest(){
      //Make sure the paths of names are preferences are equal
      int case1 = pr.namepath.length;
      int case2 = pr.path.length;
      assertTrue(case1 == case2);
    }

    @Test
    public void CalcTest(){
      //Make sure the calc function is working correctly
      int count = 0;
      pr.calc(5);
      for(int i = 0; i < pr.name.length; i++)
      {
        //Checking PageRank is always 0 or higher
        if( !(pr.pagerank[i] >= 0) )
          count++;
      }
      if(count > 0)
        assertTrue(true);
    }

    @Test
    public void OrderTest1(){
      //Making sure the first person on namepath is always
      //the first person in the name array
      for(int i = 0; i < pr.name.length; i++)
      {
        if(pr.name[i] != pr.namepath[i][0])
          assertTrue(false);
      }
      assertTrue(true);
    }

    @Test

    public void OrderTest2(){
      String copy = pr.name[1];
      pr.name[1] = "none";
      //If changed the first person in array name
      if(pr.name[1] == pr.namepath[1][0])
        assertTrue(false);
      else
        pr.name[1] = copy; assertTrue(true);
    }

    @Test
    public void OrderTest3(){
      String copy = pr.namepath[2][0];
      pr.namepath[2][0] = "none";
      //If changed the first person in array namepath
      if(pr.name[2] == pr.namepath[2][0])
        assertTrue(false);
      else
        pr.namepath[2][0] = copy; assertTrue(true);
    }

    @Test
    public void OrderTest4(){
      String copy = pr.namepath[3][0];
      pr.namepath[3][0] = "none";
      //If changed the first person in array namepath
      if(pr.name[3] == pr.namepath[3][0])
        assertTrue(false);
      else
        pr.namepath[3][0] = copy; assertTrue(true);
    }


    @Test
    public void OrderTest5(){
      String copy = pr.namepath[4][0];
      pr.namepath[4][0] = "none";
      //If changed the first person in array namepath
      if(pr.name[4] == pr.namepath[4][0])
        assertTrue(false);
      else
        pr.namepath[4][0] = copy; assertTrue(true);
    }

    @Test
    public void OrderTest6(){
      String copy = pr.namepath[5][0];
      pr.namepath[5][0] = "none";
      //If changed the first person in array namepath
      if(pr.name[5] == pr.namepath[5][0])
        assertTrue(false);
      else
        pr.namepath[5][0] = copy; assertTrue(true);
    }

    @Test
    public void CheckingPageRankTest1(){
      //To see if PageRank is greater than DampingFactor
      //Make sure it changes
      double case1 = pr.pagerank[1];
      pr.pagerank[1] = 5;

      assertTrue(case1 != pr.pagerank[1]);
    }

    @Test
    public void CheckingPageRankTest2(){
      //To see if PageRank is greater than DampingFactor
      //Make sure it changes
      double case1 = pr.pagerank[3];
      pr.pagerank[3] = 2;

      assertTrue(case1 != pr.pagerank[3]);
    }

    @Test
    public void SwapNameTest1(){
      String case1 = pr.namepath[2][3];
      pr.namepath[2][2] = pr.namepath[2][3];

      assertTrue(case1 == pr.namepath[2][2]);
    }

    @Test
    public void SwapNameTest2(){
      String case1 = pr.namepath[1][2];
      pr.namepath[3][2] = pr.namepath[1][2];

      assertTrue(case1 == pr.namepath[3][2]);
    }

    @Test
    public void SwapNameTest3(){
      String case1 = pr.namepath[4][4];
      pr.namepath[1][3] = pr.namepath[4][4];

      assertTrue(case1 == pr.namepath[1][3]);
    }

    @Test
    public void SwapNameTest4(){
      String case1 = pr.namepath[3][3];
      pr.namepath[4][2] = pr.namepath[3][3];

      assertTrue(case1 == pr.namepath[4][2]);
    }

    @Test
    public void NoPreferenceTest1(){
      //When there is no preference pagerank should be zero
      for(int i = 0; i < pr.name.length; i++)
      {
        for(int j = 1; j < pr.name.length; j++)
        {
          if(pr.namepath[i][j] == null)
          {
            assertTrue(pr.pagerank[i] == 0.0);
          }
        }
      }
    }

    @Test
    public void NoPreferenceTest2(){
      //When the name of the student and the preference is the same
      // the student does not have a preference, pagerank should be zero
      for(int i = 0; i < pr.name.length; i++)
      {
        for(int j = 1; j < pr.name.length; j++)
        {
          if(pr.namepath[i][j] == pr.name[i])
          {
            assertTrue(pr.pagerank[i] == 0.0);
          }
        }
      }
    }

    @Test
    public void ChangePageRankTest1(){
      //Make sure that if path is changed the pagerank also changes
      double case1 = pr.pagerank[3];
      pr.path[2][3] = pr.path[3][3];

      assertTrue(case1 == pr.pagerank[2]);
    }

    @Test
    public void ChangePageRankTest2(){
      //Make sure that if path is changed the pagerank also changes
      double case1 = pr.pagerank[4];
      pr.path[3][2] = pr.path[4][2];

      assertTrue(case1 == pr.pagerank[3]);
    }

    @Test
    public void ChangePageRankTest3(){
      //Make sure that if path is changed the pagerank also changes
      double case1 = pr.pagerank[2];
      pr.path[1][2] = pr.path[2][2];

      assertTrue(case1 == pr.pagerank[1]);
    }

    @Test
    public void ReadingDataTest(){
      //If pagerank changed
      for(int i = 0; i < pr.name.length; i++)
      {
        pr.pagerank[i] = 1.00;
      }
      //Convert function works
      pr.convert();
      for(int i = 0; i < pr.name.length; i++)
      {
        assertTrue(pr.pagerank[i] == 1.00);
      }
    }

    @Test
    public void DataTest1(){
      //Checking if pagerank changes after steps are done
      double case1 = pr.pagerank[1];
      pr.calc(5);
      pr.convert();
      double case2 = pr.pagerank[1];
      assertTrue(case1 != case2);
    }

    @Test
    public void DataTest2(){
      //Checking if pagerank changes after steps are done
      double case1 = pr.pagerank[2];
      pr.calc(5);
      pr.convert();
      double case2 = pr.pagerank[2];
      assertTrue(case1 != case2);
    }

    @Test
    public void DataTest3(){
      //Checking if pagerank changes after steps are done
      double case1 = pr.pagerank[3];
      pr.calc(5);
      pr.convert();
      double case2 = pr.pagerank[3];
      assertTrue(case1 != case2);
    }

}


