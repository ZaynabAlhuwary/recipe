package recipe.exam;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class SolutionTest {

    Solution solutionObj;

    @Before
    public void setUp() throws Exception {
        solutionObj = new Solution();
    }

    @Test
    public void solution() {
      /*  String result = "";
        if(null !=message && message.equals("")){

            int length=0;
            String[] splittedArr = message.split(" ");
            for(String splitted:splittedArr){
                length+=splitted.length()+1;
                if(length<=K){
                    result +=splitted+" ";
                    System.out.println(result);
                }
            }
        }
        return result.trim();
    }*/
}}