package com.example.rsocketapi;

import java.time.Duration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;

@Controller
@Slf4j
public class RSocketController {

  private final Flux<String> stream;
    
  @Autowired
  public RSocketController(final Flux<String> stream) {
    this.stream = stream;
  }
  
  @MessageMapping("my.time-updates.stream")
  public Flux<Integer> getTimeUpdatesStream() {
      return Flux.range(1, 10).log()
                .delayElements(Duration.ofSeconds(1));
    // return stream;
  }
  
  // private final List<RSocketRequester> CLIENTS = new ArrayList<>();
  
  // @ConnectMapping
  // void connectShellClientAndAskForTelemetry (RSocketRequester requester, @Payload String client) {
    // log.info("requester : {}", requester);
    // requester.rsocket()
    //     .onClose() // (1)
    //     .doFirst(() -> { 
    //       log.info("Client: {} CONNECTED.", client);
    //       CLIENTS.add(requester); // (2)
    //     })
    //     .doOnError(error -> { 
    //       log.warn("Channel to client {} CLOSED", client); // (3)
    //     })
    //     .doFinally(consumer -> { 
    //       CLIENTS.remove(requester);
    //       log.info("Client {} DISCONNECTED", client); // (4)
    //     })
    //     .subscribe();
    // log.info("CLIENTS : {}", CLIENTS);
  // }
  
  // @MessageMapping("number.stream")
  // public Flux<Integer> responseStream(Integer number) {
  //   return Flux.range(1, number).log()
  //               .delayElements(Duration.ofSeconds(1));
  // }

  // @MessageMapping("aa.stream")
  // public Flux<Integer> responseStream1(Integer number) {
  //   return Flux.range(1, number).log()
  //               .delayElements(Duration.ofSeconds(1));
  // }

  // @MessageMapping("number.channel")
  // public Flux<Long> biDirectionalStream(Flux<Long> numberFlux) {
  //   return numberFlux.map(n -> n * n)
  //                   .onErrorReturn(-1L);
  // }

}
