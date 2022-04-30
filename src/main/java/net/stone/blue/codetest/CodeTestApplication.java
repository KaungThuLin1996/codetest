package net.stone.blue.codetest;

import java.util.Set;
import java.util.stream.Collectors;

import org.reflections.Reflections;
import org.reflections.scanners.SubTypesScanner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

import lombok.extern.slf4j.Slf4j;
import net.stone.blue.codetest.annotation.Custom;

@SpringBootApplication
@EnableScheduling
@Slf4j
public class CodeTestApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(CodeTestApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Reflections reflections = new Reflections("net.stone.blue.codetest", new SubTypesScanner(false));
		Set<Class<? extends Object>> classes = reflections.getSubTypesOf(Object.class)
	      .stream()
	      .collect(Collectors.toSet());
		classes.stream().filter(c -> c.getAnnotation(Custom.class) != null).forEach(c -> log.info("[name = {}, className = {}]", 
				 c.getAnnotation(Custom.class).name(), c.getSimpleName()));
	}

}
