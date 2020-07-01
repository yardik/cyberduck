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
 * Request model for updating system defaults
 */
@ApiModel(description = "Request model for updating system defaults")
@javax.annotation.Generated(value = "io.swagger.codegen.languages.JavaClientCodegen", date = "2020-04-08T17:57:49.759+02:00")
public class UpdateSystemDefaults {
  @JsonProperty("languageDefault")
  private String languageDefault = null;

  @JsonProperty("downloadShareDefaultExpirationPeriod")
  private Integer downloadShareDefaultExpirationPeriod = null;

  @JsonProperty("uploadShareDefaultExpirationPeriod")
  private Integer uploadShareDefaultExpirationPeriod = null;

  @JsonProperty("fileDefaultExpirationPeriod")
  private Integer fileDefaultExpirationPeriod = null;

  @JsonProperty("nonmemberViewerDefault")
  private Boolean nonmemberViewerDefault = null;

  public UpdateSystemDefaults languageDefault(String languageDefault) {
    this.languageDefault = languageDefault;
    return this;
  }

   /**
   * Define which language should be default.
   * @return languageDefault
  **/
  @ApiModelProperty(example = "de-DE", value = "Define which language should be default.")
  public String getLanguageDefault() {
    return languageDefault;
  }

  public void setLanguageDefault(String languageDefault) {
    this.languageDefault = languageDefault;
  }

  public UpdateSystemDefaults downloadShareDefaultExpirationPeriod(Integer downloadShareDefaultExpirationPeriod) {
    this.downloadShareDefaultExpirationPeriod = downloadShareDefaultExpirationPeriod;
    return this;
  }

   /**
   * Default expiration period for Download Shares in days.
   * @return downloadShareDefaultExpirationPeriod
  **/
  @ApiModelProperty(value = "Default expiration period for Download Shares in days.")
  public Integer getDownloadShareDefaultExpirationPeriod() {
    return downloadShareDefaultExpirationPeriod;
  }

  public void setDownloadShareDefaultExpirationPeriod(Integer downloadShareDefaultExpirationPeriod) {
    this.downloadShareDefaultExpirationPeriod = downloadShareDefaultExpirationPeriod;
  }

  public UpdateSystemDefaults uploadShareDefaultExpirationPeriod(Integer uploadShareDefaultExpirationPeriod) {
    this.uploadShareDefaultExpirationPeriod = uploadShareDefaultExpirationPeriod;
    return this;
  }

   /**
   * Default expiration period for Upload Shares in days.
   * @return uploadShareDefaultExpirationPeriod
  **/
  @ApiModelProperty(value = "Default expiration period for Upload Shares in days.")
  public Integer getUploadShareDefaultExpirationPeriod() {
    return uploadShareDefaultExpirationPeriod;
  }

  public void setUploadShareDefaultExpirationPeriod(Integer uploadShareDefaultExpirationPeriod) {
    this.uploadShareDefaultExpirationPeriod = uploadShareDefaultExpirationPeriod;
  }

  public UpdateSystemDefaults fileDefaultExpirationPeriod(Integer fileDefaultExpirationPeriod) {
    this.fileDefaultExpirationPeriod = fileDefaultExpirationPeriod;
    return this;
  }

   /**
   * Default expiration period for all uploaded files in days.
   * @return fileDefaultExpirationPeriod
  **/
  @ApiModelProperty(value = "Default expiration period for all uploaded files in days.")
  public Integer getFileDefaultExpirationPeriod() {
    return fileDefaultExpirationPeriod;
  }

  public void setFileDefaultExpirationPeriod(Integer fileDefaultExpirationPeriod) {
    this.fileDefaultExpirationPeriod = fileDefaultExpirationPeriod;
  }

  public UpdateSystemDefaults nonmemberViewerDefault(Boolean nonmemberViewerDefault) {
    this.nonmemberViewerDefault = nonmemberViewerDefault;
    return this;
  }

   /**
   * Defines if new users get the role NONMEMBER_VIEWER by default
   * @return nonmemberViewerDefault
  **/
  @ApiModelProperty(value = "Defines if new users get the role NONMEMBER_VIEWER by default")
  public Boolean isNonmemberViewerDefault() {
    return nonmemberViewerDefault;
  }

  public void setNonmemberViewerDefault(Boolean nonmemberViewerDefault) {
    this.nonmemberViewerDefault = nonmemberViewerDefault;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    UpdateSystemDefaults updateSystemDefaults = (UpdateSystemDefaults) o;
    return Objects.equals(this.languageDefault, updateSystemDefaults.languageDefault) &&
        Objects.equals(this.downloadShareDefaultExpirationPeriod, updateSystemDefaults.downloadShareDefaultExpirationPeriod) &&
        Objects.equals(this.uploadShareDefaultExpirationPeriod, updateSystemDefaults.uploadShareDefaultExpirationPeriod) &&
        Objects.equals(this.fileDefaultExpirationPeriod, updateSystemDefaults.fileDefaultExpirationPeriod) &&
        Objects.equals(this.nonmemberViewerDefault, updateSystemDefaults.nonmemberViewerDefault);
  }

  @Override
  public int hashCode() {
    return Objects.hash(languageDefault, downloadShareDefaultExpirationPeriod, uploadShareDefaultExpirationPeriod, fileDefaultExpirationPeriod, nonmemberViewerDefault);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class UpdateSystemDefaults {\n");
    
    sb.append("    languageDefault: ").append(toIndentedString(languageDefault)).append("\n");
    sb.append("    downloadShareDefaultExpirationPeriod: ").append(toIndentedString(downloadShareDefaultExpirationPeriod)).append("\n");
    sb.append("    uploadShareDefaultExpirationPeriod: ").append(toIndentedString(uploadShareDefaultExpirationPeriod)).append("\n");
    sb.append("    fileDefaultExpirationPeriod: ").append(toIndentedString(fileDefaultExpirationPeriod)).append("\n");
    sb.append("    nonmemberViewerDefault: ").append(toIndentedString(nonmemberViewerDefault)).append("\n");
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

