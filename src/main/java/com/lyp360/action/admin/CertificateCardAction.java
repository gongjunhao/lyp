package com.lyp360.action.admin;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lyp360.entity.CertificateCard;
import com.lyp360.entity.SystemUser;
import com.lyp360.service.ICertificateCardService;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.text.DecimalFormat;
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
            CertificateCard card = new CertificateCard();
            card.setCreateTime(nowDate);
            card.setStatus("0");
            card.setCreateUserId(user.getId());
            card.setCode(rule.replace(between, new DecimalFormat(sb.toString()).format(i)));
            card.setMark(mark);
            array.add(card);
        }
        obj.put("cards", array);
        return obj;
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
                dict.setStatus("0");
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
                dict.setStatus("del");
                cardService.updateByPrimaryKeySelective(dict);
            }
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
