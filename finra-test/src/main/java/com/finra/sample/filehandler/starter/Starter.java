/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.finra.sample.filehandler.starter;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.Configuration;

/**
 *
 * @author SIGINT-X
 */
@Configuration
@SpringBootApplication(scanBasePackages = {"com.finra"})
public class Starter {

    public static void main(String[] args) {

        new SpringApplicationBuilder().sources(Starter.class).run(args);

    }

}
