package io.deepblueai.infra.generator.ui;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "io.deepblueai.infra.generator")
public class GeneratorUIApplication {

  public static void main(String[] args) {
    SpringApplication.run(GeneratorUIApplication.class, args);
  }
}
