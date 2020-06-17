package com.yonyou.guarantee.controller;

import com.yonyou.guarantee.service.TgZbsService;
import com.yonyou.guarantee.vo.PageRespVO;
import com.yonyou.guarantee.vo.RestResultVO;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/rest/zbs")
public class TgZbsRestController {

    @Resource
    private TgZbsService tgZbsService;

    @RequestMapping(value = "/cust/list", method = RequestMethod.GET)
    public Object getCustomerList(String custName, Integer currPage, Integer pageSize) {
        currPage = null == currPage ? 1 : currPage;
        pageSize = null == pageSize ? 10 : pageSize;
        List<Map<String, Object>> dataList = tgZbsService.getCustomerList(custName, currPage, pageSize);
        int total = tgZbsService.getCustomerCount(custName);
        PageRespVO pageRespVO = new PageRespVO.Builder().total(total).addList(dataList).create();
        return RestResultVO.success(pageRespVO);
    }

    @RequestMapping(value = "/std/list", method = RequestMethod.GET)
    public Object getStandardList() {
        return RestResultVO.success(tgZbsService.getStandardList());
    }

    @RequestMapping(value = "/steel/list", method = RequestMethod.GET)
    public Object getSteelList() {
        return RestResultVO.success(tgZbsService.getSteelList());
    }

    @RequestMapping(value = "/furnace/list", method = RequestMethod.GET)
    public Object getFurnaceList(String searchText, Integer currPage, Integer pageSize) {
        currPage = null == currPage ? 1 : currPage;
        pageSize = null == pageSize ? 10 : pageSize;
        List<Map<String, Object>> dataList = tgZbsService.getFurnaceList(searchText, currPage, pageSize);
        int total = tgZbsService.getFurnaceCount(searchText);
        PageRespVO pageRespVO = new PageRespVO.Builder().total(total).addList(dataList).create();
        return RestResultVO.success(pageRespVO);
    }

    @RequestMapping(value = "/batch/list", method = RequestMethod.GET)
    public Object getBatchList(String searchText, String steelGrade, Integer currPage, Integer pageSize) {
        currPage = null == currPage ? 1 : currPage;
        pageSize = null == pageSize ? 10 : pageSize;
        List<Map<String, Object>> dataList = tgZbsService.getBatchList(searchText, steelGrade, currPage, pageSize);
        int total = tgZbsService.getBatchListCount(searchText, steelGrade);
        PageRespVO pageRespVO = new PageRespVO.Builder().total(total).addList(dataList).create();
        return RestResultVO.success(pageRespVO);
    }

    @RequestMapping(value = "/tem/his", method = RequestMethod.GET)
    public Object getChemicals(String cMFNo, String cStellGrade, String cCusName, String iSteelType) {
        return RestResultVO.success(tgZbsService.getTemHistory(cMFNo, cStellGrade, cCusName, iSteelType));
    }

    @RequestMapping(value = "/tem/list", method = RequestMethod.GET)
    public Object getTemByType(String iSteelType, String custName, String startDate, String endDate, String searchText, Integer currPage, Integer pageSize) {
        currPage = null == currPage ? 1 : currPage;
        pageSize = null == pageSize ? 10 : pageSize;
        List<Map<String, Object>> dataList = tgZbsService.getTemByType(iSteelType, custName, startDate, endDate, searchText, currPage, pageSize);
        int total = tgZbsService.getTemByTypeCount(iSteelType, custName, startDate, endDate, searchText);
        PageRespVO pageRespVO = new PageRespVO.Builder().total(total).addList(dataList).create();
        return RestResultVO.success(pageRespVO);
    }

    @RequestMapping(value = "/multi/list", method = RequestMethod.GET)
    public Object getMultiByType(String iSteelType, String custName, String startDate, String endDate, String searchText, Integer currPage, Integer pageSize) {
        currPage = null == currPage ? 1 : currPage;
        pageSize = null == pageSize ? 10 : pageSize;
        List<Map<String, Object>> dataList = tgZbsService.getMultiByType(iSteelType, custName, startDate, endDate, searchText, currPage, pageSize);
        int total = tgZbsService.getMultiByTypeCount(iSteelType, custName, startDate, endDate, searchText);
        PageRespVO pageRespVO = new PageRespVO.Builder().total(total).addList(dataList).create();
        return RestResultVO.success(pageRespVO);
    }

    @RequestMapping(value = "/tem/id", method = RequestMethod.GET)
    public Object getTemById(String id) {
        return RestResultVO.success(tgZbsService.getTemById(id));
    }

    @RequestMapping(value = "/multi/id", method = RequestMethod.GET)
    public Object getMultiById(String id) {
        return RestResultVO.success(tgZbsService.getMultiById(id));
    }

    @RequestMapping(value = "/tem/seq", method = RequestMethod.GET)
    public Object getNextTemNum() {
        return RestResultVO.success(tgZbsService.getNextTemNum());
    }

    @RequestMapping(value = "/qt/list", method = RequestMethod.GET)
    public Object getQtList(String searchText, Integer currPage, Integer pageSize) {
        currPage = null == currPage ? 1 : currPage;
        pageSize = null == pageSize ? 10 : pageSize;
        List<Map<String, Object>> dataList = tgZbsService.getQtList(searchText, currPage, pageSize);
        int total = tgZbsService.getQtListCount(searchText);
        PageRespVO pageRespVO = new PageRespVO.Builder().total(total).addList(dataList).create();
        return RestResultVO.success(pageRespVO);
    }

    @RequestMapping(value = "/tem/save", method = RequestMethod.POST)
    public Object temSave(String temJson, String nurbsJosn) {
        return RestResultVO.success(tgZbsService.temSave(temJson, nurbsJosn));
    }

    @RequestMapping(value = "/multi/save", method = RequestMethod.POST)
    public Object batchSave(String headJson, String bodyJson, String refJson) {
        return RestResultVO.success(tgZbsService.multiSave(headJson, bodyJson, refJson));
    }

    @RequestMapping(value = "/elem/limits", method = RequestMethod.GET)
    public Object getQtList(String cMFNo) {
        return RestResultVO.success(tgZbsService.getElementLimits(cMFNo));
    }


    @RequestMapping(value = "/batch/his", method = RequestMethod.GET)
    public Object batchHistory(String cMFNo, String cStellGrade, String cCusName, String iSteelType) {
        return RestResultVO.success(tgZbsService.getBathHistory(cMFNo, cStellGrade, cCusName, iSteelType));
    }

    @RequestMapping(value = "/pdf/gen/single", method = RequestMethod.GET)
    public Object genSinglePdf(String id) throws IOException {
        return RestResultVO.success(tgZbsService.genSinglePdf(id));
    }

    @RequestMapping(value = "/pdf/gen/multi", method = RequestMethod.GET)
    public Object genMultiPdf(String id) throws IOException {
        return RestResultVO.success(tgZbsService.genMultiPdf(id));
    }
}
