package io.hongting.config;

import com.oracle.webservices.internal.api.databinding.DatabindingMode;
import lombok.*;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Component
@ConfigurationProperties(prefix = "aliyun.oss")
public class OssProperties {

    private String endpoint;
    private String keyId;
    private String keySecret;
    private String bucketName;
    private String bucketDomain;


}
