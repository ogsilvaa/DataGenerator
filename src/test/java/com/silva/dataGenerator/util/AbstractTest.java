package com.silva.dataGenerator.util;

import com.silva.dataGenerator.DataGeneratorApplication;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = DataGeneratorApplication.class)
@AutoConfigureMockMvc
public class AbstractTest {

}
