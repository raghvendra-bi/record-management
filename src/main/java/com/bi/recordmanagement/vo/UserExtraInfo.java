package com.bi.recordmanagement.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
public class UserExtraInfo {

    private String id, specialistIn, gomediiId;
    @JsonProperty(value = "isOwner")
    private boolean isOwner;
    private Clinic clinic;
    private Staff staff;


    @Getter
    @Setter
    @ToString
    public static class Clinic {
        private String id, domainName;
        private List<Facility> facilities;
        @JsonProperty(value = "isVirtualClinic")
        private boolean isVirtualClinic;
       
        @JsonProperty(value = "isAllowedCreateAppointmentFromConsultation")
        private boolean isAllowedCreateAppointmentFromConsultation;

        @Getter
        @Setter
        @ToString
        public static class Facility {
            private Long id;
            @JsonProperty(value = "isDeleted")
            private boolean isDeleted;
        }

    }

    @Getter
    @Setter
    @ToString
    public static class Staff {
        Long id;
    }


    /*public static void main(String ...strings) throws IOException {

        String userExtraJson = "{\n" +
                "  \"id\": 2,\n" +
                "  \"specialistIn\": null,\n" +
                "  \"isOwner\": false,\n" +
                "  \"clinic\": {\n" +
                "    \"id\": 6,\n" +
                "    \"domainName\": \"clinic1\",\n" +
                "    \"facilities\": [\n" +
                "      {\n" +
                "        \"id\": 116\n" +
                "      }\n" +
                "    ],\n" +
                "    \"isVirtualClinic\": true\n" +
                "  },\n" +
                "  \"staff\": {\n" +
                "    \"id\": 1\n" +
                "  },\n" +
                "  \"gomediiId\": null\n" +
                "}";
        ObjectMapper objectMapper = new ObjectMapper();
        UserExtraInfo extraInfo = objectMapper.readValue(userExtraJson, UserExtraInfo.class);
        System.out.println(extraInfo.getClinic().getId());
        System.out.println(extraInfo);

    }*/

}
