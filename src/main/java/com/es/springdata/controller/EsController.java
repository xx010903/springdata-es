package com.es.springdata.controller;

import com.es.springdata.entity.request.EsRequest;
import com.es.springdata.service.EsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@EnableAutoConfiguration
@RequestMapping("/es")
public class EsController {

    @Autowired
    private EsService esService;

    /**
     * http://127.0.0.1:8080/es/createIndex
     * 创建索引
     *
     * @param request
     * @return
     */
    @RequestMapping("/createIndex")
    @ResponseBody
    public String createIndex(EsRequest request) {
        return esService.createIndex(request);
    }

    /**
     * 删除记录
     * http://127.0.0.1:8080/es/delete?indexName=es_test_user&id=1
     *
     * @return
     */
    @RequestMapping("/delete")
    @ResponseBody
    public String delete(EsRequest request) {
        return esService.deleteById(request);
    }

    /**
     * 获取数据
     * http://127.0.0.1:8080/es/getData?indexName=es_test_user&id=1
     *
     * @param request
     * @return
     */
    @RequestMapping("/getData")
    @ResponseBody
    public String getData(EsRequest request) {
        return esService.getById(request);
    }

    /**
     * 查询数据
     * 短语匹配查询
     * http://127.0.0.1:8080/es/queryMatchData?indexName=es_test_user&queryField=name&queryText=aaa
     *
     * @return
     */
    @RequestMapping("/queryMatchData")
    @ResponseBody
    public String queryMatchData(EsRequest request) {
        return esService.queryMatch(request);
    }

    /**
     * 通配符查询数据
     * 通配符查询 ?用来匹配1个任意字符，*用来匹配零个或者多个字符
     * http://127.0.0.1:8080/es/queryWildcardData?indexName=es_test_user&queryField=name&queryText=aaa
     *
     * @return
     */
    @RequestMapping("/queryWildcardData")
    @ResponseBody
    public String queryWildcardData(EsRequest request) {
        return esService.queryWildcard(request);
    }

    /**
     * 正则查询
     * http://127.0.0.1:8080/es/queryRegexpData?indexName=es_test_user&queryField=name&queryText=*d*
     *
     * @return
     */
    @RequestMapping("/queryRegexpData")
    @ResponseBody
    public String queryRegexpData(EsRequest request) {
        return esService.queryRegexp(request);
    }

    /**
     * 查询范围数据
     * http://127.0.0.1:8080/es/queryRegexpData?indexName=es_test_user&queryField=age&fromQueryText=1&toQueryText=2
     *
     * @return
     */
    @RequestMapping("/queryIntRangeData")
    @ResponseBody
    public String queryIntRangeData(EsRequest request) {
        return esService.queryIntRange(request);
    }


    /**
     * 查询分页
     *
     * @param request 第几条记录开始
     *
     * 第1页 ：http://127.0.0.1:8080/es/indexName=es_test_user&queryPage?currentPage=1&pageSize=2
     * 第2页 ：http://127.0.0.1:8080/es/indexName=es_test_user&queryPage?currentPage=2&pageSize=2
     * @return
     */
    @RequestMapping("/queryPage")
    @ResponseBody
    public String queryPage(EsRequest request) {
        return esService.queryPage(request);
    }

    /**
     * 更新数据
     * @return
     */
    /*@RequestMapping("/update")
    @ResponseBody
    public String update(String indexName, String id) {
        if(StringUtils.isNotBlank(id)) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("id", id);
            jsonObject.put("age", 31);
            jsonObject.put("name", "修改");
            jsonObject.put("date", new Date());
            ElasticsearchUtil.updateDataById(jsonObject, indexName, esType, id);
            return "id=" + id;
        }
        else{
            return "id为空";
        }
    }*/

    /**
     * 插入记录
     * @return
     */
    /*@RequestMapping("/insertJson")
    @ResponseBody
    public String insertJson() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("id", DateUtil.formatDate(new Date()));
        jsonObject.put("age", 25);
        jsonObject.put("name", "j-" + new Random(100).nextInt());
        jsonObject.put("date", new Date());
        String id = ElasticsearchUtil.addData(jsonObject, indexName, esType, jsonObject.getString("id"));
        return id;
    }*/

    /**
     * 插入记录
     * @return
     */
    /*@RequestMapping("/insertModel")
    @ResponseBody
    public String insertModel() {
        EsModel esModel = new EsModel();
        esModel.setId(DateUtil.formatDate(new Date()));
        esModel.setName("m-" + new Random(100).nextInt());
        esModel.setAge(30);
        esModel.setDate(new Date());
        JSONObject jsonObject = (JSONObject) JSONObject.toJSON(esModel);
        String id = ElasticsearchUtil.addData(jsonObject, indexName, esType, jsonObject.getString("id"));
        return id;
    }*/

}
