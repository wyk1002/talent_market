package biubiu.Service;

import biubiu.Model.Invoice;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;


public interface InvoiceService {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table invoice
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table invoice
     *
     * @mbggenerated
     */
    int insert(Invoice record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table invoice
     *
     * @mbggenerated
     */
    int insertSelective(Invoice record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table invoice
     *
     * @mbggenerated
     */
    Invoice selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table invoice
     *
     * @mbggenerated
     */
    int updateByPrimaryKeySelective(Invoice record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table invoice
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(Invoice record);
    List<Invoice> selectAll();
}