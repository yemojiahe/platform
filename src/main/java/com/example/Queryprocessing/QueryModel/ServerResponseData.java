package com.example.Queryprocessing.QueryModel;
//该类是返回请求的回应消息类

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;
import java.util.Map;

public class ServerResponseData {
    @JsonProperty("secondaryProcessingMethodValue")
    private String secondaryProcessingMethodValue;

    @JsonProperty("items")
    private Map<String, ServerResponseData.Item> items;


    public Map<String, ServerResponseData.Item> getItems() {
        return items;
    }

    public void setItems(Map<String, ServerResponseData.Item> items) {
        this.items = items;
    }

    public String getSecondaryProcessingMethodValue() {
        return secondaryProcessingMethodValue;
    }

    public void setSecondaryProcessingMethodValue(String secondaryProcessingMethodValue) {
        this.secondaryProcessingMethodValue = secondaryProcessingMethodValue;
    }

    public static class Item {
        @JsonProperty("CrowdCategories")
        private List<String> crowdCategories; // 人群类别

        @JsonProperty("DateCategories")
        private String dateCategories; // 数据类别

        @JsonProperty("ProcessingMethod")
        private String processingMethod; // 处理方式


        // Getter 和 Setter 方法


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

        public List<String> getCrowdCategories() {
            return crowdCategories;
        }

        public void setCrowdCategories(List<String> crowdCategories) {
            this.crowdCategories = crowdCategories;
        }
    }
}
