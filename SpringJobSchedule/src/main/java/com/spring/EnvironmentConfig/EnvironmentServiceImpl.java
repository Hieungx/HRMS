package com.spring.EnvironmentConfig;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import javax.annotation.Resource;

import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@PropertySource(ignoreResourceNotFound = true, value = "classpath:application.yml")
@Component
public class EnvironmentServiceImpl implements EnviromentService {
	@Resource
	Environment env;

	public static final String FILENAME = "src/main/resources/application.yml";

	@Override
	public String getEvnVariable(String key) {
		System.out.println(key);
		System.out.println(env.getProperty(key));
		return env.getProperty(key);
	}

	public void setEvnVariable(String key, String value) {
		BufferedWriter bw = null;
		FileWriter fw = null;

		try {
			File file = new File(FILENAME);
			fw = new FileWriter(file.getAbsoluteFile(), true);
			bw = new BufferedWriter(fw);
			bw.write(key + "=" + value + "\n");
			System.out.println("Success...");
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (bw != null)
					bw.close();
				if (fw != null)
					fw.close();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
	}

}
