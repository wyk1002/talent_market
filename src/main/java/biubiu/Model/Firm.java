package biubiu.Model;

import java.io.Serializable;

public class Firm implements Serializable {
    public Firm(Integer id, String name, String principalName, String principalPhone) {
        this.id = id;
        this.name = name;
        this.principalName = principalName;
        this.principalPhone = principalPhone;
    }

    public Firm(String name, String principalName, String principalPhone) {
        this.name = name;
        this.principalName = principalName;
        this.principalPhone = principalPhone;
    }

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column firm.id
     *
     * @mbggenerated
     */
    private Integer id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column firm.name
     *
     * @mbggenerated
     */
    private String name;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column firm.principal_name
     *
     * @mbggenerated
     */
    private String principalName;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column firm.principal_phone
     *
     * @mbggenerated
     */
    private String principalPhone;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column firm.id
     *
     * @return the value of firm.id
     *
     * @mbggenerated
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column firm.id
     *
     * @param id the value for firm.id
     *
     * @mbggenerated
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column firm.name
     *
     * @return the value of firm.name
     *
     * @mbggenerated
     */
    public String getName() {
        return name;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column firm.name
     *
     * @param name the value for firm.name
     *
     * @mbggenerated
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column firm.principal_name
     *
     * @return the value of firm.principal_name
     *
     * @mbggenerated
     */
    public String getPrincipalName() {
        return principalName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column firm.principal_name
     *
     * @param principalName the value for firm.principal_name
     *
     * @mbggenerated
     */
    public void setPrincipalName(String principalName) {
        this.principalName = principalName == null ? null : principalName.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column firm.principal_phone
     *
     * @return the value of firm.principal_phone
     *
     * @mbggenerated
     */
    public String getPrincipalPhone() {
        return principalPhone;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column firm.principal_phone
     *
     * @param principalPhone the value for firm.principal_phone
     *
     * @mbggenerated
     */
    public void setPrincipalPhone(String principalPhone) {
        this.principalPhone = principalPhone == null ? null : principalPhone.trim();
    }
}