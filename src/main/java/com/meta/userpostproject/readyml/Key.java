package com.meta.userpostproject.readyml;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.List;

/*
The author of this class is java-suraj
lightsuraj129@gmail.com
Program was written in 2023-05-14 16:43
*/
@Data
@NoArgsConstructor
@AllArgsConstructor
@Configuration
@ConfigurationProperties("user.created")
public class Key {
    private String value1;
    private String value2;
    private String value3;
    private List<String> value4;

    @Override
    public String toString() {
        return "Key{" +
                "value1='" + value1 + '\'' +
                ", value2='" + value2 + '\'' +
                ", value3='" + value3 + '\'' +
                ", value4=" + value4 +
                '}';
    }
}
