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
 * User information
 */
@ApiModel(description = "User information")
@javax.annotation.Generated(value = "io.swagger.codegen.languages.JavaClientCodegen", date = "2020-04-08T17:57:49.759+02:00")
public class UserInfo {
  @JsonProperty("id")
  private Long id = null;

  /**
   * User type: * &#x60;internal&#x60; - ordinary DRACOON user * &#x60;external&#x60; - external user without DRACOON account * &#x60;system&#x60; - system user (non human &amp;#128125;) * &#x60;deleted&#x60; - deleted DRACOON user  [Since version 4.11.0]
   */
  public enum UserTypeEnum {
    INTERNAL("internal"),
    
    EXTERNAL("external"),
    
    SYSTEM("system"),
    
    DELETED("deleted");

    private String value;

    UserTypeEnum(String value) {
      this.value = value;
    }

    @JsonValue
    public String getValue() {
      return value;
    }

    @Override
    public String toString() {
      return String.valueOf(value);
    }

    @JsonCreator
    public static UserTypeEnum fromValue(String text) {
      for (UserTypeEnum b : UserTypeEnum.values()) {
        if (String.valueOf(b.value).equals(text)) {
          return b;
        }
      }
      return null;
    }
  }

  @JsonProperty("userType")
  private UserTypeEnum userType = null;

  @JsonProperty("avatarUuid")
  private String avatarUuid = null;

  @JsonProperty("displayName")
  private String displayName = null;

  @JsonProperty("userName")
  private String userName = null;

  @JsonProperty("firstName")
  private String firstName = null;

  @JsonProperty("lastName")
  private String lastName = null;

  @JsonProperty("email")
  private String email = null;

  @JsonProperty("title")
  private String title = null;

  public UserInfo id(Long id) {
    this.id = id;
    return this;
  }

   /**
   * Unique identifier for the user
   * @return id
  **/
  @ApiModelProperty(required = true, value = "Unique identifier for the user")
  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public UserInfo userType(UserTypeEnum userType) {
    this.userType = userType;
    return this;
  }

   /**
   * User type: * &#x60;internal&#x60; - ordinary DRACOON user * &#x60;external&#x60; - external user without DRACOON account * &#x60;system&#x60; - system user (non human &amp;#128125;) * &#x60;deleted&#x60; - deleted DRACOON user  [Since version 4.11.0]
   * @return userType
  **/
  @ApiModelProperty(required = true, value = "User type: * `internal` - ordinary DRACOON user * `external` - external user without DRACOON account * `system` - system user (non human &#128125;) * `deleted` - deleted DRACOON user  [Since version 4.11.0]")
  public UserTypeEnum getUserType() {
    return userType;
  }

  public void setUserType(UserTypeEnum userType) {
    this.userType = userType;
  }

  public UserInfo avatarUuid(String avatarUuid) {
    this.avatarUuid = avatarUuid;
    return this;
  }

   /**
   * Avatar UUID  [Since version 4.11.0]
   * @return avatarUuid
  **/
  @ApiModelProperty(required = true, value = "Avatar UUID  [Since version 4.11.0]")
  public String getAvatarUuid() {
    return avatarUuid;
  }

  public void setAvatarUuid(String avatarUuid) {
    this.avatarUuid = avatarUuid;
  }

  public UserInfo displayName(String displayName) {
    this.displayName = displayName;
    return this;
  }

   /**
   * &#x60;DEPRECATED&#x60;: Display name use other fields from &#x60;UserInfo&#x60; instead to combine a display name  [Deprecated since version 4.11.0]
   * @return displayName
  **/
  @ApiModelProperty(required = true, value = "`DEPRECATED`: Display name use other fields from `UserInfo` instead to combine a display name  [Deprecated since version 4.11.0]")
  public String getDisplayName() {
    return displayName;
  }

  public void setDisplayName(String displayName) {
    this.displayName = displayName;
  }

  public UserInfo userName(String userName) {
    this.userName = userName;
    return this;
  }

   /**
   * Username (only returned for &#x60;internal&#x60; users)  [Since version 4.13.0]
   * @return userName
  **/
  @ApiModelProperty(value = "Username (only returned for `internal` users)  [Since version 4.13.0]")
  public String getUserName() {
    return userName;
  }

  public void setUserName(String userName) {
    this.userName = userName;
  }

  public UserInfo firstName(String firstName) {
    this.firstName = firstName;
    return this;
  }

   /**
   * User first name (mandatory if &#x60;userType&#x60; is &#x60;internal&#x60;)  [Since version 4.11.0]
   * @return firstName
  **/
  @ApiModelProperty(value = "User first name (mandatory if `userType` is `internal`)  [Since version 4.11.0]")
  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public UserInfo lastName(String lastName) {
    this.lastName = lastName;
    return this;
  }

   /**
   * User last name (mandatory if &#x60;userType&#x60; is &#x60;internal&#x60;)  [Since version 4.11.0]
   * @return lastName
  **/
  @ApiModelProperty(value = "User last name (mandatory if `userType` is `internal`)  [Since version 4.11.0]")
  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public UserInfo email(String email) {
    this.email = email;
    return this;
  }

   /**
   * Email   [Since version 4.11.0]
   * @return email
  **/
  @ApiModelProperty(value = "Email   [Since version 4.11.0]")
  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public UserInfo title(String title) {
    this.title = title;
    return this;
  }

   /**
   * &#x60;DEPRECATED&#x60;: Job title  [Deprecated since version 4.18.0]
   * @return title
  **/
  @ApiModelProperty(value = "`DEPRECATED`: Job title  [Deprecated since version 4.18.0]")
  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    UserInfo userInfo = (UserInfo) o;
    return Objects.equals(this.id, userInfo.id) &&
        Objects.equals(this.userType, userInfo.userType) &&
        Objects.equals(this.avatarUuid, userInfo.avatarUuid) &&
        Objects.equals(this.displayName, userInfo.displayName) &&
        Objects.equals(this.userName, userInfo.userName) &&
        Objects.equals(this.firstName, userInfo.firstName) &&
        Objects.equals(this.lastName, userInfo.lastName) &&
        Objects.equals(this.email, userInfo.email) &&
        Objects.equals(this.title, userInfo.title);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, userType, avatarUuid, displayName, userName, firstName, lastName, email, title);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class UserInfo {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    userType: ").append(toIndentedString(userType)).append("\n");
    sb.append("    avatarUuid: ").append(toIndentedString(avatarUuid)).append("\n");
    sb.append("    displayName: ").append(toIndentedString(displayName)).append("\n");
    sb.append("    userName: ").append(toIndentedString(userName)).append("\n");
    sb.append("    firstName: ").append(toIndentedString(firstName)).append("\n");
    sb.append("    lastName: ").append(toIndentedString(lastName)).append("\n");
    sb.append("    email: ").append(toIndentedString(email)).append("\n");
    sb.append("    title: ").append(toIndentedString(title)).append("\n");
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

