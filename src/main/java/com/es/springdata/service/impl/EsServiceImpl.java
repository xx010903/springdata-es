package com.es.springdata.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.es.springdata.entity.EsPage;
import com.es.springdata.entity.request.EsRequest;
import com.es.springdata.service.EsService;
import com.es.springdata.utils.ElasticsearchUtil;
import org.apache.commons.lang3.StringUtils;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class EsServiceImpl implements EsService {

    @Override
    public String createIndex(EsRequest request) {
        String indexName = request.getIndexName();

        if (!ElasticsearchUtil.isIndexExist(indexName)) {
            ElasticsearchUtil.createIndex(indexName);
        } else {
            return "索引已经存在";
        }
        return "索引创建成功";
    }

    @Override
    public String deleteById(EsRequest request) {
        String id = request.getId();
        String esType = request.getEsType();
        String indexName = request.getIndexName();
        if (StringUtils.isNotBlank(id)) {
            ElasticsearchUtil.deleteDataById(indexName, esType, id);
            return "删除id=" + id;
        } else {
            return "没有id";
        }
    }

    @Override
    public String getById(EsRequest request) {
        String id = request.getId();
        String esType = request.getEsType();
        String indexName = request.getIndexName();

        if (StringUtils.isNotBlank(id)) {
            Map<String, Object> map = ElasticsearchUtil.searchDataById(indexName, esType, id, request.getDisplayFields());
            return JSONObject.toJSONString(map);
        } else {
            return "id为空";
        }
    }

    @Override
    public String queryMatch(EsRequest request) {
        String esType = request.getEsType();
        String indexName = request.getIndexName();

        Boolean matchPhrase = request.getMatchPhrase();
        String queryField = request.getQueryField();
        String queryText = request.getQueryText();

        BoolQueryBuilder boolQuery = QueryBuilders.boolQuery();
        //作为整个短语，不分词
        if (matchPhrase) {
            boolQuery.must(QueryBuilders.matchPhraseQuery(queryField, queryText));
        } else {
            boolQuery.must(QueryBuilders.matchQuery(queryField, queryText));
        }
        List<Map<String, Object>> list = ElasticsearchUtil.searchListData(indexName, esType, boolQuery, request.getSize(), request.getDisplayFields(), request.getSortField(), request.getHighlightField());
        return JSONObject.toJSONString(list);
    }

    @Override
    public String queryWildcard(EsRequest request) {
        String esType = request.getEsType();
        String indexName = request.getIndexName();

        QueryBuilder queryBuilder = QueryBuilders.wildcardQuery(request.getQueryField(), request.getQueryText());
        List<Map<String, Object>> list = ElasticsearchUtil.searchListData(indexName, esType, queryBuilder, request.getSize(), request.getDisplayFields(), request.getSortField(), request.getHighlightField());
        return JSONObject.toJSONString(list);
    }

    @Override
    public String queryPage(EsRequest request) {
        String esType = request.getEsType();
        String indexName = request.getIndexName();

        String currentPage = request.getCurrentPage();
        String pageSize = request.getPageSize();

        if (StringUtils.isNotBlank(currentPage) && StringUtils.isNotBlank(pageSize)) {
            BoolQueryBuilder boolQuery = QueryBuilders.boolQuery();
            EsPage list = ElasticsearchUtil.searchDataPage(indexName, esType, Integer.parseInt(currentPage), Integer.parseInt(pageSize), boolQuery, request.getDisplayFields(), request.getSortField(), request.getHighlightField());
            return JSONObject.toJSONString(list);
        } else {
            return "startPage或者pageSize缺失";
        }
    }

    @Override
    public String queryRegexp(EsRequest request) {
        String indexName = request.getIndexName();
        String esType = request.getEsType();

        QueryBuilder queryBuilder = QueryBuilders.regexpQuery("name.keyword", "j--[0-9]{1,11}");
        List<Map<String, Object>> list = ElasticsearchUtil.searchListData(indexName, esType, queryBuilder, request.getSize(), request.getDisplayFields(), request.getSortField(), request.getHighlightField());
        return JSONObject.toJSONString(list);
    }


    @Override
    public String queryIntRange(EsRequest request) {
        String indexName = request.getIndexName();
        String esType = request.getEsType();

        BoolQueryBuilder boolQuery = QueryBuilders.boolQuery();
        boolQuery.must(QueryBuilders.rangeQuery(request.getQueryField()).from(request.getFromQueryText())
                .to(request.getToQueryText()));
        List<Map<String, Object>> list = ElasticsearchUtil.searchListData(indexName, esType, boolQuery, request.getSize(), request.getDisplayFields(), request.getSortField(), request.getHighlightField());
        return JSONObject.toJSONString(list);
    }

}
