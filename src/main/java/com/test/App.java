package com.test;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.web.reactive.function.client.WebClient;

@SpringBootApplication
class App {
  WebClient.Builder webClientBuilder;

  App(WebClient.Builder webClientBuilder) {
    this.webClientBuilder = webClientBuilder;
  }

  public static void main(String ... args){
    SpringApplication.run(App.class, args);
  }

  @EventListener(ApplicationReadyEvent.class)
  public void doSomethingAfterStartup() {
    WebClient webClient = webClientBuilder
        .baseUrl("http://localhost:8080")
        .build();

    webClient.get()
        .uri("/service/{id}/endpoint", "samplePath")
        .retrieve()
        .toBodilessEntity()
        .subscribe();

    webClient.get()
        .uri("/service/endpoint?sampleParam={paramValue}", "paramValue")
        .retrieve()
        .toBodilessEntity()
        .subscribe();
  }
}
