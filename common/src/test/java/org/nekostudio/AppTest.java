package org.nekostudio;

import org.junit.Test;
import org.nekostudio.common.JwtUtil;
import org.nekostudio.entity.AppUser;

import static org.junit.Assert.assertTrue;

/**
 * Unit test for simple App.
 */
public class AppTest 
{
    /**
     * Rigorous Test :-)
     */
    @Test
    public void shouldAnswerWithTrue()
    {
        AppUser user = new AppUser();
        user.setSalt("abcd");
        JwtUtil.createTokenByDefaultKey(user);
        assertTrue( true );
    }
}
