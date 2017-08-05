/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nbody;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author anton
 */
public class BVectorTest {
    
    /**
     * Test of addBVector method, of class BVector.
     */
    @Test
    public void testGetForceX() {
        System.out.println("addBVector");
            BVector inputBVector = new BVector(Math.PI/4, 1);
        assertEquals(1.0, inputBVector.getForceY(), 0.001);
    }
    
}
