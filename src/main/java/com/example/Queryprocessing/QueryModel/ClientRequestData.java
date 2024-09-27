package com.example.Queryprocessing.QueryModel;
//支持包含多个结构相同的对象和多个额外的字段的json的model

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serial;
import java.io.Serializable;
import java.util.Map;

public class ClientRequestData  {

    @JsonProperty("secondaryProcessingMethod")
    private String secondaryProcessingMethod;

    @JsonProperty("items")
    private Map<String, Item> items;

    public String getSecondaryProcessingMethod() {
        return secondaryProcessingMethod;
    }

    public void setSecondaryProcessingMethod(String secondaryProcessingMethod) {
        this.secondaryProcessingMethod = secondaryProcessingMethod;
    }

    public Map<String, Item> getItems() {
        return items;
    }

    public void setItems(Map<String, Item> items) {
        this.items = items;
    }

    public static class Item {
        @JsonProperty("CrowdCategories")
        private String crowdCategories; // 人群类别

        @JsonProperty("DateCategories")
        private String dateCategories; // 数据类别

        @JsonProperty("ProcessingMethod")
        private String processingMethod; // 处理方式


        // Getter 和 Setter 方法
        public String getCrowdCategories() {
            return crowdCategories;
        }

        public void setCrowdCategories(String crowdCategories) {
            this.crowdCategories = crowdCategories;
        }

        public String getDateCategories() {
            return dateCategories;
        }

        public void setDateCategories(String dateCategories) {
            this.dateCategories = dateCategories;
        }

        public String getProcessingMethod() {
            return processingMethod;
        }

        public void setProcessingMethod(String processingMethod) {
            this.processingMethod = processingMethod;
        }
    }
}
