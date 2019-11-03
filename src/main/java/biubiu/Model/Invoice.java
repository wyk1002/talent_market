package biubiu.Model;

import java.io.Serializable;

public class Invoice implements Serializable {
    public Invoice(Integer id, String objectId, Integer bargainMoney, String bargainExplain, String objectCategory) {
        this.id = id;
        this.objectId = objectId;
        this.bargainMoney = bargainMoney;
        this.bargainExplain = bargainExplain;
        this.objectCategory = objectCategory;
    }

    public Invoice(String objectId, Integer bargainMoney, String bargainExplain, String objectCategory) {
        this.objectId = objectId;
        this.bargainMoney = bargainMoney;
        this.bargainExplain = bargainExplain;
        this.objectCategory = objectCategory;
    }

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column invoice.id
     *
     * @mbggenerated
     */
    private Integer id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column invoice.object_id
     *
     * @mbggenerated
     */
    private String objectId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column invoice.bargain_money
     *
     * @mbggenerated
     */
    private Integer bargainMoney;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column invoice.bargain_explain
     *
     * @mbggenerated
     */
    private String bargainExplain;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column invoice.object_category
     *
     * @mbggenerated
     */
    private String objectCategory;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column invoice.id
     *
     * @return the value of invoice.id
     *
     * @mbggenerated
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column invoice.id
     *
     * @param id the value for invoice.id
     *
     * @mbggenerated
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column invoice.object_id
     *
     * @return the value of invoice.object_id
     *
     * @mbggenerated
     */
    public String getObjectId() {
        return objectId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column invoice.object_id
     *
     * @param objectId the value for invoice.object_id
     *
     * @mbggenerated
     */
    public void setObjectId(String objectId) {
        this.objectId = objectId == null ? null : objectId.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column invoice.bargain_money
     *
     * @return the value of invoice.bargain_money
     *
     * @mbggenerated
     */
    public Integer getBargainMoney() {
        return bargainMoney;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column invoice.bargain_money
     *
     * @param bargainMoney the value for invoice.bargain_money
     *
     * @mbggenerated
     */
    public void setBargainMoney(Integer bargainMoney) {
        this.bargainMoney = bargainMoney;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column invoice.bargain_explain
     *
     * @return the value of invoice.bargain_explain
     *
     * @mbggenerated
     */
    public String getBargainExplain() {
        return bargainExplain;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column invoice.bargain_explain
     *
     * @param bargainExplain the value for invoice.bargain_explain
     *
     * @mbggenerated
     */
    public void setBargainExplain(String bargainExplain) {
        this.bargainExplain = bargainExplain == null ? null : bargainExplain.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column invoice.object_category
     *
     * @return the value of invoice.object_category
     *
     * @mbggenerated
     */
    public String getObjectCategory() {
        return objectCategory;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column invoice.object_category
     *
     * @param objectCategory the value for invoice.object_category
     *
     * @mbggenerated
     */
    public void setObjectCategory(String objectCategory) {
        this.objectCategory = objectCategory == null ? null : objectCategory.trim();
    }
}