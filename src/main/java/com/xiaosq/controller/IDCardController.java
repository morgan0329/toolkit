package com.xiaosq.controller;

import com.google.gson.Gson;
import com.xiaosq.result.IDCardResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

@RestController
@RequestMapping("idcard")
public class IDCardController {

    private Gson gson = new Gson();

    @GetMapping("/makeupDate")
    public String makeupDate(
            @RequestParam(value = "cardNo", defaultValue = "") String cardNo) {

        //测试数据  "1311261988****0685"

        List<String> idCardList = makeupIDCard(cardNo);

        List<String> validIDCardList = new ArrayList<>();
        for (String id : idCardList) {
            if (IDCardValidation.isIDCard(id)) {
                validIDCardList.add(id);
            }
        }

        IDCardResult result = new IDCardResult();
        result.setNumber(validIDCardList.size());
        result.setIdCardList(validIDCardList);
        return gson.toJson(result);
    }

    @GetMapping("/makeupYear")
    public String makeupYear(@RequestParam(value = "cardNo", defaultValue = "") String cardNo) {
        List<String> idCardList = makeupIDCardForYear(cardNo);

        List<String> validIDCardList = new ArrayList<>();
        for (String id : idCardList) {
            if (IDCardValidation.isIDCard(id)) {
                validIDCardList.add(id);
            }
        }

        IDCardResult result = new IDCardResult();
        result.setNumber(validIDCardList.size());
        result.setIdCardList(validIDCardList);
        return gson.toJson(result);
    }

    private List<String> makeupIDCardForYear(String oldCertNo) {

        List<String> idCardList = new ArrayList<>();

        if (oldCertNo != null && oldCertNo.contains("****")) {
            for (int i = 1950; i < 2000; i++) {
                String newCertNo = oldCertNo.replace("****", String.valueOf(i));
                idCardList.add(newCertNo);
            }
        }
        return idCardList;
    }


    private List<String> makeupIDCard(String oldCertNo) {

        List<String> idCardList = new ArrayList<>();

        if (oldCertNo != null && oldCertNo.contains("****")) {
            String year = oldCertNo.substring(6, 10);
            Calendar calendar = Calendar.getInstance();

            calendar.set(Calendar.YEAR, Integer.parseInt(year));

            int minDay = 0;
            int maxDay = 0;
            for (int loop = 0; loop < 12; loop++) {

                calendar.set(Calendar.MONTH, 1);
                calendar.set(Calendar.DAY_OF_YEAR, 1);

                minDay = calendar.getActualMinimum(Calendar.DAY_OF_MONTH);
                maxDay = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);

                for (int i = minDay; i <= maxDay; i++) {
                    calendar.set(Integer.parseInt(year), loop, i);
                    SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");
                    idCardList.add(oldCertNo.substring(0, 6) + df.format(calendar.getTime()) + oldCertNo.substring(14, 18));
                }
            }
        }
        return idCardList;
    }


}
