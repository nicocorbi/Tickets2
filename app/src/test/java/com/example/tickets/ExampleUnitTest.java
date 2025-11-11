package com.example.tickets;

import org.junit.Test;

import static org.junit.Assert.*;

import com.example.tickets.View.MainActivity;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
   public void TestSuma(){
        assertEquals(5, MainActivity.sumar(2,3));
   }
   @Test(expected = IllegalArgumentException.class)
   public void testDivision(){
        assertEquals(5,MainActivity.dividir(10,0));
   }
}
// es para ejercutarlo en el local