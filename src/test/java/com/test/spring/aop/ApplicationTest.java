package com.test.spring.aop;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.springframework.boot.test.OutputCapture;

import static org.assertj.core.api.Assertions.assertThat;


/**
 * Created by miaorf on 2016/7/16.
 */
public class ApplicationTest {
    @Rule
    public OutputCapture outputCapture = new OutputCapture();

    private String profiles;

    @Before
    public void init() throws Exception {
        this.profiles = System.getProperty("spring.profiles.active");
    }

    @After
    public void tearDown() throws Exception {
        if (this.profiles != null){
            System.setProperty("spring.profiles.active",this.profiles);
        }else{
            System.out.println("spring.profiles.active");
        }
    }

    @Test
    public void testDefaultSettings() throws Exception{
        Application.main(new String[0]);
        String s = this.outputCapture.toString();
        assertThat(s).contains("Hi Ryan");
    }
    @Test
    public void testCommandLineOverrides() throws Exception {
        Application.main(new String[] {"--name=Leslie"});
        String s = this.outputCapture.toString();
        assertThat(s).contains("Hi Leslie");
    }


}