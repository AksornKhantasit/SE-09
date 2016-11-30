package com.se9project.se_9_project;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() throws Exception {
        assertEquals(4, 2 + 2);
    }
    //SignUpPage======================================================
    //EmailTest-------------------------------------------------------
    @Test
    public void checkEmailTest_1(){
        SignUpPage sup = new SignUpPage();
        CharSequence email = "userName@gmail.com";
        assertEquals(sup.checkEmail(email),true);
    }
    @Test
    public void checkEmailTest_2(){
        SignUpPage sup = new SignUpPage();
        CharSequence email = "userName@hotmail.com";
        assertEquals(sup.checkEmail(email),true);
    }
    @Test
    public void checkEmailTest_3(){
        SignUpPage sup = new SignUpPage();
        CharSequence email = "userName@yahoo.com";
        assertEquals(sup.checkEmail(email),true);
    }
    @Test
    public void checkEmailTest_4(){
        SignUpPage sup = new SignUpPage();
        CharSequence email = "userName@abc.com";
        assertEquals(sup.checkEmail(email),false);
    }
    @Test
    public void checkEmailTest_5(){
        SignUpPage sup = new SignUpPage();
        CharSequence email = "userNamehotmail.com";
        assertEquals(sup.checkEmail(email),false);
    }
    //PasswordTest----------------------------------------------------
    @Test
    public void checkPasswordTest_1(){
        SignUpPage sup = new SignUpPage();
        CharSequence password = "userPassword";
        CharSequence repassword = "userPassword";
        assertEquals(sup.checkPassword(password,repassword),true);
    }
    @Test
    public void checkPasswordTest_2(){
        SignUpPage sup = new SignUpPage();
        CharSequence password = "user";
        CharSequence repassword = "user";
        assertEquals(sup.checkPassword(password,repassword),false);
    }
    @Test
    public void checkPasswordTest_3(){
        SignUpPage sup = new SignUpPage();
        CharSequence password = "userAndTheLongPassword";
        CharSequence repassword = "userAndTheLongPassword";
        assertEquals(sup.checkPassword(password,repassword),false);
    }
    @Test
    public void checkPasswordTest_4(){
        SignUpPage sup = new SignUpPage();
        CharSequence password = "userPassword";
        CharSequence repassword = "notMatchPass";
        assertEquals(sup.checkPassword(password,repassword),false);
    }
    //UsernameTest-----------------------------------------------------------

    //DBHelper===============================================================
    //LoginTest---------------------------------------------------------------
    /*@Test
    public void checkLogin(){
        DBHelper helper = new DBHelper(new MainActivity());
        Friend friend = new Friend("username","password","email");
        helper.addFriend(friend);
        assertEquals(helper.checkLogin("username","password"),true);
    }*/

}