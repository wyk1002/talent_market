package biubiu.Model;

import java.io.Serializable;

public class Recruit_area implements Serializable {
    public Recruit_area(String name) {
        this.name = name;
    }

    public Recruit_area(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column recruit_area.id
     *
     * @mbggenerated
     */
    private Integer id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column recruit_area.name
     *
     * @mbggenerated
     */
    private String name;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column recruit_area.id
     *
     * @return the value of recruit_area.id
     *
     * @mbggenerated
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column recruit_area.id
     *
     * @param id the value for recruit_area.id
     *
     * @mbggenerated
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column recruit_area.name
     *
     * @return the value of recruit_area.name
     *
     * @mbggenerated
     */
    public String getName() {
        return name;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column recruit_area.name
     *
     * @param name the value for recruit_area.name
     *
     * @mbggenerated
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }
}