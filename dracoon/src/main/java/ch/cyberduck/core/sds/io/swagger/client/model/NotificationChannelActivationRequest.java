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
 * Request model for switching notification channel status
 */
@ApiModel(description = "Request model for switching notification channel status")
@javax.annotation.Generated(value = "io.swagger.codegen.languages.JavaClientCodegen", date = "2020-04-08T17:57:49.759+02:00")
public class NotificationChannelActivationRequest {
  @JsonProperty("channelId")
  private Integer channelId = null;

  @JsonProperty("isEnabled")
  private Boolean isEnabled = null;

  public NotificationChannelActivationRequest channelId(Integer channelId) {
    this.channelId = channelId;
    return this;
  }

   /**
   * Channel ID
   * @return channelId
  **/
  @ApiModelProperty(required = true, value = "Channel ID")
  public Integer getChannelId() {
    return channelId;
  }

  public void setChannelId(Integer channelId) {
    this.channelId = channelId;
  }

  public NotificationChannelActivationRequest isEnabled(Boolean isEnabled) {
    this.isEnabled = isEnabled;
    return this;
  }

   /**
   * Determines whether channel is enabled
   * @return isEnabled
  **/
  @ApiModelProperty(required = true, value = "Determines whether channel is enabled")
  public Boolean isIsEnabled() {
    return isEnabled;
  }

  public void setIsEnabled(Boolean isEnabled) {
    this.isEnabled = isEnabled;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    NotificationChannelActivationRequest notificationChannelActivationRequest = (NotificationChannelActivationRequest) o;
    return Objects.equals(this.channelId, notificationChannelActivationRequest.channelId) &&
        Objects.equals(this.isEnabled, notificationChannelActivationRequest.isEnabled);
  }

  @Override
  public int hashCode() {
    return Objects.hash(channelId, isEnabled);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class NotificationChannelActivationRequest {\n");
    
    sb.append("    channelId: ").append(toIndentedString(channelId)).append("\n");
    sb.append("    isEnabled: ").append(toIndentedString(isEnabled)).append("\n");
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

