package com.lyp360.action.admin;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lyp360.entity.CertificateCard;
import com.lyp360.entity.Dictionary;
import com.lyp360.entity.Insurance;
import com.lyp360.entity.InsuranceAttach;
import com.lyp360.service.ICertificateCardService;
import com.lyp360.service.IDictionaryService;
import com.lyp360.service.IInsuranceAttachService;
import com.lyp360.service.IInsuranceService;
import com.lyp360.utils.Constant;
import com.lyp360.utils.PoiUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateUtils;
import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.*;

/**
 * Created by GongJunhao on 2016/4/27.
 * InsuranceController
 */
@Controller
@RequestMapping(value = "/admin/insurance")
public class InsuranceController {

    private Logger log = Logger.getLogger(getClass());

    @Autowired
    private IInsuranceService insuranceService;

    @Autowired
    private ICertificateCardService cardService;

    @Autowired
    private IInsuranceAttachService attachService;

    @Autowired
    private IDictionaryService dictionaryService;


    @RequestMapping(value = "/listPage", method = RequestMethod.GET)
    public String listPage(HttpServletRequest request) {
        return "/admin/insurance/list";
    }

    @ResponseBody
    @RequestMapping(value = "/list", method = RequestMethod.POST)
    public PageInfo list(@RequestBody String json) {
        log.debug(json);
        JSONObject data = JSON.parseObject(json);
        int pageNum = data.getInteger("pageNum");
        int pageSize = data.getInteger("pageSize");
        String orderBy = data.getString("orderBy");
        String sort = data.getString("sort");
        Insurance insurance = data.getObject("insurance", Insurance.class);
        //获取第1页，10条内容，默认查询总数count
        PageHelper.startPage(pageNum, pageSize, orderBy + " " + sort);
        //紧跟着的第一个select方法会被分页
        List<Insurance> list = insuranceService.selectInsuranceList(insurance);
        PageInfo page = new PageInfo(list);
        return page;
    }

    @RequestMapping(value = "/exportExcel", method = RequestMethod.GET)
    public void exportExcel(HttpServletRequest request, HttpServletResponse response) {
        ServletOutputStream out = null;
        try {
            List<Dictionary> dictionaries = dictionaryService.selectDictionaryList(null);
            Map<String, String> dicMap = new HashMap<>();
            for(Dictionary dic : dictionaries){
                dicMap.put(dic.getCode(), dic.getName());
            }
            out = response.getOutputStream();
            String startTime = request.getParameter("startTime");
            String endTime = request.getParameter("endTime");
            String json = request.getParameter("insurance");
            Insurance insurance = JSON.parseObject(json, Insurance.class);
            Date startDate = null;
            Date endDate = null;
            String[] patterns = {"yyyy-MM-dd HH:mm:ss"};
            if(StringUtils.isNotBlank(startTime) && !"undefined".equals(startTime))
                startDate = DateUtils.parseDate(startTime, patterns);
            if(StringUtils.isNotBlank(endTime) && !"undefined".equals(endTime))
                endDate = DateUtils.parseDate(endTime, patterns);
            List<Insurance> list = insuranceService.selectInsuranceListByTime(insurance, startDate, endDate);
            if(list != null && list.size() > 0 ){
                HSSFWorkbook workbook = PoiUtils.writeInstanceToSheet(list, dicMap);
                String fileName = new String(("注册激活表").getBytes(), "ISO8859_1");
                response.setContentType("application/octet-stream");
                response.setCharacterEncoding("UTF-8");
                response.setHeader("Content-Disposition", "attachment; filename="+fileName+".xls");
                workbook.write(out);
                out.flush();
                out.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            out = null;
        }
    }

    @ResponseBody
    @RequestMapping(value = "/getAttaches/{id}", method = RequestMethod.GET)
    public JSONObject list(@PathVariable Long id) {
        JSONObject object = new JSONObject();
        List<InsuranceAttach> attaches = attachService.selectAttachesByInsuranceId(id);
        for(InsuranceAttach attach: attaches){
            attach.setSavePath(Constant.resPath + attach.getSavePath().substring(attach.getSavePath().indexOf(":")+9));
        }
        object.put("attaches", attaches);
        return object;
    }
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public void update(@RequestBody String user) {
        try {
            Insurance insurance = JSON.parseObject(user, Insurance.class);
            if(insurance.getId() != null){
                CertificateCard card = new CertificateCard();
                card.setCode(insurance.getCertificateCode());
                if(insurance.getStatus().equals("pass")){
                    List<CertificateCard> validCard = cardService.selectCertificateCardList(card);
                    for(CertificateCard temp : validCard){
                        temp.setStatus("used");
                        cardService.updateByPrimaryKey(temp);
                    }
                } else if (insurance.getStatus().equals("notPass")){
                    List<CertificateCard> validCard = cardService.selectCertificateCardList(card);
                    for(CertificateCard temp : validCard){
                        temp.setStatus("new");
                        cardService.updateByPrimaryKey(temp);
                    }
                } else if (insurance.getStatus().equals("del")){

                }
                insuranceService.updateByPrimaryKeySelective(insurance);
            } else {
                insurance.setCreateTime(new Date());
                insurance.setStatus("0");
                insuranceService.insert(insurance);
            }
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    @RequestMapping(value = "/del", method = RequestMethod.POST)
    public void del(@RequestBody String insurance) {
        try {
            Insurance curRole = JSON.parseObject(insurance, Insurance.class);
            curRole.setStatus("del");
            insuranceService.updateByPrimaryKeySelective(curRole);
        } catch (Exception e){
            e.printStackTrace();
        }
    }

}
