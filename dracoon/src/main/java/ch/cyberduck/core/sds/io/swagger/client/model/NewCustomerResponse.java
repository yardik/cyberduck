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
import ch.cyberduck.core.sds.io.swagger.client.model.CustomerAttributes;
import ch.cyberduck.core.sds.io.swagger.client.model.FirstAdminUser;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.joda.time.DateTime;

/**
 * Customer information
 */
@ApiModel(description = "Customer information")
@javax.annotation.Generated(value = "io.swagger.codegen.languages.JavaClientCodegen", date = "2020-04-08T17:57:49.759+02:00")
public class NewCustomerResponse {
  @JsonProperty("id")
  private Long id = null;

  @JsonProperty("companyName")
  private String companyName = null;

  /**
   * Customer type
   */
  public enum CustomerContractTypeEnum {
    FREE("free"),
    
    DEMO("demo"),
    
    PAY("pay");

    private String value;

    CustomerContractTypeEnum(String value) {
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
    public static CustomerContractTypeEnum fromValue(String text) {
      for (CustomerContractTypeEnum b : CustomerContractTypeEnum.values()) {
        if (String.valueOf(b.value).equals(text)) {
          return b;
        }
      }
      return null;
    }
  }

  @JsonProperty("customerContractType")
  private CustomerContractTypeEnum customerContractType = null;

  @JsonProperty("quotaMax")
  private Long quotaMax = null;

  @JsonProperty("userMax")
  private Integer userMax = null;

  @JsonProperty("isLocked")
  private Boolean isLocked = null;

  @JsonProperty("trialDays")
  private Integer trialDays = null;

  @JsonProperty("createdAt")
  private DateTime createdAt = null;

  @JsonProperty("firstAdminUser")
  private FirstAdminUser firstAdminUser = null;

  @JsonProperty("customerAttributes")
  private CustomerAttributes customerAttributes = null;

  @JsonProperty("providerCustomerId")
  private String providerCustomerId = null;

  @JsonProperty("webhooksMax")
  private Long webhooksMax = null;

  @JsonProperty("activationCode")
  private String activationCode = null;

  @JsonProperty("lockStatus")
  private Boolean lockStatus = null;

  public NewCustomerResponse id(Long id) {
    this.id = id;
    return this;
  }

   /**
   * Unique identifier for the customer
   * @return id
  **/
  @ApiModelProperty(value = "Unique identifier for the customer")
  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public NewCustomerResponse companyName(String companyName) {
    this.companyName = companyName;
    return this;
  }

   /**
   * Company name
   * @return companyName
  **/
  @ApiModelProperty(required = true, value = "Company name")
  public String getCompanyName() {
    return companyName;
  }

  public void setCompanyName(String companyName) {
    this.companyName = companyName;
  }

  public NewCustomerResponse customerContractType(CustomerContractTypeEnum customerContractType) {
    this.customerContractType = customerContractType;
    return this;
  }

   /**
   * Customer type
   * @return customerContractType
  **/
  @ApiModelProperty(example = "pay", required = true, value = "Customer type")
  public CustomerContractTypeEnum getCustomerContractType() {
    return customerContractType;
  }

  public void setCustomerContractType(CustomerContractTypeEnum customerContractType) {
    this.customerContractType = customerContractType;
  }

  public NewCustomerResponse quotaMax(Long quotaMax) {
    this.quotaMax = quotaMax;
    return this;
  }

   /**
   * Maximal disc space which can be allocated by customer in bytes. -1 for unlimited
   * @return quotaMax
  **/
  @ApiModelProperty(required = true, value = "Maximal disc space which can be allocated by customer in bytes. -1 for unlimited")
  public Long getQuotaMax() {
    return quotaMax;
  }

  public void setQuotaMax(Long quotaMax) {
    this.quotaMax = quotaMax;
  }

  public NewCustomerResponse userMax(Integer userMax) {
    this.userMax = userMax;
    return this;
  }

   /**
   * Maximal number of users
   * @return userMax
  **/
  @ApiModelProperty(required = true, value = "Maximal number of users")
  public Integer getUserMax() {
    return userMax;
  }

  public void setUserMax(Integer userMax) {
    this.userMax = userMax;
  }

  public NewCustomerResponse isLocked(Boolean isLocked) {
    this.isLocked = isLocked;
    return this;
  }

   /**
   * Customer is locked: * &#x60;false&#x60; - unlocked * &#x60;true&#x60; - locked  All users of this customer will be blocked and cannot login anymore. (default: &#x60;false&#x60;)
   * @return isLocked
  **/
  @ApiModelProperty(value = "Customer is locked: * `false` - unlocked * `true` - locked  All users of this customer will be blocked and cannot login anymore. (default: `false`)")
  public Boolean isIsLocked() {
    return isLocked;
  }

  public void setIsLocked(Boolean isLocked) {
    this.isLocked = isLocked;
  }

  public NewCustomerResponse trialDays(Integer trialDays) {
    this.trialDays = trialDays;
    return this;
  }

   /**
   * Number of days left for trial period (relevant only for type &#x60;demo&#x60;) (not used)
   * @return trialDays
  **/
  @ApiModelProperty(value = "Number of days left for trial period (relevant only for type `demo`) (not used)")
  public Integer getTrialDays() {
    return trialDays;
  }

  public void setTrialDays(Integer trialDays) {
    this.trialDays = trialDays;
  }

  public NewCustomerResponse createdAt(DateTime createdAt) {
    this.createdAt = createdAt;
    return this;
  }

   /**
   * Creation date
   * @return createdAt
  **/
  @ApiModelProperty(example = "2018-01-01T00:00:00", value = "Creation date")
  public DateTime getCreatedAt() {
    return createdAt;
  }

  public void setCreatedAt(DateTime createdAt) {
    this.createdAt = createdAt;
  }

  public NewCustomerResponse firstAdminUser(FirstAdminUser firstAdminUser) {
    this.firstAdminUser = firstAdminUser;
    return this;
  }

   /**
   * First admin user of a customer
   * @return firstAdminUser
  **/
  @ApiModelProperty(required = true, value = "First admin user of a customer")
  public FirstAdminUser getFirstAdminUser() {
    return firstAdminUser;
  }

  public void setFirstAdminUser(FirstAdminUser firstAdminUser) {
    this.firstAdminUser = firstAdminUser;
  }

  public NewCustomerResponse customerAttributes(CustomerAttributes customerAttributes) {
    this.customerAttributes = customerAttributes;
    return this;
  }

   /**
   * Customer attributes
   * @return customerAttributes
  **/
  @ApiModelProperty(value = "Customer attributes")
  public CustomerAttributes getCustomerAttributes() {
    return customerAttributes;
  }

  public void setCustomerAttributes(CustomerAttributes customerAttributes) {
    this.customerAttributes = customerAttributes;
  }

  public NewCustomerResponse providerCustomerId(String providerCustomerId) {
    this.providerCustomerId = providerCustomerId;
    return this;
  }

   /**
   * Provider customer ID
   * @return providerCustomerId
  **/
  @ApiModelProperty(value = "Provider customer ID")
  public String getProviderCustomerId() {
    return providerCustomerId;
  }

  public void setProviderCustomerId(String providerCustomerId) {
    this.providerCustomerId = providerCustomerId;
  }

  public NewCustomerResponse webhooksMax(Long webhooksMax) {
    this.webhooksMax = webhooksMax;
    return this;
  }

   /**
   * Maximal number of webhooks  [Since version 4.19.0]
   * @return webhooksMax
  **/
  @ApiModelProperty(value = "Maximal number of webhooks  [Since version 4.19.0]")
  public Long getWebhooksMax() {
    return webhooksMax;
  }

  public void setWebhooksMax(Long webhooksMax) {
    this.webhooksMax = webhooksMax;
  }

  public NewCustomerResponse activationCode(String activationCode) {
    this.activationCode = activationCode;
    return this;
  }

   /**
   * &#x60;DEPRECATED&#x60;: Customer activation code string: * valid only for types &#x60;free&#x60; and &#x60;demo&#x60; * for &#x60;pay&#x60; customers it is empty  [Deprecated since version 4.8.0]
   * @return activationCode
  **/
  @ApiModelProperty(value = "`DEPRECATED`: Customer activation code string: * valid only for types `free` and `demo` * for `pay` customers it is empty  [Deprecated since version 4.8.0]")
  public String getActivationCode() {
    return activationCode;
  }

  public void setActivationCode(String activationCode) {
    this.activationCode = activationCode;
  }

  public NewCustomerResponse lockStatus(Boolean lockStatus) {
    this.lockStatus = lockStatus;
    return this;
  }

   /**
   * &#x60;DEPRECATED&#x60;: Customer lock status: * &#x60;false&#x60; - unlocked * &#x60;true&#x60; - locked  Please use &#x60;isLocked&#x60; instead. All users of this customer will be blocked and cannot login anymore. (default: &#x60;false&#x60;)  [Deprecated since version 4.7.0]
   * @return lockStatus
  **/
  @ApiModelProperty(required = true, value = "`DEPRECATED`: Customer lock status: * `false` - unlocked * `true` - locked  Please use `isLocked` instead. All users of this customer will be blocked and cannot login anymore. (default: `false`)  [Deprecated since version 4.7.0]")
  public Boolean isLockStatus() {
    return lockStatus;
  }

  public void setLockStatus(Boolean lockStatus) {
    this.lockStatus = lockStatus;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    NewCustomerResponse newCustomerResponse = (NewCustomerResponse) o;
    return Objects.equals(this.id, newCustomerResponse.id) &&
        Objects.equals(this.companyName, newCustomerResponse.companyName) &&
        Objects.equals(this.customerContractType, newCustomerResponse.customerContractType) &&
        Objects.equals(this.quotaMax, newCustomerResponse.quotaMax) &&
        Objects.equals(this.userMax, newCustomerResponse.userMax) &&
        Objects.equals(this.isLocked, newCustomerResponse.isLocked) &&
        Objects.equals(this.trialDays, newCustomerResponse.trialDays) &&
        Objects.equals(this.createdAt, newCustomerResponse.createdAt) &&
        Objects.equals(this.firstAdminUser, newCustomerResponse.firstAdminUser) &&
        Objects.equals(this.customerAttributes, newCustomerResponse.customerAttributes) &&
        Objects.equals(this.providerCustomerId, newCustomerResponse.providerCustomerId) &&
        Objects.equals(this.webhooksMax, newCustomerResponse.webhooksMax) &&
        Objects.equals(this.activationCode, newCustomerResponse.activationCode) &&
        Objects.equals(this.lockStatus, newCustomerResponse.lockStatus);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, companyName, customerContractType, quotaMax, userMax, isLocked, trialDays, createdAt, firstAdminUser, customerAttributes, providerCustomerId, webhooksMax, activationCode, lockStatus);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class NewCustomerResponse {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    companyName: ").append(toIndentedString(companyName)).append("\n");
    sb.append("    customerContractType: ").append(toIndentedString(customerContractType)).append("\n");
    sb.append("    quotaMax: ").append(toIndentedString(quotaMax)).append("\n");
    sb.append("    userMax: ").append(toIndentedString(userMax)).append("\n");
    sb.append("    isLocked: ").append(toIndentedString(isLocked)).append("\n");
    sb.append("    trialDays: ").append(toIndentedString(trialDays)).append("\n");
    sb.append("    createdAt: ").append(toIndentedString(createdAt)).append("\n");
    sb.append("    firstAdminUser: ").append(toIndentedString(firstAdminUser)).append("\n");
    sb.append("    customerAttributes: ").append(toIndentedString(customerAttributes)).append("\n");
    sb.append("    providerCustomerId: ").append(toIndentedString(providerCustomerId)).append("\n");
    sb.append("    webhooksMax: ").append(toIndentedString(webhooksMax)).append("\n");
    sb.append("    activationCode: ").append(toIndentedString(activationCode)).append("\n");
    sb.append("    lockStatus: ").append(toIndentedString(lockStatus)).append("\n");
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

