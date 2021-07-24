package com.digitalinnovation.livecoding.config;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapperConfig;

import org.socialsignin.spring.data.dynamodb.repository.config.EnableDynamoDBRepositories;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableDynamoDBRepositories
public class DynamoConfig {

	@Value("${aws_access_key_id}")
	private String amazonAWSAccessKey;

	@Value("${aws_secret_access_key}")
	private String amazonAWSSecretKey;

	public AWSCredentialsProvider amazonAWSCredentialsProvider() {
		return new AWSStaticCredentialsProvider(amazonAWSCredentials());
	}

	@Bean
	public AWSCredentials amazonAWSCredentials() {
		return new BasicAWSCredentials(amazonAWSAccessKey, amazonAWSSecretKey);
	}

	@Bean
	public DynamoDBMapperConfig dynamoDBMapperConfig() {
		return DynamoDBMapperConfig.DEFAULT;
	}

	@Bean
	public DynamoDBMapper dynamoDBMapper(AmazonDynamoDB amazonDynamoDB, DynamoDBMapperConfig config) {
		return new DynamoDBMapper(amazonDynamoDB, config);
	}

	@Bean
	public AmazonDynamoDB amazonDynamoDB() {
		return AmazonDynamoDBClientBuilder.standard().withCredentials(amazonAWSCredentialsProvider())
				.withRegion(Regions.SA_EAST_1).build();
	}
}

// @Configuration
// @EnableDynamoDBRepositories
// public class DynamoConfig {
//   @Value("${amazon.dynamodb.endpoint}")
//   private String amazonDynamoDBEndpoint;

//   @Value("${aws_access_key_id}")
//   private String amazonAWSAccessKey;

//   @Value("${aws_secret_access_key}")
//   private String amazonAWSSecretKey;

//   @Bean
//   public AmazonDynamoDB amazonDynamoDB() {
//     AmazonDynamoDB amazonDynamoDB
//       = new AmazonDynamoDBClient(amazonAWSCredentials());

//     if (!StringUtils.isEmpty(amazonDynamoDBEndpoint)) {
//       amazonDynamoDB.setEndpoint(amazonDynamoDBEndpoint);
//     }

//     return amazonDynamoDB;
//   }

//   @Bean
//   public AWSCredentials amazonAWSCredentials() {
//     return new BasicAWSCredentials(
//       amazonAWSAccessKey, amazonAWSSecretKey);
//   }
// }
