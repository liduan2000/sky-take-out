package com.sky.service.impl;

import com.sky.mapper.OrderMapper;
import com.sky.service.ReportService;
import com.sky.vo.TurnoverReportVO;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.sql.Date;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@Service
public class ReportServiceImpl implements ReportService {
    @Autowired
    private OrderMapper orderMapper;

    /**
     * 营业额统计
     *
     * @param begin
     * @param end
     * @return
     */
    public TurnoverReportVO getTurnoverStatistics(LocalDate begin, LocalDate end) {
        List<LocalDate> dateList = new ArrayList<>();
        LocalDate time = begin;

        dateList.add(time);
        while (!time.equals(end)) {
            time = time.plusDays(1);
            dateList.add(time);
        }
        String dateString = StringUtils.join(dateList, ",");

        List<Double> turnoverList = new ArrayList<>(Collections.nCopies(dateList.size(), 0.0));

        List<Map<String, Object>> dailySumList = orderMapper.getDailySumByRange(begin, end.plusDays(1));
        for (Map<String, Object> map : dailySumList) {
            Date date = (Date) map.get("orderDate");
            long between = ChronoUnit.DAYS.between(begin, date.toLocalDate());
            BigDecimal dailySum = (BigDecimal) map.get("dailySum");
            turnoverList.set((int) between, dailySum.doubleValue());
        }
        String turnoverString = StringUtils.join(turnoverList, ",");
        return TurnoverReportVO.builder()
                .dateList(dateString)
                .turnoverList(turnoverString)
                .build();
    }
}
