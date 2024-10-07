package org.soumia.customerservice.config;

import lombok.*;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix="global.params")
@Getter @Setter @ToString  @NoArgsConstructor @AllArgsConstructor
public class GlobalConfig {
    private int p1;
    private int p2;
}
