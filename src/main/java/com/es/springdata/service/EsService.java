package com.es.springdata.service;

import com.es.springdata.entity.request.EsRequest;

public interface EsService {
    String queryRegexp(EsRequest request);

    String deleteById(EsRequest request);

    String createIndex(EsRequest request);

    String getById(EsRequest request);

    String queryMatch(EsRequest request);

    String queryWildcard(EsRequest request);

    String queryPage(EsRequest request);

    String queryIntRange(EsRequest request);
}
