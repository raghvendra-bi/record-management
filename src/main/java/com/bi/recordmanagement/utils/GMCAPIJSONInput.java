package com.bi.recordmanagement.utils;

public class GMCAPIJSONInput {
	public static final String API_USER_CREATE = "{\n"
			+ "\"clientId\": \"gmi-client\"  //gm-client and with gmc-client provide specilistIn and isDoctor ,\n"
			+ "\"countryIATACode\": \"IN\",\n" + "\"email\": \"abc@gmail.com\",\n" + "\"phone\": \"9090909090\",\n"
			+ "\"password\": \"qwerty\",\n"
			+ "\"otpForEmail\": \"123456\"  //not required when providing otpForPhone,\n"
			+ "\"otpForPhone\": \"123456\"  //not required when providing otpForEmail\n" + "}";
	public static final String API_USER_CREATE_WITH_TOKEN = "{\n"
			+ "\"clientId\": \"gmi-client\"  //gm-client and with gmc-client provide specilistIn and isDoctor ,\n"
			+ "\"password\": \"qwerty\",\n"
			+ "\"tokenForEmail\": \"123456\"  //not required when providing otpForPhone,\n"
			+ "\"email\": \"abc@gmail.com\"  //not required when providing otpForPhone,\n"
			+ "\"phone\": \"1234567890\"  //not required when providing otpForPhone,\n"
			+ "\"phoneCode\": \"91\"  //not required when providing otpForPhone,\n"
			+ "\"tokenForPhone\": \"123456\"  //not required when providing otpForEmail\n" + "}";
    public static final String API_resetPassword_CREATE = "{\n" +
            "\"otp\": \"81733\",\n" +
            "\"password\": \"string123456\",\n" +
            "\"loginName\": \"9891262969\"}";

    public static final String API_CLINIC_CREATE = "{\n" +
            "\"gstin\": \"gstn\",\n" +
            "\"address\": {\n" +
            "\"address1\": \"address 1\",\n" +
            "\"address2\": null,\n" +
            "\"city\": {\n" +
            "\"id\": 1,\n" +
            "\"state\": {\n" +
            "\"country\": {\n" +
            "\"id\": 1\n" +
            "},\n" +
            "\"id\": 1\n" +
            "}\n" +
            "},\n" +
            "\"zip\": \"201301\"},\n" +
            "\"email\": \"go@gomedssii.com\",\n" +
            "\"clinicFacilityType\":\"SINGLE\",\n" +
            "\"specialities\": [\n" +
            "{\n" +
            "\"id\": 1\n" +
            "}\n" +
            "],\n" +
            "\"phone\": \"1236547899\",\n" +
            "\"landLine\": \"011-123456\",\n" +
            "\"description\": \"forties noida sec 62\",\n" +
            "\"domainName\": \"goomedii\",\n" +
            "\"logo\": null,\n" +
            "\"name\": \"Clinic 1\",\n" +
            "\"portalURL\": \"http://test\",\n" +
            "\"registrationId\": \"rfdfl\"}";

    public static final String API_OTP_CREATE = "{\n" +
            "\"otpFor\": \"9958711322\"" +
            "}";
    public static final String API_forgetPassword_CREATE = "{\n" +
            "\"otpFor\": \"9958711322\"}";
    public static final String API_healthRecord_CREATE = "{\n" +
            "\"otpFor\":\"9958711322\"}";
    public static final String API_changePassword_CREATE = "{\n" +
            "\"loginName\": \"9891262969\",\n" +
            "\"newPassword\": \"string123457\",\n" +
            "\"oldPassword\": \"string123456\"}";

    public static final String API_FACILITY_CREATE = "{\n" +
            "\"address\": {\n" +
            "\"address1\": \"address 1\",\n" +
            "\"address2\": \"\",\n" +
            "\"locality\":\"locality\",\n" +
            "\"city\": {\n" +
            "\"id\": 1\n" +
            "},\n" +
            "\"zip\": \"201301\"},\n" +
            "\"description\": \"forties noida sec 62\",\n" +
            "\"email\": \"forties@gmail.com\",\n" +
            "\"name\": \"forties62\",\n" +
            "\"phone\": \"7896541233\",\n" +
            "\"landLine\": \"011-123456\",\n" +
            "\"specialities\": [\n" +
            "{\n" +
            "\"id\": 1\n" +
            "}\n" +
            "]\n" +
            "}";

    public static final String API_STAFFSCHEDULE_CREATE = "{\n" +
            "\"startDate\": \"2018-07-30\",\n" +
            "\"endDate\": \"2018-05-14\",\n" +
            "\"scheduleTimings\": {\n" +
            "\"MONDAY\": [\n" +
            "{\n" +
            "\"dayOfWeek\": \"MONDAY\",\n" +
            "\"startTime\": \"09:00\",\n" +
            "\"endTime\": \"14:00\",\n" +
            "\"monthWeekAvailablities\": {\n" +
            "      \"firstWeek\": true,\n" +
            "      \"secondWeek\": false,\n" +
            "      \"thirdWeek\": true,\n" +
            "      \"fourthWeek\": false,\n" +
            "      \"fifthWeek\": true\n" +
            "    }\n" +
            "  }\n" +
            "]\n" +
            "}\n" +
            "}";
    public static final String API_STAFFSCHEDULE_UPDATE = "{\n" +
            "  \"startDate\": \"2018-07-14\",\n" +
            "  \"endDate\": \"2018-10-30\",\n" +
            "  \"scheduleTimings\": {\n" +
            "    \"MONDAY\": [\n" +
            "      {\n" +
            "        \"id\": 1,\n" +
            "        \"dayOfWeek\": \"MONDAY\",\n" +
            "        \"startTime\": \"09:00\",\n" +
            "        \"endTime\": \"19:00\",\n" +
            "        \"monthWeekAvailablities\": {\n" +
            "          \"id\": 1,\n" +
            "          \"firstWeek\": true,\n" +
            "          \"secondWeek\": false,\n" +
            "          \"thirdWeek\": true,\n" +
            "          \"fourthWeek\": false,\n" +
            "          \"fifthWeek\": true\n" +
            "        }\n" +
            "      }\n" +
            "    ]\n" +
            "  }\n" +
            "}";

    public static final String API_DOCTOR_CREATE = "{\n" +
            "  \"title\": \"Mr\",\n" +
            "  \"staffType\": \"DOCTOR\",\n" +
            "  \"age\": \"25\",\n" +
            "  \"firstName\": \"himanshu\",\n" +
            "  \"lastName\": null,\n" +
            "  \"email\": null,\n" +
            "  \"gender\": \"M\",\n" +
            "  \"practiceLicense\": \"derays\",\n" +
            "  \"qualification\": null,\n" +
            "  \"occupation\": null,\n" +
            "  \"bloodGroup\": null,\n" +
            "  \"signature\": \"abc\",\n" +
            "  \"logo\": null,\n" +
            "  \"credential\": \"surabhi13\",\n" +
            "  \"specialistIn\": {\n" +
            "    \"id\": 1,\n" +
            "    \"name\": \"Clinical Laboratory Sciences\",\n" +
            "    \"description\": null\n" +
            "  },\n" +
            "  \"physicalExams\": [\n" +
            "    {\n" +
            "      \"name\": \"Height\",\n" +
            "      \"unit\": \"ft\",\n" +
            "      \"id\": 1\n" +
            "    },\n" +
            "    {\n" +
            "      \"id\": 2,\n" +
            "      \"name\": \"BP\",\n" +
            "      \"unit\": \"mmHg\"\n" +
            "    }\n" +
            "  ],\n" +
            "  \"dob\": \"1993-10-21T18:30:00.000Z\",\n" +
            "  \"phone\": \"9854496276\",\n" +
            "  \"alterPhone\": null,\n" +
            "  \"address\": null,\n" +
            "  \"loginName\": \"himnsh42\",\n" +
            "  \"document\": []\n" +
            "}";

    public static final String API_DOCTOR_UPDATE = "{\n" +
            "  \"title\": \"Mr\",\n" +
            "  \"firstName\": \"Kishan\",\n" +
            "  \"lastName\": \"Tanwar\",\n" +
            "  \"phone\": \"9654655952\",\n" +
            "  \"alterPhone\": null,\n" +
            "  \"address\": null,\n" +
            "  \"gender\": \"M\",\n" +
            "  \"email\": null,\n" +
            "  \"bloodGroup\": null,\n" +
            "  \"occupation\": null,\n" +
            "  \"clinic\": {\n" +
            "    \"id\": 1,\n" +
            "    \"name\": \"Max Group\",\n" +
            "    \"description\": \"This is max\",\n" +
            "    \"email\": \"max@gmail.com\",\n" +
            "    \"domainName\": \"max_1\",\n" +
            "    \"phone\": \"9958487009\",\n" +
            "    \"landLine\": null,\n" +
            "    \"gstin\": \"\",\n" +
            "    \"registrationId\": \"Clinic1\",\n" +
            "    \"logo\": \"https://gmstorageqaind.blob.core.windows.net/gmcdocument/logo_265.png\",\n" +
            "    \"portalURL\": \"www.max.com\",\n" +
            "    \"address\": {},\n" +
            "    \"document\": [],\n" +
            "    \"clinicFacilityType\": \"MULTIPLE\",\n" +
            "    \"specialities\": [\n" +
            "      {\n" +
            "        \"id\": 2,\n" +
            "        \"name\": \"Clinical Neurophysiology\",\n" +
            "        \"description\": null\n" +
            "      },\n" +
            "      {\n" +
            "        \"id\": 4,\n" +
            "        \"name\": \"Emergency Medicine\",\n" +
            "        \"description\": null\n" +
            "      }\n" +
            "    ]\n" +
            "  },\n" +
            "  \"isDeleted\": false,\n" +
            "  \"document\": [],\n" +
            "  \"staffType\": \"DOCTOR\",\n" +
            "  \"logo\": \"https://gmstorageqaind.blob.core.windows.net/gmcdocument/images%20(1)_60.jpg\",\n" +
            "  \"user\": null,\n" +
            "  \"age\": \"28\",\n" +
            "  \"dob\": \"1990-08-13T18:30:00.000+0000\",\n" +
            "  \"practiceLicense\": null,\n" +
            "  \"qualification\": null,\n" +
            "  \"specialistIn\": {\n" +
            "    \"id\": 5,\n" +
            "    \"name\": \"Endocrinology\",\n" +
            "    \"description\": null\n" +
            "  },\n" +
            "  \"physicalExams\": [\n" +
            "    \"BP\",\n" +
            "    \"Height\",\n" +
            "	\"Weight\"\n" +
            "  ]\n" +
            "}";


    public static final String API_STAFF_CREATE = "{\n" +
            "\"staffType\": \"STAFF\",\n" +
            "\"credential\":\"paswd12\",\n" +
            "\"clinic\": {\n" +
            "\"id\": 1\n" +
            "},\n" +
            "\"signature\": \"qwerty\",\n" +
            "\"title\":\"Mr\",\n" +
            "\"firstName\": \"Himanshu\",\n" +
            "\"lastName\": \"Swarnakar\",\n" +
            "\"phone\": \"9891262969\",\n" +
            "\"alterPhone\": null,\n" +
            "\"address\": {\n" +
            "\"address1\": \"ghaziabad\",\n" +
            "\"address2\": \"up\",\n" +
            "\"city\": {\n" +
            "\"id\": 1,\n" +
            "\"state\": {\n" +
            "\"id\": 1,\n" +
            "\"country\": {\n" +
            "\"id\": 1\n" +
            "}\n" +
            "}\n" +
            "},\n" +
            "\"zip\": \"201301\"" +
            "},\n" +
            "\"dob\": \"2012-03-12T18:30:00.000+0000\",\n" +
            "\"age\":\"22\",\n" +
            "\"gender\": \"M\",\n" +
            "\"email\": \"himanshu@gmail.com\",\n" +
            "\"bloodGroup\": \"A+\",\n" +
            "\"logo\": \"Staff logo\",\n" +
            "\"occupation\": \"Doctor\"" +
            "}";

    public static final String API_STAFF_UPDATE = "{\n" +
            "\"title\": \"Mr\",\n" +
            "\"firstName\": \"Kishan\",\n" +
            "\"lastName\": \"Tanwar\",\n" +
            "\"age\":\"24\",\n" +
            "\"phone\": \"9654655952\",\n" +
            "\"alterPhone\": null,\n" +
            "\"address\": {\n" +
            "\"id\": 1,\n" +
            "\"address1\": \"address 1\",\n" +
            "\"address2\": null,\n" +
            "\"city\": {\n" +
            "\"id\": 1,\n" +
            "\"name\": null\n" +
            "},\n" +
            "\"zip\": \"201301\"" +
            "},\n" +
            "\"dob\": \"2012-03-12T18:30:00.000+0000\",\n" +
            "\"gender\": \"M\",\n" +
            "\"email\": \"kishan.mca.du@gmail.com\",\n" +
            "\"bloodGroup\": \"A+\",\n" +
            "\"occupation\": \"Doctor\",\n" +
            "\"isDeleted\": false,\n" +
            "\"staffType\": \"STAFF\",\n" +
            "\"credential\":\"passwd12\",\n" +
            "\"logo\": \"Staff logo\",\n" +
            "\"signature\": \"qwerty\"" +
            "}";

    public static final String API_CLINIC_UPDATE = "{\n" +
            "\"gstin\": \"gstn\",\n" +
            "\"address\": {\n" +
            "\"address1\": \"address 1\",\n" +
            "\"address2\": null,\n" +
            "\"city\": {\n" +
            "\"id\": 1,\n" +
            "\"state\": {\n" +
            "\"country\": {\n" +
            "\"id\": 1\n" +
            "},\n" +
            "\"id\": 1\n" +
            "}\n" +
            "},\n" +
            "\"zip\": \"201301\"" +
            "},\n" +
            "\"email\": \"gomedii@gomedii.com\",\n" +
            "\"phone\": \"1236547899\",\n" +
            "\"domainName\": \"gomedii.com\",\n" +
            "\"logo\": null,\n" +
            "\"name\": \"Clinic 1\",\n" +
            "\"portalURL\": \"http://test\",\n" +
            "\"registrationId\":  \"rfdfl\"" +
            "}";

    public static final String API_FACILITY_UPDATE = "{\"name\": \"arya\",\n" +
            "\"description\": \"abcas\",\n" +
            "\"specialities\": [\n" +
            "{\n" +
            "\"id\": 1,\n" +
            "\"name\": \"Clinical Laboratory Sciences\",\n" +
            "\"description\": \"aaabc\"" +
            "}\n" +
            "],\n" +
            "\"email\": \"xra@gmail.comm\",\n" +
            "\"phone\": \"1234616600\",\n" +
            "\"address\": {\n" +
            "\"id\": 3,\n" +
            "\"address1\": \"address 3311\",\n" +
            "\"address2\": \"abvc\",\n" +
            "\"city\": {\n" +
            "\"id\": 1,\n" +
            "\"name\": \"Noidaa\"" +
            "},\n" +
            "\"zip\": \"201301\"" +
            "}\n" +
            "}";

    public static final String API_PATIENT_CREATE = "{\n" +
            "  \"email\": \"himanshu@gmail.com\",\n" +
            "  \"phone\": \"9891262969\",\n" +
            "  \"address\": {\n" +
            "    \"address1\": \"noida sec-15\",\n" +
            "    \"address2\": \"up\",\n" +
            "    \"city\": {\n" +
            "      \"id\": 1\n" +
            "    },\n" +
            "    \"zip\": \"201301\",\n" +
            "    \"country\": {\n" +
            "      \"id\": 1\n" +
            "    }\n" +
            "  },\n" +
            "  \"title\": \"Mr\",\n" +
            "  \"age\": \"25\",\n" +
            "  \"firstName\": \"himanshu\",\n" +
            "  \"lastName\": \"swarnakar\",\n" +
            "  \"gender\": \"M\",\n" +
            "  \"occupation\": \"BusinessMan\",\n" +
            "  \"bloodGroup\": \"AB_NEG\",\n" +
            "  \"dob\": \"2012-03-12\",\n" +
            "  \"alterPhone\": null,\n" +
            "  \"document\": [\n" +
            "    {\n" +
            "      \"id\": 10906,\n" +
            "      \"entityType\": \"PERSON\",\n" +
            "      \"fileName\": \"AppWithHeader_10894.html\",\n" +
            "      \"documentType\": null\n" +
            "    }\n" +
            "  ],\n" +
            "  \"passport\": \"passport\",\n" +
            "  \"countryCode\": \"91\"\n" +
            "}";
    public static final String API_APPOINTMENT_CREATE = "{\n" +
            "\"appointmentDate\":\"2018-06-05\",\n" +
            "\"appointmentStatus\":\"CONFIRMED\",\n" +
            "\"startTime\":\"09:30\",\n" +
            "\"patient\":{\"id\":2}\n" +
            "}";
    public static final String API_APPOINTMENT_UPDATE = "{\r\n" +
            "  \"creationDate\": \"2019-07-08T05:30:32.000+0000\",\r\n" +
            "  \"id\": 7546,\r\n" +
            "  \"encounter\": {\r\n" +
            "    \"complaints\": [],\r\n" +
            "    \"doctor\": {\r\n" +
            "      \"id\": 1\r\n" +
            "    },\r\n" +
            "    \"diagnosis\": [],\r\n" +
            "    \"notes\": \"\",\r\n" +
            "    \"medicines\": [],\r\n" +
            "    \"observations\": [],\r\n" +
            "    \"investigations\": [],\r\n" +
            "    \"followUp\": null,\r\n" +
            "    \"document\": [],\r\n" +
            "    \"emrType\": \"GMC_EMR\",\r\n" +
            "    \"physicalExam\": {\r\n" +
            "      \"vitals\": [\r\n" +
            "        {\r\n" +
            "          \"observedValue\": \"122\",\r\n" +
            "          \"metaVital\": {\r\n" +
            "            \"id\": 3,\r\n" +
            "            \"name\": \"Weight\"\r\n" +
            "          },\r\n" +
            "          \"metaVitalUnit\": {\r\n" +
            "            \"id\": 2\r\n" +
            "          }\r\n" +
            "        },\r\n" +
            "        {\r\n" +
            "          \"observedValue\": \"122\",\r\n" +
            "          \"metaVital\": {\r\n" +
            "            \"id\": 3,\r\n" +
            "            \"name\": \"Weight\"\r\n" +
            "          },\r\n" +
            "          \"metaVitalUnit\": {\r\n" +
            "            \"id\": 2\r\n" +
            "          }\r\n" +
            "        },\r\n" +
            "        {\r\n" +
            "          \"observedValue\": \"122\",\r\n" +
            "          \"metaVital\": {\r\n" +
            "            \"id\": 3,\r\n" +
            "            \"name\": \"Weight\"\r\n" +
            "          },\r\n" +
            "          \"metaVitalUnit\": {\r\n" +
            "            \"id\": 2\r\n" +
            "          }\r\n" +
            "        }\r\n" +
            "      ]\r\n" +
            "    }\r\n" +
            "  },\r\n" +
            "  \"facility\": {\r\n" +
            "    \"creationDate\": \"2018-07-16T12:35:22.000+0000\",\r\n" +
            "    \"id\": 8\r\n" +
            "  },\r\n" +
            "  \"doctor\": {\r\n" +
            "    \"creationDate\": \"2018-07-13T04:10:04.000+0000\",\r\n" +
            "    \"id\": 1,\r\n" +
            "    \"title\": \"Mr\",\r\n" +
            "    \"firstName\": \"Abhishek\",\r\n" +
            "    \"lastName\": \"chandel\",\r\n" +
            "    \"phone\": \"9891262969\",\r\n" +
            "    \"alterPhone\": null,\r\n" +
            "    \"gender\": \"M\",\r\n" +
            "    \"email\": \"himanshu.swarnakar@gomedii.com\",\r\n" +
            "    \"bloodGroup\": null,\r\n" +
            "    \"occupation\": null,\r\n" +
            "    \"isDeleted\": false,\r\n" +
            "    \"age\": null,\r\n" +
            "    \"dob\": null,\r\n" +
            "    \"loginName\": null,\r\n" +
            "    \"clinic\": {\r\n" +
            "      \"creationDate\": \"2018-07-16T12:35:22.000+0000\",\r\n" +
            "      \"document\": []\r\n" +
            "    },\r\n" +
            "    \"document\": []\r\n" +
            "  },\r\n" +
            "  \"appointmentDate\": \"2019-07-08\",\r\n" +
            "  \"appointmentStatus\": \"CLOSED\",\r\n" +
            "  \"startTime\": \"11:15\",\r\n" +
            "  \"endTime\": null,\r\n" +
            "  \"patient\": {\r\n" +
            "    \"creationDate\": \"2018-07-13T11:34:20.000+0000\",\r\n" +
            "    \"id\": 16,\r\n" +
            "    \"title\": \"Mr\",\r\n" +
            "    \"uhid\": \"1002\",\r\n" +
            "    \"firstName\": \"Patient\",\r\n" +
            "    \"lastName\": \"Three\",\r\n" +
            "    \"phone\": \"8447092638\",\r\n" +
            "    \"alterPhone\": null,\r\n" +
            "    \"gender\": \"M\",\r\n" +
            "    \"email\": null,\r\n" +
            "    \"bloodGroup\": null,\r\n" +
            "    \"occupation\": null,\r\n" +
            "    \"isDeleted\": false,\r\n" +
            "    \"age\": \"24\",\r\n" +
            "    \"passport\": null,\r\n" +
            "    \"dob\": \"1994-10-08T18:30:00.000+0000\",\r\n" +
            "    \"isAnonymous\": false,\r\n" +
            "    \"countryCode\": null,\r\n" +
            "    \"document\": []\r\n" +
            "  },\r\n" +
            "  \"appointmentType\": \"OFFLINE\",\r\n" +
            "  \"onlineId\": null\r\n" +
            "}";

    public static final String API_META_UPDATE = "{\n" +
            "  \n" +
            "  \"name\": \"abcaa\",\n" +
            "  \"description\": \"abcaa\"" +
            "}";

    public static final String API_META_CREATE = "{\n" +
            "\"name\": \"abca\",\n" +
            "\"description\": \"abc\"" +
            "}";
    public static final String API_ENCOUNTER_UPDATE = "{\r\n" +
            "  \"emrType\": \"GMC_EMR\",\r\n" +
            "  \"complaints\": [\r\n" +
            "    \"payment fail\"\r\n" +
            "  ],\r\n" +
            "  \"doctor\": {\r\n" +
            "    \"id\": 1\r\n" +
            "  },\r\n" +
            "  \"initiatedBy\": {\r\n" +
            "    \"id\": 1\r\n" +
            "  },\r\n" +
            "  \"startTime\": null,\r\n" +
            "  \"endTime\": null,\r\n" +
            "  \"diagnosis\": [\r\n" +
            "    \"hello\"\r\n" +
            "  ],\r\n" +
            "  \"notes\": \"abc\",\r\n" +
            "  \"medicines\": [\r\n" +
            "    {\r\n" +
            "      \"name\": \"abc\",\r\n" +
            "      \"frequency\": \"abc\",\r\n" +
            "      \"schedule\": [\r\n" +
            "        \"abc\"\r\n" +
            "      ],\r\n" +
            "      \"quantity\": \"abc\",\r\n" +
            "      \"time\": [\r\n" +
            "        \"iday\"\r\n" +
            "      ],\r\n" +
            "      \"duration\": \"abca\"\r\n" +
            "    }\r\n" +
            "  ],\r\n" +
            "  \"physicalExam\": {\r\n" +
            "    \"vitals\": [\r\n" +
            "      {\r\n" +
            "        \"observedValue\": \"122\",\r\n" +
            "        \"metaVital\": {\r\n" +
            "          \"id\": 3,\r\n" +
            "          \"name\": \"Weight\"\r\n" +
            "        },\r\n" +
            "        \"metaVitalUnit\": {\r\n" +
            "          \"id\": 2\r\n" +
            "        }\r\n" +
            "      }\r\n" +
            "    ]\r\n" +
            "  }\r\n" +
            "}";

    public static final String API_ENCOUNTER_CREATE = "{\n" +
            "\"complaints\": [\n" +
            "\"payment fail\"" +
            "]\n" +
            ",\"doctor\": {\n" +
            "\"id\":1\n" +
            "},\n" +
            "\"startTime\": null,\n" +
            "\"endTime\": null,\n" +
            "\"physicalExams\": [\n" +
            "{\n" +
            "\"name\": \"BP\",\n" +
            "\"observedValue\": 120,\n" +
            "\"Observation\": \"sdfg\",\n" +
            "\"Impression\": \"acb\",\n" +
            "\"time\": \"2010-11-30T18:30:00.000+0000\"" +
            "}\n" +
            "],\n" +
            "\"diagnosis\": [" +
            "\"hello\"" +
            "],\n" +
            "\"notes\":\"abc\",\n" +
            "\"medicines\": [\n" +
            "{\n" +
            "\"name\": \"abc\",\n" +
            "\"frequency\":\"abc\", \n" +
            "\"schedule\": [" +
            "\"abc\"" +
            "],\n" +
            "\"quantity\":\"abc\",\n" +
            "\"time\":[\n" +
            "\"1day\"\n" +
            "],\n" +
            "\"duration\":\"abc\"\n" +
            "}\n" +
            "]\n" +
            "}";
    public static final String API_PUBLIC_DOCTOR_PROFILE_CREATE = "{\n" +
            "\"name\": \"Dr. Kishan Tanwar\",\n" +
            "\"description\": \"Specialist\",\n" +
            "\"header1\": \"A1\",\n" +
            "\"header2\": \"a2\",\n" +
            "\"address\": \"New Delhi\",\n" +
            "\"availableDay\": \"MON-TUE\",\n" +
            "\"designation\": \"CMO\",\n" +
            "\"isPartner\": \"false\",\n" +
            "\"speciality\": \"ENT\",\n" +
            "\"email\":\"arya.singh@gomedii.com\",\n" +
            "\"experience\": \"5\",\n" +
            "\"gender\": \"M\",\n" +
            "\"qualification\": \"mbbs\",\n" +
            "\"phone\": \"7725067542\",\n" +
            "\"consultationCharge\": \"5000\",\n" +
            "\"consultaionsProvided\": [\n" +
            "\"Monday\",\n" +
            "\"Delhi\"\n" +
            "],\n" +
            "\"timing\": \"14:00-16:00\",\n" +
            "\"clinics\": [\n" +
            "{\n" +
            "\"docId\": 1\n" +
            "}\n" +
            "],\n" +
            "\"logo\": [\n" +
            "{\n" +
            "\"documentDescription\": \"Dr2\",\n" +
            "\"documentType\": \"image/jpeg\",\n" +
            "\"URI\": \"https://gmstorageqaind.blob.core.windows.net/gmcdocument/dr_132.jpg\",\n" +
            "\"GMCURI\": \"/downloadFile/132\",\n" +
            "\"fileName\": \"dr_132.jpg\"\n" +
            "}\n" +
            "],\n" +
            "\"bannerImage\": [\n" +
            "{\n" +
            "\"documentDescription\": \"Dr\",\n" +
            "\"documentType\": \"image/jpeg\",\n" +
            "\"URI\": \"https://gmstorageqaind.blob.core.windows.net/gmcdocument/2_131.jpg\",\n" +
            "\"GMCURI\": \"/downloadFile/131\",\n" +
            "\"fileName\": \"2_131.jpg\"\n" +
            "}\n" +
            "],\n" +
            "\"gallery\": [\n" +
            "{\n" +
            "\"documentDescription\": \"Dr2\",\n" +
            "\"documentType\": \"image/jpeg\",\n" +
            "\"URI\": \"https://gmstorageqaind.blob.core.windows.net/gmcdocument/dr_132.jpg\",\n" +
            "\"GMCURI\": \"/downloadFile/132\",\n" +
            "\"fileName\": \"?dr_132.jpg\"\n" +
            "}\n" +
            "],\n" +
            "\"info\": [\n" +
            "{\n" +
            "\"details\": \"test\",\n" +
            "\"header\": \"Hello\",\n" +
            "\"additionalInfoDocument\": [\n" +
            "{\n" +
            "\"documentDescription\": \"test2\",\n" +
            "\"documentType\": \"image/jpeg\",\n" +
            "\"URI\": \"https://gmstorageqaind.blob.core.windows.net/gmcdocument/2_133.jpg\",\n" +
            "\"GMCURI\": \"/downloadFile/133\",\n" +
            "\"fileName\": \"2_133.jpg\",\n" +
            "\"additionalInfoDocumentType\":\"IMAGE\",\n" +
            "\"entityType\": \"aa\"\n" +
            "}\n" +
            "]\n" +
            "}\n" +
            "],\n" +
            "\"news\":[\n" +
            "  {\n" +
            "    \"text\":\"patient\",\n" +
            "\"pubDocNewsDocument\":[\n" +
            "    {\n" +
            "    \"documentDescription\": \"document\",\n" +
            "\"documentType\": \"image/jpeg\",\n" +
            "\"URI\": \"https://gmstorageqaind.blob.core.windows.net/gmcdocument/2_133.jpg\",\n" +
            "\"GMCURI\": \"/downloadFile/133\",\n" +
            "\"fileName\": \"2_133.jpg\",\n" +
            "\"additionalInfoDocumentType\":\"IMAGE\",\n" +
            "\"entityType\": \"aa\"\n" +
            "  }\n" +
            "  ]\n" +
            "}\n" +
            "],\n" +
            "\"profileImage\": [\n" +
            "{\n" +
            "\"documentDescription\": \"Dr\",\n" +
            "\"documentType\": \"image/jpeg\",\n" +
            "\"URI\": \"https://gmstorageqaind.blob.core.windows.net/gmcdocument/2_131.jpg\",\n" +
            "\"GMCURI\": \"/downloadFile/131\",\n" +
            "\"fileName\": \"2_131.jpg\"\n" +
            "\n" +
            "}\n" +
            "]\n" +
            "}";

    public static final String API_PLAN_CREATE = "{\n" +
            "\"name\" : \"Gold\",\n" +
            "\"description\":\"2 months\",\n" +
            "\"rate\":\"2000\",\n" +
            "\"startDate\":\"2018-09-05\",\n" +
            "\"duration\":\"3\",\n" +
            "\"durationUnit\":\"Months\",\n" +
            "\"offer\":\"50% Discount\",\n" +
            "\"isActive\":\"true\",\n" +
            "\"isApproved\":\"Y\",\n" +
            "\"approvedBy\":\"gomedii\",\n" +
            "\"termsConditions\":\"Gomedii Terms Conditions\",\n" +
            "\"features\":[\"Medical Store\", \"Gomedii\"]\n" +
            "}";
    public static final String API_ONLINE_APPOINTMENT_CREATE = "{\r\n" +
            "\"appointmentDate\":\"2018-12-05\",\r\n" +
            "\"appointmentStatus\":\"CONFIRMED\",\r\n" +
            "\"startTime\":\"18:30\",\r\n" +
            "\"patientFistName\":\"himanshiiiii\",\r\n" +
            "\"phone\":\"9876543210\",\r\n" +
            "\"email\":\"abc@gmail.com\",\r\n" +
            "\"gmToken\":\"1234567890\",\r\n" +
            "\"otp\":\"\"\r\n" +
            "}";


    public static final String API_SUBSCRIPTION_CREATE = "{\n" +
            "\"clinic\":{\"id\":9},\n" +
            "\"subscribedPlan\":{\"id\":1},\n" +
            "\"startDate\":\"2019-03-04\",\n" +
            "\"isSubscriptionActive\":true,\n" +
            "\"agent\":{\"id\":1}\n" +
            "}";

    public static final String API_SUBSCRIPTION_UPDATE = "{\n" +
            "\"clinic\":{\"id\":9},\n" +
            "\"subscribedPlan\":{\"id\":1},\n" +
            "\"startDate\":\"2019-03-25\",\n" +
            "\"isSubscriptionActive\":true,\n" +
            "\"agent\":{\"id\":1}\n" +
            "}";
    public static final String API_EMAIL_TEMPLATE_CREATE = "{\n" +
            "  \"eventGenerator\": {\n" +
            "    \"generatorType\": \"SYSTEM\",\n" +
            "    \"event\": {\n" +
            "      \"id\": 1,\n" +
            "      \"eventName\": \"ONLINE_APPOINTMENT\"\n" +
            "    }\n" +
            "  },\n" +
            "  \"template\": {\n" +
            "    \"templateData\": \"vbnvbnvbn\",\n" +
            "    \"templateName\": \"  nbvbnvbnvb\"\n" +
            "  },\n" +
            "  \"type\": \"DOCTOR\",\n" +
            "  \"subjectLine\": \"vbnvbn\"\n" +
            "}";
    public static final String API_TEMPLATE_CREATE = "{\n" +
            "  \"eventGenerator\": {\n" +
            "    \"generatorType\": \"SYSTEM\",\n" +
            "    \"event\": {\n" +
            "      \"id\": 1,\n" +
            "      \"eventName\": \"ONLINE_APPOINTMENT\"\n" +
            "    }\n" +
            "  },\n" +
            "  \"template\": {\n" +
            "    \"templateData\": \"vbnvbnvbn\",\n" +
            "    \"templateName\": \"  nbvbnvbnvb\"\n" +
            "  },\n" +
            "  \"type\": \"DOCTOR\",\n" +
            "  \"subjectLine\": \"vbnvbn\"\n" +
            "}";

    public static final String API_PRINTPREFERENCE_CREATE = "{\r\n" +
            "\"print\":\"ABC\",\r\n" +
            "\"paperSize\":\"10\",\r\n" +
            "\"pageOrientation\":\"LandScape\",\r\n" +
            "\"scale\":\"Normal\",\r\n" +
            "\"margin\":\"Normal\",\r\n" +
            "\"formatting\":\r\n" +
            "{\r\n" +
            "\"showGridlines\":true,\r\n" +
            "\"showNotes\":true,\r\n" +
            "\"pageOrder\":\"Over_then_down\",\r\n" +
            "\"alignmentHorizontal\":\"Right\",\r\n" +
            "\"alignmentVertical\":\"Top\"\r\n" +
            "},\r\n" +
            "\"doctor\":{\r\n" +
            "\"id\":1,\r\n" +
            "\"name\":\"arya\"\r\n" +
            "},\r\n" +
            "\"headersAndFooters\":\r\n" +
            "{\r\n" +
            "\"pageNumber\":true,\r\n" +
            "\"sheetName\":true,\r\n" +
            "\"workBookTitle\":true,\r\n" +
            "\"currentDatePr\":true,\r\n" +
            "\"currentTimePr\":false\r\n" +
            "},\r\n" +
            "\"marginTop\":\"Top\",\r\n" +
            "\"marginRight\":\"Right\",\r\n" +
            "\"marginBottom\":\"Bottom\",\r\n" +
            "\"marginLeft\":\"Left\",\r\n" +
            "\"scalePercentage\":\"120\",\r\n" +
            "\"templateFormat\": \"f1\"}";

    public static final String API_PRINTPREFERENCE_UPDATE = "{\r\n" +
            "  \"id\": 3,\r\n" +
            "  \"print\": \"ABC\",\r\n" +
            "  \"paperSize\": \"10\",\r\n" +
            "  \"pageOrientation\": \"LandScape\",\r\n" +
            "  \"scale\": \"Normal\",\r\n" +
            "  \"margin\": \"Normal\",\r\n" +
            "  \"formatting\": {\r\n" +
            "    \"id\": 3,\r\n" +
            "    \"showGridlines\": true,\r\n" +
            "    \"showNotes\": true,\r\n" +
            "    \"pageOrder\": \"Over_then_down\",\r\n" +
            "    \"alignmentHorizontal\": \"Right\",\r\n" +
            "    \"alignmentVertical\": \"Top\"\r\n" +
            "  },\r\n" +
            "  \"doctor\": {\r\n" +
            "    \"id\": 1,\r\n" +
            "    \"firstName\": \"7725067542\"\r\n" +
            "  },\r\n" +
            "  \"headersAndFooters\": {\r\n" +
            "    \"id\": 3,\r\n" +
            "    \"pageNumber\": true,\r\n" +
            "    \"workBookTitle\": true,\r\n" +
            "    \"sheetName\": true,\r\n" +
            "    \"currentDatePr\": true,\r\n" +
            "    \"currentTimePr\": false\r\n" +
            "  },\r\n" +
            "  \"marginTop\": \"Top\",\r\n" +
            "  \"marginRight\": \"Right\",\r\n" +
            "  \"marginBottom\": \"Bottom\",\r\n" +
            "  \"marginLeft\": \"Left\",\r\n" +
            "  \"scalePercentage\": \"120\"\r\n" +
            "}";

    public static final String API_SHAREPRESCRIPTION_CREATE = "{ \"documentId\": 11341,\r\n" +
            "  \"expirationDay\": 10,\r\n" +
            "  \"format\": \"DEFAULT\",\r\n" +
            "  \"message\": \"string\",\r\n" +
            "  \"shareWithVOs\": [{\r\n" +
            "      \"id\": 71,\r\n" +
            "      \"name\": \"string\"}],\r\n" +
            "  \"userEmail\": \"kishan.tanwar@gomedii.com\",\r\n" +
            "  \"userName\": \"string\",\r\n" +
            "  \"userPhoneno\": \"9654655952\"}";

    public static final String API_SHARE_DOCUMENT_CREATE = "{\"documentId\": 10915,\r\n" +
            "  \"expirationDay\": 2,\r\n" +
            "  \"format\": \"DEFAULT\",\r\n" +
            "  \"guid\": \"525551bd-d270-4956-9ffb-839d4e8802a2-GM\",\r\n" +
            "  \"isExpiration\": true,\r\n" +
            "  \"message\": \"string\",\r\n" +
            "  \"shareWithVOs\": [{\"email\": \"kishan.tanwar@gomedii.com\",\r\n" +
            "      \"id\": null}],\r\n" +
            "  \"userEmail\": \"kishan.tanwar@gomedii.com\",\r\n" +
            "  \"userName\": \"string\",\r\n" +
            "  \"userPhoneno\": \"string\"}";

    public static final String API_EXOTEL_CALL_DOCTOR = "{\r\n" +
            "  \"fromNumber\": \"9891262969\",\r\n" +
            "  \"otp\": \"33528\",\r\n" +
            "  \"otpVerified\": false,\r\n" +
            "  \"toPubDocKey\": \"abc\"\r\n" +
            "}";
    public static final String API_EXOTEL_CALL = "{\r\n" +
            "  \"fromNumber\": \"9891262969\",\r\n" +
            "  \"otp\": \"33528\",\r\n" +
            "  \"otpVerified\": false,\r\n" +
            "  \"toNumber\": \"string\",\r\n" +
            "}";

    public static final String API_NA_STAFFSCHEDULE_CREATE = "{\n" +
            "  \"startDate\": \"2019-02-12\",\n" +
            "  \"endDate\": \"2019-03-20\",\n" +
            "  \"startTime\": \"10:00\",\n" +
            "  \"endTime\": \"11:00\",\n" +
            "  \"title\": \"Api Test\",\n" +
            "  \"notifyAndReplace\": true,\n" +
            "  \"notifyAndCancel\": true,\n" +
            "  \"doctorSubstituteVO\": {\n" +
            "    \"type\": \"DOCTOR\",\n" +
            "    \"name\": \"dtdj\",\n" +
            "    \"email\": \"josy@forwads.com\",\n" +
            "    \"designation\": \"SMO\",\n" +
            "    \"id\": 250\n" +
            "  }\n" +
            "}";

    public static final String API_NA_STAFFSCHEDULE_UPDATE = "{\n" +
            "  \"startDate\": \"2019-02-12\",\n" +
            "  \"endDate\": \"2019-03-20\",\n" +
            "  \"startTime\": \"10:00\",\n" +
            "  \"endTime\": \"11:00\",\n" +
            "  \"title\": \"Api Test\",\n" +
            "  \"notifyAndReplace\": true,\n" +
            "  \"notifyAndCancel\": true,\n" +
            "  \"doctorSubstituteVO\": {\n" +
            "    \"type\": \"DOCTOR\",\n" +
            "    \"name\": \"dtdj\",\n" +
            "    \"email\": \"josy@forwads.com\",\n" +
            "    \"designation\": \"SMO\",\n" +
            "    \"id\": 250\n" +
            "  }\n" +
            "}";

    public static final String API_AGENT_CREATE = "{\n" +
            "\"name\":\"agent\",\n" +
            "\"phone\":\"7725067542\"\n" +
            "}";

    public static final String API_ORDER_CREATE = "{\n" +
            " \"storeId\": 21,\n" +
            " \"loginId\": \"5322\",\n" +
            " \"userAddressId\": 0,\n" +
            " \"isPaymentDone\": true,\n" +
            " \"backPrescription\": {\n" +
            " \"prescriptionImageUrl\": \"\",\n" +
            " \"numberOfDays\": 0,\n" +
            " \"numberOfUnits\": 0,\n" +
            " \"requireAllMedicines\": false\n" +
            " },\n" +
            " \"frontPrescription\": {\n" +
            " \"prescriptionImageUrl\": \"https://gmstorageqaind.blob.core.windows.net/gmcdocument/57305d10-337b-496a-bb37-7e28d1113162_2898.jpg\",\n" +
            " \"numberOfDays\": 0,\n" +
            " \"numberOfUnits\": 0,\n" +
            " \"requireAllMedicines\": true\n" +
            " },\n" +
            " \"customerAddress\": {\n" +
            " \"address1\": \"address\",\n" +
            " \"address2\": \"address\",\n" +
            " \"cityId\": 1913,\n" +
            " \"cityName\": \"Noida\",\n" +
            " \"stateId\": 30,\n" +
            " \"stateName\": \"Uttar Pradesh\",\n" +
            " \"areaId\": 10,\n" +
            " \"areaName\": \"Sector 10\",\n" +
            " \"phoneNumber\": \"9899565656\",\n" +
            " \"zipCode\": \"110037\",\n" +
            " \"landmark\": \"near me\"\n" +
            " }\n" +
            "}";

    public static final String API_CSV_CREATE = "{\n" +
            "\"headers\": {\n" +
            "\"0\":\"age\",\n" +
            "\"1\":\"gender\",\n" +
            "\"2\":\"firstName\",\n" +
            "\"3\": \"phone\",\n" +
            "\"4\": \"title\",\n" +
            "\"5\":\"clinicId\",\n" +
            "\"6\":\"uhid\",\n" +
            "\"7\":\"email\",\n" +
            "\"8\":\"address1\"\n" +
            "},\n" +
            "\"requestId\": \"1\",\n" +
            "\"rowSelected\": {\n" +
            "\"key\": \"ALL\",\n" +
            "\"rows\": [\n" +
            "\"\"\n" +
            "]\n" +
            "}\n" +
            "}";

    public static final String API_INVOICE_CREATE = "{\n" +
            "  \"name\": \"arya singh\",\n" +
            "  \"email\": \"arya.singh@gomedii.com\",\n" +
            "  \"phone\": \"7725067542\",\n" +
            "  \"state\": {\n" +
            "    \"id\": 1\n" +
            "  },\n" +
            "  \"address\": {\n" +
            "    \"address1\": \"address 1\",\n" +
            "    \"address2\": null,\n" +
            "    \"city\": {\n" +
            "      \"id\": 1\n" +
            "    },\n" +
            "    \"zip\": \"201301\"\n" +
            "  },\n" +
            "  \"dueDate\": \"2019-04-18\",\n" +
            "  \"subscribedPlan\": {\n" +
            "    \"id\": 1\n" +
            "  },\n" +
            "  \"startDate\": \"2019-03-04\",\n" +
            "  \"agent\": {\n" +
            "    \"id\": 1\n" +
            "  },\n" +
            "  \"discount\": [\n" +
            "    {\n" +
            "      \"discountDescription\": \"15% discount\",\n" +
            "      \"discountPercentage\": 15\n" +
            "    }\n" +
            "  ],\n" +
            "  \"additionalServices\": [\n" +
            "    {\n" +
            "      \"price\": 220,\n" +
            "      \"service\": {\n" +
            "        \"id\": 1,\n" +
            "        \"name\": \"\",\n" +
            "        \"price\": \"\",\n" +
            "        \"servicesType\": \"ADDON\"\n" +
            "      }\n" +
            "    }\n" +
            "  ]\n" +
            "}";

    public static final String API_INVOICE_UPDATE = "{\n" +
            "  \"name\": \"dotharaki\",\n" +
            "  \"email\": \"doth@gmail.com\",\n" +
            "  \"phone\": \"5554226684\",\n" +
            "  \"state\": {\n" +
            "    \"id\": 1,\n" +
            "    \"name\": \"UP\"\n" +
            "  },\n" +
            "  \"address\": {\n" +
            "    \"address1\": \"address1\",\n" +
            "    \"address2\": \"address2\",\n" +
            "    \"city\": {\n" +
            "      \"id\": 2,\n" +
            "      \"name\": \"Greater Noida\"\n" +
            "    },\n" +
            "    \"zip\": \"655546\"\n" +
            "  },\n" +
            "  \"dueDate\": \"2019-05-24\",\n" +
            "  \"subscribedPlan\": {\n" +
            "    \"id\": 2\n" +
            "  },\n" +
            "  \"startDate\": \"2019-05-22\",\n" +
            "  \"agent\": {\n" +
            "    \"id\": 1\n" +
            "  },\n" +
            "  \"discount\": [\n" +
            "    {\n" +
            "      \"discountDescription\": \"desc\",\n" +
            "      \"discountPercentage\": 30\n" +
            "    }\n" +
            "  ],\n" +
            "  \"additionalServices\": [\n" +
            "    {\n" +
            "      \"price\": \"2600\",\n" +
            "      \"service\": {\n" +
            "        \"id\": 1,\n" +
            "        \"name\": \"additionalServices\",\n" +
            "        \"price\": 220\n" +
            "      }\n" +
            "    },\n" +
            "    {\n" +
            "      \"price\": 1500,\n" +
            "      \"service\": {\n" +
            "        \"id\": 2,\n" +
            "        \"price\": 1500,\n" +
            "        \"name\": \"gold service\",\n" +
            "        \"servicesType\": \"ADDON\"\n" +
            "      }\n" +
            "    }\n" +
            "  ]\n" +
            "}";

    public static final String API_PAYMENT_CREATE = "{\n" +
            "\"paidOn\":\"2019-05-20\",\n" +
            "\"paymentReference\":\"12345678\",\n" +
            "\"agent\":{\"id\":1},\n" +
            "\"paymentMode\":\"CASH\",\n" +
            "\"paymentType\":\"PARTIAL_PAYMENT\",\n" +
            "\"description\":\"description\",\n" +
            "\"partialPayment\":{\n" +
            "\"duePayment\":\"1000\",\n" +
            "\"dueDate\":\"2019-05-22\"\n" +
            "},\n" +
            "\"amount\":\"3240.00\"\n" +
            "}";

    public static final String API_PAYMENT_UPDATE = "{\n" +
            "\"paidOn\":\"2019-05-20\",\n" +
            "\"paymentReference\":\"12345678\",\n" +
            "\"agent\":{\"id\":1},\n" +
            "\"paymentMode\":\"CASH\",\n" +
            "\"paymentType\":\"PARTIAL_PAYMENT\",\n" +
            "\"description\":\"description\",\n" +
            "\"partialPayment\":{\n" +
            "\"id\": 15,\n" +
            "\"duePayment\":\"1000\",\n" +
            "\"dueDate\":\"2019-05-22\"\n" +
            "},\n" +
            "\"amount\":\"3240.00\"\n" +
            "}";

    public static final String API_VIRTUAL_APPOINTMENT_CREATE = "{\n" +
            "  \"appointmentDate\": \"2019-06-25\",\n" +
            "  \"appointmentStatus\": \"CONFIRMED\",\n" +
            "  \"startTime\": \"18:30\",\n" +
            "  \"patient\": {\"id\": 118},\n" +
            "  \"staffParticipants\": [{\n" +
            "      \"id\": 1,\n" +
            "      \"staffType\": \"STAFF\"},\n" +
            "    {\n" +
            "      \"id\": 2,\n" +
            "      \"staffType\": \"DOCTOR\"},\n" +
            "    {\n" +
            "      \"id\": 3,\n" +
            "      \"staffType\": \"STAFF\"}]}";

    public static final String API_ACCESS_EMR = "{\"guid\": \"awgag-agwga-325-a\",\n" +
            "  \"label\": \"self\",\n" +
            "  \"uhid\": \"1053-1424\",\n" +
            "  \"otp\": \"7878\",\n" +
            "  \"token\": \"\"}";

    public static final String API_VITAL_CREATE = "For first time\r\n" +
            "{\r\n" +
            "\"label\": {\r\n" +
            "\"name\" : \"father\"\r\n" +
            "},\r\n" +
            "\"vitals\": [\r\n" +
            "{\r\n" +
            "\"observedValue\": \"150/90\",\r\n" +
            "\"observedImage\": null,\r\n" +
            "\"observation\": \"High Pressure\",\r\n" +
            "\"impression\": \"Take BP medicine\",\r\n" +
            "\"metaVital\": {\r\n" +
            "\"id\": 1\r\n" +
            "},\r\n" +
            "\"metaVitalUnit\": {\r\n" +
            "\"id\": 1\r\n" +
            "}\r\n" +
            "},\r\n" +
            "{\r\n" +
            "\"observedValue\": \"65\",\r\n" +
            "\"observedImage\": null,\r\n" +
            "\"observation\": \"Low Sugar\",\r\n" +
            "\"impression\": \"eat sugar\",\r\n" +
            "\"metaVital\": {\r\n" +
            "\"id\": 4\r\n" +
            "},\r\n" +
            "\"metaVitalUnit\": {\r\n" +
            "\"id\": 1\r\n" +
            "}\r\n" +
            "}\r\n" +
            "],\r\n" +
            "\"examinationTime\": \"2019-07-28\"\r\n" +
            "}\r\n" +
            "For subsequent times\r\n" +
            "\r\n" +
            "{\r\n" +
            "\"label\": {\r\n" +
            "“name” : \"Mother\",\r\n" +
            "\"id\": 20\r\n" +
            "},\r\n" +
            "\"vitals\": [\r\n" +
            "{\r\n" +
            "\"observedValue\": \"150/90\",\r\n" +
            "\"observedImage\": null,\r\n" +
            "\"observation\": \"High Pressure\",\r\n" +
            "\"impression\": \"Take BP medicine\",\r\n" +
            "\"metaVital\": {\r\n" +
            "\"id\": 1\r\n" +
            "},\r\n" +
            "\"metaVitalUnit\": {\r\n" +
            "\"id\": 1\r\n" +
            "}\r\n" +
            "},\r\n" +
            "{\r\n" +
            "\"observedValue\": \"65\",\r\n" +
            "\"observedImage\": null,\r\n" +
            "\"observation\": \"Low Sugar\",\r\n" +
            "\"impression\": \"eat sugar\",\r\n" +
            "\"metaVital\": {\r\n" +
            "\"id\": 4\r\n" +
            "},\r\n" +
            "\"metaVitalUnit\": {\r\n" +
            "\"id\": 1\r\n" +
            "}\r\n" +
            "}\r\n" +
            "],\r\n" +
            "\"examinationTime\": “2019-06-12”\r\n" +
            "}";

    public static final String API_VITAL_UPDATE = "{\r\n" +
            "  \"id\": 2610,\r\n" +
            "  \"metaVitalUnit\": {\r\n" +
            "    \"id\": 1\r\n" +
            "  },\r\n" +
            "  \"observedValue\": \"120/90\",\r\n" +
            "  \"examinationTime\": \"2019-06-13\"\r\n" +
            "}";
    public static final String API_VITAL_LABEL_UPDATE = "{\r\n" +
            "\"name\" : \"Self2\"\r\n" +
            "}";

    public static final String API_SHARE_VITALS = "{\r\n" +
            "  \"format\": \"DEFAULT\",\r\n" +
            "  \"isExpiration\": true,\r\n" +
            "  \"message\": \"string\",\r\n" +
            "  \"shareWithVOs\": [\r\n" +
            "    {\r\n" +
            "      \"id\": 70,\r\n" +
            "      \"expirationDay\": 2\r\n" +
            "    },\r\n" +
            "    {\r\n" +
            "      \"expirationDay\": 2,\r\n" +
            "      \"id\": 71\r\n" +
            "    },\r\n" +
            "    {\r\n" +
            "      \"expirationDay\": 4,\r\n" +
            "      \"id\": 69\r\n" +
            "    }\r\n" +
            "  ],\r\n" +
            "  \"sharedVital\": {\r\n" +
            "    \"startDate\": \"2019-08-03T06:50:44.251Z\",\r\n" +
            "    \"endDate\": \"2019-08-24T06:50:44.251Z\",\r\n" +
            "    \"label\": {\r\n" +
            "      \"id\": 43,\r\n" +
            "      \"name\": \"bandhu\"\r\n" +
            "    },\r\n" +
            "    \"metaVitals\": [\r\n" +
            "      {\r\n" +
            "        \"id\": 1\r\n" +
            "      },\r\n" +
            "      {\r\n" +
            "        \"id\": 4\r\n" +
            "      }\r\n" +
            "    ]\r\n" +
            "  },\r\n" +
            "  \"userEmail\": \"udaybhanu.sanyal@binaryinformatics.com\",\r\n" +
            "  \"userName\": \"string\",\r\n" +
            "  \"userPhoneno\": \"string\"\r\n" +
            "}";

    public static final String API_PATIENT_DOCUMENTS_CREATE = "[{  \"id\": 10919,\r\n" +
            "    \"documentType\": \"Image\",\r\n" +
            "    \"documentName\": \"Gomedii\"}]";

    public static final String API_PATIENT_DOCUMENT_UPDATE = "{\"documentType\": \"Image\",\r\n" +
            "  \"documentName\": \"Updated Second Tym\",\r\n" +
            "  \"documentDescription\": null,\r\n" +
            "  \"entityType\": \"PATIENT\",\r\n" +
            "  \"URI\": \"https://gmgmcstorageqaind.blob.core.windows.net/gmcdocument/Vinit_10919.pdf\",\r\n" +
            "  \"GMCURI\": \"/downloadFile/10919\",\r\n" +
            "  \"fileName\": \"Vinit_10919.pdf\",\r\n" +
            "  \"isDeleted\": false,\r\n" +
            "  \"contentType\": \"application/pdf\",\r\n" +
            "  \"additionalInfoDocumentType\": \"IMAGE\",\r\n" +
            "  \"sharedDocument\": {\"id\": 227},\r\n" +
            "  \"patient\": {\"id\": 3380}}";

    public static final String API_DOCUMENTS_LINK = "[{\"documentId\": 10972,\r\n" +
            "    \"documentType\": \"PASSPORT\",\r\n" +
            "    \"documentName\": \"ONE\"}]";

    public static final String API_EDIT_ACCESS_FOR_RECORDS = "{\r\n" +
            "    \"id\": 272,\r\n" +
            "    \"sharedWithExternal\": \"udaybhanu.sanyal@binaryinformatics.com\",\r\n" +
            "    \"sharedWithInternalDoctor\": null,\r\n" +
            "    \"sharedBy\": {\r\n" +
            "      \"id\": 268\r\n" +
            "    },\r\n" +
            "    \"message\": \"string\",\r\n" +
            "    \"accessInDays\": 1,\r\n" +
            "    \"accessExpireDate\": \"2019-07-27T11:59:23.692+0000\"\r\n" +
            "}";

    public static final String API_ADD_VITAL_APPOINTMENT = "{\r\n" +
            "  \"vitals\": [\r\n" +
            "    {\r\n" +
            "      \"metaVital\": {\r\n" +
            "        \"id\": 1,\r\n" +
            "        \"name\": \"BP\"\r\n" +
            "      },\r\n" +
            "      \"observedValue\": \"200/120\",\r\n" +
            "      \"examinationTime\": \"2019-07-29T00:00:00.000+0000\",\r\n" +
            "      \"metaVitalUnit\": {\r\n" +
            "        \"id\": 1,\r\n" +
            "        \"name\": \"mmHg\",\r\n" +
            "        \"lowerBound\": null,\r\n" +
            "        \"upperBound\": null,\r\n" +
            "        \"description\": null,\r\n" +
            "        \"defaultValue\": null\r\n" +
            "      }\r\n" +
            "    }\r\n" +
            "  ]\r\n" +
            "}";

    public static final String API_UPDATE_VITAL_APPOINTMENT = "{\r\n" +
            "  \"id\": 20,\r\n" +
            "  \"vitals\": [\r\n" +
            "    {\r\n" +
            "      \"metaVital\": {\r\n" +
            "        \"id\": 1,\r\n" +
            "        \"name\": \"BP\"\r\n" +
            "      },\r\n" +
            "      \"observedValue\": \"200/120\",\r\n" +
            "      \"examinationTime\": \"2019-07-29T00:00:00.000+0000\",\r\n" +
            "      \"metaVitalUnit\": {\r\n" +
            "        \"id\": 1,\r\n" +
            "        \"name\": \"mmHg\",\r\n" +
            "        \"lowerBound\": null,\r\n" +
            "        \"upperBound\": null,\r\n" +
            "        \"description\": null,\r\n" +
            "        \"defaultValue\": null\r\n" +
            "      }\r\n" +
            "    }\r\n" +
            "  ]\r\n" +
            "}";

    public static final String API_PHARMACY_CREATE = "{\n" +
            "\"name\":\"virtual pharmacy\",\n" +
            "\"invoiceDelivered\":true,\n" +
            "\"medicineDelivered\":true,\n" +
            "\"facility\":{\n" +
            "  \"id\":103\n" +
            "}\n" +
            "}";
    public static final String ADD_TOKEN =  "{\r\n" + 
    		"\"token\": \"Nlcl9uYW1lIjoiMiIsInJvbGVzIjpbIlNJV\",\r\n" + 
    		"\"deviceName\": \"iPhone\",\r\n" + 
    		"\"tokenKey\": \"string\",\r\n" + 
    		"\"deviceType\":\"phone\"\r\n" + 
    		"}";
    public static final String UNREGISTER_USER = "{\r\n" + 
    		"  \"token\": \"Nlcl9uYW1lIjoiMiIsInJvbGVzIjpbIlNJV\"\r\n" + 
    		"}";
	public static final String API_USER_ADD = "{\r\n" + 
			"\"domain\": \"him\",\r\n" + 
			"\"loginName\": \"9891262879\",\r\n" + 
			"\"password\": \"123123123\",\r\n" + 
			"\"phone\": \"9891262969\",\r\n" + 
			"\"roles\": [\r\n" + 
			"\"ADMIN\"\r\n" + 
			"]\r\n" + 
			"}";
	public static final String API_FACEBOOK_USER_ASSOCIATES = "{\r\n" + 
			"\"loginName\": \"himanshuu\",\r\n" + 
			"\"facebookAccessToken\": \"EAAFY9p2fyrQBAC4ONodGZABlI3KGqpCLPU5y6CF1ZA8IJvpUEu0WsgW8Fltl1qtP3yNfajkHj4aXvyEG5LJ3ZCK9iyA4NRz21IngPBcSSTSbc75SBnrABSD0QrlbgMZA2ZAsPZALiZBYHVbo41chSzkOhZADS5VFhN71yZCI2K0DVugZDZD\",\r\n" + 
			"\"password\": \"password\"\r\n" + 
			"}";

	public static final String API_FACEBOOK_USER_LOGIN = "{\r\n" + 
			"\"facebookAccessToken\": \"EAAFY9p2fyrQBAC4ONodGZABlI3KGqpCLPU5y6CF1ZA8IJvpUEu0WsgW8Fltl1qtP3yNfajkHj4aXvyEG5LJ3ZCK9iyA4NRz21IngPBcSSTSbc75SBnrABSD0QrlbgMZA2ZAsPZALiZBYHVbo41chSzkOhZADS5VFhN71yZCI2K0DVugZDZD\"\r\n" + 
			"}";
	
	public static final String  API_USER_UPDATE = "{\r\n" + 
			"\"firstName\": \"Ankit\",\r\n" +
			"\"lastName\": \"Sharma\",\r\n" +
			"\"email\": \"ankit.sharma@test.com\"\r\n" +
			"}";
}
