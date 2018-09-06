package com.lihaogn.spark.web.com.lihaogn.spark.web.dao;

import com.lihaogn.spark.web.com.lihaogn.spark.web.domain.CourseClickCount;
import com.lihaogn.spark.web.com.lihaogn.spark.web.utils.HBaseUtils;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 课程访问数量数据访问
 */
@Component
public class CourseClickCountDAO {

    /**
     * 根据日期查询
     *
     * @param day
     * @return
     * @throws Exception
     */
    public List<CourseClickCount> query(String day) throws Exception {
        List<CourseClickCount> list = new ArrayList<>();

        // 去HBase表中根据day获取课程访问量
        Map<String, Long> map = HBaseUtils.getInstance().query("course_clickcount", "20180906");

        for (Map.Entry<String, Long> entry : map.entrySet()) {
            CourseClickCount model = new CourseClickCount();
            model.setName(entry.getKey());
            model.setValue(entry.getValue());

            list.add(model);

        }
        return list;
    }

    public static void main(String[] args) throws Exception {

        CourseClickCountDAO dao = new CourseClickCountDAO();
        List<CourseClickCount> list = dao.query("20180906");
        for (CourseClickCount model : list) {
            System.out.println(model.getName() + " : " + model.getValue());
        }

    }

}
