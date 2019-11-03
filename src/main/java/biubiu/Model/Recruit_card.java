package biubiu.Model;

import java.io.Serializable;

public class Recruit_card implements Serializable {
    public Recruit_card(Integer id, String objectId, String objectCategory, Integer remainMoney) {
        this.id = id;
        this.objectId = objectId;
        this.objectCategory = objectCategory;
        this.remainMoney = remainMoney;
    }

    public Recruit_card(String objectId, String objectCategory, Integer remainMoney) {
        this.objectId = objectId;
        this.objectCategory = objectCategory;
        this.remainMoney = remainMoney;
    }

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column recruit_card.id
     *
     * @mbggenerated
     */
    private Integer id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column recruit_card.object_id
     *
     * @mbggenerated
     */
    private String objectId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column recruit_card.object_category
     *
     * @mbggenerated
     */
    private String objectCategory;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column recruit_card.remain_money
     *
     * @mbggenerated
     */
    private Integer remainMoney;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column recruit_card.id
     *
     * @return the value of recruit_card.id
     *
     * @mbggenerated
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column recruit_card.id
     *
     * @param id the value for recruit_card.id
     *
     * @mbggenerated
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column recruit_card.object_id
     *
     * @return the value of recruit_card.object_id
     *
     * @mbggenerated
     */
    public String getObjectId() {
        return objectId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column recruit_card.object_id
     *
     * @param objectId the value for recruit_card.object_id
     *
     * @mbggenerated
     */
    public void setObjectId(String objectId) {
        this.objectId = objectId == null ? null : objectId.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column recruit_card.object_category
     *
     * @return the value of recruit_card.object_category
     *
     * @mbggenerated
     */
    public String getObjectCategory() {
        return objectCategory;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column recruit_card.object_category
     *
     * @param objectCategory the value for recruit_card.object_category
     *
     * @mbggenerated
     */
    public void setObjectCategory(String objectCategory) {
        this.objectCategory = objectCategory == null ? null : objectCategory.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column recruit_card.remain_money
     *
     * @return the value of recruit_card.remain_money
     *
     * @mbggenerated
     */
    public Integer getRemainMoney() {
        return remainMoney;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column recruit_card.remain_money
     *
     * @param remainMoney the value for recruit_card.remain_money
     *
     * @mbggenerated
     */
    public void setRemainMoney(Integer remainMoney) {
        this.remainMoney = remainMoney;
    }
}