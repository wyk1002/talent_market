package biubiu.Service;

import biubiu.Model.Firm;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;


public interface FirmService {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table firm
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table firm
     *
     * @mbggenerated
     */
    int insert(Firm record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table firm
     *
     * @mbggenerated
     */
    int insertSelective(Firm record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table firm
     *
     * @mbggenerated
     */
    Firm selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table firm
     *
     * @mbggenerated
     */
    int updateByPrimaryKeySelective(Firm record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table firm
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(Firm record);
    List<Firm> selectAll();
}