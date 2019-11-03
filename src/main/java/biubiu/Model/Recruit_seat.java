package biubiu.Model;

import java.io.Serializable;

public class Recruit_seat implements Serializable {
    public Recruit_seat(Integer id, String recruitSeatNum, Integer recruitAreaId, String firmId, String state, Integer price) {
        this.id = id;
        this.recruitSeatNum = recruitSeatNum;
        this.recruitAreaId = recruitAreaId;
        this.firmId = firmId;
        this.state = state;
        this.price = price;
    }

    public Recruit_seat(String recruitSeatNum, Integer recruitAreaId, String firmId, String state, Integer price) {
        this.recruitSeatNum = recruitSeatNum;
        this.recruitAreaId = recruitAreaId;
        this.firmId = firmId;
        this.state = state;
        this.price = price;
    }

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column recruit_seat.id
     *
     * @mbggenerated
     */
    private Integer id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column recruit_seat.recruit_seat_num
     *
     * @mbggenerated
     */
    private String recruitSeatNum;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column recruit_seat.recruit_area_id
     *
     * @mbggenerated
     */
    private Integer recruitAreaId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column recruit_seat.firm_id
     *
     * @mbggenerated
     */
    private String firmId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column recruit_seat.state
     *
     * @mbggenerated
     */
    private String state;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column recruit_seat.price
     *
     * @mbggenerated
     */
    private Integer price;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column recruit_seat.id
     *
     * @return the value of recruit_seat.id
     *
     * @mbggenerated
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column recruit_seat.id
     *
     * @param id the value for recruit_seat.id
     *
     * @mbggenerated
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column recruit_seat.recruit_seat_num
     *
     * @return the value of recruit_seat.recruit_seat_num
     *
     * @mbggenerated
     */
    public String getRecruitSeatNum() {
        return recruitSeatNum;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column recruit_seat.recruit_seat_num
     *
     * @param recruitSeatNum the value for recruit_seat.recruit_seat_num
     *
     * @mbggenerated
     */
    public void setRecruitSeatNum(String recruitSeatNum) {
        this.recruitSeatNum = recruitSeatNum == null ? null : recruitSeatNum.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column recruit_seat.recruit_area_id
     *
     * @return the value of recruit_seat.recruit_area_id
     *
     * @mbggenerated
     */
    public Integer getRecruitAreaId() {
        return recruitAreaId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column recruit_seat.recruit_area_id
     *
     * @param recruitAreaId the value for recruit_seat.recruit_area_id
     *
     * @mbggenerated
     */
    public void setRecruitAreaId(Integer recruitAreaId) {
        this.recruitAreaId = recruitAreaId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column recruit_seat.firm_id
     *
     * @return the value of recruit_seat.firm_id
     *
     * @mbggenerated
     */
    public String getFirmId() {
        return firmId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column recruit_seat.firm_id
     *
     * @param firmId the value for recruit_seat.firm_id
     *
     * @mbggenerated
     */
    public void setFirmId(String firmId) {
        this.firmId = firmId == null ? null : firmId.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column recruit_seat.state
     *
     * @return the value of recruit_seat.state
     *
     * @mbggenerated
     */
    public String getState() {
        return state;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column recruit_seat.state
     *
     * @param state the value for recruit_seat.state
     *
     * @mbggenerated
     */
    public void setState(String state) {
        this.state = state == null ? null : state.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column recruit_seat.price
     *
     * @return the value of recruit_seat.price
     *
     * @mbggenerated
     */
    public Integer getPrice() {
        return price;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column recruit_seat.price
     *
     * @param price the value for recruit_seat.price
     *
     * @mbggenerated
     */
    public void setPrice(Integer price) {
        this.price = price;
    }
}