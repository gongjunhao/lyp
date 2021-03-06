package com.lyp360.action.admin;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lyp360.entity.CertificateCard;
import com.lyp360.entity.SystemUser;
import com.lyp360.service.ICertificateCardService;
import com.lyp360.utils.PoiUtils;
import org.apache.commons.lang3.RandomStringUtils;
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
import java.io.PrintWriter;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by junhao on 2016/4/30.
 * CertificateCardAction
 */
@Controller
@RequestMapping(value = "/admin/certificateCard")
public class CertificateCardAction {

    private Logger log = Logger.getLogger(getClass());

    @Autowired
    private ICertificateCardService cardService;

    @RequestMapping(value = "/producePage", method = RequestMethod.GET)
    public String produceCode(){
        return "/admin/certificateCard/produceCode";
    }

    @ResponseBody
    @RequestMapping(value = "/produce", method = RequestMethod.POST)
    public Object produce(@RequestBody String cardStr, HttpServletRequest request){
        JSONObject obj = new JSONObject();
        JSONArray array = new JSONArray();
        List<String> existCode = new ArrayList<>();
        JSONObject jsonObject = JSON.parseObject(cardStr);
        Date nowDate = new Date();
        String rule = jsonObject.getString("cardNum");
        String between = rule.substring(rule.indexOf("{"), rule.indexOf("}")+1);
        String[] split = between.replace("{", "").replace("}","").split("-");
        StringBuilder sb = new StringBuilder();
        for (int i=0; i<split[0].length(); i++){
            sb.append("0");
        }
        String mark = jsonObject.getString("mark");
        SystemUser user = (SystemUser)request.getSession().getAttribute("user");
        for (int i=Integer.parseInt(split[0]); i<=Integer.parseInt(split[1]); i++) {
            String code = rule.replace(between, new DecimalFormat(sb.toString()).format(i));
            CertificateCard temp = new CertificateCard();
            temp.setCode(code);
            List<CertificateCard> cards = cardService.selectCertificateCardList(temp);
            if(cards != null && cards.size() > 0) {
                existCode.add(code);
            } else {
                CertificateCard card = new CertificateCard();
                card.setCreateTime(nowDate);
                card.setStatus("new");
                card.setCreateUserId(user.getId());
                card.setCode(code);
                card.setMark(mark);
                array.add(card);
            }
        }
        obj.put("cards", array);
        obj.put("existCode", existCode);
        return obj;
    }

    @RequestMapping(value = "/exportExcel", method = RequestMethod.GET)
    public void exportExcel(HttpServletRequest request, HttpServletResponse response) {
        ServletOutputStream out = null;
        try {
            out = response.getOutputStream();
            String rule = request.getParameter("cardNum");
            String between = rule.substring(rule.indexOf("{"), rule.indexOf("}")+1);
            String[] split = between.replace("{", "").replace("}","").split("-");
            StringBuilder sb = new StringBuilder();
            List<String> codes = new ArrayList<>();
            for (int i=0; i<split[0].length(); i++){
                sb.append("0");
            }
            for (int i=Integer.parseInt(split[0]); i<=Integer.parseInt(split[1]); i++) {
                String code = rule.replace(between, new DecimalFormat(sb.toString()).format(i));
                CertificateCard temp = new CertificateCard();
                temp.setCode(code);
                List<CertificateCard> cards = cardService.selectCertificateCardList(temp);
                if(cards != null && cards.size() > 0) {

                } else {
                    codes.add(code);
                }
            }
            if(codes.size() > 0){
                HSSFWorkbook workbook = PoiUtils.writeCodeToSheet(codes);
                String fileName = new String(("激活码").getBytes(), "ISO8859_1");
                response.setContentType("application/octet-stream");
                response.setCharacterEncoding("UTF-8");
                response.setHeader("Content-Disposition", "attachment; filename="+fileName+".xls");
                workbook.write(out);
                out.flush();
                out.close();
            }
        } catch (IOException e) {
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
    @RequestMapping(value = "/saveProduce", method = RequestMethod.POST)
    public Object saveProduce(@RequestBody String cards) {
        List<CertificateCard> certificateCards = JSONArray.parseArray(cards, CertificateCard.class);
        for (int i=0; i<certificateCards.size(); i++){
            CertificateCard card = certificateCards.get(i);
            cardService.insert(card);
        }
        return  null;
    }

    @RequestMapping(value = "/listPage", method = RequestMethod.GET)
    public String listPage(){
        return "/admin/certificateCard/list";
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
        CertificateCard card = data.getObject("card", CertificateCard.class);
        PageHelper.startPage(pageNum, pageSize, orderBy + " " + sort);
        List<CertificateCard> list = cardService.selectCertificateCardList(card);
        PageInfo page = new PageInfo(list);
        return page;
    }

    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public void update(@RequestBody String card, HttpServletRequest request) {
        try {
            SystemUser user = (SystemUser)request.getSession().getAttribute("user");
            CertificateCard dict = JSON.parseObject(card, CertificateCard.class);
            if(dict.getId() != null){
                cardService.updateByPrimaryKeySelective(dict);
            } else {
                dict.setCreateUserId(user.getId());
                dict.setCreateTime(new Date());
                dict.setStatus("new");
                cardService.insert(dict);
            }
        } catch (Exception e){
            e.printStackTrace();
        }
    }
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    @RequestMapping(value = "/del", method = RequestMethod.POST)
    public void del(@RequestBody String card, HttpServletRequest request) {
        try {
            CertificateCard dict = JSON.parseObject(card, CertificateCard.class);
            if(dict.getId() != null){
                dict.setStatus("cardDel");
                cardService.updateByPrimaryKeySelective(dict);
            }
        } catch (Exception e){
            e.printStackTrace();
        }
    }
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    @RequestMapping(value = "/delAll", method = RequestMethod.POST)
    public void delAll(@RequestBody String idStr, HttpServletRequest request) {
        try {
            List<Long> ids = JSONArray.parseArray(idStr, Long.class);
            CertificateCard card = new CertificateCard();
            for(Long id : ids) {
                card.setId(id);
                card.setStatus("cardDel");
                cardService.updateByPrimaryKeySelective(card);
            }
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
