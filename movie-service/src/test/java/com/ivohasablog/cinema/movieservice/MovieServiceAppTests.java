package com.ivohasablog.cinema.movieservice;

import org.apache.commons.io.FileUtils;
import org.junit.Ignore;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;
import java.io.IOException;

@RunWith(SpringRunner.class)
@SpringBootTest()
@DataMongoTest
@Ignore
public class MovieServiceAppTests {

	@Autowired
	private MongoTemplate mongoTemplate;

	protected void importJSON(String collection, String file) {
		try {
			for (Object line : FileUtils.readLines(new File(file), "utf8")) {
				mongoTemplate.save(line, collection);
			}
		} catch (IOException e) {
			throw new RuntimeException("Could not import file: " + file, e);
		}
	}

}
