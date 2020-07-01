/*
 * DRACOON
 * REST Web Services for DRACOON<br>Version: 4.20.1  - built at: 2020-04-05 23:00:17<br><br><a title='Developer Information' href='https://developer.dracoon.com'>Developer Information</a>&emsp;&emsp;<a title='Get SDKs on GitHub' href='https://github.com/dracoon'>Get SDKs on GitHub</a>
 *
 * OpenAPI spec version: 4.20.1
 * 
 *
 * NOTE: This class is auto generated by the swagger code generator program.
 * https://github.com/swagger-api/swagger-codegen.git
 * Do not edit the class manually.
 */


package ch.cyberduck.core.sds.io.swagger.client.model;

import java.util.Objects;
import java.util.Arrays;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * Request model for updating a S3 configuration
 */
@ApiModel(description = "Request model for updating a S3 configuration")
@javax.annotation.Generated(value = "io.swagger.codegen.languages.JavaClientCodegen", date = "2020-04-08T17:57:49.759+02:00")
public class S3ConfigUpdateRequest {
  @JsonProperty("endpointUrl")
  private String endpointUrl = null;

  @JsonProperty("bucketName")
  private String bucketName = null;

  @JsonProperty("accessKey")
  private String accessKey = null;

  @JsonProperty("secretKey")
  private String secretKey = null;

  @JsonProperty("region")
  private String region = null;

  public S3ConfigUpdateRequest endpointUrl(String endpointUrl) {
    this.endpointUrl = endpointUrl;
    return this;
  }

   /**
   * S3 object storage endpoint URL
   * @return endpointUrl
  **/
  @ApiModelProperty(example = "https://www.random-url.com", value = "S3 object storage endpoint URL")
  public String getEndpointUrl() {
    return endpointUrl;
  }

  public void setEndpointUrl(String endpointUrl) {
    this.endpointUrl = endpointUrl;
  }

  public S3ConfigUpdateRequest bucketName(String bucketName) {
    this.bucketName = bucketName;
    return this;
  }

   /**
   * S3 bucket name
   * @return bucketName
  **/
  @ApiModelProperty(value = "S3 bucket name")
  public String getBucketName() {
    return bucketName;
  }

  public void setBucketName(String bucketName) {
    this.bucketName = bucketName;
  }

  public S3ConfigUpdateRequest accessKey(String accessKey) {
    this.accessKey = accessKey;
    return this;
  }

   /**
   * Access Key ID
   * @return accessKey
  **/
  @ApiModelProperty(value = "Access Key ID")
  public String getAccessKey() {
    return accessKey;
  }

  public void setAccessKey(String accessKey) {
    this.accessKey = accessKey;
  }

  public S3ConfigUpdateRequest secretKey(String secretKey) {
    this.secretKey = secretKey;
    return this;
  }

   /**
   * Secret Access Key
   * @return secretKey
  **/
  @ApiModelProperty(value = "Secret Access Key")
  public String getSecretKey() {
    return secretKey;
  }

  public void setSecretKey(String secretKey) {
    this.secretKey = secretKey;
  }

  public S3ConfigUpdateRequest region(String region) {
    this.region = region;
    return this;
  }

   /**
   * S3 region
   * @return region
  **/
  @ApiModelProperty(value = "S3 region")
  public String getRegion() {
    return region;
  }

  public void setRegion(String region) {
    this.region = region;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    S3ConfigUpdateRequest s3ConfigUpdateRequest = (S3ConfigUpdateRequest) o;
    return Objects.equals(this.endpointUrl, s3ConfigUpdateRequest.endpointUrl) &&
        Objects.equals(this.bucketName, s3ConfigUpdateRequest.bucketName) &&
        Objects.equals(this.accessKey, s3ConfigUpdateRequest.accessKey) &&
        Objects.equals(this.secretKey, s3ConfigUpdateRequest.secretKey) &&
        Objects.equals(this.region, s3ConfigUpdateRequest.region);
  }

  @Override
  public int hashCode() {
    return Objects.hash(endpointUrl, bucketName, accessKey, secretKey, region);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class S3ConfigUpdateRequest {\n");
    
    sb.append("    endpointUrl: ").append(toIndentedString(endpointUrl)).append("\n");
    sb.append("    bucketName: ").append(toIndentedString(bucketName)).append("\n");
    sb.append("    accessKey: ").append(toIndentedString(accessKey)).append("\n");
    sb.append("    secretKey: ").append(toIndentedString(secretKey)).append("\n");
    sb.append("    region: ").append(toIndentedString(region)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(java.lang.Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }

}

