package biubiu.Service;

import biubiu.Model.Recruit_fair;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

public interface Recruit_fairService {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table recruit_fair
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table recruit_fair
     *
     * @mbggenerated
     */
    int insert(Recruit_fair record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table recruit_fair
     *
     * @mbggenerated
     */
    int insertSelective(Recruit_fair record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table recruit_fair
     *
     * @mbggenerated
     */
    Recruit_fair selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table recruit_fair
     *
     * @mbggenerated
     */
    int updateByPrimaryKeySelective(Recruit_fair record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table recruit_fair
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(Recruit_fair record);
    List<Recruit_fair> selectAll();
}