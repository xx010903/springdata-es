package com.es.springdata.entity.request;

public class EsRequest extends PageRequest{

    private String indexName;
    private String esType = "type";
    private String id;
    private Integer size;

    /**
     * 展示字段
     */
    private String displayFields;
    /**
     * 排序字段
     */
    private String sortField;
    /**
     * 是否作为整个短语来匹配
     */
    private Boolean matchPhrase = true;
    /**
     * 高亮字段
     */
    private String highlightField;
    /**
     * 需要匹配的字段
     */
    private String queryField;
    /**
     * 需要匹配的值
     * @return
     */
    private String queryText;
    /**
     * 范围查询需要匹配的值
     * @return
     */
    private String fromQueryText;
    private String toQueryText;

    public String getIndexName() {
        return indexName;
    }

    public void setIndexName(String indexName) {
        this.indexName = indexName;
    }

    public String getEsType() {
        return esType;
    }

    public void setEsType(String esType) {
        this.esType = esType;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public String getDisplayFields() {
        return displayFields;
    }

    public void setDisplayFields(String displayFields) {
        this.displayFields = displayFields;
    }

    public String getSortField() {
        return sortField;
    }

    public void setSortField(String sortField) {
        this.sortField = sortField;
    }

    public Boolean getMatchPhrase() {
        return matchPhrase;
    }

    public void setMatchPhrase(Boolean matchPhrase) {
        this.matchPhrase = matchPhrase;
    }

    public String getHighlightField() {
        return highlightField;
    }

    public void setHighlightField(String highlightField) {
        this.highlightField = highlightField;
    }

    public String getQueryField() {
        return queryField;
    }

    public void setQueryField(String queryField) {
        this.queryField = queryField;
    }

    public String getQueryText() {
        return queryText;
    }

    public void setQueryText(String queryText) {
        this.queryText = queryText;
    }

    public String getFromQueryText() {
        return fromQueryText;
    }

    public void setFromQueryText(String fromQueryText) {
        this.fromQueryText = fromQueryText;
    }

    public String getToQueryText() {
        return toQueryText;
    }

    public void setToQueryText(String toQueryText) {
        this.toQueryText = toQueryText;
    }
}
