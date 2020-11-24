package com.exodia0.htfjavamaze;

import com.exodia0.htfjavamaze.challengeSolvers.DecodeStringSolver;
import com.exodia0.htfjavamaze.challengeSolvers.PerfectNumberSolver;
import com.exodia0.htfjavamaze.challengeSolvers.ReverseStringSolver;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class TestChallanges {

    @Autowired
    private ReverseStringSolver reverseStringSolver;

    @Autowired
    private DecodeStringSolver decodeStringSolver;

    @Autowired
    private PerfectNumberSolver perfectNumberSolver;


    @Test
    void testReverseString(){
        assertEquals("akkiim", reverseStringSolver.solve("miikka"));
    }

    @Test
    void testDecodeString(){
        assertEquals("AUzfU2IfQdRSrLsh21kyOz84APvrcCP", decodeStringSolver.solve("QVV6ZlUySWZRZFJTckxzaDIxa3lPejg0QVB2cmNDUA=="));
    }

    @Test
    void testPerfectNumber(){
        assertEquals(true, perfectNumberSolver.solve("{\"nth element\":\"28\"}"));
    }


}
