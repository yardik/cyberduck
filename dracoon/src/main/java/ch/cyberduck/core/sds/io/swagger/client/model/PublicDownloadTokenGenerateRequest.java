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
 * Request model for generating download URL
 */
@ApiModel(description = "Request model for generating download URL")
@javax.annotation.Generated(value = "io.swagger.codegen.languages.JavaClientCodegen", date = "2020-04-08T17:57:49.759+02:00")
public class PublicDownloadTokenGenerateRequest {
  @JsonProperty("password")
  private String password = null;

  public PublicDownloadTokenGenerateRequest password(String password) {
    this.password = password;
    return this;
  }

   /**
   * Password (only for password-protected shares)
   * @return password
  **/
  @ApiModelProperty(value = "Password (only for password-protected shares)")
  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    PublicDownloadTokenGenerateRequest publicDownloadTokenGenerateRequest = (PublicDownloadTokenGenerateRequest) o;
    return Objects.equals(this.password, publicDownloadTokenGenerateRequest.password);
  }

  @Override
  public int hashCode() {
    return Objects.hash(password);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class PublicDownloadTokenGenerateRequest {\n");
    
    sb.append("    password: ").append(toIndentedString(password)).append("\n");
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

