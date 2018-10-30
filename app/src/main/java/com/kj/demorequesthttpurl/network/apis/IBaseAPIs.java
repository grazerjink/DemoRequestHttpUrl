package com.kj.demorequesthttpurl.network.apis;

public interface IBaseAPIs {

    String METHOD_POST = "POST";
    String METHOD_GET = "GET";
    String METHOD_PUT = "PUT";
    String METHOD_DELETE = "DELETE";

//    String TOKEN = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJodHRwOi8vc2Fhc3BpZW5zLnNwZXJhbnphaW5jLm5ldDo5MDAyL2FwaS92MS9Mb2dpbiIsImlhdCI6MTU0MDM3NTU0NywiZXhwIjoyMTYwMDE1NDAzNzU1NDcsIm5iZiI6MTU0MDM3NTU0NywianRpIjoiYVdBRThEdkVyVE5VMHdRciIsInN1YiI6MSwicHJ2IjoiODdlMGFmMWVmOWZkMTU4MTJmZGVjOTcxNTNhMTRlMGIwNDc1NDZhYSJ9.lHJSLxckaW2XRl1ydkLfP5SFWiGji1vdoPMoPKUkFGg";
    String TOKEN = "Dzxn-49OC8BWJqp4kkUKVAW2fCU_AUcNtDhXTRTlrthRFxcYgujDEon63hi2Z2bfg6B2i9H3y6FcblvBgVDCLFb6sU1X-UJSVNidQp5GxsgZaDawMNp98MuZT1ganvoWscp7wVTFEvq077pdMgpN0RVqugFiTJfa6jE0UHGFYir7ZAt_ayYOy_xa2yyowX4D-6bV9OAgnDddBP67IexMrDFn4C3u4itYXgghVa3-JcA8o5RSLBPrf5zILm8gbgQPShgsc4WknoAQurU71S-ud928dlOTRACuKp73ajEFO0P_4kOkRcZDwUNFxpTIGqzug2j4SVeC-rD5pZXeufh3G0bybiw7JyEicjsCZN3OpPZtbYKM8otRzItKwbJtG16Wff17AU8_ynX27_KvM50Kajp8qsKS7PR3KT6WR-fFa9nStkx1VEHLThRUlFhCpTaCDbd5aSNE3-JF-r1DZXa85ZraHFgHYh2oO9IDfOgzCIKFBH4gTc-NLQ24WunyHhBnc0t7yQ6ZPJNkR6vx7GkzLw";
//    String TOKEN = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpZCI6IjUiLCJqdGkiOiI1IiwiaWF0IjoxNTQwNzk0ODk3LCJuYmYiOjE1NDA3OTQ4OTcsImV4cCI6MzYxNTQwNzk0ODk3fQ.esFoxedCzikVMWbUAxy_S1bVxIqkrPjLu67NDr98TOQ";
    String BASE_URL = "http://saaspiens.speranzainc.net:9002/api/v1/";
    String KOI_URL = "http://128.199.64.204:7171/api/";

    String API_LOGIN = BASE_URL + "Login";
    String API_CREATE_TAX_GROUP = BASE_URL + "TaxGroupCreate";
    String API_FACE_DETECT = "https://westcentralus.api.cognitive.microsoft.com/face/v1.0/detect?returnFaceId=true&returnFaceLandmarks=true&returnFaceAttributes=age,gender,smile,glasses,facialHair,emotion,headPose,accessories,blur,exposure,hair,makeup,noise,occlusion";
//    String API_UPDATE_PROFILE = "http://125.253.113.13:81/api/Account/UserInfo";
    String API_UPDATE_PROFILE = "http://10.0.0.46:8080/api/Account/UserInfo";
    String API_CREATE_REPORT = KOI_URL + "create-report";
}
