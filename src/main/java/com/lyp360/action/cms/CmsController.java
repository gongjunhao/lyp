package com.lyp360.action.cms;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.lyp360.entity.CertificateCard;
import com.lyp360.entity.Dictionary;
import com.lyp360.entity.Insurance;
import com.lyp360.entity.InsuranceAttach;
import com.lyp360.service.ICertificateCardService;
import com.lyp360.service.IDictionaryService;
import com.lyp360.service.IInsuranceAttachService;
import com.lyp360.service.IInsuranceService;
import com.lyp360.utils.LypFileUtils;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * Created by junhao on 2016/4/24.
 *
 * CmsController
 */
@Controller
@RequestMapping(value = "/cms")
public class CmsController {

    private Logger log = Logger.getLogger(getClass());

    @Autowired
    private IDictionaryService dictionaryService;

    @Autowired
    private ICertificateCardService cardService;

    @Autowired
    private IInsuranceService insuranceService;

    @Autowired
    private IInsuranceAttachService insuranceAttachService;

    @ResponseBody
    @RequestMapping(value = "/childNodes/{code}", method = RequestMethod.GET)
    public Object getChildNodes(HttpServletRequest request, @PathVariable String code){
        Dictionary parentNode = dictionaryService.findDictByCode(code);
        List<Dictionary> nodes = null;
        if(parentNode != null){
            nodes = dictionaryService.getChildNodes(parentNode.getId());
        }
        return nodes;
    }

    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    @RequestMapping(value = "/validCardCode", method = RequestMethod.POST)
    public JSONObject validLoginName(@RequestBody String json) {
        try {
            log.debug(json);
            String code = JSON.parseObject(json).getString("value");
            CertificateCard card = new CertificateCard();
            card.setCode(code);
            card.setStatus("0");
            List<CertificateCard> cards = cardService.findValidCard(card);
            JSONObject object = new JSONObject();
            if(cards != null && cards.size() == 1){
                object.put("isValid", true);
                object.put("value", code);
            } else {
                object.put("isValid", false);
                object.put("value", code);
            }
            return object;
        } catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @RequestMapping(value = "/card/active", method = RequestMethod.GET)
    public String activePage() {
        return "cms/card/active";
    }

    @RequestMapping(value = "/card/query", method = RequestMethod.GET)
    public String queryPage() {
        return "cms/card/query";
    }

    @RequestMapping(value = "/card/agreement", method = RequestMethod.GET)
    public String agreementPage() {
        return "cms/card/agreement";
    }

    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    @RequestMapping(value = "/card/active", method = RequestMethod.POST)
    public String cardActive(@RequestParam("file") List<MultipartFile> files, @RequestParam("card") String card) {
        log.debug(card);
        try {
            Insurance insurance = JSON.parseObject(card, Insurance.class);
            insurance.setStatus("0");
            insurance.setCreateTime(new Date());
            long insuranceId = insuranceService.insert(insurance);
            for (MultipartFile file : files){
                log.debug(file.getOriginalFilename());
                InsuranceAttach attach = new InsuranceAttach();
                String newName = UUID.randomUUID().toString().replaceAll("-", "");
                String type = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
                attach.setAttachName(newName + type);
                attach.setAttachSize(String.valueOf(file.getSize()));
                attach.setInsuranceId(insuranceId);
                attach.setSavePath(LypFileUtils.getInstance().generatSavePath()+attach.getAttachName());
                FileUtils.copyInputStreamToFile(file.getInputStream(), new File(LypFileUtils.getInstance().generatSavePath()+attach.getAttachName()));
                insuranceAttachService.insert(attach);
            }
        } catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

}
